CREATE DATABASE  IF NOT EXISTS `haidb2new` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `haidb2new`;
-- MySQL dump 10.13  Distrib 5.6.17, for osx10.6 (i386)
--
-- Host: 47.98.164.133    Database: haidb2new
-- ------------------------------------------------------
-- Server version	5.7.21-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `item_brand`
--

DROP TABLE IF EXISTS `item_brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_brand` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `brand_no` varchar(11) DEFAULT NULL,
  `name` varchar(64) NOT NULL COMMENT '品牌名称',
  `name_china` varchar(128) DEFAULT NULL COMMENT '中文品牌',
  `name_alias` varchar(64) DEFAULT NULL COMMENT '品牌别名',
  `seq` tinyint(3) DEFAULT '0' COMMENT '排序',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `brand_no` (`brand_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=385 DEFAULT CHARSET=utf8 COMMENT='品牌';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mall_order`
--

DROP TABLE IF EXISTS `mall_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mall_order` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dealer_code` varchar(64) DEFAULT NULL COMMENT '销售人员关联字段',
  `dealer_name` varchar(64) NOT NULL COMMENT '销售人员名称',
  `customer_no` varchar(64) DEFAULT NULL COMMENT '消费者的编号',
  `order_no` varchar(64) NOT NULL COMMENT '订单编号',
  `company_no` varchar(64) NOT NULL COMMENT '公司编号',
  `channel_no` varchar(64) DEFAULT '0' COMMENT '渠道编号',
  `channel_name` varchar(64) DEFAULT '微信小程序' COMMENT '销售名称',
  `channel_order_no` varchar(64) DEFAULT NULL COMMENT '第三方平台订单编号',
  `channel_customer_no` varchar(64) DEFAULT NULL,
  `channel_type` varchar(32) DEFAULT '0',
  `shop_code` varchar(64) NOT NULL COMMENT '公司店铺的编号',
  `wx_pay_trade_no` varchar(64) DEFAULT NULL COMMENT '微信支付商户号',
  `total_amount` double NOT NULL COMMENT '应付金额',
  `actual_amount` double NOT NULL COMMENT '实付金额',
  `pay_type` int(2) NOT NULL COMMENT '支付方式',
  `order_time` datetime NOT NULL COMMENT '下单时间',
  `status` int(2) DEFAULT '0' COMMENT '订单状态',
  `memo` varchar(512) DEFAULT NULL COMMENT '备注',
  `id_card` varchar(32) DEFAULT NULL COMMENT '身份证号号码',
  `idcard_pic_front` varchar(255) DEFAULT NULL COMMENT '身份证正面照',
  `idcard_pic_reverse` varchar(255) DEFAULT NULL COMMENT '身份证反面照',
  `source` bigint(20) DEFAULT NULL COMMENT '用户来源,跟wx_app_launch表关联',
  `freight` double DEFAULT NULL COMMENT '实际邮费，包含在实付金额里面',
  `freight_agent` double DEFAULT NULL COMMENT '实际邮费，代理',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ORDERNO` (`order_no`)
) ENGINE=InnoDB AUTO_INCREMENT=165 DEFAULT CHARSET=utf8 COMMENT='顾客的订单（在微信小程序或者第三方销售平台）';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `channel_listing_item`
--

DROP TABLE IF EXISTS `channel_listing_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `channel_listing_item` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `item_code` varchar(64) NOT NULL COMMENT '商品ID',
  `channel_no` varchar(64) NOT NULL,
  `company_no` varchar(64) NOT NULL,
  `channel_item_code` varchar(64) DEFAULT NULL COMMENT '外部平台商品id',
  `shop_code` varchar(64) NOT NULL,
  `channel_item_alias` varchar(64) DEFAULT NULL COMMENT '外部平台商品别名',
  `status` int(2) DEFAULT '0' COMMENT '//0新档 1上架 2下架 -1删除',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `OUTERID` (`channel_item_code`),
  KEY `ITEMID` (`item_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=utf8 COMMENT='渠道上的商品信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mall_wx_item_snapshot`
--

DROP TABLE IF EXISTS `mall_wx_item_snapshot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mall_wx_item_snapshot` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `company_no` varchar(64) NOT NULL,
  `item_code` varchar(64) NOT NULL,
  `sku_code` varchar(64) NOT NULL,
  `sell_price` double(10,2) NOT NULL,
  `item_name` varchar(128) DEFAULT NULL,
  `item_desc` varchar(255) DEFAULT NULL,
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信小程序商品快照';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jd_shop_oauth`
--

