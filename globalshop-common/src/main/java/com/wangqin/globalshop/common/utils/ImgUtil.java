package com.wangqin.globalshop.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author biscuit
 * @data 2018/07/05
 */
public class ImgUtil {
    /**
     * 默认图片
     */
    public static String imgUrl = ConfigUtil.getImgUrlWhenNotFound();

    /**
     * 如果是url则返回 json
     * 如果是json则返回 url
     * @param url
     * @return
     */
    public static String changeType(String url) {
        try {
          /*如果为空  返回缺省图*/
            if (EasyUtil.isStringEmpty(url)) {
                return imgUrl;
            }
            if (url.toLowerCase().startsWith("http")) {
                return "{\"picList\":[{\"type\":\"image/jpeg\",\"uid\":\"i_0\",\"url\":\""+url+"\"}],\"mainPicNum\":\"1\"}";
            }
            JSONObject jsonObject = JSON.parseObject(url);
            if (jsonObject == null) {
                return imgUrl;
            }
            JSONArray picList = (JSONArray) jsonObject.get("picList");
            Integer mainPicNum = jsonObject.getInteger("mainPicNum");
            int i = mainPicNum == null ? 1 : mainPicNum;

            if (picList.size() < i) {
                return imgUrl;
            }
            JSONObject o = (JSONObject) picList.get(i - 1);
            String img = (String) o.get("url");
            return img;
        } catch (Exception e){
            return imgUrl;
        }
    }
    /**
     * @param url
     * @return 返回JSON格式的图片
     */
    public static String initImg2Json(String url) {
        if (url.toLowerCase().startsWith("http")) {
            return "{\"picList\":[{\"type\":\"image/jpeg\",\"uid\":\"i_0\",\"url\":\""+url+"\"}],\"mainPicNum\":\"1\"}";
        }
        return url;
    }


    /**
     * @param url
     * @return 返回Url格式的图片
     */
    public static String initImg2Url(String url) {
        try {
        /*如果为空  返回缺省图*/
            if (EasyUtil.isStringEmpty(url)) {
                return imgUrl;
            }
        /*如果以http开头  说明是图片url直接返回*/
            if (url.toLowerCase().startsWith("http")) {
                return url;
            }
            JSONObject jsonObject = JSON.parseObject(url);
            if (jsonObject == null) {
                return imgUrl;
            }
            JSONArray picList = (JSONArray) jsonObject.get("picList");
            Integer mainPicNum = jsonObject.getInteger("mainPicNum");
            int i = mainPicNum == null ? 1 : mainPicNum;

            if (picList.size() < i - 1) {
                return imgUrl;
            }
            JSONObject o = (JSONObject) picList.get(i - 1);
            String img = (String) o.get("url");
            return img;
        } catch (Exception e) {
            return imgUrl;
        }
    }
}


