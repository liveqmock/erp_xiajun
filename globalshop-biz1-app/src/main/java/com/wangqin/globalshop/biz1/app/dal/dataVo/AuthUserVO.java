package com.wangqin.globalshop.biz1.app.dal.dataVo;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthRoleDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthUserDO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter@Setter
public class AuthUserVO extends AuthUserDO{

	private Long id;

	private String companyNo;

	private String userNo;

	private String loginName;

	private String name;

	private String password;

	private Byte sex;

	private Byte age;

	private String phone;

	private Byte userType;

	private Byte status;

	private Integer organizationId;

	private Date createTime;

	private String wxUnionId;

	private String wxOpenId;

	private String email;

	private String creator;

	private String modifier;

	private String companyName;


	private String organizationName;
	
	private List<AuthRoleDO> rolesList;


}
