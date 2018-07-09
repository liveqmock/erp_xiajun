##
update item_sku set status=1 where id>0;

##
ALTER TABLE `item_sku` CHANGE COLUMN `status` `status` INT ( 4 ) NOT NULL DEFAULT '1' COMMENT '0:未审核,1:审核通过';
