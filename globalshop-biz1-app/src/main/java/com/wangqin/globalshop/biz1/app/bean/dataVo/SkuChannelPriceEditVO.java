package com.wangqin.globalshop.biz1.app.bean.dataVo;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelSalePriceDO;

import java.io.Serializable;
import java.util.List;


/**
 * 商品SKU 多渠道销售价相关
 *
 *
 */
public class SkuChannelPriceEditVO implements Serializable {

	
//	private String itemName;

	private String itemCode;

	private String skuCode;
	
//	private String upc;
//
//	private Double salePrice;
//
//	private String skuPic;  //sku图片
//
//	private Integer saleType;
//
//	private Integer saleMode;

	//多渠道销售价相关
	private List<ChannelSalePriceDO> channelSalePriceList;



	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}


	public List<ChannelSalePriceDO> getChannelSalePriceList() {
		return channelSalePriceList;
	}

	public void setChannelSalePriceList(List<ChannelSalePriceDO> channelSalePriceList) {
		this.channelSalePriceList = channelSalePriceList;
	}
}
