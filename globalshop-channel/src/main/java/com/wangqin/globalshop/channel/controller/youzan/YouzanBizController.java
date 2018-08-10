package com.wangqin.globalshop.channel.controller.youzan;

import com.wangqin.globalshop.channel.dal.youzan.YouzanCommonResponse;
import com.wangqin.globalshop.common.base.BaseController;
import com.wangqin.globalshop.common.base.BaseDto;
import com.wangqin.globalshop.common.utils.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Create by 777 on 2018/8/10
 */

@Controller
@RequestMapping("/youzan")
public class YouzanBizController extends BaseController {


	public static final String youzan_client_id = "29f22f7d615e50079f";

	public static final String youzan_client_secret = "efb0091fe76678322000af822c705c0a";


	@RequestMapping(value = "/biz", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Object biz(@RequestBody MsgPushEntity entity){

		logger.info("youzan biz info : "+BaseDto.toString(entity));

		YouzanCommonResponse commonResponse = new YouzanCommonResponse();



		/**
		 *  判断是否为心跳检查消息
		 *  1.是则直接返回
		 */
		if (entity.isTest()) {
			commonResponse.buildSuccess();
			return commonResponse;
		}

		/**
		 * 解析消息推送的模式  这步判断可以省略
		 * 0-商家自由消息推送 1-服务商消息推送
		 * 以服务商 举例
		 * 判断是否为服务商类型的消息
		 * 否则直接返回
		 */
//		if (entity.getMode() != mode ){
//			return res;
//		}

		/**
		 * 判断消息是否合法
		 * 解析sign
		 * MD5 工具类开发者可以自行引入
		 */
//		String sign= MD5.digest(clientId+entity.getMsg()+clientSecret);
//		if (!sign.equals(entity.getSign())){
//			return res;
//		}

		/**
		 * 对于msg 先进行URI解码
		 */
		String msg="";
		try {
			msg= URLDecoder.decode(entity.getMsg(), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		/**
		 *  ..........
		 *  接下来是一些业务处理
		 *  判断当前消息的类型 比如交易
		 *
		 */

		if ("TRADE".equals(entity.getType())) {


			//TODO: 参考文档对应的交易对象 进行JSON解码  业务处理等

		}
		commonResponse.buildSuccess();
		/**
		 * 返回结果
		 */
		return commonResponse;
	}




	/**
	 * 消息接收类
	 */
	public class MsgPushEntity {


		private String msg;

		private int sendCount;

		private int mode; //  默认0 : appid  1 :client

		private String app_id;

		private String client_id;

		private Long version;

		private String type;

		private String id;

		private String sign;

		private Integer kdt_id;

		private boolean test = false;

		private String status;

		private String kdt_name;

		public boolean isTest() {
			return test;
		}

		public void setTest(boolean test) {
			this.test = test;
		}

		public String getSign() {
			return sign;
		}

		public void setSign(String sign) {
			this.sign = sign;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public Long getVersion() {
			return version;
		}

		public void setVersion(Long version) {
			this.version = version;
		}


		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public int getSendCount() {
			return sendCount;
		}

		public void setSendCount(int sendCount) {
			this.sendCount = sendCount;
		}

		public int getMode() {
			return mode;
		}

		public void setMode(int mode) {
			this.mode = mode;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getApp_id() {
			return app_id;
		}

		public void setApp_id(String app_id) {
			this.app_id = app_id;
		}

		public String getClient_id() {
			return client_id;
		}

		public void setClient_id(String client_id) {
			this.client_id = client_id;
		}

		public Integer getKdt_id() {
			return kdt_id;
		}

		public void setKdt_id(Integer kdt_id) {
			this.kdt_id = kdt_id;
		}

		public String getKdt_name() {
			return kdt_name;
		}

		public void setKdt_name(String kdt_name) {
			this.kdt_name = kdt_name;
		}
	}

}
