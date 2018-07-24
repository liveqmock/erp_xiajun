package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthUserDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.AuthUserVO;
import com.wangqin.globalshop.biz1.app.dal.mapper.AuthUserDOMapper;
import com.wangqin.globalshop.biz1.app.vo.UserQueryVO;

public interface AuthUserDOMapperExt extends AuthUserDOMapper {
    //    int deleteByPrimaryKey(Long id);
//
//    int insert(AuthUserDO record);
//
//    int insertSelective(AuthUserDO record);
//
//    AuthUserDO selectByPrimaryKey(Long id);
//
//    int updateByPrimaryKeySelective(AuthUserDO record);
//
//    int updateByPrimaryKey(AuthUserDO record);

//    List<AuthUserDO> selectUserVoPage(page, pageInfo.getCondition());

    //根据登录名查用户信息，密码已脱敏，通常请用这个。
    AuthUserDO selectByLoginName(String userName);

    //根据登录名查用户信息，专用于修改密码和密码校验
    AuthUserDO selectSecureByLoginName(String userName);

    void updateUserInfoByLoginName(AuthUserDO user);

    void updatePasswordByLoginName(AuthUserDO user);

    int deleteByLoginName(String userName);

    AuthUserDO selectUserVoById(String userName);

    Integer queryUsersCount(UserQueryVO userQueryVO);

    UserQueryVO queryUserQueryVOById(@Param("id") Long id);

    List<UserQueryVO> queryUserQueryVOList(UserQueryVO userQueryVO);
    
    List<AuthUserDO> queryUserByCompanyNo(String companyNo);
    
    List<AuthUserVO> queryUserListByCompanyNo(String companyNo);

//    List<AuthUserDO> selectUserVoPage();

    UserQueryVO selectUserVoByUserNo(String userNo);
    
    void insertByNoId(AuthUserDO record);

    
    //一键分享登录
    List<AuthUserDO> selectUserByWxUnionId(@RequestParam("wxUnionId") String wxUnionId);
    
    //一键分享手机号登录
    AuthUserDO selectUserByPhone(String phone);

    AuthUserDO searchAuthUser(AuthUserDO record);

    Long searchAuthUserCount(AuthUserDO record);

    List<AuthUserDO> searchAuthUserList(AuthUserDO record);

    List<AuthUserDO> selectByUnionid(@Param("unionid") String unionid);

    List<AuthUserDO> selectByUnionidAndCompanyNo(@Param("unionid")String unionid, @Param("companyNo")String companyNo);

}