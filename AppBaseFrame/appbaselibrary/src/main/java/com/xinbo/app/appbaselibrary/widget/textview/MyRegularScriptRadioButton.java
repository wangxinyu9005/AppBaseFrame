package com.xinbo.app.appbaselibrary.widget.textview;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;

/**
 * @author LC
 * @description 文鼎大颜楷简体的文本
 * @date 2018/10/24
 */
public class MyRegularScriptRadioButton extends AppCompatRadioButton {

    public MyRegularScriptRadioButton(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public MyRegularScriptRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public MyRegularScriptRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("fonts/HanYiYanKaiW-2.ttf", context);
        setTypeface(customFont);
    }
}
