package com.wangqin.globalshop.biz1.app.dal.mapperExt;


import com.wangqin.globalshop.biz1.app.bean.dataVo.MallSaleAgentItemVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.MallSaleAgentQueryVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.PageQueryParam;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSaleAgentDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.MallSaleAgentDOMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import java.util.List;

/**
 * mall_sale_agent 表对应的 mapper
 *
 * @author angus
 * @date 2018/7/31
 */
public interface MallSaleAgentDOMapperExt extends MallSaleAgentDOMapper {


    List<MallSaleAgentDO> queryAgentListByCompanyNo(String companyNo);

    /**
     * 通过 companyNo 和 userNo 获得唯一代理
     *
     * @param companyNo
     * @param userNo
     * @return
     */
    MallSaleAgentDO getByCompanyNoAndUserNo(@Param("companyNo") String companyNo, @Param("userNo") String userNo);

    /**
     *
     * @param mallSaleAgentDO
     * @return
     */
    int updateByCompanyNoAndUserNo(MallSaleAgentDO mallSaleAgentDO);

    /**
     * 根据指定条件分页查询代理列表
     *
     * @param mallSaleAgentQueryVO
     * @param pageQueryParam
     * @return
     */
    List<MallSaleAgentItemVO> listMallSaleAgents(MallSaleAgentQueryVO mallSaleAgentQueryVO, PageQueryParam pageQueryParam);

    /**
     * 根据指定条件查询代理数目
     *
     * @param mallSaleAgentQueryVO
     * @return
     */
    int countMallSaleAgents(MallSaleAgentQueryVO mallSaleAgentQueryVO);
    
    /**
     * 
     * @param userNo
     * 根据user_no查询记录
     * @return
     */
    MallSaleAgentDO queryAgentInfoByUserNo(String userNo);

}
