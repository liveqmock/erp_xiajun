package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.bean.dataVo.ItemQuery2VO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.PageQueryParam;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;
import com.alibaba.dubbo.config.support.Parameter;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ItemQueryVO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ItemDOMapper;
import com.wangqin.globalshop.biz1.app.bean.dto.ItemDTO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.boot.logging.log4j2.Log4J2LoggingSystem;


/**
 * Create by 777 on 2018/5/25
 */
public interface ItemDOMapperExt extends ItemDOMapper {

	String queryQrCodeUrlById(Long id);
	
    //定时任务
    List<ItemDO> queryItemSelfDefineTime(Integer shelfMethod);

    //定时任务
    void updateItemIsSaleAndStatus(@Param("isSale") Byte isSale, @Param("status") Integer status, @Param("itemCode") String itemCode);

    ItemDO queryItemDOByItemCode(String itemCode);

    String queryItemCodeById(Long id);

    //插入单个商品
    Long insertItemSelective(ItemDO item);

    public List<ItemDO> selectBatchIds(List<Long> idList);

    public void updateBatchById(List<ItemDO> itemDOList);

    Integer queryItemsCount(ItemQueryVO itemQueryVO);

    List<ItemDTO> queryItems(ItemQueryVO itemQueryVO);

    ItemDTO queryItemById(Long id);

    /**
     * 2017-04-04, jc
     * query all itemCode and id
     *
     * @return
     */
    List<Map<String, Object>> queryAllItemCodeAndIdHashMap();

    void updateItemNotSale();

    void updateItemSale();

    Integer queryItemsCountByhaihu(ItemQueryVO itemQueryVO);

    List<ItemDO> queryHaihuItems(ItemQueryVO itemQueryVO);

    List<ItemDO> queryHaihuByUptime(ItemQueryVO itemQueryVO);

    Integer sumNewItemNumByDate(Integer days);

    Integer sumNewItemNumByMonth(Integer months);

    ItemDO queryItemByItemCode(String itemCode);


    //根据id更新商品
    void updateByIdSelective(ItemDO item);

    Long queryIdByItemCode(String itemCode);

    //一键分享首页商品
    List<ItemDO> queryItemByStatus(@Param("companyNo") String companyNo, @Param("type") String type,
                                   @Param("start") int start, @Param("pageSize") int pageSize);

    //一键分享搜索商品
    List<ItemDO> queryItemByKeyWord(@Param("keyWord") List<String> keyWord, @Param("companyNo") String companyNo,
                                    @Param("start") int start, @Param("pageSize") int pageSize);

    //一键分享商品详情
    ItemDO itemDetailByItemCode(@Param("itemCode") String itemCode, @Param("companyNo") String companyNo);


    //一键分享获取商品的图片
    String queryItemPicByItemCodeAndCompanyNo(@Param("itemCode") String itemCode, @Param("companyNo") String comanyNo);

    //一键分享，获取商品的图片
    String queryItemPicByItemCode(String itemCode);


    List<ItemDTO> queryMallItems(ItemQueryVO itemQueryVO);

    void insertBatch(List<ItemDO> itemList);

    /**
     * 根据指定条件分页查询商品
     *
     * @param itemQueryVO
     * @param pageQueryParam
     * @return
     */
    List<ItemDTO> listItems(@Param("itemQueryVO") ItemQuery2VO itemQueryVO, @Param("pageQueryParam") PageQueryParam pageQueryParam);

    /**
     * 根据指定条件查询商品数目
     *
     * @param itemQueryVO
     * @return
     */
    int countItems(@Param("itemQueryVO") ItemQuery2VO itemQueryVO);
}
