package com.wangqin.globalshop.usercenter.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthUserDO;
import com.wangqin.globalshop.common.utils.PageInfo;
import com.wangqin.globalshop.usercenter.vo.UserVo;

/**
 *
 * AuthUserDO 表数据服务层接口
 *
 */
public interface IUserService {// extends ISuperService<AuthUserDO>

//    List<AuthUserDO> selectByLoginName(UserVo userVo);

    AuthUserDO selectByLoginName(String userNo);

    void insertByVo(UserVo userVo);

    UserVo selectVoById(String loginName);

    void updateByVo(UserVo userVo);

    void changePasswordByLoginName(String loginName, String md5Hex);

    void selectDataGrid(PageInfo pageInfo);

    void deleteUserById(String userNo);

//    void updateSelectiveById(AuthUserDO user);
    
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