package com.wangqin.globalshop.web.test.item;

import static org.junit.Assert.assertEquals;

import com.wangqin.globalshop.common.utils.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.wangqin.globalshop.biz1.app.bean.dataVo.ItemQueryVO;
import com.wangqin.globalshop.item.app.service.IItemService;


/**
 * @author patrick
 * @date 2016-9-22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
@WebAppConfiguration
public class ItemTest {

    @Autowired
    private IItemService itemService;

    /**
     * 添加商品
     * 含有前端能填入的所有项
     */
    @Test
    public void testAddItemWhole() {
    	System.out.println("===item add test start whole===");
    	ItemQueryVO item = new ItemQueryVO();
    	item.setCategoryCode("1001001");
    	item.setSexStyle("男款");
    	item.setBrand("Givenchy->纪梵希");
    	item.setEnName("JiFanXi");
    	item.setName("华为P20 Pro");
    	item.setCurrency(1);
    	item.setIdCard(1);
    	item.setCountry("362");
    	item.setStartDate(DateUtil.parseDate("2018-07-04 10:04:54"));
    	item.setEndDate(DateUtil.parseDate("2018-08-31 09:39:34"));
    	item.setBookingDate("2018-09-29 09:39:41");
    	item.setWxisSale(1);
    	item.setRemark("华为P20 Pro备注");
    	item.setMainPic("{\"picList\":[{\"type\":\"image/jpeg\",\"uid\":\"i_0\",\"url\":\"http://img.haihu.com/22_1530669778693.jpg?x-oss-process=image/resize,w_200/auto-orient,0\"}],\"mainPicNum\":\"1\"}");
    	item.setSkuList("[{\"color\":\"宝蓝色\",\"scale\":\"6inch\",\"salePrice\":4288,\"virtualInv\":134,\"weight\":13,\"upc\":\"978723265\",\"skuPic\":\"{\\\"picList\\\":[{\\\"type\\\":\\\"image/jpeg\\\",\\\"uid\\\":\\\"i_0\\\",\\\"url\\\":\\\"http://img.haihu.com/51_1530669864795.jpg?x-oss-process=image/resize,w_200/auto-orient,0\\\"}]}\",\"packageLevelId\":\"[33,20]\"},{\"color\":\"香槟金\",\"scale\":\"6.5inch\",\"salePrice\":4488,\"virtualInv\":12,\"weight\":14,\"upc\":\"978723465\",\"skuPic\":\"{\\\"picList\\\":[{\\\"type\\\":\\\"image/jpeg\\\",\\\"uid\\\":\\\"i_0\\\",\\\"url\\\":\\\"http://img.haihu.com/47_1530669869205.jpg?x-oss-process=image/resize,w_200/auto-orient,0\\\"}]}\",\"packageLevelId\":\"[33,20]\"}]");
    	item.setDetail("%3Cp%3E%E5%8D%8E%E4%B8%BAP20%E8%AF%A6%E6%83%85%3C%2Fp%3E");
    	itemService.addItem(item);
    	System.out.println("success!");
    	System.out.println("===item add test start whole===");
    }
    
    /**
     * 添加商品
     * 只含有必填项
     */
    @Test
    public void testAddItemPart() {
    	System.out.println("===item add test start part===");
    	ItemQueryVO item = new ItemQueryVO();
    	item.setCategoryCode("1001001");
    	item.setBrand("Givenchy->纪梵希");
    	item.setName("华为P20 Pro");
    	item.setCurrency(1);
    	item.setIdCard(1);
    	item.setCountry("362");
		item.setStartDate(DateUtil.parseDate("2018-07-04 10:04:54"));
		item.setEndDate(DateUtil.parseDate("2018-08-31 09:39:34"));
    	item.setWxisSale(1);
    	item.setMainPic("{\"picList\":[{\"type\":\"image/jpeg\",\"uid\":\"i_0\",\"url\":\"http://img.haihu.com/22_1530669778693.jpg?x-oss-process=image/resize,w_200/auto-orient,0\"}],\"mainPicNum\":\"1\"}");
    	item.setSkuList("[{\"color\":\"宝蓝色\",\"scale\":\"6inch\",\"salePrice\":4288,\"virtualInv\":134,\"weight\":13,\"upc\":\"978723265\",\"skuPic\":\"{\\\"picList\\\":[{\\\"type\\\":\\\"image/jpeg\\\",\\\"uid\\\":\\\"i_0\\\",\\\"url\\\":\\\"http://img.haihu.com/51_1530669864795.jpg?x-oss-process=image/resize,w_200/auto-orient,0\\\"}]}\",\"packageLevelId\":\"[33,20]\"},{\"color\":\"香槟金\",\"scale\":\"6.5inch\",\"salePrice\":4488,\"virtualInv\":12,\"weight\":14,\"upc\":\"978723465\",\"skuPic\":\"{\\\"picList\\\":[{\\\"type\\\":\\\"image/jpeg\\\",\\\"uid\\\":\\\"i_0\\\",\\\"url\\\":\\\"http://img.haihu.com/47_1530669869205.jpg?x-oss-process=image/resize,w_200/auto-orient,0\\\"}]}\",\"packageLevelId\":\"[33,20]\"}]");
    	itemService.addItem(item);
    	System.out.println("success!");
    	System.out.println("===item add test start part===");
    }


}
