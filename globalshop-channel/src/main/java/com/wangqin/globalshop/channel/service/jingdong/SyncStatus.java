package com.wangqin.globalshop.channel.service.jingdong;

/**
 * 同步至京东的状态
 * Create by 777 on 2018/6/12
 */
public class SyncStatus {

	public  final static String REQUEST = "REQUEST"; //等待下发

	public final static String FAILURE = "FAILURE"; //下发失败

	public final static String STOP = "STOP";//停止下发

	public final static String SUCCESS = "SUCCESS";//停止下发
}
