package com.wangqin.globalshop.purchase.app.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerReceiptDetailDO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.BuyerReceiptDetailVo;

import java.util.List;

/**
 * @author biscuit
 * @data 2018/06/12
 */
public interface IReceiptDetailService {
    List<BuyerReceiptDetailDO> list(BuyerReceiptDetailDO receipt);

    List<BuyerReceiptDetailVo> getVoList(BuyerReceiptDetailDO receipt);
}
