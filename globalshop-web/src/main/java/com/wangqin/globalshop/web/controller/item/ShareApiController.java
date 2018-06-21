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

        JsonResult<ItemShareEntity> jsonResult = new JsonResult<>();

        //TODO refactor
        //生成分享
        String reponse = DimensionCodeUtil.sendGet(ACCESS_TOKENURL, ACCESS_TOKENPARAM);
        JSONObject myJson = JSONObject.fromObject(reponse);
        String token = (String) myJson.get("access_token");
        String picUrl = itemService.generateItemShareUrl(userId, companyNo, itemCode, "pages/item/detail", token);


        ItemShareEntity itemShareEntity = new ItemShareEntity();

        String pic = itemService.queryItemPicByItemCodeAndCompanyNo(itemCode, companyNo);
        String desc = itemService.itemDetailByItemCode(itemCode, companyNo).getDetail();
        //TODO 写一个sql，查出商品，


        itemShareEntity.setItemDesc(desc);

        JSONObject jsonObject = JSONObject.fromObject(pic);
        JSONArray jsonArray = JSONArray.fromObject(jsonObject.getString("picList"));
        List<String> picList = new ArrayList<>();

        int maxSize = jsonArray.size() > 8 ? 8 : jsonArray.size();
        for(int i = 0; i < maxSize; i ++) {
        	JSONObject jsonPicList = jsonArray.getJSONObject(i);
        	picList.add((String) jsonPicList.get("uid"));
        }

        //插入分享码图片
        if (picList.size() < 5){
            picList.add(picUrl);
        }else{
            List<String> tmp = new ArrayList<>();
            int i = 0;
            for (String purl : picList){
                if (i++ == 5){
                    tmp.add(picUrl);
                }
                tmp.add(purl);
            }
            picList = tmp;
        }
        itemShareEntity.setItemImgList(picList);
        jsonResult.buildIsSuccess(true).buildData(itemShareEntity);
        return BaseDto.toString(jsonResult);
    }

}
