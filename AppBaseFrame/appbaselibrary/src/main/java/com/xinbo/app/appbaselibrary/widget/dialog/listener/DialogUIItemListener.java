package com.xinbo.app.appbaselibrary.widget.dialog.listener;

/**
 * Created by Administrator on 2016/7/24.
 */
public abstract class DialogUIItemListener {

    /**
     * item点击事件
     */
    public abstract void onItemClick(CharSequence text, int position);

    /**
     * 底部点击事件
     */
    public void onBottomBtnClick() {
    }

}
