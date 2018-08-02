package com.wangqin.globalshop.item.app.service;

import com.wangqin.globalshop.common.utils.HttpClientUtil;
import com.wangqin.globalshop.common.utils.JSONUtil;
import com.wangqin.globalshop.common.utils.Md5Util;
import net.sf.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.*;
/*
* 暂时是xyy的商品和库存的接口同步
*
 */

public class XyyItemSyncApiService {

    //    private static String xyyUrl = "http://www.shopxyy.com/papi.php/Index/Index";
    private static String goodsListUrl = "http://www.shopxyy.com/papi.php/Index/Index" +
            "/t/{Timestamp}/r/{randomStr}/appkey/{appkey}/secret/{appsecret}/s/{signature}/p/{page}/n/{pageNumber}";
    private static String goodsStatusUrl = "http://www.shopxyy.com/papi.php/Index/getGoodsStatus" +
            "/t/{Timestamp}/r/{randomStr}/appkey/{appkey}/secret/{appsecret}/s/{signature}/gid/{gid}";
    private static String goodsStockUrl = "http://www.shopxyy.com/papi.php/Index/getGoodsStock" +
            "/t/{Timestamp}/r/{randomStr}/appkey/{appkey}/secret/{appsecret}/s/{signature}/gid/{gid}";

    private static String appKey = "guanyi";
    private static String appSecret = "ji5na02h9sbymefz4ct7uoxplqk3vg8dr6w1";


    private static String pageNum =  "1";
    private static String pageSize =  "500";


    public static void main(String[] args) {
        System.out.println(syncXyy(goodsListUrl, pageNum, pageSize, ""));
        System.out.println(syncXyy(goodsStatusUrl, pageNum, pageSize, "2251"));
        System.out.println(syncXyy(goodsStockUrl, pageNum, pageSize, "2251"));
        ;
    }


    /***
     * 每三十分钟刷新xyy商户的商品列表
     */
//    @Scheduled(cron = "0 0/30 * * * ?")
    private static void syncXyyGoods() {
        int page=1;
        int totalPage=0;
        do {
            String itemlist=syncXyy(goodsListUrl, pageNum, pageSize, "");

            JSONObject json = JSONObject.fromObject(itemlist);

        }while(page>totalPage);
    }

    /***
     * 每三十分钟刷新xyy商户的商品库存
     */
//    @Scheduled(cron = "0 0/30 * * * ?")
    private static void syncXyyStock() {
        int page=1;
        int totalPage=0;
        do {
            String itemlist=syncXyy(goodsStockUrl, pageNum, pageSize, "");

            JSONObject json = JSONObject.fromObject(itemlist);

        }while(page>totalPage);
    }


    private static String syncXyy(String apiUrl,String pageNum,String pageSize,String gid) {
        String timestamp=""+System.currentTimeMillis();
        String randomStr="7788";
        String signature=generateSignature(timestamp,randomStr,appKey,appSecret);

        String url= apiUrl.replace("{Timestamp}",timestamp)
                .replace("{randomStr}",randomStr)
                .replace("{appkey}",appKey)
                .replace("{appsecret}",appSecret)
                .replace("{signature}",signature)
                .replace("{page}",pageNum)
                .replace("{pageNumber}",pageSize)
                .replace("{gid}",gid);
        String resp= HttpClientUtil.get(url);
        String decoded=org.apache.commons.lang.StringEscapeUtils.unescapeJava(resp);
//        System.out.println("\n\n"+ decoded);
        return decoded;
    }


    public static String generateSignature(String timestamp, String randomStr, String appKey, String appSecret) {

        //排序
        ArrayList<String> params = new ArrayList<>(4);
        params.add(""+timestamp);
        params.add(""+randomStr);
        params.add(appKey);
        params.add(appSecret);
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
