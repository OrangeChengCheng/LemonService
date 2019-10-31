package org.cheng.lemon.unit;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tools {

    //打印
    public static void Log (String string) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println("【 " + df.format(new Date()) + " 】->  " + string);
    }

    public static String getCurrentTimeFormat() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String currentTimeFormat = df.format(new Date());
        return currentTimeFormat;
    }

}
