package com.wangqin.globalshop.usercenter.service.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthRoleDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthUserDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthUserRoleDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.WxUserDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.AuthRoleDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.AuthUserDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.AuthUserRoleDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.WxUserDOMapperExt;
import com.wangqin.globalshop.biz1.app.vo.UserQueryVO;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.utils.*;
import com.wangqin.globalshop.usercenter.service.IUserService;
import com.wangqin.globalshop.usercenter.vo.UserVo;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *
 * AuthUserDO 表数据服务层接口实现类
 *
 */
@Service
public class UserServiceImpl implements IUserService { //extends SuperServiceImpl<AuthUserDOMapperExt, AuthUserDO>

    @Autowired
    private AuthUserDOMapperExt userMapper;

    @Autowired
    private AuthUserRoleDOMapperExt userRoleMapper;

    @Autowired
    private WxUserDOMapperExt wxUserDOMapper;

    @Autowired
    private AuthRoleDOMapperExt authRoleDOMapper;

    @Autowired
    private AuthUserRoleDOMapperExt userRoleDOMapperExt;

    @Override
    public AuthUserDO selectByLoginName(String userNo) {
//        AuthUserDO user = new AuthUserDO();
////        user.setLoginName(userNo);
        return userMapper.selectByLoginName(userNo);
//        EntityWrapper<AuthUserDO> wrapper = new EntityWrapper<AuthUserDO>(user);
//        if (null != userNo.getId()) {
//            wrapper.where("id != {0}", userNo.getId());
//        }
//        return this.selectList(wrapper);
    }

    @Override
//    @Transactional
    public void insertByVo(UserVo userVo) {
        AuthUserDO user = BeanUtils.copy(userVo, AuthUserDO.class);
        user.init();
        user.setName(userVo.getName());
        user.setSex(userVo.getSex().byteValue());
        user.setAge(userVo.getAge().byteValue());
        user.setUserType(userVo.getUserType().byteValue());
        user.setStatus(userVo.getStatus().byteValue());
        user.setIsDel(false);
        userMapper.insertByNoId(user);

    }

    @Override
    public void insertByUserVo(UserVo userVo) {
        // TODO Auto-generated method stub
        AuthUserDO user = BeanUtils.copy(userVo, AuthUserDO.class);
        Long id = user.getId();
        String[] roles = userVo.getRoleIds().split(",");
        AuthUserRoleDO userRole = new AuthUserRoleDO();
        for (String string : roles) {
            userRole.init();
            userRole.setUserId(id);
            userRole.setRoleId(Long.valueOf(string));
            userRoleMapper.insertByNoId(userRole);
        }
	}

    @Override
    public UserVo selectVoById(String loginName) {
        //TODO
        userMapper.selectUserVoById(loginName);
        return null;
    }

    @Override
    public void updateByVo(UserVo userVo) {
    	
        AuthUserDO authUser = userMapper.selectByPrimaryKey(userVo.getId());
        
        authUser.setLoginName(userVo.getLoginName());
        authUser.setName(userVo.getName());
        if (StringUtils.isBlank(userVo.getPassword())) {
            authUser.setPassword(null);
        }
        
        authUser.setPassword(userVo.getPassword());
        authUser.setSex((byte)userVo.getSex().intValue());
        authUser.setAge((byte)userVo.getAge().intValue());
        authUser.setUserType((byte)userVo.getUserType().intValue());
        authUser.setOrganizationId(userVo.getOrganizationId());
        authUser.setPhone(userVo.getPhone());
        authUser.setStatus((byte)userVo.getStatus().intValue());
        authUser.setIsDel(false);
   
        
       
        userMapper.updateByPrimaryKey(authUser);
        
//        System.out.println(userVo.getId());
//        List<AuthUserRoleDO> userRoles = userRoleMapper.selectByUserId(userVo.getId());
//        for(AuthUserRoleDO userRole : userRoles) {
//        	userRole.setRoleId(Long.parseLong(userVo.getRoleIds()));
//            userRoleMapper.updateByPrimaryKey(userRole);
//        }

        
        
    }

//    @Override
//    public void updateSelectiveById(AuthUserDO user) {
//			
//    }

