package com.wangqin.globalshop.common.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 一个用来转换类型的帮助类
 */
public class EasyUtil {

    /**
     * logger
     */
    private static final Log logger = LogFactory.getLog(EasyUtil.class);

    public static final Long parseLongThrowExp(String s) {
        if (isStringEmpty(s)) {
            return 0L;
        }

        Long rlt = 0L;
        try {
            rlt = Long.parseLong(s);
        } catch (Exception e) {
            logger.error(e);
        }
        return rlt;
    }

    public static final Long parseLongWithoutExp(String s) {
        if (isStringEmpty(s)) {
            return 0L;
        }

        Long rlt = 0L;
        try {
            rlt = Long.parseLong(s);
        } catch (Exception e) {
            logger.error(e);
        }
        return rlt;
    }

    public static final Double parseDoubleThrowExp(String s) {
        if (isStringEmpty(s)) {
            return 0D;
        }

        Double rlt = 0D;
        try {
            rlt = Double.parseDouble(s);
        } catch (Exception e) {
            logger.error(e);
        }
        return rlt;
    }

    public static final Double parseDoubleWithoutExp(String s) {
        if (isStringEmpty(s)) {
            return 0D;
        }

        Double rlt = 0D;
        try {
            rlt = Double.parseDouble(s);
        } catch (Exception e) {
            logger.error(e);
        }
        return rlt;
    }

    public static final Date parseDateThrowExp(DateFormat dateFormat, String s) {
        if (isStringEmpty(s)) {
            return new Date();
        }

        Date rlt = new Date();
        try {
            rlt = dateFormat.parse(s);
        } catch (Exception e) {
            logger.error(e);
        }
        return rlt;
    }

    public static final Date parseDateWithoutExp(DateFormat dateFormat, String s) {
        if (isStringEmpty(s)) {
            return new Date();
        }

        Date rlt = new Date();
        try {
            rlt = dateFormat.parse(s);
        } catch (Exception e) {
            logger.error(e);
        }
        return rlt;
    }

    public static String formatTime2yyyyMMddHH24mmss(Date time) {
        return formatTime2Str(time, "yyyyMMdd HH:mm:ss");
    }

    public static String formatTime2yyyyMMddStr(Date time) {
        return formatTime2Str(time, "yyyyMMdd");
    }

    public static String formatTime2yyMddStr(Date time) {
        Calendar now = Calendar.getInstance();
        now.setTime(time);
        String yy = now.get(Calendar.YEAR) % 100 + "";
        String m = Integer.toHexString(now.get(Calendar.MONTH) + 1).toUpperCase();
        String dd = now.get(Calendar.DATE) + "";
        if (dd.length() == 1) {
            dd = "0" + dd;
        }
        return (yy + m + dd);
    }

    public static String formatTime2Str(Date time, String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        if (time != null) {
            try {
                return df.format(time);
            } catch (Exception e) {
                return time.toString();
            }
        }
        return "";
    }

    // public static final void commonCopy(Object src, Object dest) {
    // PropertyHelper.copyProperties(src, dest);
    // }

