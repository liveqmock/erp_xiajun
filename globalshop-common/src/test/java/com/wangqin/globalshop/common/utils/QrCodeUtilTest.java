package com.wangqin.globalshop.common.utils;


import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class QrCodeUtilTest {

    @Test
    public void generateImageTest() throws IOException, WriterException {
        QrCodeUtil.generateImage("www.buyer007.com", 60, 60, "simpleqrcode.png");
    }

    @Test
    public void encodeImageTest() throws IOException, WriterException {
        QrCodeUtil.encodeImage("www.buyer007.com", 60, 60, "noramlqrcode.png");
    }

//    @Test
//    public void generateIcoImage() throws IOException, WriterException {
//        QrCodeUtil.generateIcoImage("test1.jpeg","www.buyer007.com", 120, 120, "qricocode.png");
//    }
//
//    @Test
//    public void adjustSizeTest() throws IOException {
//        BufferedImage bufferedImages = QrCodeUtil.adjustSize(120, 120, "test1.jpeg");
//
//        ImageIO.write(bufferedImages, "png", new File("logo.png"));
//    }
//
//    @Test
//    public void readImageTest() throws IOException, ChecksumException, NotFoundException, FormatException {
//        String content = QrCodeUtil.readImage("qrcode.png");
//
//        System.out.println(content);
//    }
}
