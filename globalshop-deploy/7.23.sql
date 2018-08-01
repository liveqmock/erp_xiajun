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
