package com.wangqin.globalshop.order.app.controller.shipping;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingTrackDO;
import com.wangqin.globalshop.common.utils.DateUtil;
import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.order.app.service.shipping.haihu.IHaihuService;
import com.wangqin.globalshop.order.app.service.shipping.IShippingOrderService;
import com.wangqin.globalshop.order.app.service.shipping.IShippingTrackService;
import net.sf.ezmorph.bean.MorphDynaBean;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/tpl")
public class ThirdPartyLogisticsController {
    @Autowired
    private IShippingOrderService shippingOrderService;
    @Autowired
    private IShippingTrackService shippingTrackService;
    //    @Autowired
//    private ShippingTrackYuntongMapper shippingTrackYuntongMapper;
    @Autowired
    private IHaihuService haiHuService;

    /**
     * 邮客p2 回调
     */
    @RequestMapping("/youkeP2CallBack")
    @ResponseBody
    public Object youkeP2CallBack(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            InputStream in = request.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String jsonStr = br.readLine();

            JSONObject jsonObject = JSONObject.fromObject(jsonStr);
            Map<String, Object> parameMap = (Map<String, Object>) JSONObject.toBean(jsonObject, Map.class);
            MorphDynaBean parameMorphDynaBean = (MorphDynaBean) parameMap.get("param");
            String shippingNo = (String) parameMorphDynaBean.get("oversea_express_no");    //包裹号
            Integer status = (Integer) parameMorphDynaBean.get("status");                //包裹状态
            String tplPkgStatus = null;
            switch (status) {
                case 0:
                    tplPkgStatus = "未预报已入库";
                    break;
                case 1:
                    tplPkgStatus = "预报的未入库";
                    break;
                case 2:
                    tplPkgStatus = "已预报已入库";
                    break;
                case 3:
                    tplPkgStatus = "入库异常";
                    break;
            }

            if (shippingNo != null) {
                ShippingOrderDO selShippingOrder = shippingOrderService.selectByShippingNo(shippingNo);
                if (selShippingOrder != null) {
                    selShippingOrder.setTplPkgStatus(tplPkgStatus);
                    selShippingOrder.setGmtModify(new Date());
                    shippingOrderService.update(selShippingOrder);
                }
            }

            br.close();
            in.close();

            result.put("code", 0);
            result.put("message", "");
        } catch (Exception e) {
            result.put("code", 1);
            result.put("message", "邮客P2回调异常");
        }
        return result;
    }

    /**
     * 邮客p4 回调
     */
    @RequestMapping("/youkeP4CallBack")
    @ResponseBody
    public Object youkeP4CallBack(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        try (InputStream in = request.getInputStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String jsonStr = br.readLine();

            JSONObject jsonObject = JSONObject.fromObject(jsonStr);
            JSONObject param = jsonObject.getJSONObject("param");

            String logisticNo = param.getString("transfer_id");
            Integer status = param.getInt("status");

            ShippingTrackDO selShippingTrack = shippingTrackService.getByLogisticNoAndLogisticsStatus(logisticNo, status);

            ShippingTrackDO shippingTrack = new ShippingTrackDO();
            shippingTrack.setLogisticNo(logisticNo);
            shippingTrack.setLogisticsStatus(status);

            if (status == 0) {
                shippingTrack.setTrackInfo("未出库");
            } else if (status == 1) {
                shippingTrack.setTrackInfo("发往国内");
            } else if (status == 2) {
                shippingTrack.setTrackInfo("递交航空公司");
            } else if (status == 3) {
                shippingTrack.setTrackInfo("抵达国内");
            } else if (status == 4) {
                shippingTrack.setTrackInfo("已转国内快递");
            } else if (status == 5) {
                shippingTrack.setTrackInfo("用户已签收");
            }

            shippingTrack.setShippingOrderNo(param.getString("customer_ref_no"));
            if (param.has("receive_time")) {
                shippingTrack.setReceiveTime(DateUtil.convertStr2Date(param.getString("receive_time"), DateUtil.DATE_PARTEN));
            }
            if (param.has("oversea_in_time")) {
                shippingTrack.setOverseaInTime(DateUtil.convertStr2Date(param.getString("oversea_in_time"), DateUtil.DATE_PARTEN));
            }
            if (param.has("oversea_out_time")) {
                shippingTrack.setOverseaOutTime(DateUtil.convertStr2Date(param.getString("oversea_out_time"), DateUtil.DATE_PARTEN));
            }
            if (param.has("oversea_on_transfer_time")) {
                shippingTrack.setOverseaOnTransferTime(DateUtil.convertStr2Date(param.getString("oversea_on_transfer_time"), DateUtil.DATE_PARTEN));
            }
            if (param.has("inland_in_time")) {
                shippingTrack.setInlandInTime(DateUtil.convertStr2Date(param.getString("inland_in_time"), DateUtil.DATE_PARTEN));
            }
            if (param.has("inland_out_time")) {
                shippingTrack.setInlandOutTime(DateUtil.convertStr2Date(param.getString("inland_out_time"), DateUtil.DATE_PARTEN));
            }
            if (param.has("buyer_sign_time")) {
                shippingTrack.setBuyerSignTime(DateUtil.convertStr2Date(param.getString("buyer_sign_time"), DateUtil.DATE_PARTEN));
            }
//            if (param.has("inland_express_id")) {
//                shippingTrack.setInlandExpressId(param.getString("inland_express_id"));
//            }
//            if (param.has("inland_express_no")) {
//                shippingTrack.setInlandExpressNo(param.getString("inland_express_no"));
//            }
            if (param.has("weight")) {
                shippingTrack.setWeight(param.getDouble("weight"));
            }
            if (param.has("volume")) {
                shippingTrack.setVolume(param.getString("volume"));
            }
            if (param.has("totalfee")) {
                shippingTrack.setTotalfee(param.getDouble("totalfee"));
            }
            if (param.has("air_take_off")) {
                shippingTrack.setAirTakeOff(param.getString("air_take_off"));
            }
            if (param.has("airlines")) {
                shippingTrack.setAirlines(param.getString("airlines"));
            }
            if (param.has("flight")) {
                shippingTrack.setFlight(param.getString("flight"));
            }
//            if (param.has("master_waybill_no")) {
//                shippingTrack.setMasterWaybillNo(param.getString("master_waybill_no"));
//            }
//            if (param.has("api_order_type")) {
//                shippingTrack.setApiOrderType(param.getString("api_order_type"));
//            }

            if (selShippingTrack == null) {
                shippingTrack.setGmtCreate(new Date());
                shippingTrack.setGmtModify(new Date());
                shippingTrackService.insert(shippingTrack);
            } else {
                shippingTrack.setId(selShippingTrack.getId());
                shippingTrack.setGmtModify(new Date());
                shippingTrackService.update(shippingTrack);
            }

            result.put("code", 0);
            result.put("message", "");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", 1);
            result.put("message", "邮客P4回调异常");
        }
        return result;
    }

    /**
     * 运通回调(返回和请求参数严格按照文档格式来做)
     */
    @RequestMapping("/yunTongCallBack")
    @ResponseBody
    public Object yunTongCallBack(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        try (InputStream in = request.getInputStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String jsonStr = br.readLine();
            //logger.error("运通回调参数test：", jsonStr);
            JSONObject param = JSONObject.fromObject(jsonStr);
            //logger.error("运通回调参数解析test：", param);
            String yundanCode = param.getString("Yundan_code");
            String statusName = param.getString("Status_name");
            Date statusDatetime = DateUtil.convertStr2Date(param.getString("Status_datetime")
                    .replace("T", " "), DateUtil.DATE_PARTEN);

            ShippingTrackDO shippingTrack = new ShippingTrackDO();
            shippingTrack.setShippingOrderNo(yundanCode);
            shippingTrack.setLogisticNo(yundanCode);
            if (statusName.startsWith("离开")) {
                shippingTrack.setLogisticsStatus(1);
                shippingTrack.setOverseaOutTime(statusDatetime);
                shippingTrack.setTrackInfo("发往国内");
            } else if (statusName.startsWith("抵达")) {
                shippingTrack.setLogisticsStatus(3);
                shippingTrack.setInlandInTime(statusDatetime);
                shippingTrack.setTrackInfo("抵达国内");
            } else if (statusName.contains("已收货")) {
                shippingTrack.setLogisticsStatus(4);
                shippingTrack.setInlandOutTime(statusDatetime);
                shippingTrack.setTrackInfo("已转国内快递");
            }
//            if (param.has("Trans_company_name")) {
//                shippingTrack.setInlandExpressId(param.getString("Trans_company_name"));
//            }
//            if (param.has("Trans_code")) {
//                shippingTrack.setInlandExpressNo(param.getString("Trans_code"));
//            }

            if (shippingTrack.getLogisticsStatus() != null) {
                ShippingTrackDO selShippingTrack = shippingTrackService
                        .getByLogisticNoAndLogisticsStatus(shippingTrack.getLogisticNo(), shippingTrack.getLogisticsStatus());
                if (selShippingTrack == null) {
                    shippingTrack.setGmtCreate(new Date());
                    shippingTrack.setGmtModify(new Date());
                    shippingTrackService.insert(shippingTrack);
                }
            }

//            ShippingTrackYuntong shippingTrackMiddle = new ShippingTrackYuntong();
//            shippingTrackMiddle.setYundanCode(yundanCode);
//            shippingTrackMiddle.setStatusName(statusName);
//            ShippingTrackYuntong selShippingTrackMiddle = shippingTrackYuntongMapper.selectByLogisticNo(shippingTrackMiddle);
//            if (param.has("Key")) {
//                shippingTrackMiddle.setKey(param.getString("Key"));
//            }
//            if (param.has("Action")) {
//                shippingTrackMiddle.setAction(param.getString("Action"));
//            }
//            if (param.has("Status_datetime")) {
//                shippingTrackMiddle.setStatusDatetime(statusDatetime);
//            }
//            if (param.has("Trans_company_name")) {
//                shippingTrackMiddle.setTransCompanyName(param.getString("Trans_company_name"));
//            }
//            if (param.has("Trans_code")) {
//                shippingTrackMiddle.setTransCode(param.getString("Trans_code"));
//            }
//
//            if (selShippingTrackMiddle == null) {
//                shippingTrackMiddle.setGmtCreate(new Date());
//                shippingTrackMiddle.setGmtModify(new Date());
//                shippingTrackYuntongMapper.insert(shippingTrackMiddle);
//            } else {
//                shippingTrackMiddle.setId(selShippingTrackMiddle.getId());
//                shippingTrackMiddle.setGmtModify(new Date());
//                shippingTrackYuntongMapper.updateSelectiveById(shippingTrackMiddle);
//            }

            result.put("Code", 100);
            result.put("Msg", "success");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("Code", 199);
            result.put("Msg", "运通快递回调异常");
        }
        return result;
    }

    //测试抓取海狐推送物流状态
    @RequestMapping("/shippingTrack")
    @ResponseBody
    public Object shippingTrack(Long id) throws ParseException {
        JsonResult<String> result = new JsonResult<>();
        //haiHuService.shippingTrack("1708061624477508435176460");
        //haiHuService.createOrder(id);
        List<ShippingOrderDO> list = shippingOrderService.queryAllInHaihu();
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                haiHuService.shippingTrack(list.get(i).getShippingNo());
            }
        }
        return result.buildSuccess("");
    }
}
