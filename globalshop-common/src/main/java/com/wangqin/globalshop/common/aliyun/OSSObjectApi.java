package com.wangqin.globalshop.common.aliyun;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.ObjectMetadata;

public class OSSObjectApi {

    protected static Logger     ossLog       = LoggerFactory.getLogger("ossUpload");

    private static final String ACCESS_ID    = "BfsPR1lUTdLPvb9h";
    private static final String ACCESS_KEY   = "fKEQxDugvcucQq4aK0CUka9bia2oaR";
    private static final String OSS_ENDPOINT = "http://oss-cn-hangzhou.aliyuncs.com";
    private static final String BUCKET_NAME  = "haihuimage";

    // 上传文件
    public static boolean uploadFile(InputStream inputStream, Long size, String contentType, String fileUrl) {
        // 使用默认的OSS服务器地址创建OSSClient对象。
        OSSClient client = new OSSClient(OSS_ENDPOINT, ACCESS_ID, ACCESS_KEY);

        ObjectMetadata objectMeta = new ObjectMetadata();
        objectMeta.setContentLength(size);
        // 可以在metadata中标记文件类型
        objectMeta.setContentType(contentType);

        try {
            client.putObject(BUCKET_NAME, fileUrl, inputStream, objectMeta);

        } catch (OSSException e) {
            ossLog.error("oss upload file fail!", e);
            return false;
        } catch (ClientException e) {
            ossLog.error("oss upload file fail!", e);
            return false;
        } catch (Exception e) {
            ossLog.error("oss upload file fail!", e);
            return false;
        }

        return true;
    }
}