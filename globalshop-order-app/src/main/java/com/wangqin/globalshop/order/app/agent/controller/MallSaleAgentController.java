package com.wangqin.globalshop.order.app.agent.controller;

import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.bean.dataVo.*;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSaleAgentDO;
import com.wangqin.globalshop.biz1.app.exception.BizCommonException;
import com.wangqin.globalshop.order.app.agent.service.MallSaleAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 与代理管理相关的 controller
 *
 * @author angus
 * @date 2018/7/31
 */
@Authenticated
@RestController
@RequestMapping("/mallSaleAgent")
public class MallSaleAgentController {

    @Autowired
    private MallSaleAgentService mallSaleAgentService;

    /**
     * 添加代理
     *
     * @param mallSaleAgentDO
     * @return
     */
    @PostMapping("/addMallSaleAgent")
    public Object addMallSaleAgent(MallSaleAgentDO mallSaleAgentDO) {

        JsonResult result = new JsonResult();

        try {
            mallSaleAgentService.insertMallSaleAgent(mallSaleAgentDO);
            result.buildIsSuccess(true);
        } catch (BizCommonException e) {
            result.buildMsg(e.getMessage())
                    .buildIsSuccess(false);
        } catch (Exception ex) {
            ex.printStackTrace();
            result.buildMsg("操作出现异常")
                    .buildIsSuccess(false);
        }

        return result;
    }


    /**
     * 查询代理
     * <li> 搜索指定条件下的代理
     * <li> 列出一级代理（parent_agent 字段为 null）
     * <li> 列出二级代理（parent_agent 字段需为 一级代理 user_no）
     *
     * @param mallSaleAgentQueryVO
     * @param pageQueryParam
     * @return
     */
    @PostMapping("/queryMallSaleAgents")
    public Object queryMallSaleAgents(MallSaleAgentQueryVO mallSaleAgentQueryVO, PageQueryParam pageQueryParam) {

        JsonPageResult<List<MallSaleAgentItemVO>> result = new JsonPageResult<>();

        try {
            // TODO: 后期建议将一级代理和二级代理查询分开，简化逻辑
            List<MallSaleAgentItemVO> mallSaleAgentItemVOList =
                    mallSaleAgentService.listMallSaleAgents(mallSaleAgentQueryVO, pageQueryParam);
            int totalCount = mallSaleAgentService.countMallSaleAgents(mallSaleAgentQueryVO);
            result.buildData(mallSaleAgentItemVOList)
                    .buildTotalCount(totalCount)
                    .buildIsSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.buildMsg("查询出现异常")
                    .buildIsSuccess(false);
        }

        return result;
    }

    /**
     * 代理信息修改
     * <li> 编辑信息
     * <li> 解除代理
     * <li> 删除
     *
     * @param mallSaleAgentDO
     * @return
     */
    @PostMapping("/updateMallSaleAgent")
    public Object updateMallSaleAgent(MallSaleAgentDO mallSaleAgentDO) {

        JsonResult result = new JsonResult();

        try {
            mallSaleAgentService.updateMallSaleAgent(mallSaleAgentDO);
            result.buildIsSuccess(true);
        } catch (BizCommonException e) {
            result.buildMsg(e.getMessage())
                    .buildIsSuccess(false);
        } catch (Exception ex) {
            ex.printStackTrace();
            result.buildMsg("操作出现异常")
                    .buildIsSuccess(false);
        }

        return result;
    }


    /**
     * 分佣比率修改
     *
     * @param userNo
     * @param commissionMode
     * @param commissionValue
     * @return
     */
    @PostMapping("/updateCommissionValue")
    public Object updateCommissionValue(String userNo, Long commissionMode, Double commissionValue) {
        JsonResult result = new JsonResult();

        try {
            mallSaleAgentService.updateCommissionValue(userNo, commissionMode, commissionValue);
            result.buildIsSuccess(true);
        } catch (BizCommonException e) {
            result.buildMsg(e.getMessage())
                    .buildIsSuccess(false);
        } catch (Exception ex) {
            ex.printStackTrace();
            result.buildMsg("操作出现异常")
                    .buildIsSuccess(false);
        }

        return result;
    }

    /**
     * 根据分享 userNo 查询代理的分佣比率
     *
     * @param userNo
     * @param companyNo
     * @return
     */
    @GetMapping("/queryCommissionValue")
    public Object queryCommissionValue(@RequestParam("agentUserId") String userNo,
                                       @RequestParam("companyNo") String companyNo) {

        JsonResult<CommissionValueVO> result = new JsonResult<>();

        try {
            CommissionValueVO commissionValueVO = mallSaleAgentService.queryCommissionValue(userNo, companyNo);
            result.buildData(commissionValueVO)
                    .buildIsSuccess(true);
        } catch (BizCommonException e) {
            result.buildMsg(e.getMessage())
                    .buildIsSuccess(false);
        } catch (Exception ex) {
            ex.printStackTrace();
            result.buildMsg("查询出现异常")
                    .buildIsSuccess(false);
        }

        return result;
    }
}
