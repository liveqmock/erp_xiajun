package com.wangqin.globalshop.web.dto.api;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MyHomeOrderEntity implements Serializable {

    private String time;
    private String orderCount;
    private String commission;

}
