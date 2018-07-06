-- 20180622
-- 增加一张表，修改三张表
-- oneshare模块对数据库haidb2new的改动
-- 在133/111/180了上已经运行过了


use haidb2new;

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 新增applet_config表-- -- -- -- -- -- -- -- --
CREATE TABLE applet_config
(
  id          BIGINT(64) AUTO_INCREMENT
    PRIMARY KEY,
  company_no  VARCHAR(64)                        NOT NULL
  COMMENT '公司代号',
  secret      VARCHAR(64)                        NOT NULL
  COMMENT '小程序secret',
  appid       VARCHAR(64)                        NOT NULL
  COMMENT '小程序appid',
  applet_type VARCHAR(5)                         NOT NULL
  COMMENT '小程序的类型  1: 采购 2.商城',
  is_del      TINYINT(1) DEFAULT '0'             NULL,
  creator     VARCHAR(32) DEFAULT 'system'       NULL,
  modifier    VARCHAR(32) DEFAULT 'system'       NULL,
  gmt_create  DATETIME DEFAULT CURRENT_TIMESTAMP NULL,
  gmt_modify  DATETIME DEFAULT CURRENT_TIMESTAMP NULL
);

-- -- -- -- -- -- -- -- -- -- -- -- -- -- 新增wx_user表-- -- -- -- -- -- -- -- --
-- auto-generated definition
CREATE TABLE wx_user
(
  id                 BIGINT AUTO_INCREMENT
    PRIMARY KEY,
  open_id            VARCHAR(64)                        NULL
  COMMENT '微信open_id',
  union_id           VARCHAR(128)                       NULL
  COMMENT '微信union_id',
  nick_name          VARCHAR(64)                        NULL,
  gender             INT(3)                             NULL
  COMMENT '0未知,1男,2女',
  city               VARCHAR(64)                        NULL,
  province           VARCHAR(64)                        NULL,
  country            VARCHAR(64)                        NULL,
  avatar_url         VARCHAR(256)                       NULL,
  referer            VARCHAR(64)                        NULL
  COMMENT '用户来源',
  first_login_time   DATETIME                           NULL,
  last_login_time    DATETIME                           NULL,
  first_login_device BIGINT                             NULL,
  last_login_device  BIGINT                             NULL,
  gmt_create         DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  gmt_modify         DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  is_del             TINYINT(1) DEFAULT '0'             NOT NULL,
  creator            VARCHAR(32)                        NOT NULL,
  modifier           VARCHAR(32)                        NOT NULL,
  CONSTRAINT OPENID
  UNIQUE (open_id)
);

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 修改MallOrder表-- -- -- -- -- -- -- -- --
ALTER TABLE mall_order ADD union_id VARCHAR(64) NULL COMMENT '微信unionId';
ALTER TABLE mall_order CHANGE customer_no open_id VARCHAR(64) COMMENT '微信open_id';
-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 修改item表-- -- -- -- -- -- -- -- --
ALTER TABLE `item` ADD COLUMN `origin_sale_price` VARCHAR(64) NULL DEFAULT NULL COMMENT '原始销售价格';
ALTER TABLE `item` ADD COLUMN `commission_mode` VARCHAR(64) NULL DEFAULT NULL COMMENT '佣金比率';


