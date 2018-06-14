package com.wangqin.globalshop.item.app.controller;

import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ScaleTypeDO;
import com.wangqin.globalshop.biz1.app.dto.ISkuDTO;
import com.wangqin.globalshop.biz1.app.vo.InventoryAddVO;
import com.wangqin.globalshop.biz1.app.vo.ItemSkuQueryVO;
import com.wangqin.globalshop.biz1.app.vo.JsonPageResult;
import com.wangqin.globalshop.biz1.app.vo.JsonResult;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.HaiJsonUtils;
import com.wangqin.globalshop.common.utils.PicModel;
import com.wangqin.globalshop.common.utils.excel.ExcelHelper;
import com.wangqin.globalshop.inventory.app.service.InventoryService;
import com.wangqin.globalshop.item.app.service.IItemService;
import com.wangqin.globalshop.item.app.service.IItemSkuService;
import com.wangqin.globalshop.item.app.service.IScaleTypeService;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * 商品SKU处理器
 *
 * @author zhulu
 */
@Controller
@RequestMapping("/itemSku")
@Authenticated
public class ItemSkuController {

  @Autowired
  private IItemService iItemService;
  @Autowired
  private IItemSkuService iItemSkuService;
  @Autowired
  private InventoryService inventoryService;


  @Autowired
  private IScaleTypeService scaleTypeService;

  /**
   * 删除
   *
   * @param id
   * @return
   */
  @RequestMapping("/delete")
  @ResponseBody
  public Object delete(Long id) {
    JsonResult<String> result = new JsonResult<>();
		/*
		if(id!=null){
			if(iItemSkuService.isCanDeleteSku(id)) {
				iItemSkuService.deleteByPrimaryKey(id);
				return result.buildIsSuccess(true);
			} else {
				return result.buildIsSuccess(false).buildMsg("此sku已有业务记录，不能删除");
			}
		}else{
			return result.buildIsSuccess(false).buildMsg("没有SKU id");
		}*/
    iItemSkuService.deleteById(id);
    result.buildIsSuccess(true);
    return result;
  }

  @RequestMapping("/saleable")
  @ResponseBody
  public Object getSaleAble() {
    JsonResult<List<ItemSkuDO>> result = new JsonResult<>();
    List<ItemSkuDO> skuList = iItemSkuService.querySaleableSkus();
    if (0 == skuList.size()) {
      result.buildIsSuccess(false).buildMsg("无可售商品");
    }
    result.buildData(skuList).buildIsSuccess(true);
    return result;
  }
//
//  /**
//   * SKU 锁定虚拟库存，用在提前修改可售库存，以便同步到第三方平台(有赞)
//   *
//   * @param
//   * @return
//   */
//  @RequestMapping("/lockedVirtualInv")
//  @ResponseBody
//  public Object lockedVirtualInv(InventoryAddVO inventory) {
//    JsonResult<ItemSkuDO> result = new JsonResult<>();
////		if(itemSku.getId()==null) {
////			return result.buildIsSuccess(false).buildMsg("SKU ID错误");
////		} else if(itemSku.getItemCode()==null) {
////			return result.buildIsSuccess(false).buildMsg("商品编码错误");
////		}
//    InventoryAddVO inv = inventoryService.se(inventory.getSkuCode());
//    //if(inventory == null) {
//    //	return result.buildIsSuccess(false).buildMsg("未找到此sku的库存");
//    //}
//
////		int lockedNum = inventory.getLockedVirtualInv() + itemSku.getLockedVirtualInv();
////		if(lockedNum<0 || (lockedNum>inventory.getVirtualInv() && lockedNum>inventory.getTotalAvailableInv())) {
////			return result.buildIsSuccess(false).buildMsg("锁定数量异常");
////		}
//    inv.setLockedVirtualInv(inventory.getLockedVirtualInv());
//    inventoryService.lockVirtualInv(inv);
//
//    /**
//     //同步到有赞
//     Item item = iItemService.selectById(itemSku.getItemId());
//     // 同步到有赞并上架
//     if (item.getIsSale() != null && item.getIsSale() == 1) {
//     if (item.getSaleOnYouzan() == 1) {
//     try {
//     ShiroUser user = ShiroUtil.getShiroUser();
//     IChannelService channelService = ChannelFactory.getChannel(user.getCompanyId(), ChannelType.YouZan);
//     if (item.getSaleOnYouzan() == 1 && item.getIsSale() != null && item.getIsSale() == 1) { // 同步到有赞并上架
//     channelService.syncItem(item.getId());
//     } else { // 下架
//     channelService.syncDelistingItem(item.getId());
//     }
//     } catch (Exception e) {
//     logger.error("SKU锁定虚拟库存时同步到有赞：", e);
//     }
//     }
//     }
//     **/
//    return result.buildIsSuccess(true);
//  }

