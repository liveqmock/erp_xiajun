package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerTaskDetailDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.BuyerTaskDetailDOMapper;
import com.wangqin.globalshop.biz1.app.bean.dataVo.BuyerTaskExportDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BuyerTaskDetailDOMapperExt extends BuyerTaskDetailDOMapper {
    void inserBatch(List<BuyerTaskDetailDO> detailList);

    void updateTaskDetailDailyStatus(@Param("status") Integer status, @Param("buyerTaskNo") String buyerTaskNo);

    List<BuyerTaskExportDTO> taskDailyExportByTaskNo(@Param("buyerTaskNo") String buyerTaskNo);

    List<BuyerTaskDetailDO> taskDailyByTaskNo(@Param("buyerTaskNo") String buyerTaskNo);

	void deleteByTaskNo(@Param("buyerTaskNo") String buyerTaskNo, @Param("companyNo") String companyNo);
}
