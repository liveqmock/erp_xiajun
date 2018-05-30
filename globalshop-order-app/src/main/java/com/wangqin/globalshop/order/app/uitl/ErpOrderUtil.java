package com.wangqin.globalshop.order.app.uitl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.common.enums.StockUpStatus;

public class ErpOrderUtil {
	
	/**
	 * 根据订单的备货商品数量来计算备货状态
	 * @param erpOrder
	 * @param realBooked 此订单的现货备货数量
	 * @param transBooked 此订单的在途备货数量
	 */
	public static void calculateStockStatus(MallSubOrderDO erpOrder, Long realBooked, Long transBooked) {
		int stockstatus = -1;// 备货状态
		int qua = erpOrder.getQuantity();
		if (realBooked == 0 && transBooked == 0) {
			stockstatus = StockUpStatus.INIT.getCode();
			erpOrder.setWarehouseNo(null);
		} else {
			if (qua == realBooked) {
				stockstatus = StockUpStatus.STOCKUP.getCode();
			} else if (qua == transBooked) {
				stockstatus = StockUpStatus.TRANS_STOCKUP.getCode();
			} else if (qua == (realBooked + transBooked)) {
				stockstatus = StockUpStatus.MIX_STOCKUP.getCode();
			}
			if (stockstatus == -1) {
				if (realBooked == 0) {
					stockstatus = StockUpStatus.TRANS_PART.getCode();
				} else if (transBooked == 0) {
					stockstatus = StockUpStatus.PART.getCode();

				} else {
					stockstatus = StockUpStatus.PART.getCode();
				}
			}
		}
		erpOrder.setStockStatus((byte) stockstatus);
	}
}
