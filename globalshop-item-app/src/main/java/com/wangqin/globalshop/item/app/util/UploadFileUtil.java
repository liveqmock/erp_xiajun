package com.wangqin.globalshop.item.app.util;

import java.io.InputStream;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;

public class UploadFileUtil {
	
    String            endpoint        = "http://oss-cn-hangzhou.aliyuncs.com";
    // accessKey请登录https://ak-console.aliyun.com/#/查看
    static String            accessKeyId     = "BfsPR1lUTdLPvb9h";
    static String            accessKeySecret = "fKEQxDugvcucQq4aK0CUka9bia2oaR";
    static String            bucketName      = "haihuimage";   
    private static String    urlhead         = "http://img.haihu.com/"; 
    
	public static String uploadImg(InputStream inputStream, String picKey) {
		OSSClient ossClient       = new OSSClient(accessKeyId, accessKeySecret);
        ObjectMetadata objectMeta = new ObjectMetadata();
        objectMeta.setContentType("image/jpeg");
        ossClient.putObject(bucketName, picKey, inputStream, objectMeta);
        return urlhead + picKey;
    }

}
