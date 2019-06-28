package com.xinbo.app.baseframe;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.header.BezierCircleHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.xinbo.app.appbaselibrary.base.BaseLibraryApplication;
import com.xinbo.app.appbaselibrary.interceptor.LoggerInterceptor;
import com.xinbo.app.appbaselibrary.network.NetStateChangeReceiver;
import com.xinbo.app.appbaselibrary.utils.DensityAdapterUtils;
import com.xinbo.app.appbaselibrary.utils.DialogUIUtils;
import com.xinbo.app.intelligentdoctor.BuildConfig;
import com.xinbo.app.intelligentdoctor.R;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
* @description
* @date 2019年04月09日14:32:45
* @author wxy
*/
public class BaseApplication extends Application {
    private static final String TAG = BaseApplication.class.getSimpleName();
    private static Context context;
    private final int OKGO_TIME_OUT = 12000;

    private static BaseApplication myApplication = null;

    public static BaseApplication getApplication() {
        return myApplication;
    }

    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.app_base_colorPrimary, android.R.color.white);//全局设置主题颜色
                layout.setHeaderHeight(40);
                return new BezierCircleHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication=this;
        context = getApplicationContext();
        BaseLibraryApplication.init(context);
        DialogUIUtils.init(this);
        //适配必须设置
        DensityAdapterUtils.setDensity(this);
        //初始化网络请求
        initOkGo();
        //初始化日志打印logger
        initLogger();
        //网络监听
        registerNetworkObserver();

    }
    @Override
    public void onTerminate() {
        super.onTerminate();
        unregisteretworkObserver();
    }


    public static Context getAppContext() {
        return context;
    }

    //解决问题: Caused by: java.lang.ClassNotFoundException: Didn't find class "android.support.v4.content.FileProvider" on path:
    // DexPathList[[zip file "/data/app/com.lecong.app.lawyer-1.apk"],nativeLibraryDirectories=[/data/app-lib/com.lecong.app.lawyer-1, /system/lib]]
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    private void initOkGo() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //log相关
        HttpLoggingInterceptor loggerInterceptor = new HttpLoggingInterceptor(new LoggerInterceptor());
        loggerInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addNetworkInterceptor(loggerInterceptor);                                            //添加OkGo默认debug日志

        builder.readTimeout(OKGO_TIME_OUT, TimeUnit.MILLISECONDS);      //全局的读取超时时间
        builder.writeTimeout(OKGO_TIME_OUT, TimeUnit.MILLISECONDS);     //全局的写入超时时间
        builder.connectTimeout(OKGO_TIME_OUT, TimeUnit.MILLISECONDS);   //全局的连接超时时间


        // 详细说明看GitHub文档：https://github.com/jeasonlzy/
        OkGo.getInstance().init(this)                           //必须调用初始化
                .setOkHttpClient(builder.build())               //建议设置OkHttpClient，不设置会使用默认的
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(0);                             //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
    }

    /**
     * 初始化logger
     */
    private void initLogger(){
        //日志打印
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }
    /**
     * 初始化网络监听
     */
    private void registerNetworkObserver(){
        NetStateChangeReceiver.registerReceiver(this);
    }

    private void unregisteretworkObserver(){
        NetStateChangeReceiver.unregisterReceiver(this);
    }

}
