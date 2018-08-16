ALTER TABLE `haidb2new`.`jd_shop_config`
ADD COLUMN `version` bigint(19) AFTER `id`;

ALTER TABLE `haidb2new`.`channel_listing_item_sku`
DROP INDEX `ITEM_SKU_ID`,
ADD INDEX `ITEM_SKU_ID`(`item_code`) USING BTREE;

CREATE TABLE `id_card`  (
  `id` bigint(19) UNSIGNED NOT NULL AUTO_INCREMENT,
  `gmt_modify` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '操作时间',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT 0,
  `real_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '姓名',
  `id_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '身份证',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name_card_union_index`(`id_number`, `real_name`) USING BTREE COMMENT '姓名和身份证唯一性索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

###初始化海狐授权信息
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
	'',
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
	'',
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

# 对接盛付通需要的四张表
# 支付单表
DROP TABLE IF EXISTS `shengpay_pay`;
CREATE TABLE `shengpay_pay` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `company_no` varchar(64) NOT NULL COMMENT '所属公司编号',
  `merchant_order_no` varchar(64) NOT NULL COMMENT '商户系统内的唯一订单号',
  `amount` double NOT NULL COMMENT '该笔订单的交易金额，单位默认为RMB-元，精确到小数点后两位，如：23.42',
  `currency` varchar(10) DEFAULT NULL COMMENT '货币类型',
  `pay_channel` varchar(10) DEFAULT NULL COMMENT '支付渠道',
  `sft_order_no` varchar(64) DEFAULT NULL COMMENT '盛付通系统内针对此商户订单的唯一订单号，如: C20160105105839885474',
  `order_create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '订单创建时间',
  `trace_no` varchar(64) DEFAULT NULL COMMENT '商户生成的报文唯一消息标识',
  `trans_no` varchar(64) DEFAULT NULL COMMENT '商户调用收单接口成功后盛付通返回的交易订单号',
  `trans_status` varchar(10) DEFAULT NULL COMMENT '支付状态',
  `trans_amount` double DEFAULT NULL COMMENT '实际交易金额',
  `trans_type` varchar(10) DEFAULT NULL COMMENT '交易类型',
  `trans_time` datetime DEFAULT NULL COMMENT '交易时间',
  `creator` varchar(32) DEFAULT 'system' COMMENT '创建者',
  `modifier` varchar(32) DEFAULT 'system' COMMENT '修改者',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '软删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

# 退款单表
DROP TABLE IF EXISTS `shengpay_refund`;
CREATE TABLE `shengpay_refund` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `company_no` varchar(64) NOT NULL COMMENT '所属公司编号',
  `merchant_order_no` varchar(64) NOT NULL COMMENT '商户系统内的唯一订单号',
  `refund_order_no` varchar(64) NOT NULL COMMENT '退款请求流水号(商户系统唯一)',
  `refund_amount` double DEFAULT NULL COMMENT '退款金额(与支付金额一致)',
  `status` varchar(10) DEFAULT NULL COMMENT '退款状态',
  `refund_trans_no` varchar(64) DEFAULT NULL COMMENT '盛付通退款订单号',
  `sft_order_no` varchar(64) DEFAULT NULL COMMENT '盛付通系统内针对此商户订单的唯一订单号，如: C20160105105839885474',
  `order_amount` double DEFAULT NULL COMMENT '订单金额',
  `refund_time` datetime DEFAULT NULL COMMENT '退款时间',
  `trace_no` varchar(64) DEFAULT NULL COMMENT '商户生成的报文唯一消息标识',
  `creator` varchar(32) DEFAULT 'system' COMMENT '创建者',
  `modifier` varchar(32) DEFAULT 'system' COMMENT '修改者',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '软删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

# 分账单表
DROP TABLE IF EXISTS `shengpay_sharing`;
CREATE TABLE `shengpay_sharing` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `company_no` varchar(64) NOT NULL COMMENT '所属公司编号',
  `merchant_order_no` varchar(64) DEFAULT NULL COMMENT '商户系统内的唯一订单号',
  `sharing_order_no` varchar(64) DEFAULT NULL COMMENT '分账请求订单号',
  `sharing_query_order_no` varchar(64) DEFAULT NULL COMMENT '分账查询请求订单号',
  `sharing_req_no` varchar(64) DEFAULT NULL COMMENT '分账请求号',
  `status` varchar(10) DEFAULT NULL COMMENT '分账状态 C:创建 P:处理中 S:成功 F:失败 R:被风控',
  `creator` varchar(32) DEFAULT 'system' COMMENT '创建者',
  `modifier` varchar(32) DEFAULT 'system' COMMENT '修改者',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '软删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

# 分账单子项表
DROP TABLE IF EXISTS `shengpay_sharing_item`;
CREATE TABLE `shengpay_sharing_item` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `company_no` varchar(64) NOT NULL COMMENT '所属公司编号',
  `sharing_req_no` varchar(64) NOT NULL COMMENT '盛付通分账流水号',
  `sharing_no` varchar(64)  DEFAULT NULL COMMENT '分帐子项请求序号',
  `sd_sharing_no` varchar(64) DEFAULT NULL COMMENT '盛付通子分账流水号',
  `status` varchar(10) DEFAULT NULL COMMENT '分账状态：0处理中，1成功',
  `sharing_amount` double DEFAULT NULL COMMENT '分帐金额，如10.00表示10元',
  `sharing_rate` double DEFAULT NULL COMMENT '分帐比例 如0.50表示50%',
  `payee_id` varchar(64) DEFAULT NULL COMMENT '会员标识',
  `payee_id_type` varchar(10) DEFAULT NULL COMMENT '会员类型1：商户号，4：memberid',
  `creator` varchar(32) DEFAULT 'system' COMMENT '创建者',
  `modifier` varchar(32) DEFAULT 'system' COMMENT '修改者',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '软删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

