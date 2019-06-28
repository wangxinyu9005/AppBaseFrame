package com.xinbo.app.baseframe.modules.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.xinbo.app.baseframe.modules.home.bean.Weather7Days;
import com.xinbo.app.intelligentdoctor.R;

import java.util.ArrayList;

/**
 * @author wxy
 * @description 首页中温馨提示
 * @date 2018/11/30 9:31 AM
 */
public class WarmRemindAdapter extends RecyclerView.Adapter<WarmRemindAdapter.RemindViewHolder> {

    private Context mContext;
    private ArrayList<Weather7Days.MeasureListVo> measureListVos;

    public WarmRemindAdapter(Context mContext, ArrayList<Weather7Days.MeasureListVo> measureListVos) {
        this.mContext = mContext;
        this.measureListVos = measureListVos;
    }

    @NonNull
    @Override
    public RemindViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RemindViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_warm_remind, null, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RemindViewHolder holder, int position) {
        final String content = measureListVos.get(position).getZhishu();
        if (content.contains("交通")) {
            holder.remindIcon.setImageResource(R.mipmap.icon_warm_remind_jiaotong);
        } else if (content.contains("过敏")) {
            holder.remindIcon.setImageResource(R.mipmap.icon_warm_remind_guomin);
        } else if (content.contains("穿衣")) {
            holder.remindIcon.setImageResource(R.mipmap.icon_warm_remind_chuanyi);
        } else if (content.contains("感冒")) {
            holder.remindIcon.setImageResource(R.mipmap.icon_warm_remind_ganmao);
        } else if (content.contains("紫外线")) {
            holder.remindIcon.setImageResource(R.mipmap.icon_warm_remind_ziwaixian);
        } else if (content.contains("洗车")) {
            holder.remindIcon.setImageResource(R.mipmap.icon_warm_remind_xiche);
        } else if (content.contains("污染扩散")) {
            holder.remindIcon.setImageResource(R.mipmap.icon_warm_remind_kongqiwuran);
        } else if (content.contains("化妆")) {
            holder.remindIcon.setImageResource(R.mipmap.icon_warm_remind_huazhuang);
        } else if (content.contains("运动")) {
            holder.remindIcon.setImageResource(R.mipmap.icon_warm_remind_yundong);
        } else if (content.contains("钓鱼")) {
            holder.remindIcon.setImageResource(R.mipmap.icon_warm_remind_diaoyu);
        } else if (content.contains("旅游")) {
            holder.remindIcon.setImageResource(R.mipmap.icon_warm_remind_lvyou);
        }
        holder.remindLabel.setText(measureListVos.get(position).getZhishu());
        holder.remindContent.setText(measureListVos.get(position).getZhishuNeiRong());
    }

    @Override
    public int getItemCount() {
        return measureListVos.size();
    }

    public static class RemindViewHolder extends RecyclerView.ViewHolder{

        public final TextView remindContent;
        public final TextView remindLabel;
        public final ImageView remindIcon;
        public final LinearLayout warmRemindLl;

        public RemindViewHolder(View itemView) {
            super(itemView);
            remindContent = itemView.findViewById(R.id.item_warm_remind_text);
            remindLabel = itemView.findViewById(R.id.item_warm_remind_label);
            remindIcon = itemView.findViewById(R.id.item_warm_remind_icon);
            warmRemindLl = itemView.findViewById(R.id.item_warm_remind_ll);
        }
    }
}
