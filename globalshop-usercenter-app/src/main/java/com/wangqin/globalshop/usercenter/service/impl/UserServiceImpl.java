package com.wangqin.globalshop.usercenter.service.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthUserDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthUserRoleDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.AuthUserDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.AuthUserRoleDOMapperExt;
import com.wangqin.globalshop.biz1.app.vo.UserQueryVO;
import com.wangqin.globalshop.common.utils.BeanUtils;
import com.wangqin.globalshop.common.utils.JsonPageResult;
import com.wangqin.globalshop.common.utils.PageInfo;
import com.wangqin.globalshop.common.utils.StringUtils;
import com.wangqin.globalshop.usercenter.service.IUserService;
import com.wangqin.globalshop.usercenter.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


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
//        user.setCreateTime(new Date());
        user.init();
        user.setSex(userVo.getSex().byteValue());
        user.setAge(userVo.getAge().byteValue());
        user.setUserType(userVo.getUserType().byteValue());
        user.setStatus(userVo.getStatus().byteValue());
        user.setIsDel(false);
        userMapper.insertSelective(user);
        
        Long id = user.getId();
        String[] roles = userVo.getRoleIds().split(",");
        AuthUserRoleDO userRole = new AuthUserRoleDO();

        for (String string : roles) {
            userRole.init();
            userRole.setCompanyNo(userVo.getCompanyNo());
            userRole.setUserId(id);
            userRole.setRoleId(Long.valueOf(string));
            userRoleMapper.insertSelective(userRole);
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
        AuthUserDO user = BeanUtils.copy(userVo, AuthUserDO.class);
        if (StringUtils.isBlank(user.getPassword())) {
            user.setPassword(null);
        }
        userMapper.updateByPrimaryKey(user);
        
//        Long id = userVo.getId();
        List<AuthUserRoleDO> userRoles = userRoleMapper.selectByLoginName(userVo.getLoginName());
        if (userRoles != null && !userRoles.isEmpty()) {
            for (AuthUserRoleDO userRole : userRoles) {
                userRoleMapper.deleteByPrimaryKey(userRole.getId());
            }
        }

        String[] roles = userVo.getRoleIds().split(",");
        AuthUserRoleDO userRole = new AuthUserRoleDO();
        for (String string : roles) {
//            userRole.setUserId(id);
            userRole.setRoleId(Long.valueOf(string));
            userRoleMapper.insert(userRole);
        }
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
    public void deleteUserById(String userNo) {
        userMapper.deleteByLoginName(userNo);
        List<AuthUserRoleDO> userRoles = userRoleMapper.selectByLoginName(userNo);
        if (userRoles != null && !userRoles.isEmpty()) {
            for (AuthUserRoleDO userRole : userRoles) {
                userRoleMapper.deleteByPrimaryKey(userRole.getId());
            }
        }
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
}