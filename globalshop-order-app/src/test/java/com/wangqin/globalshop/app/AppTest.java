package com.wangqin.globalshop.app;


import com.alibaba.fastjson.JSON;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.MallOrderVO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.MallSubOrderVO;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "/META-INF/spring/applicationContext.xml" })
public class AppTest
{
        public static void main(String[] args) throws  InterruptedException{
//            StdScheduler quartzScheduler = (StdScheduler)applicationContext.getBean("startQuertz");
//                quartzScheduler.start();
//            new ClassPathXmlApplicationContext("/META-INF/spring/applicationContext.xml");
                MallOrderVO mallOrderVO = new MallOrderVO();
                mallOrderVO.setActualAmount(32.23);
                mallOrderVO.setCustomerNo("customerNo");
                mallOrderVO.setCompanyNo("company");
                mallOrderVO.setShopCode("shop_code");
                mallOrderVO.setChannelOrderNo("channel_order_no");
                mallOrderVO.setOrderTime(new Date());
                mallOrderVO.setModifier("修改者");
                mallOrderVO.setCreator("创建者");
                mallOrderVO.setTotalAmount(999.1);//总金额
                mallOrderVO.setPayType((byte) 1);//支付方式
                mallOrderVO.setChannelCustomerNo("bunengweikong");
                List<MallSubOrderDO> list = new ArrayList<>();
                MallSubOrderDO orderDO = new MallSubOrderDO();
                orderDO.setChannelOrderNo("123123");
                orderDO.setCreator("cre");

                orderDO.setModifier("adasd");
                //为空出错
                orderDO.setCustomerNo("bunengweikong");

                //为空出错
                orderDO.setIsDel(false);


                orderDO.setReceiver("mmmm");
                orderDO.setSkuCode("S0206004Q117850001");
                orderDO.setSalePrice(1111.0);
                orderDO.setQuantity(132);
                list.add(orderDO);
                mallOrderVO.setOuterOrderDetails(list);
                String str = JSON.toJSONString(mallOrderVO);
//                Map<String,String> map =  new HashMap<>();
//                map.put("jsonStr",str);
                System.out.println(str);

        }

        @Test
        public void testAD() {
                MallSubOrderVO orderDO = new MallSubOrderVO();
                orderDO.setId(53L);
                orderDO.setSkuCode("123");
                orderDO.setOrderNo("111");
                System.out.println(JSON.toJSON(orderDO));

        }
}
