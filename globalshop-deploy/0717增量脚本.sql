ALTER TABLE inventory_out_manifest CHANGE `desc` remark VARCHAR(256) COMMENT '备注';
-- 一个appid只能绑定给一个公司
CREATE UNIQUE INDEX applet_config_appid_uindex ON applet_config (appid);
-- 每个公司只能绑定一个商城小程序和一个采购小程序
ALTER TABLE applet_config
  ADD UNIQUE KEY(company_no, applet_type);


ALTER TABLE `item_find`
ADD COLUMN `brand_no` VARCHAR(16) NULL COMMENT '品牌编码' ,
ADD COLUMN `brand_name` VARCHAR(64) NULL COMMENT '品牌名字';

# mall_return_order 表添加 mall_return_order_no 字段
ALTER TABLE `mall_return_order`
ADD COLUMN `mall_return_order_no` VARCHAR(64) NOT NULL COMMENT '退单号';

# 入库单新增实际入库数
ALTER TABLE `haidb2new`.`buyer_storage_detail`
ADD COLUMN `entry_quantity` int(11) DEFAULT 0 COMMENT '实际入库数' AFTER `trans_quantity`;

ALTER TABLE `mall_return_order`
ADD COLUMN `company_no` VARCHAR(64) NOT NULL COMMENT '公司编号';