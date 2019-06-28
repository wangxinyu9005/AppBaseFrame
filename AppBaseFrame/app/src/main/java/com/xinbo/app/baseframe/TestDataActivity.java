package com.xinbo.app.baseframe;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xinbo.app.appbaselibrary.base.BaseActivity;
import com.xinbo.app.appbaselibrary.constants.Urls;
import com.xinbo.app.appbaselibrary.utils.AppHttpUtils;
import com.xinbo.app.baseframe.modules.home.adapter.WarmRemindByBaseAdapter;
import com.xinbo.app.baseframe.modules.home.bean.Weather7Days;
import com.xinbo.app.intelligentdoctor.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestDataActivity extends BaseActivity {

    @BindView(R.id.base_title_back_img)
    ImageView baseTitleBackImg;
    @BindView(R.id.base_title_text)
    TextView baseTitleText;
    @BindView(R.id.base_title_right)
    TextView baseTitleRight;
    @BindView(R.id.base_title_layout)
    Toolbar baseTitleLayout;
    @BindView(R.id.warm_remind_list)
    RecyclerView warmRemindList;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        baseTitleText.setText("接口请求数据");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_data;
    }

    @Override
    protected int getLayoutTitleId() {
        return R.id.base_title_layout;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getShenghuozhishu();
            }
        });
        smartRefreshLayout.autoRefresh();
    }

    /**
     * 获取生活指数接口
     */
    private void getShenghuozhishu() {
        Map<String, Object> getParamMap = new HashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        getParamMap.put("date", simpleDateFormat.format(new Date()));
        getParamMap.put("cityname", "廊坊市");
        AppHttpUtils.postJson(TestDataActivity.this, Urls.HOME_LIFE_INDEX, getParamMap, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                if (null != response.body()) {
                    smartRefreshLayout.finishRefresh();
                    Gson gson = new Gson();
                    Weather7Days.Aqi aqi = gson.fromJson(response.body(), Weather7Days.Aqi.class);
                    warmRemindList.setLayoutManager(new LinearLayoutManager(TestDataActivity.this));
                    warmRemindList.setAdapter(new WarmRemindByBaseAdapter(aqi.getAqiInfo().getMeasureList()));
                }
            }
        });
    }

    @OnClick({R.id.base_title_back_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.base_title_back_img:
                finish();
                break;
        }
    }
}
