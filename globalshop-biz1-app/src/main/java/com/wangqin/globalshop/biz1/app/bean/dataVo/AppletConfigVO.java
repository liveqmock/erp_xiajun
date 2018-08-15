package com.wangqin.globalshop.biz1.app.bean.dataVo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class AppletConfigVO {
    private Long id;

    private String companyNo;


    /*
         显示以下敏感信息
         private String payKey;
         private String secret;
         private String authorizerRefreshToken;
         private String authorizerAccessToken;
     */
    private String appid;

    private String appletType;

    private String creator;

    private String modifier;

    private Integer publishStatus;

    private Integer templetId;

    private String imgUrl;

    private String auditId;

    private String extJson;

    private String mchId;

    private String status;

    private Date gmtCreate;
    private Date gmtModify;

    private String requestdomain;

    private String wsrequestdomain;

    private String uploaddomain;

    private String downloaddomain;

    private String webviewdomain;

    private String companyName;
}