-- 20180622
-- 增加一张表，修改三张表
-- oneshare模块对数据库haidb2new的改动
-- 在133/111/180了上已经运行过了

---- -- -- -- -- -- -- -- -- -- -- -- -- -- 新增wx_user表-- -- -- -- -- -- -- -- --
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

---- -- -- -- -- -- -- -- -- -- -- -- -- -- 修改MallOrder表-- -- -- -- -- -- -- -- --
ALTER TABLE mall_order ADD union_id VARCHAR(64) NULL COMMENT '微信unionId';
ALTER TABLE mall_order CHANGE customer_no open_id VARCHAR(64) COMMENT '微信open_id';
---- -- -- -- -- -- -- -- -- -- -- -- -- -- 修改item表-- -- -- -- -- -- -- -- --
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


ALTER TABLE `item` ADD COLUMN `origin_sale_price` VARCHAR(64) NULL DEFAULT NULL COMMENT '原始销售价格';
ALTER TABLE `item` ADD COLUMN `commission_rate` VARCHAR(64) NULL DEFAULT NULL COMMENT '佣金比率';