    public static final Boolean isListEmpty(Collection<?> l) {
        if (l == null || l.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static final Boolean isStringEmpty(String s) {
        if (s == null || s.trim().length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static final Boolean isStringEquals(String a, String b) {
        if (a == null && b == null) {
            return true;
        } else if (a != null) {
            return a.equals(b);
        } else {
            return false;
        }
    }

    public static final Boolean isStringEqualsIgnoreCase(String a, Object b) {
        if (a == null && b == null) {
            return true;
        } else if (a != null) {
            return upperString(a).equals(upperString(b.toString()));
        } else {
            return false;
        }
    }

    public static final int getStringLength(String s) {
        return (s == null ? 0 : s.length());
    }

    /**
     * 计算百分比，保留两位小数，以字符串形式返回。
     * 
     * @param mole 分子
     * @param deno 分母
     * @return
     */
    public static final String calculatePercent(Double mole, Double deno) {
        if (deno == null || deno == 0) return "0.00%";

        double result = (mole / deno) * 100;

        java.text.DecimalFormat df = new java.text.DecimalFormat("#0.00");
        return df.format(result) + "%";

    }

    public static final String calculatePercent(Integer mole, Integer deno) {
        if (deno == null || deno == 0) return "0.00%";

        double result = (mole * 1.0 / deno) * 100;

        java.text.DecimalFormat df = new java.text.DecimalFormat("#0.00");
        return df.format(result) + "%";

    }

    public static final Date parseDate(String dateStr) {
        if (dateStr != null && !"".equals(dateStr.trim())) {
            try {
                return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(dateStr);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    /**
     * 加list中元素按“，”分隔连成一个字符串
     * 
     * @param list
     * @return
     */
    public static final String list2Str(List<?> list) {
        return list2Str(list, ',');
    }

    public static final String list2Str(List<?> list, char tag) {
        StringBuffer sb = new StringBuffer();
        if (!isListEmpty(list)) {
            for (Object item : list) {
                if (sb.length() > 0) sb.append(tag);
                sb.append(item);
            }
        }
        return sb.toString();
    }

    /**
     * 将对象转换成Double。用于SQL取出的数据类型字段处理，因为double 单元测试环境下取出的是BigDecimal，jobss下取出的是Double。 统一转为String再转回Double。
     * 
     * @param value
     * @return
     */
    public static final Double convertObject2Double(Object value) {
        Double rlt = 0D;
        try {
            rlt = Double.valueOf(value == null ? "0" : value.toString());
        } catch (Exception e) {
            logger.error(e);
        }
        return rlt;
    }

    /**
     * 用于导入，转换时进行报错
     * 
     * @param value
     * @return
     */
    public static final Double convertText2Double(Object value) {
        Double rlt = 0D;
        try {
            rlt = Double.valueOf(value == null ? "0" : value.toString());
        } catch (Exception e) {
            logger.error(e);
            throw e;
        }
        return rlt;
    }

    /**
     * 用于导入，转换时进行报错
     * 
     * @param value
     * @return
     */
    public static final Long convertText2Long(Object value) {
        Long rlt = 0L;
        try {
            rlt = Long.valueOf(value == null ? "0" : value.toString());
        } catch (Exception e) {
            logger.error(e);
            throw e;
        }
        return rlt;
    }

    public static final Long convertObject2Long(Object value) {
        Long rlt = 0L;
        try {
            rlt = Long.valueOf(value == null ? "0" : value.toString());
        } catch (Exception e) {
            logger.error(e);
        }
        return rlt;
    }

    public static final Integer convertObject2Integer(Object value) {
        Integer rlt = 0;
        try {
            rlt = Integer.valueOf(value == null ? "0" : value.toString());
        } catch (Exception e) {
            logger.error(e);
        }
        return rlt;
    }

    public static final List<Long> convertObjectList2LongList(List<Object> valueList) {
        List<Long> rltList = new ArrayList<Long>();
        if (!isListEmpty(valueList)) {
            for (Object obj : valueList) {
                Long tmp = convertObject2Long(obj);
                rltList.add(tmp);
            }
        }
        return rltList;
    }

    public static final List<Double> convertObjectList2DoubleList(List<Object> valueList) {
        List<Double> rltList = new ArrayList<Double>();
        if (!isListEmpty(valueList)) {
            for (Object obj : valueList) {
                Double tmp = convertObject2Double(obj);
                rltList.add(tmp);
            }
        }
        return rltList;
    }

    /**
     * 去除一个list中的重复元素
     * 
     * @param list
     * @return
     */
    public static <T> List<T> removeDuplicatedItems(List<T> list) {
        Set<T> set = new HashSet<T>();
        if (!isListEmpty(list)) {
            set.addAll(list);

            list.clear();
            list.addAll(set);
        }
        return list;

    }

    /**
     * 将一个list按照一个限制拆分成若干个小的list
     * 
     * @param <T>
     * @param list
     * @param sizeLimit
     * @return
     */
    public static final <T> List<List<T>> splitList(List<T> list, int sizeLimit) {
        List<List<T>> rlt = new ArrayList<List<T>>();

        if (list == null || list.size() <= sizeLimit) {
            rlt.add(list);
            return rlt;
        }

        for (int i = 0; i < list.size(); i += sizeLimit) {
            rlt.add(list.subList(i, i + sizeLimit > list.size() ? list.size() : (i + sizeLimit)));
        }

        return rlt;
    }

    /**
     * String的小写转大写
     * 
     * @param s
     * @return
     */
    public static final String upperString(String s) {
        if (s == null) {
            return null;
        } else {
            return s.trim().toUpperCase();
        }
    }

    /**
     * String的小写转大写，再拼上“%%”，一般用在like查询
     * 
     * @param s
     * @return
     */
    public static final String upperStringLike(String s) {
        if (s == null) {
            return null;
        } else {
            return "%" + s.trim().toUpperCase() + "%";
        }
    }

    /***
     * concat "%%" with string
     */
    public static final String stringLike(String s) {
        if (s == null) {
            return null;
        } else {
            return "%" + s.trim() + "%";
        }
    }

    /**
     * 将这个string的list转成大写
     * 
     * @param l
     * @return
     */
    public static final List<String> upperStringList(List<String> l) {
        if (isListEmpty(l) == true) {
            return l;
        }

        for (int i = 0; i < l.size(); i++) {
            l.set(i, upperString(l.get(i)));
        }
        return l;
    }

    /**
     * 将这个string的list转成大写加%
     * 
     * @param l
     * @return
     */
    public static final List<String> upperStringListLike(List<String> l) {
        if (isListEmpty(l) == true) {
            return l;
        }

        for (int i = 0; i < l.size(); i++) {
            l.set(i, upperStringLike(l.get(i)));
        }
        return l;
    }

    /**
     * 看一下这个list里面有没有重复的元素
     * 
     * @return
     */
    public static final boolean isListElementSame(List<?> l) {
        if (isListEmpty(l)) {
            return true;
        }
        Set<?> s = new HashSet<Object>(l);
        return !(s.size() == l.size());
    }

    /**
     * 判断2个object是否一致
     * 
     * @param a
     * @param b
     * @return
     */
    public static final boolean isObjEqual(Object a, Object b) {
        if (a == null) {
            if (b == null) {
                return true;
            } else {
                return false;
            }
        } else {
            return a.equals(b);
        }
    }

    /**
     * 将这个string的list转成大写 要是l 为空，就会报指针异常。在隐式调用的时候就很难看出错误， 所以不要用该方法了。
     * 
     * @param l
     * @return
     */
    public static final List<String> upperList(List<String> l) {
        if (l == null) {
            return l;
        }

        for (int i = 0; i < l.size(); i++) {
            l.set(i, l.get(i).toUpperCase());
        }
        return l;
    }

    public static final String trimString(String s) {
        if (s == null) {
            return "";
        }
        return s.trim();
    }

    /**
     * 一些通用的打印log的函数
     * 
     * @param theirLogger
     * @param whId
     * @param operateName
     * @param methodName
     * @param beginTime
     */
    public static void writeKeyLog(Log theirLogger, Long whId, String operateName, String methodName, Long beginTime) {
        if (whId == null) {
            whId = 0L;
        }
        if (operateName == null) {
            operateName = "";
        }
        theirLogger.info("***********OPERATE:" + operateName + " WAREHOUSE:" + whId + " METHOD:" + methodName
                         + " EXCUTE:" + (System.currentTimeMillis() - beginTime) + "(ms)***********");
    }

    /**
     * 判断字符串中是否只含有一个"."，且不在开头与结尾。
     * 
     * @param str
     * @return true: 如果只有一个点号 or false;
     */
    public static boolean containsOneDot(String str) {
        Pattern pattern = Pattern.compile("^[^\\.]+[\\.]{1}[^\\.]+$");// 正则匹配
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static String getCurrentTreadId() {
        return "thread: " + Thread.currentThread().getId() + " ";
    }

    /**
     * 校验两个Long类型的list里面的元素是不是完全一模一样
     * 
     * @param aList
     * @param bList
     * @return
     */
    public static boolean checkListEqual(List<Long> aList, List<Long> bList) {

        if (isListEmpty(aList) == true && isListEmpty(bList) == true) {
            return true;
        } else if (isListEmpty(aList) == true && isListEmpty(bList) == false) {
            return false;
        } else if (isListEmpty(aList) == false && isListEmpty(bList) == true) {
            return false;
        } else {
            if (aList.size() != bList.size()) {
                return false;
            }

            L1: for (Long a : aList) {
                for (Long b : bList) {
                    if (a.equals(b)) {
                        continue L1;
                    }
                }
                return false;
            }

            L2: for (Long b : bList) {
                for (Long a : aList) {
                    if (b.equals(a)) {
                        continue L2;
                    }
                }
                return false;
            }

            return true;
        }

    }

    /**
     * 首字母大写，in:deleteDate，out:DeleteDate
     * 
     * @param in
     * @return
     */
    public static String upperHeadChar(String in) {
        String head = in.substring(0, 1);
        String out = head.toUpperCase() + in.substring(1, in.length());
        return out;
    }

    public static String getIpAddress() {
        String os = System.getProperties().getProperty("os.name");
        if (os.contains("Windows")) {
            return "localhost";
        } else {
            Enumeration<NetworkInterface> netInterfaces = null;
            try {
                netInterfaces = NetworkInterface.getNetworkInterfaces();
                while (netInterfaces.hasMoreElements()) {
                    NetworkInterface ni = netInterfaces.nextElement();
                    if ("eth0".equals(ni.getName())) {
                        Enumeration<InetAddress> ips = ni.getInetAddresses();
                        while (ips.hasMoreElements()) {
                            return ips.nextElement().getHostAddress();
                        }
                    }
                }
            } catch (Exception e) {
                logger.error("", e);
            }
        }

        return null;
    }

    /**
     * 设置date的时间为当天的开始时间, 即00:00:00
     * 
     * @param date
     * @return
     */
    public static Date setTimeToTheStartOfTheDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 截断字符串，使得 str.length()<= fitSize
     * 
     * @param str :
     * @param maxSize
     * @return str
     */
    public static String truncateLEFitSize(String str, int fitSize) {
        if (!EasyUtil.isStringEmpty(str)) {
            str = str.replaceAll("\r|\n", "");
        }
        if (str == null || str.length() <= fitSize || fitSize <= 0) {
            return str;
        } else if (fitSize < 3) {
            return str.substring(0, fitSize);
        }

        String tmp = str.substring(0, fitSize - 3);
        tmp += "...";
        return tmp;
    }

    /**
     * 字符串str添加某个没有包含字符串subStr 1.判断str字符串是否包含了subStr 2.如果没有包含，进行连接
     */
    public static String concatNoContainsStr(String str, String subStr) {
        if (str == null) {
            str = subStr;
            return str;
        }
        if (str.indexOf(subStr) < 0) {
            str = str.concat(subStr);
        }
        return str;
    }

    /**
     * 过滤未知字符
     * 
     * @param str
     * @return
     */
    public static String clearUnknownChar(String str) {
        if (EasyUtil.isStringEmpty(str)) {
            return str;
        }
        /**
         * D800-DFFF 在utf-8中是保留字段。
         */
        String s = "\uD800\uDFFF";
        char c1 = s.charAt(0);
        char c2 = s.charAt(1);
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            if ((c1 < str.charAt(i) && str.charAt(i) < c2) || '\n' == str.charAt(i) || '\t' == str.charAt(i)
                || '\r' == str.charAt(i) || '\b' == str.charAt(i) || '\f' == str.charAt(i)) {
                // System.out.println(str.charAt(i));
            } else {
                buffer.append(str.charAt(i));
            }
        }
        return buffer.toString();
    }

}
