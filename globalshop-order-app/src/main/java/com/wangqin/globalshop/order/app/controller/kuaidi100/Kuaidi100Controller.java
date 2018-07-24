package com.wangqin.globalshop.order.app.controller.kuaidi100;

import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.order.app.kuaidi_bean.Result;
import com.wangqin.globalshop.order.app.service.shipping.kuaidi100.IKuaidi100Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author angus
 * @date 2018/7/24
 */
@RestController
@RequestMapping("/kuaidi100")
public class Kuaidi100Controller {
    @Autowired
    private IKuaidi100Service kuaidi100Service;

    @PostMapping("/query")
    private JsonResult query(String shippingNo, String com, String num) {
        JsonResult result = new JsonResult();
        Result queryResult = kuaidi100Service.query(com, num);
        kuaidi100Service.handleTrackList(queryResult);
        result.setData(queryResult);
        return result;
    }

    /**
     * 通过 shippingNo 查询物流轨迹
     *
     * @param shippingNo
     * @return
     */
    @PostMapping("/queryByshippingNo")
    private JsonResult queryByshippingNo(String shippingNo) {
        JsonResult result = new JsonResult();
        try {
            Result queryResult = kuaidi100Service.query(shippingNo);
            result.buildIsSuccess(true).buildData(queryResult);
        } catch (ErpCommonException e) {
            result.buildIsSuccess(false).buildMsg(e.getErrorMsg());
        }
        return result;
    }
}
