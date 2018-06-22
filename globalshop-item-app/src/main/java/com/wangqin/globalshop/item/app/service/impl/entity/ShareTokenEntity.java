package com.wangqin.globalshop.item.app.service.impl.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShareTokenEntity {
    private String itemCode;
    private String companyNo;
    private String shareUserId;
    private String uuidShare;
    private String uuidSale;
    private String saleUserId;
    private String saleWxId;
    private String saleParentUserId;
    private String saleParentWxId;
    private long seq;


    public static ShareTokenEntity buildShareToken(String userId, String companyNo, String itemCode, String uuidShare){
        ShareTokenEntity entity = new ShareTokenEntity();
        entity.setSaleUserId(userId);
        entity.setCompanyNo(companyNo);
        entity.setItemCode(itemCode);
        entity.setUuidShare(uuidShare);
        entity.setUuidSale("");
        entity.setSaleUserId(userId);
        entity.setSeq(1L);
        return entity;
    }

}
