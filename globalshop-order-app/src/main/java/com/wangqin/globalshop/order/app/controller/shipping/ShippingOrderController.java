package com.wangqin.globalshop.order.app.controller.shipping;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.constants.enums.OrderStatus;
import com.wangqin.globalshop.biz1.app.constants.enums.ShippingOrderType;
import com.wangqin.globalshop.biz1.app.dal.dataObject.*;
import com.wangqin.globalshop.biz1.app.dal.dataVo.ShippingTrackVO;
import com.wangqin.globalshop.biz1.app.dto.MultiDeliveryFormDTO;
import com.wangqin.globalshop.biz1.app.vo.JsonPageResult;
import com.wangqin.globalshop.biz1.app.vo.JsonResult;
import com.wangqin.globalshop.biz1.app.vo.ShippingOrderVO;
import com.wangqin.globalshop.common.enums.StockUpStatus;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.exception.InventoryException;
import com.wangqin.globalshop.common.utils.*;
import com.wangqin.globalshop.common.utils.excel.ExcelHelper;
import com.wangqin.globalshop.order.app.kuaidi_bean.CommonShippingTrack;
import com.wangqin.globalshop.order.app.kuaidi_bean.CommonShippingTrackNode;
import com.wangqin.globalshop.order.app.kuaidi_bean.Kuaidi100ShippingTrackResult;
import com.wangqin.globalshop.order.app.service.shipping.haihu.IHaihuService;
import com.wangqin.globalshop.order.app.service.mall.IMallOrderService;
import com.wangqin.globalshop.order.app.service.mall.IMallSubOrderService;
import com.wangqin.globalshop.order.app.service.mall.OrderMallCustomerService;
import com.wangqin.globalshop.order.app.service.shipping.IShippingOrderService;
import com.wangqin.globalshop.order.app.service.shipping.IShippingTrackService;
import com.wangqin.globalshop.order.app.service.shipping.kuaidi100.IKuaidi100Service;
import com.wangqin.globalshop.order.app.service.shipping.sifang.ISiFangService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * @author biscuits
 * @date 2018/5/24
 */
@Controller
@RequestMapping("/shippingOrder")
@Authenticated
public class ShippingOrderController {

    @Autowired
    private IShippingOrderService shippingOrderService;
    @Autowired
    private IMallSubOrderService mallSubOrderService;
    @Autowired
    private ISiFangService siFangService;
    @Autowired
    private OrderMallCustomerService mallCustomerService;
    @Autowired
    private IMallOrderService mallOrderService;
    @Autowired
    private IHaihuService haihuService;
    @Autowired
    private IShippingTrackService shippingTrackService;
    @Autowired
    private IKuaidi100Service kuaidi100Service;

    @RequestMapping("/queryShippingTrack")
    @ResponseBody
    public Object query(ShippingOrderVO shippingOrderVO) {
        JsonResult<List<ShippingOrderDO>> result = new JsonResult<>();
        try {
            List<ShippingOrderDO> list = shippingOrderService.queryShippingOrders(shippingOrderVO);
            result.buildData(list);
            result.setSuccess(true);
        } catch (ErpCommonException e) {
            result.buildMsg(e.getErrorMsg());
            result.buildIsSuccess(false);
        } catch (Exception ex) {
            ex.printStackTrace();
            result.buildMsg("未知异常");
            result.buildIsSuccess(false);
        }
        return result;
    }

    // 合单发货表单
    @RequestMapping("/multiDeliveryForm")
    @ResponseBody
    public Object multiDeliveryForm(String erpOrderId) {
        JsonResult<MultiDeliveryFormDTO> result = new JsonResult();
        MultiDeliveryFormDTO dto;
        try {
            dto = shippingOrderService.queryByOrderId(erpOrderId);
            result.buildData(dto).buildIsSuccess(true);
        } catch (ErpCommonException e) {
            result.buildMsg(e.getErrorMsg()).buildIsSuccess(false);
        }
        return result;
    }

    // 合单发货(将多个子订单合并成一个包裹)
    @RequestMapping("/multiDelivery")
    @ResponseBody
    public Object multiDelivery(ShippingOrderDO shippingOrder) {
        JsonResult<String> result = new JsonResult<>();
        try {
            shippingOrderService.ship(shippingOrder);
        } catch (ErpCommonException e) {
            return result.buildMsg(e.getErrorMsg()).buildIsSuccess(false);
        }

        return result.buildMsg("发货成功").buildIsSuccess(true);
    }

