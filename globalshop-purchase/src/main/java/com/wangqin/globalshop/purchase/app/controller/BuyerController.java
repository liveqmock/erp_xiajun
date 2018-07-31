package com.wangqin.globalshop.purchase.app.controller;

import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.BuyerTaskDetailDOMapperExt;
import com.wangqin.globalshop.biz1.app.bean.dataVo.JsonResult;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.HaiJsonUtils;
import com.wangqin.globalshop.common.utils.PicModel;
import com.wangqin.globalshop.common.utils.StringUtil;
import com.wangqin.globalshop.common.utils.excel.ExcelHelper;
import com.wangqin.globalshop.biz1.app.bean.dataVo.BuyerTaskExportDTO;
import com.wangqin.globalshop.purchase.app.service.IBuyerService;
import com.wangqin.globalshop.purchase.app.service.IBuyerTaskService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author biscuit
 * @data 2018/06/12
 * 买手相关Controller
 */
@Controller
@Authenticated
@ResponseBody
@RequestMapping(value = {"/purchase"})
public class BuyerController {
    @Autowired
    private IBuyerService service;
    @Autowired
    private IBuyerTaskService buyerTaskService;
    @Autowired
    private BuyerTaskDetailDOMapperExt buyerTaskDetailDOMapperExt;



    /**
     * 查询买手
     * @return
     */
    @PostMapping(value = {"/queryBuyers"})
    public Object queryWxPurchaseUser() {
        JsonResult<List<BuyerDO>> result = new JsonResult<>();
        try {
            List<BuyerDO> list = service.list();
            result.buildData(list);
        } catch (Exception e) {
            return result.buildIsSuccess(false).buildMsg(e.toString());
        }
        return result.buildIsSuccess(true);

    }



    @PostMapping("/setCommission")
    @Transactional(rollbackFor = ErpCommonException.class)
    public Object setCommission(Long id, String purchaseCommissionStr, Long purchaseCommissionMode) {
        try {
            BuyerDO buyer = service.selectByPrimaryKey(id);
            buyer.setPurchaseCommissionMode(purchaseCommissionMode);
            buyer.setPurchaseCommissionStr(purchaseCommissionStr);
            service.updateByPrimaryKey(buyer);
        } catch (Exception e) {
            return JsonResult.buildSuccess(false).buildMsg(e.toString());
        }
        return JsonResult.buildSuccess(true);
    }

    @GetMapping("/queryBuyerById")
    public Object setCommission(Long id) {
        BuyerDO buyer = service.selectByPrimaryKey(id);
        return JsonResult.buildSuccess(buyer);
    }

    @PostMapping("/add")
    public Object add(BuyerDO buyer) {
        try {
            buyer.initCompany();
            buyer.init();
            service.insert(buyer);
        } catch (Exception e) {
            return JsonResult.buildSuccess(false).buildMsg(e.toString());
        }
        return JsonResult.buildSuccess(buyer);
    }


    @PostMapping("/update")
    @Transactional(rollbackFor = ErpCommonException.class)
    public Object update(BuyerDO buyer) {
        try {
            BuyerDO buyerOld = service.selectByPrimaryKey(buyer.getId());
            buyerOld.setCompanyNo(AppUtil.getLoginUserCompanyNo());
            buyerOld.setNickName(buyer.getNickName());
            //仓库暂时不添加，后续再维护
            buyerOld.setPurchaseCommissionStr(buyer.getPurchaseCommissionStr());
            service.updateByPrimaryKey(buyerOld);
        } catch (Exception e) {
            return JsonResult.buildSuccess(false).buildMsg(e.toString());
        }
        return JsonResult.buildSuccess(buyer);
    }

    @PostMapping("/delete")
    public Object delete(Long id) {
        try {
            service.deleteSoft(id);
        } catch (Exception e) {
            e.printStackTrace();
			return JsonResult.buildFailed("");
        }
        return JsonResult.buildSuccess(true);
    }

    //本接口提供功能采用原参数，后续再规划调整
    /**
     * 根据采购单taskNo导出excel
     *
     * @param buyerTaskNo
     */
    @RequestMapping("/taskDailyExport")
    public ResponseEntity<byte[]> taskDailyExport(String buyerTaskNo) throws Exception {
        List<List<Object>> rowDatas = new ArrayList<>();
        List<BuyerTaskExportDTO> itemSkuList = buyerTaskDetailDOMapperExt.taskDailyExportByTaskNo(buyerTaskNo);
        if (itemSkuList != null) {
            int i = 0;
            for (BuyerTaskExportDTO item : itemSkuList) {
                List<Object> list = new ArrayList<>();
                // 序号
                // list.add(++i);
                // sku图片
                String skuPic = item.getSkuPic();
                if (StringUtil.isNotBlank(skuPic)) {
                    PicModel pm = HaiJsonUtils.toBean(skuPic, PicModel.class);
                    String imgSrc = pm.getPicList().get(0).getUrl();
                    // System.out.println(imgSrc);
                    URL url = new URL(imgSrc);
                    //取图片类型
                    String imgType = url.getPath().substring(url.getPath().lastIndexOf(".") + 1).toUpperCase();
                    if (imgSrc.contains("aliyuncs.com")) {
                        imgSrc += "?x-oss-process=image/resize,m_lfit,h_100,w_100";
                    } else {
                        imgSrc = imgSrc.replaceAll("Resource", "Thumbnail");
                    }
                    //取图
                    BufferedImage image = ImageIO.read(url);
                    ByteArrayOutputStream output = new ByteArrayOutputStream();
                    ImageIO.write(image, imgType, output);
                    output.flush();
                    list.add(output.toByteArray());
                    output.close();
                } else {
                    list.add(null);
                }

                list.add(item.getItemName()); // 商品名称
                list.add(item.getSkuCode()); // SKU代码
                list.add(item.getUpc()); // UPC
                // list.add(item.getBrandName()); //品牌
                list.add(item.getColor()); // 颜色
                list.add(item.getScale()); // 尺码
                list.add(""); //item.getBuySite()// 采购站点
                list.add(item.getCount()); // 采购数量
                list.add(item.getEntryCount()==null?0:item.getEntryCount()); // 入库数量
                rowDatas.add(list);
            }
        }
        ExcelHelper excelHelper = new ExcelHelper();
        String[] columnTitles = new String[] { "商品图片", "商品名称", "SKU代码", "UPC", "颜色", "尺码", "采购站点", "采购数量", "入库数量" };
        Integer[] columnWidth = new Integer[] { 15, 30, 25, 25, 15, 15, 15, 15, 15, 15 };
        //TODO 根据imgUrl 获取 图片格式
        excelHelper.setTaskDailyToSheet("采购任务", columnTitles, rowDatas, columnWidth, Workbook.PICTURE_TYPE_JPEG);
        // excelHelper.writeToFile("/Users/liuyang/Work/test.xls");

        ResponseEntity<byte[]> filebyte = null;
        ByteArrayOutputStream out = excelHelper.writeToByteArrayOutputStream();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        String fileName = "采购单_" + ((itemSkuList==null|| itemSkuList.size()==0)?"":itemSkuList.get(0).getItemName()) + ".xlsx";
        headers.setContentDispositionFormData("attachment", new String(fileName.getBytes("utf-8"), "ISO8859-1"));

        filebyte = new ResponseEntity<byte[]>(out.toByteArray(), headers, HttpStatus.OK);
        out.close();
        excelHelper.close();
        return filebyte;
    }

}
