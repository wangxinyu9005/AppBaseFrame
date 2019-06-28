package com.xinbo.app.appbaselibrary.base;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author wxy
 * @description Adapter基类，继承BaseQuickAdapter
 * @date 2019/4/11 9:30 AM
 */
public abstract class BaseAdapter<T, K extends BaseViewHolder> extends BaseQuickAdapter<T, K> {
    public BaseAdapter(int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }

    public BaseAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected abstract void convert(K helper, T item) ;

}
