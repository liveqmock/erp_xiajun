package com.wangqin.globalshop.common.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author jieyingxiong
 */
public class ShellCmdUtil {
    public static String exec(String args) {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process=runtime.exec(args);
            int waitFor = process.waitFor();
            InputStream in = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(in,"gbk");
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while((line =br.readLine())!=null) {
                return line;
            }
            isr.close();
        } catch (Exception e) {
//            System.out.println("Error!");
            e.printStackTrace();
        }
        return "";
    }

    public static String genRand(int length) {
        return exec("openssl rand -hex "+length/2);
    }
    public static void main(String[] args) {

        System.out.println(genRand(4));
    }
}