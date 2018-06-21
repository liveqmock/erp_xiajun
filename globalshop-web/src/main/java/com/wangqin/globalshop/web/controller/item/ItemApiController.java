package com.wangqin.globalshop.web.controller.item;


import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;
import com.wangqin.globalshop.common.base.BaseDto;
import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.item.app.service.IItemService;
import com.wangqin.globalshop.item.app.service.IItemSkuService;
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
	@Autowired
	private IItemSkuService itemSkuService;
	
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

    	JsonResult<List<ItemEntity>> jsonResult = new JsonResult<>();
    	List<ItemEntity> items = new ArrayList<>();
    	int start = (Integer.parseInt(pageNo)-1)*Integer.parseInt(pageSize);
    	List<ItemDO>itemList = itemService.queryItemByStatus(companyNo, type, start, pageSize);
    	
    	for(int i = 0; i < itemList.size(); i++) {
    		ItemEntity itemEntity = new ItemEntity();
    		ItemDO item = itemList.get(i);
    		Double price = itemSkuService.querySalePriceByItemCode(item.getItemCode());
    		itemEntity.setItemCode(item.getItemCode());
    		itemEntity.setPrice(price.toString());//价格
    		itemEntity.setOriginPrice(item.getOriginSalePrice());//外币
    		itemEntity.setTitle(item.getItemName());//标题
    		itemEntity.setImgUrl(item.getMainPic());//商品图片
    		items.add(itemEntity); 
    	}

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
        int start = (Integer.parseInt(pageNo)-1)*Integer.parseInt(pageSize);
        List<ItemDO> itemList = itemService.queryItemByKeyWord(keyword, companyNo, start, pageSize);
      
        for(int i = 0; i < itemList.size(); i++) {
        	ItemEntity itemEntity = new ItemEntity();
        	ItemDO item = itemList.get(i);
        	Double price = itemSkuService.querySalePriceByItemCode(item.getItemCode());
        	itemEntity.setItemCode(item.getItemCode());
        	itemEntity.setImgUrl(item.getMainPic());
        	itemEntity.setOriginPrice(item.getOriginSalePrice());
        	itemEntity.setPrice(price.toString());
        	itemEntity.setTitle(item.getItemName());
        	items.add(itemEntity);

        }

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
        Double price = itemSkuService.querySalePriceByItemCode(itemDO.getItemCode());
        itemDetailEntity.setItemCode(itemDO.getItemCode());
        itemDetailEntity.setItemDesc(itemDO.getDetail());
        itemDetailEntity.setPrice(price.toString());
        itemDetailEntity.setOriginPrice(itemDO.getOriginSalePrice());
        itemDetailEntity.setImgList(Arrays.asList(itemDO.getMainPic()));
      
        jsonResult.buildIsSuccess(true).buildData(itemDetailEntity);
        return BaseDto.toString(jsonResult);
    }

}
