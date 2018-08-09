ALTER TABLE `company` ADD `state` VARCHAR(20) DEFAULT NULL COMMENT '省';
ALTER TABLE `company` ADD `city` VARCHAR(20) DEFAULT NULL COMMENT '市';
ALTER TABLE `company` ADD `district` VARCHAR(20) DEFAULT NULL COMMENT '区';
ALTER TABLE `company` ADD `full_address` VARCHAR(100) DEFAULT NULL COMMENT '详细地址';
ALTER TABLE `company` ADD `oversea_address` VARCHAR(100) DEFAULT NULL COMMENT '海外地址';
ALTER TABLE `company` ADD `country` VARCHAR(20) DEFAULT NULL COMMENT '国家';
ALTER TABLE `company` ADD `main_category` VARCHAR(20) DEFAULT NULL COMMENT '主要品类';
ALTER TABLE `company` ADD `offline_annual_sale` DOUBLE(10,2) DEFAULT NULL COMMENT '线下年销售额';
ALTER TABLE `company` ADD `online_annual_sale` DOUBLE(10,2) DEFAULT NULL COMMENT '线上年销售额';
ALTER TABLE company ADD company_group VARCHAR(64) NULL COMMENT '归属公司   如果与companyNo相同，则表示当前为一个公司
如果与companyNo不同，表示当前的companyNo对应得到company为companyGroup对应的公司下面的一个商户';
ALTER TABLE auth_organization MODIFY address VARCHAR(200) COMMENT '地址';

ALTER TABLE auth_organization MODIFY org_id VARCHAR(64);

ALTER TABLE applet_config MODIFY appid VARCHAR(64) NULL COMMENT '小程序appid';

ALTER TABLE `company` ADD COLUMN `admin_no` VARCHAR(64) NOT NULL COMMENT '管理员UserNo';


CREATE TABLE `item_qrcode_share`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gmt_modify` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '操作时间',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT 0,
  `share_no` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `company_no` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所属公司id',
  `item_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `pic_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '二维码地址',
  `ext` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique_index`(`share_no`) USING BTREE COMMENT '唯一性性索引',
  UNIQUE INDEX `search_index`(`item_code`, `user_no`, `company_no`) USING BTREE COMMENT '加快查询'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;




ALTER TABLE `item` ADD COLUMN `is_abroad` INT(4) NOT NULL DEFAULT '1' COMMENT '0:国内,1:海外';
ALTER TABLE `item` ADD COLUMN `shelf_method` INT(4) NOT NULL DEFAULT '0' COMMENT '0:立即售卖,1:暂不售卖;2:自定义';
ALTER TABLE `item_sku` ADD COLUMN `goods_no` VARCHAR(64) NULL COMMENT '货号';


ALTER TABLE channel_sale_price MODIFY channal_no channel_no varchar(64) NOT NULL DEFAULT '';

ALTER TABLE company MODIFY  COLUMN country int(2)