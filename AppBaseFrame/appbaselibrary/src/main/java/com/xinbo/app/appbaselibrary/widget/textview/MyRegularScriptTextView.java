package com.xinbo.app.appbaselibrary.widget.textview;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * @author wxy
 * @description 文鼎大颜楷简体的文本
 * @date 2018/9/29 下午2:43
 */
public class MyRegularScriptTextView extends AppCompatTextView {

    public MyRegularScriptTextView(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public MyRegularScriptTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public MyRegularScriptTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("fonts/HanYiYanKaiW-2.ttf", context);
        setTypeface(customFont);
    }
}
