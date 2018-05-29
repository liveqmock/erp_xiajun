package com.wangqin.globalshop.service;

import com.baomidou.framework.service.ISuperService;
import com.wangqin.globalshop.common.utils.PageInfo;
import com.wangqin.globalshop.model.SysLog;

/**
 *
 * SysLog 表数据服务层接口
 *
 */
public interface ISysLogService extends ISuperService<SysLog> {

    void selectDataGrid(PageInfo pageInfo);


}