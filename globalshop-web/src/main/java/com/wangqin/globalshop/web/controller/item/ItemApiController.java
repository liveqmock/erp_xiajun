package com.wangqin.globalshop.web.controller.item;

import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.web.dto.BaseDto;
import com.wangqin.globalshop.web.dto.api.ItemDetailEntity;
import com.wangqin.globalshop.web.dto.api.ItemEntity;
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


        //-------------------------------------------------------
        //TODO
        ItemEntity item0 = new ItemEntity();
        item0.setItemCode("11111");
        item0.setPrice("120");       //默认SKU的价格
        item0.setOriginPrice("$20"); //TODO 外币
        item0.setTitle("title11111");
        item0.setImgUrl("http://img.haihu.com/wq_logo.jpg");
        items.add(item0);

        //-------------------------------------------------------

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


        ItemEntity item0 = new ItemEntity();
        item0.setItemCode("11111");
        item0.setPrice("120");
        item0.setOriginPrice("$20");
        item0.setTitle("title11111");
        item0.setImgUrl("http://img.haihu.com/wq_logo.jpg");
        items.add(item0);


        jsonResult.buildIsSuccess(true).buildData(items);
        return BaseDto.toString(jsonResult);
    }

    @RequestMapping("/detail")
    @ResponseBody
    public String detail(@RequestParam("itemCode") String itemCode,
                         @RequestParam("companyNo") String companyNo){

        //TODO
        JsonResult<ItemDetailEntity> jsonResult = new JsonResult<>();
        ItemDetailEntity entity = new ItemDetailEntity();

        entity.setItemCode("1111");
        entity.setItemDesc("xxxxxx");
        entity.setPrice("120");
        entity.setOriginPrice("$20");
        entity.setImgList(Arrays.asList("http://img.haihu.com/wq_logo.jpg"));


        jsonResult.buildIsSuccess(true).buildData(entity);
        return BaseDto.toString(jsonResult);
    }

}
