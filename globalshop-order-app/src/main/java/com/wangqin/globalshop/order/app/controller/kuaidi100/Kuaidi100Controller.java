package com.wangqin.globalshop.order.app.controller.kuaidi100;

import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.order.app.kuaidi_bean.CommonShippingTrack;
import com.wangqin.globalshop.order.app.kuaidi_bean.Kuaidi100ShippingTrackResult;
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


    @PostMapping("/queryShippingTrack")
    private JsonResult queryShippingTrack(String shippingNo, String com, String num) {
        JsonResult result = new JsonResult();
        Kuaidi100ShippingTrackResult queryResult = kuaidi100Service.queryShippingTrack(com, num);
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
        JsonResult<CommonShippingTrack> result = new JsonResult<>();
        try {
            Kuaidi100ShippingTrackResult kuaidi100Result = kuaidi100Service.queryShippingTrack(shippingNo);
            result.buildIsSuccess(true).buildData(kuaidi100Result.toCommonShippingTrack());
        } catch (ErpCommonException e) {
            result.buildIsSuccess(false).buildMsg(e.getErrorMsg());
        }
        return result;
    }
}