DROP TABLE IF EXISTS `jd_shop_oauth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jd_shop_oauth` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `version` bigint(19) unsigned zerofill DEFAULT '0000000000000000000',
  `channel_no` varchar(64) NOT NULL,
  `company_no` varchar(64) DEFAULT NULL,
  `shop_code` varchar(64) NOT NULL,
  `access_token` varchar(255) DEFAULT NULL,
  `expires_time` datetime DEFAULT NULL,
  `refresh_token` varchar(255) DEFAULT NULL,
  `refresh_exprires_time` datetime DEFAULT NULL,
  `app_key` varchar(64) DEFAULT NULL,
  `appsecret_key` varchar(255) DEFAULT NULL,
  `access_key` varchar(64) DEFAULT NULL,
  `access_value` varchar(255) DEFAULT NULL,
  `server_url` varchar(255) DEFAULT NULL,
  `token_url` varchar(255) DEFAULT NULL,
  `shop_type` varchar(64) DEFAULT NULL,
  `open` tinyint(1) DEFAULT '1',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mall_shopping_cart`
--

DROP TABLE IF EXISTS `mall_shopping_cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mall_shopping_cart` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  `customer_no` varchar(64) DEFAULT NULL,
  `open_id` varchar(64) DEFAULT NULL COMMENT '微信公众号',
  `company_no` varchar(64) NOT NULL,
  `channel_no` varchar(64) NOT NULL DEFAULT '0',
  `shop_code` varchar(64) DEFAULT NULL,
  `sku_name` varchar(64) DEFAULT NULL,
  `sku_code` varchar(64) DEFAULT NULL,
  `item_code` varchar(64) DEFAULT NULL,
  `item_name` varchar(64) DEFAULT NULL COMMENT '商品名称',
  `scale` varchar(64) DEFAULT NULL COMMENT '尺寸',
  `quantity` int(11) DEFAULT NULL COMMENT '该商品在购物车的数量',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '购物车状态',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `OPENID` (`open_id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8 COMMENT='顾客在商城的购物车（仅限微信小程序）';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jd_logistics`
--

DROP TABLE IF EXISTS `jd_logistics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jd_logistics` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `version` bigint(19) DEFAULT '0',
  `channel_no` varchar(64) DEFAULT NULL,
  `shop_code` varchar(64) NOT NULL,
  `channel_order_no` varchar(64) DEFAULT NULL,
  `channel_sub_order_no` varchar(64) DEFAULT NULL,
  `send_status` varchar(64) DEFAULT NULL,
  `logistic_code` varchar(64) DEFAULT NULL,
  `logistic_name` varchar(64) DEFAULT NULL,
  `logistic_no` varchar(64) DEFAULT NULL,
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `buyer_receipt`
--

DROP TABLE IF EXISTS `buyer_receipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `buyer_receipt` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `buyer_task_no` varchar(40) NOT NULL COMMENT 'buyer_task表里面的buyer_task_id',
  `company_no` varchar(64) NOT NULL COMMENT '公司',
  `receipt_no` varchar(64) DEFAULT NULL COMMENT '发票编号',
  `total_price` double(10,2) DEFAULT NULL COMMENT '总价',
  `currency` varchar(32) DEFAULT NULL COMMENT '币种',
  `purchase_storage_no` varchar(64) DEFAULT NULL COMMENT '预入库单ID',
  `status` tinyint(2) DEFAULT NULL COMMENT '小票状态',
  `buyer_id` bigint(19) DEFAULT NULL COMMENT '买手ID',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `receipt_no` (`receipt_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='采购的发票，一个发票可以对应好几种的商品';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `site_msg_read`
--

DROP TABLE IF EXISTS `site_msg_read`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `site_msg_read` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `site_msg_id` bigint(11) NOT NULL,
  `receiver_id` bigint(11) NOT NULL COMMENT '收件人',
  `read_status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '阅读状态，0已读，1未读',
  `sender_id` bigint(11) NOT NULL COMMENT '发件人',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `site_msg_read_id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='用户站内信';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `channel_sale_price`
--

DROP TABLE IF EXISTS `channel_sale_price`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `channel_sale_price` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  `channal_no` varchar(64) NOT NULL,
  `company_no` varchar(64) NOT NULL,
  `shop_code` bigint(20) NOT NULL,
  `sale_price` float(20,2) NOT NULL,
  `sku_code` varchar(64) NOT NULL,
  `item_code` varchar(64) NOT NULL,
  `batch_no` varchar(64) DEFAULT NULL COMMENT '批次号',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='每个公司的每个商品在每个渠道的每个店铺的销售价格';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `buyer_task_detail`
--

DROP TABLE IF EXISTS `buyer_task_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `buyer_task_detail` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  `buyer_task_no` varchar(64) DEFAULT NULL,
  `item_code` varchar(64) DEFAULT NULL,
  `upc` varchar(64) DEFAULT NULL,
  `price` float DEFAULT NULL COMMENT '采购价',
  `max_price` float DEFAULT NULL,
  `count` int(10) DEFAULT NULL COMMENT '商品采购数量',
  `owner_name` varchar(64) DEFAULT NULL COMMENT '分配人名称',
  `owner_no` varchar(64) DEFAULT NULL COMMENT '分配人ID',
  `currency` tinyint(2) DEFAULT NULL COMMENT '0 美元 1人民币',
  `buyer_name` varchar(64) DEFAULT NULL COMMENT '买手名称',
  `buyer_open_id` bigint(19) DEFAULT NULL COMMENT '买手微信ID',
  `status` int(11) DEFAULT '0' COMMENT '-1为已取消，0为待采购，2为采购中，1为采购结束',
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `mode` tinyint(2) DEFAULT NULL COMMENT '采购方式 0 线上 1线下',
  `sku_code` varchar(64) DEFAULT NULL COMMENT 'sku代码',
  `desc` varchar(200) DEFAULT NULL COMMENT '说明',
  `max_count` int(10) DEFAULT NULL COMMENT '最大采购数量',
  `sku_pic_url` varchar(256) DEFAULT NULL COMMENT 'sku图片',
  `entry_count` int(10) DEFAULT '0' COMMENT '已入库数量',
  `upc_pass_reason` varchar(256) DEFAULT NULL COMMENT '小程序扫UPC强制通过理由',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `TASKID` (`buyer_task_no`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='采购任务的详情，一种商品对应一条记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `logistic_category_mapping`
--

DROP TABLE IF EXISTS `logistic_category_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `logistic_category_mapping` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `category_code` varchar(10) NOT NULL,
  `category_name` varchar(64) NOT NULL,
  `logistics_company_code` bigint(19) NOT NULL,
  `logistics_company_name` varchar(64) NOT NULL,
  `logistics_company_category_code` varchar(32) NOT NULL,
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台的类目与转运公司类目的对应';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `site_msg`
--

DROP TABLE IF EXISTS `site_msg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `site_msg` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  `send_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '发送时间',
  `send_type` int(11) DEFAULT '0',
  `category` varchar(10) NOT NULL DEFAULT '‘0’',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='站内信';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `shipping_track_polling_4px`
--

DROP TABLE IF EXISTS `shipping_track_polling_4px`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shipping_track_polling_4px` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `delivery_no` varchar(64) CHARACTER SET utf8mb4 NOT NULL COMMENT '物流单号',
  `shipping_no` varchar(64) CHARACTER SET utf8mb4 NOT NULL COMMENT '商家订单号',
  `is_normal` int(1) DEFAULT NULL COMMENT '包裹是否正常',
  `track_info` varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '轨迹详情',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='四方物流记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `buyer_entry_manifest`
--

DROP TABLE IF EXISTS `buyer_entry_manifest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `buyer_entry_manifest` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `purchase_storage_no` varchar(64) NOT NULL COMMENT '预入库单ID=入库单ID',
  `warehouse_no` varchar(64) NOT NULL COMMENT '仓库ID',
  `warehouse_name` varchar(64) DEFAULT NULL COMMENT '仓库名称',
  `shelf_no` varchar(64) DEFAULT NULL COMMENT '货架号',
  `sku_code` varchar(64) DEFAULT NULL COMMENT 'sku编码',
  `item_code` varchar(64) DEFAULT NULL,
  `currency` varchar(32) DEFAULT NULL COMMENT '货币',
  `purchase_price` double(10,2) DEFAULT NULL COMMENT '采购价',
  `batch_num` varchar(40) DEFAULT NULL COMMENT '批次号',
  `upc` varchar(64) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL COMMENT '入库数量',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `purchase_storage_no` (`purchase_storage_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='采购入库单详情原始备份表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_no` varchar(64) NOT NULL COMMENT '索引唯一',
  `company_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '公司名称',
  `status` int(4) DEFAULT '0' COMMENT '状态 0:正常，1:关闭',
  `shop_name` varchar(45) DEFAULT NULL COMMENT '公司的店铺名称',
  `logo_url` varchar(256) DEFAULT NULL,
  `intro` text COMMENT '介绍',
  `force_idcard` int(2) NOT NULL DEFAULT '1' COMMENT '身份证号，默认需要',
  `tel` varchar(45) NOT NULL,
  `im` varchar(45) NOT NULL COMMENT '及时通讯工具，如微信',
  `service_time` varchar(128) DEFAULT NULL COMMENT '服务时间',
  `force_idcard_upload` int(2) NOT NULL DEFAULT '0' COMMENT '身份证图片，默认不需要',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `company_no` (`company_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='买手公司';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `channel_session`
--

DROP TABLE IF EXISTS `channel_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `channel_session` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `company_no` varchar(64) NOT NULL,
  `channel_no` varchar(64) NOT NULL,
  `shop_code` varchar(64) NOT NULL,
  `access_token` varchar(128) DEFAULT NULL,
  `session` varchar(255) DEFAULT NULL,
  `expire_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '过期时间',
  `expire_period` bigint(64) NOT NULL COMMENT '有效时间',
  `last_index` varchar(128) NOT NULL COMMENT '上次刷新位置',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `shipping_packing_pattern`
--

DROP TABLE IF EXISTS `shipping_packing_pattern`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shipping_packing_pattern` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pattern_no` varchar(16) NOT NULL COMMENT '编号，索引',
  `packaging_scale_no` varchar(16) NOT NULL COMMENT '打包规格',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `name_en` varchar(64) NOT NULL COMMENT '英文名称，用于业务判断',
  `weight` double(20,2) DEFAULT NULL COMMENT '重量',
  `package_level` int(2) DEFAULT NULL COMMENT '包装级别',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `method_no` (`pattern_no`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COMMENT='打包规格';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mall_sub_order`
--

DROP TABLE IF EXISTS `mall_sub_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mall_sub_order` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_no` varchar(64) NOT NULL COMMENT '主订单编号',
  `mall_return_order_no` varchar(64) DEFAULT NULL COMMENT '退单ID',
  `customer_no` varchar(64) DEFAULT NULL COMMENT '买手Id',
  `open_id` varchar(64) DEFAULT NULL COMMENT '微信open_id',
  `company_no` varchar(64) NOT NULL,
  `shop_code` varchar(64) NOT NULL COMMENT '外部平台订单编号',
  `channel_order_no` varchar(64) DEFAULT NULL COMMENT '渠道订单号',
  `order_time` datetime DEFAULT NULL COMMENT '销售时间',
  `item_code` varchar(64) DEFAULT NULL,
  `item_name` varchar(128) DEFAULT NULL COMMENT '商品名称',
  `sku_code` varchar(64) DEFAULT NULL COMMENT 'sku编码',
  `channel_sku_code` varchar(64) DEFAULT NULL COMMENT '第三方sku',
  `upc` varchar(128) DEFAULT NULL COMMENT '商品条形码',
  `scale` varchar(64) DEFAULT NULL COMMENT '尺寸',
  `sku_pic` varchar(512) DEFAULT NULL,
  `logistic_type` int(4) DEFAULT '0',
  `freight` double DEFAULT NULL COMMENT '运费(预估物流费用)',
  `freight_real` double DEFAULT NULL COMMENT '真实物流费用',
  `weight` double(10,2) DEFAULT NULL COMMENT 'sku的重量,单位(磅)',
  `sale_price` double DEFAULT NULL COMMENT '销售价',
  `sale_price_agent` double DEFAULT NULL COMMENT '代理销售价',
  `quantity` int(11) DEFAULT NULL COMMENT '销售数量',
  `status` int(2) DEFAULT '0' COMMENT '订单状态',
  `close_reason` varchar(128) DEFAULT NULL COMMENT '订单关闭原因',
  `warehouse_no` varchar(32) DEFAULT NULL COMMENT '配货仓库ID',
  `stock_status` int(2) DEFAULT '0' COMMENT '备货状态',
  `shipping_order_no` varchar(64) DEFAULT NULL COMMENT '发货单id',
  `shipping_no` varchar(64) DEFAULT NULL COMMENT '包裹号',
  `receiver` varchar(64) NOT NULL COMMENT '收货人姓名',
  `receiver_country` varchar(64) DEFAULT NULL,
  `receiver_state` varchar(64) DEFAULT NULL COMMENT '省',
  `receiver_city` varchar(128) DEFAULT NULL COMMENT '市',
  `receiver_district` varchar(128) DEFAULT NULL COMMENT '区',
  `receiver_address` varchar(100) DEFAULT NULL COMMENT '详细地址',
  `telephone` varchar(32) DEFAULT NULL COMMENT '联系电话',
  `postcode` varchar(18) DEFAULT NULL COMMENT '邮编',
  `id_card` varchar(32) DEFAULT NULL COMMENT '身份证号码',
  `idcard_pic_front` varchar(255) DEFAULT NULL COMMENT '身份证号正面',
  `idcard_pic_reverse` varchar(255) DEFAULT NULL COMMENT '身份证号反面',
  `memo` varchar(256) DEFAULT NULL COMMENT '备注',
  `sub_order_no` varchar(64) DEFAULT NULL COMMENT '子订单号',
  `channel_sub_order_no` varchar(64) DEFAULT NULL COMMENT '外部子订单号',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `OUTERORDERID` (`channel_order_no`)
) ENGINE=InnoDB AUTO_INCREMENT=166 DEFAULT CHARSET=utf8 COMMENT='商城子订单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `inventory_out_manifest`
--

DROP TABLE IF EXISTS `inventory_out_manifest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inventory_out_manifest` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `inventory_out_no` varchar(64) DEFAULT NULL COMMENT '出库单号',
  `company_no` varchar(64) NOT NULL,
  `warehouse_no` varchar(64) DEFAULT NULL COMMENT '仓库ID',
  `warehouse_name` varchar(64) DEFAULT NULL COMMENT '仓库名称',
  `status` int(11) DEFAULT NULL COMMENT '出库单状态',
  `desc` varchar(256) DEFAULT NULL COMMENT '备注',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出库单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `buyer`
--

DROP TABLE IF EXISTS `buyer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `buyer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_no` varchar(64) NOT NULL COMMENT '所属公司id',
  `open_id` varchar(64) DEFAULT NULL COMMENT '微信open_id',
  `union_id` varchar(128) DEFAULT NULL COMMENT '微信union_id',
  `nick_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `head_protrait_url` varchar(256) DEFAULT NULL COMMENT '头像',
  `gender` int(3) DEFAULT NULL COMMENT '0未知,1男,2女',
  `city` varchar(64) DEFAULT NULL,
  `province` varchar(64) DEFAULT NULL,
  `country` varchar(64) DEFAULT NULL,
  `referer` bigint(20) DEFAULT NULL COMMENT '用户来源,跟mall_user_track表关联',
  `purchase_commission_mode` bigint(18) DEFAULT '0' COMMENT '采购佣金模式',
  `purchase_commission_str` varchar(45) DEFAULT '' COMMENT '采购佣金表达式',
  `first_login_time` datetime DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `OPENID` (`open_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='买手';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mall_wx_banner`
--

DROP TABLE IF EXISTS `mall_wx_banner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mall_wx_banner` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company_no` varchar(64) NOT NULL,
  `type` varchar(45) DEFAULT NULL,
  `picture_url` varchar(512) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `url` varchar(256) DEFAULT NULL,
  `item_code` varchar(64) DEFAULT NULL,
  `special_page_no` varchar(64) DEFAULT NULL,
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `status_UNIQUE` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COMMENT='商城的banner';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `shipping_order`
--

DROP TABLE IF EXISTS `shipping_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shipping_order` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `shipping_no` varchar(64) DEFAULT NULL COMMENT '发货单号(包裹号),erp系统自动生产',
  `logistic_no` varchar(64) DEFAULT NULL COMMENT '物流运单号,外部物流公司的运单号',
  `logistic_company` varchar(128) DEFAULT NULL COMMENT '外部物流公司名称',
  `type` int(4) DEFAULT NULL COMMENT '快递渠道：包税线，身份证线，BC线',
  `status` int(2) DEFAULT NULL COMMENT '运单状态',
  `transfer_status` int(4) NOT NULL DEFAULT '0' COMMENT '转运状态。0: 未预报；1: 预报失败；10：预报成功；20：创建转运单成功',
  `sync_send_status` int(4) DEFAULT '0' COMMENT '是否同步到第三方渠道。0: 未同步；1: 已同步',
  `tpl_pkg_status` varchar(64) DEFAULT NULL COMMENT '第三方物流的包裹入库状态',
  `freight` double DEFAULT NULL COMMENT '实际运费',
  `sku_weight` double(10,2) DEFAULT NULL COMMENT '包裹里面的商品总重量',
  `mall_orders` varchar(2048) DEFAULT NULL COMMENT 'ERP订单编号多个用,分割',
  `shipping_printer` varchar(64) DEFAULT NULL COMMENT 'PDF打印者',
  `company_no` varchar(64) NOT NULL COMMENT '公司',
  `gls_return_back` varchar(2048) DEFAULT NULL COMMENT 'gls返回xml完整保留',
  `receiver` varchar(64) DEFAULT NULL COMMENT '收件人',
  `receiver_state` varchar(64) DEFAULT NULL COMMENT '省',
  `receiver_city` varchar(128) DEFAULT NULL COMMENT '市',
  `receiver_district` varchar(128) DEFAULT NULL COMMENT '区',
  `address` varchar(500) DEFAULT NULL COMMENT '详细地址',
  `telephone` varchar(32) DEFAULT NULL COMMENT '联系电话',
  `postcode` varchar(18) DEFAULT NULL COMMENT '邮编',
  `memo` varchar(256) DEFAULT NULL COMMENT '备注',
  `id_card` varchar(32) DEFAULT NULL COMMENT '身份证号码',
  `id_card_back` varchar(256) DEFAULT NULL COMMENT '身份证背面url',
  `id_card_front` varchar(256) DEFAULT NULL COMMENT '身份证正面url',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `SHIPPINGNO` (`shipping_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物流订单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `item_category`
--

DROP TABLE IF EXISTS `item_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_category` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category_code` varchar(10) NOT NULL DEFAULT '0' COMMENT '类目的编码，一级类目(3位)+二级类目(3位)+三级类目(4位)',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `p_code` varchar(10) DEFAULT NULL COMMENT '父级id',
  `root_code` varchar(10) DEFAULT NULL COMMENT '一级类目',
  `seq` int(3) DEFAULT '0' COMMENT '排序',
  `level` int(2) NOT NULL DEFAULT '0' COMMENT '级别',
  `status` int(2) NOT NULL DEFAULT '0' COMMENT '状态',
  `all_path` varchar(128) DEFAULT NULL COMMENT '类目名称全路径',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `category_code` (`category_code`)
) ENGINE=InnoDB AUTO_INCREMENT=419 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='平台的商品类目';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mall_return_order`
--

DROP TABLE IF EXISTS `mall_return_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mall_return_order` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `order_no` varchar(64) DEFAULT NULL COMMENT '主订单号',
  `outer_order_no` bigint(20) DEFAULT NULL,
  `sub_order_no` varchar(64) DEFAULT NULL COMMENT '子订单号',
  `status` tinyint(4) DEFAULT NULL COMMENT '退单状态',
  `return_reason` varchar(128) DEFAULT NULL COMMENT '退货原因分类',
  `return_reason_detail` varchar(512) DEFAULT NULL COMMENT '退货原因明细',
  `return_quantity` int(11) DEFAULT '0' COMMENT '退货数量',
  `return_price` double DEFAULT '0' COMMENT '退款金额',
  `is_civil` tinyint(4) DEFAULT NULL COMMENT '是否国内退货',
  `is_checkin` tinyint(4) DEFAULT NULL COMMENT '是否入库',
  `customer_open_id` varchar(64) DEFAULT NULL COMMENT '小程序消费者微信open_id',
  `telephone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `receive_time` datetime DEFAULT NULL COMMENT '收货时间',
  `return_pay_time` datetime DEFAULT NULL COMMENT '退款时间',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `return_refer` int(11) DEFAULT NULL COMMENT '退款来源（0:erp创建；1：小程序）',
  `proof_img` varchar(2048) DEFAULT NULL COMMENT '退款上传图片凭证',
  `return_type` int(11) DEFAULT NULL COMMENT '退款形式（0直接退款;1既退货又退款）',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=133 DEFAULT CHARSET=utf8 COMMENT='顾客在商城的退货单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `auth_role`
--

DROP TABLE IF EXISTS `auth_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_role` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_id` bigint(19) NOT NULL,
  `company_no` varchar(64) NOT NULL,
  `name` varchar(64) NOT NULL COMMENT '角色名',
  `seq` tinyint(2) DEFAULT '0' COMMENT '排序号',
  `description` varchar(255) DEFAULT NULL COMMENT '简介',
  `status` tinyint(2) DEFAULT '0' COMMENT '状态',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_id` (`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COMMENT='角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `inventory_booking_record`
--

DROP TABLE IF EXISTS `inventory_booking_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inventory_booking_record` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `company_no` varchar(64) NOT NULL,
  `order_no` varchar(64) DEFAULT NULL COMMENT '订单id',
  `sub_order_no` varchar(64) NOT NULL COMMENT '子订单ID',
  `item_code` varchar(64) DEFAULT NULL COMMENT '商品id',
  `sku_code` varchar(64) DEFAULT NULL COMMENT 'sku码',
  `quantity` bigint(11) DEFAULT '0' COMMENT '订单购买量',
  `booked_quantity` bigint(11) DEFAULT NULL COMMENT '预定量',
  `inventory` bigint(11) DEFAULT '0' COMMENT '实际库存',
  `inventory_type` varchar(64) DEFAULT NULL COMMENT '库存类型',
  `operator_type` varchar(64) DEFAULT NULL COMMENT '库存操作类型',
  `inventory_on_warehouse_no` varchar(16) DEFAULT NULL COMMENT '仓库库存id',
  `warehouse_no` varchar(64) DEFAULT NULL COMMENT '仓库ID',
  `position_no` varchar(64) DEFAULT NULL COMMENT '货架编号',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='库存备货记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `item_sku`
--

DROP TABLE IF EXISTS `item_sku`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_sku` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sku_code` varchar(64) NOT NULL DEFAULT '0' COMMENT 'sku编码',
  `item_code` varchar(64) NOT NULL DEFAULT '0' COMMENT '商品编码',
  `item_name` varchar(128) NOT NULL COMMENT '名称',
  `company_no` varchar(64) NOT NULL COMMENT '所属公司id',
  `category_code` varchar(10) DEFAULT NULL COMMENT '所属类目编码',
  `category_name` varchar(64) DEFAULT NULL COMMENT '所属类目名字',
  `upc` varchar(64) NOT NULL COMMENT '商品条码',
  `saleable` tinyint(2) DEFAULT '0' COMMENT '是否可售',
  `purchase_price` double(10,2) DEFAULT NULL COMMENT '采购价',
  `freight` bigint(20) DEFAULT NULL COMMENT '运费',
  `discount` double(10,2) DEFAULT NULL COMMENT '折扣率',
  `remark` text COMMENT '备注',
  `logistic_type` tinyint(4) DEFAULT '0' COMMENT 'sku物流方式：0,直邮;1,拼邮',
  `cost_price` double(10,2) DEFAULT NULL COMMENT '原价',
  `brand_name` varchar(64) DEFAULT NULL COMMENT '品牌名字',
  `weight` double(10,2) DEFAULT NULL COMMENT '商品重量',
  `sku_pic` varchar(512) DEFAULT NULL COMMENT 'sku图片',
  `package_code` varchar(64) DEFAULT NULL COMMENT '包装ID（运费计算）',
  `package_name` varchar(64) DEFAULT NULL COMMENT '包装名称，运费计算',
  `package_en` varchar(64) DEFAULT NULL COMMENT '规格英文,运费计算',
  `package_weight` double(10,2) DEFAULT NULL COMMENT '包装重量，运费计算，单位g',
  `package_level_id` varchar(64) DEFAULT NULL COMMENT 'shipping_packing_pattern_no的索引',
  `scale` varchar(64) DEFAULT NULL COMMENT '尺寸',
  `model` varchar(45) DEFAULT NULL COMMENT '商品型号',
  `status` int(4) DEFAULT NULL,
  `sale_type` tinyint(1) DEFAULT NULL COMMENT '销售类型:现货,代购',
  `sale_price` double(10,2) DEFAULT NULL COMMENT '销售价',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `SKUCODE` (`sku_code`),
  KEY `ITEMCODE` (`item_code`),
  KEY `GMTCREATE` (`gmt_create`)
) ENGINE=InnoDB AUTO_INCREMENT=186 DEFAULT CHARSET=utf8 COMMENT='商品sku';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `shipping_track`
--

DROP TABLE IF EXISTS `shipping_track`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shipping_track` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `shipping_order_no` varchar(64) NOT NULL COMMENT '包裹号',
  `waybill_no` varchar(64) NOT NULL COMMENT '主单号',
  `logistic_no` varchar(64) DEFAULT NULL COMMENT '第三方物流公司运单号',
  `logistics_status` int(2) DEFAULT NULL COMMENT '轨迹状态',
  `weight` double DEFAULT NULL COMMENT '转运包裹重量单位kg',
  `volume` varchar(64) DEFAULT NULL COMMENT ' 体积尺寸单位mm',
  `totalfee` double DEFAULT NULL COMMENT '费用 单位 人民币',
  `receive_time` datetime DEFAULT NULL COMMENT '接收时间',
  `oversea_in_time` datetime DEFAULT NULL COMMENT '海外入库时间',
  `oversea_out_time` datetime DEFAULT NULL COMMENT '海外出库(发往国内)时间',
  `oversea_on_transfer_time` datetime DEFAULT NULL COMMENT '上航班时间',
  `inland_in_time` datetime DEFAULT NULL COMMENT '抵达国内时间',
  `inland_out_time` datetime DEFAULT NULL COMMENT ' 国内出库时间(转国内快递)',
  `inland_express_company_code` varchar(64) DEFAULT NULL COMMENT '国内快递公司编码 字符串 20位',
  `inland_express_num` varchar(64) DEFAULT NULL COMMENT '国内快递编号 字符串 40位',
  `buyer_sign_time` datetime DEFAULT NULL COMMENT '用户签收时间',
  `air_take_off` varchar(64) DEFAULT NULL COMMENT '起飞地',
  `airlines` varchar(64) DEFAULT NULL COMMENT '航空公司名称',
  `flight` varchar(64) DEFAULT NULL COMMENT '航班号',
  `track_info` varchar(3072) DEFAULT NULL COMMENT '物流轨迹说明',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `SHIPPINGNO` (`shipping_order_no`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jd_item_operate`
--

DROP TABLE IF EXISTS `jd_item_operate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jd_item_operate` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `channel_no` varchar(64) DEFAULT NULL,
  `shop_code` varchar(64) NOT NULL,
  `item_code` varchar(64) NOT NULL,
  `sync_status` varchar(64) DEFAULT NULL,
  `send_status` varchar(64) DEFAULT NULL,
  `operate_type` varchar(64) DEFAULT NULL,
  `error_massge` varchar(1024) DEFAULT NULL,
  `item_json` mediumtext,
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `item_code` varchar(64) NOT NULL DEFAULT '0' COMMENT '商品编码',
  `company_no` varchar(64) NOT NULL COMMENT '所属公司编号',
  `category_code` varchar(10) NOT NULL COMMENT '所属类目code',
  `qr_code_url` varchar(2000) DEFAULT NULL COMMENT '商品在小程序中的二维码',
  `remark` text COMMENT '商品描述信息',
  `video` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '商品展示视频',
  `subtitle` varchar(45) DEFAULT '',
  `item_name` varchar(128) NOT NULL COMMENT '商品名称',
  `en_name` varchar(128) DEFAULT NULL COMMENT '英文名称',
  `item_short` varchar(64) DEFAULT NULL COMMENT '商品简称',
  `category_name` varchar(64) DEFAULT '0' COMMENT '类目名称',
  `sale_type` tinyint(3) DEFAULT '0' COMMENT '销售类型',
  `main_pic` varchar(2000) DEFAULT NULL COMMENT '商品主图',
  `brand_no` varchar(16) DEFAULT NULL COMMENT '品牌编号',
  `brand_name` varchar(64) DEFAULT NULL COMMENT '品牌名字',
  `country` varchar(10) DEFAULT NULL COMMENT '国家',
  `currency` tinyint(3) DEFAULT NULL COMMENT '币种',
  `origin` varchar(64) DEFAULT NULL COMMENT '产地',
  `freight` double DEFAULT NULL COMMENT '运费,对外展示用',
  `weight` double DEFAULT NULL COMMENT '重量',
  `logistic_type` tinyint(4) DEFAULT '0' COMMENT 'sku物流方式：0,直邮;1,拼邮',
  `price_range` varchar(64) DEFAULT NULL COMMENT '商品价格区间',
  `unit` varchar(64) DEFAULT NULL COMMENT '包装单位',
  `source` varchar(64) DEFAULT NULL COMMENT '商品来源',
  `promotion` varchar(64) DEFAULT NULL COMMENT '促销',
  `id_card` tinyint(2) DEFAULT '0' COMMENT '是否身份证',
  `start_date` datetime DEFAULT NULL COMMENT '销售开始日期',
  `end_date` datetime DEFAULT NULL COMMENT '销售结束日期',
  `booking_date` datetime DEFAULT NULL COMMENT '预售时间',
  `is_sale` tinyint(4) DEFAULT '0' COMMENT '是否可售，1可售0不可销售',
  `sale_on_youzan` int(4) DEFAULT NULL COMMENT '有赞，1可售，0不可售）',
  `third_sale` int(4) DEFAULT NULL COMMENT '是否第三方可售（海狐，1可售，0不可售）',
  `wxis_sale` tinyint(4) DEFAULT '1' COMMENT '小程序是否可售 (1:小程序可售 0:小程序不可售)',
  `is_find` tinyint(4) DEFAULT '0' COMMENT '是否为小程序发现，0否 1是',
  `status` int(4) DEFAULT '0' COMMENT '//0新档 1上架 2下架 -1删除',
  `spec` varchar(64) DEFAULT NULL COMMENT '规格',
  `model` varchar(64) DEFAULT NULL COMMENT '型号',
  `detail` text COMMENT '商品详情',
  `buyer_open_id` varchar(128) DEFAULT NULL COMMENT '买手open_id，可以有多个',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ITEMCODE` (`item_code`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8 COMMENT='商品';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jd_shop_config`
--

DROP TABLE IF EXISTS `jd_shop_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jd_shop_config` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `channel_no` varchar(64) DEFAULT NULL,
  `shop_code` varchar(64) NOT NULL,
  `config_key` varchar(64) DEFAULT NULL,
  `config_value` varchar(64) DEFAULT NULL,
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `channel`
--

DROP TABLE IF EXISTS `channel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `channel` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `channel_no` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `contact_mobile` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `contact_name` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `contact_email` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `contact_url` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `remark` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `channel_no` (`channel_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='渠道';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `item_sku_scale`
--

DROP TABLE IF EXISTS `item_sku_scale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_sku_scale` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  `item_code` varchar(64) NOT NULL,
  `sku_code` varchar(64) NOT NULL,
  `scale_code` varchar(32) NOT NULL,
  `scale_name` varchar(16) NOT NULL,
  `scale_value` varchar(64) NOT NULL COMMENT '规格的值',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='商品对应的规格';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mall_wx_pay_bill`
--

DROP TABLE IF EXISTS `mall_wx_pay_bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mall_wx_pay_bill` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  `company_no` varchar(64) NOT NULL,
  `channel_no` varchar(64) DEFAULT NULL,
  `shop_code` varchar(64) DEFAULT NULL,
  `wx_pay_trade_no` varchar(64) DEFAULT NULL COMMENT '微信支付流水号',
  `pay_type` varchar(10) NOT NULL DEFAULT '微信支付',
  `order_info` varchar(2048) DEFAULT NULL COMMENT '订单详情',
  `status` int(2) DEFAULT '0' COMMENT '状态， 0待确认，1成功，-1无效',
  `customer_no` varchar(64) NOT NULL,
  `wx_open_id` varchar(64) DEFAULT NULL COMMENT '微信openId',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `WXPAYTRADENO` (`wx_pay_trade_no`),
  KEY `OPENID` (`wx_open_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='顾客在微信小程序商城的付款单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mall_customer`
--

DROP TABLE IF EXISTS `mall_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mall_customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customer_no` varchar(64) NOT NULL,
  `open_id` varchar(64) DEFAULT NULL COMMENT '微信open_id',
  `union_id` varchar(128) DEFAULT NULL COMMENT '微信union_id',
  `nick_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `gender` int(3) DEFAULT NULL COMMENT '0未知,1男,2女',
  `city` varchar(64) DEFAULT NULL,
  `province` varchar(64) DEFAULT NULL,
  `country` varchar(64) DEFAULT NULL,
  `portrait_url` varchar(256) DEFAULT NULL COMMENT '头像图片地址',
  `refer` bigint(20) DEFAULT NULL COMMENT '用户来源,跟mall_user_track表关联',
  `role` int(11) DEFAULT '0' COMMENT '用户角色',
  `first_login_time` datetime DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `first_login_device` bigint(20) DEFAULT NULL,
  `last_login_device` bigint(20) DEFAULT NULL,
  `company_no` varchar(64) NOT NULL,
  `channel_no` varchar(64) NOT NULL,
  `shop_code` varchar(64) NOT NULL,
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `OPENID` (`customer_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='商城的顾客（包括微信小程序和第三方销售平台）';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `auth_role_resource`
--

DROP TABLE IF EXISTS `auth_role_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_role_resource` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `company_no` varchar(64) NOT NULL,
  `role_id` bigint(19) NOT NULL COMMENT '角色id',
  `resource_id` bigint(19) NOT NULL COMMENT '资源id',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_id` (`role_id`,`resource_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2742 DEFAULT CHARSET=utf8 COMMENT='角色资源';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sequence`
--

DROP TABLE IF EXISTS `sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sequence` (
  `name` varchar(50) NOT NULL,
  `current_value` int(11) NOT NULL,
  `increment` int(11) NOT NULL DEFAULT '1',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jd_item`
--

DROP TABLE IF EXISTS `jd_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jd_item` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `channel_no` varchar(64) DEFAULT NULL,
  `shop_code` varchar(64) NOT NULL,
  `item_json` mediumtext,
  `send_status` varchar(64) DEFAULT NULL,
  `error_massge` varchar(1024) DEFAULT NULL,
  `item_modify_time` datetime DEFAULT NULL,
  `channel_item_code` varchar(64) DEFAULT NULL,
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jd_order`
--

DROP TABLE IF EXISTS `jd_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jd_order` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `modifier` varchar(64) DEFAULT '-1',
  `channel_no` varchar(64) DEFAULT NULL,
  `shop_code` varchar(64) NOT NULL,
  `order_json` mediumtext,
  `send_status` varchar(64) DEFAULT NULL,
  `order_modify_time` datetime DEFAULT NULL,
  `error_massge` varchar(1024) DEFAULT NULL,
  `channel_order_no` varchar(64) DEFAULT NULL,
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `react_version`
--

DROP TABLE IF EXISTS `react_version`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `react_version` (
  `id` bigint(20) NOT NULL,
  `version` bigint(20) DEFAULT NULL,
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='react版本信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dealer`
--

DROP TABLE IF EXISTS `dealer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dealer` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `company_no` varchar(64) NOT NULL COMMENT '所属公司id',
  `code` varchar(64) DEFAULT NULL COMMENT '经销商代码',
  `name` varchar(64) DEFAULT NULL COMMENT '登入用户ID',
  `type_code` varchar(64) NOT NULL COMMENT '经销商类型代码',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `CODE` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 COMMENT='经销商';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `scale`
--

DROP TABLE IF EXISTS `scale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scale` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) NOT NULL COMMENT '尺寸名称',
  `type_id` varchar(64) NOT NULL COMMENT 'type名称',
  `seq` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=450 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='尺寸';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `inventory_inout`
--

DROP TABLE IF EXISTS `inventory_inout`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inventory_inout` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `company_no` varchar(64) NOT NULL,
  `item_code` varchar(64) DEFAULT NULL COMMENT '商品id',
  `sku_code` varchar(64) NOT NULL COMMENT 'sku码',
  `inventory_on_warehouse_no` varchar(64) NOT NULL COMMENT '仓库库存id',
  `warehouse_no` varchar(64) DEFAULT NULL COMMENT '仓库ID',
  `shelf_no` varchar(64) DEFAULT NULL COMMENT '货架编号',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `quantity` bigint(11) DEFAULT '0' COMMENT '出入库数量',
  `operator_type` int(5) DEFAULT NULL COMMENT '库存操作类型',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=137 DEFAULT CHARSET=utf8 COMMENT='库存出入库流水';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mall_wx_customer_track`
--

DROP TABLE IF EXISTS `mall_wx_customer_track`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mall_wx_customer_track` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_no` varchar(64) NOT NULL,
  `path` varchar(128) DEFAULT NULL COMMENT '小程序路径',
  `query` varchar(128) DEFAULT NULL COMMENT '小程序参数',
  `scene` varchar(64) DEFAULT NULL COMMENT '场景值',
  `share_open_id` varchar(64) DEFAULT NULL COMMENT '分享者openID',
  `owner_open_id` varchar(64) DEFAULT NULL COMMENT '自己的openId',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1093 DEFAULT CHARSET=utf8 COMMENT='商城顾客的分享情况';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `channel_listing_item_sku`
--

DROP TABLE IF EXISTS `channel_listing_item_sku`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `channel_listing_item_sku` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `item_code` varchar(64) NOT NULL COMMENT '商品ID',
  `sku_code` varchar(64) DEFAULT NULL COMMENT 'sku编码',
  `platform_type` int(2) NOT NULL COMMENT '外部平台类型',
  `channel_item_sku_code` varchar(64) DEFAULT NULL COMMENT '外部平台SKU id，例如有赞SKU id',
  `channel_item_code` varchar(64) DEFAULT NULL COMMENT 'outer_item表里面的主键',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ITEM_SKU_ID` (`item_code`),
  KEY `SKUCODE` (`sku_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='渠道商品的SKU信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inventory` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `company_no` varchar(64) NOT NULL,
  `item_code` varchar(64) DEFAULT NULL COMMENT '商品id',
  `item_name` varchar(128) DEFAULT NULL COMMENT '商品名称',
  `upc` varchar(64) DEFAULT NULL COMMENT '条码',
  `sku_code` varchar(64) DEFAULT NULL COMMENT 'sku码',
  `virtual_inv` bigint(19) DEFAULT '0' COMMENT '虚拟库存',
  `locked_virtual_inv` bigint(19) DEFAULT '0' COMMENT '虚拟占用库存(仅用来同步第三方平台可售库存时使用)',
  `inv` bigint(19) DEFAULT '0' COMMENT '现货库存',
  `locked_inv` bigint(19) DEFAULT '0' COMMENT '现货占用库存',
  `trans_inv` bigint(11) DEFAULT '0' COMMENT '在途库存',
  `locked_trans_inv` bigint(19) DEFAULT '0' COMMENT '在途占用库存',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `SKUCODE` (`sku_code`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='总的库存记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `shipping_track_polling_yuntong`
--

DROP TABLE IF EXISTS `shipping_track_polling_yuntong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shipping_track_polling_yuntong` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `waybill_no` varchar(64) DEFAULT NULL COMMENT '运单号',
  `inland_trans_code` varchar(64) DEFAULT NULL COMMENT '国内快递运单号',
  `inland_trans_company_name` varchar(64) DEFAULT NULL COMMENT '国内快递公司',
  `current_status` varchar(64) DEFAULT NULL COMMENT '状态',
  `status_time` datetime DEFAULT NULL COMMENT '状态时间',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `CODESTATUS` (`waybill_no`,`current_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `warehouse`
--

DROP TABLE IF EXISTS `warehouse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `warehouse` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `warehouse_no` varchar(64) NOT NULL,
  `name` varchar(64) DEFAULT NULL COMMENT '仓库名称',
  `company_no` varchar(64) NOT NULL COMMENT '公司编号',
  `delivery_priority` int(4) DEFAULT '0' COMMENT '发货优先级,值越大越优先',
  `address` varchar(64) DEFAULT NULL,
  `tel` varchar(32) DEFAULT NULL,
  `contact_person` varchar(32) NOT NULL,
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `warehouse_no` (`warehouse_no`)
) ENGINE=InnoDB AUTO_INCREMENT=139 DEFAULT CHARSET=utf8 COMMENT='仓库';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mall_wx_special_page`
--

DROP TABLE IF EXISTS `mall_wx_special_page`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mall_wx_special_page` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `special_page_id` int(11) NOT NULL,
  `company_no` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `item_ids` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  `picture` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  `intro` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`special_page_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='商城的活动页面信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `buyer_storage_detail`
--

DROP TABLE IF EXISTS `buyer_storage_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `buyer_storage_detail` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `storage_no` varchar(64) NOT NULL COMMENT '入库单ID',
  `warehouse_no` varchar(64) DEFAULT NULL COMMENT '仓库ID',
  `warehouse_name` varchar(64) DEFAULT NULL COMMENT '仓库名称',
  `shelf_no` varchar(64) DEFAULT NULL COMMENT '货架号',
  `sku_code` varchar(64) DEFAULT NULL COMMENT 'sku编码',
  `price` double(10,2) DEFAULT NULL COMMENT '销售价',
  `total_price` double(10,2) DEFAULT NULL COMMENT '总价',
  `currency` tinyint(3) DEFAULT NULL COMMENT '币种',
  `upc` varchar(64) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL COMMENT '入库数量',
  `trans_quantity` int(11) DEFAULT NULL COMMENT '在途库存',
  `purchase_storage_no` varchar(64) DEFAULT NULL COMMENT '采购入库单号',
  `buyer_task_detail_no` varchar(64) DEFAULT NULL COMMENT '采购子任务号',
  `item_code` bigint(19) DEFAULT NULL COMMENT '入库单号',
  `sku_buysite` varchar(64) DEFAULT NULL COMMENT '订购站点',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='买手采购入库详情表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `logistic_company`
--

DROP TABLE IF EXISTS `logistic_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `logistic_company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `code` varchar(32) DEFAULT NULL,
  `name_in_jd` varchar(32) DEFAULT NULL,
  `code_in_jd` varchar(32) DEFAULT NULL COMMENT '物流公司在京东的编码',
  `name_in_youzan` varchar(32) DEFAULT NULL,
  `code_in_youzan` varchar(32) DEFAULT NULL,
  `name_in_kuaidi100` varchar(32) DEFAULT NULL,
  `code_in_kuaidi100` varchar(32) NOT NULL,
  `name_in_pdd` varchar(32) DEFAULT NULL,
  `code_in_pdd` varchar(32) DEFAULT NULL,
  `name_in_taobao` varchar(32) DEFAULT NULL,
  `code_in_taobao` varchar(32) DEFAULT NULL,
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='转运公司';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `item_comment`
--

DROP TABLE IF EXISTS `item_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `score` int(1) NOT NULL DEFAULT '5',
  `company_no` varchar(64) NOT NULL,
  `shop_code` varchar(64) NOT NULL,
  `channel_no` varchar(64) NOT NULL,
  `mall_order_no` bigint(45) DEFAULT NULL,
  `customer_no` varchar(64) NOT NULL,
  `item_code` varchar(64) NOT NULL,
  `sku_code` varchar(64) NOT NULL,
  `comment_pic` varchar(1024) CHARACTER SET utf8 DEFAULT NULL COMMENT '评价的图片',
  `content` varchar(500) CHARACTER SET utf8 NOT NULL COMMENT '评价内容',
  `status` tinyint(2) DEFAULT NULL,
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='对商品的评价';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `auth_user_role`
--

DROP TABLE IF EXISTS `auth_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_user_role` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(19) NOT NULL COMMENT '用户id',
  `role_id` bigint(19) NOT NULL COMMENT '角色id',
  `company_no` varchar(64) NOT NULL,
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`,`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=538 DEFAULT CHARSET=utf8 COMMENT='用户角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `inventory_out_manifest_detail`
--

DROP TABLE IF EXISTS `inventory_out_manifest_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inventory_out_manifest_detail` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `inventory_out_no` varchar(64) DEFAULT NULL COMMENT '父id',
  `item_code` varchar(64) DEFAULT NULL COMMENT 'item ID',
  `quantity` bigint(19) NOT NULL COMMENT '数量',
  `item_name` varchar(128) DEFAULT NULL COMMENT '商品名称',
  `scale` varchar(64) DEFAULT NULL COMMENT '规格',
  `upc` varchar(64) DEFAULT NULL COMMENT '商品条码',
  `sku_code` varchar(64) DEFAULT NULL COMMENT 'SKU CODE',
  `sku_pic` varchar(500) DEFAULT NULL COMMENT 'SKU 图片',
  `shelf_no` varchar(64) DEFAULT NULL COMMENT '货架号',
  `company_no` varchar(64) NOT NULL,
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出库单详情';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `auth_organization`
--

DROP TABLE IF EXISTS `auth_organization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_organization` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `company_no` varchar(64) NOT NULL DEFAULT '0',
  `org_id` varchar(10) NOT NULL,
  `name` varchar(64) NOT NULL COMMENT '组织名',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `code` varchar(64) NOT NULL COMMENT '编号',
  `icon` varchar(32) DEFAULT NULL COMMENT '图标',
  `pid` bigint(19) DEFAULT NULL COMMENT '父级主键',
  `seq` int(2) NOT NULL DEFAULT '0' COMMENT '排序',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `org_id` (`org_id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8 COMMENT='组织机构';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `inventory_on_warehouse`
--

DROP TABLE IF EXISTS `inventory_on_warehouse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inventory_on_warehouse` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `inventory_on_warehouse_no` varchar(64) NOT NULL,
  `company_no` varchar(64) NOT NULL,
  `item_code` varchar(64) DEFAULT NULL COMMENT '商品id',
  `item_name` varchar(128) DEFAULT NULL COMMENT '商品名称',
  `upc` varchar(64) DEFAULT NULL COMMENT '条码',
  `scale` varchar(64) DEFAULT NULL COMMENT '规格',
  `sku_code` varchar(64) DEFAULT NULL COMMENT 'sku码',
  `sku_pic` varchar(500) DEFAULT NULL COMMENT 'sku图片信息',
  `inventory` bigint(11) DEFAULT '0' COMMENT '现货库存',
  `locked_inv` bigint(11) DEFAULT '0' COMMENT '现货占用库存',
  `trans_inv` bigint(11) DEFAULT '0' COMMENT '在途库存',
  `locked_trans_inv` bigint(19) DEFAULT '0' COMMENT '在途占用库存',
  `warehouse_no` varchar(64) DEFAULT NULL COMMENT '仓库ID',
  `warehouse_name` varchar(64) DEFAULT NULL COMMENT '仓库名称',
  `shelf_no` varchar(64) DEFAULT NULL COMMENT '货架编号',
  `batch_no` varchar(64) NOT NULL COMMENT '批次号',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `POSITIONNO` (`item_code`,`inventory_on_warehouse_no`,`warehouse_no`,`shelf_no`)
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=utf8 COMMENT='仓库库存';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `item_find`
--

DROP TABLE IF EXISTS `item_find`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_find` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `item_find_no` varchar(64) NOT NULL,
  `item_name` varchar(128) NOT NULL COMMENT '商品名称',
  `item_code` varchar(64) NOT NULL DEFAULT '0' COMMENT '商品编码',
  `sku_code` varchar(64) NOT NULL,
  `is_new` tinyint(3) DEFAULT '0' COMMENT '是否新品',
  `sale_type` tinyint(3) DEFAULT '0' COMMENT '销售类型',
  `currency` tinyint(3) DEFAULT NULL COMMENT '币种',
  `buy_site` varchar(64) DEFAULT NULL COMMENT '采购站点',
  `origin` varchar(64) DEFAULT NULL COMMENT '产地',
  `logistic_type` tinyint(4) DEFAULT '0' COMMENT 'sku物流方式：0,直邮;1,拼邮',
  `contact_person` varchar(64) DEFAULT NULL COMMENT '联系人',
  `contact_tel` varchar(64) DEFAULT NULL COMMENT '联系电话',
  `id_card` tinyint(2) DEFAULT '0' COMMENT '是否身份证',
  `start_date` datetime DEFAULT NULL COMMENT '销售开始日期',
  `end_date` datetime DEFAULT NULL COMMENT '销售结束日期',
  `booking_date` datetime DEFAULT NULL COMMENT '预售时间',
  `is_sale` tinyint(4) DEFAULT '0' COMMENT '是否可售，1可售0不可销售',
  `wxis_sale` tinyint(4) DEFAULT '1' COMMENT '小程序是否可售 (1:小程序可售 0:小程序不可售)',
  `is_find` tinyint(4) DEFAULT '0' COMMENT '是否为小程序发现，0否 1是',
  `status` tinyint(2) DEFAULT '0' COMMENT '//0新档 1上架 2下架 -1删除',
  `desc` varchar(500) DEFAULT NULL COMMENT '商品描述信息',
  `spec` varchar(64) DEFAULT NULL COMMENT '规格',
  `model` varchar(64) DEFAULT NULL COMMENT '型号',
  `detail` text COMMENT '商品详情',
  `buyer_open_id` varchar(64) DEFAULT NULL COMMENT '买手id',
  `buyer_name` varchar(64) DEFAULT NULL COMMENT '买手名称',
  `purchase_status` tinyint(2) DEFAULT NULL COMMENT '采购发现状态 0:待审核 1:已通过 -1:已拒绝',
  `reason` varchar(1024) DEFAULT NULL COMMENT '推荐原因',
  `find_address` varchar(128) DEFAULT NULL COMMENT '商品发现地址',
  `refuse_reason` varchar(1024) DEFAULT NULL COMMENT '拒绝原因',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ITEMCODE` (`item_code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='商品';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `auth_resource`
--

DROP TABLE IF EXISTS `auth_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_resource` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `resource_id` varchar(64) DEFAULT NULL COMMENT '资源编码',
  `name` varchar(64) NOT NULL COMMENT '资源名称',
  `url` varchar(100) DEFAULT NULL COMMENT '资源路径',
  `open_mode` varchar(32) DEFAULT NULL COMMENT '打开方式 ajax,iframe,interface',
  `description` varchar(255) DEFAULT NULL COMMENT '资源介绍',
  `icon` varchar(32) DEFAULT NULL COMMENT '资源图标',
  `pid` bigint(19) DEFAULT NULL COMMENT '父级资源id',
  `seq` tinyint(2) NOT NULL DEFAULT '0' COMMENT '排序',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态',
  `resource_type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '资源类别',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `resource_id` (`resource_id`)
) ENGINE=InnoDB AUTO_INCREMENT=329 DEFAULT CHARSET=utf8 COMMENT='资源';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `buyer_receipt_detail`
--

DROP TABLE IF EXISTS `buyer_receipt_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `buyer_receipt_detail` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sku_code` varchar(64) NOT NULL COMMENT 'SKU ID',
  `item_code` varchar(64) DEFAULT NULL,
  `price` double(10,2) DEFAULT NULL COMMENT '采购价',
  `quantity` int(11) DEFAULT NULL COMMENT '采购数量',
  `upc` varchar(64) DEFAULT NULL,
  `receipt_no` varchar(64) DEFAULT NULL,
  `company_no` varchar(64) NOT NULL,
  `buyer_task_detail_no` varchar(64) DEFAULT NULL,
  `trans_quantity` int(11) DEFAULT NULL COMMENT '在途数量',
  `sku_buysite` varchar(64) DEFAULT NULL COMMENT '订购站点（方便采购人员标记使用）',
  `purchase_storage_no` varchar(64) DEFAULT NULL COMMENT '预入库单ID',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='采购发票详情，发票里面的每一种商品对应一条记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `scale_type`
--

DROP TABLE IF EXISTS `scale_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scale_type` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` varchar(64) NOT NULL COMMENT '名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=355 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='尺寸类型';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mall_shipping_address`
--

DROP TABLE IF EXISTS `mall_shipping_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mall_shipping_address` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `channel_no` varchar(64) NOT NULL,
  `customer_no` varchar(64) DEFAULT NULL,
  `open_id` varchar(64) NOT NULL,
  `receiver` varchar(50) NOT NULL,
  `receiver_state` varchar(50) NOT NULL,
  `receiver_city` varchar(50) NOT NULL,
  `receiver_district` varchar(50) NOT NULL,
  `address_detail` varchar(100) NOT NULL,
  `telephone` varchar(20) NOT NULL,
  `identity_card` varchar(18) DEFAULT NULL,
  `idcard_pic_front` varchar(255) DEFAULT NULL,
  `idcard_pic_reverse` varchar(255) DEFAULT NULL,
  `is_default` tinyint(4) DEFAULT '0',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `OPENID` (`open_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='顾客收件地址';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dealer_type`
--

DROP TABLE IF EXISTS `dealer_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dealer_type` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `code` varchar(64) DEFAULT NULL COMMENT '经销商类型代码',
  `name` varchar(64) DEFAULT NULL COMMENT '经销商类型名称',
  `remark` varchar(64) DEFAULT NULL COMMENT '备注',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `CODE` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=utf8 COMMENT='经销商类型';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `country` (
  `id` int(4) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `en_name` varchar(32) DEFAULT NULL COMMENT '外文名',
  `name` varchar(20) DEFAULT NULL COMMENT '国家名',
  `short_name` varchar(20) DEFAULT NULL COMMENT '国家简称',
  `country_code` varchar(10) DEFAULT NULL COMMENT '国家编码',
  `length_unit` varchar(32) DEFAULT NULL COMMENT '长度单位',
  `volume_unit` varchar(32) DEFAULT NULL COMMENT '容量单位',
  `weight_unit` varchar(32) DEFAULT NULL COMMENT '重量单位',
  `money_unit` varchar(32) DEFAULT NULL COMMENT '货币单位',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `country_code` (`country_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=380 DEFAULT CHARSET=utf8 COMMENT='国家';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `item_category_scale`
--

DROP TABLE IF EXISTS `item_category_scale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_category_scale` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  `category_code` varchar(10) NOT NULL COMMENT '类目编码',
  `category_name` varchar(64) NOT NULL COMMENT '类目名字',
  `scale_code` varchar(64) NOT NULL COMMENT '规格编码',
  `scale_name` varchar(64) NOT NULL,
  `scale_values` varchar(512) NOT NULL COMMENT '规格值域',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='规格和类目的映射表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `channel_account`
--

DROP TABLE IF EXISTS `channel_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `channel_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `channel_id` bigint(20) NOT NULL,
  `channel_no` varchar(64) NOT NULL,
  `type` int(4) DEFAULT NULL COMMENT '渠道类型；1：有赞，2：海狐，3：淘宝',
  `channel_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '渠道名称',
  `company_no` varchar(64) NOT NULL COMMENT '渠道账号所属买手公司id',
  `shop_code` varchar(255) NOT NULL COMMENT '迁移数据的时候，把appvalue1迁移过来',
  `shop_name` varchar(1024) DEFAULT NULL,
  `status` int(4) NOT NULL DEFAULT '0' COMMENT '状态 0:正常，1:关闭',
  `app_key` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '渠道品台分配给买手公司的账号appkey/clientId等',
  `app_secret` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '渠道品台分配给买手公司的账号secret',
  `access_token` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '授权后，获取到的token',
  `refresh_token` varchar(255) DEFAULT NULL COMMENT '刷新access_token的token',
  `server_url` varchar(255) DEFAULT NULL COMMENT '平台地址',
  `token_url` varchar(255) DEFAULT NULL COMMENT '获取token的URL',
  `access_key` varchar(255) DEFAULT NULL COMMENT '接入码',
  `secrete_key` varchar(255) DEFAULT NULL COMMENT '授权密码',
  `cookie` varchar(4096) DEFAULT NULL COMMENT '淘宝cookie，较长',
  `app_value1` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '渠道品台分配给买手公司的其他值value1',
  `value1_desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'app_value1描述',
  `app_value2` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '渠道品台分配给买手公司的其他值value1',
  `value2_desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'app_value2描述',
  `app_value3` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '渠道品台分配给买手公司的其他值value1',
  `value3_desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'app_value3描述',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `company_n` (`company_no`,`channel_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='买手公司渠道账号';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `shipping_packing_scale`
--

DROP TABLE IF EXISTS `shipping_packing_scale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shipping_packing_scale` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `packaging_scale_no` varchar(16) NOT NULL COMMENT '编号，即索引',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `name_en` varchar(64) DEFAULT NULL COMMENT '规格英文名称',
  `weight` double(10,2) DEFAULT NULL COMMENT '此包装对应的重量，单位g',
  `size_level` tinyint(2) DEFAULT NULL COMMENT '尺寸级别',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `scale_no` (`packaging_scale_no`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COMMENT='打包尺寸';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `channel_account_config`
--

DROP TABLE IF EXISTS `channel_account_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `channel_account_config` (
  `id` bigint(19) NOT NULL,
  `company_no` varchar(64) DEFAULT NULL,
  `shopCode` varchar(255) NOT NULL,
  `itemKey` varchar(255) DEFAULT NULL,
  `itemValue` varchar(255) DEFAULT NULL,
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `index_shopcode` (`shopCode`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `buyer_task`
--

DROP TABLE IF EXISTS `buyer_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `buyer_task` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  `buyer_task_no` varchar(64) NOT NULL,
  `title` varchar(256) DEFAULT NULL,
  `owner_no` varchar(64) DEFAULT NULL,
  `company_no` varchar(64) NOT NULL,
  `remark` varchar(256) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '-1为已取消，0为待采购，2为采购中，1为采购结束',
  `buyer_open_id` bigint(19) DEFAULT NULL,
  `buyer_name` varchar(64) DEFAULT NULL COMMENT '买手',
  `purchase_commission_mode` bigint(18) DEFAULT '0' COMMENT '采购佣金模式',
  `purchase_commission_str` varchar(45) DEFAULT '',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `buyer_task_no` (`buyer_task_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='采购任务，一个任务可以含有多种商品';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `auth_user`
--

DROP TABLE IF EXISTS `auth_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_user` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `company_no` varchar(64) NOT NULL COMMENT '所属公司id',
  `user_no` varchar(64) NOT NULL,
  `login_name` varchar(64) NOT NULL COMMENT '登陆名',
  `name` varchar(64) NOT NULL COMMENT '用户名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `sex` tinyint(2) DEFAULT '0' COMMENT '性别',
  `age` tinyint(2) DEFAULT '0' COMMENT '年龄',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `user_type` tinyint(2) DEFAULT '0' COMMENT '用户类别',
  `status` tinyint(2) DEFAULT '0' COMMENT '用户状态',
  `organization_id` int(11) DEFAULT '0' COMMENT '所属机构',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `wx_user_id` bigint(19) DEFAULT NULL COMMENT '微信用户ID',
  `wx_open_id` bigint(19) DEFAULT NULL COMMENT '微信openID',
  `wx_union_id` bigint(19) DEFAULT NULL COMMENT '微信unionID',
  `email` varchar(32) DEFAULT NULL COMMENT '电子邮箱',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_no` (`user_no`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `channel_shop`
--

DROP TABLE IF EXISTS `channel_shop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `channel_shop` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `version` bigint(19) unsigned DEFAULT '0',
  `channel_no` varchar(64) NOT NULL,
  `company_no` varchar(64) DEFAULT NULL,
  `shop_name` varchar(255) DEFAULT NULL,
  `shop_code` varchar(64) NOT NULL,
  `expires_time` datetime DEFAULT NULL,
  `proxy_url` varchar(255) DEFAULT NULL,
  `shop_type` varchar(64) DEFAULT NULL,
  `open` tinyint(1) DEFAULT '1',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `buyer_storage`
--

DROP TABLE IF EXISTS `buyer_storage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `buyer_storage` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `storage_no` varchar(64) NOT NULL,
  `warehouse_no` varchar(64) NOT NULL COMMENT '仓库ID',
  `company_no` varchar(64) NOT NULL,
  `warehouse_name` varchar(64) DEFAULT NULL COMMENT '仓库名称',
  `buyer_name` varchar(64) DEFAULT NULL COMMENT '买手名称',
  `buyer_open_id` bigint(19) DEFAULT '0' COMMENT '买手ID',
  `purchase_storage_no` varchar(64) NOT NULL COMMENT '入库单号',
  `buyer_task_no` varchar(64) DEFAULT NULL COMMENT '采购任务编号',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `storage_type` tinyint(4) DEFAULT '0' COMMENT '入库方式：0计划采购入库，1扫描入库',
  `entry_date` datetime DEFAULT NULL COMMENT '入库时间',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `storage_no` (`storage_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='买手采购入库表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sys_log`
--

DROP TABLE IF EXISTS `sys_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `log_no` varchar(64) NOT NULL,
  `login_name` varchar(255) DEFAULT NULL COMMENT '登陆名',
  `role_name` varchar(255) DEFAULT NULL COMMENT '角色名',
  `opt_content` text COMMENT '内容',
  `client_ip` varchar(255) DEFAULT NULL COMMENT '客户端ip',
  `result` varchar(255) DEFAULT NULL COMMENT '结果',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2606 DEFAULT CHARSET=utf8 COMMENT='系统日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'haidb2new'
--
/*!50003 DROP FUNCTION IF EXISTS `currval` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE FUNCTION `currval`(seq_name VARCHAR(50)) RETURNS int(11)
    DETERMINISTIC
BEGIN
     DECLARE value INTEGER; 
     SET value = 0; 
     SELECT current_value INTO value 
          FROM sequence
          WHERE name = seq_name; 
     RETURN value; 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `getTaskDailyItemSkuCase` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE FUNCTION `getTaskDailyItemSkuCase`(seq_sku_id int) RETURNS varchar(64) CHARSET utf8
BEGIN

     DECLARE value1 int default 0;
     DECLARE value2 int default 0;
     DECLARE value3 int default 0;
     DECLARE value4 int default 0;
     DECLARE value5 int default 0;
     DECLARE value6 int default 0;
     DECLARE value7 int default 0;

	SELECT sum(`quantity`) into value1 FROM erp_order WHERE status=0 and sku_id=seq_sku_id group by sku_id;
	SELECT inventory,trans_inv,virtual_inv,locked_inv,locked_trans_inv into value3,value4,value5,value6,value7 FROM inventory WHERE sku_id=seq_sku_id;
	
	
	IF ISNULL(value1) THEN SET value1=0;
	END IF;
	IF ISNULL(value3) THEN SET value3=0;
	END IF;
	IF ISNULL(value4) THEN SET value4=0;
	END IF;
	IF ISNULL(value5) THEN SET value5=0;
	END IF;
	IF ISNULL(value6) THEN SET value6=0;
	END IF;
	IF ISNULL(value7) THEN SET value7=0;
	END IF;

	SET value2 = value1-value3-value4;
	IF value2<0 THEN SET value2=0;	
	END IF;

return CONCAT(value1, ",", value2, ",", value3, ",", value4, ",", value5, ",", value6, ",", value7);

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `nextval` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE FUNCTION `nextval`(seq_name VARCHAR(50)) RETURNS int(11)
    DETERMINISTIC
BEGIN
     UPDATE sequence
          SET current_value = current_value + increment 
          WHERE name = seq_name; 
     RETURN currval(seq_name); 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `setval` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE FUNCTION `setval`(seq_name VARCHAR(50), value INTEGER) RETURNS int(11)
    DETERMINISTIC
BEGIN
     UPDATE sequence
          SET current_value = value 
          WHERE name = seq_name; 
     RETURN currval(seq_name); 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-14 21:31:48
