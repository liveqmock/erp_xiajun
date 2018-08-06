package com.wangqin.globalshop.order.app.agent.controller;

import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.bean.dataVo.*;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSaleAgentDO;
import com.wangqin.globalshop.biz1.app.exception.BizCommonException;
import com.wangqin.globalshop.common.utils.BigDecimalHelper;
import com.wangqin.globalshop.common.utils.StringUtil;
import com.wangqin.globalshop.order.app.agent.service.MallSaleAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
     * 代理信息更新
     *
     * @param mallSaleAgentEditVO
     * @return
     */
    @PostMapping("/updateMallSaleAgent")
    public Object updateMallSaleAgent(MallSaleAgentEditVO mallSaleAgentEditVO) {

        JsonResult result = new JsonResult();

        try {
            mallSaleAgentService.updateMallSaleAgent(mallSaleAgentEditVO);
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
     * @param mallSaleAgentEditVO
     * @return
     */
    @PostMapping("/updateCommissionValue")
    public Object updateCommissionValue(MallSaleAgentEditVO mallSaleAgentEditVO) {
        JsonResult result = new JsonResult();

        try {
            if(!StringUtil.isEmpty(mallSaleAgentEditVO.getCommissionValueStr()))
            {
                try{
                    BigDecimal value= BigDecimal.valueOf(Double.parseDouble(mallSaleAgentEditVO.getCommissionValueStr()));
                    value=value.divide(new BigDecimal(100));
                    mallSaleAgentEditVO.setCommissionValue(value.doubleValue());
                }catch (Exception e){
                    result.buildMsg("非正常数字: CommissionValueStr"+mallSaleAgentEditVO.getCommissionValueStr())
                            .buildIsSuccess(false);
                }
            }
            mallSaleAgentService.updateCommissionValue(mallSaleAgentEditVO);
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
     * 解除代理
     *
     * @param userNo
     * @return
     */
    public Object removeMallSaleAgent(String userNo) {
        JsonResult result = new JsonResult();
        try {
            mallSaleAgentService.removeMallSaleAgent(userNo);
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
     * 删除代理
     *
     * @param userNo
     * @return
     */
    public Object deleteMallSaleAgent(String userNo) {
        JsonResult result = new JsonResult();
        try {
            mallSaleAgentService.deleteMallSaleAgent(userNo);
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
