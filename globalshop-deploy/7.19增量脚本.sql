ALTER TABLE applet_config MODIFY secret VARCHAR(64) COMMENT '小程序secret';


ALTER TABLE `haidb2new`.`channel`
ADD COLUMN `sale_level` varchar(45) DEFAULT NULL,
ADD COLUMN `discount` double DEFAULT NULL,
ADD COLUMN `discount1` double DEFAULT NULL,
ADD COLUMN `discount2` double DEFAULT NULL,
ADD COLUMN `discount3` double DEFAULT NULL;

# 增加主图字段
ALTER TABLE `haidb2new`.`item_find`
ADD COLUMN `main_pic` varchar(2048) ;

# 增加字段长度
ALTER TABLE `haidb2new`.`db_migrate_send_record`
MODIFY COLUMN `db_script` varchar(6144) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL ;

ALTER TABLE `haidb2new`.`buyer_task_detail`
CHANGE COLUMN `mode` `mode` TINYINT(2) NULL DEFAULT '1' COMMENT '采购方式 0 线上 1线下' ;


# buyer_task新增任务描述
ALTER TABLE `haidb2new`.`buyer_task`
MODIFY COLUMN `remark` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '任务备注',
ADD COLUMN `task_desc` varchar(255) COMMENT '任务描述',
ADD COLUMN `image_url` varchar(1024) COMMENT '任务主图，item_find主图';

# buyer 唯一性所有增加删除字段
ALTER TABLE `haidb2new`.`buyer`
DROP INDEX `OPENID`,
ADD UNIQUE INDEX `OPENID`(`open_id`, `union_id`, `is_del`) USING BTREE;


