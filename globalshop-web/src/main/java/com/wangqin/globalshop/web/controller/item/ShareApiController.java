package com.wangqin.globalshop.web.controller.item;

import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.web.dto.BaseDto;
import com.wangqin.globalshop.web.dto.api.ItemShareEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

@Controller
public class ShareApiController {

    @RequestMapping("/api/items/share")
    @ResponseBody
    public String detail(@RequestParam("itemCode") String itemCode,
                         @RequestParam("companyNo") String companyNo,
                         @RequestParam("userId") String userId){

        //TODO
        JsonResult<ItemShareEntity> jsonResult = new JsonResult<>();
        ItemShareEntity entity = new ItemShareEntity();
        entity.setItemDesc("share from wq");
        entity.setItemImgList(Arrays.asList("http://img.haihu.com/wq_logo.jpg"));

        jsonResult.buildIsSuccess(true).buildData(entity);
        return BaseDto.toString(jsonResult);
    }

}
