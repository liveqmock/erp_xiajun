ALTER TABLE mall_order ADD is_show TINYINT(1) DEFAULT 1 NULL COMMENT '是否显示';
-- ###初始化海狐授权信息，授权给一键分享
INSERT INTO `haidb2new`.`jd_shop_oauth` (
`channel_no`,
`company_no`,
`shop_code`,
`access_token`,
`expires_time`,
`refresh_token`,
`refresh_exprires_time`,
`app_key`,
`appsecret_key`,
`access_key`,
`access_value`,
`server_url`,
`token_url`,
`shop_type`,
`open`,
`gmt_modify`,
`gmt_create`,
`modifier`,
`creator`,
`is_del`
)
VALUES
	(
	'2',
	'YiJianFenXiang',
	'haihu001',
	'haihuhaitao',
	'2019-08-21 13:56:15',
	'irhua',
	NULL,
	'',
	'',
	NULL,
	NULL,
	'',
	'',
	NULL,
	1,
	'2018-08-14 13:56:15',
	'2018-08-12 09:16:12',
	'-1',
	'-1',
	0
	);

####给生产环境一键分享账号添加海狐渠道

INSERT INTO `haidb2new`.`channel_shop` (
`version`,
`channel_no`,
`company_no`,
`shop_name`,
`shop_code`,
`expires_time`,
`proxy_url`,
`shop_type`,
`open`,
`gmt_modify`,
`gmt_create`,
`modifier`,
`creator`,
`is_del`
)
VALUES
	(
	0,
	'2',
	'YiJianFenXiang',
	'海狐渠道',
	'haihu001',
	'2019-08-22 10:08:06',
	'',
	NULL,
	1,
	'2018-08-15 10:08:05',
	'2018-08-12 09:16:12',
	'-1',
	'-1',
	0
	);


INSERT INTO `auth_resource` VALUES
 (232,'myApp','我的小程序','',NULL,NULL,'',NULL,1,0,0,'2017-06-05 19:14:21','2018-06-12 14:14:37','SYS','SYS',0),
 (233,'marketing','营销管理','',NULL,NULL,'',NULL,1,0,0,'2017-06-05 19:14:21','2018-06-12 14:14:37','SYS','SYS',0),
 (234,'settings','设置','',NULL,NULL,'',NULL,1,0,0,'2017-06-05 19:14:21','2018-06-12 14:14:37','SYS','SYS',0),
 (235,'channel','渠道管理','',NULL,NULL,'',NULL,1,0,0,'2017-06-05 19:14:21','2018-06-12 14:14:37','SYS','SYS',0),

 (248,'cate_list','类目管理','',NULL,NULL,'',231,4,0,0,'2017-06-05 19:18:26','2018-06-12 14:14:37','SYS','SYS',0),

 (250,'appSettings','小程序设置','',NULL,NULL,'',232,1,0,0,'2017-06-05 19:14:21','2018-06-12 14:14:37','SYS','SYS',0),
 (251,'release','小程序发布管理','',NULL,NULL,'',232,2,0,0,'2017-06-05 19:18:26','2018-06-12 14:14:37','SYS','SYS',0),

 (253,'sale_channel','销售渠道管理','',NULL,NULL,'',233,1,0,0,'2017-06-05 19:14:21','2018-06-12 14:14:37','SYS','SYS',0),
 (254,'sale_agent','代理管理','',NULL,NULL,'',233,2,0,0,'2017-06-05 19:14:21','2018-06-12 14:14:37','SYS','SYS',0),

 (255,'business','商家信息管理','',NULL,NULL,'',234,2,0,0,'2017-06-05 19:14:21','2018-06-12 14:14:37','SYS','SYS',0),


 (257,'channelInstall','价格设置','',NULL,NULL,'',235,1,0,0,'2017-06-05 19:14:21','2018-06-12 14:14:37','SYS','SYS',0),
 (258,'channelAuth','渠道授权','',NULL,NULL,'',235,2,0,0,'2017-06-05 19:14:21','2018-06-12 14:14:37','SYS','SYS',0)
;

