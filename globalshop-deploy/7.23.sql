ALTER TABLE applet_config ADD publish_status   INT(11) NULL COMMENT '小程序发布状态：1.已授权 2.已提交体验版 3.待审核 4.审核通过待发布 5.已发布';
ALTER TABLE applet_config ADD templet_id   INT(11) NULL COMMENT '小程序模板id';
ALTER TABLE applet_config ADD img_url  VARCHAR(64) NULL COMMENT '体验版二维码';
ALTER TABLE applet_config ADD audit_id   VARCHAR(64) NULL COMMENT ' 微信审核的id   用于查询审核状态等api';
ALTER TABLE applet_config ADD ext_json   VARCHAR(1024) NULL COMMENT '小程序的ext.json文件';

ALTER TABLE `warehouse` CHANGE COLUMN `address` `address` VARCHAR(256) NULL DEFAULT NULL;

insert into `logistic_company` (`name`, `code`, `name_in_kuaidi100`, `code_in_kuaidi100`, `modifier`, `creator`) values('申通','001',NULL,'shentong','SYSTEM','SYSTEM');
insert into `logistic_company` (`name`, `code`, `name_in_kuaidi100`, `code_in_kuaidi100`, `modifier`, `creator`) values('中通','002',NULL,'zhongtong','SYSTEM','SYSTEM');
insert into `logistic_company` (`name`, `code`, `name_in_kuaidi100`, `code_in_kuaidi100`, `modifier`, `creator`) values('圆通','003',NULL,'yuantong','SYSTEM','SYSTEM');
insert into `logistic_company` (`name`, `code`, `name_in_kuaidi100`, `code_in_kuaidi100`, `modifier`, `creator`) values('顺丰','004',NULL,'shunfeng','SYSTEM','SYSTEM');
insert into `logistic_company` (`name`, `code`, `name_in_kuaidi100`, `code_in_kuaidi100`, `modifier`, `creator`) values('韵达','005',NULL,'yunda','SYSTEM','SYSTEM');
insert into `logistic_company` (`name`, `code`, `name_in_kuaidi100`, `code_in_kuaidi100`, `modifier`, `creator`) values('天天','006',NULL,'tiantian','SYSTEM','SYSTEM');
insert into `logistic_company` (`name`, `code`, `name_in_kuaidi100`, `code_in_kuaidi100`, `modifier`, `creator`) values('4PX','007',NULL,'zhuanyunsifang','SYSTEM','SYSTEM');
insert into `logistic_company` (`name`, `code`, `name_in_kuaidi100`, `code_in_kuaidi100`, `modifier`, `creator`) values('百世快递','008',NULL,'baishiwuliu','SYSTEM','SYSTEM');

###增加任务主图的字段长度
ALTER TABLE `haidb2new`.`buyer_task`
MODIFY COLUMN `image_url` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '任务主图，item_find主图';
