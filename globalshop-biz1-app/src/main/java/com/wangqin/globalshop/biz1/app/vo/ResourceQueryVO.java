package com.wangqin.globalshop.biz1.app.vo;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.wangqin.globalshop.common.utils.PageQueryVO;

/**
 * 
 * Title: ResourceQueryVO.java
 * Description: 
 *
 * @author jc
 * Apr 17, 2017
 *
 */
public class ResourceQueryVO extends PageQueryVO {
	
	private Long id;
	
    private String resourceId;
    @Size(max = 100, min = 1, message = "资源路径不能为空也不能超过100位")
    private String url;
    @Size(max = 32, min = 1, message = "打开方式不能为空也不能超过32位")
    private String openMode;

    private String description;

    private String icon;

    private Byte seq;

    private Byte status;

    private String creator;

    private String modifier;
	/** 资源名称 */
    @Size(max = 64, min = 1, message = "资源名不能为空也不能长过64位")
	private String name;

	/** 父级资源id */
	private Long pid;

	/** 资源类别 */
	@Max(value = 0, message = "0是菜单")
	@Min(value = 1, message = "1是按钮")
	private Integer resourceType;

	private Date startGmt;
	private Date endGmt;
	
	/**
	 * 资源编码
	 */
	private String resCode;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Integer getResourceType() {
		return resourceType;
	}

	public void setResourceType(Integer resourceType) {
		this.resourceType = resourceType;
	}

	public Date getStartGmt() {
		return startGmt;
	}

	public void setStartGmt(Date startGmt) {
		this.startGmt = startGmt;
	}

	public Date getEndGmt() {
		return endGmt;
	}

	public void setEndGmt(Date endGmt) {
		this.endGmt = endGmt;
	}

	public String getResCode() {
		return resCode;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOpenMode() {
		return openMode;
	}

	public void setOpenMode(String openMode) {
		this.openMode = openMode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Byte getSeq() {
		return seq;
	}

	public void setSeq(Byte seq) {
		this.seq = seq;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	
}
