CREATE DATABASE  IF NOT EXISTS `haidb2new` default character set utf8mb4 collate utf8mb4_unicode_ci;
USE `haidb2new`;


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

# 海关订单表
DROP TABLE IF EXISTS `customs_order`;
CREATE TABLE `customs_order` (
  `id` BIGINT(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `business_no` VARCHAR(20) DEFAULT NULL COMMENT '业务编码：可以是清单预录入号，字段作用是回执给到企业的时候通过这个编号企业能认出对应之前发送的哪个单子',

  `company_name` VARCHAR(200) DEFAULT NULL COMMENT '企业备案名称：电商平台在跨境电商通关服务平台的备案名',
  `company_code` VARCHAR(20) DEFAULT NULL COMMENT '企业备案编号：电商平台在跨境电商通关服务的备案编号',
  `ie_flag` CHAR(1) DEFAULT NULL COMMENT '进出口标志：I:进口E:出口',
  `pay_type` VARCHAR(60) DEFAULT NULL COMMENT '支付类型：01:银行卡支付 02:余额支付 03:其他',
  `pay_company_code` VARCHAR(50) DEFAULT NULL COMMENT '支付公司编码：支付平台在跨境平台备案编号',
  `pay_company_name` VARCHAR(50) DEFAULT NULL COMMENT '支付公司名称：对接总署版必填；支付平台在跨境平台备案名称',
  `pay_number` VARCHAR(60) DEFAULT NULL COMMENT '支付单号：支付成功后，支付平台反馈给电商平台的支付单号',
  `order_total_amount` DOUBLE(12,4) DEFAULT NULL COMMENT '订单总金额：货款+订单税款+运费+保费',
  `order_goods_amount` DOUBLE(12,4) DEFAULT NULL COMMENT '订单货款：与成交总价一致，按以电子订单的实际销售价格作为完税价格',
  `discount` DOUBLE(12,4) DEFAULT NULL COMMENT '非现金抵扣金额：使用积分、虚拟货币、代金券等非现金支付金额，无则填写"0"',
  `order_no` VARCHAR(60) DEFAULT NULL COMMENT '订单编号：电商平台订单号，注意：一个订单只能对应一个运单(包裹)',
  `order_tax_amount` DOUBLE(12,4) DEFAULT NULL COMMENT '订单税款：交易过程中商家向用户征收的税款，按缴税新政计算填写',
  `fee_amount` DOUBLE(12,4) DEFAULT NULL COMMENT '运费：交易过程中商家向用户征收的运费，免邮模式填写0',
  `insure_amount` DOUBLE(12,4) DEFAULT NULL COMMENT '保费：商家向用户征收的保价费用，无保费可填写0',
  `e_commerce_code` VARCHAR(60) DEFAULT NULL COMMENT '电商企业编码：电商平台下的商家备案编号',
  `e_commerce_name` VARCHAR(200) DEFAULT NULL COMMENT '电商企业名称：电商平台下的商家备案名称',
  `trade_time` VARCHAR(25) DEFAULT NULL COMMENT '成交时间：格式 2014-02-18 15:58:11',
  `curr_code` VARCHAR(3) DEFAULT NULL COMMENT '成交币制',
  `total_amount` DOUBLE(14,4) DEFAULT NULL COMMENT '成交总价：与订单货款一致',
  `consignee_email` VARCHAR(60) DEFAULT NULL COMMENT '收件人Email',
  `consignee_tel` VARCHAR(60) DEFAULT NULL COMMENT '收件人联系方式',
  `consignee` VARCHAR(60) DEFAULT NULL COMMENT '收件人姓名',
  `consignee_address` VARCHAR(255) DEFAULT NULL COMMENT '收件人地址',
  `total_count` INT DEFAULT NULL COMMENT '总件数：包裹中独立包装的物品总数，不考虑物品计量单位',
  `batch_numbers` VARCHAR(100) DEFAULT NULL COMMENT '商品批次号',
  `consignee_ditrict` VARCHAR(6) DEFAULT NULL COMMENT '收货地址行政区划代码：参照国家统计局公布的国家行政区划标准填制',
  `post_mode` VARCHAR(20) DEFAULT NULL COMMENT '发货方式（物流方式）',
  `sender_country` VARCHAR(3) DEFAULT NULL COMMENT '发件人国别',
  `sender_name` VARCHAR(200) DEFAULT NULL COMMENT '发件人姓名',
  `logis_company_name` VARCHAR(200) DEFAULT NULL COMMENT '物流企业名称',
  `logis_company_code` VARCHAR(20) DEFAULT NULL COMMENT '物流企业编号',
  `zip_code` VARCHAR(20) DEFAULT NULL COMMENT '邮编',
  `note` VARCHAR(255) DEFAULT NULL COMMENT '备注',
  `way_bills` VARCHAR(255) DEFAULT NULL COMMENT '运单号列表：运单之间以分号隔开',
  `rate` VARCHAR(10) DEFAULT NULL COMMENT '汇率：如果是人民币，统一填写1',
  `user_procotol` VARCHAR(255) DEFAULT '本人承诺所购买商品系个人合理自用，现委托商家代理申报、代缴税款等通关事宜，本人保证遵守《海关法》和国家相关法律法规，保证所提供的身份信息和收货信息真实完整，无侵犯他人权益的行为，以上委托关系系如实填写，本人愿意接受海关、检验检疫机构及其他监管部门的监管，并承担相应法律责任.' COMMENT '个人委托申报协议',

  `purchaser_id` VARCHAR(100) DEFAULT NULL COMMENT '购买人ID：购买人在电商平台的注册ID',
  `name` VARCHAR(100) DEFAULT NULL COMMENT '姓名',
  `email` VARCHAR(140) DEFAULT NULL COMMENT '注册邮箱',
  `tel_number` VARCHAR(30) DEFAULT NULL COMMENT '联系电话',
  `paper_type` VARCHAR(20) DEFAULT NULL COMMENT '证件类型代码：01-身份证（试点期间只能是身份证），02-护照，03-其他',
  `paper_number` VARCHAR(100) DEFAULT NULL COMMENT '证件号码',
  `address` VARCHAR(200) DEFAULT NULL COMMENT '地址',

  `chk_mark` CHAR(1) DEFAULT NULL COMMENT '处理结果：1-成功 2-处理失败',
  `notice_date` VARCHAR(25) DEFAULT NULL COMMENT '通知日期及时间：YYYY-MM-DD HH:MM',
  `result_info` VARCHAR(400) DEFAULT NULL COMMENT '5位校验状态码+冒号+处理结果文字信息，格式类似：22001:企业编号未备',

  `creator` VARCHAR(32) DEFAULT 'system' COMMENT '创建者',
  `modifier` VARCHAR(32) DEFAULT 'system' COMMENT '修改者',
  `gmt_modify` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_del` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '软删除',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

# 海关订单详情表
DROP TABLE IF EXISTS `customs_order_detail`;
CREATE TABLE `customs_order_detail`(
  `id` BIGINT(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `business_no` VARCHAR(20) DEFAULT NULL COMMENT '业务编码：可以是清单预录入号，字段作用是回执给到企业的时候通过这个编号企业能认出对应之前发送的哪个单子',
  `goods_order` INT DEFAULT NULL COMMENT '商品序号：商品序号,序号不大于50',
  `goods_name` VARCHAR(255) DEFAULT NULL COMMENT '物品名称',
  `code_ts` VARCHAR(10) DEFAULT NULL COMMENT '商品HS编码',
  `goods_model` VARCHAR(255) DEFAULT NULL COMMENT '商品规格、型号',
  `origin_country` VARCHAR(5) DEFAULT NULL COMMENT '产销国',
  `unit_price` DOUBLE(15,4) DEFAULT NULL COMMENT '成交单价：各商品成交单价*成交数量总和应等于表头的货款、成交总价',
  `currency` VARCHAR(3) DEFAULT '142' COMMENT '币制：限定为人民币，填写“142”',
  `goods_count` DOUBLE(15,4) DEFAULT NULL COMMENT '成交数量',
  `goods_unit` VARCHAR(3) DEFAULT NULL COMMENT '成交计量单位',
  `gross_weight` DOUBLE(15,4) DEFAULT NULL COMMENT '商品毛重',
  `bar_code` VARCHAR(50) DEFAULT NULL COMMENT '条形码：国际通用的商品条形码，一般由前缀部分、制造厂商代码、商品代码和校验码组成。',
  `note` VARCHAR(1000) DEFAULT NULL COMMENT '备注：促销活动，商品单价偏离市场价格的，可以在此说明。',
  `creator` VARCHAR(32) DEFAULT 'system' COMMENT '创建者',
  `modifier` VARCHAR(32) DEFAULT 'system' COMMENT '修改者',
  `gmt_modify` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_del` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '软删除',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

# 海关清单表
DROP TABLE IF EXISTS `customs_declare`;
CREATE TABLE `customs_declare`(
  `id` BIGINT(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `business_no` VARCHAR(20) DEFAULT NULL COMMENT '业务编码：可以是清单预录入号，字段作用是回执给到企业的时候通过这个编号企业能认出对应之前发送的哪个单子',

  `account_book_no` VARCHAR(50) DEFAULT NULL COMMENT '账册编号：一线转入的对应账册编号，保税模式必填，直邮模式不填',
  `ie_flag` CHAR(1) DEFAULT 'I' COMMENT '进出口标记：必须为I',
  `pre_entry_number` CHAR(18) DEFAULT NULL COMMENT '预录入编号：4位电商编号+14位企业流水，电商平台/物流企业生成后发送服务平台，与运单号一一对应，同个运单重新申报时，保持不变',
  `import_type` CHAR(1) DEFAULT NULL COMMENT '监管方式：填写0-对应9610直邮进口，填写1-对应1210保税进口，填写2-对应1239保税进口',
  `in_out_date_str` VARCHAR(25) DEFAULT NULL COMMENT '进口日期：格式 2014-02-18 20:33:33',
  `ie_port` VARCHAR(5) DEFAULT NULL COMMENT '进口口岸代码：口岸代码表',
  `destination_port` VARCHAR(5) DEFAULT NULL COMMENT '指运港(抵运港)：对应参数表',
  `traf_name` VARCHAR(100) DEFAULT NULL COMMENT '运输工具名称：包括字母和数字，可以填写中文；转关时填写@+16位转关单号',
  `voyage_no` VARCHAR(32) DEFAULT NULL COMMENT '航班航次号：直邮必填,包括字母和数字，可以有中文',
  `traf_no` VARCHAR(100) DEFAULT NULL COMMENT '运输工具编号：直邮必填',
  `traf_mode` VARCHAR(30) DEFAULT NULL COMMENT '运输方式：参照运输方式代码表(TRANSF)',
  `declare_company_type` VARCHAR(30) DEFAULT NULL COMMENT '申报单位类别：个人委托电商企业申报、个人委托物流企业申报、个人委托第三方申报',
  `declare_company_code` VARCHAR(20) DEFAULT NULL COMMENT '申报企业代码：指委托申报单位代码，需在海关注册，具有报关资质',
  `declare_company_name` VARCHAR(200) DEFAULT NULL COMMENT '申报企业名称：指委托申报单位名称，需在海关注册，具有报关资质',
  `company_name` VARCHAR(200) DEFAULT NULL COMMENT '电商平台名称：电商平台在跨境电商通关服务平台的备案名称',
  `company_code` VARCHAR(20) DEFAULT NULL COMMENT '电商平台代码：电商平台在跨境电商通关服务的备案编号',
  `e_commerce_code` VARCHAR(60) DEFAULT NULL COMMENT '电商企业编码：电商平台下的商家备案编号',
  `e_commerce_name` VARCHAR(200) DEFAULT NULL COMMENT '电商企业名称：电商平台下的商家备案名称',
  `logis_company_name` VARCHAR(200) DEFAULT NULL COMMENT '物流企业名称：物流企业在跨境平台备案的企业名称',
  `logis_company_code` VARCHAR(20) DEFAULT NULL COMMENT '物流企业编号：物流企业在跨境平台备案编码',
  `order_no` VARCHAR(60) DEFAULT NULL COMMENT '订单编号',
  `way_bill` VARCHAR(50) DEFAULT NULL COMMENT '物流运单编号',
  `bill_no` VARCHAR(37) DEFAULT NULL COMMENT '提运单号：直邮必填',
  `trade_country` VARCHAR(20) DEFAULT NULL COMMENT '启运国（地区）：参照国别代码表(COUNTRY)',
  `pack_no` DOUBLE(14,4) DEFAULT NULL COMMENT '件数：表体独立包装的物品总数，不考虑物品计量单位，大于0',
  `gross_weight` DOUBLE(14,4) DEFAULT NULL COMMENT '毛重（公斤）：大于0',
  `net_weight` DOUBLE(14,4) DEFAULT NULL COMMENT '净重（公斤）：大于0',
  `warp_type` VARCHAR(20) DEFAULT NULL COMMENT '包装种类代码：参照包装种类代码表',
  `remark` VARCHAR(200) DEFAULT NULL COMMENT '备注',
  `decl_port` VARCHAR(5) DEFAULT NULL COMMENT '申报地海关代码：对应参数表',
  `entering_person` VARCHAR(20) DEFAULT '9999' COMMENT '录入人：默认9999',
  `entering_company_name` VARCHAR(30) DEFAULT '9999' COMMENT '录入单位名称：默认9999',
  `declarant_no` VARCHAR(20) DEFAULT NULL COMMENT '报关员代码',
  `customs_field` VARCHAR(20) DEFAULT NULL COMMENT '监管场所代码：对应参数表 292801-下城园区、299102-下沙园区、299201-萧山园区',
  `sender_name` VARCHAR(20) DEFAULT NULL COMMENT '发件人：可以数字和字母，可以有中文和英文',
  `consignee` VARCHAR(20) DEFAULT NULL COMMENT '收件人：可以数字和字母，可以有中文和英文',
  `sender_country` VARCHAR(20) DEFAULT NULL COMMENT '发件人国别：参数表',
  `sender_city` VARCHAR(20) DEFAULT NULL COMMENT '发件人城市：可以数字和字母，可以有中文和英文',
  `paper_type` CHAR(1) DEFAULT NULL COMMENT '收件人证件类型：1-身份证（试点期间只能是身份证）、2-护照、3-其他',
  `paper_number` VARCHAR(50) DEFAULT NULL COMMENT '收件人证件号：可以有数字和字母',
  `consignee_address` VARCHAR(255) DEFAULT NULL COMMENT '收件人地址：对应订单中的收件人地址',
  `purchaser_tel_number` VARCHAR(30) DEFAULT NULL COMMENT '购买人电话：海关监管对象的电话,对应订单中的购买人联系电话',
  `buyer_id_type` VARCHAR(1) DEFAULT NULL COMMENT '订购人证件类型：1-身份证；2-其它',
  `buyer_id_number` VARCHAR(60) DEFAULT NULL COMMENT '订购人证件号码：海关监控对象的身份证号,对应订单购买人证件号码',
  `buyer_name` VARCHAR(60) DEFAULT NULL COMMENT '订购人姓名：海关监控对象的姓名,对应订单购买人人姓名',
  `worth` DOUBLE(14,4) DEFAULT NULL COMMENT '价值：表体所有商品总价的和+运费+保费',
  `fee_amount` DOUBLE(12,4) DEFAULT NULL COMMENT '运费：交易过程中商家向用户征收的运费，免邮模式填写0',
  `insure_amount` DOUBLE(12,4) DEFAULT NULL COMMENT '商家向用户征收的保价费用，无保费可填写0',
  `curr_code` VARCHAR(18) DEFAULT NULL COMMENT '币制：对应参数表',
  `main_g_name` VARCHAR(255) DEFAULT NULL COMMENT '主要货物名称：可以数字和字母或者中文',
  `internal_area_company_no` VARCHAR(50) DEFAULT NULL COMMENT '区内企业代码：保税进口必填，填报仓储企业编码',
  `internal_area_company_name` VARCHAR(200) DEFAULT NULL COMMENT '区内企业名称：保税进口必填，填报仓储企业名称',
  `assure_code` VARCHAR(50) DEFAULT NULL COMMENT '担保企业编号',
  `application_form_no` VARCHAR(30) DEFAULT NULL COMMENT '申请单编号：保税进口必填，指仓储企业事先在辅助系统申请的分送集报申请单编号',
  `is_authorize` CHAR(1) DEFAULT NULL COMMENT '是否授权：代表是否个人买家授权电商申报数据，0代表否，1代表是',
  `license_no` VARCHAR(50) DEFAULT NULL COMMENT '许可证号',

  `personal_goods_form_no` VARCHAR(18) DEFAULT NULL COMMENT '清单编号：关区号（4位）+年份（4位）+进出口标志（1位）+流水号（9位），服务平台生成后，反馈管理平台及电商平台',
  `approve_result` VARCHAR(2) DEFAULT NULL COMMENT '清单状态：见参数表',
  `approve_comment` VARCHAR(70) DEFAULT NULL COMMENT '四位审单状态码+冒号+海关审批意见，格式类似：3201:支付类型不存在.四位审单状态码定义见参数文档。',
  `process_time` VARCHAR(30) DEFAULT NULL COMMENT '海关处理时间 格式要求：20140623101024',

  `chk_mark` CHAR(1) DEFAULT NULL COMMENT '处理结果：1-成功 2-处理失败',
  `notice_date` VARCHAR(25) DEFAULT NULL COMMENT '通知日期及时间：YYYY-MM-DD HH:MM',
  `result_info` VARCHAR(400) DEFAULT NULL COMMENT '5位校验状态码+冒号+处理结果文字信息，格式类似：22001:企业编号未备',

  `creator` VARCHAR(32) DEFAULT 'system' COMMENT '创建者',
  `modifier` VARCHAR(32) DEFAULT 'system' COMMENT '修改者',
  `gmt_modify` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_del` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '软删除',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

# 海关清单详情表
DROP TABLE IF EXISTS `customs_declare_detail`;
CREATE TABLE `customs_declare_detail`(
  `id` BIGINT(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `business_no` VARCHAR(20) DEFAULT NULL COMMENT '业务编码：可以是清单预录入号，字段作用是回执给到企业的时候通过这个编号企业能认出对应之前发送的哪个单子',
  `goods_order` INT DEFAULT NULL COMMENT '序号：只能有数字，外网自动生成大于0小于50',
  `code_ts` VARCHAR(50) DEFAULT NULL COMMENT '商品编码：填写商品对应的HS编码',
  `goods_item_no` VARCHAR(30) DEFAULT NULL COMMENT '企业商品货号',
  `item_record_no` VARCHAR(30) DEFAULT NULL COMMENT '账册备案料号：保税必填，与仓储企业备案的电子账册中料号数据一致',
  `item_name` VARCHAR(250) DEFAULT NULL COMMENT '企业商品品名',
  `goods_name` VARCHAR(255) DEFAULT NULL COMMENT '商品名称',
  `goods_model` VARCHAR(255) DEFAULT NULL COMMENT '商品规格型号：有数字和字母或者中文',
  `origin_country` VARCHAR(30) DEFAULT NULL COMMENT '原产国（地区）：参照国别代码表(COUNTRY)',
  `trade_curr` VARCHAR(20) DEFAULT NULL COMMENT '币制：参照币制代码表(CURR)',
  `trade_total` DOUBLE(14,4) DEFAULT NULL COMMENT '成交总价：与申报总价一致',
  `decl_price` DOUBLE(14,4) DEFAULT NULL COMMENT '单价',
  `decl_total_price` DOUBLE(14,4) DEFAULT NULL COMMENT '总价：申报数量乘以申报单价',
  `use_to` VARCHAR(200) DEFAULT NULL COMMENT '用途',
  `declare_count`  DOUBLE(14,4) DEFAULT NULL COMMENT '数量',
  `goods_unit` VARCHAR(20) DEFAULT NULL COMMENT '计量单位：参照计量单位代码表(UNIT)',
  `goods_gross_weight` DOUBLE(14,4) DEFAULT NULL COMMENT '商品毛重',
  `first_unit` VARCHAR(15) DEFAULT NULL COMMENT '法定计量单位：填写商品HS编码对应的第一单位',
  `first_count` DOUBLE(14,4) DEFAULT NULL COMMENT '法定数量：根据第一单位填写对应数量',
  `second_unit` VARCHAR(15) DEFAULT NULL COMMENT '第二计量单位：填写商品HS编码对应的第二单位',
  `second_count` DOUBLE(14,4) DEFAULT NULL COMMENT '第二数量：根据第二单位填写对应数量',
  `product_record_no` VARCHAR(18) DEFAULT NULL COMMENT '产品国检备案编号：通过向国检备案获取',
  `web_site` VARCHAR(100) DEFAULT NULL COMMENT '商品网址',
  `bar_code` VARCHAR(50) DEFAULT NULL COMMENT '条形码',
  `note` VARCHAR(1000) DEFAULT NULL COMMENT '备注',
  `creator` VARCHAR(32) DEFAULT 'system' COMMENT '创建者',
  `modifier` VARCHAR(32) DEFAULT 'system' COMMENT '修改者',
  `gmt_modify` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_del` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '软删除',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;



# 对接盛付通需要的四张表
# 支付单表
DROP TABLE IF EXISTS `shengpay_pay`;
CREATE TABLE `shengpay_pay` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `company_no` varchar(64) NOT NULL COMMENT '所属公司编号',
  `merchant_order_no` varchar(64) NOT NULL COMMENT '商户系统内的唯一订单号',
  `amount` double NOT NULL COMMENT '该笔订单的交易金额，单位默认为RMB-元，精确到小数点后两位，如：23.42',
  `currency` varchar(10) DEFAULT NULL COMMENT '货币类型',
  `pay_channel` varchar(10) DEFAULT NULL COMMENT '支付渠道',
  `sft_order_no` varchar(64) DEFAULT NULL COMMENT '盛付通系统内针对此商户订单的唯一订单号，如: C20160105105839885474',
  `order_create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '订单创建时间',
  `trace_no` varchar(64) DEFAULT NULL COMMENT '商户生成的报文唯一消息标识',
  `trans_no` varchar(64) DEFAULT NULL COMMENT '商户调用收单接口成功后盛付通返回的交易订单号',
  `trans_status` varchar(10) DEFAULT NULL COMMENT '支付状态',
  `trans_amount` double DEFAULT NULL COMMENT '实际交易金额',
  `trans_type` varchar(10) DEFAULT NULL COMMENT '交易类型',
  `trans_time` datetime DEFAULT NULL COMMENT '交易时间',
  `creator` varchar(32) DEFAULT 'system' COMMENT '创建者',
  `modifier` varchar(32) DEFAULT 'system' COMMENT '修改者',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '软删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC;

# 退款单表
DROP TABLE IF EXISTS `shengpay_refund`;
CREATE TABLE `shengpay_refund` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `company_no` varchar(64) NOT NULL COMMENT '所属公司编号',
  `merchant_order_no` varchar(64) NOT NULL COMMENT '商户系统内的唯一订单号',
  `refund_order_no` varchar(64) NOT NULL COMMENT '退款请求流水号(商户系统唯一)',
  `refund_amount` double DEFAULT NULL COMMENT '退款金额(与支付金额一致)',
  `status` varchar(10) DEFAULT NULL COMMENT '退款状态',
  `refund_trans_no` varchar(64) DEFAULT NULL COMMENT '盛付通退款订单号',
  `sft_order_no` varchar(64) DEFAULT NULL COMMENT '盛付通系统内针对此商户订单的唯一订单号，如: C20160105105839885474',
  `order_amount` double DEFAULT NULL COMMENT '订单金额',
  `refund_time` datetime DEFAULT NULL COMMENT '退款时间',
  `trace_no` varchar(64) DEFAULT NULL COMMENT '商户生成的报文唯一消息标识',
  `creator` varchar(32) DEFAULT 'system' COMMENT '创建者',
  `modifier` varchar(32) DEFAULT 'system' COMMENT '修改者',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '软删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC;

# 分账单表
DROP TABLE IF EXISTS `shengpay_sharing`;
CREATE TABLE `shengpay_sharing` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `company_no` varchar(64) NOT NULL COMMENT '所属公司编号',
  `merchant_order_no` varchar(64) DEFAULT NULL COMMENT '商户系统内的唯一订单号',
  `sharing_order_no` varchar(64) DEFAULT NULL COMMENT '分账请求订单号',
  `sharing_query_order_no` varchar(64) DEFAULT NULL COMMENT '分账查询请求订单号',
  `sharing_req_no` varchar(64) DEFAULT NULL COMMENT '分账请求号',
  `status` varchar(10) DEFAULT NULL COMMENT '分账状态 C:创建 P:处理中 S:成功 F:失败 R:被风控',
  `creator` varchar(32) DEFAULT 'system' COMMENT '创建者',
  `modifier` varchar(32) DEFAULT 'system' COMMENT '修改者',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '软删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC;

# 分账单子项表
DROP TABLE IF EXISTS `shengpay_sharing_item`;
CREATE TABLE `shengpay_sharing_item` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `company_no` varchar(64) NOT NULL COMMENT '所属公司编号',
  `sharing_req_no` varchar(64) NOT NULL COMMENT '盛付通分账流水号',
  `sharing_no` varchar(64)  DEFAULT NULL COMMENT '分帐子项请求序号',
  `sd_sharing_no` varchar(64) DEFAULT NULL COMMENT '盛付通子分账流水号',
  `status` varchar(10) DEFAULT NULL COMMENT '分账状态：0处理中，1成功',
  `sharing_amount` double DEFAULT NULL COMMENT '分帐金额，如10.00表示10元',
  `sharing_rate` double DEFAULT NULL COMMENT '分帐比例 如0.50表示50%',
  `payee_id` varchar(64) DEFAULT NULL COMMENT '会员标识',
  `payee_id_type` varchar(10) DEFAULT NULL COMMENT '会员类型1：商户号，4：memberid',
  `creator` varchar(32) DEFAULT 'system' COMMENT '创建者',
  `modifier` varchar(32) DEFAULT 'system' COMMENT '修改者',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '软删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC;


CREATE TABLE `id_card`  (
  `id` bigint(19) UNSIGNED NOT NULL AUTO_INCREMENT,
  `gmt_modify` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '操作时间',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT 0,
  `real_name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `id_number` varchar(255) DEFAULT NULL COMMENT '身份证',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name_card_union_index`(`id_number`, `real_name`) USING BTREE COMMENT '姓名和身份证唯一性索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 ROW_FORMAT = Dynamic;


DROP TABLE IF EXISTS `shengpay_pay`;
CREATE TABLE `shengpay_pay` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `company_no` varchar(64) NOT NULL COMMENT '所属公司编号',
  `merchant_order_no` varchar(64) NOT NULL COMMENT '商户系统内的唯一订单号',
  `amount` double NOT NULL COMMENT '该笔订单的交易金额，单位默认为RMB-元，精确到小数点后两位，如：23.42',
  `currency` varchar(10) DEFAULT NULL COMMENT '货币类型',
  `pay_channel` varchar(10) DEFAULT NULL COMMENT '支付渠道',
  `sft_order_no` varchar(64) DEFAULT NULL COMMENT '盛付通系统内针对此商户订单的唯一订单号，如: C20160105105839885474',
  `order_create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '订单创建时间',
  `trace_no` varchar(64) DEFAULT NULL COMMENT '商户生成的报文唯一消息标识',
  `trans_no` varchar(64) DEFAULT NULL COMMENT '商户调用收单接口成功后盛付通返回的交易订单号',
  `trans_status` varchar(10) DEFAULT NULL COMMENT '支付状态',
  `trans_amount` double DEFAULT NULL COMMENT '实际交易金额',
  `trans_type` varchar(10) DEFAULT NULL COMMENT '交易类型',
  `trans_time` datetime DEFAULT NULL COMMENT '交易时间',
  `creator` varchar(32) DEFAULT 'system' COMMENT '创建者',
  `modifier` varchar(32) DEFAULT 'system' COMMENT '修改者',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '软删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `shengpay_refund`;
CREATE TABLE `shengpay_refund` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `company_no` varchar(64) NOT NULL COMMENT '所属公司编号',
  `merchant_order_no` varchar(64) NOT NULL COMMENT '商户系统内的唯一订单号',
  `refund_order_no` varchar(64) NOT NULL COMMENT '退款请求流水号(商户系统唯一)',
  `refund_amount` double DEFAULT NULL COMMENT '退款金额(与支付金额一致)',
  `status` varchar(10) DEFAULT NULL COMMENT '退款状态',
  `refund_trans_no` varchar(64) DEFAULT NULL COMMENT '盛付通退款订单号',
  `sft_order_no` varchar(64) DEFAULT NULL COMMENT '盛付通系统内针对此商户订单的唯一订单号，如: C20160105105839885474',
  `order_amount` double DEFAULT NULL COMMENT '订单金额',
  `refund_time` datetime DEFAULT NULL COMMENT '退款时间',
  `trace_no` varchar(64) DEFAULT NULL COMMENT '商户生成的报文唯一消息标识',
  `creator` varchar(32) DEFAULT 'system' COMMENT '创建者',
  `modifier` varchar(32) DEFAULT 'system' COMMENT '修改者',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '软删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `shengpay_sharing`;
CREATE TABLE `shengpay_sharing` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `company_no` varchar(64) NOT NULL COMMENT '所属公司编号',
  `merchant_order_no` varchar(64) DEFAULT NULL COMMENT '商户系统内的唯一订单号',
  `sharing_order_no` varchar(64) DEFAULT NULL COMMENT '分账请求订单号',
  `sharing_query_order_no` varchar(64) DEFAULT NULL COMMENT '分账查询请求订单号',
  `sharing_req_no` varchar(64) DEFAULT NULL COMMENT '分账请求号',
  `status` varchar(10) DEFAULT NULL COMMENT '分账状态 C:创建 P:处理中 S:成功 F:失败 R:被风控',
  `creator` varchar(32) DEFAULT 'system' COMMENT '创建者',
  `modifier` varchar(32) DEFAULT 'system' COMMENT '修改者',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '软删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `shengpay_sharing_item`;
CREATE TABLE `shengpay_sharing_item` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `company_no` varchar(64) NOT NULL COMMENT '所属公司编号',
  `sharing_req_no` varchar(64) NOT NULL COMMENT '盛付通分账流水号',
  `sharing_no` varchar(64)  DEFAULT NULL COMMENT '分帐子项请求序号',
  `sd_sharing_no` varchar(64) DEFAULT NULL COMMENT '盛付通子分账流水号',
  `status` varchar(10) DEFAULT NULL COMMENT '分账状态：0处理中，1成功',
  `sharing_amount` double DEFAULT NULL COMMENT '分帐金额，如10.00表示10元',
  `sharing_rate` double DEFAULT NULL COMMENT '分帐比例 如0.50表示50%',
  `payee_id` varchar(64) DEFAULT NULL COMMENT '会员标识',
  `payee_id_type` varchar(10) DEFAULT NULL COMMENT '会员类型1：商户号，4：memberid',
  `creator` varchar(32) DEFAULT 'system' COMMENT '创建者',
  `modifier` varchar(32) DEFAULT 'system' COMMENT '修改者',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '软删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC;

CREATE TABLE `commission_sumary_detail`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_no` varchar(64) NOT NULL COMMENT '所属公司id',
  `status` int(4) DEFAULT NULL COMMENT '1待结算2可结算3已结算',
  `gmt_modify` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '操作时间',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `settlement_no` varchar(64) DEFAULT NULL COMMENT '结算单号',
  `is_del` tinyint(1) NOT NULL DEFAULT 0,
  `share_user_id` varchar(255) DEFAULT NULL,
  `settlement` decimal(16, 2) DEFAULT NULL,
  `sub_order_no` varchar(64) DEFAULT NULL COMMENT '绑定订单信息',
  `sale_price` decimal(16, 2) DEFAULT NULL COMMENT '销售额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1   ROW_FORMAT = Dynamic;

CREATE TABLE `commission_sumary_settlement`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_no` varchar(64) NOT NULL COMMENT '所属公司id',
  `status` int(4) DEFAULT NULL,
  `gmt_modify` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '操作时间',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT 0,
  `settlement_no` varchar(64) DEFAULT NULL COMMENT '结算单号',
  `share_user_id` varchar(64) DEFAULT NULL,
  `share_user_name` varchar(64) DEFAULT NULL COMMENT '代理名字',
  `total_price` decimal(16, 2) DEFAULT NULL COMMENT '实付总计',
  `settlement` decimal(16, 2) DEFAULT NULL COMMENT '实际结算',
  `detail_count` int(10) DEFAULT NULL COMMENT '结算订单数',
  `settlement_time` datetime(0) DEFAULT NULL COMMENT '结算时间',
  `remark` varchar(1024) DEFAULT NULL COMMENT '结算备注',
  `pay_type` varchar(255) DEFAULT NULL COMMENT '支付方式',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1   ROW_FORMAT = Dynamic;

CREATE TABLE `commission_sumary`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_no` varchar(64) NOT NULL COMMENT '所属公司id',
  `gmt_modify` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '操作时间',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT 0,
  `order_no` varchar(64) DEFAULT NULL,
  `sub_order_no` varchar(64) DEFAULT NULL,
  `item_code` varchar(255) DEFAULT NULL,
  `item_name` varchar(255) DEFAULT NULL,
  `sku_code` varchar(255) DEFAULT NULL,
  `sku_pic` varchar(255) DEFAULT NULL,
  `upc` varchar(255) DEFAULT NULL,
  `scale` varchar(255) DEFAULT NULL,
  `sale_price` decimal(16, 2) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL COMMENT '商品数量',
  `total_settlement` decimal(16, 2) DEFAULT NULL,
  `receiver_info` varchar(1024) DEFAULT NULL COMMENT '收件信息',
  `order_time` datetime(0) DEFAULT NULL COMMENT '销售时间，下单时间',
  `order_status` varchar(255) DEFAULT NULL,
  `order_desc` varchar(255) DEFAULT NULL,
  `status` int(4) DEFAULT NULL COMMENT '0待结算1可结算2已结算',
  `receive_date` datetime(0) DEFAULT NULL COMMENT '签收时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1   ROW_FORMAT = Dynamic;


CREATE TABLE IF NOT EXISTS `mall_commision_apply` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `company_no` varchar(64) DEFAULT NULL,
  `order_no` varchar(64) DEFAULT NULL,
  `sub_order_no` varchar(64) DEFAULT NULL,
  `commision` varchar(4096) DEFAULT NULL,
  `status` varchar(2) DEFAULT NULL COMMENT '0-新建; 1-订单签收确认; 2-清分',
  `is_sync` int(2) DEFAULT '0' COMMENT '是否同步',
  `order_time` varchar(64) DEFAULT NULL,
  `order_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `receive_time` varchar(64) DEFAULT NULL COMMENT '签收时间',
  `receive_date` datetime DEFAULT NULL COMMENT '签收日期',
  `share_user_id` varchar(64) DEFAULT NULL,
  `is_del` tinyint(1) DEFAULT '0',
  `creator` varchar(32) DEFAULT NULL,
  `modifier` varchar(32) DEFAULT NULL,
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modify` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `mall_sub_order_snapshot` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `company_no` varchar(64) DEFAULT NULL,
  `order_no` varchar(64) DEFAULT NULL,
  `sub_order_no` varchar(64) DEFAULT NULL,
  `item_code` varchar(64) DEFAULT NULL,
  `item_name` varchar(128) DEFAULT NULL,
  `sku_code` varchar(64) DEFAULT NULL,
  `sku_pic` varchar(512) DEFAULT NULL,
  `upc` varchar(64) DEFAULT NULL,
  `scale` varchar(128) DEFAULT NULL COMMENT '规格，用分号隔开',
  `ext` varchar(4096) DEFAULT NULL,
  `sale_price` decimal(16,2) DEFAULT NULL,
  `is_del` tinyint(1) DEFAULT '0',
  `creator` varchar(32) DEFAULT NULL,
  `modifier` varchar(32) DEFAULT NULL,
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modify` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `item_qrcode_share`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gmt_modify` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '操作时间',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT 0,
  `share_no` varchar(64) NOT NULL,
  `company_no` varchar(64) NOT NULL COMMENT '所属公司id',
  `item_code` varchar(255) DEFAULT NULL,
  `user_no` varchar(255) DEFAULT NULL,
  `pic_url` varchar(255) DEFAULT NULL COMMENT '二维码地址',
  `ext` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique_index`(`share_no`) USING BTREE COMMENT '唯一性性索引',
  UNIQUE INDEX `search_index`(`item_code`, `user_no`, `company_no`) USING BTREE COMMENT '加快查询'
) ENGINE = InnoDB AUTO_INCREMENT = 1   ROW_FORMAT = Dynamic;




CREATE TABLE `mall_sale_agent` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_no` varchar(64) NOT NULL COMMENT '所属公司id',
  `user_no` varchar(64) NOT NULL COMMENT '唯一userNo，需要跟Auth_User一致',
  `parent_agent` varchar(64) DEFAULT NULL COMMENT '上级代理的userNo，如已是一级代理该值为null',
  `open_id` varchar(64) DEFAULT NULL COMMENT '微信open_id',
  `union_id` varchar(128) DEFAULT NULL COMMENT '微信union_id',
  `agent_name` varchar(64) DEFAULT NULL COMMENT '代理人的微信号/昵称',
  `real_name` varchar(32) DEFAULT NULL COMMENT '真实姓名',
  `id_card` varchar(32) DEFAULT NULL COMMENT '身份证号号码',
  `head_protrait_url` varchar(256) DEFAULT NULL COMMENT '头像',
  `gender` int(3) DEFAULT NULL COMMENT '0未知,1男,2女',
  `city` varchar(64) DEFAULT NULL,
  `province` varchar(64) DEFAULT NULL,
  `country` varchar(64) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `commission_mode` int(1) DEFAULT '0' COMMENT '佣金模式，0为按百分比，1为按金额',
  `commission_value` double(6,4) DEFAULT '0.0' COMMENT '佣金数字值，百分比模式如5%填0.05，金额模式则为金额',
  `status` int(1) DEFAULT 1 COMMENT '状态，1正常，0已解除',
  `join_time` datetime DEFAULT NULL COMMENT '加入时间',
  `last_login_time` datetime DEFAULT NULL,
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `USER_NO_IDX` (`user_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 COMMENT='销售代理';


CREATE TABLE `monitor_record`(
  `id` BIGINT(64) NOT NULL AUTO_INCREMENT,
  `app_name` VARCHAR(64) COMMENT '应用名称',
  `monitor_url` VARCHAR(256) COMMENT '监控url',
  `status` VARCHAR(2) COMMENT '0-新建，1-生效，2-暂停',
  `is_del` TINYINT(1) DEFAULT 0,
  `creator` VARCHAR(32),
  `modifier` VARCHAR(32),
  `gmt_create` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `gmt_modify` DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=INNODB CHARSET=utf8;


CREATE TABLE `applet_config` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `company_no` varchar(64) NOT NULL COMMENT '公司代号',
  `secret` varchar(64) DEFAULT NULL COMMENT '小程序secret',
  `appid` varchar(64) NULL COMMENT '小程序appid',
  `applet_type` varchar(5) NOT NULL COMMENT '小程序的类型  1: 采购 2.商城',
  `mch_id` varchar(64) DEFAULT NULL COMMENT '商户号(微信支付用)',
  `status` varchar(5) NULL DEFAULT '1' COMMENT '微信支付接入类型 1.服务商版 2.商户版 ',
  `pay_key` varchar(64) DEFAULT NULL COMMENT '商户版的支付秘钥',
  `authorizer_refresh_token` varchar(64) DEFAULT NULL COMMENT '第三方授权平台刷新token',
  `authorizer_access_token` varchar(512) DEFAULT NULL COMMENT '第三方授权平台token',
  `publish_status` int(11) DEFAULT NULL COMMENT '小程序发布状态：1.已授权 2.已提交体验版 3.待审核 4.审核通过待发布 5.已发布',
  `templet_id` int(11) DEFAULT NULL COMMENT '小程序模板id',
  `img_url` varchar(64) DEFAULT NULL COMMENT '体验版二维码',
  `audit_id` varchar(64) DEFAULT NULL COMMENT ' 微信审核的id   用于查询审核状态等api',
  `ext_json` varchar(1024) DEFAULT NULL COMMENT '小程序的ext.json文件',
  `requestdomain` VARCHAR(256) NULL COMMENT 'request合法域名',
  `wsrequestdomain` VARCHAR(256) NULL COMMENT 'socket合法域名',
  `uploaddomain` VARCHAR(256) NULL COMMENT 'uploadFile合法域名',
  `downloaddomain` VARCHAR(256) NULL COMMENT 'downloadFile合法域名',
  `webviewdomain` VARCHAR(256) NULL COMMENT '业务域名',
  `is_del` tinyint(1) DEFAULT '0',
  `creator` varchar(32) DEFAULT 'system',
  `modifier` varchar(32) DEFAULT 'system',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modify` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `applet_config_appid_uindex` (`appid`),
  UNIQUE KEY `company_no` (`company_no`,`applet_type`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;


CREATE TABLE wx_user
(
  id                 BIGINT AUTO_INCREMENT
    PRIMARY KEY,
  open_id            VARCHAR(64)                        NULL
  COMMENT '微信open_id',
  union_id           VARCHAR(128)                       NULL
  COMMENT '微信union_id',
  nick_name          VARCHAR(64)                        NULL,
  gender             INT(3)                             NULL
  COMMENT '0未知,1男,2女',
  city               VARCHAR(64)                        NULL,
  province           VARCHAR(64)                        NULL,
  country            VARCHAR(64)                        NULL,
  avatar_url         VARCHAR(256)                       NULL,
  referer            VARCHAR(64)                        NULL
  COMMENT '用户来源',
  first_login_time   DATETIME                           NULL,
  last_login_time    DATETIME                           NULL,
  first_login_device BIGINT                             NULL,
  last_login_device  BIGINT                             NULL,
  gmt_create         DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  gmt_modify         DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  is_del             TINYINT(1) DEFAULT '0'             NOT NULL,
  creator            VARCHAR(32)                        NOT NULL,
  modifier           VARCHAR(32)                        NOT NULL,
  CONSTRAINT OPENID
  UNIQUE (open_id)
);


-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 新增share_token_record表-- -- -- -- -- -- -- -- --
 CREATE TABLE `share_token_record` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `company_no` varchar(64) DEFAULT NULL COMMENT '公司名称',
  `item_code` varchar(64) DEFAULT NULL COMMENT '商品代码',
  `token` varchar(64) DEFAULT NULL COMMENT '商品分享快照token',
  `seq` bigint(64) DEFAULT NULL COMMENT '分享层级',
  `user_wx` varchar(64) DEFAULT NULL COMMENT '用户微信ID(可能未注册)',
  `user_no` varchar(64) DEFAULT NULL COMMENT '用户ID(注册用户标识)',
  `parent_user_no` varchar(64) DEFAULT NULL COMMENT '分销上级用户名',
  `parent_user_wx` varchar(64) DEFAULT NULL COMMENT '分销上级用户微信id',
  `share_token` varchar(2048) DEFAULT NULL COMMENT '分享分销合成token',
  `is_del` tinyint(1) DEFAULT '0',
  `creator` varchar(32) DEFAULT 'system',
  `modifier` varchar(32) DEFAULT 'system',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modify` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;


CREATE TABLE `db_migrate_receive_record` (
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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `db_migrate_send_record` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `token` varchar(64) DEFAULT NULL,
  `db_script` varchar(6144)  DEFAULT NULL,
  `retry_times` int(2) DEFAULT '0',
  `status` varchar(2) DEFAULT '0' COMMENT '0 新增 1 成功 2 失败',
  `gmt_modify` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `modifier` varchar(32) DEFAULT 'sys',
  `creator` varchar(32) DEFAULT 'sys',
  `is_del` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


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
) ENGINE=InnoDB AUTO_INCREMENT=385 COMMENT='品牌';
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
  `dealer_name` VARCHAR(64) NULL COMMENT '销售人员名称',
  `union_id` VARCHAR(64) NULL COMMENT '微信unionId',
  `open_id` VARCHAR(64) COMMENT '微信open_id',
  `order_no` varchar(64) NOT NULL COMMENT '订单编号',
  `company_no` varchar(64) NOT NULL COMMENT '公司编号',
  `channel_no` varchar(64) DEFAULT '0' COMMENT '渠道编号',
  `channel_name` varchar(64) DEFAULT '管理平台' COMMENT '销售名称',
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
  `is_show` TINYINT(1) DEFAULT 1 NULL COMMENT '是否显示',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ORDERNO` (`order_no`)
) ENGINE=InnoDB AUTO_INCREMENT=165 COMMENT='顾客的订单（在微信小程序或者第三方销售平台）';
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
) ENGINE=InnoDB AUTO_INCREMENT=130 COMMENT='渠道上的商品信息';
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
) ENGINE=InnoDB COMMENT='微信小程序商品快照';
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
  `item_name` varchar(128) DEFAULT NULL COMMENT '商品名称',
  `scale` varchar(64) DEFAULT NULL COMMENT '尺寸',
  `quantity` int(11) DEFAULT NULL COMMENT '该商品在购物车的数量',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '购物车状态',
  `share_token` VARCHAR(2048) NULL   COMMENT '分享分销token',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `OPENID` (`open_id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 COMMENT='顾客在商城的购物车（仅限微信小程序）';
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
  `errorMsg` varchar(1024) COMMENT '错误信息',
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
  `open_id` varchar(64) DEFAULT NULL COMMENT '买手ID,根据openID，与buyer关联',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `receipt_no` (`receipt_no`)
) ENGINE=InnoDB COMMENT='采购的发票，一个发票可以对应好几种的商品';
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
) ENGINE=InnoDB AUTO_INCREMENT=25 COMMENT='用户站内信';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `channel_sale_price`
--

DROP TABLE IF EXISTS `channel_sale_price`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `channel_sale_price` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  `channel_no` varchar(64) NOT NULL DEFAULT '',
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
) ENGINE=InnoDB COMMENT='每个公司的每个商品在每个渠道的每个店铺的销售价格';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `buyer_task_detail`
--

DROP TABLE IF EXISTS `buyer_task_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `buyer_task_detail` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  `buyer_task_detail_no` VARCHAR(64) NULL DEFAULT NULL COMMENT '采购明细的编码，和小票明细有关联',
  `buyer_task_no` varchar(64) DEFAULT NULL,
  `company_no` varchar(64) NOT NULL,
  `item_code` varchar(64) DEFAULT NULL,
  `upc` varchar(64) DEFAULT NULL,
  `price` float DEFAULT NULL COMMENT '采购价',
  `max_price` float DEFAULT NULL,
  `count` int(10) DEFAULT NULL COMMENT '商品采购数量',
  `owner_name` varchar(64) DEFAULT NULL COMMENT '分配人名称',
  `owner_no` varchar(64) DEFAULT NULL COMMENT '分配人ID',
  `currency` tinyint(2) DEFAULT NULL COMMENT '0 美元 1人民币',
  `buyer_name` varchar(64) DEFAULT NULL COMMENT '买手名称',
  `buyer_open_id` varchar(64) NULL DEFAULT NULL COMMENT '买手微信ID',
  `status` int(11) DEFAULT '0' COMMENT '-1为已取消，0为待采购，2为采购中，1为采购结束',
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `mode` tinyint(2) DEFAULT '1' COMMENT '采购方式 0 线上 1线下',
  `sku_code` varchar(64) DEFAULT NULL COMMENT 'sku代码',
  `remark`  varchar(200) DEFAULT NULL COMMENT '说明',
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
) ENGINE=InnoDB AUTO_INCREMENT=10 COMMENT='采购任务的详情，一种商品对应一条记录';
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
) ENGINE=InnoDB COMMENT='平台的类目与转运公司类目的对应';
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
) ENGINE=InnoDB AUTO_INCREMENT=10 COMMENT='站内信';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `shipping_track_polling_4px`
--

DROP TABLE IF EXISTS `shipping_track_polling_4px`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shipping_track_polling_4px` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `delivery_no` varchar(64) NOT NULL COMMENT '物流单号',
  `shipping_no` varchar(64) NOT NULL COMMENT '商家订单号',
  `is_normal` int(1) DEFAULT NULL COMMENT '包裹是否正常',
  `track_info` varchar(4096) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '轨迹详情',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 COMMENT='四方物流记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `buyer_entry_manifest`
--

DROP TABLE IF EXISTS `buyer_entry_manifest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `buyer_entry_manifest` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `company_no`  varchar(64) NULL,
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
) ENGINE=InnoDB COMMENT='采购入库单详情原始备份表';
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
  `company_group` VARCHAR(64) NULL COMMENT '归属公司。如与companyNo相同，则表示当前为一个公司，如不同，表示当前的companyNo对应company为companyGroup下面的一个商户',
  `company_name` varchar(64) DEFAULT NULL COMMENT '公司名称',
  `admin_no` varchar(64) NOT NULL COMMENT '管理员ID',
  `status` int(4) DEFAULT '0' COMMENT '状态 0:正常，1:关闭',
  `shop_name` varchar(45) DEFAULT NULL COMMENT '公司的店铺名称',
  `logo_url` varchar(256) DEFAULT NULL,
  `intro` text COMMENT '介绍',
  `force_idcard` int(2) NOT NULL DEFAULT '1' COMMENT '身份证号，默认需要',
  `tel` varchar(45) NOT NULL,
  `im` varchar(45) NOT NULL COMMENT '及时通讯工具，如微信',
  `service_time` varchar(128) DEFAULT NULL COMMENT '服务时间',
  `force_idcard_upload` int(2) NOT NULL DEFAULT '0' COMMENT '身份证图片，默认不需要',
  `state` varchar(20) DEFAULT NULL COMMENT '省',
  `city` varchar(20) DEFAULT NULL COMMENT '市',
  `district` varchar(20) DEFAULT NULL COMMENT '区',
  `full_address` varchar(100) DEFAULT NULL COMMENT '详细地址',
  `oversea_address` varchar(100) DEFAULT NULL COMMENT '海外地址',
  `country` int(2) COMMENT '国家代码',
  `main_category` varchar(20) DEFAULT NULL COMMENT '主要品类',
  `offline_annual_sale` double(10,2) DEFAULT NULL COMMENT '线下年销售额',
  `online_annual_sale` double(10,2) DEFAULT NULL COMMENT '线上年销售额',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `company_no` (`company_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 COMMENT='买手公司';
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
) ENGINE=InnoDB AUTO_INCREMENT=40 COMMENT='打包规格';
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
  `share_user_id` VARCHAR(64) NULL   COMMENT '分享人' ,
  `share_token` VARCHAR(2048) NULL   COMMENT '分销token' ,
  `share_time` VARCHAR(16) NULL   COMMENT '分享统计维度日期' ,
  `share_close_flag` VARCHAR(1) DEFAULT '0'  NULL   COMMENT '分享分销结算标识' ,
  `share_close_time` VARCHAR(16) NULL   COMMENT '分享分销结算时间' ,
  `share_money` NUMERIC(16,4) NULL   COMMENT '分享获得的佣金',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `SUBORDERNO` (`sub_order_no`)
) ENGINE=InnoDB AUTO_INCREMENT=166 COMMENT='商城子订单';
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
  `remark` VARCHAR(256) COMMENT '备注',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='出库单';
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
  `nick_name` varchar(64) DEFAULT NULL,
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
  UNIQUE INDEX `OPENID`(`open_id`, `union_id`, `is_del`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 COMMENT='买手';
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
  `logistic_type` varchar(45) DEFAULT NULL COMMENT '物流类型',
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
  `memo` varchar(1024) DEFAULT NULL COMMENT '备注',
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
) ENGINE=InnoDB COMMENT='物流订单';
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
) ENGINE=InnoDB AUTO_INCREMENT=419 ROW_FORMAT=DYNAMIC COMMENT='平台的商品类目';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mall_return_order`
--

DROP TABLE IF EXISTS `mall_return_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mall_return_order` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `company_no` VARCHAR(64) NOT NULL COMMENT '公司编号',
  `order_no` varchar(64) DEFAULT NULL COMMENT '主订单号',
  `outer_order_no` bigint(20) DEFAULT NULL,
  `sub_order_no` varchar(64) DEFAULT NULL COMMENT '子订单号',
  `mall_return_order_no` VARCHAR(64) NOT NULL COMMENT '退单号',
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
) ENGINE=InnoDB AUTO_INCREMENT=133 COMMENT='顾客在商城的退货单';
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
) ENGINE=InnoDB AUTO_INCREMENT=42 COMMENT='角色';
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
) ENGINE=InnoDB COMMENT='库存备货记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `item_sku`
--

DROP TABLE IF EXISTS `item_sku`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE IF NOT EXISTS `item_sku` (
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
  `status` int(4) NOT NULL DEFAULT '1' COMMENT '0:未审核,1:审核通过',
  `sale_type` tinyint(1) DEFAULT NULL COMMENT '销售类型:现货,代购',
  `sale_price` double(10,2) DEFAULT NULL COMMENT '销售价',
  `sku_rate` double(10,4) NOT NULL DEFAULT '0.00' COMMENT '代理佣金比例',
  `goods_no` varchar(64) DEFAULT NULL COMMENT '货号',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `SKUCODE` (`sku_code`),
  KEY `ITEMCODE` (`item_code`),
  KEY `GMTCREATE` (`gmt_create`)
) ENGINE=InnoDB AUTO_INCREMENT=4877 COMMENT='商品sku';

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
CREATE TABLE IF NOT EXISTS `item` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `item_code` varchar(64) NOT NULL DEFAULT '0' COMMENT '商品编码',
  `company_no` varchar(64) NOT NULL COMMENT '所属公司编号',
  `category_code` varchar(10) NOT NULL COMMENT '所属类目code',
  `qr_code_url` varchar(2000) DEFAULT NULL COMMENT '商品在小程序中的二维码',
  `remark` text COMMENT '商品描述信息',
  `video` varchar(1024) DEFAULT NULL COMMENT '商品展示视频',
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
  `status` int(4) DEFAULT '0' COMMENT '0新档 1上架 2下架 -1删除',
  `spec` varchar(64) DEFAULT NULL COMMENT '规格',
  `model` varchar(64) DEFAULT NULL COMMENT '型号',
  `detail` text COMMENT '商品详情',
  `buyer_open_id` varchar(128) DEFAULT NULL COMMENT '买手open_id，可以有多个',
  `origin_sale_price` varchar(64) DEFAULT NULL COMMENT '原始销售价格',
  `commission_mode` varchar(64) DEFAULT NULL COMMENT '佣金模式',
  `commission_rate` varchar(64) DEFAULT NULL COMMENT '佣金比率',
  `is_abroad` int(4) NOT NULL DEFAULT '1' COMMENT '0:国内,1:海外，2：保税仓',
  `shelf_method` int(4) NOT NULL DEFAULT '0' COMMENT '0:立即售卖,1:暂不售卖;2:自定义',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ITEMCODE` (`item_code`)
) ENGINE=InnoDB AUTO_INCREMENT=10311 COMMENT='商品';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jd_shop_config`
--

DROP TABLE IF EXISTS `jd_shop_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jd_shop_config` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `version` bigint(19),
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
  `sale_level` varchar(45) DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `discount1` double DEFAULT NULL,
  `discount2` double DEFAULT NULL,
  `discount3` double DEFAULT NULL,
  `remark` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `channel_no` (`channel_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 COLLATE=utf8_unicode_ci COMMENT='渠道';
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
  PRIMARY KEY (`id`),
  INDEX `skucode_index` (`sku_code`) USING BTREE COMMENT 'skucode查询比较频繁'
) ENGINE=InnoDB AUTO_INCREMENT=3 COMMENT='商品对应的规格';
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
) ENGINE=InnoDB COMMENT='顾客在微信小程序商城的付款单';
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
  `nick_name` varchar(64) DEFAULT NULL,
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
  UNIQUE KEY `CUSTOMER_NO` (`customer_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 COMMENT='商城的顾客（包括微信小程序和第三方销售平台）';
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
) ENGINE=InnoDB AUTO_INCREMENT=2742 COMMENT='角色资源';
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
) ENGINE=InnoDB COMMENT='react版本信息';
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
) ENGINE=InnoDB AUTO_INCREMENT=57 COMMENT='经销商';
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
) ENGINE=InnoDB AUTO_INCREMENT=450 ROW_FORMAT=DYNAMIC COMMENT='尺寸';
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
  `inventory_on_warehouse_no` VARCHAR(64) NULL COMMENT '仓库库存号',
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
) ENGINE=InnoDB AUTO_INCREMENT=137 COMMENT='库存出入库流水';
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
) ENGINE=InnoDB AUTO_INCREMENT=1093 COMMENT='商城顾客的分享情况';
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
) ENGINE=InnoDB COMMENT='渠道商品的SKU信息';
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
) ENGINE=InnoDB AUTO_INCREMENT=12 COMMENT='总的库存记录';
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
  `address` varchar(256) DEFAULT NULL,
  `tel` varchar(32) DEFAULT NULL,
  `contact_person` varchar(32) NOT NULL,
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `warehouse_no` (`warehouse_no`)
) ENGINE=InnoDB AUTO_INCREMENT=139 COMMENT='仓库';
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
) ENGINE=InnoDB AUTO_INCREMENT=2 COLLATE=utf8_unicode_ci COMMENT='商城的活动页面信息';
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
  `entry_quantity` int(11) DEFAULT 0 COMMENT '实际入库数',
  `purchase_storage_no` varchar(64) DEFAULT NULL COMMENT '采购入库单号',
  `buyer_task_detail_no` varchar(64) DEFAULT NULL COMMENT '采购子任务号',
  `item_code`  varchar(64) NULL DEFAULT NULL COMMENT '入库单号',
  `status`  int(4) NULL,
  `mem`  varchar(1024) NULL,
  `op_user_no`  varchar(64) NULL,
  `op_time`  datetime NULL,
  `sku_buysite` varchar(64) DEFAULT NULL COMMENT '订购站点',
  `batch_num` int(4) COMMENT '批次号',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 COMMENT='买手采购入库详情表';
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
) ENGINE=InnoDB AUTO_INCREMENT=10 COMMENT='转运公司';
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
  `comment_pic` varchar(1024) DEFAULT NULL COMMENT '评价的图片',
  `content` varchar(500) NOT NULL COMMENT '评价内容',
  `status` tinyint(2) DEFAULT NULL,
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`) USING BTREE
) ENGINE=InnoDB COMMENT='对商品的评价';
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
  UNIQUE KEY `com_user_role` (`company_no`,`user_id`,`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=538 COMMENT='用户角色';
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
) ENGINE=InnoDB COMMENT='出库单详情';
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
  `org_id` varchar(64) ,
  `name` varchar(64) NOT NULL COMMENT '组织名',
  `address` varchar(200) DEFAULT NULL COMMENT '地址',
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
) ENGINE=InnoDB AUTO_INCREMENT=50 COMMENT='组织机构';
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
) ENGINE=InnoDB AUTO_INCREMENT=136 COMMENT='仓库库存';
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
  `sku_code` varchar(64) DEFAULT NULL,
  `is_new` tinyint(3) DEFAULT '0' COMMENT '是否新品',
  `sale_type` tinyint(3) DEFAULT '0' COMMENT '销售类型',
  `currency` tinyint(3) DEFAULT NULL COMMENT '币种',
  `brand_no` VARCHAR(16) NULL COMMENT '品牌编码',
  `brand_name` VARCHAR(64) NULL COMMENT '品牌名字',
  `main_pic` varchar(2048) COMMENT '主图',
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
  `desc_msg` varchar(500) DEFAULT NULL COMMENT '商品描述信息',
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
) ENGINE=InnoDB AUTO_INCREMENT=4 COMMENT='商品';
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
) ENGINE=InnoDB AUTO_INCREMENT=329 COMMENT='资源';
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
  `batch_num` int(4) COMMENT '批次号',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='采购发票详情，发票里面的每一种商品对应一条记录';
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
) ENGINE=InnoDB AUTO_INCREMENT=355 ROW_FORMAT=DYNAMIC COMMENT='尺寸类型';
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
  `company_no` VARCHAR(64) NOT NULL COMMENT '商户号',
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
) ENGINE=InnoDB AUTO_INCREMENT=14 COMMENT='顾客收件地址';
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
  `company_no` varchar(64) NOT NULL,
  `remark` varchar(64) DEFAULT NULL COMMENT '备注',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `CODE` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=136 COMMENT='经销商类型';
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
) ENGINE=InnoDB AUTO_INCREMENT=380 COMMENT='国家';
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
) ENGINE=InnoDB AUTO_INCREMENT=3 COMMENT='规格和类目的映射表';
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
  `channel_name` varchar(16) DEFAULT NULL COMMENT '渠道名称',
  `company_no` varchar(64) NOT NULL COMMENT '渠道账号所属买手公司id',
  `shop_code` varchar(255) NOT NULL COMMENT '迁移数据的时候，把appvalue1迁移过来',
  `shop_name` varchar(1024) DEFAULT NULL,
  `status` int(4) NOT NULL DEFAULT '0' COMMENT '状态 0:正常，1:关闭',
  `app_key` varchar(64) DEFAULT NULL COMMENT '渠道品台分配给买手公司的账号appkey/clientId等',
  `app_secret` varchar(64) DEFAULT NULL COMMENT '渠道品台分配给买手公司的账号secret',
  `access_token` varchar(64) DEFAULT NULL COMMENT '授权后，获取到的token',
  `refresh_token` varchar(255) DEFAULT NULL COMMENT '刷新access_token的token',
  `server_url` varchar(255) DEFAULT NULL COMMENT '平台地址',
  `token_url` varchar(255) DEFAULT NULL COMMENT '获取token的URL',
  `access_key` varchar(255) DEFAULT NULL COMMENT '接入码',
  `secrete_key` varchar(255) DEFAULT NULL COMMENT '授权密码',
  `cookie` varchar(4096) DEFAULT NULL COMMENT '淘宝cookie，较长',
  `app_value1` varchar(64) DEFAULT NULL COMMENT '渠道品台分配给买手公司的其他值value1',
  `value1_desc` varchar(128) DEFAULT NULL COMMENT 'app_value1描述',
  `app_value2` varchar(64) DEFAULT NULL COMMENT '渠道品台分配给买手公司的其他值value1',
  `value2_desc` varchar(128) DEFAULT NULL COMMENT 'app_value2描述',
  `app_value3` varchar(64) DEFAULT NULL COMMENT '渠道品台分配给买手公司的其他值value1',
  `value3_desc` varchar(128) DEFAULT NULL COMMENT 'app_value3描述',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `company_n` (`company_no`,`channel_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=25 COMMENT='买手公司渠道账号';
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
) ENGINE=InnoDB AUTO_INCREMENT=40 COMMENT='打包尺寸';
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
  `task_desc` varchar(255) COMMENT '任务描述',
  `image_url` varchar(2048) COMMENT '任务主图，item_find主图',
  `remark` varchar(256) DEFAULT NULL COMMENT '任务备注',
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '-1为已取消，0为待采购，2为采购中，1为采购结束',
  `buyer_open_id` varchar(64) DEFAULT NULL,
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
) ENGINE=InnoDB COMMENT='采购任务，一个任务可以含有多种商品';
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
  `wx_user_id` varchar(64) DEFAULT NULL COMMENT '微信用户ID',
  `wx_open_id` varchar(64) DEFAULT NULL COMMENT '微信openID',
  `wx_union_id` varchar(64) DEFAULT NULL COMMENT '微信unionID',
  `email` varchar(32) DEFAULT NULL COMMENT '电子邮箱',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_name` (`login_name`),
  UNIQUE KEY `user_no` (`user_no`)
) ENGINE=InnoDB AUTO_INCREMENT=20 COMMENT='用户';
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
) ENGINE=InnoDB AUTO_INCREMENT=6 ROW_FORMAT=DYNAMIC;
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
  `buyer_open_id` varchar(64) DEFAULT NULL COMMENT '买手ID',
  `purchase_storage_no` varchar(64) NOT NULL COMMENT '入库单号',
  `buyer_task_no` varchar(64) DEFAULT NULL COMMENT '采购任务编号',
  `storage_type` tinyint(4) DEFAULT '0' COMMENT '入库方式：0计划采购入库，1扫描入库',
  `entry_date` datetime DEFAULT NULL COMMENT '入库时间',
  `status`  int(4) NULL,
  `memo` varchar(256) DEFAULT NULL COMMENT '备注',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) NOT NULL,
  `creator` varchar(32) NOT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `storage_no` (`storage_no`)
) ENGINE=InnoDB COMMENT='买手采购入库表';
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
) ENGINE=InnoDB AUTO_INCREMENT=2606 COMMENT='系统日志';
/*!40101 SET character_set_client = @saved_cs_client */;


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
