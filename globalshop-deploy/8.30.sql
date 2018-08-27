
###初始化intraMirror店铺,请确认商户companyNo:  yJlyUZ1gt5  E群大叔奢侈品直购平台
INSERT INTO `haidb2new`.`channel_shop`( `version`, `channel_no`, `company_no`, `shop_name`, `shop_code`, `expires_time`, `proxy_url`, `shop_type`, `open`, `gmt_modify`, `gmt_create`, `modifier`, `creator`, `is_del`)
VALUES ( 0, '5', 'yJlyUZ1gt5', 'IntraMirror', 'IM001', '2019-08-27 15:59:51', 'http://channel.intramirror.com/auth/token?buyer_id=EQunDaShu20180813&secret_key=324j89fgkljynbxe12&version=1.0', NULL, 1, NOW(), NOW(), '-1', '-1', 0);


####初始化intraMirror授权,请确认商户companyNo:  yJlyUZ1gt5  E群大叔奢侈品直购平台
INSERT INTO `haidb2new`.`jd_shop_oauth`( `version`, `channel_no`, `company_no`, `shop_code`, `access_token`, `expires_time`, `refresh_token`, `refresh_exprires_time`, `app_key`, `appsecret_key`, `access_key`, `access_value`, `server_url`, `token_url`, `shop_type`, `open`, `gmt_modify`, `gmt_create`, `modifier`, `creator`, `is_del`)
VALUES ( 0, '5', 'yJlyUZ1gt5', 'IM001', 'vdjguewjdi2ngksalw323et5ingsdlw12', '2019-08-27 15:59:51', NULL, NULL, 'EQunDaShu20180813', '324j89fgkljynbxe12', NULL, NULL, '', 'http://channel.intramirror.com/auth/token', NULL, 1, NOW(),  NOW(), '-1', '-1', 0);
