package com.wangqin.globalshop.web.controller.item;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemFindDO;
import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.item.app.service.IFindItemService;
import com.wangqin.globalshop.item.app.service.IItemService;
import com.wangqin.globalshop.web.dto.BaseDto;
import com.wangqin.globalshop.web.dto.api.ItemDetailEntity;
import com.wangqin.globalshop.web.dto.api.ItemEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/api/items")
public class ItemApiController {
	
	
	@Autowired
	private IItemService itemService;
    /**
     * 0对应今日上新，1对应全部商品
     * 每页24个
     * @param type
     * @param companyNo
     * @param pageSize
     * @param pageNo
     * @return
     */
    @RequestMapping("/startup")
    @ResponseBody
    public String startUp(@RequestParam("type") String type,
                          @RequestParam("companyNo") String companyNo,
                          @RequestParam("pageSize") String pageSize,
                          @RequestParam("pageNo") String pageNo){


        //TODO
    	JsonResult<List<ItemEntity>> jsonResult = new JsonResult<>();
    	List<ItemEntity> items = new ArrayList<>();
    	List<ItemDO>itemList = itemService.queryItemByStatus(companyNo, type);
    	ItemEntity itemEntity = new ItemEntity();
    	for(int i = 0; i < itemList.size(); i ++) {
    		itemEntity.setItemCode(itemList.get(i).getItemCode());
    		itemEntity.setPrice(itemList.get(i).getPriceRange());//价格
    		itemEntity.setOriginPrice("meiyuan");//外币
    		itemEntity.setTitle(itemList.get(i).getItemName());//标题
    		itemEntity.setImgUrl(itemList.get(i).getMainPic());//头像
    	}
    	
    	items.add(itemEntity); 

        jsonResult.buildIsSuccess(true).buildData(items);
        return BaseDto.toString(jsonResult);
    }

    @RequestMapping("/search")
    @ResponseBody
        public String search(@RequestParam("keyword") String keyword,
                @RequestParam("companyNo") String companyNo,
                @RequestParam("pageSize") String pageSize,
                @RequestParam("pageNo") String pageNo){

        //TODO
        JsonResult<List<ItemEntity>> jsonResult = new JsonResult<>();
        List<ItemEntity> items = new ArrayList<>();
        List<ItemDO> itemList = itemService.queryItemByKeyWord(keyword, companyNo, pageSize, pageNo);
        ItemEntity itemEntity = new ItemEntity();
        for(int i = 0; i < itemList.size(); i ++) {
        	itemEntity.setItemCode(itemList.get(i).getItemCode());
        	itemEntity.setPrice(itemList.get(i).getMainPic());
        	itemEntity.setOriginPrice("美元");
        	itemEntity.setPrice("价格");
        	itemEntity.setTitle("标题");
        }

    
        items.add(itemEntity);


        jsonResult.buildIsSuccess(true).buildData(items);
        return BaseDto.toString(jsonResult);
    }

    @RequestMapping("/detail")
    @ResponseBody
    public String detail(@RequestParam("itemCode") String itemCode,
                         @RequestParam("companyNo") String companyNo){

        //TODO
        JsonResult<ItemDetailEntity> jsonResult = new JsonResult<>();
        ItemDetailEntity itemDetailEntity = new ItemDetailEntity();
        ItemDO itemDO = itemService.itemDetailByItemCode(itemCode, companyNo);
        itemDetailEntity.setItemCode(itemDO.getItemCode());
        itemDetailEntity.setItemDesc(itemDO.getDetail());
        itemDetailEntity.setPrice(itemDO.getMainPic());
        itemDetailEntity.setOriginPrice("美元");
        itemDetailEntity.setImgList(Arrays.asList("http://img.haihu.com/wq_logo.jpg"));
      
        
//        entity.setItemCode("1111");
//        entity.setItemDesc("xxxxxx");
//        entity.setPrice("120");
//        entity.setOriginPrice("$20");
//        entity.setImgList(Arrays.asList("http://img.haihu.com/wq_logo.jpg"));
//

        jsonResult.buildIsSuccess(true).buildData(itemDetailEntity);
        return BaseDto.toString(jsonResult);
    }

}
