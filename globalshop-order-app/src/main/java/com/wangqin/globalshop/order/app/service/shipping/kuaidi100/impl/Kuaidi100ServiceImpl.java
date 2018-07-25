package com.wangqin.globalshop.order.app.service.shipping.kuaidi100.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.LogisticCompanyDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingTrackDO;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.utils.Md5Util;
import com.wangqin.globalshop.order.app.kuaidi_bean.*;
import com.wangqin.globalshop.order.app.kuaidi_bean._4px.LogisticsStatus;
import com.wangqin.globalshop.order.app.service.shipping.IShippingOrderService;
import com.wangqin.globalshop.order.app.service.shipping.IShippingTrackService;
import com.wangqin.globalshop.order.app.service.shipping.kuaidi100.IKuaidi100Service;
import com.wangqin.globalshop.order.app.util.HttpClient;
import com.wangqin.globalshop.order.app.util.JacksonHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class Kuaidi100ServiceImpl implements IKuaidi100Service {

    private static Logger logger = LoggerFactory.getLogger(Kuaidi100ServiceImpl.class);

    private static final String KUAIDI_100_POST_URL = "http://www.kuaidi100.com/poll";
    /**
     * 快递轨迹查询URL
     */
    private static final String KUAIDI_100_QUERY_URL = "http://poll.kuaidi100.com/poll/query.do";
    private static final String KUAIDI_100_POST_CALLBACK = "https://test.buyer007.cn/kuaidi100/callback";
    /**
     * KEY
     */
    private static final String KUAIDI_100_QUERY_CUSTOMER = "788C8EBE2B8E2E6C9D699D9C459C9AD2";
    private static final String KUAIDI_100_KEY = "zUNguBRY2899";
    /**
     * 根据运单号查询快递公司代码URL
     */
    private static final String COM_QUERY_URL = "http://www.kuaidi100.com/autonumber/autoComNum?text=";
    private static final Pattern COM_PATTERN = Pattern.compile("\"comCode\":\"([a-z].+?)\"");
    @Autowired
    private IShippingOrderService shippingOrderService;

    @Autowired
    private IShippingTrackService shippingTrackService;

    /**
     * 物流公司Code映射
     * key-公司名称 value-对应的快递100 code
     */
    private Map<String, String> companyCodeMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        Kuaidi100ServiceImpl impl = new Kuaidi100ServiceImpl();
        impl.queryShippingTrack("yunda", "3101738504868");
    }

    @Override
    public Kuaidi100ShippingTrackResult queryShippingTrack(String shippingNo, String com, String num) {
        try {
            String paramStr = "{\"com\":\"" + com + "\",\"num\":\"" + num + "\"}";
            String key = KUAIDI_100_KEY;
            String customer = KUAIDI_100_QUERY_CUSTOMER;
            String signedStr = paramStr + key + customer;
            String sign = Md5Util.string2MD5(signedStr).toUpperCase();

            logger.debug("主动查询参数 shippingNo:{}, paramStr:{}", shippingNo, paramStr);
            Map<String, String> params = new HashMap<>();
            params.put("customer", customer);
            params.put("sign", sign);
            params.put("param", paramStr);
            HttpClient.HttpResponse response = HttpClient.httpPost(KUAIDI_100_QUERY_URL, params);

            String resultStr = response.getStringResult("utf-8");
            if (StringUtils.isEmpty(resultStr)) {
                logger.debug("主动查询失败 shippingNo:{}, num:{}", shippingNo, num);
                return null;
            }
            logger.debug("主动查询结果 shippingNo:{}, num:{}, response:{} ", shippingNo, num, resultStr);
            return JacksonHelper.fromJSON(resultStr, Kuaidi100ShippingTrackResult.class);
        } catch (Exception e) {
            logger.error("查询出现异常 shippingNo:{}, num:{}", shippingNo, num, e);
            return null;
        }
    }

    @Override
    public Kuaidi100ShippingTrackResult queryShippingTrack(String com, String num) {
        return this.queryShippingTrack(null, com, num);
    }

    @Override
    public Kuaidi100ShippingTrackResult queryShippingTrack(String shippingNo) {
        if (shippingNo == null) {
            throw new ErpCommonException("发货单号为空");
        }
        // 根据shippingNo查询ShippingOrder
        ShippingOrderDO shippingOrder = shippingOrderService.selectByShippingNo(shippingNo);
        if (shippingOrder == null) {
            throw new ErpCommonException("没有该发货单");
        }
        String logisticCompany = shippingOrder.getLogisticCompany();
        // 根据物流公司名获取其对应的快递100 Code
        String com = codeInKuaidi100(logisticCompany);
        if(com == null) {
            throw new ErpCommonException("不支持该物流公司");
        }
        String num = shippingOrder.getLogisticNo();
        if (num == null) {
            throw new ErpCommonException("物流单号信息缺失");
        }
        return this.queryShippingTrack(shippingNo, com, num);
    }

    /**
     * 根据快递公司名获取公司code
     *
     * @param companyName
     * @return
     */
    private String codeInKuaidi100(String companyName) {
        if (StringUtils.isEmpty(companyName)) {
            return null;
        }
        // 从缓存中取出code
        String code = this.companyCodeMap.get(companyName);

        // 缓存不存在
        if (code == null) {
            List<LogisticCompanyDO> list = shippingOrderService.queryLogisticCompany();
            if (list == null) {
                return null;
            }
            // 保存到缓存
            for (LogisticCompanyDO company : list) {
                String name = company.getName();
                String value = company.getCodeInKuaidi100();
                if (StringUtils.isEmpty(name)) {
                    continue;
                }
                // 为空，也要缓存，避免缓存穿透
                if (StringUtils.isEmpty(value)) {
                    this.companyCodeMap.put(name, "");
                } else {
                    this.companyCodeMap.put(name, value);
                }
            }
            code = this.companyCodeMap.get(companyName);
        }
        return code;
    }

    /**
     * 根据要查询的快递单号nu，查询快递公司代码com
     *
     * @param nu 快递单号
     * @return 快递公司代码集合（自动查询结果不唯一）
     */
    private List<String> queryComByNu(String nu) {
        List<String> data = new ArrayList<>();
        String result = HttpClient.httpGet(COM_QUERY_URL + nu).getStringResult();
        logger.debug(result);
        Matcher m = COM_PATTERN.matcher(result);
        while (m.find()) {
            data.add(m.group(1));
            logger.debug(m.group(1));
        }
        return data;
    }

    /**
     * 根据发货单号获取指定发货单的轨迹信息
     *
     * @param shippingNo
     */
    @Override
    public void fetchTrackByShippingNo(String shippingNo) {
        if (StringUtils.isEmpty(shippingNo)) {
            return;
        }
        // 根据shippingNo查询ShippingOrder
        ShippingOrderDO order = shippingOrderService.selectByShippingNo(shippingNo);

        this.fetchTrackByShippingOrder(order);
    }


    /**
     * 根据发货单获取指定发货单的轨迹信息
     *
     * @param order
     */
    @Override
    public void fetchTrackByShippingOrder(ShippingOrderDO order) {
        if (order == null) {
            logger.error("查询快递100 ShippingOrder为null");
            return;
        }
        String shippingNo = order.getShippingNo();
        // 物流公司code
        String comCode = this.codeInKuaidi100(order.getLogisticCompany());
        if (StringUtils.isEmpty(comCode)) {
            logger.error("查询快递100 comCode为null，shippingNo: " + shippingNo);
            return;
        }

        // 物流单号
        String logisticsNo = order.getLogisticNo();

        if (StringUtils.isEmpty(logisticsNo)) {
            logger.error("查询快递100 logisticsNo为null，shippingNo: " + shippingNo);
            return;
        }

        Kuaidi100ShippingTrackResult result = this.queryShippingTrack(shippingNo, comCode, logisticsNo);
        try {
            this.handleTrackList(result);
        } catch (Exception e) {
            logger.error("查询快递100 异常！shippingNo: " + shippingNo, e);
            e.printStackTrace();
        }
    }

    /**
     * 处理物流查询结果信息并更新数据库
     *
     * @param result
     */
    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public void handleTrackList(Kuaidi100ShippingTrackResult result) {
        // 获取物流单号
        String logisticNo = result.getNu();
        if (StringUtils.isEmpty(logisticNo)) {
            logger.error("kuaidi100[handleTrackingNodeForOrderDetail] logisticsNo为null");
            return;
        }
        // 获取物流轨迹信息
        List<Kuaidi100ShippingTrackResultNode> data = result.getData();
        // 反转物流轨迹信息，时间升序排列
        if (data != null && data.size() > 0) {
            Collections.reverse(data);
        }
        // 根据 logisticNo 查出 shippingOrder
        ShippingOrderDO order = shippingOrderService.selectByLogisticNo(logisticNo);

        if (order == null) {
            logger.error("kuaidi100[handleTrackingNodeForOrderDetail] ShippingOrder为null logistics:" + logisticNo);
            return;
        }
        // 根据 logisticNo 查出物流表中，该订单的所有物流节点
        List<ShippingTrackDO> shippingReadyTracks = shippingTrackService.queryShippingTrack(logisticNo);
        ArrayList<String> trackInfoList = new ArrayList<>();
        if (shippingReadyTracks != null) {
            for (ShippingTrackDO shippingTrackDO : shippingReadyTracks) {
                trackInfoList.add(shippingTrackDO.getTrackInfo());
            }
        }


        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<ShippingTrackDO> newTrackList = new ArrayList<>();
        boolean isFinished = false;
        for (Kuaidi100ShippingTrackResultNode item : data) {
            String content = item.getContext().replaceAll("\n|\t|\r", " ");
            if (trackInfoList.contains(content)) {
                continue;
            }
            LogisticsStatus status = LogisticsStatus.CHINA_DISPATCHED;

            if (!isFinished) {
                isFinished = this.isContainFinishText(content);
                if (isFinished) {
                    status = LogisticsStatus.SIGNED;
                }
            }
            Date timeDate = null;
            try {
                timeDate = date.parse(item.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            ShippingTrackDO newTrack = new ShippingTrackDO();
            newTrack.setLogisticNo(logisticNo);
            newTrack.setLogisticNo(logisticNo);
            newTrack.setLogisticsStatus(status.getValue());
            newTrack.setOverseaInTime(timeDate);
            newTrack.setTrackInfo(content);
            newTrack.setGmtCreate(timeDate);
            newTrack.setGmtModify(new Date());
            newTrackList.add(newTrack);
        }
        // 把新节点插入到库中
        if (newTrackList.size() > 0) {
            for (ShippingTrackDO shippingTrackDO : newTrackList) {
                shippingTrackService.insert(shippingTrackDO);
            }

        }
        // 已签收，完结订单
        if (isFinished) {
            ShippingOrderDO shippingOrder = new ShippingOrderDO();
            shippingOrder.setShippingNo(logisticNo);
            shippingOrderService.updateStatusByShippingNo(logisticNo);
        }
    }

    /**
     * 订阅
     *
     * @param request
     * @return
     */
    private PostResponse postLogistics(PostRequest request) {
        request.addCallbackParam(KUAIDI_100_POST_CALLBACK);
        Map<String, String> params = new HashMap<>();
        params.put("schema", "json");
        params.put("param", JacksonHelper.toJSON(request));
        HttpClient.HttpResponse response = HttpClient.httpPost(KUAIDI_100_POST_URL, params);
        return JacksonHelper.fromJSON(response.getStringResult("utf-8"), PostResponse.class);
    }

    /**
     * 判断是否包含“签收”关键字
     *
     * @param content
     * @return
     */
    public boolean isContainFinishText(String content) {
        if (!StringUtils.isEmpty(content) && !content.contains("失败") && !content.contains("准备签收")
                && (content.toUpperCase().contains("DELIVERED") || content.toUpperCase().contains("FINAL DELIVERY")
                || content.contains("签收") || content.contains("送达成功") || content.contains("已妥投")
                || content.contains("用户已领取") || content.contains("已投到") || content.contains("用户已取件"))) {
            return true;
        }
        return false;
    }

    @Override
    public void subscribe(String shippingNo) {
        if (StringUtils.isEmpty(shippingNo)) {
            return;
        }
        // 查出shippingNo
        ShippingOrderDO order = shippingOrderService.selectByShippingNo(shippingNo);
        this.subscribe(order);
    }


    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public void subscribe(ShippingOrderDO order) {
        if (order == null) {
            logger.debug("订阅快递100 ShippingOrder为null");
            return;
        }
        String shippingNo = order.getShippingNo();
        // 物流公司code
        String comCode = this.codeInKuaidi100(order.getLogisticCompany());
        if (StringUtils.isEmpty(comCode)) {
            logger.debug("订阅快递100 comCode为null，shippingNo: " + shippingNo);
            return;
        }
        // 物流单号
        String logisticsNo = order.getLogisticNo();

        if (StringUtils.isEmpty(logisticsNo)) {
            logger.debug("订阅快递100 ShippingOrder为null，shippingNo: " + shippingNo);
            return;
        }
        //已经有物流公司名称的可以指定物流公司名称
        if (!StringUtils.isEmpty(comCode)) {
            //已经有物流公司名称的可以指定物流公司名称
            PostRequest request = new PostRequest(comCode, logisticsNo);
            PostResponse postResponse = this.postLogistics(request);
            logger.error("订阅快递100 已经有物流公司名称,开始订阅\t[" + comCode + "]\t[" + logisticsNo + "]");
            if ((postResponse.getResult() && "200".equals(postResponse.getReturnCode())) ||
                    (postResponse.getMessage() != null && postResponse.getMessage().contains("重复订阅"))) {
                // 修改数据库subscribe
                ShippingOrderDO updateOrder = new ShippingOrderDO();
                updateOrder.setId(order.getId());
                shippingOrderService.update(updateOrder);
                logger.error("订阅快递100 已经有物流公司名称,结束订阅\t[" + comCode + "]\t[" + logisticsNo + "],订阅结果:\t" + postResponse);
            }
        } else {
            List<String> coms = queryComByNu(logisticsNo);
            if (coms.size() > 0) {
                for (String com : coms) {
                    PostRequest request = new PostRequest(com, logisticsNo);
                    PostResponse postResponse = this.postLogistics(request);
                    logger.error("订阅快递100 开始订阅\t[" + com + "]\t[" + logisticsNo + "]");
                    if (postResponse.getResult() && "200".equals(postResponse.getReturnCode())) {
                        // 修改数据库subscribe
                        ShippingOrderDO updateOrder = new ShippingOrderDO();
                        updateOrder.setId(order.getId());
                        shippingOrderService.update(updateOrder);
                        logger.error("订阅快递100 结束订阅\t[" + com + "]\t[" + logisticsNo + "],订阅结果:\t" + postResponse);
                        break;
                    }
                }
            } else {
                logger.error("单号(" + logisticsNo + ")没有查到对应的物流公司");
            }
        }
    }

    @Override
    public NoticeResponse handleCallback(String json) {
        logger.error("---> 快递100 推送物流过来  :" + json);
        NoticeResponse response = new NoticeResponse();
        if (StringUtils.hasLength(json)) {
            NoticeRequest request = JacksonHelper.fromJSON(json, NoticeRequest.class);
            try {
                // 保存到数据库
                Kuaidi100ShippingTrackResult result = request.getLastResult();

                String status = request.getStatus();
                if ("abort".equals(status)) {
//					this.handleAbort(result, response);
                } else {
                    this.handleTrackList(result);
                }

            } catch (Exception e) {
                response.setMessage("保存到数据库出错");
                response.setResult(false);
                response.setReturnCode("500");
                logger.error("", e);
            }
        } else {
            response.setMessage("回传参数为空");
            response.setResult(false);
            response.setReturnCode("500");
        }
        return response;
    }

    @Override
    public void fetchTrack(String logisticsNo) {
        // TODO Auto-generated method stub

    }
}
