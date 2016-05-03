package com.lcc.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import zsbpj.lccpj.frame.FrameManager;

public class SharePreferenceUtil {

    //session id
    public static final String USER_TK = "user_tk";

    //是否开启缓存
    public static final String IS_CACHE = "is_cache";

    /**
     * 获取 session id
     */
    public static String getUserTk() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(FrameManager.getAppContext());
        return sharedPreferences.getString(USER_TK, "");
    }

    /**
     * 存放 session id
     */
    public static void setUserTk(String value){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(FrameManager.getAppContext());
        sharedPreferences.edit().putString(USER_TK,value).apply();
    }

    /**
     * 读取是否开启了缓存，默认是开启的
     */
    public static boolean isCache() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(FrameManager.getAppContext());
        return sharedPreferences.getBoolean(IS_CACHE, true);
    }

    /**
     * 设置是否开启了缓存
     */
    public static void setIsCache(boolean isCache){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(FrameManager.getAppContext());
        sharedPreferences.edit().putBoolean(IS_CACHE,isCache).apply();
    }

}
