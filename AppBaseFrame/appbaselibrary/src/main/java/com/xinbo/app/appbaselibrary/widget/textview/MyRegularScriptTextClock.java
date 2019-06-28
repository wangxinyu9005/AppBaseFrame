package com.xinbo.app.appbaselibrary.widget.textview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextClock;

/**
 * @author wxy
 * @description 文鼎大颜楷简体的时间
 * @date 2018/9/29 下午3:00
 */
public class MyRegularScriptTextClock extends TextClock {
    public MyRegularScriptTextClock(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public MyRegularScriptTextClock(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public MyRegularScriptTextClock(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context);
    }


    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("fonts/HanYiYanKaiW-2.ttf", context);
        setTypeface(customFont);
    }
}
