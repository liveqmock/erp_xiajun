package com.wangqin.globalshop.purchase.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemCategoryDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.PurchaseItemCategoryMapperExt;
import com.wangqin.globalshop.common.result.RetCode;
import com.wangqin.globalshop.common.utils.ErrorResult;
import com.wangqin.globalshop.common.utils.ListResult;
import com.wangqin.globalshop.common.utils.SingleResult;
import com.wangqin.globalshop.purchase.model.ItemCategoryDOExt;


@Controller
@ResponseBody
@RequestMapping("/api/data/")
public class ApiDataController {

    @Autowired
    PurchaseItemCategoryMapperExt categoryMapper;

    /**
     * 分类数据列表，返回1 2级
     * 
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping("/list")
    public String dataList() {
        List<ItemCategoryDO> categoryList = categoryMapper.selectByTop();
        List<ItemCategoryDOExt> categoryExtList = new ArrayList<>();
        List<ItemCategoryDO> categoryChildList = new ArrayList<>();
        if (!categoryList.isEmpty()) {
            categoryChildList = categoryMapper.selectListByPidList(categoryList);
        }
        Iterator iter = categoryChildList.iterator();
        for (ItemCategoryDO category : categoryList) {
            ItemCategoryDOExt categoryExt = new ItemCategoryDOExt();
            BeanCopier copy = BeanCopier.create(ItemCategoryDO.class, ItemCategoryDOExt.class, false);
            copy.copy(category, categoryExt, null);
            List<ItemCategoryDO> categoryTempList = new ArrayList<>();
            for (iter = categoryChildList.iterator(); iter.hasNext();) {
                ItemCategoryDO categoryTemp = (ItemCategoryDO) iter.next();
                if (categoryTemp.getpCode().equals(category.getpCode())) {
                    categoryTempList.add(categoryTemp);
                    iter.remove();
                }
            }
            categoryExt.setCategoryList(categoryTempList);
            categoryExtList.add(categoryExt);
        }
        Map<String, Object> resut = new HashMap<>();
        resut.put("category", categoryExtList);
        return new SingleResult<>(resut).toJSONString();
    }

    /**
     * 返回下级分类
     * 
     * @param pid
     * @return
     */
    @RequestMapping("/child_list")
    public String childList(String pCode) {
        if (StringUtil.isBlank(pCode)) {
            return new ErrorResult(RetCode.ILLEGAL_ARGS).toJSONString();
        }
        List<ItemCategoryDO> categoryList = categoryMapper.selectByPid(pCode);
        return new ListResult<>(categoryList).toJSONString();
    }

}
