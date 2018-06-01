package com.wangqin.globalshop.usercenter.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * SysLog 表数据服务层接口实现类
 *
 */
//@Service
//public class SysLogServiceImpl   implements ISysLogService {
//
//    @Autowired
//    private SysLogMapperDO sysLogMapper;
//
//    @Override
//    public void selectDataGrid(PageInfo pageInfo) {
//        Page<SysLog> page = new Page<SysLog>(pageInfo.getNowpage(), pageInfo.getSize());
//        List<AuthRoleDO> list = sysLogMapper.selectSysLogList(page);
//        pageInfo.setRows(list);
//        pageInfo.setTotal(page.getTotal());
//    }
//
//}