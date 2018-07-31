package com.wangqin.globalshop.order.app.agent.controller;

import com.wangqin.globalshop.biz1.app.bean.dataVo.*;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSaleAgentDO;
import com.wangqin.globalshop.biz1.app.exception.BizCommonException;
import com.wangqin.globalshop.order.app.agent.service.MallSaleAgentService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 与代理管理相关的 controller
 *
 * @author angus
 * @date 2018/7/31
 */
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
     * <li> 列出二级代理（parent_agent 字段为 一级代理 user_no）
     *
     * @param mallSaleAgentQueryVO
     * @param pageQueryParam
     * @return
     */
    @PostMapping("/queryMallSaleAgents")
    public Object queryMallSaleAgents(MallSaleAgentQueryVO mallSaleAgentQueryVO, PageQueryParam pageQueryParam) {

        JsonPageResult<List<MallSaleAgentItemVO>> result = new JsonPageResult<>();

        try {
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
     * <li> 佣金修改
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
     * 根据分享 userNo 查询代理的分佣比率
     * @param userNo
     * @param companyNo
     * @return
     */
    @GetMapping("/queryCommissionValue")
    public Object queryCommissionValue(String userNo, String companyNo) {

        JsonResult<CommissionValueVO> result = new JsonResult<>();

        try {
            CommissionValueVO commissionValueVO = mallSaleAgentService.queryCommissionValue(userNo, companyNo);
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
