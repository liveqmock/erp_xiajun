package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 转运公司
 *
 */
@TableName("logistic_company")
public class LogisticCompany implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/**  */
	@TableId(type = IdType.AUTO)
	private Long id;

	/**  */
	private String name;

	/**  */
	private String code;

	/**  */
	@TableField(value = "name_in_jd")
	private String nameInJd;

	/** 物流公司在京东的编码 */
	@TableField(value = "code_in_jd")
	private String codeInJd;

	/**  */
	@TableField(value = "name_in_youzan")
	private String nameInYouzan;

	/**  */
	@TableField(value = "code_in_youzan")
	private String codeInYouzan;

	/**  */
	@TableField(value = "name_in_pdd")
	private String nameInPdd;

	/**  */
	@TableField(value = "code_in_pdd")
	private String codeInPdd;

	/**  */
	@TableField(value = "name_in_taobao")
	private String nameInTaobao;

	/**  */
	@TableField(value = "code_in_taobao")
	private String codeInTaobao;

	/**  */
	@TableField(value = "is_del")
	private Integer isDel;

	/**  */
	@TableField(value = "gmt_create")
	private Date gmtCreate;

	/**  */
	@TableField(value = "gmt_modify")
	private Date gmtModify;

	/**  */
	private String creator;

	/**  */
	private String modifier;


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNameInJd() {
		return this.nameInJd;
	}

	public void setNameInJd(String nameInJd) {
		this.nameInJd = nameInJd;
	}

	public String getCodeInJd() {
		return this.codeInJd;
	}

	public void setCodeInJd(String codeInJd) {
		this.codeInJd = codeInJd;
	}

	public String getNameInYouzan() {
		return this.nameInYouzan;
	}

	public void setNameInYouzan(String nameInYouzan) {
		this.nameInYouzan = nameInYouzan;
	}

	public String getCodeInYouzan() {
		return this.codeInYouzan;
	}

	public void setCodeInYouzan(String codeInYouzan) {
		this.codeInYouzan = codeInYouzan;
	}

	public String getNameInPdd() {
		return this.nameInPdd;
	}

	public void setNameInPdd(String nameInPdd) {
		this.nameInPdd = nameInPdd;
	}

	public String getCodeInPdd() {
		return this.codeInPdd;
	}

	public void setCodeInPdd(String codeInPdd) {
		this.codeInPdd = codeInPdd;
	}

	public String getNameInTaobao() {
		return this.nameInTaobao;
	}

	public void setNameInTaobao(String nameInTaobao) {
		this.nameInTaobao = nameInTaobao;
	}

	public String getCodeInTaobao() {
		return this.codeInTaobao;
	}

	public void setCodeInTaobao(String codeInTaobao) {
		this.codeInTaobao = codeInTaobao;
	}

	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public Date getGmtCreate() {
		return this.gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModify() {
		return this.gmtModify;
	}

	public void setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getModifier() {
		return this.modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

}
