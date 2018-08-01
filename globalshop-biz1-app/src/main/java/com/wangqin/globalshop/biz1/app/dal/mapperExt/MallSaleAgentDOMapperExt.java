package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSaleAgentDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.MallSaleAgentDOMapper;

import java.util.List;

/**
 * mall_sale_agent 表对应的 mapper
 *
 * @author angus
 * @date 2018/7/31
 */
public interface MallSaleAgentDOMapperExt extends MallSaleAgentDOMapper {

    List<MallSaleAgentDO> queryAgentListByCompanyNo(String companyNo);
}
