package com.wangqin.globalshop.web.controller.item;

import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.item.app.service.IItemService;
import com.wangqin.globalshop.web.dto.BaseDto;
import com.wangqin.globalshop.web.dto.api.ItemShareEntity;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class ShareApiController {
	
	@Autowired
	private IItemService itemService;
    @RequestMapping("/api/items/share")
    @ResponseBody
    public String detail(@RequestParam("itemCode") String itemCode,
                         @RequestParam("companyNo") String companyNo,
                         @RequestParam("userId") String userId){

        //TODO
    	
        JsonResult<ItemShareEntity> jsonResult = new JsonResult<>();
        ItemShareEntity ItemShareEntity = new ItemShareEntity();
        
        String pic = itemService.queryItemPicByItemCode(itemCode, companyNo);
        String desc = itemService.itemDetailByItemCode(itemCode, companyNo).getDetail();
        
        ItemShareEntity.setItemDesc(desc);
        JSONObject jsonObject = JSONObject.fromObject(pic);
        int num = Integer.parseInt(jsonObject.getString("mainPicNum"));
        JSONArray jsonArray = JSONArray.fromObject(jsonObject.getString("picList"));
        List<String> picList = new ArrayList<>();
        for(int i = 0; i < jsonArray.size(); i ++) {
        	JSONObject jsonPicList = jsonArray.getJSONObject(i);
        	picList.add((String) jsonPicList.get("uid"));
        }
        if(num > 8) {
        	
        }
        ItemShareEntity.setItemImgList(Arrays.asList("http://img.haihu.com/wq_logo.jpg"));

        jsonResult.buildIsSuccess(true).buildData(ItemShareEntity);
        return BaseDto.toString(jsonResult);
    }

}
