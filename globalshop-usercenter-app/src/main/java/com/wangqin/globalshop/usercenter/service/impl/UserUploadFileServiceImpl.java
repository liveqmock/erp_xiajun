package com.wangqin.globalshop.usercenter.service.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.wangqin.globalshop.usercenter.service.UserUploadFileService;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service("mallUploadFileService")
public class UserUploadFileServiceImpl implements UserUploadFileService {

    // endpoint以杭州为例，其它region请按实际情况填写
    String            endpoint        = "http://oss-cn-hangzhou.aliyuncs.com";
    // accessKey请登录https://ak-console.aliyun.com/#/查看
    // String accessKeyId = "LTAIVI0EwNcDNmUe";
    // String accessKeySecret = "hFBvxmCDoX5XOZALYENHjl4Q7I8Hsl";
    // String bucketName = "iruhua-card";
    // private OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    String            accessKeyId     = "BfsPR1lUTdLPvb9h";
    String            accessKeySecret = "fKEQxDugvcucQq4aK0CUka9bia2oaR";
    String            bucketName      = "haihuimage";
    private OSSClient ossClient       = new OSSClient(accessKeyId, accessKeySecret);

    // private String urlhead = "http://" + bucketName + ".oss-cn-hangzhou.aliyuncs.com/";
    private String    urlhead         = "http://img.haihu.com/";

    @Override
    public String uploadImg(InputStream inputStream, String picKey) {
        ObjectMetadata objectMeta = new ObjectMetadata();
        objectMeta.setContentType("image/jpeg");
        ossClient.putObject(bucketName, picKey, inputStream, objectMeta);
        return urlhead + picKey;
    }

    @Override
    public OSSObject getOSSObject(String picKey) {
        OSSObject object = ossClient.getObject(bucketName, picKey);
        return object;
    }

}
