package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerTaskDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.BuyerTaskDOMapper;
import com.wangqin.globalshop.biz1.app.bean.dataVo.BuyerTaskVO;

import java.util.List;

public interface BuyerTaskDOMapperExt extends BuyerTaskDOMapper {

	Long gainTASKSequence();

    List<BuyerTaskVO> list(BuyerTaskVO buyerTask);

    void insertBatch(List<BuyerTaskDO> taskList);

    BuyerTaskDO selectVoById(Long id);

}
