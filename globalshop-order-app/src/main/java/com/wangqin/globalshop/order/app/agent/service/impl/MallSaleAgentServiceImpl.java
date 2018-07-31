package com.wangqin.globalshop.order.app.agent.service.impl;

import com.wangqin.globalshop.biz1.app.bean.dataVo.PageQueryParam;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSaleAgentDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.MallSaleAgentDOMapperExt;
import com.wangqin.globalshop.common.utils.StringUtil;
import com.wangqin.globalshop.order.app.agent.service.MallSaleAgentService;
import com.wangqin.globalshop.biz1.app.bean.dataVo.MallSaleAgentItemVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.MallSaleAgentQueryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public MallSaleAgentDO getByCompanyNoAndUserNo(String companyNo, String userNo) {
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

            // 查询二级代理，需要为每个二级代理添加其一级代理姓名
            MallSaleAgentDO parentMallSaleAgentDO =
                    getByCompanyNoAndUserNo(mallSaleAgentQueryVO.getCompanyNo(), parentAgent);

            mallSaleAgentItemVOList.forEach(mallSaleAgentItemVO -> {
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
    public void insertMallSaleAgent(MallSaleAgentDO mallSaleAgentDO) {
        // 要从 auth_user 表获取信息
    }

    @Override
    public void updateMallSaleAgent(MallSaleAgentDO mallSaleAgentDO) {
        // 根据 company_no 和 user_no 唯一确定 MallSaleAgent，将其更新
    }
}
