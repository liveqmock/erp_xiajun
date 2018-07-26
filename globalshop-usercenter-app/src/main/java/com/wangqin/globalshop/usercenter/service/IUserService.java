package com.wangqin.globalshop.usercenter.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthUserDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.WxUserDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.AuthUserVO;
import com.wangqin.globalshop.biz1.app.vo.UserQueryVO;
import com.wangqin.globalshop.common.utils.JsonPageResult;
import com.wangqin.globalshop.common.utils.PageInfo;
import com.wangqin.globalshop.usercenter.vo.UserVo;

import java.util.List;

/**
 *
 * AuthUserDO 表数据服务层接口
 *
 */
public interface IUserService {// extends ISuperService<AuthUserDO>

//    List<AuthUserDO> selectByLoginName(UserVo userVo);

    //查询包括了密码密文的用户信息，慎用
    AuthUserDO selectSecureByLoginName(String userNo);

    //查询用户信息推荐用这个，密码已脱敏。
    AuthUserDO selectByLoginName(String userNo);
    
    List<AuthUserDO> queryUserByCompanyNo(String companyNo);
    
    List<AuthUserVO> queryUserListByCompanyNo(String companyNo);
    
    void insertByVo(UserVo userVo);
    
    void insertByUserVo(UserVo userVo);

    UserVo selectVoById(String loginName);

    void updateByVo(UserVo userVo);

    void changePasswordByLoginName(String loginName, String md5Hex);

    void selectDataGrid(PageInfo pageInfo);

    void deleteUserById(Long id);

    UserQueryVO queryVoById(Long id);

    JsonPageResult<List<UserQueryVO>> queryUserQueryVOList(UserQueryVO userQueryVO);
    
    UserQueryVO selectUserVoByUserNo(String userNo);

    //一键分享登录
    List<AuthUserDO> selectUserByWxUnionId(String wxUnionId);
    
    //一键分享手机号登录
    AuthUserDO selectUserByPhone(String phone);
    


    void addUserByqrcode(String companyNo, WxUserDO wxInfoJson);


    List<AuthUserDO> selectByUnionid(String unionid);

    List<AuthUserDO> selectByUnionidAndCompanyNo(String unionid, String state);

    AuthUserDO selectByUserNoAndCompanyNo(String userNo, String companyNo);


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