package com.wangqin.globalshop.app;



import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;

import com.wangqin.globalshop.common.utils.RandomUtils;
import com.wangqin.globalshop.item.app.service.IItemService;


/**
 * 添加商品的单元测试
 * @author admin
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ItemAddTest {

	 @Autowired
	 private IItemService itemService;
	 
	 /**
	  * 测试 ItemService.insertItemSelective
	  * 含有所有的项目
	  */
	 @Test
	 public void testInsertItemSelectiveAll() {
		 ItemDO newItem = new ItemDO();
		 newItem.setCategoryCode("0102005");
		 newItem.setCategoryName("面部护理");
		 newItem.setItemName("ShanCun 善存 女款 测试商品的名字");
		 newItem.setDetail("详情");
	     newItem.setPriceRange("12.00-13.00");
         newItem.setBrandName("Centrum->善存");
         newItem.setBrandNo("b1527763120");
    	 newItem.setEnName("ShanCun");
	     newItem.setCurrency((byte) 1);
	     newItem.setIdCard((byte) 1);
	     newItem.setLogisticType((byte) 1);
	     newItem.setCountry("218");
	     newItem.setItemCode("I0102005T"+RandomUtils.getTimeRandom());
	     newItem.setWxisSale((byte) 1);
	     newItem.setRemark("我的小备注");       
	     newItem.setMainPic("{\"picList\":[{\"type\":\"image/jpeg\",\"uid\":\"i_0\",\"url\":\"http://img.haihu.com/41_1530185630887.jpg?x-oss-process=image/resize,w_200/auto-orient,0\"}],\"mainPicNum\":\"1\"}");
	     newItem.setIsSale((byte) 1);
	     newItem.setItemShort("测试商品的名字");    
	     newItem.setCompanyNo("11");
	     newItem.setModifier("test");
	     newItem.setCreator("test");
	     newItem.setStartDate(new Date());
	     newItem.setEndDate(new Date());
	     newItem.setBookingDate(new Date());
	     newItem.setThirdSale(1);
	     newItem.setSaleOnYouzan(1);
	     itemService.insertItemSelective(newItem);
	     System.out.println("全部添加商品测试通过");
	 }
	 
	 /**
	  * 测试 ItemService.insertItemSelective
	  * 含有必填的项目
	  */
	 @Test
	 public void testInsertItemSelectivePart() {
		 ItemDO newItem = new ItemDO();
		 newItem.setCategoryCode("0102005");
		 newItem.setCategoryName("面部护理");
		 newItem.setItemName("ShanCun 善存 女款 测试商品的名字");
		 
	     newItem.setPriceRange("12.00-13.00");
         newItem.setBrandName("Centrum->善存");
         newItem.setBrandNo("b1527763120");
    	 
	     newItem.setCurrency((byte) 1);
	     newItem.setIdCard((byte) 1);
	     newItem.setLogisticType((byte) 1);
	     newItem.setCountry("218");
	     newItem.setItemCode("I0102005T"+RandomUtils.getTimeRandom());
	     newItem.setWxisSale((byte) 1);
	           
	     newItem.setMainPic("{\"picList\":[{\"type\":\"image/jpeg\",\"uid\":\"i_0\",\"url\":\"http://img.haihu.com/41_1530185630887.jpg?x-oss-process=image/resize,w_200/auto-orient,0\"}],\"mainPicNum\":\"1\"}");
	     newItem.setIsSale((byte) 1);
	         
	     newItem.setCompanyNo("11");
	     newItem.setModifier("test");
	     newItem.setCreator("test");
	     newItem.setStartDate(new Date());
	     newItem.setEndDate(new Date());
	     
	     
	     itemService.insertItemSelective(newItem);
	     System.out.println("部分添加商品测试通过");
	 }
	
}
