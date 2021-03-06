package com.wangqin.globalshop.biz1.app.bean.dataVo;

public class ChannelSalePriceVO  {
	
	private Integer skuIndex;
	
	private String channelName;
	
    private Long id;

    private String channelNo;

    private String companyNo;

    private Long shopCode;

    private Float salePrice;

    private String skuCode;

    private String itemCode;

    private String batchNo;

    private String creator;

    private String modifier;

    private String channelSkuKey; //skuId+channelNo
	public String getChannelSkuKey() {
		return channelSkuKey;
	}
	public void setChannelSkuKey(String channelSkuKey) {
		this.channelSkuKey = channelSkuKey;
	}
	public Integer getSkuIndex() {
		return skuIndex;
	}

	public void setSkuIndex(Integer skuIndex) {
		this.skuIndex = skuIndex;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo == null ? null : channelNo.trim();
    }

    public String getCompanyNo() {
        return companyNo;
    }

   
    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo == null ? null : companyNo.trim();
    }

    public Long getShopCode() {
        return shopCode;
    }

    public void setShopCode(Long shopCode) {
        this.shopCode = shopCode;
    }

    public Float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Float salePrice) {
        this.salePrice = salePrice;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode == null ? null : skuCode.trim();
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode == null ? null : itemCode.trim();
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo == null ? null : batchNo.trim();
    }

    public String getCreator() {
        return creator;
    }

   
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getModifier() {
        return modifier;
    }

   
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }
}
