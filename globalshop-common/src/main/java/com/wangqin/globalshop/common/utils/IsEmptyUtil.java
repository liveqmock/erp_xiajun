package com.wangqin.globalshop.common.utils;

import java.util.Collection;

/**
 * 判断变量是否为空
 * @author xiajun
 *
 */
public class IsEmptyUtil {

	/**
	 * 集合类是否为空
	 * @param c
	 * @return
	 */
	public static final Boolean isCollectionEmpty(Collection<?> c) {
        if (null == c || 0 == c.size()) {
            return true;
        } else {
            return false;
        }
    }
	
	/**
	 * 集合类是否为非空
	 * @param c
	 * @return
	 */
	public static final Boolean isCollectionNotEmpty(Collection<?> c) {
        if (null == c || 0 == c.size()) {
            return false;
        } else {
            return true;
        }
    }
	
	/**
	 * 字符串是否为null或者""
	 * @param s
	 * @return
	 */
	public static final Boolean isStringEmpty(String s) {
        if (null == s || "".equals(s)) {
            return true;
        } else {
            return false;
        }
    }
	
	/**
	 * 字符串是否不是null而且不是""
	 * @param s
	 * @return
	 */
	public static final Boolean isStringNotEmpty(String s) {
        if (null == s || "".equals(s)) {
            return false;
        } else {
            return true;
        }
    }
}
