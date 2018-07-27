package com.wangqin.globalshop.item.app.util;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AppletConfigDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;
import com.wangqin.globalshop.biz1.app.vo.ItemQueryVO;
import com.wangqin.globalshop.common.enums.AppletType;
import com.wangqin.globalshop.common.enums.ItemIsSale;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.DateUtil;
import com.wangqin.globalshop.common.utils.DimensionCodeUtil;
import com.wangqin.globalshop.common.utils.IsEmptyUtil;
import com.wangqin.globalshop.common.utils.PriceUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ItemUtil {

	 /**
     * 工具类：处理商品的名字
     * 
     * 商品名称公式：品牌英文名+品牌中文名+男女款+商品名
     */
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
    public static void setIsSale(ItemDO newItem) {
        if (DateUtil.belongCalendar(new Date(), newItem.getStartDate(), DateUtil.getDateByCalculate(newItem.getEndDate(), Calendar.DATE, 1))) {
        	newItem.setIsSale(ItemIsSale.SALABLE.getCode().byteValue());
        } else {
        	newItem.setIsSale(ItemIsSale.UNSALABLE.getCode().byteValue());
        }
    }
    
    /**
     * 工具类
     * 处理商品时间相关的字段
     */
    public static void setItemDate(ItemQueryVO item,ItemDO newItem) throws ParseException{    	
    	newItem.setStartDate(DateUtil.transferStringToDate(item.getStartDate()));
    	newItem.setEndDate(DateUtil.transferStringToDate(item.getEndDate()));
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
    

}
