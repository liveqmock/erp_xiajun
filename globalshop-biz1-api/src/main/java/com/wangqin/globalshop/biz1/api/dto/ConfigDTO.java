package com.wangqin.globalshop.biz1.api.dto;

import java.util.Date;

import lombok.Data;

/**
 * Created by Patrick on 15/12/2017.
 */
@Data
public class ConfigDTO{
    private Long id;

    private String configType;

    private String keyName;

    private String configValue;

    private String configDesc;

    private String ext1;

    private String isDeleted;

    private String creator;

    private Date gmtCreated;

    private String modifier;

    private Date gmtModified;

}
