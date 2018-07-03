package com.wangqin.globalshop.order.app.service.kuaidi100.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.LogisticCompanyDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingTrackDO;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.utils.Md5Util;
import com.wangqin.globalshop.order.app.kuaidi_bean.*;
import com.wangqin.globalshop.order.app.kuaidi_bean._4px.LogisticsStatus;
import com.wangqin.globalshop.order.app.service.kuaidi100.IKuaidi100Service;
import com.wangqin.globalshop.order.app.service.shipping.IShippingOrderService;
import com.wangqin.globalshop.order.app.service.shipping.IShippingTrackService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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
	
	// logger
	protected Logger logger = LogManager.getLogger(getClass());
	
	private static final String kuaidi100_post_url = "http://www.kuaidi100.com/poll";
	private static final String kuaidi100_query_url = "http://poll.kuaidi100.com/poll/query.do";
	private static final String kuaidi100_post_callback = "https://erp.buyer007.cn/kuaidi100/callback";
//	private static final String kuaidi100_post_callback = "https://erp.buyer007.com/kuaidi100/callback";


	private static final String kuaidi100_query_constomer = "788C8EBE2B8E2E6C9D699D9C459C9AD2";
	private static final String kuaidi100_key = "zUNguBRY2899";
	
	public static final String comQueryUrl = "http://www.kuaidi100.com/autonumber/autoComNum?text=";
	public static final Pattern comPattern = Pattern.compile("\"comCode\":\"([a-z].+?)\"");
	
	@Autowired
    IShippingOrderService shippingOrderService;
	
	@Autowired
	private IShippingTrackService shippingTrackService;
		
	// 物流公司Code映射 key:公司名称  value:对应的快递100code
	private Map<String,String> companyCodeMap = new ConcurrentHashMap<String, String>();
	
	
	static String emptyValue = "";

	@Override
	public void subscribe(String shippingNo) {
		if (StringUtils.isEmpty(shippingNo)) {
			return;
		}
		ShippingOrderDO order = new ShippingOrderDO();
		order.setShippingNo(shippingNo);
		// 查出shippingNo
		order = shippingOrderService.selectOne(order);
		this.subscribe(order);
		
	}


	@Override
	@Transactional(rollbackFor = ErpCommonException.class)
	public void subscribe(ShippingOrderDO order) {
		String shippingNo = order.getShippingNo();
		if (order == null) {
			logger.error("订阅快递100 ShippingOrder为null，shippingNo: " + shippingNo);
			return;
		}
		
//		Kuaidi100Status status = Kuaidi100Status.statusOfValue(order.getSubscribeKuaidi100());
//		if (status != Kuaidi100Status.Need_Subscribe) {
//			logger.error("订阅快递100 subscribe_kuaidi100状态为不是Need_Subscribe，shippingNo: " + shippingNo);
//			return;
//		}
		
		// 物流公司code
		String comCode = this.codeInKuaidi100(order.getLogisticCompany());
		if (StringUtils.isEmpty(comCode)) {
			logger.error("订阅快递100 comCode为null，shippingNo: " + shippingNo);
			return;
		}
		// 物流单号
		String logisticsNo = order.getLogisticNo();
		
		if (StringUtils.isEmpty(logisticsNo)) {
			logger.error("订阅快递100 ShippingOrder为null，shippingNo: " + shippingNo);
			return;
		}
		
		//已经有物流公司名称的可以指定物流公司名称
		if(!StringUtils.isEmpty(comCode)){
			//已经有物流公司名称的可以指定物流公司名称
			PostRequest request = new PostRequest(comCode, logisticsNo);
			PostResponse postResponse = this.postLogistics(request);
			logger.error("订阅快递100 已经有物流公司名称,开始订阅\t[" + comCode + "]\t[" + logisticsNo + "]");
			if ((postResponse.getResult() && "200".equals(postResponse.getReturnCode())) || 
					(postResponse.getMessage() != null && postResponse.getMessage().contains("重复订阅"))){
				// 修改数据库subscribe
				ShippingOrderDO updateOrder = new ShippingOrderDO();
				updateOrder.setId(order.getId());
//				updateOrder.setSubscribeKuaidi100(Kuaidi100Status.Subscribed.getValue());
				shippingOrderService.update(updateOrder);
				
				logger.error("订阅快递100 已经有物流公司名称,结束订阅\t[" + comCode + "]\t[" + logisticsNo + "],订阅结果:\t" + postResponse);
			}
		}else{
			List<String> coms = queryCompany(logisticsNo);
			if (coms.size() > 0){
				for (String com : coms){
					PostRequest request = new PostRequest(com, logisticsNo);
					PostResponse postResponse = this.postLogistics(request);
					logger.error("订阅快递100 开始订阅\t[" + com + "]\t[" + logisticsNo + "]");
					if (postResponse.getResult() && "200".equals(postResponse.getReturnCode())){
						// 修改数据库subscribe
						ShippingOrderDO updateOrder = new ShippingOrderDO();
						updateOrder.setId(order.getId());
//						updateOrder.setSubscribeKuaidi100(Kuaidi100Status.Subscribed.getValue());
						shippingOrderService.update(updateOrder);
						
						logger.error("订阅快递100 结束订阅\t[" + com + "]\t[" + logisticsNo + "],订阅结果:\t" + postResponse);
						break;
					}
				}
			}else{
				logger.error("单号(" + logisticsNo + ")没有查到对应的物流公司");
			}
		}
	}

	@Override
	public void fetchTrack(String logisticsNo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void fetchTrackByShippingNo(String shippingNo) {
		if (StringUtils.isEmpty(shippingNo)) {
			return;
		}
		ShippingOrderDO order = new ShippingOrderDO();
		order.setShippingNo(shippingNo);
		// 查出shippingNo
		order = shippingOrderService.selectOne(order);
		
		this.fetchTrackByShippingOrder(order);
	}


	@Override
	public void fetchTrackByShippingOrder(ShippingOrderDO order) {
		String shippingNo = order.getShippingNo();
		if (order == null) {
			logger.error("查询快递100 ShippingOrder为null，shippingNo: " + shippingNo);
			return;
		}
//		Kuaidi100Status status = Kuaidi100Status.statusOfValue(order.getSubscribeKuaidi100());
//		if (status != Kuaidi100Status.Subscribed) {
//			logger.error("查询快递100 subscribe_kuaidi100状态不为Subscribed，shippingNo: " + shippingNo);
//			return;
//		}
		
		// 物流公司code
		String comCode = this.codeInKuaidi100(order.getLogisticCompany());
		if (StringUtils.isEmpty(comCode)) {
			logger.error("查询快递100 comCode为null，shippingNo: " + shippingNo);
			return;
		}
		
		// 物流单号
		String logisticsNo = order.getLogisticNo();
		
		if (StringUtils.isEmpty(logisticsNo)) {
			logger.error("查询快递100 ShippingOrder为null，shippingNo: " + shippingNo);
			return;
		}
		
		Result result = this.query(shippingNo, comCode, logisticsNo);
		try {
			this.handleTrackList(result);
		} catch (Exception e) {
			logger.error("查询快递100 异常！shippingNo: " + shippingNo, e);
			e.printStackTrace();
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
				Result result = request.getLastResult();

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

	
	// 订阅
	public PostResponse postLogistics(PostRequest request) {
		request.addCallbackParam(kuaidi100_post_callback);
		Map<String, String> params = new HashMap<String, String>();
		params.put("schema", "json");
		params.put("param", JacksonHelper.toJSON(request));
		HttpClient.HttpResponse response = HttpClient.httpPost(kuaidi100_post_url, params);
		return JacksonHelper.fromJSON(response.getStringResult("utf-8"), PostResponse.class);
	}
	
	// 查公司code
	public static List<String> queryCompany(String nu){
		List<String> data = new ArrayList<String>();
		String result = HttpClient.httpGet(comQueryUrl + nu).getStringResult();
		Matcher m = comPattern.matcher(result);
		while (m.find()){
			data.add(m.group(1));
		}
		return data;
	}
	
	public static void main(String[] args) {
		Kuaidi100ServiceImpl impl = new Kuaidi100ServiceImpl();
		impl.query("a", "yunda", "3101738504868");
	}
	
	// 查物流轨迹
	public Result query(String orderNo, String trackName, String trackNo) {
		try {
			logger.error("kuaidi100主动查询 orderNo:" + orderNo + ", trackNo:" + trackNo);
			// 已经有物流公司名称的可以指定物流公司名称
			String com = trackName;

			String paramStr = "{\"com\":\"" + com + "\",\"num\":\"" + trackNo + "\"}";
			String key = kuaidi100_key;
			String customer = kuaidi100_query_constomer;
			String signedStr = paramStr + key + customer;
			String sign = Md5Util.string2MD5(signedStr).toUpperCase();
			
			logger.error("kuaidi100主动查询参数 orderNo:" + orderNo + ", trackNo:" + trackNo + " paramStr:" + paramStr);

			Map<String, String> params = new HashMap<String, String>();
			params.put("customer", customer);
			params.put("sign", sign);
			params.put("param", paramStr);
			HttpClient.HttpResponse response = HttpClient.httpPost(kuaidi100_query_url, params);

			String resultStr = response.getStringResult("utf-8");
			if (StringUtils.isEmpty(resultStr)) {
				logger.error("kuaidi100主动查询失败 orderNo:" + orderNo + ", trackNo:" + trackNo);
				return null;
			}
			logger.error("kuaidi100主动查询结果 orderNo:" + orderNo + ", trackNo:" + trackNo + ", response: " + resultStr);
			Result result = (Result) JacksonHelper.fromJSON(resultStr, Result.class);

			return result;
		} catch (Exception e) {
			logger.error("kuaidi100主动查询，出现异常 orderNo:" + orderNo + ", trackNo:" + trackNo, e);
			return null;
		}
	}
	
	// 获取物流公司code
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
					this.companyCodeMap.put(name, emptyValue);
				} else {
					this.companyCodeMap.put(name, value);
				}
			}
			code = this.companyCodeMap.get(companyName);
		}

		return code;
	}
	@Transactional(rollbackFor = ErpCommonException.class)
	private void handleTrackList(Result result){
		String logisticsNo = result.getNu();
		if (StringUtils.isEmpty(logisticsNo)) {
			logger.error("kuaidi100[handleTrackingNodeForOrderDetail] logisticsNo为null");
			return;
		}
		
		List<ResultItem> data = result.getData();
		
		if (data != null && data.size() > 0) {
			Collections.reverse(data);
		}
		// 查出shippingOrder
		ShippingOrderDO order = new ShippingOrderDO();
		order.setLogisticNo(logisticsNo);
		order = shippingOrderService.selectOne(order);
		
		if (order == null) {
			logger.error("kuaidi100[handleTrackingNodeForOrderDetail] ShippingOrder为null logistics:" + logisticsNo);
			return;
		}
		
		String logisticNo = order.getLogisticNo();
		// 查出物流表中，该订单的物流节点
		// 查出所有的节点，避免重复
		List<ShippingTrackDO> shippingReadyTracks = shippingTrackService.queryShippingTrack(logisticNo);
		ArrayList<String> contentList = null;
		if (shippingReadyTracks != null && shippingReadyTracks.size() > 0) {
			contentList = new ArrayList<>(shippingReadyTracks.size());
			for (ShippingTrackDO item : shippingReadyTracks) {
				contentList.add(item.getTrackInfo());
			}
		}
		if (contentList == null) {
			contentList = new ArrayList<String>();
		}
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<ShippingTrackDO> newTrackList = new ArrayList<>();
		boolean isFinished = false;
		for (ResultItem item : data) {
			String content = item.getContext().replaceAll("\n|\t|\r", " ");
			if (contentList.contains(content)) {
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
			newTrack.setLogisticNo(logisticsNo);
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
	 * 是否包含“签收”关键字
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
}
