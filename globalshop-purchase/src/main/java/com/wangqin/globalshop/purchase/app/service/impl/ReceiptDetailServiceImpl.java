package com.wangqin.globalshop.purchase.app.service.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerReceiptDetailDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.BuyerReceiptDetailVo;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.BuyerReceiptDetailDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemSkuMapperExt;
import com.wangqin.globalshop.common.utils.BeanUtils;
import com.wangqin.globalshop.common.utils.EasyUtil;
import com.wangqin.globalshop.common.utils.ImageUtil;
import com.wangqin.globalshop.purchase.app.service.IReceiptDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author biscuit
 * @data 2018/06/12
 */
@Service
public class ReceiptDetailServiceImpl implements IReceiptDetailService {
    @Autowired
    private BuyerReceiptDetailDOMapperExt mapper;

    @Autowired
    private ItemSkuMapperExt skuDOMapperExt;

    @Override
    public List<BuyerReceiptDetailDO> list(BuyerReceiptDetailDO receipt) {
        return mapper.list(receipt);
    }

    @Override
    public List<BuyerReceiptDetailVo> getVoList(BuyerReceiptDetailDO receipt){
        List<BuyerReceiptDetailDO> doList = mapper.list(receipt);
        List<BuyerReceiptDetailVo> voList = new ArrayList<>();
        if(!EasyUtil.isListEmpty(doList)){
            for(BuyerReceiptDetailDO detail : doList){
                BuyerReceiptDetailVo vo = new BuyerReceiptDetailVo();
                BeanUtils.copies(detail, vo);

                ItemSkuDO skuSo = new ItemSkuDO();
                skuSo.setSkuCode(detail.getSkuCode());
                skuSo.initCompany();
                skuSo.setUpc(detail.getUpc());

                ItemSkuDO skuDO = skuDOMapperExt.queryItemSku(skuSo);
                if(skuDO != null){
                    vo.setCostPrice(skuDO.getCostPrice());
                    vo.setItemName(skuDO.getItemName());

                    if(skuDO.getCostPrice() == null || skuDO.getCostPrice() <= 0 || vo.getPrice() == null || vo.getPrice() <= 0){
                        vo.setDisCount(skuDO.getDiscount());
                    }else {
                        vo.setDisCount(skuDO.getCostPrice()/vo.getPrice());
                    }
                    vo.setSkuPic(ImageUtil.getImageUrl(skuDO.getSkuPic()));
                }
                voList.add(vo);
            }
        }
        return voList;
    }
}
