package com.wangqin.globalshop.item.app.service.impl.entity;

import com.wangqin.globalshop.common.base.BaseDto;
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

    public static void main(String[] args) {
        String token = "{\"itemCode\":\"1529562233\",\"companyNo\":\"YiJianFenXiang\",\"uuidShare\":\"449536223fd94f79b4b278e06e3b171c\",\"uuidSale\":\"\",\"saleUserId\":\"2\",\"seq\":1}";

        ShareTokenEntity entity = BaseDto.fromJson(token, ShareTokenEntity.class);
        System.out.println(entity.getUuidShare());

    }

}
