package com.xinbo.app.appbaselibrary.base;

import android.app.Application;
import android.content.Context;

/**
* @description
* @date 2019年04月09日14:32:45
* @author wxy
*/
public class BaseLibraryApplication extends Application {

    public static Context getAppContext() {
        return appContext;
    }

    private static Context appContext;

    public static void init(Context context) {
        if (null != context) {
            appContext = context;
        }
    }
}
