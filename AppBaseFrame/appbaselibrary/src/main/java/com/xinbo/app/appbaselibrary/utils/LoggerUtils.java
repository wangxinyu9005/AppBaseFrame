package com.xinbo.app.appbaselibrary.utils;


import com.orhanobut.logger.Logger;

/**
 * @author LC
 * @date 2019/4/10 0010
 * @Describe
 */
public class LoggerUtils {


    public static void d(String message) {
        Logger.d(message);
    }

    public static void i(String message) {
        Logger.i(message);
    }

    public static void w(String message) {
        Logger.w(message);
    }

    public static void e(String message) {
        Logger.e(message);
    }

    public static void json(String json) {
        Logger.json(json);
    }
}