    // 批量发货表单(检查是否有需要合单发货的，检查是否有需要等待一起合单发货的)
    @RequestMapping("/batchDeliveryForm")
    @ResponseBody
    public Object batchDeliveryForm(String erpOrderId) {
        JsonResult<String> result = new JsonResult<>();

        try {
            String s = erpOrderId.replace("&quot;", "\"");
            List<Long> erpOrderIdList = HaiJsonUtils.toBean(s, new TypeReference<List<Long>>() {
            });
            List<MallSubOrderDO> erpOrderList = mallSubOrderService.selectBatchIds(erpOrderIdList);

            StringBuffer erpNoErrStr = new StringBuffer();
            StringBuffer erpNoTipStr = new StringBuffer();
            for (MallSubOrderDO erpOrder : erpOrderList) {
                if (erpOrder.getStockStatus() == null || erpOrder.getStockStatus() != StockUpStatus.STOCKUP.getCode()) {
                    throw new ErpCommonException("商品备货状态不对，子订单号：" + erpOrder.getShopCode());
                }
                if (StringUtils.isBlank(erpOrder.getReceiver()) || StringUtils.isBlank(erpOrder.getTelephone())
                        || StringUtils.isBlank(erpOrder.getReceiverState())
                        || StringUtils.isBlank(erpOrder.getReceiverCity())
                        || StringUtils.isBlank(erpOrder.getReceiverDistrict())) {
                    throw new ErpCommonException("收货人地址不能为空：" + erpOrder.getShopCode());
                }
                MallSubOrderDO tjErpOrder = new MallSubOrderDO();
                tjErpOrder.setReceiver(erpOrder.getReceiver());
                tjErpOrder.setTelephone(erpOrder.getTelephone());
                tjErpOrder.setReceiverState(erpOrder.getReceiverState());
                tjErpOrder.setReceiverCity(erpOrder.getReceiverCity());
                tjErpOrder.setReceiverDistrict(erpOrder.getReceiverDistrict());
                tjErpOrder.setStatus(0); // 订单状态：新建
                // tjErpOrder.setStockStatus(erpOrder.getStockStatus()); //备货状态：已备货
                // tjErpOrder.setWarehouseId(erpOrder.getWarehouseId()); //相同仓库

                List<MallSubOrderDO> selErpOrderList = mallSubOrderService.selectByOrderNo(erpOrder.getOrderNo());
                int countErr = 0;
                int countTip = 0;
                for (int i = 0; i < selErpOrderList.size(); i++) {
                    MallSubOrderDO selErpOrder = selErpOrderList.get(i);
                    // 不在同一仓库的情况，不用考虑
                    if (selErpOrder.getWarehouseNo() != null
                            && selErpOrder.getWarehouseNo() != erpOrder.getWarehouseNo()) {
                        continue;
                    }
                    // 在同一个仓库，已备货
                    if (selErpOrder.getStockStatus() == StockUpStatus.STOCKUP.getCode()) {
                        countErr++;
                    } else { // 备货未完成，这个时候仓库ID不一定有值
                        countTip++;
                    }
                }
                if (countErr > 1) {
                    erpNoErrStr.append("\r\n" + erpOrder.getShopCode() + ";");
                }
                if (countTip > 0) {
                    erpNoTipStr.append(erpOrder.getShopCode() + "；");
                }
            }

            if (!"".equals(erpNoErrStr.toString())) {
                result.buildIsSuccess(false).buildMsg("需要合单发货的子订单：" + erpNoErrStr.toString());
            } else if (!"".equals(erpNoTipStr.toString())) {
                result.buildIsSuccess(true).buildData("需要等待合单发货的子订单：" + erpNoTipStr.toString());
            } else {
                result.buildIsSuccess(true);
            }
        } catch (ErpCommonException e) {
            result.buildMsg(e.getErrorMsg()).buildIsSuccess(false);
        }
        return result;
    }

    // 批量发货(每个子订单作为一个包裹)
    @RequestMapping("/batchDelivery")
    @ResponseBody
    public Object batchDelivery(ShippingOrderDO shippingOrder) throws InventoryException {
        JsonResult<String> result = new JsonResult<>();
        if (StringUtils.isNotBlank(shippingOrder.getMallOrders())) {
            // ShiroUser shiroUser = this.getShiroUser();
            // shippingOrder.setUserCreate(shiroUser.getLoginName());
            if (shippingOrder.getLogisticCompany() != null && "海狐".equals(shippingOrder.getLogisticCompany())) {
                List<MallSubOrderDO> ErpOrderList = shippingOrderService.queryShippingOrderDetail(shippingOrder.getMallOrders());
                if (ErpOrderList.size() > 1) {
                    throw new ErpCommonException("海狐的包裹只能包含一个商品且数量为1，请选择其他物流公司！");
                }
                if (ErpOrderList.get(0).getQuantity() > 1) {
                    throw new ErpCommonException("海狐的包裹只能包含一个商品且数量为1，请选择其他物流公司！");
                }
                if (StringUtils.isEmpty(ErpOrderList.get(0).getIdCard())) {
                    throw new ErpCommonException("海狐物流发货单号缺少身份证信息");
                }
            }
            try {
                Map<String, Set<String>> idsMap = shippingOrderService.batchDelivery(shippingOrder);
                Set<String> mainIds = idsMap.get("mainIds");
                // 更新主订单发货状态
                if (CollectionUtils.isNotEmpty(mainIds)) {
                    shippingOrderService.updateOuterstatus(mainIds);
                }
                Set<String> shippingOrderIds = idsMap.get("shippingOrderIds");
                // //对接邮客
                // if(CollectionUtils.isNotEmpty(shippingOrderIds) && shippingOrder.getLogisticCompany().equals("邮客")) {
                // for(Long shippingOrderId : shippingOrderIds) {
                // youkeService.createOrder(shippingOrderId);
                // }
                // }
                // //对接运通快递
                // if(CollectionUtils.isNotEmpty(shippingOrderIds) && shippingOrder.getLogisticCompany().equals("运通快递"))
                // {
                // for(Long shippingOrderId : shippingOrderIds) {
                // yunTongService.createOrder(shippingOrderId);
                // }
                // }
                // //对接海狐
                // if(shippingOrder.getLogisticCompany()!=null && shippingOrder.getLogisticCompany().equals("海狐")) {
                // for(Long shippingOrderId : shippingOrderIds) {
                // haihuService.createOrder(shippingOrderId);
                // }
                //
                // }
                // //对接海狐联邦转运
                // if(shippingOrder.getLogisticCompany()!=null && shippingOrder.getLogisticCompany().equals("海狐联邦转运")) {
                // haihuService.returnPackageNo(shippingOrder);
                //
                // }
                // //对接美国转运四方
                if (shippingOrder.getLogisticCompany() != null && "4PX".equals(shippingOrder.getLogisticCompany())) {
                    for (String shippingOrderNO : shippingOrderIds) {
                        siFangService.createOrder(shippingOrderNO);
                    }
                }
                // //对接联邦
                // if(shippingOrder.getLogisticCompany()!=null && shippingOrder.getLogisticCompany().equals("联邦转运")) {
                // for(Long shippingOrderId : shippingOrderIds) {
                // fadRoadService.createOrder(shippingOrderId);
                // }
                // }
                // //直接选择顺丰或者韵达快递补全status = 4特殊国内物流轨迹
                // if(shippingOrder.getLogisticCompany()!=null && (shippingOrder.getLogisticCompany().equals("顺丰")||
                // shippingOrder.getLogisticCompany().equals("韵达"))) {
                // CommonShippingTrack shippingTrack = new CommonShippingTrack();
                // shippingTrack.setGmtCreate(new Date());
                // shippingTrack.setGmtModify(new Date());
                // shippingTrack.setStatus(4);
                // shippingTrack.setLogisticNo(shippingOrder.getLogisticNo());
                // shippingTrack.setInlandExpressId(shippingOrder.getLogisticCompany());
                // shippingTrack.setInlandExpressNo(shippingOrder.getLogisticNo());
                // shippingTrack.setShippingNo(shippingOrder.getShippingNo());
                // shippingTrackMapper.insert(shippingTrack);
                // }
                result.buildIsSuccess(true);
            } catch (ErpCommonException e) {
                result.buildMsg(e.getErrorMsg()).buildIsSuccess(false);
            }
        } else {
            result.buildMsg("错误数据").buildIsSuccess(false);
        }
        return result;
    }

