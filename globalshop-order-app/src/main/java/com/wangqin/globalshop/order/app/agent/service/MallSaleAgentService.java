package com.wangqin.globalshop.order.app.agent.service;

import com.wangqin.globalshop.biz1.app.bean.dataVo.PageQueryParam;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSaleAgentDO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.MallSaleAgentItemVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.MallSaleAgentQueryVO;

import java.util.List;

/**
 * 与代理管理相关的 service
 *
 * @author angus
 * @date 2018/7/31
 */
public interface MallSaleAgentService {

    /**
     * 通过 companyNo 和 userNo 获得唯一代理
     *
     * @param companyNo
     * @param userNo
     * @return
     */
    MallSaleAgentDO getByCompanyNoAndUserNo(String companyNo, String userNo);

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
     * 添加代理
     *
     * @param mallSaleAgentDO
     */
    void insertMallSaleAgent(MallSaleAgentDO mallSaleAgentDO);

    /**
     * 更新代理
     *
     * @param mallSaleAgentDO
     */
    void updateMallSaleAgent(MallSaleAgentDO mallSaleAgentDO);

    // TODO: 考虑实现单独的佣金修改
}
