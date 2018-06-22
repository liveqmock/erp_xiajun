package com.wangqin.globalshop.web.dto.api;

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
}