    // 修改发货单
    @RequestMapping("/update")
    @ResponseBody
    public Object update(ShippingOrderDO shippingOrder) {
        JsonResult<String> result = new JsonResult<>();
        // ShiroUser shiroUser = this.getShiroUser();
        // shippingOrder.setUserCreate(shiroUser.getLoginName());
        if (shippingOrder.getId() != null) {
            String message = null;
            shippingOrderService.update(shippingOrder);
            // if(shippingOrder.getLogisticCompany()!=null && shippingOrder.getLogisticCompany().equals("邮客") &&
            // StringUtil.isBlank(shippingOrder.getLogisticNo())) { //对接邮客
            // youkeService.createOrder(shippingOrder.getId());
            // } else if(shippingOrder.getLogisticCompany()!=null && shippingOrder.getLogisticCompany().equals("运通快递")
            // && StringUtil.isBlank(shippingOrder.getLogisticNo())) {//对接运通
            // yunTongService.createOrder(shippingOrder.getId());
            // } else
            if (shippingOrder.getLogisticCompany() != null && "4PX".equals(shippingOrder.getLogisticCompany())
                    && StringUtil.isBlank(shippingOrder.getLogisticNo())) {// 对接4PX
                message = siFangService.createOrder(shippingOrder.getShippingNo());
            }
            // else if(shippingOrder.getLogisticCompany()!=null && shippingOrder.getLogisticCompany().equals("海狐联邦转运")
            // && StringUtil.isBlank(shippingOrder.getLogisticNo())) {//对接海狐联邦转运
            // haihuService.returnPackageNo(shippingOrder);
            // } else if(shippingOrder.getLogisticCompany()!=null && shippingOrder.getLogisticCompany().equals("联邦转运")
            // && StringUtil.isBlank(shippingOrder.getLogisticNo())) {//对接联邦
            // fadRoadService.createOrder(shippingOrder.getId());
            // }
            else if (shippingOrder.getLogisticCompany() != null && "海狐".equals(shippingOrder.getLogisticCompany())
                    && StringUtil.isBlank(shippingOrder.getLogisticNo())) {// 对接海狐
                List<MallSubOrderDO> ErpOrderList = shippingOrderService.queryShippingOrderDetail(shippingOrder.getMallOrders());
                if (ErpOrderList.size() > 1) {
                    throw new ErpCommonException("海狐的包裹只能包含一个商品且数量为1，请选择其他物流公司！");
                } else if (ErpOrderList.get(0).getQuantity() > 1) {
                    throw new ErpCommonException("海狐的包裹只能包含一个商品且数量为1，请选择其他物流公司！");
                } else if (StringUtils.isEmpty(ErpOrderList.get(0).getIdCard())) {
                    throw new ErpCommonException("海狐物流发货单号缺少身份证信息");
                } else {
                    haihuService.createOrder(shippingOrder.getId());
                }
            } else if (shippingOrder.getLogisticCompany() != null && "海狐联邦转运".equals(shippingOrder.getLogisticCompany())
                    && StringUtil.isBlank(shippingOrder.getLogisticNo())) {
                haihuService.returnPackageNo(shippingOrder);
            }
            // else if(shippingOrder.getLogisticCompany()!=null && (shippingOrder.getLogisticCompany().equals("顺丰")||
            // shippingOrder.getLogisticCompany().equals("韵达"))){
            // CommonShippingTrack shippingTrack = new CommonShippingTrack();
            // shippingTrack.setGmtCreate(new Date());
            // shippingTrack.setGmtModify(new Date());
            // shippingTrack.setStatus(4);
            // shippingTrack.setLogisticNo(shippingOrder.getLogisticNo());
            // shippingTrack.setInlandExpressId(shippingOrder.getLogisticCompany());
            // shippingTrack.setInlandExpressNo(shippingOrder.getLogisticNo());
            // shippingTrack.setShippingNo(shippingOrder.getShippingNo());
            // shippingTrackMapper.insert(shippingTrack);
            // }
            if (StringUtil.isBlank(message)) {
                result.setSuccess(true);
            } else {
                result.buildMsg(message).buildIsSuccess(false);
            }
        } else {
            result.buildMsg("错误数据").buildIsSuccess(false);
        }
        return result;
    }

    // 查询物流公司
    @RequestMapping("/queryLogisticCompany")
    @ResponseBody
    public Object queryLogisticCompany() {
        JsonResult<List<LogisticCompanyDO>> result = new JsonResult<>();
        List<LogisticCompanyDO> LogisticCompanyList = shippingOrderService.queryLogisticCompany();
        result.setData(LogisticCompanyList);
        result.setSuccess(true);
        return result;
    }

