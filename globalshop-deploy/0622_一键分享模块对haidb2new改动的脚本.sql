-- 20180622
-- 增加一张表，修改三张表
-- oneshare模块对数据库haidb2new的改动
-- 在133/111/180了上已经运行过了


---- -- -- -- -- -- -- -- -- -- -- -- -- -- 修改item表-- -- -- -- -- -- -- -- -- 
ALTER TABLE `item` ADD COLUMN `origin_sale_price` VARCHAR(64) NULL DEFAULT NULL COMMENT '原始销售价格';
ALTER TABLE `item` ADD COLUMN `commission_mode` VARCHAR(64) NULL DEFAULT NULL COMMENT '佣金比率';


-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 新增share_token_record表-- -- -- -- -- -- -- -- -- 
 CREATE TABLE `share_token_record` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `company_no` varchar(64) DEFAULT NULL COMMENT '公司名称',
  `item_code` varchar(64) DEFAULT NULL COMMENT '商品代码',
  `token` varchar(32) DEFAULT NULL COMMENT '商品分享快照token',
  `seq` bigint(64) DEFAULT NULL COMMENT '分享层级',
  `user_wx` varchar(64) DEFAULT NULL COMMENT '用户微信ID(可能未注册)',
  `user_no` varchar(64) DEFAULT NULL COMMENT '用户ID(注册用户标识)',
  `parent_user_no` varchar(64) DEFAULT NULL COMMENT '分销上级用户名',
  `parent_user_wx` varchar(64) DEFAULT NULL COMMENT '分销上级用户微信id',
  `share_token` varchar(256) DEFAULT NULL COMMENT '分享分销合成token',
  `is_del` tinyint(1) DEFAULT '0',
  `creator` varchar(32) DEFAULT 'system',
  `modifier` varchar(32) DEFAULT 'system',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modify` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 修改mall_shopping_cart表-- -- -- -- -- -- -- -- -- =
ALTER TABLE `haidb2new`.`mall_shopping_cart`   
  ADD COLUMN `share_token` VARCHAR(256) NULL   COMMENT '分享分销token';


-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 修改mall_sub_order表-- -- -- -- -- -- -- -- -- 
ALTER TABLE `haidb2new`.`mall_sub_order`   
  ADD COLUMN `share_user_id` VARCHAR(64) NULL   COMMENT '分享人' ,
  ADD COLUMN `share_token` VARCHAR(256) NULL   COMMENT '分销token' ,
  ADD COLUMN `share_time` VARCHAR(16) NULL   COMMENT '分享统计维度日期' ,
  ADD COLUMN `share_close_flag` VARCHAR(1) DEFAULT '0'  NULL   COMMENT '分享分销结算标识' ,
  ADD COLUMN `share_close_time` VARCHAR(16) NULL   COMMENT '分享分销结算时间' ;

ALTER TABLE `haidb2new`.`mall_sub_order`   
  ADD COLUMN `share_money` NUMERIC(16,4) NULL   COMMENT '分享获得的佣金';

