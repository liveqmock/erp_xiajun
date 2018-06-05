package com.wangqin.globalshop.deal.app.controller;

import com.wangqin.globalshop.biz1.app.dal.dataObject.DealerTypeDO;
import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.deal.app.service.IDealerService;
import com.wangqin.globalshop.deal.app.service.IDealerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author biscuit
 * @data 2018/06/05
 */
@Controller
@RequestMapping("/sellerType")
public class DealerTypeController {

    @Autowired
    private IDealerTypeService iSellerTypeService;

    @Autowired
    private IDealerService isellerService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Object add(DealerTypeDO sellerType) {
        JsonResult result = new JsonResult<>();
        iSellerTypeService.insert(sellerType);
//        JsonResult<String> result = new JsonResult<>();
//
//        if (null == sellerType.getId()) {
//            SellerTypeQueryVO sellerTypeQueryVO = new SellerTypeQueryVO();
//            sellerTypeQueryVO.setCode(sellerType.getCode());
//            //Check code is unique
//            Integer count = iSellerTypeService.querySellerTypesCount(sellerTypeQueryVO);
//            if (count > 0) {
//                result.buildMsg("经销商类别代码不可以重复").buildIsSuccess(false);
//            } else {
//                sellerType.setGmtCreate(new Date());
//                sellerType.setGmtModify(new Date());
//                result.buildIsSuccess(iSellerTypeService.insert(sellerType));
//            }
//        } else {
//            result.buildMsg("错误数据").buildIsSuccess(false);
//        }

//        return result;
        return result.buildIsSuccess(true);
    }

    @RequestMapping("/update")
    @ResponseBody
    public Object update(DealerTypeDO sellerType) {
//        JsonResult<String> result = new JsonResult<>();
//
//        if (null != sellerType.getId()) {
//            DealerTypeDO tjSellerType = new DealerTypeDO();
//            tjSellerType.setCode(sellerType.getCode());
//            DealerTypeDO selSellerType = iSellerTypeService.selectOne(tjSellerType);
//            if(selSellerType!=null && selSellerType.getId().equals(sellerType.getId())) {
//                return result.buildMsg("经销商类别代码不可以重复").buildIsSuccess(false);
//            }
//
//            sellerType.setGmtCreate(null);
//            sellerType.setGmtModify(new Date());
//            //TODO: JC, 事务或存储过程？
//            boolean bIsSuccess = iSellerTypeService.updateSelectiveById(sellerType);
//
//            if (bIsSuccess) {
//                //update Seller table
//                isellerService.updateSellerTypeByTypeCode(sellerType);
//            }
//
//            result.buildIsSuccess(bIsSuccess);
//        } else {
//            result.buildMsg("错误数据").buildIsSuccess(false);
//        }
//
//        return result;
        return null;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(DealerTypeDO sellerType) {
//        JsonResult<String> result = new JsonResult<>();
//
//        if (null != sellerType.getId()) {
//            SellerQueryVO sellerQueryVO = new SellerQueryVO();
//            sellerQueryVO.setTypeId(sellerType.getId());
//
//            Integer count = isellerService.querySellersCount(sellerQueryVO);
//            if (count > 0) {
//                result.buildMsg("经销商类别信息被使用，无法删除").buildIsSuccess(false);
//            } else {
//                result.buildIsSuccess(iSellerTypeService.deleteSelective(sellerType));
//            }
//        } else {
//            result.buildMsg("错误数据").buildIsSuccess(false);
//        }
//
//        return result;
        return null;
    }

    @RequestMapping("/query")
    @ResponseBody
    public Object query(Long id) {
//        JsonResult<DealerTypeDO> result = new JsonResult<>();
//
//        return result.buildData(iSellerTypeService.selectById(id)).buildIsSuccess(true);
        return null;
    }

    @RequestMapping("/querySellerTypeList")
    @ResponseBody
    public Object querySellerTypeList() {
        JsonResult<List<DealerTypeDO>> result = new JsonResult<>();
        List<DealerTypeDO> list = iSellerTypeService.list();
        result.setData(list);
//        JsonResult<List<DealerTypeDO>> result = new JsonResult<>();
//        List<DealerTypeDO> list = iSellerTypeService.querySellerTypeList(sellerTypeQueryVO);
//        result.setData(list);
//
//        return result.buildIsSuccess(true);
        return result.buildIsSuccess(true);
    }
}

