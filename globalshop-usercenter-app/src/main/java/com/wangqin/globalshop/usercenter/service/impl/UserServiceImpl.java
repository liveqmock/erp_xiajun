package com.wangqin.globalshop.usercenter.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthUser;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthUserRole;
import com.wangqin.globalshop.biz1.app.dal.mapper.AuthUserMapper;
import com.wangqin.globalshop.biz1.app.service.IAuthUserService;
import com.wangqin.globalshop.common.utils.BeanUtils;
import com.wangqin.globalshop.common.utils.PageInfo;
import com.wangqin.globalshop.common.utils.StringUtils;
import com.wangqin.globalshop.usercenter.model.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.SuperServiceImpl;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wangqin.globalshop.usercenter.mapper.UserRoleMapper;

/**
 *
 * AuthUser 表数据服务层接口实现类
 *
 */
@Service
public class UserServiceImpl extends SuperServiceImpl<AuthUserMapper, AuthUser> implements IAuthUserService {

    @Autowired
    private AuthUserMapper authUserMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    
    @Override
    public List<AuthUser> selectByLoginName(UserVo userVo) {
        AuthUser user = new AuthUser();
        user.setLoginName(userVo.getLoginName());
        EntityWrapper<AuthUser> wrapper = new EntityWrapper<AuthUser>(user);
        if (null != userVo.getId()) {
            wrapper.where("id != {0}", userVo.getId());
        }
        return this.selectList(wrapper);
    }

    @Override
    public void insertByVo(UserVo userVo) {
        AuthUser user = BeanUtils.copy(userVo, AuthUser.class);
        user.setCreateTime(new Date());
        this.insert(user);
        
        Long id = user.getId();
        String[] roles = userVo.getRoleIds().split(",");
        AuthUserRole userRole = new AuthUserRole();

        for (String string : roles) {
            userRole.setUserId(id);
            userRole.setRoleId(Long.valueOf(string));
            userRoleMapper.insert(userRole);
        }
    }

    @Override
    public UserVo selectVoById(Long id) {
        return authUserMapper.selectUserVoById(id);
    }

    @Override
    public void updateByVo(UserVo userVo) {
        AuthUser user = BeanUtils.copy(userVo, AuthUser.class);
        if (StringUtils.isBlank(user.getPassword())) {
            user.setPassword(null);
        }
        this.updateSelectiveById(user);
        
        Long id = userVo.getId();
        List<AuthUserRole> userRoles = userRoleMapper.selectByUserId(id);
        if (userRoles != null && !userRoles.isEmpty()) {
            for (AuthUserRole userRole : userRoles) {
                userRoleMapper.deleteById(userRole.getId());
            }
        }

        String[] roles = userVo.getRoleIds().split(",");
        AuthUserRole userRole = new AuthUserRole();
        for (String string : roles) {
            userRole.setUserId(id);
            userRole.setRoleId(Long.valueOf(string));
            userRoleMapper.insert(userRole);
        }
    }

    @Override
    public void updatePwdByUserId(Long userId, String md5Hex) {
        AuthUser user = new AuthUser();
        user.setId(userId);
        user.setPassword(md5Hex);
        this.updateSelectiveById(user);
    }

    @Override
    public void selectDataGrid(PageInfo pageInfo) {
        Page<UserVo> page = new Page<UserVo>(pageInfo.getNowpage(), pageInfo.getSize());
        List<UserVo> list = authUserMapper.selectUserVoPage(page, pageInfo.getCondition());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
    }

    @Override
    public void deleteUserById(Long id) {
        this.deleteById(id);
        List<AuthUserRole> userRoles = userRoleMapper.selectByUserId(id);
        if (userRoles != null && !userRoles.isEmpty()) {
            for (AuthUserRole userRole : userRoles) {
                userRoleMapper.deleteById(userRole.getId());
            }
        }
    }

	@Override
	public List<AuthUser> queryBuyer() {
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("organization_id", 7);
		return authUserMapper.selectByMap(columnMap);
	}
	
	@Override
    public List<Long> selectUserIds() {
    	return authUserMapper.selectUserIds();
    }

}