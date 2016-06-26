package com.lcc.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import zsbpj.lccpj.frame.FrameManager;

public class SharePreferenceUtil {

    //session id
    public static final String USER_TK = "user_tk";
    //职业类型
    public static final String USER_TYPE = "user_type";
    //是否已经看了首页
    public static final String GUIDE = "guide";

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


    /**
     * 获取职业类型
     */
    public static String getUserType() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(FrameManager.getAppContext());
        return sharedPreferences.getString(USER_TYPE, "");
    }

    /**
     * 存放职业类型
     */
    public static void setUserType(String value){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(FrameManager.getAppContext());
        sharedPreferences.edit().putString(USER_TYPE,value).apply();
    }

    /**
     * 设置是否引导了界面
     */
    public static boolean getGuide() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(FrameManager.getAppContext());
        return sharedPreferences.getBoolean(GUIDE, false);
    }

    /**
     * 设置是否引导了界面
     */
    public static void setGuide(boolean value){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(FrameManager.getAppContext());
        sharedPreferences.edit().putBoolean(GUIDE,value).apply();
    }

}