  /**
   * 根据sku_code获取sku
   *
   * @param id
   * @return
   */
  @RequestMapping("/query")
  @ResponseBody
  public Object query(String skuCode) {
    JsonResult<ISkuDTO> result = new JsonResult<>();
    //if haven't item id ,add item
    if (null != skuCode) {
      ISkuDTO itemSku = iItemSkuService.queryItemSkuBySkuCode(skuCode);
      result.setData(itemSku);
    } else {
      result.buildIsSuccess(false).buildMsg("没有skuCode");
    }
    return result.buildIsSuccess(true);
  }

  @RequestMapping("/queryBySkuCodeOrUpc")
  @ResponseBody
  public Object queryBySkuCodeOrUpc(String code) {
    JsonPageResult<List<ItemSkuDO>> result = new JsonPageResult<>();
    if (StringUtils.isNoneBlank(code)) {
      List<ItemSkuDO> itemSkuList = null;
      ItemSkuDO tjItemSku = new ItemSkuDO();
      tjItemSku.setSkuCode(code);
      ItemSkuDO selItemSku = iItemSkuService.selectByPrimaryKey(tjItemSku.getId());
      if (selItemSku != null) {
        itemSkuList = new ArrayList<ItemSkuDO>();
        itemSkuList.add(selItemSku);
      } else {
        tjItemSku.setSkuCode(null);
        tjItemSku.setUpc(code);

//				EntityWrapper<ItemSkuDO> entityWrapper = new EntityWrapper<ItemSkuDO>();
//				entityWrapper.setEntity(tjItemSku);
        //itemSkuList = iItemSkuService.selectList(entityWrapper);
      }
      result.setData(itemSkuList);
    } else {
      result.buildIsSuccess(false).buildMsg("skuCode 不能为空");
    }
    return result.buildIsSuccess(true);
  }

  /**
   * 根据upc获取商品信息(取该upc对应的第一个item_sku记录，返回item_sku+inventory+virtualInv)
   *
   * @param upc
   * @return
   * @author XiaJun
   */
  @RequestMapping("/queryItemSkuByUpc")
  @ResponseBody
  public Object queryItemSkuByUpc(String upc) {
    JsonResult<ItemSkuDO> result = new JsonResult<>();
    List<ItemSkuDO> itemSkuList = iItemSkuService.queryItemSkusByUpc(upc);
    if (itemSkuList.size() == 0) {
      result.buildIsSuccess(false);
      result.buildMsg("没有该upc对应的商品");
    } else {
      ItemSkuDO itemSku = iItemSkuService.queryItemSkusByUpc(upc).get(0);
      result.setData(itemSku);
      result.buildIsSuccess(true);
      result.buildMsg("查找成功");
    }
    return result;
  }

  /**
   * sku列表展示
   *
   * @param itemSkuQueryVO
   * @return
   */
  @RequestMapping("/queryItemSkuList")
  @ResponseBody
  public Object queryItemSkus(ItemSkuQueryVO itemSkuQueryVO) {
    JsonPageResult<List<ISkuDTO>> result = new JsonPageResult<>();
    if (null == AppUtil.getLoginUserCompanyNo()) {
      return result.buildIsSuccess(false).buildMsg("请登录");
    }
    itemSkuQueryVO.setCompanyNo(AppUtil.getLoginUserCompanyNo());
    result = iItemSkuService.queryItemSkus(itemSkuQueryVO);
    result.buildIsSuccess(true);
    return result;
  }

