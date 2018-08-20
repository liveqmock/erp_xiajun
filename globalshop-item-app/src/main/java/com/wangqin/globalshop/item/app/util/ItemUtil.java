package com.wangqin.globalshop.item.app.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.apache.xmlbeans.impl.xb.xsdschema.impl.PublicImpl;
import org.springframework.context.annotation.EnableLoadTimeWeaving;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuScaleDO;
import com.wangqin.globalshop.biz1.app.enums.ItemShelfMethod;
import com.wangqin.globalshop.biz1.app.enums.ItemStatus;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ItemQueryVO;
import com.wangqin.globalshop.common.enums.ItemIsSale;
import com.wangqin.globalshop.common.utils.CodeGenUtil;
import com.wangqin.globalshop.common.utils.DateUtil;
import com.wangqin.globalshop.common.utils.IsEmptyUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ItemUtil {

	 /**
     * 工具类：处理商品的名字
     * 
     * 商品名称公式：品牌英文名+品牌中文名+男女款+商品名
     */
	@Deprecated
    public static void setItemNewName(ItemQueryVO item) {
    	StringBuffer nameNew = new StringBuffer();
        String[] brandArr = item.getBrand().split("->");
        for(String s:brandArr) { //品牌处理
        	nameNew.append(s+" ");
        }
        if(IsEmptyUtil.isStringNotEmpty(item.getSexStyle())) { //男女款处理
        	nameNew.append(item.getSexStyle() + " ");
        }
        nameNew.append(item.getName());   
        item.setName(nameNew.toString());//重新设置商品名称
    }
    
    /**
     * 工具类
     * 设置商品是否可售
     */
	@Deprecated
    public static void setIsSale(ItemDO newItem) {
        if (DateUtil.belongCalendar(new Date(), newItem.getStartDate(), newItem.getEndDate())) {
        	newItem.setIsSale(ItemIsSale.SALABLE.getCode().byteValue());
        } else {
        	newItem.setIsSale(ItemIsSale.UNSALABLE.getCode().byteValue());
        }
    }
    
    /**
     * 工具类
     * 处理商品时间相关的字段
     */
	@Deprecated
    public static void setItemDate(ItemQueryVO item,ItemDO newItem) throws ParseException{    	
    	newItem.setStartDate(item.getStartDate());
    	newItem.setEndDate(item.getEndDate());
    	if(IsEmptyUtil.isStringNotEmpty(item.getBookingDate())) {
    		newItem.setBookingDate(DateUtil.transferStringToDate(item.getBookingDate()));
    	}
    }   
    
    
    /**
     * 工具类
     * 判断用户是否提供了主图
     * @param itemCode
     * @return
     */
    public static Boolean picCheck(String pic) {   
        JSONObject jsonObject = JSONObject.fromObject(pic);
        JSONArray jsonArray = jsonObject.getJSONArray("picList");
        if(0 == jsonArray.size()) {
         	return false;
        }
        return true;
    }
    
    //处理第三方销售平台，TEMP
    @Deprecated
    public static void setChannel(ItemQueryVO item,ItemDO newItem) {
    	String channelList = item.getSaleOnChannels();
        if (IsEmptyUtil.isStringEmpty(channelList)) {
        	newItem.setThirdSale(0);
        	newItem.setSaleOnYouzan(0);
        } else {
            String[] channels = channelList.split(",");
            for(String channel:channels) {
            	if("chan_youzan".equals(channel) || "有赞".equals(channel)) {
            		newItem.setSaleOnYouzan(1);
            	}
            	if("chan_haihu".equals(channel) || "海狐海淘".equals(channel)) {
            		newItem.setThirdSale(1);
            	}
            }
        }
    }	
    
    public static Double divideOneHundred(String s) {
    	//构造以字符串内容为值的BigDecimal类型的变量bd 
    	BigDecimal bd = new BigDecimal(s); 
    	BigDecimal divisor = new BigDecimal(100);
    	BigDecimal result = bd.divide(divisor);
    	//设置小数位数，第一个变量是小数位数，第二个变量是取舍方法(四舍五入) 
    	result = result.setScale(2, BigDecimal.ROUND_HALF_UP); 
    	//转化为字符串输出 
    	return result.doubleValue();
    }
    
    public static String multiplyOneHundred(Double s) {
    	//构造以字符串内容为值的BigDecimal类型的变量bd 
    	BigDecimal bd = new BigDecimal(s); 
    	BigDecimal divisor = new BigDecimal(100);
    	BigDecimal result = bd.multiply(divisor);
    	//设置小数位数，第一个变量是小数位数，第二个变量是取舍方法(四舍五入) 
    	result = result.setScale(2, BigDecimal.ROUND_HALF_UP); 
    	//转化为字符串输出 
    	return result.toString();
    }
    
    /**
     * 上架方式，销售时间，是否可售处理
     */
    public static void handleShelf(ItemQueryVO item,ItemDO newItem) throws ParseException {
    	//对销售时间进行处理
        Integer shelfMethod = item.getShelfMethod();
        newItem.setShelfMethod(shelfMethod);
        if (ItemShelfMethod.SALE_IMMEDIATE.getValue().equals(shelfMethod)) {//立刻上架
        	newItem.setStartDate(new Date());
        	newItem.setIsSale(ItemIsSale.SALABLE.getCode().byteValue());
        	newItem.setStatus(ItemStatus.LISTING.getCode());
        } else if (ItemShelfMethod.TEMP_UNSALE.getValue().equals(shelfMethod)) {//暂不售卖
        	newItem.setIsSale(ItemIsSale.UNSALABLE.getCode().byteValue());
        	newItem.setStatus(ItemStatus.INIT.getCode());
        } else {//自定义上架时间
        	Date startDate = DateUtil.transferStringToDate(item.getStartTime());
        	newItem.setStartDate(startDate);     
        	newItem.setStatus(ItemStatus.INIT.getCode());
        	newItem.setIsSale(ItemIsSale.UNSALABLE.getCode().byteValue());
        }
    }
    
    
    /**
     * 封装一个ItemSkuScaleDO对象
     * @author xiajun
     *
     */
    public static ItemSkuScaleDO genScaleDO(String comNo,String userNo,String scaleName,String scaleVal,String skuCode,String itemCode) {
    	ItemSkuScaleDO scaleDO = new ItemSkuScaleDO();    	
    	scaleDO.setSkuCode(skuCode);
    	scaleDO.setItemCode(itemCode);
    	scaleDO.setScaleCode(CodeGenUtil.getScaleCode());
    	scaleDO.setScaleName(scaleName);
    	scaleDO.setScaleValue(scaleVal);
    	scaleDO.setCompanyNo(comNo);
    	scaleDO.setCreator(userNo);
    	scaleDO.setModifier(userNo);
    	return scaleDO;
    }

}
