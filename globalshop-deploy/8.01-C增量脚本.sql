ALTER TABLE applet_config ADD mch_id VARCHAR(64) NULL COMMENT '商户号(微信支付用)';
ALTER TABLE applet_config ADD status VARCHAR(5) NULL COMMENT '支付类型 1.商户版 2.平台版';
ALTER TABLE applet_config ADD pay_key VARCHAR(64) NULL COMMENT '商户版支付秘钥';
ALTER TABLE buyer_task_detail ADD company_no VARCHAR(64) NULL;