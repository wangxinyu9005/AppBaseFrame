package com.xinbo.app.baseframe.modules.home.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.xinbo.app.appbaselibrary.base.BaseAdapter;
import com.xinbo.app.baseframe.modules.home.bean.Weather7Days;
import com.xinbo.app.intelligentdoctor.R;

import java.util.List;

/**
 * @author wxy
 * @description 首页中温馨提示, 继承BaseQuickAdapter
 * @date 2018/11/30 9:31 AM
 */
public class WarmRemindByBaseAdapter extends BaseAdapter<Weather7Days.MeasureListVo, BaseViewHolder> {

    public WarmRemindByBaseAdapter(@Nullable List<Weather7Days.MeasureListVo> data) {
        super(R.layout.item_warm_remind, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Weather7Days.MeasureListVo item) {
        final String content = item.getZhishu();
        if (content.contains("交通")) {
            setIcon(helper, R.mipmap.icon_warm_remind_jiaotong);
        } else if (content.contains("过敏")) {
            setIcon(helper, R.mipmap.icon_warm_remind_guomin);
        } else if (content.contains("穿衣")) {
            setIcon(helper, R.mipmap.icon_warm_remind_chuanyi);
        } else if (content.contains("感冒")) {
            setIcon(helper, R.mipmap.icon_warm_remind_ganmao);
        } else if (content.contains("紫外线")) {
            setIcon(helper, R.mipmap.icon_warm_remind_ziwaixian);
        } else if (content.contains("洗车")) {
            setIcon(helper, R.mipmap.icon_warm_remind_xiche);
        } else if (content.contains("污染扩散")) {
            setIcon(helper, R.mipmap.icon_warm_remind_kongqiwuran);
        } else if (content.contains("化妆")) {
            setIcon(helper, R.mipmap.icon_warm_remind_huazhuang);
        } else if (content.contains("运动")) {
            setIcon(helper, R.mipmap.icon_warm_remind_yundong);
        } else if (content.contains("钓鱼")) {
            setIcon(helper, R.mipmap.icon_warm_remind_diaoyu);
        } else if (content.contains("旅游")) {
            setIcon(helper, R.mipmap.icon_warm_remind_lvyou);
        }

        helper.setText(R.id.item_warm_remind_label, item.getZhishu());
        helper.setText(R.id.item_warm_remind_text, item.getZhishuNeiRong());
    }

    private void setIcon(BaseViewHolder helper, int drawableId) {
        helper.setImageResource(R.id.item_warm_remind_icon, drawableId);
    }
}
