package com.hsfcompany.tzcs.dao;

import android.content.Context;

import com.hsfcompany.tzcs.base.BaseApplication;
import com.hsfcompany.tzcs.base.Frame;

import java.util.List;
import de.greenrobot.dao.query.Query;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    2015年12月15日10:47:52
 * Description:  DataManager 管理保存的用户信息
 */
public class DataManager {

    /**
     * 根据主键删除实体
     */
    public static void deleteAllUser() {
        getNoteDao(Frame.getAppContext()).deleteAll();
    }


    public static UserInfoDao getNoteDao(Context context) {
        return ((BaseApplication) context.getApplicationContext()).getDaoSession().getUserInfoDao();
    }

    /**
     * 向数据库里插入登录信息
     */
    public static void addUserInfo(UserInfo userInfo) {
        if (userInfo != null) {
            getNoteDao(Frame.getAppContext()).insert(userInfo);
        }
    }

    /**
     * 获取保存数据库的uf
     */
    public static UserInfo getUserInfoByTime() {
        Query query = getNoteDao(Frame.getAppContext()).queryBuilder()
                .orderDesc(UserInfoDao.Properties.Ctime)
                .build();
        List list= query.list();

        if (list != null && list.size() > 0) {
           return (UserInfo) list.get(0);
        }
        return null;
    }

    /**
     * 获取所有用户的信息
     */
    public static List<UserInfo> getAllData() {
        Query query = getNoteDao(Frame.getAppContext()).queryBuilder()
                .orderDesc(UserInfoDao.Properties.Ctime)
                .build();
        return query.list();
    }

    /**
     * 根据主键删除实体
     */
    public static void deleteById(long Id) {
        getNoteDao(Frame.getAppContext()).deleteByKey(Id);
    }

}
