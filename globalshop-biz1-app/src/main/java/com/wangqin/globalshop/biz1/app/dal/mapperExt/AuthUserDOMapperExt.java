package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthUserDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.AuthUserDOMapper;
import com.wangqin.globalshop.biz1.app.vo.UserQueryVO;
import org.apache.ibatis.annotations.Param;


import java.util.List;

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

    //Here are
    AuthUserDO selectByLoginName(String userName);

    void updateByLoginName(AuthUserDO user);

    int deleteByLoginName(String userName);

    AuthUserDO selectUserVoById(String userName);

    Integer queryUsersCount(UserQueryVO userQueryVO);

    UserQueryVO queryUserQueryVOById(@Param("id") Long id);

    List<UserQueryVO> queryUserQueryVOList(UserQueryVO userQueryVO);

//    List<AuthUserDO> selectUserVoPage();

    AuthUserDO selectUserVoByUserNo(String userNo);
    
    void insertByNoId(AuthUserDO record);

    AuthUserDO searchAuthUser(AuthUserDO record);

    Long searchAuthUserCount(AuthUserDO record);

    List<AuthUserDO> searchAuthUserList(AuthUserDO record);
}