    // 查询小程序代理角色
    @RequestMapping("/queryAgentMan")
    @ResponseBody
    public Object queryAgentMan() {
        JsonResult<List<MallCustomerDO>> result = new JsonResult<>();
        List<MallCustomerDO> wxUserList = mallCustomerService.selectByRole(1);
        result.setData(wxUserList);
        result.setSuccess(true);
        return result;
    }

    // 包裹号标签导出
    @RequestMapping("/shippingOrderExportPdf")
    @ResponseBody
    public ResponseEntity<byte[]> shippingOrderExportPdf(String shippingOrderIds) throws Exception {
        if (StringUtil.isBlank(shippingOrderIds) || "[]".equals(shippingOrderIds)) {
            return null;
        }
        // ShiroUser shiroUser = this.getShiroUser();
        // String userPrinter = shiroUser.getLoginName();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // PDF设置
        Rectangle rect = new Rectangle(288, 216);
        Document document = new Document(rect, 0, 0, 0, 0);
        // PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("HelloWorld.pdf"));
        PdfWriter writer = PdfWriter.getInstance(document, out);
        BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font FontChinese_10 = new Font(bfChinese, 10, Font.NORMAL);
        Font FontChinese_18 = new Font(bfChinese, 18, Font.NORMAL);
        Font FontChinese_36 = new Font(bfChinese, 36, Font.NORMAL);
        Font FontEnglish_8 = FontFactory.getFont(FontFactory.COURIER, 8);
        document.open();
        PdfContentByte cd = writer.getDirectContent();
        // =========虚线start===================
        CustomDashedLineSeparator separator = new CustomDashedLineSeparator();
        separator.setDash(1);
        separator.setGap(1);
        separator.setLineWidth(0.5f);
        separator.setPercentage(100f);
        separator.setOffset(2);
        Chunk linebreak = new Chunk(separator);
        Paragraph lineParagraph = new Paragraph();
        lineParagraph.add(linebreak);
        lineParagraph.setLeading(12);
        lineParagraph.setSpacingBefore(0);
        lineParagraph.setSpacingAfter(2);
        // =========虚线end===================
        float[] tableWidths = {0.65f, 0.27f, 0.08f};

        String s = shippingOrderIds.replace("&quot;", "\"");
        List<Long> shippingOrderIdList = HaiJsonUtils.toBean(s, new TypeReference<List<Long>>() {
        });
        List<ShippingOrderDO> shippingOrderList = shippingOrderService.selectBatchIds(shippingOrderIdList);
        int shippingOrderListLength = shippingOrderList.size();
        for (int i = 0; i < shippingOrderListLength; i++) {
            ShippingOrderDO shippingOrder = shippingOrderList.get(i);
            // 打印者
            if (StringUtil.isBlank(shippingOrder.getShippingPrinter())) {
                // shippingOrder.setShippingPrinter(userPrinter);
                shippingOrderService.update(shippingOrder);
            }

            // ===条形码start
            Barcode128 code128 = new Barcode128();
            code128.setCode(shippingOrder.getShippingNo());
            Image image128 = code128.createImageWithBarcode(cd, null, null);
            image128.setAlignment(Image.MIDDLE);
            image128.scalePercent(150);
            Paragraph imgParagraph = new Paragraph();
            imgParagraph.add(image128);
            document.add(imgParagraph);
            // ===条形码end

            // ===地址start
            Paragraph addressParagraph = new Paragraph("姓名：" + shippingOrder.getReceiver() + "   电话："
                    + shippingOrder.getTelephone(), FontChinese_18);
            addressParagraph.setLeading(22);
            addressParagraph.setSpacingBefore(5);
            document.add(addressParagraph);
            // ===地址end

            // ===虚线start
            document.add(lineParagraph);
            // ===虚线end

            // ===快递公司:渠道start
            String logisticCompanyStr = "";
            if (shippingOrder.getLogisticCompany() != null) {
                logisticCompanyStr += shippingOrder.getLogisticCompany();
            }
            if (shippingOrder.getType() != null) {
                logisticCompanyStr += "\r\n(" + ShippingOrderType.of(shippingOrder.getType()).getDescription() + ")";
            }
            Paragraph logisticCompanyParagraph = new Paragraph(logisticCompanyStr, FontChinese_36);
            logisticCompanyParagraph.setLeading(42);
            logisticCompanyParagraph.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(logisticCompanyParagraph);
            // ===快递公司:渠道end

            // ===添加商品start
            /*
             * PdfPTable itemTable = new PdfPTable(tableWidths); itemTable.setSpacingBefore(0);
             * itemTable.setSpacingAfter(0); itemTable.setWidthPercentage(100);
             * itemTable.getDefaultCell().setFixedHeight(15); itemTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
             * itemTable.getDefaultCell().setPadding(0); itemTable.addCell(new Phrase("货物信息", FontChinese_10));
             * itemTable.addCell(new Phrase("UPC", FontChinese_10)); itemTable.addCell(new Phrase("数量",
             * FontChinese_10)); List<Long> erpOrderIdList = HaiJsonUtils.toBean(shippingOrder.getErpOrderId(), new
             * TypeReference<List<Long>>(){}); List<ErpOrder> erpOrderList =
             * erpOrderService.selectBatchIds(erpOrderIdList); for(int j=0; j<erpOrderList.size(); j++) { if(j == 6) {
             * //最多6个商品,第7个商品直接跳出 Paragraph para = new Paragraph("•••••••••", FontChinese_10); para.setAlignment(1);
             * para.setLeading(6); PdfPCell cell = new PdfPCell(); cell.setColspan(3);
             * cell.setBorder(PdfPCell.NO_BORDER); cell.addElement(para); itemTable.addCell(cell); break; } ErpOrder
             * erpOrder = erpOrderList.get(j); String itemName = erpOrder.getItemName(); itemName =
             * itemName.replace("直邮", ""); itemName = itemName.replace("拼邮", ""); itemName = itemName.replace("包税", "");
             * itemTable.addCell(new Phrase(itemName, FontChinese_10)); itemTable.addCell(new Phrase(erpOrder.getUpc(),
             * FontEnglish_8)); itemTable.addCell(new Phrase(erpOrder.getQuantity().toString(), FontEnglish_8)); }
             * document.add(itemTable);
             */
            // ===添加商品end

            if (i < shippingOrderListLength - 1) {
                document.newPage();
            }
        }
        document.close();
        writer.close();

        ResponseEntity<byte[]> filebyte = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        String fileName = "BarcodeList.pdf";
        headers.setContentDispositionFormData("attachment", new String(fileName.getBytes("utf-8"), "ISO8859-1"));
        filebyte = new ResponseEntity<byte[]>(out.toByteArray(), headers, HttpStatus.OK);
        out.close();
        return filebyte;
    }

