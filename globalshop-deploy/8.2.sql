ALTER TABLE `item_sku` ADD COLUMN `sku_rate` DOUBLE(10,2) NOT NULL DEFAULT '0' COMMENT '代理佣金比例';



CREATE TABLE IF NOT EXISTS `mall_commision_apply` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `company_no` varchar(64) DEFAULT NULL,
  `order_no` varchar(64) DEFAULT NULL,
  `sub_order_no` varchar(64) DEFAULT NULL,
  `commision` varchar(4096) DEFAULT NULL,
  `status` varchar(2) DEFAULT NULL COMMENT '0-新建; 1-订单签收确认; 2-清分',
  `order_time` varchar(64) DEFAULT NULL,
  `order_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `share_user_id` varchar(64) DEFAULT NULL,
  `is_del` tinyint(1) DEFAULT '0',
  `creator` varchar(32) DEFAULT NULL,
  `modifier` varchar(32) DEFAULT NULL,
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modify` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `mall_sub_order_snapshot` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `company_no` varchar(64) DEFAULT NULL,
  `order_no` varchar(64) DEFAULT NULL,
  `sub_order_no` varchar(64) DEFAULT NULL,
  `item_code` varchar(64) DEFAULT NULL,
  `item_name` varchar(128) DEFAULT NULL,
  `sku_code` varchar(64) DEFAULT NULL,
  `sku_pic` varchar(512) DEFAULT NULL,
  `upc` varchar(64) DEFAULT NULL,
  `scale` varchar(128) DEFAULT NULL COMMENT '规格，用分号隔开',
  `ext` varchar(4096) DEFAULT NULL,
  `sale_price` decimal(16,2) DEFAULT NULL,
  `is_del` tinyint(1) DEFAULT '0',
  `creator` varchar(32) DEFAULT NULL,
  `modifier` varchar(32) DEFAULT NULL,
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modify` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


