ALTER TABLE applet_config MODIFY secret VARCHAR(64) COMMENT '小程序secret';


ALTER TABLE `haidb2new`.`channel`
ADD COLUMN `sale_level` varchar(45) DEFAULT NULL,
ADD COLUMN `discount` double DEFAULT NULL,
ADD COLUMN `discount1` double DEFAULT NULL,
ADD COLUMN `discount2` double DEFAULT NULL,
ADD COLUMN `discount3` double DEFAULT NULL;

# 增加主图字段
ALTER TABLE `haidb2new`.`item_find`
ADD COLUMN `main_pic` varchar(2048) ;

# 增加字段长度
ALTER TABLE `haidb2new`.`db_migrate_send_record`
MODIFY COLUMN `db_script` varchar(6144) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL ;

ALTER TABLE `haidb2new`.`buyer_task_detail`
CHANGE COLUMN `mode` `mode` TINYINT(2) NULL DEFAULT '1' COMMENT '采购方式 0 线上 1线下' ;


# buyer_task新增任务描述
ALTER TABLE `haidb2new`.`buyer_task`
MODIFY COLUMN `remark` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '任务备注',
ADD COLUMN `task_desc` varchar(255) COMMENT '任务描述',
ADD COLUMN `image_url` varchar(1024) COMMENT '任务主图，item_find主图';

# buyer 唯一性所有增加删除字段
ALTER TABLE `haidb2new`.`buyer`
DROP INDEX `OPENID`,
ADD UNIQUE INDEX `OPENID`(`open_id`, `union_id`, `is_del`) USING BTREE;




ALTER TABLE applet_config ADD publish_status   INT(11) NULL COMMENT '小程序发布状态：1.已授权 2.已提交体验版 3.待审核 4.审核通过待发布 5.已发布';
ALTER TABLE applet_config ADD templet_id   INT(11) NULL COMMENT '小程序模板id';
ALTER TABLE applet_config ADD img_url  VARCHAR(64) NULL COMMENT '体验版二维码';
ALTER TABLE applet_config ADD audit_id   VARCHAR(64) NULL COMMENT ' 微信审核的id   用于查询审核状态等api';
ALTER TABLE applet_config ADD ext_json   VARCHAR(1024) NULL COMMENT '小程序的ext.json文件';
ALTER TABLE applet_config MODIFY authorizer_access_token VARCHAR(512) COMMENT '第三方授权平台token';


ALTER TABLE `warehouse` CHANGE COLUMN `address` `address` VARCHAR(256) NULL DEFAULT NULL;

insert into `logistic_company` (`id`, `name`, `code`, `code_in_kuaidi100`, `gmt_modify`, `gmt_create`, `modifier`, `creator`, `is_del`) values('10','申通','001','shentong','2018-06-14 15:21:06','2018-06-14 15:21:06','SYSTEM','SYSTEM','0');
insert into `logistic_company` (`id`, `name`, `code`, `code_in_kuaidi100`, `gmt_modify`, `gmt_create`, `modifier`, `creator`, `is_del`) values('11','中通','002','zhongtong','2018-06-14 15:21:06','2018-06-14 15:21:06','SYSTEM','SYSTEM','0');
insert into `logistic_company` (`id`, `name`, `code`, `code_in_kuaidi100`, `gmt_modify`, `gmt_create`, `modifier`, `creator`, `is_del`) values('12','圆通','003','yuantong','2018-06-14 15:21:06','2018-06-14 15:21:06','SYSTEM','SYSTEM','0');
insert into `logistic_company` (`id`, `name`, `code`, `code_in_kuaidi100`, `gmt_modify`, `gmt_create`, `modifier`, `creator`, `is_del`) values('13','顺丰','004','shunfeng','2018-06-14 15:21:06','2018-06-14 15:21:06','SYSTEM','SYSTEM','0');
insert into `logistic_company` (`id`, `name`, `code`, `code_in_kuaidi100`, `gmt_modify`, `gmt_create`, `modifier`, `creator`, `is_del`) values('14','韵达','005','yunda','2018-06-14 15:21:06','2018-06-14 15:21:06','SYSTEM','SYSTEM','0');
insert into `logistic_company` (`id`, `name`, `code`, `code_in_kuaidi100`, `gmt_modify`, `gmt_create`, `modifier`, `creator`, `is_del`) values('15','天天','006','tiantian','2018-06-14 15:21:06','2018-06-14 15:21:06','SYSTEM','SYSTEM','0');
insert into `logistic_company` (`id`, `name`, `code`, `code_in_kuaidi100`, `gmt_modify`, `gmt_create`, `modifier`, `creator`, `is_del`) values('16','4PX','007','zhuanyunsifang','2018-06-14 15:21:07','2018-06-14 15:21:07','SYSTEM','SYSTEM','0');
insert into `logistic_company` (`id`, `name`, `code`, `code_in_kuaidi100`, `gmt_modify`, `gmt_create`, `modifier`, `creator`, `is_del`) values('17','百世快递','008','baishiwuliu','2018-06-14 15:21:07','2018-06-14 15:21:07','SYSTEM','SYSTEM','0');
insert into `logistic_company` (`id`, `name`, `code`, `code_in_kuaidi100`, `gmt_modify`, `gmt_create`, `modifier`, `creator`, `is_del`) values('18','邮政（国内）','009','youzhengguonei','2018-07-26 10:42:57','2018-07-26 10:42:59','SYSTEM','SYSTEM','0');
insert into `logistic_company` (`id`, `name`, `code`, `code_in_kuaidi100`, `gmt_modify`, `gmt_create`, `modifier`, `creator`, `is_del`) values('19','邮政（国际）','010','youzhengguoji','2018-07-26 10:43:54','2018-07-26 10:43:55','SYSTEM','SYSTEM','0');
insert into `logistic_company` (`id`, `name`, `code`, `code_in_kuaidi100`, `gmt_modify`, `gmt_create`, `modifier`, `creator`, `is_del`) values('20','EMS','011','ems','2018-07-26 10:45:11','2018-07-26 10:45:16','SYSTEM','SYSTEM','0');

###增加任务主图的字段长度
ALTER TABLE `haidb2new`.`buyer_task`
MODIFY COLUMN `image_url` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '任务主图，item_find主图';

###item_sku_scale增加索引
ALTER TABLE `haidb2new`.`item_sku_scale`
ADD INDEX `skucode_index`(`sku_code`) USING BTREE COMMENT 'skucode查询比较频繁';

ALTER TABLE mall_shopping_cart MODIFY item_name VARCHAR(128) COMMENT '商品名称';

CREATE TABLE `haidb2new`.`monitor_record`(
  `id` BIGINT(64) NOT NULL AUTO_INCREMENT,
  `app_name` VARCHAR(64) COMMENT '应用名称',
  `monitor_url` VARCHAR(256) COMMENT '监控url',
  `status` VARCHAR(2) COMMENT '0-新建，1-生效，2-暂停',
  `is_del` TINYINT(1) DEFAULT 0,
  `creator` VARCHAR(32),
  `modifier` VARCHAR(32),
  `gmt_create` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `gmt_modify` DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=INNODB CHARSET=utf8;

