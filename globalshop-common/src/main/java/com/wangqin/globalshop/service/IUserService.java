package com.wangqin.globalshop.service;

import java.util.List;

import com.baomidou.framework.service.ISuperService;
import com.wangqin.globalshop.common.utils.PageInfo;
import com.wangqin.globalshop.model.User;
import com.wangqin.globalshop.model.vo.UserVo;

/**
 *
 * User 表数据服务层接口
 *
 */
public interface IUserService extends ISuperService<User> {

    List<User> selectByLoginName(UserVo userVo);

    void insertByVo(UserVo userVo);

    UserVo selectVoById(Long id);

    void updateByVo(UserVo userVo);

    void updatePwdByUserId(Long userId, String md5Hex);

    void selectDataGrid(PageInfo pageInfo);

    void deleteUserById(Long id);
    
    /**
     * 查询买手
     * @return
     */
    List<User> queryBuyer();
    
    List<Long> selectUserIds();
}