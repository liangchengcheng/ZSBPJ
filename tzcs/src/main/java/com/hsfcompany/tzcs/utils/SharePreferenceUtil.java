package com.hsfcompany.tzcs.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.hsfcompany.tzcs.base.Frame;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |05-17 21:44
 * Description:  |
 */
public class SharePreferenceUtil {

    //软件更新的时间
    public static final String UPDATE_TIME = "update_time";

    /**
     * 获取软件更新的时间
     */
    public static String getUpdateTime() {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(Frame.getAppContext());
        return sharedPreferences.getString(UPDATE_TIME, null);
    }

    public static void setUpdateTime(String value){
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(Frame.getAppContext());
        sharedPreferences.edit().putString(UPDATE_TIME,value).apply();
    }


}
