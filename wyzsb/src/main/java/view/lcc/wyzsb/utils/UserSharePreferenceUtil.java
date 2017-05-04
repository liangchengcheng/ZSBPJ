package view.lcc.wyzsb.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import view.lcc.wyzsb.frame.Frame;


public class UserSharePreferenceUtil {

    //session id
    public static final String USER_MID = "user_mid";

    public static final String USER_PHONE = "user_phone";

    public static final String USER_NAME = "user_name";

    public static final String USER_IMAGE = "user_image";


    /**
     * 获取 session id
     */
    public static String getUserSession() {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(Frame.getAppContext());
        return sharedPreferences.getString(USER_MID, "");
    }

    /**
     * 存放 session id
     */
    public static void setUserSession(String value){
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(Frame.getAppContext());
        sharedPreferences.edit().putString(USER_MID,value).commit();
    }

    /**
     * 获取 用户的手机号
     */
    public static String getUserPhone() {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(Frame.getAppContext());
        return sharedPreferences.getString(USER_PHONE, "");
    }

    /**
     * 存放 用户的手机号
     */
    public static void setUserPhone(String value){
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(Frame.getAppContext());
        sharedPreferences.edit().putString(USER_PHONE,value).commit();
    }

    /**
     * 获取 用户的昵称
     */
    public static String getUserName() {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(Frame.getAppContext());
        return sharedPreferences.getString(USER_NAME, "");
    }

    /**
     * 存放 用户的昵称
     */
    public static void setUserName(String value){
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(Frame.getAppContext());
        sharedPreferences.edit().putString(USER_NAME,value).commit();
    }

    /**
     * 获取 用户的头像
     */
    public static String getUserImage() {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(Frame.getAppContext());
        return sharedPreferences.getString(USER_IMAGE, "");
    }

    /**
     * 存放 用户的头像
     */
    public static void setUserImage(String value){
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(Frame.getAppContext());
        sharedPreferences.edit().putString(USER_IMAGE,value).commit();
    }


}
