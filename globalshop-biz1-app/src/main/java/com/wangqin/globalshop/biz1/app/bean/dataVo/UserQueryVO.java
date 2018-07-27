 package com.wangqin.globalshop.biz1.app.bean.dataVo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthRoleDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthUserDO;


import com.wangqin.globalshop.common.utils.PageQueryVO;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * Title: UserQueryVO.java
 * Description: 
 *
 * @author jc
 * Apr 17, 2017
 *
 */
public class UserQueryVO extends PageQueryVO {
	
	@NotNull(message = "id不能为空")
	private Long id;
	@NotNull(message = "用户编号不能为空")
	private String userNo;
	@Size(max = 64,min = 1,message = "登陆名不能为空也不能大于64位")
	private String loginName;
	@Size(max = 64,min = 1,message = "用户名不能为空也不能大于64位")
	private String name;
	@Size(max = 64, min = 1, message = "所属公司不能为空也不能大于64位")
	private String companyNo;
	@JsonIgnore
	@Size(max = 64, min = 1, message = "密码不能为空也不能大于64位")
	private String password;
	@Max(value = 0, message = "用户类别，0是男")
	@Min(value = 1, message = "用户类别，1是女")
	private Integer sex;
	@Max(value = 0, message = "年龄最小只能写0")
	@Min(value = 120, message = "年龄最大只能写120")
	private Integer age;
	private Integer userType;
	
	private Integer status;
	private Integer organizationId;
	@NotNull(message = "创建时间不能为空")
	private Date createTime;
	@Size(max = 11, min = 0, message = "电话最多11位")
	private String phone;

	private List<AuthRoleDO> rolesList;
	
	private String organizationName;

	private String roleIds;
	@NotNull(message = "不能为空")
	private Date createdateStart;
	@NotNull(message = "不能为空")
	private Date createdateEnd;
	@NotNull(message = "不能为空")
	private Date gmtCreate;
	@NotNull(message = "不能为空")
	private Date startTime;
	@NotNull(message = "不能为空")
	private Date endTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName == null ? null : loginName.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public List<AuthRoleDO> getRolesList() {
		return rolesList;
	}

	public void setRolesList(List<AuthRoleDO> rolesList) {
		this.rolesList = rolesList;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * 比较vo和数据库中的用户是否同一个user，采用id比较
	 * @param user 用户
	 * @return 是否同一个人
	 */
	public boolean equalsUser(AuthUserDO user) {
		if (user == null) {
			return false;
		}
		Long userId = user.getId();
		if (id == null || userId == null) {
			return false;
		}
		return id.equals(userId);
	}

//	@Override
//	public String toString() {
//		return JsonUtils.toJson(this);
//	}

}
