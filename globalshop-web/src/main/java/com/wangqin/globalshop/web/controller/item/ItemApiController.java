package com.wangqin.globalshop.web.controller.item;


import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;
import com.wangqin.globalshop.common.base.BaseDto;
import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.common.utils.StringUtil;
import com.wangqin.globalshop.common.utils.StringUtils;
import com.wangqin.globalshop.item.app.service.IItemService;
import com.wangqin.globalshop.item.app.service.IItemSkuService;
import com.wangqin.globalshop.web.dto.api.ItemDetailEntity;
import com.wangqin.globalshop.web.dto.api.ItemEntity;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
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
    	if (type.equals("1")){
    	    type = null;  //TODO
        }
    	List<ItemDO> itemList = itemService.queryItemByStatus(companyNo, type, start, Integer.valueOf(pageSize));
    	
    	for(int i = 0; i < itemList.size(); i++) {
    		ItemEntity itemEntity = new ItemEntity();
    		ItemDO item = itemList.get(i);
    		Double price = itemSkuService.querySalePriceByItemCode(item.getItemCode());
    		itemEntity.setItemCode(item.getItemCode());
    		itemEntity.setPrice(price.toString());//价格

            String originSalePrice = item.getOriginSalePrice();
            if(StringUtils.isBlank(originSalePrice)){
                itemEntity.setOriginPrice(" ");
            }else{
                itemEntity.setOriginPrice(String.format("(%s)", originSalePrice));//外币
            }
    		itemEntity.setTitle(item.getItemName());//标题


            JSONObject jsonObject = JSONObject.fromObject(item.getMainPic());
            JSONArray array = jsonObject.getJSONArray("picList");
            JSONObject imgObject = array.getJSONObject(0);

            String picUrl = imgObject.getString("url");
            itemEntity.setImgUrl(formImg(picUrl));//商品图片
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

        JsonResult<List<ItemEntity>> jsonResult = new JsonResult<>();
        List<ItemEntity> items = new ArrayList<>();
        int start = (Integer.parseInt(pageNo)-1)*Integer.parseInt(pageSize);

        //TODO reafctor
        String[] keyWordArr = keyword.split(" ");
        List<String> kwList = new ArrayList<>();
        for (String kw : keyWordArr){
            if (StringUtil.isNotBlank(kw)){
                kwList.add(kw.trim());
            }
        }
        List<ItemDO> itemList = itemService.queryItemByKeyWord(kwList, companyNo, start, Integer.valueOf(pageSize));
      
        for(int i = 0; i < itemList.size(); i++) {
        	ItemEntity itemEntity = new ItemEntity();
        	ItemDO item = itemList.get(i);
        	Double price = itemSkuService.querySalePriceByItemCode(item.getItemCode());
        	itemEntity.setItemCode(item.getItemCode());

        	//TODO
            JSONObject jsonObject = JSONObject.fromObject(item.getMainPic());
            JSONArray array = jsonObject.getJSONArray("picList");
            JSONObject imgObject = array.getJSONObject(0);
            String picUrl = imgObject.getString("url");
            itemEntity.setImgUrl(formImg(picUrl));//商品图片

            String originSalePrice = item.getOriginSalePrice();
            if(StringUtils.isBlank(originSalePrice)){
                itemEntity.setOriginPrice(" ");
            }else{
                itemEntity.setOriginPrice(String.format("(%s)", originSalePrice));//外币
            }


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
        itemDetailEntity.setItemDesc(itemDO.getItemName());

        String commissionMode = itemDO.getCommissionRate();

        if (price == null){
            price = 0d;
        }
        if (StringUtils.isBlank(commissionMode)){
            commissionMode = "0";
        }

        //TODO refactor
        BigDecimal pb = new BigDecimal(price);
        BigDecimal cb = new BigDecimal(commissionMode);
        BigDecimal cbm = pb.multiply(cb).setScale(2, BigDecimal.ROUND_HALF_UP);

        itemDetailEntity.setSharePrice(cbm.toString());
        itemDetailEntity.setPrice(price.toString());

        String originSalePrice = itemDO.getOriginSalePrice();
        if(StringUtils.isBlank(originSalePrice)){
            itemDetailEntity.setOriginPrice("");//外币
        }else{
            itemDetailEntity.setOriginPrice(String.format("(%s)", originSalePrice));//外币
        }


        itemDetailEntity.setTitle(itemDO.getItemName());

        String mainPic = itemDO.getMainPic();

        JSONObject jsonObject = JSONObject.fromObject(mainPic);
        JSONArray array = jsonObject.getJSONArray("picList");
        //JSONArray jsonArray = JSONArray.fromObject(jsonObject.getString("picList"));
        List<String> picList = new ArrayList<>();

        int maxSize = array.size() > 8 ? 8 : array.size();
        for(int i = 0; i < maxSize; i ++) {
            String pic = array.getJSONObject(i).getString("url");
            pic = formImg(pic);
            picList.add(pic);
        }

        itemDetailEntity.setImgList(picList);

        jsonResult.buildIsSuccess(true).buildData(itemDetailEntity);
        return BaseDto.toString(jsonResult);
    }

    public String formImg(String url){
        if (url.indexOf("?") == -1){
            return url;
        }
        int len = url.indexOf("?");
        url = url.substring(0, len);
        url = url + "@1c_1e_480w.webp";
        return url;
    }

}
