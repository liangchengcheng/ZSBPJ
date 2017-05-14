package com.hsfcompany.tzcs.base;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.database.sqlite.SQLiteDatabase;
import android.os.Vibrator;

import com.hsfcompany.tzcs.dao.DaoMaster;
import com.hsfcompany.tzcs.dao.DaoSession;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 自定义的Application
 * <p>
 * Created by yishon on 15/9/9.
 */
public class BaseApplication extends Application {

    /**
     * 下面是greendao相关
     */
    public DaoSession daoSession;
    public SQLiteDatabase db;
    public DaoMaster.DevOpenHelper helper;
    public DaoMaster daoMaster;

    @Override
    public void onCreate() {
        super.onCreate();
        Frame.setAppContext(this);
        Frame.getInstance().init();
        setupDatabase();
    }

    /**
     * 创建数据库
     */
    private void setupDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        helper = new DaoMaster.DevOpenHelper(this, "tzjc", null);
        db = helper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    private static List<Activity> activityList = new LinkedList();

    /**
     * 添加Activity到容器中
     */
    public static void addActivity(Activity activity) {
        activityList.add(activity);
    }

    /**
     * 遍历所有Activity并finish
     */
    public static void exit() {
        for (Activity activity : activityList) {
            activity.finish();
        }
        //  System.exit(0);
    }

}