    // 包裹明细导出
    @RequestMapping("/shippingOrderExportExcel")
    @ResponseBody
    public ResponseEntity<byte[]> shippingOrderExportExcel(ShippingOrderVO shippingOrderQueryVO, BindingResult bindingResult) throws Exception {
        if (shippingOrderQueryVO.getStartOrderTime() == null || shippingOrderQueryVO.getEndOrderTime() == null) {
            throw new ErpCommonException("必须选择发货时间段");
        }
        String startOrderTimeStr = DateUtil.ymdFormat(shippingOrderQueryVO.getStartOrderTime());
        Date startOrderTime = DateUtil.parseDate(startOrderTimeStr + " 00:00:00");
        shippingOrderQueryVO.setStartOrderTime(startOrderTime);
        String endOrderTimeStr = DateUtil.ymdFormat(shippingOrderQueryVO.getEndOrderTime());
        Date endOrderTime = DateUtil.parseDate(endOrderTimeStr + " 23:59:59");
        shippingOrderQueryVO.setEndOrderTime(endOrderTime);

        //需加上商户号隔离
        shippingOrderQueryVO.setCompanyNo(AppUtil.getLoginUserCompanyNo());
        List<List<Object>> rowDatas = new ArrayList<>();
        List<MallSubOrderDO> erpOrderlist = mallSubOrderService.queryByShippingOrder(shippingOrderQueryVO);
        if (erpOrderlist != null) {
            for (MallSubOrderDO erpOrder : erpOrderlist) {
                List<Object> list = new ArrayList<>();

                list.add(erpOrder.getSkuCode()); // SKU编号
                list.add(erpOrder.getItemName()); // 商品名称
                // sku图片
                String skuPic = erpOrder.getSkuPic();
                if (StringUtil.isNotBlank(skuPic)) {
                    PicModel pm = HaiJsonUtils.toBean(skuPic, PicModel.class);
                    String imgSrc = pm.getPicList().get(0).getUrl();
                    String imgType = imgSrc.substring(imgSrc.lastIndexOf(".") + 1).toUpperCase();
                    if (imgSrc.contains("aliyuncs.com")) {
                        imgSrc += "?x-oss-process=image/resize,m_lfit,h_100,w_100";
                    } else {
                        imgSrc = imgSrc.replaceAll("Resource", "Thumbnail");
                    }
                    // System.out.println(imgSrc);
                    URL url = new URL(imgSrc);
                    BufferedImage image = ImageIO.read(url);
                    ByteArrayOutputStream output = new ByteArrayOutputStream();
                    ImageIO.write(image, imgType, output);
                    output.flush();
                    list.add(output.toByteArray());
                    output.close();
                } else {
                    list.add(null);
                }
                list.add(erpOrder.getScale()); // 商品尺码
                list.add(erpOrder.getUpc()); // 商品条码
                list.add(erpOrder.getQuantity()); // 发货数量
                // list.add(erpOrder.getSalePrice()); //商品单价
                list.add(erpOrder.getGmtCreate()); // 发货时间
                list.add(erpOrder.getShippingNo()); // 子订单编号
                list.add(erpOrder.getReceiver()); // 收件人
                list.add(erpOrder.getTelephone()); // 联系电话
                list.add(erpOrder.getReceiverState()); // 省
                list.add(erpOrder.getReceiverCity()); // 市
                list.add(erpOrder.getReceiverDistrict()); // 区
                list.add(erpOrder.getReceiverAddress()); // 详细地址
                list.add(erpOrder.getWarehouseNo()); // 仓库名称
                list.add(erpOrder.getShippingNo()); // 包裹号
                list.add(erpOrder.getCompanyNo()); // 物流公司
                //TODO LogisticType这字段怎么会为空导致异常
                list.add("");
//                list.add(ShippingOrderType.of(erpOrder.getLogisticType()).getDescription());// 渠道方式
                if (erpOrder.getLogisticType() == null || erpOrder.getLogisticType() == 0) {
                    list.add("直邮");
                } else {
                    list.add("拼邮");
                }
                rowDatas.add(list);
            }
        }
        ExcelHelper excelHelper = new ExcelHelper();
        String[] columnTitles = new String[]{"SKU编号", "商品名称", "商品图片", "颜色", "尺码", "品牌", "商品条码", "发货数量", "发货时间", "销售员",
                "子订单编号", "小程序客户", "收件人", "手机", "省", "市", "区", "详细地址", "仓库", "包裹号",
                "物流公司", "渠道方式", "物流单号", "发货方式"};
        Integer[] columnWidth = new Integer[]{25, 25, 15, 20, 20, 20, 20, 20, 20, 20, 25, 20, 20, 20, 20, 20, 20, 20,
                20, 25, 20, 20, 20, 20};

        excelHelper.setShippingOrderToSheet("Shipping Order", columnTitles, rowDatas, columnWidth);
        // excelHelper.writeToFile("/Users/liuyang/Work/test.xls");

        ResponseEntity<byte[]> filebyte = null;
        ByteArrayOutputStream out = excelHelper.writeToByteArrayOutputStream();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        String fileName = "订单发货明细表(" + DateUtil.formatDate(startOrderTime, "yyyyMMdd") + ").xlsx";
        headers.setContentDispositionFormData("attachment", new String(fileName.getBytes("utf-8"), "ISO8859-1"));

        filebyte = new ResponseEntity<byte[]>(out.toByteArray(), headers, HttpStatus.OK);
        out.close();
        excelHelper.close();
        return filebyte;
    }

