package com.wangqin.globalshop.logistic.app.util;

import org.apache.tomcat.util.codec.binary.Base64;

/**
 * 加解密/签名工具类
 *
 * @author angus
 * @date 2018/8/24
 */
public class EncryptionUtil {
    /**
     * AES_KEY 用于报文加密
     */
    private static final String AES_KEY = "WAQqTRVwIEkpiqQBsCJR9Q==";

    /**
     * PRIVATE_KEY 用于报文签名
     */
    private static final String PRIVATE_KEY = "MIIBVgIBADANBgkqhkiG9w0BAQEFAASCAUAwggE8AgEAAkEA9WM3iCCXqI7Yb0hVASUBo9ufD" +
            "WPVpIy2D1UrAR5At2d+S5jFHuxk47xx719rJ7+Its4HzRy8m2RE6vbyehdnaQIDAQABAkEA6+ktFCQxaY2bKrFhd646O2wDJ35ZgM7+oRMC" +
            "Oxr4oNhuuD9ubR3vre0hHTsJyaoBzlL6OBdy8k1UkNKwCoY/2QIhAPszfcjqf/gSTxMu1TkUPVjidrTy2nA6ctpok7gXYOInAiEA+hNLSN1" +
            "++VZJmCVkwxwlxQRnipX99flEiRiDpexFs+8CIQD3vHNrx2EHTT8xAvoD/eL2mvlJQUyObAZDQemVH3FL9wIgOlvST9jQzuMiHY1sbFPfRJ" +
            "D4kNDcCVD4e33rCweOZKUCIQDq/ZCmpKoQuHb8Kzjinj14pgfxAZsORA080hNPFjMLJA==";

    /**
     * 获取报文密文
     *
     * @param inputContent
     * @return
     * @throws Exception
     */
    public static String getEncContent(byte[] inputContent) throws Exception {
        byte[] aesKeyCode = Base64.decodeBase64(AES_KEY.getBytes("utf-8"));
        // 报文加密
        String encData = new String(Base64.encodeBase64(AESUtil.encrypt(inputContent, aesKeyCode)), "utf-8");
        return encData;
    }

    /**
     * 获取验签字串
     *
     * @param inputContent
     * @return
     * @throws Exception
     */
    public static String getSign(byte[] inputContent) throws Exception {
        byte[] privateKeyCode = Base64.decodeBase64(PRIVATE_KEY.getBytes("utf-8"));
        // 生成签名
        String sign = new String(Base64.encodeBase64(RSAUtil.sign(inputContent, privateKeyCode)), "utf-8");
        return sign;
    }
}
