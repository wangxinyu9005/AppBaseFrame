package com.xinbo.app.appbaselibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Base64;

import org.json.JSONArray;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SharedPreferencesUtils {

    // 如果设置中支持更改app应用字体大小，那么就通过该key保存该字体的缩放比
    private static final String APP_FONT_SCALE = "pref_font_scale";

    // 保存应用第一次启动时获取到的application的appDensity
    public static final String APPLICATION_APP_DENSITY = "pref_application_app_density";

    // 保存应用第一次启动时获取到的application的appDensity
    public static void setApplicationAppDensity(Context context, float value) {
        setFloatPreference(context, APPLICATION_APP_DENSITY, value);
    }

    public static Float getApplicationAppDensity(Context context, float defaultValue) {
        return getFloatPreference(context, APPLICATION_APP_DENSITY, defaultValue);
    }


    public static void setAppFontScale(Context context, float fontScale) {
        setFloatPreference(context, APP_FONT_SCALE, fontScale);
    }

    public static float getAppFontScale(Context context, float defaultValue) {
        return getFloatPreference(context, APP_FONT_SCALE, defaultValue);
    }

    //=======================================dividers=====================================================

    public static void setStringPreference(Context context, String key, String value) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(key, value).apply();
    }

    public static String getStringPreference(Context context, String key, String defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(key, defaultValue);
    }


    public static void setFloatPreference(Context context, String key, float value) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putFloat(key, value).apply();
    }

    public static Float getFloatPreference(Context context, String key, float defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(context).getFloat(key, defaultValue);
    }

    public static Set<String> getStringSetPreference(Context context, String key, Set<String> defaultValues) {
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        if (prefs.contains(key)) {
            return prefs.getStringSet(key, Collections.<String>emptySet());
        } else {
            return defaultValues;
        }
    }

    public static boolean getBooleanPreference(Context context, String key, boolean defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(key, defaultValue);
    }

    public static void setBooleanPreference(Context context, String key, boolean value) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(key, value).apply();
    }

    public static int getIntegerPreference(Context context, String key, int defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt(key, defaultValue);
    }

    public static void setIntegerPreference(Context context, String key, int value) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(key, value).apply();
    }

    public static long getLongPreference(Context context, String key, long defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(context).getLong(key, defaultValue);
    }

    public static void setStringListPreference(Context context, String key, List<String> list) {
        JSONArray jsonArray = new JSONArray();
        for (String s : list) {
            jsonArray.put(s);
        }

        setStringPreference(context, key, jsonArray.toString());
    }

    public static List<String> getStringListPreference(Context context, String key) {
        List<String> list = new ArrayList<>();
        String json = getStringPreference(context, key, "[]");
        if (json.equals("[]")) return list;

        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                list.add(jsonArray.getString(i));
            }

            return list;
        } catch (Exception e) {
            return list;
        }
    }

    public static boolean hasPreference(Context context, String key) {
        return PreferenceManager.getDefaultSharedPreferences(context).contains(key);
    }

    public static void putStringSet(Context context, String key, String values) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor keyEditor = prefs.edit();
        Set<String> address = getStringSetPreference(context, key, Collections.<String>emptySet());
        Set<String> newAddress = new HashSet<>(address);
        newAddress.add(values);
        keyEditor.putStringSet(key, newAddress).commit();
    }


    public static void removePreference(Context context, String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor keyEditor = prefs.edit();
        keyEditor.remove(key);
        keyEditor.commit();
    }

    public static void putList(Context context, String key, List<? extends Serializable> list) {
        try {
            put(context, key, list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <E extends Serializable> List<E> getList(Context context, String key) {
        try {
            return (List<E>) get(context, key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void put(Context context, String key, Object obj)
            throws IOException
    {
        if (obj == null) {
            return;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(obj);
        String objectStr = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
        baos.close();
        oos.close();

        setStringPreference(context, key, objectStr);
    }

    public static Object get(Context context, String key)
            throws IOException, ClassNotFoundException
    {
        String wordBase64 = getStringPreference(context, key, "");
        if (TextUtils.isEmpty(wordBase64)) {
            return null;
        }
        byte[]               objBytes = Base64.decode(wordBase64.getBytes(), Base64.DEFAULT);
        ByteArrayInputStream bais     = new ByteArrayInputStream(objBytes);
        ObjectInputStream ois      = new ObjectInputStream(bais);
        Object obj = ois.readObject();
        bais.close();
        ois.close();
        return obj;
    }

}
