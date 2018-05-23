package com.wangqin.globalshop.usercenter.service.impl;

import java.util.List;

import com.wangqin.globalshop.common.utils.PageInfo;
import com.wangqin.globalshop.usercenter.mapper.SysLogMapper;
import com.wangqin.globalshop.usercenter.model.Role;
import com.wangqin.globalshop.usercenter.model.SysLog;
import com.wangqin.globalshop.usercenter.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.SuperServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;

/**
 *
 * SysLog 表数据服务层接口实现类
 *
 */
@Service
public class SysLogServiceImpl extends SuperServiceImpl<SysLogMapper, SysLog> implements ISysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;
    
    @Override
    public void selectDataGrid(PageInfo pageInfo) {
        Page<SysLog> page = new Page<SysLog>(pageInfo.getNowpage(), pageInfo.getSize());
        List<Role> list = sysLogMapper.selectSysLogList(page);
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
    }

}