    @RequestMapping("/shippingOrderPackageExportExcel")
    @ResponseBody
    public ResponseEntity<byte[]> shippingOrderPackageExportExcel(ShippingOrderVO shippingOrderQueryVO, BindingResult bindingResult) throws Exception {
        if (shippingOrderQueryVO.getStartOrderTime() == null || shippingOrderQueryVO.getEndOrderTime() == null) {
            throw new ErpCommonException("必须选择发货时间段");
        }
        String startOrderTimeStr = DateUtil.ymdFormat(shippingOrderQueryVO.getStartOrderTime());
        Date startOrderTime = DateUtil.parseDate(startOrderTimeStr + " 00:00:00");
        shippingOrderQueryVO.setStartOrderTime(startOrderTime);
        String endOrderTimeStr = DateUtil.ymdFormat(shippingOrderQueryVO.getEndOrderTime());
        Date endOrderTime = DateUtil.parseDate(endOrderTimeStr + " 23:59:59");
        shippingOrderQueryVO.setEndOrderTime(endOrderTime);

        List<List<Object>> rowDatas = new ArrayList<>();
        List<ShippingOrderDO> shippingOrderList = shippingOrderService.queryByShippingOrderPackageTime(shippingOrderQueryVO);

        if (shippingOrderList != null) {
            for (ShippingOrderDO shippingOrder : shippingOrderList) {
                List<Object> list = new ArrayList<>();
                list.add(shippingOrder.getShippingNo());
                list.add(shippingOrder.getLogisticCompany());
                list.add(shippingOrder.getFreight());
                list.add(shippingOrder.getSkuWeight());
                list.add(shippingOrder.getReceiver());
                list.add(shippingOrder.getReceiverState());
                list.add(shippingOrder.getReceiverCity());
                list.add(shippingOrder.getReceiverDistrict());
                list.add(shippingOrder.getAddress());
                list.add(shippingOrder.getTelephone());
                list.add(shippingOrder.getIdCard());
                list.add(shippingOrder.getCreator());
                list.add(shippingOrder.getGmtCreate());
                rowDatas.add(list);
            }
        }
        ExcelHelper excelHelper = new ExcelHelper();
        String[] columnTitles = new String[]{"包裹单号", "物流公司", "预估物流费用", "商品净重(磅)", "收货人", "省", "市", "区", "详细地址",
                "联系电话", "身份证", "创建者", "创建时间"};
        Integer[] columnWidth = new Integer[]{25, 25, 15, 20, 20, 20, 20, 20, 25, 20, 25, 20, 20};
        excelHelper.setShippingOrderPackageToSheet("Shipping Order Detail", columnTitles, rowDatas, columnWidth);
        // excelHelper.writeToFile("/Users/liuyang/Work/test.xls");

        ResponseEntity<byte[]> filebyte = null;
        ByteArrayOutputStream out = excelHelper.writeToByteArrayOutputStream();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        String fileName = "包裹明细(" + DateUtil.formatDate(startOrderTime, "yyyyMMdd") + ").xlsx";
        headers.setContentDispositionFormData("attachment", new String(fileName.getBytes("utf-8"), "ISO8859-1"));

        filebyte = new ResponseEntity<byte[]>(out.toByteArray(), headers, HttpStatus.OK);
        out.close();
        excelHelper.close();
        return filebyte;
    }

    // 预出库
    @RequestMapping("/prepareShipping")
    @ResponseBody
    public Object prepareShipping(String erpOrderId) {
        String s = erpOrderId.replace("&quot;", "\"");
        List<Long> erpOrderIdList = HaiJsonUtils.toBean(s, new TypeReference<List<Long>>() {
        });
        List<MallSubOrderDO> erpOrderList = mallSubOrderService.selectBatchIds(erpOrderIdList);
        StringBuffer errorMsg = new StringBuffer();
        erpOrderList.forEach(erpOrder -> {
            // 拼邮、备货状态(已备货)、子订单状态(新建)
            if (erpOrder.getLogisticType() != null && erpOrder.getLogisticType() == 1
                    && erpOrder.getStockStatus() == StockUpStatus.STOCKUP.getCode()
                    && erpOrder.getStatus() == OrderStatus.INIT.getCode()) {
                erpOrder.setStockStatus(StockUpStatus.PREPARE.getCode());
                mallSubOrderService.update(erpOrder);
            } else {
                errorMsg.append(erpOrder.getShopCode() + ",");
            }
        });

        String errorMsgStr = errorMsg.toString();
        if (StringUtil.isNotBlank(errorMsgStr)) {
            errorMsgStr = "子订单号：" + errorMsgStr + "状态有误，只有拼邮、备货状态(已备货)、子订单状态(新建)的子订单才能预出库";
            return JsonResult.buildFailed(errorMsgStr);
        } else {
            return JsonResult.buildSuccess(null);
        }
    }

