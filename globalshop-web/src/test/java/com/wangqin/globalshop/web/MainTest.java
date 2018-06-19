package com.wangqin.globalshop.web;

import java.awt.*;
import java.util.Date;

public class MainTest {

    public static void main(String[] args) throws Exception {

        Robot robot = new Robot();
        int x = 100;
        while (true) {
            System.out.println("========" + new Date());
            x = -x;
            robot.mouseMove(200 + x, 200 + x);
            Thread.sleep(2 * 60 * 1000);
        }

    }


}
