package com.wangqin.globalshop.web.dto.api;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ItemEntity implements Serializable {
    private String title;
    private String itemCode;
    private String price;
    private String originPrice;
    private String imgUrl;
}
