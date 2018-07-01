##1 buyer_entry_manifest
ALTER TABLE `buyer_entry_manifest`
ADD COLUMN `company_no`  varchar(64) NULL AFTER `id`;

##2 buyer_receipt
ALTER TABLE `buyer_receipt`
CHANGE COLUMN `buyer_id` `open_id`  varchar(64) NULL DEFAULT NULL COMMENT '买手ID' AFTER `status`;

##3 buyer_storage  最好加上备注：COMMENT '-1关闭，0新建，1已确认入库，2.成功，3入库中'
ALTER TABLE `buyer_storage`
ADD COLUMN `status`  int(4) NULL AFTER `modifier`;

##4 buyer_storage_detail
ALTER TABLE `buyer_storage_detail`
MODIFY COLUMN `item_code`  varchar(64) NULL DEFAULT NULL COMMENT 'itemcode' AFTER `buyer_task_detail_no`;
ADD COLUMN `status`  int(4) NULL AFTER `modifier`;
ADD COLUMN `mem`  varchar(1024) NULL AFTER `status`;
ADD COLUMN `op_user_no`  varchar(64) NULL AFTER `mem`;
ADD COLUMN `op_time`  datetime NULL AFTER `op_user_no`;

##4 buyer_task_detail
ALTER TABLE `buyer_task_detail`
MODIFY COLUMN `buyer_open_id`  varchar(64) NULL DEFAULT NULL COMMENT '买手微信ID' AFTER `buyer_name`;
CHANGE COLUMN `desc` `remark`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明' AFTER `sku_code`;
ADD COLUMN `company_no`  varchar(64) NULL AFTER `buyer_task_detail_no`;

##5 dealer_type
ALTER TABLE `dealer_type`
ADD COLUMN `company_no`  varchar(64) NULL AFTER `modifier`;

##6 item_find
ALTER TABLE `item_find`
CHANGE COLUMN `desc` `desc_msg`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品描述信息' AFTER `status`;
ADD COLUMN `company_no`  varchar(64) NULL AFTER `id`;











