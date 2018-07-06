


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

