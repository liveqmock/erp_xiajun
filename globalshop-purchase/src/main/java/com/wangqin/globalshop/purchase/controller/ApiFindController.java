package com.wangqin.globalshop.purchase.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemFindDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemCategoryDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemFindDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemSkuDOMapperExt;
import com.wangqin.globalshop.common.aliyun.OSSObjectApi;
import com.wangqin.globalshop.common.result.RetCode;
import com.wangqin.globalshop.common.utils.ErrorResult;
import com.wangqin.globalshop.common.utils.ResourceUtil;
import com.wangqin.globalshop.common.utils.SingleResult;
import com.wangqin.globalshop.common.utils.SuccessResult;
import com.wangqin.globalshop.common.utils.UploadUtil;

/**
 * 发现功能模块 类ApiFindController.java的实现描述：
 * 
 * @author sivanblack 2018年4月24日 上午10:38:55
 */
@Controller
@ResponseBody
@RequestMapping("/api/find")
public class ApiFindController {

    @Autowired
    ItemDOMapperExt     itemDOMapper;
    @Autowired
    ItemFindDOMapperExt itemFindDOMapperExt;
    @Autowired
    ItemSkuDOMapperExt  itemSkuDOMapperExt;
    @Autowired
    ItemCategoryDOMapperExt itemCategoryDOMapperExt;

    /**
     * 删除发现商品
     * 
     * @param id
     * @return
     */
    @Transactional
    @RequestMapping("/delItem")
    public String delItem(Long id) {
        if (id == null) {
            return new ErrorResult(RetCode.ILLEGAL_ARGS, "参数错误").toJSONString();
        }
        ItemFindDO itemFindDO = itemFindDOMapperExt.selectByPrimaryKey(id);
        itemFindDO.setIsDel(true);
        int result = itemFindDOMapperExt.updateByPrimaryKeySelective(itemFindDO);
        if (result > 0) {
            return new SuccessResult().toJSONString();
        }
        return new ErrorResult(RetCode.SYS_ERROR, "删除失败").toJSONString();
    }


    /**
     * 上传图片
     * 
     * @param file
     * @return
     */
    @ResponseBody
    @RequestMapping("/upload")
    public String uploadAvatar(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        map = UploadUtil.upload(request, "item");
        List<String> pictureList = new ArrayList<>();
        Map<String, Object> results = new HashMap<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String filePath = entry.getValue();
            File tempFile = new File(ResourceUtil.getImageUploadPath() + filePath);
            StringBuilder fileUrlStrBuf = new StringBuilder("");
            String imgName = UUID.randomUUID().toString();
            fileUrlStrBuf.append(imgName + ".jpg");
            FileInputStream fileStream = null;
            boolean result = false;
            try {
                fileStream = new FileInputStream(tempFile);
                result = OSSObjectApi.uploadFile(fileStream, tempFile.length(), null, fileUrlStrBuf.toString());
                FileUtils.forceDelete(tempFile);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (fileStream != null) {
                    try {
                        fileStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (result) {
                pictureList.add(ResourceUtil.getImageDomain() + "/" + fileUrlStrBuf.toString());
            }
        }
        if (pictureList.isEmpty()) {
            return new ErrorResult(RetCode.SYS_ERROR, "上传失败").toJSONString();
        }
        results.put("pictureList", pictureList);
        return new SingleResult<>(results).toJSONString();
    }

    @RequestMapping("/skuList")
    public String skuList(String itemCode) {
        if (itemCode == null) {
            return new ErrorResult(RetCode.ILLEGAL_ARGS).toJSONString();
        }
        List<ItemSkuDO> findItemSkuList = itemSkuDOMapperExt.selectByItemCode(itemCode);
        Map<String, Object> result = new HashMap<>(1);
        result.put("skuList", findItemSkuList);
        return new SingleResult<>(result).toJSONString();
    }
    
    /**
     * 扫码返回
     * 
     * @param upc
     * @return
     */
    @RequestMapping("/scan")
    public String scan(String upc) {
        ItemSkuDO findItemSku = itemSkuDOMapperExt.selectByUpc(upc);
        if (findItemSku == null) {
            return new ErrorResult(RetCode.NO_DATA, "商品不存在数据").toJSONString();
        }
        return new SingleResult<>(findItemSku.getItemCode()).toJSONString();
    }

}
