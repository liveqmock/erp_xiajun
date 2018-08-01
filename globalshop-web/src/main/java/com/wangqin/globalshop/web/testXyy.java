package com.wangqin.globalshop.web;

import com.wangqin.globalshop.common.utils.HttpClientUtil;
import com.wangqin.globalshop.common.utils.Md5Util;

import java.util.*;

public class testXyy {
    private static String goodsListUrl = "http://www.shopxyy.com/papi.php/Index/Index" +
            "/t/{Timestamp}/r/{randomStr}/appkey/{appkey}/secret/{appsecret}/s/{signature}/p/{page}/n/{pageNumber}";
    private static String goodsStatusUrl = "http://www.shopxyy.com/papi.php/Index/getGoodsStatus" +
            "/t/{Timestamp}/r/{randomStr}/appkey/{appkey}/secret/{appsecret}/s/{signature}/gid/{gid}";
    private static String goodsStockUrl = "http://www.shopxyy.com/papi.php/Index/getGoodsStock" +
            "/t/{Timestamp}/r/{randomStr}/appkey/{appkey}/secret/{appsecret}/s/{signature}/gid/{gid}";

//    private static String xyyUrl = "http://www.shopxyy.com/papi.php/Index/Index";
    private static String appKey = "guanyi";
    private static String appSecret = "ji5na02h9sbymefz4ct7uoxplqk3vg8dr6w1";

    public static void main(String[] args) {
        String timestamp=""+System.currentTimeMillis();
        String randomStr="7788";
        String signature=generateSignature(timestamp,randomStr,appKey,appSecret);

        String url= goodsStockUrl.replace("{Timestamp}",timestamp)
                .replace("{randomStr}",randomStr)
                .replace("{appkey}",appKey)
                .replace("{appsecret}",appSecret)
                .replace("{signature}",signature)
                .replace("{page}","9")
                .replace("{pageNumber}","500")
                .replace("{gid}","2251");
        String resp=HttpClientUtil.get(url);
        String decode=org.apache.commons.lang.StringEscapeUtils.unescapeJava(resp);
        System.out.println("\n\n"+ decode);
//        try {
////            System.out.println("\n\n"+ URLDecoder.decode(resp,"gb18030"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
    }


    public static String generateSignature(String timestamp, String randomStr, String appKey, String appSecret) {

        ArrayList<String> params = new ArrayList<>(4);
        params.add(""+timestamp);
        params.add(""+randomStr);
        params.add(appKey);
        params.add(appSecret);

        //排序
        params.sort(
                new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.compareTo(o2);
                    }
                });
        //拼接
        String str = "";
        for (int i = 0; i < params.size(); i++) {
            str += params.get(i).toString();
        }
        //sha1, md5, md5
        String signature = Md5Util.getMD5(Md5Util.getMD5(Md5Util.getSha1(str)));
        //转大写
        return signature.toUpperCase();
    }

}