  /**
   * 导出excel
   *
   * @param id
   */
  @RequestMapping("/queryItemSkusForExcel")
  public ResponseEntity<byte[]> queryItemSkusForExcel() throws Exception {
    List<List<Object>> rowDatas = new ArrayList<>();
    List<ItemSkuDO> itemSkuList = iItemSkuService.queryItemSkusForExcel();
    if (itemSkuList != null) {
      for (ItemSkuDO itemSku : itemSkuList) {
        List<Object> list = new ArrayList<>();
        //sku图片
        String skuPic = itemSku.getSkuPic();
        if (StringUtil.isNotBlank(skuPic)) {
          PicModel pm = HaiJsonUtils.toBean(skuPic, PicModel.class);
          String imgSrc = pm.getPicList().get(0).getUrl();
          String imgType = imgSrc.substring(imgSrc.lastIndexOf(".") + 1).toUpperCase();
          if (imgSrc.contains("aliyuncs.com")) {
            imgSrc += "?x-oss-process=image/resize,m_lfit,h_100,w_100";
          } else {
            imgSrc = imgSrc.replaceAll("Resource", "Thumbnail");
          }
          //System.out.println(imgSrc);
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
        list.add(itemSku.getItemName());  //商品名称
        list.add(itemSku.getBrandName());       //商品品牌

        list.add(itemSku.getScale());    //尺码
        list.add(itemSku.getSalePrice());  //销售价格
        rowDatas.add(list);
      }
    }
    ExcelHelper excelHelper = new ExcelHelper();
    String[] columnTitles = new String[]{"商品图片", "商品名称", "商品品牌", "颜色", "尺码", "销售价格"};
    Integer[] columnWidth = new Integer[]{10, 30, 10, 10, 20, 20};
    excelHelper.setItemToSheet("Item", columnTitles, rowDatas, columnWidth);
    //excelHelper.writeToFile("/Users/liuyang/Work/test.xls");

    ResponseEntity<byte[]> filebyte = null;
    ByteArrayOutputStream out = excelHelper.writeToByteArrayOutputStream();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    String fileName = "商品.xlsx";
    headers.setContentDispositionFormData("attachment", new String(fileName.getBytes("utf-8"), "ISO8859-1"));

    filebyte = new ResponseEntity<byte[]>(out.toByteArray(), headers, HttpStatus.OK);
    out.close();
    excelHelper.close();
    return filebyte;
  }

  @RequestMapping("/scaleTypeList")
  @ResponseBody
  public Object scaleTypeList() {
    JsonResult<List<ScaleTypeDO>> result = new JsonResult<>();
    List<ScaleTypeDO> scaleTypeList = scaleTypeService.scaleTypeList();

    return result.buildData(scaleTypeList).buildIsSuccess(true);
  }

  /**
   * 更新sku
   *
   * @param
   * @return
   */
  @RequestMapping("/update")
  @ResponseBody
  public Object update(ItemSkuQueryVO itemSku) {
    itemSku.setModifier("admin");
    itemSku.setCreator("admin");
    JsonResult<String> result = new JsonResult<>();
    /**
     //if haven't item id ,add item
     if(itemSku.getId()==null){
     return result.buildIsSuccess(false).buildMsg("没有SKU id");
     }else{
     //当upc改变时，订正订单明细里面的upc数据
     ItemSkuDO selItemSku = iItemSkuService.selectByPrimaryKey(itemSku.getId());
     if(StringUtil.isNotBlank(itemSku.getUpc())) {
     MallOrderDO erpOrder = new MallOrderDO();
     //erpOrder.setSkuId(itemSku.getId());
     //erpOrder.setUpc(itemSku.getUpc());
     erpOrderService.updateUpcForOrder(erpOrder);
     }
     //当weight改变时，订正订单明细里面的weight数据
     if(itemSku.getWeight()!=null && itemSku.getWeight()!=selItemSku.getWeight()) {
     MallOrderDO erpOrder = new MallOrderDO();
     //erpOrder.setS
     //erpOrder.setWeight(itemSku.getWeight());
     erpOrderService.updateWeightForOrder(erpOrder);
     }

     //1. find item
     ItemDO item = iItemService.queryItemByItemCode(itemSku.getItemCode());
     if(item==null){
     return result.buildMsg("the item is not find").buildIsSuccess(false);
     }
     itemSku.setItemCode(item.getItemCode());
     itemSku.setCategoryCode(item.getCategoryCode());
     itemSku.setCategoryName(item.getCategoryName());
     itemSku.setItemName(item.getItemName());
     String skuPic = ImageUtil.getImageUrl(itemSku.getSkuPic());
     itemSku.setSkuPic(skuPic);
     //2.init
     itemSku.setModel("admin");
     itemSku.setGmtModify(new Date());
     //			iItemSkuService.updateById(itemSku);
     iItemSkuService.updateItemSku(itemSku);

     //同步到有赞并上架
     /*
     if(item.getIsSale()!=null && item.getIsSale()==1) {
     if (item.getSaleOnYouzan() == 1) {
     try {
     IChannelService channelService = ChannelFactory.getChannel(user.getCompanyId(), ChannelType.YouZan);
     if (item.getSaleOnYouzan() == 1 && item.getIsSale() != null && item.getIsSale() == 1) { //同步到有赞并上架
     channelService.syncItem(item.getId());
     } else { // 下架
     channelService.syncDelistingItem(item.getId());
     }
     } catch (Exception e) {
     logger.error("商品添加时同步到有赞：", e);
     }
     }
     }*/

    //return result.buildIsSuccess(true);
    //}
    result.buildIsSuccess(true);
    result.buildMsg("更新成功");
    //检测upc是否重复
    if (0 < iItemSkuService.queryItemCountByUpc(itemSku.getUpc())) {
      result.buildIsSuccess(false);
      result.buildMsg("upc不可以重复，请再次输入");
      return result;
    }
    iItemSkuService.updateById(itemSku);
    return result;
  }
}

