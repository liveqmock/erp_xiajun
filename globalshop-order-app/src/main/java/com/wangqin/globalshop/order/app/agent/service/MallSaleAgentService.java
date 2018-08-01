package com.wangqin.globalshop.order.app.agent.service;

import com.wangqin.globalshop.biz1.app.bean.dataVo.CommissionValueVO;
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

    /**
     * 分佣比率修改
     *
     * @param userNo
     * @param commissionMode
     * @param commissionValue
     */
    void updateCommissionValue(String userNo, Long commissionMode, Double commissionValue);

    /**
     * 通过 companyNo 和 userNo 获得唯一代理
     *
     * @param companyNo
     * @param userNo
     * @return
     */
    MallSaleAgentDO getMallSaleAgent(String companyNo, String userNo);

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
     * 根据分享 userNo 查询代理的分佣比率
     *
     * @param userNo
     * @param companyNo
     * @return
     */
    CommissionValueVO queryCommissionValue(String userNo, String companyNo);
    
    /**
     * 根据userNo查询代理的信息（如头像，登录名等）
     * @author xiajun
     * @param userNo
     * @return
     */
    MallSaleAgentDO queryAgentInfoByUserNo(String userNo);

}
