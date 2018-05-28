package com.wangqin.globalshop.common.utils;
import java.util.ResourceBundle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * 读取系统配置信息
 * 
 * @author Sivan
 */

public final class ResourceUtil {
    private final static Log      log = LogFactory.getLog(ResourceUtil.class);
    private static ResourceBundle system;
    static {
        try {
            system = ResourceBundle.getBundle("/config/application");
        } catch (Exception e) {
            System.out.println("application.properties Not Found,");
        }
    }

    /**
     * 
     * @param key
     * @return
     */
    public static String getValue(String key) {
        String msg = null;
        try {
            msg = system.getString(key);
        } catch (Exception e) {
            log.error("Key['" + key + "'] Not Found in systemConfig.properties .");
        }
        return msg;
    }

    public static boolean isProductMode() {
        String msg = null;
        try {
            msg = system.getString("productMode");
            return Boolean.valueOf(msg);
        } catch (Exception e) {
            log.error("Key['productMode'] Not Found or error config in systemConfig.properties .");
        }
        return false;
    }

    public static String getSystemDomain() {
        String msg = null;
        try {
            msg = system.getString("system_domain");
        } catch (Exception e) {
            log.error("Key['system_domain'] Not Found in systemConfig.properties .");
        }
        return msg;
    }

    public static String getChatDomain() {
        String msg = null;
        try {
            msg = system.getString("chat_domain");
        } catch (Exception e) {
            log.error("Key['chat_domain'] Not Found in systemConfig.properties .");
        }
        return msg;
    }

    public static String getAdminSystemDomain() {
        String msg = null;
        try {
            msg = system.getString("admin_system_domain");
        } catch (Exception e) {
            log.error("Key['admin_system_domain'] Not Found in systemConfig.properties .");
        }
        return msg;
    }

    public static String getAppDomain() {
        String msg = null;
        try {
            msg = system.getString("app_domain");
        } catch (Exception e) {
            log.error("Key['app_domain'] Not Found in systemConfig.properties .");
        }
        return msg;
    }

    public static String getMAppDomain() {
        String msg = null;
        try {
            msg = system.getString("m_app_domain");
        } catch (Exception e) {
            log.error("Key['app_domain'] Not Found in systemConfig.properties .");
        }
        return msg;
    }

    public static String getApiDomain() {
        String msg = null;
        try {
            msg = system.getString("api_domain");
        } catch (Exception e) {
            log.error("Key['api_domain'] Not Found in systemConfig.properties .");
        }
        return msg;
    }

    public static String getStyleDomain() {
        String msg = null;
        try {
            msg = system.getString("style_domain");
        } catch (Exception e) {
            log.error("Key['style_domain'] Not Found in systemConfig.properties .");
        }
        return msg;
    }

    public static String getStyleDomain(String msg) {
        try {
            if (StringUtils.isNotBlank(msg)) {
                msg = system.getString("style_domain") + msg + "?" + system.getString("style_version");
            } else {
                msg = system.getString("style_domain");
            }
        } catch (Exception e) {
            log.error("Key['style_domain'] or Key['style_version'] Not Found in systemConfig.properties .");
        }
        return msg;
    }

    public static String getImageDomain() {
        String msg = null;
        try {
            msg = system.getString("image_domain");
        } catch (Exception e) {
            log.error("Key['image_domain'] Not Found in systemConfig.properties .");
        }
        return msg;
    }

    public static String getImageUploadPath() {
        String msg = null;
        try {
            msg = system.getString("image_upload_path");
        } catch (Exception e) {
            log.error("Key['image_upload_path'] Not Found in systemConfig.properties .");
        }
        return msg;
    }

    public static String getTaskKey() {
        String msg = null;
        try {
            msg = system.getString("task_key");
        } catch (Exception e) {
            log.error("Key['image_upload_path'] Not Found in systemConfig.properties .");
        }
        return msg;
    }

    public static String getCertificatePath() {
        String msg = null;
        try {
            msg = system.getString("ios_push_certificate_path");
        } catch (Exception e) {
            log.error("Key['ios_push_certificate_path'] Not Found in systemConfig.properties .");
        }
        return msg;
    }

    public static String getCertificatePassword() {
        String msg = null;
        try {
            msg = system.getString("ios_push_certificate_password");
        } catch (Exception e) {
            log.error("Key['ios_push_certificate_password'] Not Found in systemConfig.properties .");
        }
        return msg;
    }
}
