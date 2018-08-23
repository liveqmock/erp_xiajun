package com.wangqin.globalshop.item.app.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.wangqin.globalshop.biz1.app.bean.dataVo.*;
import com.wangqin.globalshop.biz1.app.bean.dataVo.JsonPageResult;
import com.wangqin.globalshop.biz1.app.bean.dataVo.JsonResult;
import com.wangqin.globalshop.biz1.app.bean.dto.ItemDTO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ItemSkuQueryVO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.*;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.*;
import com.wangqin.globalshop.biz1.app.exception.BizCommonException;
import com.wangqin.globalshop.channelapi.dal.*;
import com.wangqin.globalshop.common.base.BaseDto;
import com.wangqin.globalshop.common.enums.AppletType;
import com.wangqin.globalshop.common.redis.Cache;
import com.wangqin.globalshop.common.utils.*;
import com.wangqin.globalshop.inventory.app.service.InventoryService;
import com.wangqin.globalshop.item.app.service.*;
import com.wangqin.globalshop.item.app.service.impl.entity.ShareTokenEntity;
import com.wangqin.globalshop.item.app.util.ItemUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.URLDecoder;
import java.util.*;

@Service
public class ItemServiceImplement implements IItemService {
    @Value("#{sys.IMGURL}")
    private String imgUrl;

    @Autowired
    private CompanyDOMapperExt companyDOMapperExt;
    @Autowired
    private ItemDOMapperExt itemDOMapperExt;
    @Autowired
    private IItemBrandService brandService;
    @Autowired
    private IItemSkuScaleService scaleService;
    @Autowired
    private InventoryService invService;
    @Autowired
    private ChannelListingItemDOMapperExt channelListingItemDOMapperExt;
    @Autowired
    private ChannelListingItemSkuDOMapperExt channelListingItemSkuDOMapperExt;
    @Autowired
    private InventoryMapperExt inventoryDOMapperExt;
    @Autowired
    private ItemSkuScaleMapperExt itemSkuScaleDOMapper;
    @Autowired
    private IItemSkuService itemSkuService;
    @Autowired
    private IItemBrandService iBrandService;
    @Autowired
    private IUploadFileService uploadFileService;
    @Autowired
    private ItemSkuMapperExt itemSkuMapperExt;
    @Autowired
    private IItemCategoryService categoryService;
    @Autowired
    private Cache shareCache;
    @Autowired
    private IAppletConfigService appletConfigService;
    @Autowired
    private IItemSubOrderService orderService;
    @Autowired
    private ItemQrcodeShareDOMapperExt qrcodeShareDOMapperExt;
    @Autowired
    private ICountryService countryServiceImpl;
    
    public static final String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";
    public static final String ACCESS_TOKEN_PART = "grant_type=client_credential&appid=";
    public static final String ACCESS_TOKEN_MI = "&secret=";


    @Override
    public ItemDO queryItemDOByItemCode(String itemCode) {
        return itemDOMapperExt.queryItemDOByItemCode(itemCode);
    }

    @Override
    public String queryItemCodeById(Long id) {
        return itemDOMapperExt.queryItemCodeById(id);
    }

    //根据id更新商品(在用)
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateByIdSelective(ItemDO item) {
        itemDOMapperExt.updateByIdSelective(item);
    }

    //插入单个商品(在用)
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insertItemSelective(ItemDO item) {
        return itemDOMapperExt.insertItemSelective(item);
    }

