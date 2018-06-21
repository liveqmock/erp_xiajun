package com.wangqin.globalshop.web.controller.item;

import com.wangqin.globalshop.common.base.BaseDto;
import com.wangqin.globalshop.common.utils.DimensionCodeUtil;
import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.item.app.service.IItemService;
import com.wangqin.globalshop.web.dto.api.ItemShareEntity;
import net.sf.json.JSONObject;


import net.sf.json.JSONArray;

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

    public static final String ACCESS_TOKENURL = "https://api.weixin.qq.com/cgi-bin/token";
    public static final String ACCESS_TOKENPARAM = "grant_type=client_credential&appid=wxdef3e972a4a93e91&secret=fef11f402f8e8f3c1442163155aeb65a";

    @RequestMapping("/api/items/share")
    @ResponseBody
    public String detail(@RequestParam("itemCode") String itemCode,
                         @RequestParam("companyNo") String companyNo,
                         @RequestParam("userId") String userId){

        //TODO
    	
        JsonResult<ItemShareEntity> jsonResult = new JsonResult<>();
        ItemShareEntity entity = new ItemShareEntity();
        entity.setItemDesc("share from wq");

        //TODO refactor
        String reponse = DimensionCodeUtil.sendGet(ACCESS_TOKENURL, ACCESS_TOKENPARAM);
        JSONObject myJson = JSONObject.fromObject(reponse);
        String token = (String) myJson.get("access_token");
        String picUrl = itemService.generateItemShareUrl(userId, companyNo, itemCode, "pages/item/detail", token);

        entity.setItemImgList(Arrays.asList(picUrl));

        ItemShareEntity itemShareEntity = new ItemShareEntity();

        String pic = itemService.queryItemPicByItemCodeAndCompanyNo(itemCode, companyNo);
        String desc = itemService.itemDetailByItemCode(itemCode, companyNo).getDetail();

        itemShareEntity.setItemDesc(desc);
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

        jsonResult.buildIsSuccess(true).buildData(entity);
        return BaseDto.toString(jsonResult);
    }

}
