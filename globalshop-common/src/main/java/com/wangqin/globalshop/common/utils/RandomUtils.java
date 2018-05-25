package com.wangqin.globalshop.common.utils;

/**随机数产生器**/

public class RandomUtils {

	public static String getTimeRandom() {
		Long currentTime = System.currentTimeMillis();
		return currentTime.toString().substring(0,10);
	}
}
