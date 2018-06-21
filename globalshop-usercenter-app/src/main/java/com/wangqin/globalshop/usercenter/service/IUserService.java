package com.wangqin.globalshop.usercenter.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthUserDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthUserRoleDO;
import com.wangqin.globalshop.common.utils.JsonPageResult;
import com.wangqin.globalshop.common.utils.PageInfo;
import com.wangqin.globalshop.biz1.app.vo.RoleQueryVO;
import com.wangqin.globalshop.biz1.app.vo.UserQueryVO;
import com.wangqin.globalshop.usercenter.vo.UserVo;

import java.util.List;

/**
 *
 * AuthUserDO 表数据服务层接口
 *
 */
public interface IUserService {// extends ISuperService<AuthUserDO>

//    List<AuthUserDO> selectByLoginName(UserVo userVo);

    AuthUserDO selectByLoginName(String userNo);

    void insertByVo(UserVo userVo);
    
    void insertByUserVo(UserVo userVo);

    UserVo selectVoById(String loginName);

    void updateByVo(UserVo userVo);

    void changePasswordByLoginName(String loginName, String md5Hex);

    void selectDataGrid(PageInfo pageInfo);

    void deleteUserById(Long id);

    UserQueryVO queryVoById(Long id);

    JsonPageResult<List<UserQueryVO>> queryUserQueryVOList(UserQueryVO userQueryVO);
    
    AuthUserDO selectUserVoByUserNo(String userNo);
    
    //一键分享登录
    List<AuthUserDO> selectUserByWxOpenId(String wxOpenId);
    
    //一键分享手机号登录
    AuthUserDO selectUserByPhone(String phone);
    
    /**
     * 查询买手
     * @return
     */
//    List<AuthUserDO> queryBuyer();
    
//    List<Long> selectUserIds();


//    int updateSelectiveById(UserVo userVo);
//    List<UserVo>  selectUserVoPage(page, pageInfo.getCondition());
//    int deleteById(Long id);
//	 selectByMap(columnMap);
//    selectUserIds();
}