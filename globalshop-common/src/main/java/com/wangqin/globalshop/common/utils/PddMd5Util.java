package com.wangqin.globalshop.common.utils;



import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import com.wangqin.globalshop.common.exception.ErpCommonException;



public class PddMd5Util {
	
    public static final String UTF_8 = "UTF-8";
	
	public static final String GBK = "GBK";
	
	//老版拼多多制作签名，只限于系统级别的参数
	@Deprecated
	public static String makeSignForMtype(String uCode, String mType, String secret, String timeStamp){	
		
		PddSignType signType = new PddSignType(uCode, mType, secret, timeStamp);
		String toSign = signType.makeToSignString();
		
		String sign = "";
		try {			
			 sign = Md5Util.sign(toSign).toUpperCase();
		} catch (Exception e) {						
			throw new ErpCommonException("Get Pddsign failed","获取签名失败");
		}		
        if(sign == null || sign.trim().equals("")){        	
        	throw new ErpCommonException("获取的签名为空");
        }				
		return sign;
	}
	//新版拼多多制作签名，包括系统级别参数和方法级别的参数
	public static String makeSign(Map<String, String> map, String secret) {
		if (map == null || map.size() == 0 || secret == null || secret.length() == 0) {
			throw new ErpCommonException("make Pddsign failed", "制作签名失败，参数缺失");
		}
		ArrayList<String> list = new ArrayList<>(map.keySet());
		Collections.sort(list);
		String sign = "";
		for (String tmp : list) {
			sign = sign + tmp + map.get(tmp);
		}
		sign = secret + sign + secret;
		try {
			sign = Md5Util.sign(sign).toUpperCase();
		} catch (Exception e) {
			throw new ErpCommonException("Get Pddsign failed", "获取签名失败");
		}
		if (sign == null || sign.trim().equals("")) {
			throw new ErpCommonException("获取的签名为空");
		}
		return sign;
	}
	
	
	
	
	public static String makeSign(String toSign)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return makeSign(toSign, UTF_8);
	}
	
	public static String makeSign(String toSign, String encode)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return digestEncrypte(toSign.getBytes(encode));
	}

	private static String digestEncrypte(byte[] plainText)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md = MessageDigest.getInstance("md5");
		md.update(plainText);
		byte[] b = md.digest();
		StringBuilder output = new StringBuilder(32);
		for (int i = 0; i < b.length; i++) {
			String temp = Integer.toHexString(b[i] & 0xff);
			if (temp.length() < 2) {
				output.append("0");
			}
			output.append(temp);
		}						
		return output.toString().toUpperCase();
	}

}
