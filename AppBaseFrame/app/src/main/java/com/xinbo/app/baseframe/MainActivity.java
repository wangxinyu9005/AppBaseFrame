package com.xinbo.app.baseframe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xinbo.app.appbaselibrary.base.BaseActivity;
import com.xinbo.app.appbaselibrary.utils.DialogUIUtils;
import com.xinbo.app.intelligentdoctor.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.base_title_back_img)
    ImageView baseTitleBackImg;
    @BindView(R.id.base_title_text)
    TextView baseTitleText;
    @BindView(R.id.base_title_right)
    TextView baseTitleRight;
    @BindView(R.id.base_title_layout)
    Toolbar baseTitleLayout;
    @BindView(R.id.test_show_dialog)
    TextView showDialog;
    @BindView(R.id.test_get_data)
    TextView testGetData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        baseTitleText.setText("框架展示");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getLayoutTitleId() {
        return R.id.base_title_layout;
    }

    @Override
    protected void initView() {
        DialogUIUtils.showMdLoading(this, "加载中...", true, true, true, true).show();
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.base_title_back_img, R.id.test_show_dialog,R.id.test_get_data})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.base_title_back_img:
                finish();
                break;
            case R.id.test_show_dialog:
                startActivity(new Intent(this, TestShowDialogActivity.class));
                break;
            case R.id.test_get_data:
                startActivity(new Intent(this, TestDataActivity.class));
                break;
        }
    }

}
