package com.wangqin.globalshop.web.dto.api;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ItemShareEntity implements Serializable {

    private List<String> itemImgList;
    private String itemDesc;

}
