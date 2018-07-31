package com.wangqin.globalshop.purchase.app.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.wangqin.globalshop.biz1.app.exception.BizCommonException;
import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.enums.TaskDailyStatus;
import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerTaskDO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.BuyerTaskVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.JsonResult;
import com.wangqin.globalshop.common.utils.HaiJsonUtils;
import com.wangqin.globalshop.common.utils.excel.ReadExcel;
import com.wangqin.globalshop.purchase.app.service.IBuyerTaskService;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author biscuit
 * @data 2018/06/19
 */
@Controller
@ResponseBody
@RequestMapping("/purchaseTask")
@Authenticated
public class BuyerTaskController {
    @Autowired
    private IBuyerTaskService buyerTaskService;

    @RequestMapping("/improtTask")
    public Object importTask(MultipartFile file) {
        JsonResult<Object> result = new JsonResult<>();
        try {
            if (!file.isEmpty()) {
                // 文件保存路径
                List<List<Object>> list = ReadExcel.readExcel(file.getInputStream(),file.getOriginalFilename(),1,0,5);
                buyerTaskService.importTask(list);
            }
        } catch (IOException e) {
            return result.buildIsSuccess(false).buildMsg("文件上传错误，请重试");
        } catch (BizCommonException e) {
            String str = e.getErrorMsg().replace(",", "</br>");
            return result.buildIsSuccess(false).buildMsg(str);
        } catch (Exception e) {
            return result.buildIsSuccess(false).buildMsg(e.getMessage());
        }
        return result.buildIsSuccess(true).buildMsg("上传成功");
    }



    /**
     * 查询采购任务的明细
     * @return
     */
    @PostMapping("/queryTaskDailyList")
    public Object queryTaskDailyList(BuyerTaskVO buyerTaskVO) {
        JsonResult<List<BuyerTaskVO>> result = new JsonResult<>();
        List<BuyerTaskVO> buyerTaskList = null;
        try {
            buyerTaskList = buyerTaskService.list(buyerTaskVO);
        } catch (Exception e) {
            return result.buildIsSuccess(false).buildMsg(e.getMessage());
        }
        return result.buildData(buyerTaskList).buildIsSuccess(true);
    }

    /**
     * 新增采购任务
     * @param buyerTaskDO
     * @return
     */
    @PostMapping("/add")
    public Object addTask(BuyerTaskVO buyerTaskDO) {
        JsonResult<List<BuyerTaskDO>> result = new JsonResult<>();
        try {
            buyerTaskService.add(buyerTaskDO);
        } catch (BizCommonException e) {
			return result.buildIsSuccess(false).buildMsg(e.getErrorMsg());
		} catch (Exception e) {
            return result.buildIsSuccess(false).buildMsg(e.toString());
        }
        return result.buildIsSuccess(true);
    }


	/**
	 * 更新采购任务
	 *
	 * @param
	 * @return
	 */
	@PostMapping("/update")
	public Object update(BuyerTaskVO buyerTaskDO) {
		JsonResult<List<BuyerTaskDO>> result = new JsonResult<>();
		try {
			buyerTaskService.update(buyerTaskDO);
		} catch (BizCommonException e) {
			return result.buildIsSuccess(false).buildMsg(e.getErrorMsg());
		} catch (Exception e) {
			return result.buildIsSuccess(false).buildMsg(e.toString());
		}
		return result.buildIsSuccess(true);
	}




    /**
     * 查询该买手的采购任务
     * @param
     * @return
     */
    @PostMapping("/queryBuyerTaskList")
    public Object queryBuyerTaskList(BuyerTaskVO buyerTaskVO) {
        JsonResult<List<BuyerTaskVO>> result = new JsonResult<>();
        try {
            List<BuyerTaskVO> list = buyerTaskService.list(buyerTaskVO);
            result.buildData(list);
        } catch (Exception e) {
            return result.buildIsSuccess(false).buildMsg(e.getMessage());
        }
        return result.buildIsSuccess(true);
    }


    /**
     * 查询单个采购任务
     *
     * @param
     * @return
     */
    @RequestMapping("/query")
    @ResponseBody
    public Object query(Long id) {
        JsonResult<BuyerTaskVO> result = new JsonResult<>();
        if (id == null) {
            return result.buildIsSuccess(false).buildMsg("id 不能为 null");
        }
		try {
			BuyerTaskVO td = buyerTaskService.selectVoById(id);
			return result.buildIsSuccess(true).buildData(td);
		} catch (BizCommonException ex) {
			ex.printStackTrace();
			return result.buildIsSuccess(false).buildMsg(ex.getErrorMsg());
		} catch (Exception ex) {
			ex.printStackTrace();
			return result.buildIsSuccess(false).buildMsg("未知异常");
		}

    }

	/**
	 * 查询单个采购任务
	 *
	 * @param
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(Long id) {
		JsonResult<BuyerTaskVO> result = new JsonResult<>();
		if (id == null) {
			return result.buildIsSuccess(false).buildMsg("id 不能为 null");
		}
		try {
			buyerTaskService.delete(id);
			return result.buildIsSuccess(true).buildMsg("删除成功");
		} catch (BizCommonException ex) {
			ex.printStackTrace();
			return result.buildIsSuccess(false).buildMsg(ex.getErrorMsg());
		} catch (Exception ex) {
			ex.printStackTrace();
			return result.buildIsSuccess(false).buildMsg("未知异常");
		}

	}

    /**
     * 完成采购按钮：采购中->采购结束
     *
     * @return
     */
    @RequestMapping("/finishTaskDaily")
    @ResponseBody
    public Object finishTaskDaily(String taskDailyIds) {
        JsonResult<String> result = new JsonResult<>();
        if (StringUtil.isBlank(taskDailyIds)) {
            return result.buildIsSuccess(false).buildMsg("未选中采购任务");
        }
        String s = taskDailyIds.replace("&quot;", "\"");
        List<Long> taskDailyIdList = HaiJsonUtils.toBean(s, new TypeReference<List<Long>>() {
        });
        try {
            buyerTaskService.updateTaskStatus(TaskDailyStatus.CONFIRM.getCode(),taskDailyIdList);
            return result.buildIsSuccess(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            return result.buildIsSuccess(false).buildMsg("未知异常");
        }
    }

    /**
     * 取消采购，任意状态->采购取消
     *
     * @return
     */
    @RequestMapping("/closeTaskDaily")
    @ResponseBody
    public Object closeTaskDaily(String taskDailyIds) {
        JsonResult<String> result = new JsonResult<>();
        if (StringUtil.isBlank(taskDailyIds)) {
            return result.buildIsSuccess(false).buildMsg("未选中采购任务");
        }
        String s = taskDailyIds.replace("&quot;", "\"");
        List<Long> taskDailyIdList = HaiJsonUtils.toBean(s, new TypeReference<List<Long>>() {
        });
        try {
            buyerTaskService.updateTaskStatus(TaskDailyStatus.CLOSE.getCode(),taskDailyIdList);
            return result.buildIsSuccess(true);
        } catch (Exception ex) {
            return result.buildIsSuccess(false).buildMsg("未知异常");
        }
    }
}
