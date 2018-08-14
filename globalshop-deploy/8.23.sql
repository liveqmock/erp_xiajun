ALTER TABLE `haidb2new`.`jd_shop_config`
ADD COLUMN `version` bigint(19) AFTER `id`;

ALTER TABLE `haidb2new`.`channel_listing_item_sku`
DROP INDEX `ITEM_SKU_ID`,
ADD INDEX `ITEM_SKU_ID`(`item_code`) USING BTREE;

CREATE TABLE `id_card`  (
  `id` bigint(19) UNSIGNED NOT NULL AUTO_INCREMENT,
  `gmt_modify` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '操作时间',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT 0,
  `real_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '姓名',
  `id_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '身份证',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name_card_union_index`(`id_number`, `real_name`) USING BTREE COMMENT '姓名和身份证唯一性索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
