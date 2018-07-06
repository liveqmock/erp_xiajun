package com.wangqin.globalshop.item.app.service.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.wangqin.globalshop.item.app.service.IUploadFileService;
import org.springframework.stereotype.Service;

import java.io.*;

@Service("uploadFileService")
public class UploadFileServiceImpl implements IUploadFileService {

    // endpoint以杭州为例，其它region请按实际情况填写
    String            endpoint        = "http://oss-cn-hangzhou.aliyuncs.com";
    // accessKey请登录https://ak-console.aliyun.com/#/查看
    /*
     * String accessKeyId = "LTAISRg0cXghto8b"; String accessKeySecret = "tRqkBoUk6H8mfY38movbVIyWHyv37f"; String
     * bucketName = "haierp";
     */
    String            accessKeyId     = "BfsPR1lUTdLPvb9h";
    String            accessKeySecret = "fKEQxDugvcucQq4aK0CUka9bia2oaR";
    String            bucketName      = "haihuimage";
    private OSSClient ossClient       = new OSSClient(accessKeyId, accessKeySecret);

    // private String urlhead = "http://" + bucketName + ".oss-cn-hangzhou.aliyuncs.com/";
    private String    urlhead         = "http://img.haihu.com/";

    @Override
    public String uploadImg(InputStream inputStream, String picKey) {
        ObjectMetadata objectMeta = new ObjectMetadata();
        // objectMeta.setContentLength(inputStream.);
        objectMeta.setContentType("image/jpeg");
        ossClient.putObject(bucketName, picKey, inputStream, objectMeta);
        return urlhead + picKey + "?x-oss-process=image";
    }

    @Override
    public OSSObject getOSSObject(String picKey) {
        OSSObject object = null;
        try {
            object = ossClient.getObject(bucketName, picKey);
        } catch (Exception e) {
            object = ossClient.getObject("haihu-card", picKey);
        }
        return object;
    }

    public static void main(String[] args) {
        UploadFileServiceImpl impl = new UploadFileServiceImpl();
        File imgFile = new File("/Users/zhubowen/Desktop/ffff.jpg");
        InputStream in = null;
        try {
            in = new FileInputStream(imgFile);
            String img = impl.uploadImg(in, "adfkadfijiaj2.jpg");
            System.out.println("img:" + img);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
