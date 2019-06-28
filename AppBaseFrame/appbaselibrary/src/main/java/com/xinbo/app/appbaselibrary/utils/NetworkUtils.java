package com.xinbo.app.appbaselibrary.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.RequiresPermission;
import android.telephony.TelephonyManager;

import com.xinbo.app.appbaselibrary.network.NetworkType;


/**
 * 网络状态相关工具类
 */
public class NetworkUtils {

    private NetworkUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 获取活动网络信息
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>}</p>
     *
     * @return NetworkInfo
     */
    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    private static NetworkInfo getActiveNetworkInfo(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }

    /**
     * 获取当前网络类型
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>}</p>
     *
     * @return 网络类型
     * <ul>
     * <li>{@link NetworkType#NETWORK_WIFI   } </li>
     * <li>{@link NetworkType#NETWORK_4G     } </li>
     * <li>{@link NetworkType#NETWORK_3G     } </li>
     * <li>{@link NetworkType#NETWORK_2G     } </li>
     * <li>{@link NetworkType#NETWORK_UNKNOWN} </li>
     * <li>{@link NetworkType#NETWORK_NO     } </li>
     * </ul>
     */
    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public static NetworkType getNetworkType(Context context) {
        NetworkType netType = NetworkType.NETWORK_NO;
        NetworkInfo info = getActiveNetworkInfo(context);
        if (info != null && info.isAvailable()) {

            if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                netType = NetworkType.NETWORK_WIFI;
            } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                switch (info.getSubtype()) {

                    case TelephonyManager.NETWORK_TYPE_TD_SCDMA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B:
                    case TelephonyManager.NETWORK_TYPE_EHRPD:
                    case TelephonyManager.NETWORK_TYPE_HSPAP:
                        netType = NetworkType.NETWORK_3G;
                        break;

                    case TelephonyManager.NETWORK_TYPE_LTE:
                    case TelephonyManager.NETWORK_TYPE_IWLAN:
                        netType = NetworkType.NETWORK_4G;
                        break;

                    case TelephonyManager.NETWORK_TYPE_GSM:
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN:
                        netType = NetworkType.NETWORK_2G;
                        break;
                    default:

                        String subtypeName = info.getSubtypeName();
                        if (subtypeName.equalsIgnoreCase("TD-SCDMA")
                                || subtypeName.equalsIgnoreCase("WCDMA")
                                || subtypeName.equalsIgnoreCase("CDMA2000")) {
                            netType = NetworkType.NETWORK_3G;
                        } else {
                            netType = NetworkType.NETWORK_UNKNOWN;
                        }
                        break;
                }
            } else {
                netType = NetworkType.NETWORK_UNKNOWN;
            }
        }
        return netType;
    }
    /**
     * 网络接口是否可用（即网络连接是否可行）和/或连接（即是否存在网络连接，是否可以建立套接字并传递数据）
     *
     * @param context 上下文
     * @return {@code true} 网络可用
     */
    public static boolean isNetConnected(Context context) {
        checkNonNull(context, "context == null");
        NetworkInfo activeInfo = getActiveNetworkInfo(context);
        return (activeInfo != null && activeInfo.isConnected());
    }

    /**
     * 是否移动数据连接
     *
     * @param context 上下文
     * @return {@code true} 移动数据连接
     */
    public static boolean isMobileConnected(Context context) {
        checkNonNull(context, "context == null");
        NetworkInfo activeInfo = getActiveNetworkInfo(context);
        return (activeInfo != null && activeInfo.isConnected() && activeInfo.getType() == ConnectivityManager.TYPE_MOBILE);
    }

    /**
     * 是否2G网络连接
     *
     * @param context 上下文
     * @return {@code true} 2G网络连接
     */
    public static boolean is2GConnected(Context context) {
        checkNonNull(context, "context == null");
        NetworkInfo activeInfo = getActiveNetworkInfo(context);
        if (activeInfo == null || !activeInfo.isConnected()) {
            return false;
        }
        int subtype = activeInfo.getSubtype();
        switch (subtype) {
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_GSM:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return true;
            default:
                return false;
        }
    }

    /**
     * 是否3G网络连接
     *
     * @param context 上下文
     * @return {@code true} 3G网络连接
     */
    public static boolean is3GConnected(Context context) {
        checkNonNull(context, "context == null");
        NetworkInfo activeInfo = getActiveNetworkInfo(context);
        if (activeInfo == null || !activeInfo.isConnected()) {
            return false;
        }
        int subtype = activeInfo.getSubtype();
        switch (subtype) {
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
            case TelephonyManager.NETWORK_TYPE_TD_SCDMA:
                return true;
            default:
                return false;
        }
    }

    /**
     * 是否4G网络连接
     *
     * @param context 上下文
     * @return {@code true} 4G网络连接
     */
    public static boolean is4GConnected(Context context) {
        checkNonNull(context, "context == null");
        NetworkInfo activeInfo = getActiveNetworkInfo(context);
        if (activeInfo == null || !activeInfo.isConnected()) {
            return false;
        }
        int subtype = activeInfo.getSubtype();
        switch (subtype) {
            case TelephonyManager.NETWORK_TYPE_LTE:
            case TelephonyManager.NETWORK_TYPE_IWLAN:
                return true;
            default:
                return false;
        }
    }

    /**
     * 获取移动网络运营商名称
     * <lu>
     * <li>中国联通</li>
     * <li>中国移动</li>
     * <li>中国电信</li>
     * </lu>
     *
     * @param context 上下文
     * @return 移动网络运营商名称
     */
    public static String getNetworkOperatorName(Context context) {
        checkNonNull(context, "context == null");
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getNetworkOperatorName();
    }

    /**
     * 获取移动终端类型
     *
     * @param context 上下文
     * @return 手机制式
     * <ul>
     * <li>{@link TelephonyManager#PHONE_TYPE_NONE } : 0 手机制式未知</li>
     * <li>{@link TelephonyManager#PHONE_TYPE_GSM  } : 1 手机制式为GSM，移动和联通</li>
     * <li>{@link TelephonyManager#PHONE_TYPE_CDMA } : 2 手机制式为CDMA，电信</li>
     * <li>{@link TelephonyManager#PHONE_TYPE_SIP  } : 3</li>
     * </ul>
     */
    public static int getPhoneType(Context context) {
        checkNonNull(context, "context == null");
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getPhoneType();
    }

    /**
     * 判断是否Wifi连接
     *
     * @param context 上下文
     * @return true 如果是wifi连接
     */
    public static boolean isWifiConnected(Context context) {
        checkNonNull(context, "context == null");
        NetworkInfo activeInfo = getActiveNetworkInfo(context);
        return (activeInfo != null && activeInfo.isConnected() && activeInfo.getType() == ConnectivityManager.TYPE_WIFI);
    }

    private static void checkNonNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

}