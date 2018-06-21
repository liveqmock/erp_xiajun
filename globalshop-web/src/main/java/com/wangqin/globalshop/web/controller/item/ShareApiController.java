package com.wangqin.globalshop.web.controller.item;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;
import com.wangqin.globalshop.common.base.BaseDto;
import com.wangqin.globalshop.common.utils.DimensionCodeUtil;
import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.item.app.service.IItemService;
import com.wangqin.globalshop.web.dto.api.ItemShareEntity;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
        
        ItemDO item = itemService.itemDetailByItemCode(itemCode, companyNo);
        itemShareEntity.setItemDesc(item.getDetail());
        
        String mainPic = item.getMainPic();

        JSONObject jsonObject = JSONObject.fromObject(mainPic);
        JSONArray array = jsonObject.getJSONArray("picList");
        //JSONArray jsonArray = JSONArray.fromObject(jsonObject.getString("picList"));
        List<String> picList = new ArrayList<>();

        int maxSize = array.size() > 8 ? 8 : array.size();
        for(int i = 0; i < maxSize; i ++) {
        	String pic = array.getJSONObject(i).getString("url");
        	picList.add(pic);
        }

        //插入分享码图片
        if (picList.size() < 5){
            picList.add(picUrl);
        }else{
            List<String> tmp = new ArrayList<>();
            int i = 0;
            for (String purl : picList){
                if (i == 4){ //TODO refactor
                    tmp.add(picUrl);
                }
                tmp.add(purl);
                i++;
            }
            picList = tmp;
        }
        itemShareEntity.setItemImgList(picList);
        jsonResult.buildIsSuccess(true).buildData(itemShareEntity);
        return BaseDto.toString(jsonResult);
    }

}