    /**
     * 查看发货单明细
     */
    @RequestMapping("/queryShippingOrderDetail")
    @ResponseBody
    public Object queryShippingOrderDetail(Long shippingOrderId) {
        JsonPageResult<List<MallSubOrderDO>> result = new JsonPageResult<>();
        try {
            ShippingOrderDO shippingOrder = shippingOrderService.selectById(shippingOrderId);
            String mallSubOrderNos = shippingOrder.getMallOrders();
            String[] nos = mallSubOrderNos.split(",");
            List<String> noList = Arrays.asList(nos);
            List<MallSubOrderDO> ErpOrderList = mallSubOrderService.queryByMallSubOrderNos(noList);
//            List<MallSubOrderDO> ErpOrderList = shippingOrderService.queryShippingOrderDetail(erpOrderIds);
            for (MallSubOrderDO mallSubOrder : ErpOrderList) {
                mallSubOrder.setSkuPic(ImgUtil.initImg2Json(mallSubOrder.getSkuPic()));
            }
            result.setData(ErpOrderList);
            return result.buildIsSuccess(true);
        } catch (ErpCommonException e) {
            return result.buildIsSuccess(false).buildMsg(e.getErrorMsg());
        } catch (Exception ex) {
            ex.printStackTrace();
            return result.buildIsSuccess(false).buildMsg("未知异常");
        }
    }
//
//    /**
//     * 运通接口测试
//     *
//     * @param id
//     * @return
//     */
//    @RequestMapping("/yuntongTest")
//    public void queryLogisticCompany(Long id) {
//        // yunTongService.createOrder(4800l);
//    }

//    /**
//     * 邮客接口测试
//     */
//    @RequestMapping("/youkeTest")
//    public void youkeTest() {
//        // youkeService.createOrder(2l);
//        // youkeService.inboundStatus("PKG17062502002910007");
//        // youkeService.orderStatus("1739255020");
//        // EntityWrapper<ShippingOrderDO> entityWrapper = new EntityWrapper<>();
//        ShippingOrderDO Order = shippingOrderService.selectByShippingNo("PKG17062710132810022");
//        // haihuService.returnPackageNo(Order);
//    }

    /**
     * 批量订正主订单状态
     */
    @RequestMapping("/updateOuterstatusBatch")
    public void updateOuterstatusBatch() {
        List<MallOrderDO> outerOrderList = mallOrderService.queryByStatus((byte) 0);
        Set<String> mainIds = Sets.newHashSet();
        for (int i = 0; i < outerOrderList.size(); i++) {
            mainIds.add(outerOrderList.get(i).getOrderNo());
        }
        shippingOrderService.updateOuterstatus(mainIds);
    }

    /**
     * 判断一个人一周内发货包裹数
     */
    @RequestMapping("/checkManyTimesDelivery")
    @ResponseBody
    public Object checkManyTimesDelivery(String idCard, String logisticCompany, String erpOrderIds, boolean isBatch) {
        if (StringUtil.isNotBlank(idCard) && StringUtil.isNotBlank(logisticCompany) && !isBatch) {
            int num = shippingOrderService.selectCount(idCard, logisticCompany);
            if (num >= 2) {
                return JsonResult.buildFailed("注意：此收件人已用\"" + logisticCompany + "\"在一周之内发过2次以上了");
            } else {
                return JsonResult.buildSuccess(null);
            }
        } else if (StringUtil.isNotBlank(erpOrderIds) && StringUtil.isNotBlank(logisticCompany) && isBatch) {
            String s = erpOrderIds.replace("&quot;", "\"");
            List<Long> erpOrderIdList = HaiJsonUtils.toBean(s, new TypeReference<List<Long>>() {
            });
            List<MallSubOrderDO> erpOrderList = mallSubOrderService.selectBatchIds(erpOrderIdList);
            String error = "";
            for (int i = 0; i < erpOrderList.size(); i++) {
                MallSubOrderDO erpOrder = erpOrderList.get(i);
                String idCardTemp = erpOrder.getIdCard();
                if (StringUtil.isNotBlank(idCardTemp)) {
                    int num = shippingOrderService.selectCount(idCardTemp, logisticCompany);
                    if (num >= 2) {
                        error += erpOrder.getReceiver() + "，";
                    }
                }
            }
            if (StringUtil.isNotBlank(error)) {
                return JsonResult.buildFailed("注意：" + error + "用\"" + logisticCompany + "\"在一周之内发货2次以上了");
            } else {
                return JsonResult.buildSuccess(null);
            }
        }
        return JsonResult.buildSuccess(null);
    }

    /**
     * 邮客接口测试
     *
     * @throws ParseException
     */
    @RequestMapping("/FadroadTest")
    public void FadRoadTest(String packageNO) throws ParseException {
        // fadRoadService.shippingTrack(packageNO);
        // siFangService.shippingTrack(packageNO);
    }

