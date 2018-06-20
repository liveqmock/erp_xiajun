package com.wangqin.globalshop.usercenter.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthRoleDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthUserDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthUserRoleDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.WxUserDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.WxUserVO;
import com.wangqin.globalshop.biz1.app.dal.mapper.AuthRoleDOMapper;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.AuthRoleDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.AuthUserDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.AuthUserRoleDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.WxUserDOMapperExt;
import com.wangqin.globalshop.biz1.app.vo.UserQueryVO;
import com.wangqin.globalshop.common.utils.*;
import com.wangqin.globalshop.usercenter.service.IUserService;
import com.wangqin.globalshop.usercenter.vo.UserVo;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	@Override
    public void addUserByqrcode(String companyNo, WxUserVO wxUserVO){

        //创建微信用户
        WxUserDO wxUserso = new WxUserDO();
        wxUserso.setOpenId(wxUserVO.getOpenId());
        wxUserso.setUnionId(wxUserVO.getUnionId());

        WxUserDO existWxUser = wxUserDOMapper.searchWxUser(wxUserso);

        if(existWxUser == null){
            WxUserDO newWxUser = new WxUserDO();
            BeanUtils.copies(wxUserVO,newWxUser);
            newWxUser.init4NoLogin();
            wxUserDOMapper.insert(newWxUser);
        }else {
            existWxUser.setLastLoginTime(new Date());
            existWxUser.setGmtModify(new Date());
            existWxUser.setLastLoginDevice(wxUserVO.getLastLoginDevice());
            wxUserDOMapper.updateByPrimaryKey(existWxUser);
        }


        //创建用户

        AuthUserDO authUserSo = new AuthUserDO();
        //authUserDO.init4NoLogin();
        authUserSo.setCompanyNo(companyNo);
        authUserSo.setWxOpenId(wxUserVO.getOpenId());
        authUserSo.setWxUnionId(wxUserVO.getUnionId());

        AuthUserDO existAuthUser = userMapper.searchAuthUser(authUserSo);

        if(existAuthUser == null){
            existAuthUser = new AuthUserDO();
            existAuthUser.init4NoLogin();
            existAuthUser.setCompanyNo(companyNo);
            existAuthUser.setWxUnionId(wxUserVO.getUnionId());
            existAuthUser.setWxOpenId(wxUserVO.getOpenId());
            existAuthUser.setSex(wxUserVO.getGender().byteValue());
            Integer userType = new Integer(0);
            existAuthUser.setUserType(userType.byteValue());
            Integer status = new Integer(0);
            existAuthUser.setStatus(status.byteValue());
            existAuthUser.setLoginName(wxUserVO.getNickName());
            existAuthUser.setName(wxUserVO.getNickName());

            String uuid = UUID.randomUUID().toString().replace("-", "");
            existAuthUser.setPassword(Md5Util.getMD5(uuid));//uuid+md5
            String userNo=DateUtil.formatDate(new Date(),"yyMMdd HH:mm:ss")+String.format("%1$06d", RandomUtils.nextInt(1000000));
            existAuthUser.setUserNo(userNo);

            userMapper.insert(existAuthUser);
            existAuthUser = userMapper.searchAuthUser(authUserSo);
        }else {
            existAuthUser.update();
            userMapper.updateByPrimaryKey(existAuthUser);
        }

        //绑定默认权限
        AuthRoleDO authRoleSo = new AuthRoleDO();
        authRoleSo.setCompanyNo(companyNo);
        authRoleSo.setName("新成员");
        AuthRoleDO existRole = authRoleDOMapper.searchAuthRole(authRoleSo);
        if(existRole == null){
            existRole = new AuthRoleDO();
            existRole.setCompanyNo(companyNo);
            existRole.setName("新成员");
            existRole.init4NoLogin();
            existRole.setRoleId((long)RandomUtils.nextInt(1000000000));
            authRoleDOMapper.insertSelective(existRole);
            existRole = authRoleDOMapper.searchAuthRole(authRoleSo);
        }

        AuthUserRoleDO userRoleSo = new AuthUserRoleDO();
        userRoleSo.setCompanyNo(companyNo);
        userRoleSo.setRoleId(existRole.getRoleId());
        userRoleSo.setUserId(existAuthUser.getId());

        AuthUserRoleDO existUserRole = userRoleDOMapperExt.searchUserRole(userRoleSo);
        if(existUserRole != null){
            AuthUserRoleDO userRole = new AuthUserRoleDO();
            userRole.setCompanyNo(companyNo);
            userRole.setRoleId(existRole.getRoleId());
            userRole.setUserId(existAuthUser.getId());
            userRole.init4NoLogin();
            userRoleDOMapperExt.insert(userRole);
        }


    }

    public void addUserFromToken(String companyNo, String wxInfoJson){
        WxUserVO wxUserVO = JSON.parseObject(wxInfoJson, WxUserVO.class);
        this.addUserByqrcode(companyNo, wxUserVO);
    }
	
}
