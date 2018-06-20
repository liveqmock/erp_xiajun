package com.wangqin.globalshop.usercenter.service.impl;

import com.wangqin.globalshop.common.utils.QrCodeUtil;
import com.wangqin.globalshop.usercenter.service.QrCodeService;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Service;

/**
 * Create by 777 on 2018/6/20
 */
@Service
public class QrCodeServiceImpl implements QrCodeService {


	protected org.apache.logging.log4j.Logger logger = LogManager.getLogger(getClass());

	public static final String server_url = "https://www.buyer007.com";

	public static final String qrcode_common_path = "";

	public String getQrCodeUrl(String companyNo) {

		String qrCodeUrl = "";

		String text = server_url+"?companyNo="+companyNo;

		String filePath = qrcode_common_path+"/"+companyNo+"_simpleqrcode.png";
		try {
			QrCodeUtil.generateImage(text, 60, 60, filePath);
		} catch (Exception e) {
			logger.error("",e);
			return qrCodeUrl;
		}
		qrCodeUrl = server_url+"/"+filePath;

		return qrCodeUrl;
	}

}
