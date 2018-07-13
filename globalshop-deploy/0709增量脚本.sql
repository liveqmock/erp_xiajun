##
update item_sku set status=1 where id>0;

##
ALTER TABLE `item_sku` CHANGE COLUMN `status` `status` INT ( 4 ) NOT NULL DEFAULT '1' COMMENT '0:未审核,1:审核通过';


###批次号
ALTER TABLE `haidb2new`.`buyer_receipt_detail`
ADD COLUMN `batch_num` int(4) COMMENT '批次号';

###批次号
ALTER TABLE `haidb2new`.`buyer_storage_detail`
ADD COLUMN `batch_num` int(4) COMMENT '批次号';

###20180712
ALTER TABLE `haidb2new`.`buyer`
DROP INDEX `OPENID`,
ADD UNIQUE INDEX `OPENID`(`open_id`, `union_id`) USING BTREE;
