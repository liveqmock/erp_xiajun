package com.wangqin.globalshop.common.utils;

/**
 * 整个系统相关的工具类，这个里面一般主要存放一些和系统相关或者全局业务相关的全局使用的工具方法或者暂存的信息
 * 
 * @author Sivan
 */
public class AppUtil {

    // 存储当前登录用户的userId，这个信息只在单次访问线程内有效，每次访问登录鉴权的时候会设置这个值
    private static ThreadLocal<Integer> CURRENT_USER_ID          = new ThreadLocal<Integer>();

    private static ThreadLocal<String>  CURRENT_ACCOUNT_USERNAME = new ThreadLocal<String>();

    /**
     * 取得当前登录者的userId，未登录鉴权请求无法取到
     * 
     * @return
     */
    public static Integer getLoginUserId() {
        return (Integer) CURRENT_USER_ID.get();
    }

    /**
     * 设置当前登录者的userId，在登录成功的时候或者每次访问登录鉴权的时候设置这个值，其他情况不能设置
     * 
     * @param userId
     */
    public static void setLoginUserId(Integer userId) {
        CURRENT_USER_ID.set(userId);
    }

    /**
     * 删除当前登录用户的userId，在请求处理完成之后删除，一般不要随便调用这个方法
     */
    public static void removeLoginUserId() {
        CURRENT_USER_ID.remove();
    }

    /**
     * 取得当前登录者的Username，未登录鉴权请求无法取到
     * 
     * @return
     */
    public static String getLoginAccount() {
        return (String) CURRENT_ACCOUNT_USERNAME.get();
    }

    /**
     * 设置当前登录者的Username，在登录成功的时候或者每次访问登录鉴权的时候设置这个值，其他情况不能设置
     * 
     * @param userId
     */
    public static void setLoginAccount(String account) {
        CURRENT_ACCOUNT_USERNAME.set(account);
    }

    /**
     * 删除当前登录用户的Username，在请求处理完成之后删除，一般不要随便调用这个方法
     */
    public static void removeLoginAccount() {
        CURRENT_ACCOUNT_USERNAME.remove();
    }

}
