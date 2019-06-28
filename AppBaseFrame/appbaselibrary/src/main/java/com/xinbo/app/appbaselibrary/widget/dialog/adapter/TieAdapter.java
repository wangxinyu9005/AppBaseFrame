package com.xinbo.app.appbaselibrary.widget.dialog.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.xinbo.app.appbaselibrary.R;
import com.xinbo.app.appbaselibrary.widget.dialog.bean.TieBean;
import com.xinbo.app.appbaselibrary.widget.dialog.holder.SuperItemHolder;
import com.xinbo.app.appbaselibrary.widget.dialog.holder.TieItemHolder;

import java.util.List;


/**
 * ========================================
 * <p/>
 * 版 权：dou361.com 版权所有 （C） 2015
 * <p/>
 * 作 者：陈冠明
 * <p/>
 * 个人网站：http://www.dou361.com
 * <p/>
 * 版 本：1.0
 * <p/>
 * 创建日期：2016/10/5
 * <p/>
 * 描 述：编码器适配器
 * <p/>
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
public class TieAdapter extends SuperAdapter<TieBean> {

    private boolean isItemType;

    public TieAdapter(Context mContext, List<TieBean> list, boolean isItemType) {
        super(mContext, list);
        this.isItemType = isItemType;
    }

    public TieAdapter(Context mContext, List<TieBean> list) {
        super(mContext, list);
    }

    @Override
    public SuperItemHolder getItemHolder(ViewGroup parent, int viewType) {
        return new TieItemHolder(mContext, mListener, LayoutInflater.from(mContext).
                inflate(R.layout.app_base_dialogui_holder_item_tie, parent, false));
    }

    /**
     * 1top 2midle 3bottom 4all
     */
    protected int countPosition(int position) {
        if (isItemType) {
            if (mDatas.size() == 1) {
                return 4;
            }
            if (mDatas.size() > 1) {
                if (position == 0) {
                    return 1;
                } else if (position == mDatas.size() - 1) {
                    return 3;
                }
            }
            return 2;
        } else {
            return super.countPosition(position);
        }
    }
}
