package com.xinbo.app.appbaselibrary.utils;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;

/**
* @description  参考：https://mp.weixin.qq.com/s/d9QCoBP6kV9VSWvVldVVwA
屏幕单向适配类，水平适配或者纵向适配，双向适配是不可能的。
比如横向用该适配类适配后，纵向布局要用权重或者wrap_content进行适配。
如果纵向使用该适配类适配，那么横向布局要用权重或者wrap_content适配。
* @date 2019年04月09日14:10:24
* @author wxy
*/
public class DensityAdapterUtils {

    private static float appDensity;
    private static float appScaledDensity;
    private static DisplayMetrics appDisplayMetrics;
    private static int barHeight;
    private static int navigationHeight;

    public static void setDensity(@NonNull final Application application) {
        //获取application的DisplayMetrics
        appDisplayMetrics = application.getResources().getDisplayMetrics();
        //获取状态栏高度
        barHeight = AppUtils.getStatusBarHeight(application);
        //获取导航栏高度
        navigationHeight = AppUtils.getNavigationHeight(application);

        if (appDensity == 0) {
            //初始化的时候赋值
            appDensity = appDisplayMetrics.density;
            appScaledDensity = SharedPreferencesUtils.getAppFontScale(application, 1.0f) * appDisplayMetrics.scaledDensity;
            // 保存初始化获得的appDensity
            SharedPreferencesUtils.setApplicationAppDensity(application, appDensity);
            //添加系统更改字体大小变化的监听
//            application.registerComponentCallbacks(new ComponentCallbacks() {
//                @Override
//                public void onConfigurationChanged(Configuration newConfig) {
//                    //字体改变后,将appScaledDensity重新赋值
//                    if (newConfig != null && newConfig.fontScale > 0) {
//                        float saveScaleDensity = SharedPreferencesUtils.getAppFontScale(application, appDisplayMetrics.scaledDensity);
//                        appScaledDensity = application.getResources().getDisplayMetrics().scaledDensity * saveScaleDensity;
//                        LogUtils.e(MyConstants.LOG_TAG,"字体变化的监听==========" + appScaledDensity);
//                    }
//                }
//
//                @Override
//                public void onLowMemory() {
//                }
//            });
        }

    }

    //此方法在BaseActivity中做初始化(如果不封装BaseActivity的话,直接用下面那个方法就好)
    public static void setDefault(Context context) {
        float savedAppDensity = SharedPreferencesUtils.getApplicationAppDensity(context, 1.0f);
        appScaledDensity = SharedPreferencesUtils.getAppFontScale(context, 1.0f) * savedAppDensity;
        if (AppUtils.isPortrait()) {
            setAppOrientation(context, AppUtils.HEIGHT);
        } else {
            setAppOrientation(context, AppUtils.WIDTH);
        }
    }

    //此方法用于在某一个Activity里面更改适配的方向
    public static void setOrientation(Context context, String orientation) {
        setAppOrientation(context, orientation);
    }

    /**
     * targetDensity
     * targetScaledDensity
     * targetDensityDpi
     * 这三个参数是统一修改过后的值
     * <p>
     * orientation:方向值,传入width或height
     */
    private static void setAppOrientation(@Nullable Context context, String orientation) {
        float targetDensity;
        if (orientation.equals(AppUtils.HEIGHT)) {
            // 竖屏的按照水平方向适配
            targetDensity = appDisplayMetrics.widthPixels / 360f;
        } else {
            targetDensity = appDisplayMetrics.widthPixels / 640f;
        }

        float targetScaledDensity = targetDensity * (appScaledDensity / appDensity);


        int targetDensityDpi = (int) (160 * targetDensity);
        /**
         *
         * 最后在这里将修改过后的值赋给系统参数
         *
         * 只修改Activity的density值
         */
        DisplayMetrics activityDisplayMetrics = context.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.scaledDensity = targetScaledDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;
    }
}
