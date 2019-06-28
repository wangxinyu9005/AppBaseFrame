package com.xinbo.app.appbaselibrary.base;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.xinbo.app.appbaselibrary.R;
import com.xinbo.app.appbaselibrary.network.NetStateChangeObserver;
import com.xinbo.app.appbaselibrary.network.NetStateChangeReceiver;
import com.xinbo.app.appbaselibrary.network.NetworkType;
import com.xinbo.app.appbaselibrary.utils.AppManager;
import com.xinbo.app.appbaselibrary.utils.DensityAdapterUtils;
import com.xinbo.app.appbaselibrary.utils.DialogUIUtils;
import com.xinbo.app.appbaselibrary.utils.LoggerUtils;
import com.xinbo.app.appbaselibrary.widget.dialog.listener.DialogUIListener;

/**
* @description 支持沉浸式状态栏设置
* @date 2019年04月09日15:13:58
* @author wxy
*/
public abstract class BaseActivity extends AppCompatActivity implements NetStateChangeObserver {

    private InputMethodManager mInputMethodManager;
    private boolean isNeedShowError = true;
    private Dialog dialog = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //App屏幕适配
        DensityAdapterUtils.setDefault(this);//适配必须设置
        //将Activity统一管理
        AppManager.getAppManager().addActivity(this);

        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
            // 页面渲染完成后，开始进行初始化和加载数据等耗时操作。
            getWindow().getDecorView().post(new Runnable() {
                @Override
                public void run() {
                    //初始化view
                    initView();
                    //初始化数据
                    initData();
                }
            });

        }

        //初始化沉浸式状态栏
        if (isImmersionBarEnabled()) {
            initImmersionBar();
        }
    }

    protected abstract void initView();

    protected abstract void initData();

    /**
     * setContentView中布局Id
     */
    protected abstract int getLayoutId();

    /**
     * 沉浸式状态栏指定的title
     */
    protected abstract int getLayoutTitleId();

    /**
     * 在BaseActivity里初始化沉浸式状态栏,默认是白色标题栏，状态栏文字是黑色
     */
    protected void initImmersionBar() {
        ImmersionBar.with(this)
                .statusBarDarkFont(true)
                .keyboardEnable(true)
                .navigationBarColor(R.color.app_base_main_color_black)
                .titleBar(getLayoutTitleId())
                .init();
    }
    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    public void finish() {
        super.finish();
        hideSoftKeyBoard();
    }

    public void hideSoftKeyBoard() {
        View localView = getCurrentFocus();
        if (this.mInputMethodManager == null) {
            this.mInputMethodManager = ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
        }
        if ((localView != null) && (this.mInputMethodManager != null)) {
            this.mInputMethodManager.hideSoftInputFromWindow(localView.getWindowToken(), 2);
        }
    }

    /**
     * 是否需要注册网络变化的Observer,如果不需要监听网络变化,则返回false;否则返回true.默认返回false
     */
    protected boolean needRegisterNetworkChangeObserver() {
        return true;
    }

    @Override
    public void onNetDisconnected() {
        LoggerUtils.e("BaseActivity====网络已断开..");
        if (isNeedShowError){
            if (null!=dialog&&!dialog.isShowing()){
                getTwoButtonDialog("", "检测到网络已断开", "去设置",new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Settings.ACTION_SETTINGS);
                        startActivity(intent);
                        isNeedShowError = false;
                    }
                });
            }
        }


    }

    @Override
    public void onNetConnected(NetworkType networkType) {
        LoggerUtils.e("BaseActivity网络已连接>>>" + networkType.toString());
        DialogUIUtils.showToastCenter("网络已连接");
        isNeedShowError=true;
    }

    /**
     * 双按钮dialog
     * @param title
     * @param msg
     * @param listener
     */
    private void getTwoButtonDialog(String title, String msg,String sure,View.OnClickListener listener){
        @SuppressLint("InflateParams")
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialogui_alert_two_button,null);
        dialog =DialogUIUtils.showCustomAlert(this,dialogView, Gravity.CENTER,true,true).show();
        TextView tvTitle = dialogView.findViewById(R.id.dialogui_tv_title);
        TextView tvContent = dialogView.findViewById(R.id.dialogui_tv_msg);
        Button btnCancle = dialogView.findViewById(R.id.btn_cancle);
        Button btnSure = dialogView.findViewById(R.id.btn_sure);

        tvTitle.setText(title);
        tvContent.setText(msg);
        btnSure.setText(sure);
        btnCancle.setText("取消");
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSure.setOnClickListener(listener);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (needRegisterNetworkChangeObserver()) {
            NetStateChangeReceiver.registerObserver(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (needRegisterNetworkChangeObserver()) {
            NetStateChangeReceiver.unregisterObserver(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mInputMethodManager = null;
        if (isImmersionBarEnabled()) {
            ImmersionBar.with(this).destroy();
        }
        isNeedShowError=true;
    }
}
