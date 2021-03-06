
###初始化intraMirror店铺,请确认商户companyNo:  yJlyUZ1gt5  E群大叔奢侈品直购平台
INSERT INTO `haidb2new`.`channel_shop`( `version`, `channel_no`, `company_no`, `shop_name`, `shop_code`, `expires_time`, `proxy_url`, `shop_type`, `open`, `gmt_modify`, `gmt_create`, `modifier`, `creator`, `is_del`)
VALUES ( 0, '5', 'yJlyUZ1gt5', 'IntraMirror', 'IM001', '2019-08-27 15:59:51', 'http://channel.intramirror.com/auth/token?buyer_id=EQunDaShu20180813&secret_key=324j89fgkljynbxe12&version=1.0', NULL, 1, NOW(), NOW(), '-1', '-1', 0);


####初始化intraMirror授权,请确认商户companyNo:  yJlyUZ1gt5  E群大叔奢侈品直购平台
INSERT INTO `haidb2new`.`jd_shop_oauth`( `version`, `channel_no`, `company_no`, `shop_code`, `access_token`, `expires_time`, `refresh_token`, `refresh_exprires_time`, `app_key`, `appsecret_key`, `access_key`, `access_value`, `server_url`, `token_url`, `shop_type`, `open`, `gmt_modify`, `gmt_create`, `modifier`, `creator`, `is_del`)
VALUES ( 0, '5', 'yJlyUZ1gt5', 'IM001', 'vdjguewjdi2ngksalw323et5ingsdlw12', '2019-08-27 15:59:51', NULL, NULL, 'EQunDaShu20180813', '324j89fgkljynbxe12', NULL, NULL, '', 'http://channel.intramirror.com/auth/token', NULL, 1, NOW(),  NOW(), '-1', '-1', 0);

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



###增加支付单相关字段
ALTER TABLE `haidb2new`.`mall_wx_pay_bill`
ADD COLUMN `appid` varchar(1024) COMMENT '网擒的公众账号';
ALTER TABLE `haidb2new`.`mall_wx_pay_bill`
ADD COLUMN `mch_id` varchar(1024) COMMENT '商户号';
ALTER TABLE `haidb2new`.`mall_wx_pay_bill`
ADD COLUMN `out_trade_no` varchar(1024) COMMENT '商户订单号';
ALTER TABLE `haidb2new`.`mall_wx_pay_bill`
ADD COLUMN `transaction_id` varchar(1024) COMMENT '交易单号';
ALTER TABLE `haidb2new`.`mall_wx_pay_bill`
ADD COLUMN `sign` varchar(1024) COMMENT '签名串，可能后续去掉';
ALTER TABLE `haidb2new`.`mall_wx_pay_bill`
ADD COLUMN `nonce_str` varchar(1024) COMMENT '随机串，可能后续去掉';

  `appid` varchar(32) DEFAULT NULL,
  `mch_id` varchar(32) DEFAULT NULL,
  `out_trade_no` varchar(64) DEFAULT NULL,
  `transaction_id` varchar(64) DEFAULT NULL,
  `sign` varchar(64) DEFAULT NULL,
  `nonce_str` varchar(64) DEFAULT NULL,

###外部item类目记录
ALTER TABLE `haidb2new`.`channel_listing_item`
ADD COLUMN `category_json` varchar(1024) COMMENT '类目json字符串';

###新增字段，用来下单
ALTER TABLE `haidb2new`.`channel_listing_item_sku`
ADD COLUMN `shop_product_sku_id` int(0) COMMENT 'intraMirror用来下单';
