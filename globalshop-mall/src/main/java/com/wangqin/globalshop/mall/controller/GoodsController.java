package com.wangqin.globalshop.mall.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.jetty.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallShippingCartDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.ItemQueryVO;
import com.wangqin.globalshop.biz1.app.vo.JsonPageResult;
import com.wangqin.globalshop.biz1.app.vo.JsonResult;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.utils.DateUtil;
import com.wangqin.globalshop.common.utils.HaiJsonUtils;
import com.wangqin.globalshop.common.utils.PicModel;
import com.wangqin.globalshop.mall.dal.mapperExt.MallItemDOMapperExt;
import com.wangqin.globalshop.mall.dal.mapperExt.MallShippingCartDOMapperExt;
import com.wangqin.globalshop.mall.dal.mapperExt.MallSubOrderDOMapperExt;

@Controller
@RequestMapping("/wx/item")
public class GoodsController {

    @Autowired
    MallItemDOMapperExt         mallItemDOMapperExt;
    @Autowired
    MallShippingCartDOMapperExt mallShippingCartDOMapperExt;
    @Autowired
    MallSubOrderDOMapperExt     mallSubOrderDOMapperExt;

    @RequestMapping("/query")
    @ResponseBody
    @Transactional(rollbackFor = ErpCommonException.class)
    public Object query(Long id, String openId) {
        JsonResult<ItemDO> result = new JsonResult<>();
        ItemDO ItemDO = mallItemDOMapperExt.selectByPrimaryKey(id);
        if (ItemDO == null) {
            return result.buildData(ItemDO).buildIsSuccess(false);
        }
        if (!StringUtil.isBlank(openId)) {
            List<MallShippingCartDO> wxShoppingCartList = mallShippingCartDOMapperExt.selectListByOpenId(openId);
            List<String> cartSkuIdList = new ArrayList<>();
            for (MallShippingCartDO wxShoppingCart : wxShoppingCartList) {
                cartSkuIdList.add(wxShoppingCart.getSkuCode());
            }
            // ItemDO.setCartSkuIdList(cartSkuIdList);
        }
        List<ItemSkuDO> itemSkuList = new ArrayList<>();
        return result;
    }

    /**
     * 热销商品 销量前十的商品
     */

    @RequestMapping("/queryHotItems")
    @ResponseBody
    public Object queryHotItems(Integer pageIndex) {
        if (pageIndex == null) {
            pageIndex = 0;
        }
        JsonResult<List<ItemDO>> result = new JsonResult<>();
        List<ItemDO> hotItems = mallItemDOMapperExt.queryHotItems(pageIndex);
        result.buildData(hotItems).buildIsSuccess(true);
        return result;
    }

    @RequestMapping("/queryItemList")
    @ResponseBody
    public Object queryItems(ItemQueryVO itemQueryVO) {
        JsonPageResult<List<ItemDO>> result = mallItemDOMapperExt.queryItems(itemQueryVO);
        return result.buildIsSuccess(true);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping("/queryItemListByDate")
    @ResponseBody
    public Object queryItemListByDate(ItemQueryVO itemQueryVO) {
        JsonPageResult<List<ItemDO>> result = mallItemDOMapperExt.queryItems(itemQueryVO);

        Map<String, List<ItemDO>> itemsMap = new TreeMap<String, List<ItemDO>>(new Comparator<String>() {

            @Override
            public int compare(String obj1, String obj2) {
                // 降序排序
                return obj2.compareTo(obj1);
            }
        });

        List<ItemDO> itemList = result.getData();
        itemList.forEach(ItemDO -> {
            String dateStr = DateUtil.formatSimpleDate(ItemDO.getGmtCreate());
            List<ItemDO> itemListTmp = itemsMap.get(dateStr);
            if (itemListTmp == null) {
                itemListTmp = new ArrayList();
                itemListTmp.add(ItemDO);
                itemsMap.put(dateStr, itemListTmp);
            } else {
                itemListTmp.add(ItemDO);
            }
        });

        JsonResult<Map<String, List<ItemDO>>> result2 = new JsonResult();
        result2.setData(itemsMap);
        return result2.buildIsSuccess(true);
    }

    @RequestMapping("/queryRecommend")
    @ResponseBody
    @Transactional(rollbackFor = ErpCommonException.class)
    public Object queryRecommend() {
        MallSubOrderDO outerOrderDetail = mallSubOrderDOMapperExt.selectByRecommend();
        ItemDO ItemDO = new ItemDO();
        if (outerOrderDetail != null) {
            ItemDO = mallItemDOMapperExt.selectByCode(outerOrderDetail.getItemCode());
        } else {
            ItemDO = mallItemDOMapperExt.selectByNew();
        }
        if (ItemDO != null && StringUtil.isNotBlank(ItemDO.getMainPic())) {
            PicModel pm = HaiJsonUtils.toBean(ItemDO.getMainPic(), PicModel.class);
            String imgSrc = pm.getPicList().get(0).getUrl();
            ItemDO.setMainPic(imgSrc);
        }
        JsonPageResult<ItemDO> result = new JsonPageResult<ItemDO>();
        result.buildData(ItemDO);
        return result.buildIsSuccess(true);
    }

    @RequestMapping("/queryItemsByCategory")
    @ResponseBody
    @Transactional(rollbackFor = ErpCommonException.class)
    public Object queryItemsByCategory(Long categoryId, Long itemId) {
        JsonResult<List<ItemDO>> result = new JsonPageResult<List<ItemDO>>();
        Integer limit = 32;
        List<Long> ids = new ArrayList<>();
        ids.add(itemId);
        List<ItemDO> itemList = mallItemDOMapperExt.selectByCategotegoryAndNotInList(categoryId, limit, ids);
        if (itemList.size() < 32) {
            itemList.addAll(mallItemDOMapperExt.selectByNotInCategotegory(categoryId, limit - itemList.size()));
        }
        itemList.forEach(ItemDO -> {
            if (StringUtil.isNotBlank(ItemDO.getMainPic())) {
                PicModel pm = HaiJsonUtils.toBean(ItemDO.getMainPic(), PicModel.class);
                String imgSrc = pm.getPicList().get(Integer.parseInt(pm.getMainPicNum()) - 1).getUrl();
                ItemDO.setMainPic(imgSrc);
            }
        });
        result.buildData(itemList).buildIsSuccess(true);
        return result;
    }
}
