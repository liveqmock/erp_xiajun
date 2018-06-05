package com.wangqin.globalshop.channel.dal.youzan;

import com.fasterxml.jackson.annotation.JsonProperty;

public class YouZanSku {
	@JsonProperty(value = "skus")
	private Skus skus  = new Skus();
	@JsonProperty(value = "price")
	private Double price;
	@JsonProperty(value = "quantity")
	private Integer quantity;
	@JsonProperty(value = "item_no")
	private String itemNo;

	public Skus getSkus() {
		return skus;
	}

	public void setSkus(Skus skus) {
		this.skus = skus;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}


	public static class Skus {
		@JsonProperty(value = "颜色")
		private String color;
		@JsonProperty(value = "尺寸")
		private String scale;
		public String getColor() {
			return color;
		}
		public void setColor(String color) {
			this.color = color;
		}
		public String getScale() {
			return scale;
		}
		public void setScale(String scale) {
			this.scale = scale;
		}
		  
	  }
}
