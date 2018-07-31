package com.wangqin.globalshop.schedule.service.commision;

import com.wangqin.globalshop.schedule.dto.CommisionModel;

public interface IStrategy {

    void handle(CommisionModel model);

}
