package com.wangqin.globalshop.web.controller.order;

import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.web.dto.BaseDto;
import com.wangqin.globalshop.web.dto.api.MyHomeOrderDetailEntity;
import com.wangqin.globalshop.web.dto.api.MyHomeOrderEntity;
import com.wangqin.globalshop.web.dto.api.OrderDetailEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.List;
@Setter
@Getter

@Controller
@RequestMapping("/api/orders")
public class OrderApiController {

    @RequestMapping("/myHome")
    @ResponseBody
    public String startUp(@RequestParam("userId") String userId,
                          @RequestParam("pageSize") String pageSize,
                          @RequestParam("pageNo") String pageNo){

        JsonResult<List<MyHomeOrderEntity>> jsonResult = new JsonResult<>();
        List<MyHomeOrderEntity> orders = new ArrayList<>();

        MyHomeOrderEntity entity = new MyHomeOrderEntity();
        entity.setCommission("123");
        entity.setOrderCount("1");
        entity.setTime("2018-06-20");
        orders.add(entity);


        jsonResult.buildIsSuccess(true).buildData(orders);
        return BaseDto.toString(jsonResult);
    }

    @RequestMapping("/detail")
    @ResponseBody
    public String detail( @RequestParam("userId") String userId,
                          @RequestParam("time") String time,
                          @RequestParam("pageSize") String pageSize,
                          @RequestParam("pageNo") String pageNo){

        JsonResult<MyHomeOrderDetailEntity> jsonResult = new JsonResult<>();
        MyHomeOrderDetailEntity entity = new MyHomeOrderDetailEntity();


        String summary =  String.format("%s个订单，佣金%s", "", "");
        entity.setOrderDetailDesc(summary);

        List<OrderDetailEntity> orderDetailList = new ArrayList<>();
        OrderDetailEntity tmp = new OrderDetailEntity();
        tmp.setOrderNo("1111111");
        tmp.setOrderTime("2018-06-20 11:11:11");
        tmp.setCommission("120");
        tmp.setRealPay("0");
        tmp.setImgUrl("http://img.haihu.com/wq_logo.jpg");
        orderDetailList.add(tmp);
        entity.setOrderDetailList(orderDetailList);
        jsonResult.buildIsSuccess(true).buildData(entity);
        return BaseDto.toString(jsonResult);
    }


}
