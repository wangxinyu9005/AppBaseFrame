package com.xinbo.app.baseframe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import com.xinbo.app.intelligentdoctor.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wxy
 * @description App快速启动页，提前加载首页数据
 * @date 2019/2/26 10:19 AM
 */
public class LaunchActivity extends AppCompatActivity {
    @BindView(R.id.launch_text)
    TextView launchText;
    private AlphaAnimation alphaAnimation;

    /**
     * launchImgTop执行动画
     */
    private void startLaunchTopAnimation() {
        alphaAnimation = new AlphaAnimation(0.0f, 1.0f);//第一个参数开始的透明度，第二个参数结束的透明度
        alphaAnimation.setDuration(1000);//多长时间完成这个动作
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(LaunchActivity.this, MainActivity.class));
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        launchText.startAnimation(alphaAnimation);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.app_base_AppTheme_Launcher);
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        setContentView(R.layout.activity_launch);
        ButterKnife.bind(this);
        launchText.setText("基本框架启动页");
        // launchImgTop执行动画
        startLaunchTopAnimation();
    }
}
