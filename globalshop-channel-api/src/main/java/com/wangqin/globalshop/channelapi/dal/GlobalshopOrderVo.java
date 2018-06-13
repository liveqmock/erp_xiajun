package com.wangqin.globalshop.channelapi.dal;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;

import java.util.List;

/**
 * Create by 777 on 2018/6/12
 */
public class GlobalshopOrderVo {

	private MallOrderDO mallOrderDO;


	private List<MallSubOrderDO> mallSubOrderDOS;

	public MallOrderDO getMallOrderDO() {
		return mallOrderDO;
	}
	public void setMallOrderDO(MallOrderDO mallOrderDO) {
		this.mallOrderDO = mallOrderDO;
	}
	public List<MallSubOrderDO> getMallSubOrderDOS() {
		return mallSubOrderDOS;
	}
	public void setMallSubOrderDOS(List<MallSubOrderDO> mallSubOrderDOS) {
		this.mallSubOrderDOS = mallSubOrderDOS;
	}
}
