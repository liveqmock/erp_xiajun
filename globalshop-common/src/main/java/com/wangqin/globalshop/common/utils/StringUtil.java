package com.wangqin.globalshop.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;

public class StringUtil {

    /**
     * 是否为null
     * 
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * 不为null
     * 
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !StringUtil.isEmpty(str);
    }

    /**
     * 是否为空（包括null）
     * 
     * @param str
     * @return 空true，不空false
     */
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 不为空
     * 
     * @param str
     * @return
     */
    public static boolean isNotBlank(String str) {
        return !StringUtil.isBlank(str);
    }

    /**
     * 字符串去空
     * 
     * @param str
     * @return
     */
    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    /**
     * 字符串去null
     * 
     * @param str
     * @return
     */
    public static String trimToNull(String str) {
        String ts = trim(str);
        return isEmpty(ts) ? null : ts;
    }

    /**
     * 比较字符串
     * 
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equals(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equals(str2);
    }

    /**
     * 字符串是否包含
     * 
     * @param str
     * @param searchStr
     * @return
     */
    public static boolean contains(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return false;
        }
        return str.indexOf(searchStr) >= 0;
    }

    /**
     * 是否是数字
     * 
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        if (sz == 0) {
            return false;
        }
        for (int i = 0; i < sz; i++) {
            if (Character.isDigit(str.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    public static boolean isRequireLength(String str, int first, int last) {
        if (str == null) {
            return false;
        }

        int sz = str.length();
        if (sz >= first && sz <= last) {
            return true;
        }
        return false;
    }

    public static int countBytes(String str) throws UnsupportedEncodingException {
        return str.getBytes("UTF-8").length;
    }

    public static boolean isDouble(String str) {
        if (isBlank(str)) {
            return false;
        }
        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
        return pattern.matcher(str).matches();
    }

    public static String getHall(String str) {
        return String.valueOf(str.charAt(2));
    }

    public static String getRoom(String str) {
        return String.valueOf(str.charAt(0));
    }

    public static String[] getaddress(String address) {
        String[] args = address.split("\\|");
        return args;
    }

    /**
     * 1TB=1024GB 1GB=1024MB 1MB=1024KB 1KB=1024B
     * 
     * @param size(byte)
     * @return
     */
    public static String getHtmlFileSize(Long size) {
        if (size < 1024) {
            return size + "B";
        }
        size = size / 1024;
        if (size < 1024) {
            return size + "KB";
        }
        size = size / 1024;
        if (size < 1024) {
            return size + "MB";
        }
        size = size / 1024;
        if (size < 1024) {
            return size + "GB";
        }
        size = size / 1024;
        return size + "TB";
    }

    public static String trimNull(String value) {
        if (value == null) {
            value = "";
        }
        return value;
    }

    public static String getFullName(Integer nameshow, String firstName, String lastName) {
        if (1 == nameshow) {
            return StringUtil.isEmpty(lastName) ? firstName : lastName + " " + firstName;
        }
        return StringUtil.isEmpty(lastName) ? firstName : firstName + " " + lastName;
    }

    public static boolean startWith(String content, String with) {
        if (isNotBlank(content)) {
            return content.startsWith(with);
        }
        return false;
    }

    /**
     * 转义工具 防止字符串里的标签被转义
     * 
     * @param content
     * @return
     */
    public static String escape(String content) {
        if (isNotBlank(content)) {
            content = content.replace("'", "\"");
        }
        return content;
    }



}
