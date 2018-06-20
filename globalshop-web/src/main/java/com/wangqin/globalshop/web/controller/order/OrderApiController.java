package com.wangqin.globalshop.web.controller.order;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dto.MyOrderDTO;
import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.item.app.service.IItemService;
import com.wangqin.globalshop.mall.service.IMallSubOrderService;
import com.wangqin.globalshop.web.dto.BaseDto;
import com.wangqin.globalshop.web.dto.api.MyHomeOrderDetailEntity;
import com.wangqin.globalshop.web.dto.api.MyHomeOrderEntity;
import com.wangqin.globalshop.web.dto.api.OrderDetailEntity;







@Controller
@RequestMapping("/api/orders")
public class OrderApiController {

	@Autowired
	private IMallSubOrderService mallSubOrderService;
	
	@Autowired
	private IItemService itemService;
	
    @RequestMapping("/myHome")
    @ResponseBody
    public String startUp(@RequestParam("userId") String userId,
                          @RequestParam("pageSize") String pageSize,
                          @RequestParam("pageNo") String pageNo){

        JsonResult<List<MyHomeOrderEntity>> jsonResult = new JsonResult<>();
        List<MyHomeOrderEntity> orders = new ArrayList<>();

        //分页的计算
        int start = (Integer.parseInt(pageNo)-1)*Integer.parseInt(pageSize);
        //获取订单list
        List<MyOrderDTO> myOrderList = mallSubOrderService.queryOrderByShareUserId(userId, start, pageSize);
        if(null == myOrderList) {//无订单
           jsonResult.buildIsSuccess(true).buildData(orders);
           return BaseDto.toString(jsonResult);
        }
        myOrderList.forEach(myOrder -> {
        	MyHomeOrderEntity entity = new MyHomeOrderEntity();
        	entity.setCommission(myOrder.getCommission());
        	entity.setOrderCount(myOrder.getOrderCount());
        	entity.setTime(myOrder.getTime());
        	orders.add(entity);
        });
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
        
        //分页的计算
        int start = (Integer.parseInt(pageNo)-1)*Integer.parseInt(pageSize);
        //订单详情
        List<MallSubOrderDO> orderList = mallSubOrderService.queryOrderDetailByTime(userId, time, start, pageSize);
        if(null == orderList) {
        	 entity.setOrderDetailList(orderDetailList);
        	 jsonResult.buildIsSuccess(true).buildData(entity);
             return BaseDto.toString(jsonResult);
        }
        
        orderList.forEach(order -> {
        	OrderDetailEntity tmp = new OrderDetailEntity();
        	tmp.setCommission(order.getShareMoney().toString());
        	String itemPic = itemService.queryItemPicByItemCode(order.getItemCode());
        	if(null != itemPic) {
        		tmp.setImgUrl(itemPic);
        	}       	
        	tmp.setOrderNo(order.getOrderNo());
        	tmp.setOrderTime(order.getShareCloseTime());
        	tmp.setRealPay("$12");
        	orderDetailList.add(tmp);
        });       
        entity.setOrderDetailList(orderDetailList);
        jsonResult.buildIsSuccess(true).buildData(entity);
        return BaseDto.toString(jsonResult);
    }


}
