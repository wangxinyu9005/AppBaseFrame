package com.xinbo.app.appbaselibrary.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.xinbo.app.appbaselibrary.constants.MyConstants;

import java.util.LinkedHashMap;
import java.util.Map;

/**
* @description 公用网络请求工具类，对OkGo的再一次封装
* @date 2019年04月09日14:09:44
* @author wxy
*/
public class AppHttpUtils {

    /**
     * 注意：该方法应用于数字家庭医生！！！！！！！！！！！！！！！！！！！！！！
     * Post请求，以json形式传递给服务器
     *
     * @param context  OkGo需要绑定一个Context
     * @param url      请求地址（域名+端口）
     * @param params   请求参数
     * @param callback 得到服务器返回数据的回调
     */
    public static void postJson(Context context, String url, Map<String, Object> params, Callback<String> callback) {
        //首先判断是否有网络
        boolean isAvailable = isNetWorkConnected(context);
        if (!isAvailable){
            //无网络状态,弹出吐司提示用户没有网络
            try {
                Toast.makeText(context,"网络无连接，请检查网络", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        //有网络状态，则进行正常访问
        Map<String, Object> postParamMap = params;

        postParamMap.put("ip", AppUtils.getIpAddress(context));
        postParamMap.put("timestamp", System.currentTimeMillis());
        postParamMap.put("clientCode", MyConstants.CLIENT_CODE);

        Gson gson = new Gson();
        String json = gson.toJson(postParamMap);

        Map<String, String> finalMap = new LinkedHashMap<>();
        finalMap.put("json", json);
        finalMap.put("signature", SHA256Utils.getHmacSHA256Str(json, MyConstants.CLIENT_SECRET));

        OkGo.<String>post(url)
                .tag(context)
                .upJson(new Gson().toJson(finalMap))
                .execute(callback);

    }

    /**
     * 注意：该方法应用于数字家庭医生！！！！！！！！！！！！！！！！！！！！！！
     * Post请求，以Map形式传递给服务器
     *
     * @param context  OkGo需要绑定一个Context
     * @param url      请求地址（域名+端口）
     * @param params   请求参数
     * @param callback 得到服务器返回数据的回调
     */
    public static void postParams(Context context, String url, Map<String, Object> params, Callback<String> callback) {
        //首先判断是否有网络
        boolean isAvailable = isNetWorkConnected(context);
        if (!isAvailable){
            //无网络状态,弹出吐司提示用户没有网络
            try {
                Toast.makeText(context,"网络无连接，请检查网络", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        //有网络状态，则进行正常访问
        Map<String, Object> postParamMap = params;

        postParamMap.put("ip", AppUtils.getIpAddress(context));
        postParamMap.put("timestamp", System.currentTimeMillis());
        postParamMap.put("clientCode", MyConstants.CLIENT_CODE);

        Gson gson = new Gson();
        String json = gson.toJson(postParamMap);

        Map<String, String> finalMap = new LinkedHashMap<>();
        finalMap.put("json", json);
        finalMap.put("signature", SHA256Utils.getHmacSHA256Str(json, MyConstants.CLIENT_SECRET));

        OkGo.<String>post(url)
                .tag(context)
                .params(finalMap)
                .execute(callback);

    }

    /**
     * 注意：该方法应用于数字家庭医生！！！！！！！！！！！！！！！！！！！！！！
     * get请求
     *
     * @param context  OkGo需要绑定一个Context
     * @param url      请求地址（域名+端口）
     * @param params   请求参数
     * @param callback 得到服务器返回数据的回调
     */
    public static void get(Context context, String url, Map<String, String> params, Callback<String> callback) {
        //首先判断是否有网络
        boolean isAvailable = isNetWorkConnected(context);
        if (!isAvailable){
            //无网络状态,弹出吐司提示用户没有网络
            try {
                Toast.makeText(context,"网络无连接，请检查网络", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        //有网络状态，则进行正常访问
        Map<String, String> getParamMap = params;

        getParamMap.put("ip", AppUtils.getIpAddress(context));
        getParamMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
        getParamMap.put("clientCode", MyConstants.CLIENT_CODE);
        getParamMap.put("signature", SHA256Utils.getHmacSHA256Str(
                AppUtils.joinParamsBySortedKey(getParamMap), MyConstants.CLIENT_SECRET));

        OkGo.<String>get(url)
                .tag(context)
                .params(getParamMap, false)
                .execute(callback);

    }


    /**
     * get请求,快速问医等类似功能模块通用
     *
     * @param context  OkGo需要绑定一个Context
     * @param url      请求地址（域名+端口）
     * @param params   请求参数
     * @param callback 得到服务器返回数据的回调
     */
    public static void kswyGetMethod(Context context, String url, Map<String, String> params, Callback<String> callback) {
        Map<String, String> getParamMap = params;

        getParamMap.put("message", "");
        getParamMap.put("radioMsg", "");
        getParamMap.put("type", "");

        getParamMap.put("ip", AppUtils.getIpAddress(context));
        getParamMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
        getParamMap.put("clientCode", MyConstants.CLIENT_CODE);
        getParamMap.put("signature", SHA256Utils.getHmacSHA256Str(
                AppUtils.joinParamsBySortedKey(getParamMap), MyConstants.CLIENT_SECRET));

        OkGo.<String>get(url)
                .tag(context)
                .params(getParamMap, false)
                .execute(callback);

    }

    public Map<String, String> setGetParams(String... mString) {

        Map<String, String> getParamMap = new LinkedHashMap<>();
        if (mString != null && mString.length % 2 == 0) {
            for (int i = 0; i < mString.length; i += 2) {
                getParamMap.put(mString[i], mString[i + 1]);
            }
        }

        return getParamMap;
    }

    //判断网络状态
    public static boolean isNetWorkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable() && mNetworkInfo.isConnected();
            }
        }

        return false;
    }


}
