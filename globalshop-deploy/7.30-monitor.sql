

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

