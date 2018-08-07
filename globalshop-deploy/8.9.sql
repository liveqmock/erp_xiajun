ALTER TABLE `company` ADD `state` VARCHAR(20) DEFAULT NULL COMMENT '省';
ALTER TABLE `company` ADD `city` VARCHAR(20) DEFAULT NULL COMMENT '市';
ALTER TABLE `company` ADD `district` VARCHAR(20) DEFAULT NULL COMMENT '区';
ALTER TABLE `company` ADD `full_address` VARCHAR(100) DEFAULT NULL COMMENT '详细地址';
ALTER TABLE `company` ADD `oversea_address` VARCHAR(100) DEFAULT NULL COMMENT '海外地址';
ALTER TABLE `company` ADD `country` VARCHAR(20) DEFAULT NULL COMMENT '国家';
ALTER TABLE `company` ADD `main_category` VARCHAR(20) DEFAULT NULL COMMENT '主要品类';
ALTER TABLE `company` ADD `offline_annual_sale` DOUBLE(10,2) DEFAULT NULL COMMENT '线下年销售额';
ALTER TABLE `company` ADD `online_annual_sale` DOUBLE(10,2) DEFAULT NULL COMMENT '线上年销售额';

ALTER TABLE auth_organization MODIFY address VARCHAR(200) COMMENT '地址';

ALTER TABLE auth_organization MODIFY org_id VARCHAR(64);

ALTER TABLE applet_config MODIFY appid VARCHAR(64) NULL COMMENT '小程序appid';