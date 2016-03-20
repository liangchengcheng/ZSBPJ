package com.lcc.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.lcc.base.BaseApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import zsbpj.lccpj.frame.FrameManager;

/**
 * Created by lcc on 16/3/20.
 */
public class CacheHelper {
    private static long wifi_cache_time = 10 * 60 * 1000;
    // 其他网络环境为48小时
    private static long other_cache_time = 2 * 24 * 60 * 60 * 1000;

    public final static String FAV = "fav.pref";
    public final static String GROUP_LIST_CACHE_KEY = "grup_list";
    public final static String CONTENT_LIST_CACHE_KEY = "content_list_";
    public final static String CONTENT_CACHE_KEY = "content_";
    public final static String TEST = "test_";


    public static SharedPreferences getPreferences(String prefName) {
        return FrameManager.getAppContext().getSharedPreferences(prefName, Context.MODE_PRIVATE);
    }

    private static void apply(SharedPreferences.Editor editor) {
        if (BaseApplication.isAtLeastGB) {
            editor.apply();
        } else {
            editor.commit();
        }
    }

    public static long getFav(String key) {
        return getPreferences(FAV).getInt(key, -1);
    }

    public static void putToFav(String key, int value) {
        SharedPreferences preferences = getPreferences(FAV);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        apply(editor);
    }

    public static void removeToFav(String key) {
        SharedPreferences preferences = getPreferences(FAV);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key);
        apply(editor);
    }


    /**
     * 保存对象
     */
    public static boolean saveObject(Context context, Serializable ser,
                                     String file) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = context.openFileOutput(file, Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(ser);
            oos.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取对象
     */
    public static Serializable readObject(Context context, String file) {
        if (!isExistDataCache(context, file))
            return null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = context.openFileInput(file);
            ois = new ObjectInputStream(fis);
            return (Serializable) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            // 反序列化失败 - 删除缓存文件
            if (e instanceof InvalidClassException) {
                File data = context.getFileStreamPath(file);
                if (data != null) {
                    data.delete();
                }
            }
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 判断缓存是否存在
     */
    public static boolean isExistDataCache(Context context, String cachefile) {
        if (context == null)
            return false;
        boolean exist = false;
        File data = context.getFileStreamPath(cachefile);
        if (data.exists())
            exist = true;
        return exist;
    }

    /**
     * 判断缓存是否已经失效
     */
    public static boolean isCacheDataFailure(Context context, String cachefile) {
        File data = context.getFileStreamPath(cachefile);
        if (!data.exists()) {

            return false;
        }
        long existTime = System.currentTimeMillis() - data.lastModified();
        boolean failure = false;
        if (NetWorkUtils.getNetworkType() == NetWorkUtils.NETTYPE_WIFI) {
            failure = existTime > wifi_cache_time ? true : false;
        } else {
            failure = existTime > other_cache_time ? true : false;
        }
        return failure;
    }
}