    /**
     * 完整物流轨迹详情展示
     *
     * @param shippingNo
     * @return
     */
    @RequestMapping("/getShippingTrackDetail")
    @ResponseBody
    public Object getShippingTrackDetail(String shippingNo) {
        JsonResult<List<ShippingTrackVO>> result = new JsonResult<>();
        try {
            Kuaidi100ShippingTrackResult kuaidi100Result = kuaidi100Service.queryShippingTrack(shippingNo);
            CommonShippingTrack shippingTrack = kuaidi100Result.toCommonShippingTrack();

            List<CommonShippingTrackNode> shippingTrackInfos = shippingTrack.getShippingTrackInfo();
            List<ShippingTrackVO> shippingTrackVOList = new ArrayList<>();

            if (shippingTrackInfos != null) {
                shippingTrackInfos.forEach(shippingTrackInfo -> {
                    ShippingTrackVO shippingOne = new ShippingTrackVO();
                    shippingOne.setInfo(shippingTrackInfo.getInfo());
                    shippingOne.setGmtCreate(DateUtil.parseDate(shippingTrackInfo.getDate()));
                    shippingTrackVOList.add(shippingOne);
                });
            }

            result.buildIsSuccess(true).buildData(shippingTrackVOList);
        } catch (ErpCommonException e) {
            result.buildIsSuccess(false).buildMsg(e.getErrorMsg());
        } catch (Exception e) {
            e.printStackTrace();
            result.buildIsSuccess(false).buildMsg("物流信息查询失败");
        }
        return result;
    }
    /*
        if (StringUtils.isNotBlank(shippingNo)) {
            List<ShippingTrackDO> shippingTracks = shippingTrackService.queryShippingTrack(shippingNo);
            List<ShippingTrackVO> shippingTrackVOList = Lists.newArrayList();

            if (!CollectionUtils.isEmpty(shippingTracks)) {
                shippingTracks.forEach(shippingTrack -> {
                    ShippingTrackVO shippingOne = new ShippingTrackVO();
                    shippingOne.setGmtCreate(shippingTrack.getGmtCreate());
                    shippingOne.setInfo(shippingTrack.getTrackInfo());
                    shippingOne.setStatus(shippingTrack.getLogisticsStatus());
                    shippingTrackVOList.add(shippingOne);
                });
            }

     * if (shippingTracks.size() > 0) { shippingTracks.forEach(shippingTrack -> { ShippingTrackVO shippingOne =
     * new ShippingTrackVO(); switch (shippingTrack.getStatus()) { case 0:
     * shippingOne.setGmtCreate(shippingTrack.getGmtModify()); shippingOne.setInfo("未出库");
     * shippingTrackVOList.add(shippingOne); break; case 1:
     * shippingOne.setGmtCreate(shippingTrack.getOverseaOutTime()); shippingOne.setInfo("已出库");
     * shippingTrackVOList.add(shippingOne); break; case 2:
     * shippingOne.setGmtCreate(shippingTrack.getOverseaOutTime()); shippingOne.setInfo("递交航空公司");
     * shippingTrackVOList.add(shippingOne); break; case 3:
     * shippingOne.setGmtCreate(shippingTrack.getInlandInTime()); shippingOne.setInfo("清关");
     * shippingTrackVOList.add(shippingOne); break; case 4: if(shippingTrack.getInlandOutTime()!=null) {
     * shippingOne.setGmtCreate(shippingTrack.getInlandOutTime()); } else {
     * shippingOne.setGmtCreate(shippingTrack.getGmtCreate()); } shippingOne.setInfo("国内派送");
     * shippingTrackVOList.add(shippingOne); String infodetail = ""; JSONArray jsonArrayss = null; for (int i =
     * 0; i < shippingTracks.size(); i++) { if (shippingTracks.get(i).getStatus() == 4) { infodetail =
     * shippingTracks.get(i).getInfo(); if (StringUtils.isNotBlank(infodetail) && !infodetail.equals("已转国内快递")
     * && infodetail.contains("{")) { JSONObject object = JSONObject.fromObject(infodetail); if
     * (shippingTrack.getInlandExpressId().equals("tiantian") ||
     * shippingTrack.getInlandExpressId().equals("百世汇通")) { jsonArrayss = object.getJSONArray("data"); for (int
     * j = jsonArrayss.size() - 1; j > 0; j--) { ShippingTrackVO shippingTrackArray = new ShippingTrackVO();
     * JSONObject object2 = jsonArrayss.getJSONObject(j); Date time =
     * DateUtil.parseDate(object2.getString("time")); shippingTrackArray.setGmtCreate(time);
     * shippingTrackArray.setInfo(String.valueOf(object2.get("context")));
     * shippingTrackVOList.add(shippingTrackArray); } } else { jsonArrayss = object.getJSONArray("Traces"); for
     * (int j = jsonArrayss.size() - 1; j > 0; j--) { ShippingTrackVO shippingTrackArray = new
     * ShippingTrackVO(); JSONObject object2 = jsonArrayss.getJSONObject(j); Date time =
     * DateUtil.parseDate(object2.getString("AcceptTime")); shippingTrackArray.setGmtCreate(time);
     * shippingTrackArray.setInfo(String.valueOf(object2.get("AcceptStation")));
     * shippingTrackVOList.add(shippingTrackArray); } } } } } break; case 5:
     * shippingOne.setGmtCreate(shippingTrack.getGmtCreate()); shippingOne.setInfo("签收");
     * shippingTrackVOList.add(shippingOne); break; } }); }

            ListSort(shippingTrackVOList);
            result.buildData(shippingTrackVOList).buildIsSuccess(true);
        } else {
            result.buildIsSuccess(false).buildMsg("包裹信息异常！");
        }
    }
    */

    private static void ListSort(List<ShippingTrackVO> list) {
        Collections.sort(list, new Comparator<ShippingTrackVO>() {

            @Override
            public int compare(ShippingTrackVO o1, ShippingTrackVO o2) {
                Date dt1 = o1.getGmtCreate();
                Date dt2 = o2.getGmtCreate();
                if (dt1.getTime() < dt2.getTime()) {
                    return -1;
                } else if (dt1.getTime() > dt2.getTime()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
    }

    class CustomDashedLineSeparator extends DottedLineSeparator {

        protected float dash = 5;
        protected float phase = 2.5f;

        public float getDash() {
            return dash;
        }

        public float getPhase() {
            return phase;
        }

        public void setDash(float dash) {
            this.dash = dash;
        }

        public void setPhase(float phase) {
            this.phase = phase;
        }

        @Override
        public void draw(PdfContentByte canvas, float llx, float lly, float urx, float ury, float y) {
            canvas.saveState();
            canvas.setLineWidth(lineWidth);
            canvas.setLineDash(dash, gap, phase);
            drawLine(canvas, llx, urx, y);
            canvas.restoreState();
        }
    }

}