    @Override
    public void changePasswordByLoginName(String loginName, String md5Hex) {
        AuthUserDO user = new AuthUserDO();
//        user.setId(userId);
        user.setLoginName(loginName);
        user.setPassword(md5Hex);
        userMapper.updateByLoginName(user);
    }

    @Override
    public void selectDataGrid(PageInfo pageInfo) {
//        Page<UserVo> page = new Page<UserVo>(pageInfo.getNowpage(), pageInfo.getSize());
//        List<UserVo> list = userMapper.selectUserVoPage(page, pageInfo.getCondition());
//        pageInfo.setRows(list);
//        pageInfo.setTotal(page.getTotal());
    }

    @Override
    public void deleteUserById(Long id) {
    	
        userMapper.deleteByPrimaryKey(id);
        
        
        
    }

//	@Override
//	public List<AuthUserDO> queryBuyer() {
//		Map<String, Object> columnMap = new HashMap<>();
//		columnMap.put("organization_id", 7);
//		return userMapper.selectByMap(columnMap);
//	}
	
//	@Override
//    public List<Long> selectUserIds() {
//    	return userMapper.selectUserIds();
//    }

    @Override
    public UserQueryVO queryVoById(Long id) {
        return userMapper.queryUserQueryVOById(id);
    }

    @Override
    public JsonPageResult<List<UserQueryVO>> queryUserQueryVOList(UserQueryVO userQueryVO) {
        JsonPageResult<List<UserQueryVO>> userResult = new JsonPageResult<>();

        Integer totalCount = userMapper.queryUsersCount(userQueryVO);

        if ((null != totalCount) && (0L != totalCount)) {
            userResult.buildPage(totalCount, userQueryVO);

            List<UserQueryVO> users = userMapper.queryUserQueryVOList(userQueryVO);
            userResult.setData(users);
        } else {
            List<UserQueryVO> users = new ArrayList<>();
            userResult.setData(users);
        }

        return userResult;
    }

	@Override
	public AuthUserDO selectUserVoByUserNo(String userNo) {
		// TODO Auto-generated method stub
		return userMapper.selectUserVoByUserNo(userNo);
	}
	
	//一键分享登录
	@Override
	public  List<AuthUserDO> selectUserByWxOpenId(String wxOpenId) {
    	return userMapper.selectUserByWxOpenId(wxOpenId);
    }


	//一键分享手机号登录
	@Override
	public AuthUserDO selectUserByPhone(String phone) {
    	return userMapper.selectUserByPhone(phone);
    }
	


    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public void addUserByqrcode(String companyNo, WxUserDO wxUser) {

        WxUserDO existWxUser = wxUserDOMapper.queryByUnionId(wxUser.getUnionId());

        if (existWxUser == null) {
            wxUser.init4NoLogin();
            wxUserDOMapper.insertSelective(wxUser);
        } else {
            wxUser.setModifier("system");
            wxUserDOMapper.updateByPrimaryKey(existWxUser);
        }

        AuthUserDO user = new AuthUserDO();
        user.init4NoLogin();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        user.setPassword(Md5Util.getMD5(uuid));
		String userNo=CodeGenUtil.genUserNo();
        user.setUserNo(userNo);
        user.setCompanyNo(companyNo);
        user.setWxUnionId(wxUser.getUnionId());
        user.setWxOpenId(wxUser.getOpenId());
        user.setSex(wxUser.getGender().byteValue());
        user.setUserType((byte) 0);
        user.setStatus((byte) 0);
        user.setName(wxUser.getNickName());
        user.setLoginName("#init#"+companyNo+RandomUtils.nextInt(100000));
        userMapper.insertSelective(user);
        AuthUserDO userDO = userMapper.selectUserVoByUserNo(userNo);


        AuthRoleDO role = authRoleDOMapper.selectByNameAndCompanyNo("新成员", companyNo);
        AuthUserRoleDO authUserRole = new AuthUserRoleDO();
        authUserRole.setRoleId(role.getRoleId());
        authUserRole.setCompanyNo(companyNo);
        authUserRole.setUserId(userDO.getId());
        authUserRole.init4NoLogin();
        userRoleDOMapperExt.insert(authUserRole);


    }


    @Override
    public List<AuthUserDO> selectByUnionid(String unionid) {
        return userMapper.selectByUnionid(unionid);
    }


}

