package com.wangqin.shopkeeper.common.utils;

import net.sf.cglib.beans.BeanCopier;

/**
 * @author pw
 * @date 2016-6-28
 */
public class BeanUtils {

    /**
     * @param from
     * @param to
     * @return 目标 对象
     */
    public static <T> T copy(final Object from, final T to) {
        final BeanCopier copier = BeanCopier.create(from.getClass(), to.getClass(), false);
        copier.copy(from, to, null);
        return to;
    }
}
