ALTER TABLE `haidb2new`.`jd_shop_config`
ADD COLUMN `version` bigint(19) AFTER `id`;

ALTER TABLE `haidb2new`.`channel_listing_item_sku`
DROP INDEX `ITEM_SKU_ID`,
ADD INDEX `ITEM_SKU_ID`(`item_code`) USING BTREE;
