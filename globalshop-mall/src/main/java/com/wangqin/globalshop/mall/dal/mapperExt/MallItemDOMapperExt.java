package com.wangqin.globalshop.mall.dal.mapperExt;

import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ItemQueryVO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ItemDOMapper;
import com.wangqin.globalshop.biz1.app.bean.dataVo.JsonPageResult;

public interface MallItemDOMapperExt extends ItemDOMapper {

    List<ItemDO> selectByCategotegoryAndNotInList(Long categoryId, Integer limit, List<Long> ids);

    List<ItemDO> selectByNotInCategotegory(Long categoryId, int i);

    ItemDO selectByNew();

    ItemDO selectByCode(String itemCode);

    List<ItemDO> queryHotItems(Integer pageIndex);

    JsonPageResult<List<ItemDO>> queryItems(ItemQueryVO itemQueryVO);

}
