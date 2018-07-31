package com.wangqin.globalshop.schedule.service.commision;

import com.wangqin.globalshop.common.base.BaseDto;
import com.wangqin.globalshop.common.utils.StringUtil;
import com.wangqin.globalshop.schedule.dto.AgencyModel;
import com.wangqin.globalshop.schedule.dto.CommisionModel;

public class AgencyStrategyService implements IStrategy {
    @Override
    public void handle(CommisionModel model) {

        if (model == null || StringUtil.isNotBlank(model.getModel())){
            return ;
        }
        String agencyModelStr = model.getModel();
        AgencyModel agencyModel = BaseDto.fromJson(agencyModelStr, AgencyModel.class);


    }
}
