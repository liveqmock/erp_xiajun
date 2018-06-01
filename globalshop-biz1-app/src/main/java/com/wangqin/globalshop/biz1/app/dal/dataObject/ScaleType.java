package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;

/**
 * 
 * @author zhulu
 *
 */
public class ScaleType {
	@TableId(type = IdType.AUTO)
	private Long id;//类型id
	private String type;//名称
	@TableField(exist = false)
	private List<Scale> scaleList = new ArrayList<>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Scale> getScaleList() {
		return scaleList;
	}
	public void setScaleList(List<Scale> scaleList) {
		this.scaleList = scaleList;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
