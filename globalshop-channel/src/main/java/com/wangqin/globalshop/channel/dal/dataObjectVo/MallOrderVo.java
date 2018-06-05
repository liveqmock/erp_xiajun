package com.wangqin.globalshop.channel.dal.dataObjectVo;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;

import java.util.List;

/**
 * Create by 777 on 2018/5/29
 */
public class MallOrderVo extends MallOrderDO {

	public List<MallSubOrderDO> mallSubOrderDOS;


	public List<MallSubOrderDO> getMallSubOrderDOS() {
		return mallSubOrderDOS;
	}
	public void setMallSubOrderDOS(List<MallSubOrderDO> mallSubOrderDOS) {
		this.mallSubOrderDOS = mallSubOrderDOS;
	}
}
