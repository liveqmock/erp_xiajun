package com.wangqin.globalshop.channel.service.intramirror;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.type.TypeReference;
import com.wangqin.globalshop.common.base.BaseDto;
import com.wangqin.globalshop.common.utils.EasyUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Create by 777 on 2018/8/27
 */
public class IMProduct {

	private String product_id;//tbspuid
	private String designer_id;//upc
	private String colorcode;//颜色编码
	private String color_description;//颜色描述
	private String brand; //英文品牌名
	private String season_code;
	private String carry_over;
	private String category_l1;
	private Integer category_l1_id;
	private String category_l2;
	private Integer category_l2_id;
	private String category_l3;
	private Integer category_l3_id;

	private String product_name;//itemName
	private String product_description;//itemDetail
	private List<String> cover_img;//图片地址列表
	private List<String> description_img;//图片列表描述
	private List<IMSku> sku;//子品
	private String updated_at;


	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getDesigner_id() {
		return designer_id;
	}
	public void setDesigner_id(String designer_id) {
		this.designer_id = designer_id;
	}
	public String getColorcode() {
		return colorcode;
	}
	public void setColorcode(String colorcode) {
		this.colorcode = colorcode;
	}
	public String getColor_description() {
		return color_description;
	}
	public void setColor_description(String color_description) {
		this.color_description = color_description;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getSeason_code() {
		return season_code;
	}
	public void setSeason_code(String season_code) {
		this.season_code = season_code;
	}
	public String getCarry_over() {
		return carry_over;
	}
	public void setCarry_over(String carry_over) {
		this.carry_over = carry_over;
	}
	public String getCategory_l1() {
		return category_l1;
	}
	public void setCategory_l1(String category_l1) {
		this.category_l1 = category_l1;
	}
	public Integer getCategory_l1_id() {
		return category_l1_id;
	}
	public void setCategory_l1_id(Integer category_l1_id) {
		this.category_l1_id = category_l1_id;
	}
	public String getCategory_l2() {
		return category_l2;
	}
	public void setCategory_l2(String category_l2) {
		this.category_l2 = category_l2;
	}
	public Integer getCategory_l2_id() {
		return category_l2_id;
	}
	public void setCategory_l2_id(Integer category_l2_id) {
		this.category_l2_id = category_l2_id;
	}
	public String getCategory_l3() {
		return category_l3;
	}
	public void setCategory_l3(String category_l3) {
		this.category_l3 = category_l3;
	}
	public Integer getCategory_l3_id() {
		return category_l3_id;
	}
	public void setCategory_l3_id(Integer category_l3_id) {
		this.category_l3_id = category_l3_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_description() {
		return product_description;
	}
	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}


	public List<String> getDescription_img() {
		return description_img;
	}
	public void setDescription_img(List<String> description_img) {
		this.description_img = description_img;
	}
	public List<IMSku> getSku() {
		return sku;
	}
	public void setSku(List<IMSku> sku) {
		this.sku = sku;
	}
	public String getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
	public List<String> getCover_img() {
		return cover_img;
	}
	public void setCover_img(List<String> cover_img) {
		this.cover_img = cover_img;
	}
}
