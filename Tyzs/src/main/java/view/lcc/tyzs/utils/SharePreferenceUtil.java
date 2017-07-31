package view.lcc.tyzs.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import view.lcc.tyzs.frame.Frame;

public class SharePreferenceUtil {


    public static final String NAME = "name";

    public static final String R_NAME = "r_name";

    public static final String CARD_ID = "card_id";

    public static final String UID = "uid";

    public static final String RATE = "rate";

    public static final String PWD = "pwd";


    public static String getName() {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(Frame.getAppContext());
        return sharedPreferences.getString(NAME, "");
    }

    public static void setName(String value){
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(Frame.getAppContext());
        sharedPreferences.edit().putString(NAME,value).commit();
    }

    public static String getrName() {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(Frame.getAppContext());
        return sharedPreferences.getString(R_NAME, "");
    }

    public static void setrName(String value){
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(Frame.getAppContext());
        sharedPreferences.edit().putString(R_NAME,value).commit();
    }

    public static String getCardId() {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(Frame.getAppContext());
        return sharedPreferences.getString(CARD_ID, "");
    }

    public static void setCardId(String value){
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(Frame.getAppContext());
        sharedPreferences.edit().putString(CARD_ID,value).commit();
    }

    public static String getUid() {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(Frame.getAppContext());
        return sharedPreferences.getString(UID, "");
    }

    public static void setUid(String value){
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(Frame.getAppContext());
        sharedPreferences.edit().putString(UID,value).commit();
    }

    public static String getPwd() {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(Frame.getAppContext());
        return sharedPreferences.getString(PWD, "");
    }

    public static void setPwd(String value){
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(Frame.getAppContext());
        sharedPreferences.edit().putString(PWD,value).commit();
    }
    public static String getRate() {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(Frame.getAppContext());
        return sharedPreferences.getString(RATE, "");
    }

    public static void setRate(String value){
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(Frame.getAppContext());
        sharedPreferences.edit().putString(RATE,value).commit();
    }



}