-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 新增share_token_record表-- -- -- -- -- -- -- -- -- 
 CREATE TABLE `share_token_record` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `company_no` varchar(64) DEFAULT NULL COMMENT '公司名称',
  `item_code` varchar(64) DEFAULT NULL COMMENT '商品代码',
  `token` varchar(64) DEFAULT NULL COMMENT '商品分享快照token',
  `seq` bigint(64) DEFAULT NULL COMMENT '分享层级',
  `user_wx` varchar(64) DEFAULT NULL COMMENT '用户微信ID(可能未注册)',
  `user_no` varchar(64) DEFAULT NULL COMMENT '用户ID(注册用户标识)',
  `parent_user_no` varchar(64) DEFAULT NULL COMMENT '分销上级用户名',
  `parent_user_wx` varchar(64) DEFAULT NULL COMMENT '分销上级用户微信id',
  `share_token` varchar(2048) DEFAULT NULL COMMENT '分享分销合成token',
  `is_del` tinyint(1) DEFAULT '0',
  `creator` varchar(32) DEFAULT 'system',
  `modifier` varchar(32) DEFAULT 'system',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modify` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;



-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 修改mall_shopping_cart表-- -- -- -- -- -- -- -- -- =
ALTER TABLE `haidb2new`.`mall_shopping_cart`   
  ADD COLUMN `share_token` VARCHAR(2048) NULL   COMMENT '分享分销token';


-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 修改mall_sub_order表-- -- -- -- -- -- -- -- -- 
ALTER TABLE `haidb2new`.`mall_sub_order`   
  ADD COLUMN `share_user_id` VARCHAR(64) NULL   COMMENT '分享人' ,
  ADD COLUMN `share_token` VARCHAR(2048) NULL   COMMENT '分销token' ,
  ADD COLUMN `share_time` VARCHAR(16) NULL   COMMENT '分享统计维度日期' ,
  ADD COLUMN `share_close_flag` VARCHAR(1) DEFAULT '0'  NULL   COMMENT '分享分销结算标识' ,
  ADD COLUMN `share_close_time` VARCHAR(16) NULL   COMMENT '分享分销结算时间' ,
  ADD COLUMN `share_money` NUMERIC(16,4) NULL   COMMENT '分享获得的佣金';


ALTER TABLE `buyer_task_detail` ADD COLUMN `buyer_task_detail_no` VARCHAR(64) NULL DEFAULT NULL COMMENT '采购明细的编码，和小票明细有关联';


ALTER TABLE `haidb2new`.`buyer_task`
MODIFY COLUMN `buyer_open_id` varchar(64) DEFAULT NULL AFTER `gmt_modify`;


ALTER TABLE `haidb2new`.`buyer_storage`
MODIFY COLUMN `buyer_open_id` varchar(64) DEFAULT 0 COMMENT '买手ID' AFTER `buyer_name`;


##1 buyer_entry_manifest
ALTER TABLE `buyer_entry_manifest`
  ADD COLUMN `company_no`  varchar(64) NULL AFTER `id`;

##2 buyer_receipt
ALTER TABLE `buyer_receipt`
  CHANGE COLUMN `buyer_id` `open_id`  varchar(64) NULL DEFAULT NULL COMMENT '买手ID' AFTER `status`;

ALTER TABLE `haidb2new`.`buyer_receipt`
  MODIFY COLUMN `open_id` varchar(64) DEFAULT NULL COMMENT '买手ID,根据openID，与buyer关联' AFTER `status`;

##3 buyer_storage  最好加上备注： COMMENT '-1关闭，0新建，1已确认入库，2.成功，3入库中'
ALTER TABLE `buyer_storage`
  ADD COLUMN `status`  int(4) NULL AFTER `modifier`,
  MODIFY COLUMN `buyer_open_id` varchar(64) DEFAULT NULL;

##4 buyer_storage_detail
ALTER TABLE `buyer_storage_detail`
  MODIFY COLUMN `item_code`  varchar(64) NULL DEFAULT NULL COMMENT 'itemcode' AFTER `buyer_task_detail_no`,
  ADD COLUMN `status`  int(4) NULL AFTER `modifier`,
  ADD COLUMN `mem`  varchar(1024) NULL AFTER `status`,
  ADD COLUMN `op_user_no`  varchar(64) NULL AFTER `mem`,
  ADD COLUMN `op_time`  datetime NULL AFTER `op_user_no`;

##4 buyer_task_detail
ALTER TABLE `buyer_task_detail`
  MODIFY COLUMN `buyer_open_id`  varchar(64) NULL DEFAULT NULL COMMENT '买手微信ID' AFTER `buyer_name`,
  CHANGE COLUMN `desc` `remark`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明' AFTER `sku_code`,
  ADD COLUMN `company_no`  varchar(64) NULL AFTER `buyer_task_detail_no`;

##5 dealer_type
## existed.

##6 item_find
ALTER TABLE `item_find`
  CHANGE COLUMN `desc` `desc_msg`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品描述信息' AFTER `status`,
  ADD COLUMN `company_no`  varchar(64) NULL AFTER `id`;


ALTER TABLE `haidb2new`.`item_find`
  MODIFY COLUMN `sku_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci AFTER `item_code`;



ALTER TABLE `haidb2new`.`mall_shipping_address` ADD COLUMN company_no VARCHAR(64) NOT NULL COMMENT '商户号';

ALTER TABLE `haidb2new`.`item_find`
  MODIFY COLUMN `sku_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci AFTER `item_code`;




CREATE TABLE `haidb2new`.`db_migrate_receive_record` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `token` varchar(64) DEFAULT NULL,
  `db_script` varchar(4096) DEFAULT NULL,
  `status` varchar(2) DEFAULT '1',
  `gmt_modify` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `modifier` varchar(32) DEFAULT 'sys',
  `creator` varchar(32) DEFAULT 'sys',
  `is_del` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

CREATE TABLE `haidb2new`.`db_migrate_send_record` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `token` varchar(64) DEFAULT NULL,
  `db_script` varchar(4096) DEFAULT NULL,
  `retry_times` int(2) DEFAULT '0',
  `status` varchar(2) DEFAULT '0' COMMENT '0 新增 1 成功 2 失败',
  `gmt_modify` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `modifier` varchar(32) DEFAULT 'sys',
  `creator` varchar(32) DEFAULT 'sys',
  `is_del` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;







