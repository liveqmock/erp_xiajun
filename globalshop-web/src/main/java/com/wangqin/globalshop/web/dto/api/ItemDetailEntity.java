package com.wangqin.globalshop.web.dto.api;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ItemDetailEntity {

    private String title;
    private String itemCode;
    private String itemDesc;
    private String price;
    private String originPrice;
    private String sharePrice;
    private List<String> imgList;
}
