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
