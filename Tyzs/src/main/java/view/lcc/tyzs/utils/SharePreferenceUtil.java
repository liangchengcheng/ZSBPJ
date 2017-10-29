package view.lcc.tyzs.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import view.lcc.tyzs.frame.Frame;

public class SharePreferenceUtil {


    public static final String NAME = "name";

    public static final String NICKNAME = "n_name";

    public static final String R_NAME = "r_name";

    public static final String CARD_ID = "card_id";

    public static final String UID = "uid";

    public static final String RATE = "rate";

    public static final String PWD = "pwd";

    public static void setNickname(String NAME) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(Frame.getAppContext());
        sharedPreferences.edit().putString(NICKNAME,NAME).commit();
    }

    public static String getNickname() {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(Frame.getAppContext());
        return sharedPreferences.getString(NICKNAME, "");
    }

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

    //收件人
    public static String getAddressPerson() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Frame.getAppContext());
        return sharedPreferences.getString("addressee1", "");
    }

    public static void setAddressPerson(String value){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Frame.getAppContext());
        sharedPreferences.edit().putString("addressee1",value).commit();
    }

    //收件人的电话
    public static String getAddressPhone() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Frame.getAppContext());
        return sharedPreferences.getString("aphone1", "");
    }

    public static void setAddressPhone(String value){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Frame.getAppContext());
        sharedPreferences.edit().putString("aphone1",value).commit();
    }


    //收件人的地址
    public static String getAddressInfo() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Frame.getAppContext());
        return sharedPreferences.getString("address1", "");
    }

    public static void setAddressInfo(String value){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Frame.getAppContext());
        sharedPreferences.edit().putString("address1",value).commit();
    }


    //收件人的地址 的id
    public static String getAddressId() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Frame.getAppContext());
        return sharedPreferences.getString("aid", "");
    }

    public static void setAddressId(String value){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Frame.getAppContext());
        sharedPreferences.edit().putString("aid",value).commit();
    }

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
