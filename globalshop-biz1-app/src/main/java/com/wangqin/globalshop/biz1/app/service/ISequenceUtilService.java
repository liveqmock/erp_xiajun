package com.wangqin.globalshop.biz1.app.service;

public interface ISequenceUtilService {

	Long gainSkuSequence();
	Long gainItemSequence();
	/**
	 * 入库单号
	 * @return
	 */
	Long gainPOSequence();
	/**
	 * 子订单号
	 * @return
	 */
	Long gainERPOSequence();
	/**
	 * 小票号
	 * @return
	 */
	Long gainRECOSequence();
	
	/**
	 * order no
	 * @return
	 */
	Long gainORDSequence();
	 
	Long gainPKGSequence();
	
	Long gainTASKSequence();
	Long gainINVOUTSequence();
}
