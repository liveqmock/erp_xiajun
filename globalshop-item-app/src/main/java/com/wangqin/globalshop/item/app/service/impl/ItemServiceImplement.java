package com.wangqin.globalshop.item.app.service.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemDOMapperExt;
import com.wangqin.globalshop.biz1.app.dto.ItemDTO;
import com.wangqin.globalshop.biz1.app.vo.ItemQueryVO;
import com.wangqin.globalshop.biz1.app.vo.JsonPageResult;
import com.wangqin.globalshop.item.app.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImplement implements IItemService {

    @Autowired
    private ItemDOMapperExt    itemDOMapperExt;

    @Autowired
    private IItemSkuService    itemSkuService;

    @Autowired
    private IFreightService    iFreightService;

    @Autowired
    private ItemIInventoryService inventoryService;

    @Autowired
    private IItemBrandService  iBrandService;

    @Autowired
    private IUploadFileService uploadFileService;

    //插入单个商品
    @Override
    public int insertItemSelective(ItemDO item) {
    	item.setCreator("admin");
    	item.setModifier("admin");
    	return itemDOMapperExt.insertItemSelective(item);
    }
  	
    @Override
    public void addItem(ItemDO item) {
    	
    	itemDOMapperExt.insertSelective(item);	

    }

    // 计算当前SKU的运费
    private void skuFreight(ItemSkuDO itemSku) {
        Double packageWeight = 0.0d;
        // 1,获取包装重量
        /**
         * if(itemSku.getPackageId()!=null){ PackageLevel pl= iFreightService.getPackageLevel(itemSku.getPackageId());
         * if(pl==null){ return; }else{ packageWeight = pl.getWeight(); itemSku.setPackageWeight(packageWeight);
         * itemSku.setPackageEn(pl.getPackageEn()); itemSku.setPackageName(pl.getName()); } } Double itemWeight = 0.0d;
         * if(itemSku.getWeight()!=null){ itemWeight = itemSku.getWeight(); }
         **/
        // if(itemSku.getPackageWeight()!=null){
        // packageWeight = itemSku.getPackageWeight();
        // }
        // Long f = iFreightService.calculateFreight(itemWeight, packageWeight);
        // itemSku.setFreight(f);
    }

   

    @Override
    public ItemDO queryItem(Long id) {
        if (id == null) {
            throw new RuntimeException("the item id is null");
        }
        ItemDO item = itemDOMapperExt.selectByPrimaryKey(id);
        if (item != null) {
            // Map<String,Object> clumnMap = new HashMap<>();
            // clumnMap.put("item_id", id);
            List<ItemSkuDO> itemSkus = itemSkuService.queryItemSkusByItemId(id);
            if (itemSkus != null) {
                // item.setItemSkus(itemSkus);
            }
        }
        return item;
    }

    @Override
    public JsonPageResult<List<ItemDTO>> queryItems(ItemQueryVO itemQueryVO) {
        JsonPageResult<List<ItemDTO>> itemResult = new JsonPageResult<>();

        // itemQueryVO.setCompanyId(ShiroUtil.getShiroUser().getCompanyId());

        // 1、查询总的记录数量
        Integer totalCount = itemDOMapperExt.queryItemsCount(itemQueryVO);

        // 2、查询分页记录
        if (totalCount != null && totalCount != 0) {
            itemResult.buildPage(totalCount, itemQueryVO);
            List<ItemDTO> items = itemDOMapperExt.queryItems(itemQueryVO);
            itemResult.setData(items);
        } else {
            List<ItemDTO> items = new ArrayList<>();
            itemResult.setData(items);
        }
        return itemResult;
    }
    
    @Override
    public ItemDTO queryItemById(Long id) {
    	return itemDOMapperExt.queryItemById(id);
    }

    @Override
    public List<ItemDO> queryItems(List<Long> ids) {
        // return itemDOMapperExt.selectBatchIds(ids);
        return null;
    }

    /**
     * 获取所有的 ItemCode and Id 2017-04-04,jc
     */
    @Override
    public Map<String, Long> queryAllItemCodeAndIdHashMap() {
        List<Map<String, Object>> codeAndIdMap = itemDOMapperExt.queryAllItemCodeAndIdHashMap();
        Map<String, Long> result = new HashMap<String, Long>();

        String itemCode = null;
        Long id = null;
        for (Map<String, Object> map : codeAndIdMap) {
            itemCode = null;
            id = null;

            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if ("id".equals(entry.getKey())) {
                    id = ((Long) entry.getValue());
                } else if ("itemCode".equals(entry.getKey())) {
                    itemCode = entry.getKey();
                }
            }
            result.put(itemCode, id);
        }

        return result;
    }

    @Override
    public String insertIntoItemDimension(String sceneStr, String pages, String accessToken) {
        RestTemplate rest = new RestTemplate();
        InputStream inputStream = null;
        OutputStream outputStream = null;
        String picUrl = null;
        try {
            String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + accessToken;
            Map<String, Object> param = new HashMap<>();
            param.put("scene", sceneStr);
            param.put("page", pages);
            // param.put("path", pages);
            param.put("width", 430);
            param.put("auto_color", false);
            Map<String, Object> line_color = new HashMap<>();
            line_color.put("r", 0);
            line_color.put("g", 0);
            line_color.put("b", 0);
            param.put("line_color", line_color);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            HttpEntity requestEntity = new HttpEntity(param, headers);
            ResponseEntity<byte[]> entity = rest.exchange(url, HttpMethod.POST, requestEntity, byte[].class);
            byte[] result = entity.getBody();
            inputStream = new ByteArrayInputStream(result);

            StringBuilder sb = new StringBuilder();
            sb.append((int) (Math.random()
                             * 100)).append("_").append(System.currentTimeMillis()).append(".").append("jpg");
            String picKey = sb.toString();
            picUrl = uploadFileService.uploadImg(inputStream, picKey);
            if (picUrl.isEmpty()) {
                throw new RuntimeException("二维码生成异常！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("调用生成微信URL接口传参:" + e);
            // LOG.error("调用小程序生成微信永久小程序码URL接口异常",e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return picUrl;

    }

    @Override
    public JsonPageResult<List<ItemDO>> queryHaihuItems(ItemQueryVO itemQueryVO) {
        JsonPageResult<List<ItemDO>> itemResult = new JsonPageResult<>();
        // 1、查询总的记录数量
        Integer totalCount = itemDOMapperExt.queryItemsCountByhaihu(itemQueryVO);

        // 2、查询分页记录
        if (totalCount != null && totalCount != 0) {
            itemResult.buildPage(totalCount, itemQueryVO);
            List<ItemDO> items = itemDOMapperExt.queryHaihuItems(itemQueryVO);
            itemResult.setData(items);
        } else {
            List<ItemDO> items = new ArrayList<>();
            itemResult.setData(items);
        }
        return itemResult;
    }

    @Override
    public List<ItemDO> queryHaihuByUptime(ItemQueryVO itemQueryVO) {
        List<ItemDO> items = itemDOMapperExt.queryHaihuByUptime(itemQueryVO);
        return items;
    }

    @Override
    public Integer sumNewItemNumByDate(Integer days) {
        return itemDOMapperExt.sumNewItemNumByDate(days);
    }

    @Override
    public Integer sumNewItemNumByMonth(Integer months) {
        return itemDOMapperExt.sumNewItemNumByMonth(months);
    }

 

    @Override
    public ItemDO selectByPrimaryKey(Long id) {
        // TODO Auto-generated method stub

        return itemDOMapperExt.selectByPrimaryKey(id);
    }

	@Override
	public List<ItemDTO> queryItemListSelective(ItemQueryVO itemQueryVO) {
		return itemDOMapperExt.queryItems(itemQueryVO);
	}
    
	@Override
	public void updateByPrimaryKeySelective(ItemDO item) {
		itemDOMapperExt.updateByIdSelective(item);
	}

   
}
