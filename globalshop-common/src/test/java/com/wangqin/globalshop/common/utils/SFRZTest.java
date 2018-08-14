package com.wangqin.globalshop.common.utils;

import com.songshun.sdk.entity.SFRZReq;
import com.wangqin.globalshop.common.idcard.ConfigContant;
import com.wangqin.globalshop.common.idcard.IDCardHttpClientUtil;
import org.junit.Test;

import java.util.HashMap;

/**
 * 全国身份证实名认证
 * @date:2017/05/17
 */
public class SFRZTest {


    /**
     * 身份证二要素认证
     * @throws Exception
     */
    @Test
    public void testTwoItem() throws Exception{

    	String idName = "张宇飞";
    	String idNum = "360722198909151519";

        SFRZReq req=new SFRZReq();
        req.setServiceCode("101");
        req.setName(idName);
        req.setIdNumber(idNum);
        HashMap<String, Object> map = ConfigContant.initParams(req);
        IDCardHttpClientUtil.invoke(map);
    }

    /**
     * 静默活体认证
     * @throws Exception
     */
    @Test
    public   void testFourItem() throws Exception{
        SFRZReq req=new SFRZReq();
        req.setServiceCode("102");
        req.setImageData( ConfigContant.IMAGEDATA);
        HashMap<String, Object> map = ConfigContant.initParams(req);
        IDCardHttpClientUtil.invoke(map);
    }

    /**
     * 身份证二要素认证+人像比对
     * 图片数据过大，建议采用POST提交
     * @throws Exception
     */
    @Test
    public   void testTwoItemAndImage() throws Exception{
        SFRZReq req=new SFRZReq();
        req.setServiceCode("103");
        req.setName( "");
        req.setIdNumber("");
        req.setImageData( ConfigContant.IMAGEDATA);
        HashMap<String, Object> map = ConfigContant.initParams(req);
        IDCardHttpClientUtil.invoke(map);
    }

    /**
     * 身份证四要素认证+人像比对
     * 图片数据过大，建议采用POST提交
     * @throws Exception
     */
    @Test
    public   void testFourItemAndImage() throws Exception{
        SFRZReq req=new SFRZReq();
        req.setServiceCode("104");
        req.setName( "");
        req.setIdNumber("");
        req.setValidFrom( "");
        req.setValidEnd( "");
        req.setImageData( ConfigContant.IMAGEDATA);
        HashMap<String, Object> map = ConfigContant.initParams(req);
        IDCardHttpClientUtil.invoke(map);
    }

    /**
     * 身份证二要素认证，返回照片
     * @throws Exception
     */
    @Test
    public   void testTwoItemImage() throws Exception{
        SFRZReq req=new SFRZReq();
        req.setServiceCode("105");
        req.setName( "");
        req.setIdNumber("");
        HashMap<String, Object> map = ConfigContant.initParams(req);
        IDCardHttpClientUtil.invoke(map);
    }


    /**
     * 身份证二要素认证+SDK活体采集人像比对
     * 图片数据过大，建议采用POST提交
     * @throws Exception
     */
    @Test
    public   void testTwoItemAndSDKImage() throws Exception{
        SFRZReq req=new SFRZReq();
        req.setServiceCode("108");
        req.setName( "");
        req.setIdNumber("");
        req.setImageData( ConfigContant.IMAGEDATA);
        HashMap<String, Object> map = ConfigContant.initParams(req);
        IDCardHttpClientUtil.invoke(map);
    }
}
