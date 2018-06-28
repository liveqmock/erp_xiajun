package com.wangqin.globalshop.web.controller.order;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.helper.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dto.MyOrderDTO;
import com.wangqin.globalshop.common.base.BaseDto;
import com.wangqin.globalshop.common.utils.DateUtil;
import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.item.app.service.IItemService;
import com.wangqin.globalshop.mall.service.IMallSubOrderService;
import com.wangqin.globalshop.web.dto.api.MyHomeOrderDetailEntity;
import com.wangqin.globalshop.web.dto.api.MyHomeOrderEntity;
import com.wangqin.globalshop.web.dto.api.OrderDetailEntity;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;







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

        JsonResult<Map<String,Object>> jsonResult = new JsonResult<>();
        Map<String, Object> result = new HashMap<>();
        List<MyHomeOrderEntity> orders = new ArrayList<>();
        result.put("orderList",orders);

        //分页的计算
        int start = (Integer.parseInt(pageNo)-1)*Integer.parseInt(pageSize);
        //获取订单list
        List<MyOrderDTO> myOrderList = mallSubOrderService.queryOrderByShareUserId(userId, start, Integer.parseInt(pageSize));
        if(CollectionUtils.isEmpty(myOrderList)) {//无订单
           result.put("commission","0.00");
           jsonResult.buildIsSuccess(true).buildData(result);
           return BaseDto.toString(jsonResult);
        }
        Double totalCommission = 0.0;
        for(MyOrderDTO myOrder:myOrderList) {
        	MyHomeOrderEntity entity = new MyHomeOrderEntity();
        	entity.setCommission(myOrder.getCommission());
        	entity.setOrderCount(myOrder.getOrderCount());
        	entity.setTime(myOrder.getTime());
        	orders.add(entity);
        	totalCommission += Double.parseDouble(myOrder.getCommission());
        }
       
        result.put("commission",totalCommission);
        
        
        jsonResult.buildIsSuccess(true).buildData(result);
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

        List<OrderDetailEntity> orderDetailList = new ArrayList<>();
        
        //分页的计算
        int start = (Integer.parseInt(pageNo)-1)*Integer.parseInt(pageSize);
        //订单详情
        List<MallSubOrderDO> orderList = mallSubOrderService.queryOrderDetailByTime(userId, time, start, Integer.parseInt(pageSize));
        if(CollectionUtils.isEmpty(orderList)) {
        	 entity.setOrderDetailList(orderDetailList);
        	 jsonResult.buildIsSuccess(true).buildData(entity);
        	 String summary =  String.format("%s个订单，佣金%s", 0, 0.00);
             entity.setOrderDetailDesc(summary);
             return BaseDto.toString(jsonResult);
        }
        
        BigDecimal totalMoney = new BigDecimal(0);
        for(MallSubOrderDO order:orderList) {
        	OrderDetailEntity tmp = new OrderDetailEntity();
        	tmp.setCommission(order.getShareMoney().toString());
        	String itemPic = itemService.queryItemPicByItemCode(order.getItemCode());
        	
        	//筛选出一张图片
        	JSONObject jsonObject = JSONObject.fromObject(itemPic);
            JSONArray array = jsonObject.getJSONArray("picList");
            JSONObject imgObject = array.getJSONObject(0);
            

        	tmp.setImgUrl(imgObject.getString("url"));
        	       	
        	tmp.setOrderNo(order.getOrderNo());
        	tmp.setOrderTime(DateUtil.formatDate(order.getGmtCreate()));
        	tmp.setRealPay("0");
        	orderDetailList.add(tmp);
        	totalMoney = totalMoney.add(order.getShareMoney());
        }
 
        String summary =  String.format("%s个订单，佣金%s", orderList.size(), totalMoney);
        entity.setOrderDetailDesc(summary);
        
        
        entity.setOrderDetailList(orderDetailList);
        jsonResult.buildIsSuccess(true).buildData(entity);
        return BaseDto.toString(jsonResult);
    }


}
