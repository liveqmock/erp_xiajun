package com.wangqin.globalshop.web.dto.api;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class MyHomeOrderDetailEntity implements Serializable {

    private String orderDetailDesc;

    private List<OrderDetailEntity> orderDetailList;
}
