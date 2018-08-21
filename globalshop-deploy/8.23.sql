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


###增加字段发货反馈字段
ALTER TABLE `haidb2new`.`jd_logistics`
ADD COLUMN `errorMsg` varchar(1024) COMMENT '错误信息';


UPDATE logistic_company set code_in_youzan='1', name_in_youzan='申通快递' where code='001' and name='申通';
UPDATE logistic_company set code_in_youzan='3', name_in_youzan='中通快递' where code='002' and name='中通';
UPDATE logistic_company set code_in_youzan='2', name_in_youzan='圆通速递' where code='003' and name='圆通';
UPDATE logistic_company set code_in_youzan='7', name_in_youzan='顺丰速运' where code='004' and name='顺丰';
UPDATE logistic_company set code_in_youzan='4', name_in_youzan='韵达快递' where code='005' and name='韵达';


UPDATE logistic_company set code_in_youzan='5', name_in_youzan='天天快递' where code='006' and name='天天';
UPDATE logistic_company set code_in_youzan='', name_in_youzan='' where code='007' and name='4PX';
UPDATE logistic_company set code_in_youzan='6', name_in_youzan='百世快递' where code='008' and name='百世快递';
UPDATE logistic_company set code_in_youzan='8', name_in_youzan='邮政快递包裹' where code='009' and name='邮政（国内）';
UPDATE logistic_company set code_in_youzan='12', name_in_youzan='邮政平邮' where code='010' and name='邮政（国际）';
UPDATE logistic_company set code_in_youzan='11', name_in_youzan='EMS' where code='011' and name='EMS';
