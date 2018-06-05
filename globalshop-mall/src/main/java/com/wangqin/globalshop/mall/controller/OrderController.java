package com.wangqin.globalshop.mall.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingTrackDO;

@Controller
@RequestMapping("/wx/order")
public class OrderController {

    @SuppressWarnings("unused")
    private static void ListSort(List<ShippingTrackDO> list) {
        Collections.sort(list, new Comparator<ShippingTrackDO>() {

            @Override
            public int compare(ShippingTrackDO o1, ShippingTrackDO o2) {
                Date dt1 = o1.getGmtCreate();
                Date dt2 = o2.getGmtCreate();
                if (dt1.getTime() > dt2.getTime()) {
                    return -1;
                } else if (dt1.getTime() < dt2.getTime()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
    }
}
