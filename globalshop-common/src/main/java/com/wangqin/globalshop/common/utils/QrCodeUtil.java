package com.wangqin.globalshop.common.utils;


import com.google.zxing.*;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class QrCodeUtil {

    /**
     * 读取二维码的信息
     *
     * @param filePath
     * @return
     * @throws FormatException
     * @throws ChecksumException
     * @throws NotFoundException
     * @throws IOException
     */
    public static String readImage(String filePath) throws FormatException, ChecksumException, NotFoundException, IOException {

        BufferedImage bufferedImage = ImageIO.read(new File(filePath));

        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);

        Binarizer binarizer = new HybridBinarizer(source);
        BinaryBitmap bitmap = new BinaryBitmap(binarizer);

        QRCodeReader qrCodeReader = new QRCodeReader();

        Result result = qrCodeReader.decode(bitmap);

        return result.getText();

    }

    /**
     * 生成最基本的二维码
     *
     * @param text
     * @param width
     * @param height
     * @param filePath
     * @throws WriterException
     * @throws IOException
     */
    public static void generateImage(String text, int width, int height, String filePath) throws WriterException, IOException {

        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault().getPath(filePath);

        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

    }

    /**
     * 生成带有参数的二维码
     *
     * @param text
     * @param width
     * @param height
     * @param filePath
     * @throws WriterException
     * @throws IOException
     */
    public static void encodeImage(String text, int width, int height, String filePath) throws WriterException, IOException {

        Path path = FileSystems.getDefault().getPath(filePath);

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

        Map<EncodeHintType, Object> map = new HashMap<>();

        map.put(EncodeHintType.CHARACTER_SET, "utf-8"); //设置字符集
        map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M); //设置纠错等级
        map.put(EncodeHintType.MARGIN, 2); //设置边距

        BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, width, height, map);

        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }

    /**
     * 生成带有logo的二维码
     *
     * @param icoPath
     * @param text
     * @param width
     * @param height
     * @param filePath
     * @throws WriterException
     * @throws IOException
     */
    public static void generateIcoImage(String icoPath, String text, int width, int height, String filePath) throws WriterException, IOException {

        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        Map<EncodeHintType, Object> map = new HashMap<>();

        map.put(EncodeHintType.CHARACTER_SET, "utf-8"); //设置字符集
        map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H); //设置纠错等级
        map.put(EncodeHintType.MARGIN, 1); //设置边距

        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, map);

        BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

        int qrHeight = qrImage.getHeight();
        int qrWidth = qrImage.getWidth();

        BufferedImage logoImage = adjustSize(qrHeight, qrWidth, icoPath);

        //初始化新的二维码的生成
        BufferedImage combined = new BufferedImage(qrImage.getHeight(), qrImage.getWidth(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) combined.getGraphics();

        //画新的图片
        g2d.drawImage(qrImage, 0, 0, null);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        g2d.drawImage(logoImage, (int) Math.round((qrWidth - logoImage.getWidth()) / 2), (int) Math.round((qrHeight - logoImage.getHeight()) / 2), null);

        //
        ImageIO.write(combined, "png", new File(filePath));
    }

    public static BufferedImage adjustSize(int qrHeight, int qrWidth, String logoPath) throws IOException {
        //读取LOGO ICO
        File file = new File(logoPath);

        BufferedImage logoImage = ImageIO.read(file);

        //对logo进行缩放 将logo的大小设置为整个图片的1/5
        int icoHeight = qrHeight / 5;
        int icoWidth = qrWidth / 5;

        BufferedImage newLogoImage = new BufferedImage(icoWidth, icoHeight, logoImage.getType());
        Graphics g = newLogoImage.getGraphics();
        g.drawImage(logoImage, 0, 0, icoWidth, icoHeight, null);

        g.dispose();

        return newLogoImage;
    }

}