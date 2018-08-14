ALTER TABLE applet_config ADD requestdomain VARCHAR(256) NULL COMMENT 'request合法域名';
ALTER TABLE applet_config ADD wsrequestdomain VARCHAR(256) NULL COMMENT 'socket合法域名';
ALTER TABLE applet_config ADD uploaddomain VARCHAR(256) NULL COMMENT 'uploadFile合法域名';
ALTER TABLE applet_config ADD downloaddomain VARCHAR(256) NULL COMMENT 'downloadFile合法域名';
ALTER TABLE applet_config ADD webviewdomain VARCHAR(256) NULL COMMENT '业务域名';