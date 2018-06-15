package com.wangqin.globalshop.mall.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallShippingCartDO;
import com.wangqin.globalshop.biz1.app.vo.JsonPageResult;
import com.wangqin.globalshop.biz1.app.vo.JsonResult;
import com.wangqin.globalshop.common.utils.StringUtil;
import com.wangqin.globalshop.mall.dal.mapperExt.MallItemSkuDOMapperExt;
import com.wangqin.globalshop.mall.dal.mapperExt.MallShippingCartDOMapperExt;

@Controller
@RequestMapping("/wx/shoppingCart")
public class WxShoppingCartController {

    @Autowired
    MallItemSkuDOMapperExt      mallItemSkuDOMapperExt;
    @Autowired
    MallShippingCartDOMapperExt mallShippingCartDOMapperExt;

    /**
     * 添加商品到购物车
     * 
     * @param
     * @return
     */
    @RequestMapping("/addToShoppingCart")
    @ResponseBody
    public Object addToShoppingCart(MallShippingCartDO shoppingCart) {
        JsonResult<String> result = new JsonResult<>();
        ItemSkuDO sku = mallItemSkuDOMapperExt.selectByCode(shoppingCart.getSkuCode());
        shoppingCart.setItemName(sku.getItemName());
        MallShippingCartDO selWxShoppingCart = mallShippingCartDOMapperExt.selectByOpenIdAndSkuCode(shoppingCart.getSkuCode(),
                                                                                                    shoppingCart.getOpenId());
        if (selWxShoppingCart != null) {
            selWxShoppingCart.setItemName(shoppingCart.getItemName());
            selWxShoppingCart.setQuantity(shoppingCart.getQuantity() + selWxShoppingCart.getQuantity());
            mallShippingCartDOMapperExt.updateByPrimaryKeySelective(selWxShoppingCart);
        } else {
            mallShippingCartDOMapperExt.insertSelective(shoppingCart);
        }
        return result.buildIsSuccess(true);
    }

    /**
     * 删除商品
     * 
     * @param
     * @return
     */
    @RequestMapping("/deleteById")
    @ResponseBody
    public Object deleteById(String ids, String openId) {
        JsonResult<String> result = new JsonResult<>();
        if (StringUtil.isNotBlank(ids)) {
            List<String> idList = Lists.newArrayList(ids.split(","));
            mallShippingCartDOMapperExt.deleteShoppingByIdsAndOpenId(idList,openId);
            return result.buildIsSuccess(true).buildData("删除成功");
        } else {
            return result.buildIsSuccess(false).buildMsg("参数错误");
        }
    }

    /**
     * 修改购物车商品的属性
     * 
     * @param
     * @return
     */
    @RequestMapping("/updateShoppingCart")
    @ResponseBody
    public Object updateShoppingCartAttribute(MallShippingCartDO shoppingCart) {
        JsonResult<String> result = new JsonResult<>();
        mallShippingCartDOMapperExt.updateByPrimaryKeySelective(shoppingCart);
        return result.buildIsSuccess(true);
    }

    /**
     * 根据open_id查找商品
     * 
     * @param
     * @return
     */
    @RequestMapping("/shoppingCartList")
    @ResponseBody
    public Object queryByUserId(String openId) {
        JsonPageResult<List<MallShippingCartDO>> shoppingCartResult = new JsonPageResult<>();
        List<MallShippingCartDO> shoppingCartList = mallShippingCartDOMapperExt.selectListByOpenId(openId);
        shoppingCartResult.setData(shoppingCartList);
        return shoppingCartResult;
    }
}