    //商品管理->商品发布
    @Transactional(rollbackFor = BizCommonException.class)
    @Override
    public Object addItem(ItemQueryVO item) {
        JsonResult<ItemDO> result = new JsonResult<>();
        if (!loginCheck()) {
            return result.buildIsSuccess(false).buildMsg("请先登录");
        }
        String companyNo = AppUtil.getLoginUserCompanyNo();
        String userNo = AppUtil.getLoginUserId();
        ItemDO newItem = new ItemDO();

        //类目处理
        String categoryCode = item.getCategoryCode();
        newItem.setCategoryName(categoryService.queryByCategoryCode(categoryCode).getName());
        //商品必须有主图
        if (!ItemUtil.picCheck(item.getMainPic())) {
            return result.buildIsSuccess(false).buildMsg("商品必须有主图");
        }
        //系统自动生成item_code
        String itemCode = ItemEnCodeUtil.generateItemCode(categoryCode);
        // 解析skuList 数组对象
        String skuList = item.getSkuList();
        List<Double> itemSkuPriceList = new ArrayList<Double>();
        List<ItemSkuAddVO> skus = new ArrayList<ItemSkuAddVO>();
        try {
            String s = skuList.replace("&quot;", "\"");
            skus = HaiJsonUtils.toBean(s, new TypeReference<List<ItemSkuAddVO>>() { });
        } catch (Exception e) {
            return result.buildMsg("解析SKU错误").buildIsSuccess(false);
        }
        int i = 0;
        for (ItemSkuAddVO itemSku : skus) {
        	itemSkuPriceList.add(itemSku.getSalePrice());
        	itemSku.setSkuCode(ItemEnCodeUtil.generateSkuCode(itemCode, i++));
        	//sku没有图片就用商品的图片
        	if (!ItemUtil.picCheck(itemSku.getSkuPic())) {//没图
        		itemSku.setSkuPic(item.getMainPic());
        	}
        	//检测upc是否和数据库里面已有的upc重复,按公司划分
            List<String> codeList = itemSkuService.querySkuCodeListByUpc(companyNo, itemSku.getUpc());
            if (IsEmptyUtil.isCollectionNotEmpty(codeList)) {
                return result.buildIsSuccess(false).buildMsg("新增失败，添加的upc和已有的upc重复");
            }   
        }
        //上架处理
        try {
            ItemUtil.handleShelf(item, newItem);
        } catch (Exception e) {
            return result.buildIsSuccess(false).buildMsg("上架时间填写错误");
        }
       
        //可以直接从PageBean存在数据库的字段
        ItemUtil.transItemVoToDO(item, newItem, companyNo, userNo, categoryCode, itemCode);
        //对不能直接转换的字段做处理
        detailDecoder(newItem);
        newItem.setPriceRange(PriceUtil.calNewPriceRange(itemSkuPriceList));     
        newItem.setBrandNo(brandService.selectBrandNoByName(item.getBrand().split("->")[0]));        
        generateQrCode(newItem, companyNo);  
        //渠道处理
        ItemUtil.handleThirdSale(newItem, item);        
        //插入itemsku和库存
        for (ItemSkuAddVO itemSku : skus) {                    
            //插入规格信息
            insertItemScaleInfo(itemSku, companyNo, userNo, itemCode);
            //item_sku所需的其他信息
            ItemUtil.genItemSku(itemSku, newItem, companyNo, userNo, itemCode);            
        }
        itemSkuService.insertBatch(skus);
        List<InventoryDO> inventoryList = itemSkuService.initInventory(skus);
        invService.outbound(inventoryList);
        insertItemSelective(newItem);
        return result.buildIsSuccess(true).buildMsg("添加商品成功");
    }

    /**
     * 商品发布->插入规格信息
     */
    @Transactional
    private void insertItemScaleInfo(ItemSkuAddVO itemSku, String companyNo, String userNo, String itemCode) {    	
        if (IsEmptyUtil.isStringNotEmpty(itemSku.getColor())) {
            ItemSkuScaleDO color = ItemUtil.genScaleDO(companyNo,userNo,"颜色",itemSku.getColor(),itemSku.getSkuCode(),itemCode);
            scaleService.insertSelective(color);
        }
        if (IsEmptyUtil.isStringNotEmpty(itemSku.getScale())) {
        	ItemSkuScaleDO scale = ItemUtil.genScaleDO(companyNo,userNo,"尺寸",itemSku.getScale(),itemSku.getSkuCode(),itemCode);
            scaleService.insertSelective(scale);
        }
    }

    /**
     * 封装ItemSkuScala对象信息
     *
     * @param obj     封装的对象
     * @param itemSku
     * @param value   scalaValue
     * @param name    scalaName
     * @author ChenZiHao
     */
    private void setInfo(ItemSkuScaleDO obj, ItemSkuAddVO itemSku, String value, String name) {
        obj.setSkuCode(itemSku.getSkuCode());
        obj.setItemCode(itemSku.getItemCode());
        obj.setScaleCode(CodeGenUtil.getScaleCode());
        obj.setScaleName(name);
        obj.setScaleValue(value);
        obj.init();

    }

    /**
     * 商品列表
     *
     * @param itemQueryVO
     * @return
     */
    @Override
    @Transactional(rollbackFor = BizCommonException.class)
    public JsonPageResult<List<ItemDTO>> queryItems(ItemQueryVO itemQueryVO) {
        JsonPageResult<List<ItemDTO>> itemResult = new JsonPageResult<>();
        // 1、查询总的记录数量
        Integer totalCount = itemDOMapperExt.queryItemsCount(itemQueryVO);
        itemResult.setTotalCount(totalCount);
        Integer totalPage = totalCount / itemQueryVO.getPageSize();
        itemResult.setTotalPage(totalPage);
        itemResult.setPageIndex(itemQueryVO.getPageIndex());

        // 2、查询分页记录
        if (totalCount != null && totalCount != 0) {
            List<ItemDTO> items = itemDOMapperExt.queryItems(itemQueryVO);
            if (IsEmptyUtil.isCollectionNotEmpty(items)) {
                for (ItemDTO item : items) {
                    Integer salesVolume = orderService.calItemSalesVolume(item.getItemCode(), AppUtil.getLoginUserCompanyNo());
                    item.setSalesVolume(salesVolume);
                }
            }
            itemResult.setData(items);
        } else {
            List<ItemDTO> items = new ArrayList<>();
            itemResult.setData(items);
        }
        return itemResult;
    }

