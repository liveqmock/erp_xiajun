package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.bean.dataVo.MallSaleAgentItemVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.MallSaleAgentQueryVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.PageQueryParam;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSaleAgentDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.MallSaleAgentDOMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * mall_sale_agent 表对应的 mapper
 *
 * @author angus
 * @date 2018/7/31
 */
public interface MallSaleAgentDOMapperExt extends MallSaleAgentDOMapper {

    /**
     * 通过 companyNo 和 userNo 获得 mall_sale_agent 表中的一条记录
     *
     * @param companyNo
     * @param userNo
     * @return
     */
    MallSaleAgentDO getByCompanyNoAndUserNo(@Param("companyNo") String companyNo, @Param("userNo") String userNo);

    /**
     * 根据 company_no 和 user_no 唯一确定 mall_sale_agent 表中的一条记录，将其更新
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
    List<MallSaleAgentItemVO> listMallSaleAgents(@Param("mallSaleAgentQueryVO") MallSaleAgentQueryVO mallSaleAgentQueryVO,
                                                 @Param("pageQueryParam") PageQueryParam pageQueryParam);

    /**
     * 根据指定条件查询代理数目
     *
     * @param mallSaleAgentQueryVO
     * @return
     */
    int countMallSaleAgents(@Param("mallSaleAgentQueryVO") MallSaleAgentQueryVO mallSaleAgentQueryVO);
}
