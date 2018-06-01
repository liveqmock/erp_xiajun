package com.wangqin.globalshop.web;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;
import com.wangqin.globalshop.order.app.service.mall.IMallOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * @author patrick
 * @date 2016-9-22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
public class TST {
    @Autowired
    private IMallOrderService mallOrderService;

    @Test
    public void testUpa() {
        MallOrderDO mallOrderDO = mallOrderService.selectById(1L);
        System.out.println(mallOrderDO);
    }


}
