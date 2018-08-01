package com.wangqin.globalshop.order.app.agent.service.impl;

import com.wangqin.globalshop.biz1.app.bean.dataVo.CommissionValueVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.MallSaleAgentItemVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.MallSaleAgentQueryVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.PageQueryParam;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSaleAgentDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.MallSaleAgentDOMapperExt;
import com.wangqin.globalshop.biz1.app.exception.BizCommonException;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.StringUtil;
import com.wangqin.globalshop.order.app.agent.service.MallSaleAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author angus
 * @date 2018/7/31
 */
@Service
public class MallSaleAgentServiceImpl implements MallSaleAgentService {
    @Autowired
    private MallSaleAgentDOMapperExt mallSaleAgentDOMapper;

    @Override
    public void insertMallSaleAgent(MallSaleAgentDO mallSaleAgentDO) {
        // TODO: 要从 auth_user 表获取信息，才能进行下一步的操作
    }

    @Override
    public void updateMallSaleAgent(MallSaleAgentDO mallSaleAgentDO) {

        int effectedNum = mallSaleAgentDOMapper.updateByCompanyNoAndUserNo(mallSaleAgentDO);
        if (effectedNum <= 0) {
            throw new BizCommonException("更新出现异常！");
        }
    }

    @Override
    @Transactional(rollbackFor = BizCommonException.class)
    public void updateCommissionValue(String userNo, Long commissionMode, Double commissionValue) {
        if (StringUtil.isBlank(userNo) || commissionMode == null || commissionValue == null) {
            throw new BizCommonException("数据不完整！");
        }

        if (commissionMode != 0 && commissionMode != 1) {
            throw new BizCommonException("分佣模式非法！");
        }

        if (commissionMode == 0) {
            // 分佣模式为百分比模式（分佣最大百分比是否为 100%？）
            if (commissionValue < 0 || commissionValue > 1) {
                throw new BizCommonException("分佣比率非法！");
            }
            // 构造更新模板
            MallSaleAgentDO mallSaleAgentDO = new MallSaleAgentDO();
            mallSaleAgentDO.setUserNo(userNo);
            mallSaleAgentDO.setCompanyNo(AppUtil.getLoginUserCompanyNo());
            mallSaleAgentDO.setCommissionMode(commissionMode);
            mallSaleAgentDO.setCommissionValue(commissionValue);
            // 更新代理信息
            updateMallSaleAgent(mallSaleAgentDO);
        } else {
            // 分佣模式为金额模式的情况
            throw new BizCommonException("暂不支持分佣模式为金额模式的情况！");
        }
    }


    @Override
    public MallSaleAgentDO getMallSaleAgent(String companyNo, String userNo) {
        return mallSaleAgentDOMapper.getByCompanyNoAndUserNo(companyNo, userNo);
    }

    @Override
    public List<MallSaleAgentItemVO> listMallSaleAgents(MallSaleAgentQueryVO mallSaleAgentQueryVO,
                                                        PageQueryParam pageQueryParam) {
        pageQueryParam.calculateRowIndex();
        List<MallSaleAgentItemVO> mallSaleAgentItemVOList =
                mallSaleAgentDOMapper.listMallSaleAgents(mallSaleAgentQueryVO, pageQueryParam);

        // 根据 MallSaleAgentQueryVO 中 parentAgent 字段是否为空判断是查询一级代理还是二级代理
        String parentAgent = mallSaleAgentQueryVO.getParentAgent();

        if (StringUtil.isBlank(parentAgent)) {
            // 查询一级代理，需要为每个一级代理添加其二级代理人数
            mallSaleAgentItemVOList.forEach(mallSaleAgentItemVO -> {

                // 构造查询 VO
                MallSaleAgentQueryVO childAgentsQueryVO = new MallSaleAgentQueryVO();
                childAgentsQueryVO.setParentAgent(mallSaleAgentItemVO.getUserNo());

                // 查询并设置二级代理人数
                int childAgentsNum = countMallSaleAgents(childAgentsQueryVO);
                mallSaleAgentItemVO.setChildAgentsNum(childAgentsNum);
            });

        } else {
            // 查询二级代理，需要为每个二级代理添加其一级代理信息
            MallSaleAgentDO parentMallSaleAgentDO =
                    getMallSaleAgent(mallSaleAgentQueryVO.getCompanyNo(), parentAgent);

            mallSaleAgentItemVOList.forEach(
                    mallSaleAgentItemVO -> {
                        mallSaleAgentItemVO.setParentAgent(parentAgent);
                        mallSaleAgentItemVO.setParentAgentName(parentMallSaleAgentDO.getAgentName());
                    });
        }

        return mallSaleAgentItemVOList;
    }

    @Override
    public int countMallSaleAgents(MallSaleAgentQueryVO mallSaleAgentQueryVO) {
        return mallSaleAgentDOMapper.countMallSaleAgents(mallSaleAgentQueryVO);
    }



    @Override
    @Transactional(rollbackFor = BizCommonException.class)
    public CommissionValueVO queryCommissionValue(String userNo, String companyNo) {
        if (StringUtil.isBlank(userNo) || StringUtil.isBlank(companyNo)) {
            throw new BizCommonException("数据不完整！");
        }

        // 获取该代理详细信息
        MallSaleAgentDO mallSaleAgent = getMallSaleAgent(companyNo, userNo);
        if (mallSaleAgent == null) {
            throw new BizCommonException("数据库中无此记录！");
        }

        CommissionValueVO commissionValueVO = new CommissionValueVO();
        // 判断代理级别
        String parentAgentNo = mallSaleAgent.getParentAgent();
        if (parentAgentNo == null) {
            // 一级代理
            commissionValueVO.setLevelOneUserId(mallSaleAgent.getUserNo());
            commissionValueVO.setLevelOneCommissionMode(String.valueOf(mallSaleAgent.getCommissionMode()));
            commissionValueVO.setLevelOneCommissionRate(String.valueOf(mallSaleAgent.getCommissionValue()));
        } else {
            // 二级代理
            commissionValueVO.setLevelTwoUserId(mallSaleAgent.getUserNo());
            commissionValueVO.setLevelTwoCommissionMode(String.valueOf(mallSaleAgent.getCommissionMode()));
            commissionValueVO.setLevelTwoCommissionRate(String.valueOf(mallSaleAgent.getCommissionValue()));
            // 获取该二级代理所属一级代理的信息
            MallSaleAgentDO parentAgent = getMallSaleAgent(companyNo, parentAgentNo);
            commissionValueVO.setLevelOneUserId(parentAgent.getUserNo());
            commissionValueVO.setLevelOneCommissionMode(String.valueOf(parentAgent.getCommissionMode()));
            commissionValueVO.setLevelOneCommissionRate(String.valueOf(parentAgent.getCommissionValue()));
        }

        return commissionValueVO;
    }
    
    /**
     * 根据userNo查询代理的信息（如头像，登录名等）
     * @author xiajun
     * @param userNo
     * @return
     */
    @Override
    public MallSaleAgentDO queryAgentInfoByUserNo(String userNo) {
    	return mallSaleAgentDOMapper.queryAgentInfoByUserNo(userNo);
    }

}
