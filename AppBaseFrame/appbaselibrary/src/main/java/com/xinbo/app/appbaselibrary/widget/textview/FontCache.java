package com.xinbo.app.appbaselibrary.widget.textview;

import android.content.Context;
import android.graphics.Typeface;

import java.util.HashMap;

/**
 * @author wxy
 * @description 缓存字体，同时最小化对assets文件夹的访问次数
 * @date 2018/9/29 下午2:48
 */
public class FontCache {

    private static HashMap<String, Typeface> fontCache = new HashMap<>();

    public static Typeface getTypeface(String fontname, Context context) {
        Typeface typeface = fontCache.get(fontname);
        if (typeface == null) {
            try {
                typeface = Typeface.createFromAsset(context.getAssets(), fontname);
            } catch (Exception e) {
                return null;
            }
            fontCache.put(fontname, typeface);
        }
        return typeface;
    }
}
