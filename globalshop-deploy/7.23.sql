ALTER TABLE applet_config ADD publish_status   INT(11) NULL COMMENT '小程序发布状态：1.已授权 2.已提交体验版 3.待审核 4.审核通过待发布
5.已发布';
ALTER TABLE applet_config ADD templet_id   INT(11) NULL COMMENT '小程序模板id';
ALTER TABLE applet_config ADD img_url  VARCHAR(64) NULL COMMENT '体验版二维码';
ALTER TABLE applet_config ADD audit_id   VARCHAR(64) NULL COMMENT ' 微信审核的id   用于查询审核状态等api
';
ALTER TABLE applet_config ADD ext_json   VARCHAR(1024) NULL COMMENT '小程序的ext.json文件';