package com.wangqin.globalshop.common.utils;

import java.math.BigDecimal;

/**
 * 类MoneyUtil.java的实现描述：TODO 金额单位转换工具类
 * 
 * @author sivanblack 2018年5月5日 下午3:52:29
 */
public class MoneyUtil {

    /**
     * 将分为单位的转换为元 （除100）
     * 
     * @param amount
     * @return
     * @throws Exception
     */
    public static String changeFun2Yuan(String amount) throws Exception {
        return new BigDecimal(amount).divide(new BigDecimal(100)).toString();
    }

    /**
     * 将元为单位的转换为分 （乘100）
     * 
     * @param amount
     * @return
     */
    public static Integer changeYuan2Fen(String amount) {
        BigDecimal bigDec = new BigDecimal(amount);
        // 设置小数位数，第一个变量是小数位数，第二个变量是取舍方法(四舍五入)
        bigDec = bigDec.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bigDec.multiply(new BigDecimal(100)).intValue();
    }

}
