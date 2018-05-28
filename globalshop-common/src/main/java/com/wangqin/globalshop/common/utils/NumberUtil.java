package com.wangqin.globalshop.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberUtil {
	
	public static double formatDouble2(double d) {
        // 新方法，如果不需要四舍五入，可以使用RoundingMode.DOWN
        BigDecimal bg = new BigDecimal(d).setScale(2, RoundingMode.DOWN);
        return bg.doubleValue();
    }
}
