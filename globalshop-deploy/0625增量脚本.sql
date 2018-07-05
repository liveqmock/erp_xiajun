
ALTER TABLE `haidb2new`.`mall_shipping_address` ADD COLUMN company_no VARCHAR(64) NOT NULL COMMENT '商户号';

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
  gmt_modify  DATETIME DEFAULT CURRENT_TIMESTAMP NULL,
  mch_id      VARCHAR(64)                        NULL
  COMMENT '商户号',
  status      VARCHAR(5) DEFAULT '1'             NOT NULL
  COMMENT '区别微信支付是平台版还是商户版。1是平台版，2是商户版',
  `key`       VARCHAR(64)                        NULL
  COMMENT '商户版的支付秘钥'
);