    /**
     * 根据id查询商品，商品编辑使用(fin)
     */
    @Override
    @Transactional(rollbackFor = BizCommonException.class)
    public ItemDTO queryItemById(Long id) {
        ItemDTO item = itemDOMapperExt.queryItemById(id);
        if (null == item) {
            throw new RuntimeException("商品不存在或者已删除");
        }
        List<ItemSkuQueryVO> itemSkus = itemSkuService.querySkuListByItemCodeContainsVirtualInvScale(item.getItemCode());
        if (EasyUtil.isListEmpty(itemSkus)) {
            throw new RuntimeException("商品无sku，无法编辑");
        }
        for (ItemSkuQueryVO sku : itemSkus) {
            //处理佣金比率
            sku.setSkuRateString(ItemUtil.multiplyOneHundred(sku.getSkuRate()));
            String skuCode = sku.getSkuCode();
            List<ItemSkuScaleDO> skuScaleList = itemSkuScaleDOMapper.selectScaleNameValueBySkuCode(skuCode);
            if (!EasyUtil.isListEmpty(skuScaleList)) {
                for (ItemSkuScaleDO scale : skuScaleList) {
                    if ("颜色".equals(scale.getScaleName())) {
                        sku.setColor(scale.getScaleValue());
                    }
                    if ("尺寸".equals(scale.getScaleName())) {
                        sku.setScale(scale.getScaleValue());
                    }
                }
            }
        }
        item.setItemSkus(itemSkus);
        return item;
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


    public ShareTokenEntity getTokenFromCache(String uuid) {
        ShareTokenEntity entity = null;
        if (StringUtils.isBlank(uuid)) {
            return entity;
        }
        String tokenStr = (String) shareCache.get(uuid);
        if (StringUtils.isNotBlank(tokenStr)) {
            entity = BaseDto.fromJson(tokenStr, ShareTokenEntity.class);
        }
        return entity;
    }

    public String generateItemShareUrl(String userId, String companyNo, String itemCode, String pages, String accessToken) {

        String key = String.format("%s-uuid-%s-%s-%s", "token", userId, companyNo, itemCode);

        //String picUrl = (String) shareCache.get(key);

        String picUrl = qrcodeShareDOMapperExt.selectPicUrl(userId, companyNo, itemCode);

        if (StringUtils.isBlank(picUrl)) {
            UUID uid = UUID.randomUUID();
            String uuid = uid.toString().replaceAll("-", "");
            //ShareTokenEntity tokenEntity = ShareTokenEntity.buildShareToken(userId, companyNo, itemCode, uuid);
            picUrl = insertIntoItemDimension(uuid, pages, accessToken);

            ItemQrcodeShareDO itemQrcodeShareDO = new ItemQrcodeShareDO();
            itemQrcodeShareDO.setCompanyNo(companyNo);
            itemQrcodeShareDO.setShareNo(uuid);
            itemQrcodeShareDO.setItemCode(itemCode);
            itemQrcodeShareDO.setUserNo(userId);
            itemQrcodeShareDO.setPicUrl(picUrl);
            itemQrcodeShareDO.init4NoLogin();

            qrcodeShareDOMapperExt.insert(itemQrcodeShareDO);
        }
        return picUrl;
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
            sb.append((int) (Math.random() * 100)).append("_").append(System.currentTimeMillis()).append(".").append("jpg");
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
    @Transactional(rollbackFor = BizCommonException.class)
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
    public List<ItemDTO> queryItemListSelective(ItemQueryVO itemQueryVO) {
        List<ItemDTO> list = itemDOMapperExt.queryItems(itemQueryVO);
        for (ItemDTO item : list) {
            item.setCategoryId(337L);
        }
        return itemDOMapperExt.queryItems(itemQueryVO);
    }


    @Override
    public Long queryIdByItemCode(String itemCode) {
        return itemDOMapperExt.queryIdByItemCode(itemCode);
    }


    /**
     * 专供渠道查询
     *
     * @param itemCode
     * @return
     */
    @Override
    @Transactional(rollbackFor = BizCommonException.class)
    public ItemVo queryAdd(String itemCode) {

        ItemVo itemVo = new ItemVo();
        ItemDO itemDo = itemDOMapperExt.queryItemByItemCode(itemCode);
        if (itemDo != null) {
            BeanUtils.copies(itemDo, itemVo);
        }
        List<ItemSkuVo> itemSkuVos = new ArrayList<>();
        ItemSkuDO skuSo = new ItemSkuDO();
        skuSo.setItemCode(itemDo.getItemCode());
        List<ItemSkuDO> skuList = itemSkuService.querySkuListByItemCode(itemCode);
        for (ItemSkuDO sku : skuList) {
            ItemSkuVo itemSkuVo = new ItemSkuVo();
            if (sku != null) {
                BeanUtils.copies(sku, itemSkuVo);
            }
            //库存
            InventoryDO inventoryDO = inventoryDOMapperExt.queryBySkuCodeAndCompanyNo(sku.getSkuCode(), itemDo.getCompanyNo());
            itemSkuVo.setInventoryDO(inventoryDO);

            //规格尺寸
            List<ItemSkuScaleDO> itemSkuScaleDOS = itemSkuScaleDOMapper.selectScaleNameValueBySkuCode(sku.getSkuCode());
            Map<String, ItemSkuScaleDO> scaleMap = new HashMap<>();
            for (ItemSkuScaleDO scale : itemSkuScaleDOS) {
                scaleMap.put(scale.getScaleCode(), scale);
            }
            itemSkuVo.setScaleMap(scaleMap);

            itemSkuVos.add(itemSkuVo);

        }
        itemVo.setItemSkus(itemSkuVos);
        return itemVo;

    }

    /**
     * 专供渠道查询
     *
     * @param itemCode
     * @return
     */
    @Override
    @Transactional(rollbackFor = BizCommonException.class)
    public GlobalShopItemVo queryUpdate(String itemCode, String shopCode) {

        GlobalShopItemVo resultVo = new GlobalShopItemVo();

        ItemVo itemVo = this.queryAdd(itemCode);

        resultVo.setItemVo(itemVo);

        ChannelListingItemVo channelListingItemVo = new ChannelListingItemVo();

        ChannelListingItemDO so = new ChannelListingItemVo();

        so.setItemCode(itemVo.getItemCode());
        so.setShopCode(shopCode);

        ChannelListingItemDO channelListingItemDO = channelListingItemDOMapperExt.queryPo(so);

        if (channelListingItemDO != null) {
            BeanUtils.copies(channelListingItemDO, channelListingItemVo);
        }


        ChannelListingItemSkuDO skuSo = new ChannelListingItemSkuDO();
        skuSo.setChannelItemCode(channelListingItemDO.getChannelItemCode());


        List<ChannelListingItemSkuDO> channelListingItemSkuDOS = channelListingItemSkuDOMapperExt.queryPoList(skuSo);

        List<ChannelListingItemSkuVo> channelListingItemSkuVos = new ArrayList<>();
        for (ChannelListingItemSkuDO sku : channelListingItemSkuDOS) {
            ChannelListingItemSkuVo skuVo = new ChannelListingItemSkuVo();
            if (sku != null) {
                BeanUtils.copies(sku, skuVo);
            }
            channelListingItemSkuVos.add(skuVo);
        }

        channelListingItemVo.setChannelListingItemSkuVos(channelListingItemSkuVos);


        resultVo.setChannelListingItemVo(channelListingItemVo);

        return resultVo;

    }

    @Override
    @Transactional(rollbackFor = BizCommonException.class)
    public void dealItemAndChannelItem4JdAdd(JdCommonParam jdCommonParam, GlobalShopItemVo globalShopItemVo) {

        ChannelListingItemVo channelListingItemVo = globalShopItemVo.getChannelListingItemVo();

        //第一步：channellistingitem
        ChannelListingItemDO channelListingItemDO = new ChannelListingItemDO();
        BeanUtils.copies(channelListingItemVo, channelListingItemDO);
        channelListingItemDOMapperExt.insert(channelListingItemDO);

        //第二步：channellistingitemSku
        List<ChannelListingItemSkuVo> channelListingItemSkuVoS = channelListingItemVo.getChannelListingItemSkuVos();
        for (ChannelListingItemSkuVo sku : channelListingItemSkuVoS) {
            ChannelListingItemSkuDO channelListingItemSkuDO = new ChannelListingItemSkuDO();
            BeanUtils.copies(sku, channelListingItemSkuDO);
            channelListingItemSkuDOMapperExt.insert(channelListingItemSkuDO);
        }

        ItemVo itemVo = globalShopItemVo.getItemVo();

        ItemDO itemDO = new ItemDO();
        BeanUtils.copies(itemVo, itemDO);
        itemDOMapperExt.insert(itemDO);

        List<ItemSkuVo> skuVos = itemVo.getItemSkus();
        for (ItemSkuVo skuVo : skuVos) {
            ItemSkuDO skuDO = new ItemSkuDO();
            BeanUtils.copies(skuVo, skuDO);
            itemSkuMapperExt.insert(skuDO);
        }
    }

    @Override
    @Transactional(rollbackFor = BizCommonException.class)
    public void dealItemAndChannelItem4JdTask(JdCommonParam jdCommonParam, GlobalShopItemVo globalShopItemVo) {

        ChannelListingItemVo channelListingItemVo = globalShopItemVo.getChannelListingItemVo();

        //第一步：channellistingitem
        ChannelListingItemDO channelListingItemDO = new ChannelListingItemDO();
        BeanUtils.copies(channelListingItemVo, channelListingItemDO);
        channelListingItemDOMapperExt.insert(channelListingItemDO);

        //第二步：channellistingitemSku
        List<ChannelListingItemSkuVo> channelListingItemSkuVoS = channelListingItemVo.getChannelListingItemSkuVos();
        for (ChannelListingItemSkuVo sku : channelListingItemSkuVoS) {
            ChannelListingItemSkuDO channelListingItemSkuDO = new ChannelListingItemSkuDO();
            BeanUtils.copies(sku, channelListingItemSkuDO);
            channelListingItemSkuDOMapperExt.insert(channelListingItemSkuDO);
        }

        ItemVo itemVo = globalShopItemVo.getItemVo();

        ItemDO itemDO = new ItemDO();
        BeanUtils.copies(itemVo, itemDO);
        itemDOMapperExt.insert(itemDO);

        List<ItemSkuVo> skuVos = itemVo.getItemSkus();
        for (ItemSkuVo skuVo : skuVos) {
            ItemSkuDO skuDO = new ItemSkuDO();
            BeanUtils.copies(skuVo, skuDO);
            itemSkuMapperExt.insert(skuDO);
        }
    }


    @Override
    @Transactional(rollbackFor = BizCommonException.class)
    public void importItem(List<List<Object>> list) throws Exception {
        try {
            List<String> errMsg = new ArrayList<>();
            List<ItemDO> itemList = new ArrayList<>();
            List<ItemSkuDO> skuList = new ArrayList<>();
            List<ItemSkuScaleDO> scaleList = new ArrayList<>();
            List<String> upcs = new ArrayList();
            ItemDO item;
            Map<String, ItemDO> map = new HashMap<>(1000);
            /**图片链接*/
            String s = ImgUtil.initImg2Json(imgUrl);
            int i = 0;
            if (list.size() > 1000) {
                throw new BizCommonException("最多只能导入一千条");
            }
            if (list.size() == 0) {
                throw new BizCommonException("当前导入为空");
            }
            for (List<Object> obj : list) {
                i++;
                ItemSkuDO itemSku = new ItemSkuDO();
                //外部商品代码
                String sn = obj.get(0).toString().trim();
                if (!map.containsKey(sn)) {
                    item = new ItemDO();
                    /**商品名称*/
                    String itemName = obj.get(2).toString().trim();
                    if (StringUtils.isBlank(itemName)) {
                        errMsg.add("第" + i + "行:商品名不能为空");
                    } else {
                        item.setItemName(itemName);
                    }


                    String brandEnName = obj.get(3).toString();
                    /*让英文品牌中间只隔一个空格*/
                    String[] names = brandEnName.split(" ");
                    StringBuilder sb = new StringBuilder();
                    for (String name : names) {
                        if ("".equals(name.trim())) {
                            continue;
                        }
                        sb.append(" ").append(name);
                    }
                    brandEnName = sb.toString().substring(1);


                    /**品牌(英文)*/
                    if (StringUtils.isBlank(brandEnName)) {
                        errMsg.add("第" + i + "行:品牌(英文)不能为空");
                    } else {
                        /**品牌(中文)*/
                        String brandCnName = obj.get(4).toString().trim();
                        brandCnName = brandCnName == null ? "" : brandCnName;
                        item.setBrandName(brandEnName + " " + brandCnName);
                        List<ItemBrandDO> brand = iBrandService.queryByEnName(brandEnName);
                        if (brand.size() == 0) {
                            errMsg.add("第" + i + "行:找不到" + brandEnName + " " + brandCnName + "对应的品牌");
                        } else if (brand.size() > 1) {
                            errMsg.add("第" + i + "行:" + brandEnName + " " + brandCnName + "对应的品牌不唯一");
                        } else {
                            item.setBrandNo(brand.get(0).getBrandNo());
                        }
                    }

                    /**类目1*/
                    String category1 = obj.get(5).toString().trim();
                    /**类目2*/
                    String category2 = obj.get(6).toString().trim();
                    /**类目3*/
                    String category3 = obj.get(7).toString().trim();
                    List<ItemCategoryDO> categoryList = null;
                    if (EasyUtil.isStringEmpty(category1) || EasyUtil.isStringEmpty(category1) || EasyUtil.isStringEmpty(category1)) {
                        errMsg.add("第" + i + "行:类目为空");
                    } else {
                        List<ItemCategoryDO> categoryList1 = categoryService.selectByName(category1);
                        if (categoryList1.size() != 0) {
                            List<ItemCategoryDO> categoryList2 = categoryService.selectByParentAndName(categoryList1, category2);
                            if (categoryList2.size() != 0) {
                                categoryList = categoryService.selectByParentAndName(categoryList2, category3);
                            }

                        }

                    }

                    if (categoryList == null || categoryList.size() == 0) {
                        errMsg.add("第" + i + "行:找不到" + category1 + "/" + category2 + "/" + category3 + "对应的类目");
                    } else if (categoryList.size() > 1) {
                        errMsg.add("第" + i + "行:" + category1 + "/" + category2 + "/" + category3 + "对应的类目不唯一");
                    } else {
                        ItemCategoryDO category = categoryList.get(0);
                        String categoryCode = category.getCategoryCode();
                        item.setCategoryCode(categoryCode);

                    }


                    item.setMainPic(s);
                    String itemCode = CodeGenUtil.generateItemCode(item.getCategoryCode());
                    item.setItemCode(itemCode);
                    if (categoryList == null || categoryList.size() == 0) {
                        errMsg.add("第" + i + "行:找不到" + category1 + "/" + category2 + "/" + category3 + "对应的类目");
                    } else if (categoryList.size() > 1) {
                        errMsg.add("第" + i + "行:" + category1 + "/" + category2 + "/" + category3 + "对应的类目不唯一");
                    } else {
                        ItemCategoryDO category = categoryList.get(0);
                        item.setCategoryCode(category.getCategoryCode());
                        item.setCategoryName(category.getName());

                    }

                    /**重量*/
                    String weight = obj.get(14).toString();
                    weight = StringUtil.isBlank(weight) ? "0" : weight;
                    if (isParseToDouble(weight)) {
                        item.setWeight(Double.valueOf(weight));
                        itemSku.setWeight(Double.valueOf(weight));
                    }
                    /**采购地*/
                    String purchaseFrom = obj.get(10).toString().trim();
                    //todo
                    Long s1 = countryServiceImpl.queryCodeByName(purchaseFrom);
                    if (s1 != null) {
                        item.setCountry(s1.toString());
                    }
                    /**币种*/
                    String currency = obj.get(11).toString();
                    currency = StringUtil.isBlank(currency) ? "0" : currency;
                    if (isParseToInteger(currency)) {
                        item.setCurrency(Byte.valueOf(currency));
                    } else {
                        errMsg.add("存在未知格式的数据:第" + i + "行 第13列的  " + currency);
                    }
                    map.put(sn, item);
                    itemList.add(item);
                } else {
                    item = map.get(sn);
                }

                /**重量*/
                String weight = obj.get(14).toString();
                weight = StringUtil.isBlank(weight) ? "0" : weight;
                if (isParseToDouble(weight)) {
                    itemSku.setWeight(Double.valueOf(weight));
                } else {
                    errMsg.add("存在未知格式的数据:第" + i + "行 第15列的  " + weight);
                }
                itemSku.setBrandName(item.getBrandName());
                itemSku.setItemName(item.getItemName());
                itemSku.setCategoryCode(item.getCategoryCode());
                itemSku.setCategoryCode(item.getCategoryName());

                /**销售价*/
                String salePrice = obj.get(12).toString();
                salePrice = StringUtil.isBlank(salePrice) ? "0" : salePrice;
                if (isParseToDouble(salePrice)) {
                    Double salePrice1 = Double.valueOf(salePrice);
                    itemSku.setSalePrice(salePrice1);
                    String s1 = updateItemPriceRange(item, salePrice);
                    if (!EasyUtil.isStringEmpty(s1)) {
                        errMsg.add("第" + i + "行 第13列 " + s1);
                    }
                } else {
                    errMsg.add("存在未知格式的数据:第" + i + "行 第13列的  " + salePrice);
                }
                /**UPC*/
                String upc = obj.get(1).toString().trim();
                if (StringUtils.isBlank(upc)) {
                    errMsg.add("第" + i + "行:upc不能为空");
                } else {
                    //检测upc是否和数据库里面已有的upc重复,按公司划分
                    //todo
                    Integer duplcatedCountNumber = itemSkuService.queryCountByUpcAndCompanyNo(AppUtil.getLoginUserCompanyNo(), upc);
                    if (0 < duplcatedCountNumber) {
                        errMsg.add("第" + i + "行:商品库中已存在对应的upc" + upc);
                    }
                    if (upcs.contains(upc)) {
                        errMsg.add("第" + i + "行:本次导入中存在重复的upc" + upc);
                    }
                    upcs.add(upc);
                    itemSku.setUpc(upc);
                }


                /**规格(颜色)*/
                String scala1 = obj.get(8).toString().trim();
                /**规格(尺寸)*/
                String scala2 = obj.get(9).toString().trim();

                /**SKU编号*/
                String skuCode = CodeGenUtil.generateSkuCode(item.getItemCode(), i);
                itemSku.setSkuCode(skuCode);
                /**虚拟库存*/
                String virInv = obj.get(13).toString();
                virInv = StringUtil.isBlank(virInv) ? "0" : virInv;
                if (isParseToLong(virInv)) {
//                    itemSku.setVirtualInv(Integer.valueOf(virInv));
                    InventoryDO inventory = new InventoryDO();
                    inventory.setItemName(item.getItemName());
                    inventory.setItemCode(item.getItemCode());
                    inventory.setSkuCode(skuCode);
                    inventory.setUpc(upc);
                    inventory.setVirtualInv(Long.valueOf(virInv));
                    invService.outbound(inventory);
                } else {
                    errMsg.add("存在未知格式的数据:第" + i + "行 第14列的  " + virInv);
                }

                itemSku.setSkuPic(s);


                itemSku.setItemCode(item.getItemCode());
                item.init();
                itemSku.init();

                skuList.add(itemSku);
                addInfo2ScaleList(scala1, scala2, scaleList, item.getItemCode(), skuCode);
            }
            int size = errMsg.size();
            if (size == 0) {
                itemDOMapperExt.insertBatch(itemList);
                itemSkuMapperExt.inserBatch(skuList);
                if (scaleList.size() != 0) {
                    itemSkuScaleDOMapper.insertBatch(scaleList);
                }
            } else if (size < 10) {
                throw new BizCommonException(errMsg.toString());
            } else {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < 10; j++) {
                    String s1 = errMsg.get(j);
                    sb.append(s1).append("\n");
                }
                sb.append("....");
                throw new BizCommonException(sb.toString());
            }

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }


    private String updateItemPriceRange(ItemDO item, String salePrice) {
        try {
            String priceRange = item.getPriceRange();
            Double min, max;
            if (EasyUtil.isStringEmpty(priceRange)) {
                item.setPriceRange(salePrice);
                return null;
            }
            Double price = Double.valueOf(salePrice);
            if (priceRange.contains("-")) {
                String[] split = priceRange.split("-");
                min = Double.valueOf(split[0]);
                max = Double.valueOf(split[1]);

                if (min > price) {
                    item.setPriceRange(price + "-" + max);
                    return null;
                }
                if (max < price) {
                    item.setPriceRange(min + "-" + price);
                    return null;
                }
            } else {
                min = Double.valueOf(priceRange);
                if (min > price) {
                    item.setPriceRange(price + "-" + min);
                    return null;
                }
                if (min < price) {
                    item.setPriceRange(min + "-" + price);
                    return null;

                }
            }
        } catch (Exception e) {
            return "更新价格区间错误";
        }
        return null;

    }

    private boolean isParseToLong(String virInv) {
        try {
            Long.valueOf(virInv);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }


    /**
     * 判断是否能够转换成Double类型
     *
     * @param price
     */
    private boolean isParseToDouble(String price) {
        try {
            Double.valueOf(price);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * 根据颜色和尺寸完成itemSkuScale对象的封装并装配到链表中
     *
     * @param scala1    颜色对应的值
     * @param scala2    尺寸对应的值
     * @param scaleList 装配itemSkuScale对象的链表
     * @param itemCode
     * @param skuCode
     */
    private void addInfo2ScaleList(String scala1, String scala2, List<ItemSkuScaleDO> scaleList, String itemCode, String skuCode) {
        if (StringUtils.isNotBlank(scala1)) {
            ItemSkuScaleDO itemScale1 = new ItemSkuScaleDO();
            itemScale1.init();
            itemScale1.setScaleCode(CodeGenUtil.getScaleCode());
            itemScale1.setScaleName("颜色");
            itemScale1.setScaleValue(scala1);
            itemScale1.setItemCode(itemCode);
            itemScale1.setSkuCode(skuCode);
            scaleList.add(itemScale1);
        }
        if (StringUtils.isNotBlank(scala2)) {
            ItemSkuScaleDO itemScale2 = new ItemSkuScaleDO();
            itemScale2.init();
            itemScale2.setScaleCode(CodeGenUtil.getScaleCode());
            itemScale2.setScaleName("尺寸");
            itemScale2.setScaleValue(scala2);
            itemScale2.setItemCode(itemCode);
            itemScale2.setSkuCode(skuCode);
            scaleList.add(itemScale2);
        }
    }

    /**
     * 判断是否能够转换成Integer类型
     *
     * @param maxPrice
     */
    private Boolean isParseToInteger(String maxPrice) {
        try {
            Integer.valueOf(maxPrice);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /*
     *商品详情处理
     */
    private void detailDecoder(ItemDO item) {
        if (StringUtils.isNotBlank(item.getDetail())) {
            String detail = item.getDetail();
            try {
                String deStr = URLDecoder.decode(detail, "UTF-8");
                item.setDetail(deStr);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }


    //一键分享商品的首页
    @Override
    public List<ItemDO> queryItemByStatus(String companyNo, String status, int start, int pageSize) {
        // TODO Auto-generated method stub
        return itemDOMapperExt.queryItemByStatus(companyNo, status, start, pageSize);
    }

    //一键分享商品的搜索
    @Override
    public List<ItemDO> queryItemByKeyWord(List<String> keyWord, String companyNo, int start, int pageSize) {
        // TODO Auto-generated method stub
        return itemDOMapperExt.queryItemByKeyWord(keyWord, companyNo, start, pageSize);
    }

    //一键分享商品的详情
    @Override
    public ItemDO itemDetailByItemCode(String itemCode, String companyNo) {
        // TODO Auto-generated method stub
        return itemDOMapperExt.itemDetailByItemCode(itemCode, companyNo);
    }


    //一键分享，获取商品的图片
    @Override
    public String queryItemPicByItemCode(String itemCode) {
        return itemDOMapperExt.queryItemPicByItemCode(itemCode);
    }

    //一键分享，获取商品的图片
    @Override
    public String queryItemPicByItemCodeAndCompanyNo(String itemCode, String companyNo) {
        return itemDOMapperExt.queryItemPicByItemCodeAndCompanyNo(itemCode, companyNo);
    }

    /**
     * 工具类
     * 用户登录判断
     *
     * @return
     */
    public Boolean loginCheck() {
        if (IsEmptyUtil.isStringEmpty(AppUtil.getLoginUserCompanyNo()) || IsEmptyUtil.isStringEmpty(AppUtil.getLoginUserId())) {
            return false;
        }
        return true;
    }


    /**
     * 工具类
     * 商品列表->商品发布->自动生成二维码
     * 二维码生成
     *
     * @return
     */
    private void generateQrCode(ItemDO newItem, String companyNo) {
        CompanyDO companyDO = companyDOMapperExt.selectByCompanyNo(companyNo);
        if (null != companyDO) {
            if (IsEmptyUtil.isStringNotEmpty(companyDO.getCompanyGroup())) {
                companyNo = companyDO.getCompanyGroup();
            }
        }
        AppletConfigDO appletConfig = appletConfigService.queryWxMallConfigInfoByCompanyNo(companyNo, AppletType.MALL_APPLET.getValue());
        if (null == appletConfig) {
            return;
        }
        String appId = appletConfig.getAppid();
        String secret = appletConfig.getSecret();
        String accessToken = appletConfig.getAuthorizerAccessToken();
        String token = "";
        if (IsEmptyUtil.isStringNotEmpty(accessToken)) {
            token = accessToken;
        } else {
            if (IsEmptyUtil.isStringEmpty(appId) || IsEmptyUtil.isStringEmpty(secret)) {
                return;
            }
            String reponse = DimensionCodeUtil.sendGet(TOKEN_URL, ACCESS_TOKEN_PART + appId + ACCESS_TOKEN_MI + secret);
            JSONObject myJson = JSONObject.fromObject(reponse);
            token = (String) myJson.get("access_token");
        }
        String uuid = "item" + newItem.getItemCode();
        String picUrl = insertIntoItemDimension(uuid, "pages/item/detail", token);
        if (IsEmptyUtil.isStringNotEmpty(picUrl)) {
            newItem.setQrCodeUrl(picUrl);
            //插入item_qrcode_share表
            ItemQrcodeShareDO shareDO = new ItemQrcodeShareDO();
            String currentUserNo = AppUtil.getLoginUserId();
            shareDO.setCompanyNo(AppUtil.getLoginUserCompanyNo());

            shareDO.setShareNo(uuid);
            shareDO.setCreator(currentUserNo);
            shareDO.setModifier(currentUserNo);
            shareDO.setItemCode(newItem.getItemCode());
            shareDO.setPicUrl(picUrl);
//            shareDO.setUserNo(currentUserNo);
            qrcodeShareDOMapperExt.insertSelective(shareDO);
        }

    }


}
