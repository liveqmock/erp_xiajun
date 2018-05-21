package com.wangqin.shopkeeper.common.utils;

import java.math.BigDecimal;

/**
 * @author pw
 * @date 2016-9-27
 */
public class BigDecimalHelper {
    /**
     * @param yuan
     * @return
     */
    public static long convertYuan2Fen(long yuan) {
        return BigDecimal.valueOf(yuan).multiply(new BigDecimal(100)).longValue();
    }

    /**
     * @param yuan
     * @return
     */
    public static long convertYuan2Fen(BigDecimal yuan) {
        return yuan.multiply(new BigDecimal(100)).longValue();
    }

    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("精度不能小于0");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 除法
     * @param b1
     * @param b2
     * @param scale
     * @return
     */
    public static double div(BigDecimal b1, BigDecimal b2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("精度不能小于0");
        }
        if(b2.compareTo(new BigDecimal(0))==0){
            return 0;
        }
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
