package com.lcc;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;

import com.lcc.db.test.DaoMaster;
import com.lcc.db.test.DaoSession;

import zsbpj.lccpj.frame.FrameManager;


public class App extends Application {

    private static final String CANARO_EXTRA_BOLD_PATH = "fonts/canaro_extra_bold.otf";
    public static Typeface canaroExtraBold;
    public DaoSession daoSession;
    public SQLiteDatabase db;
    public DaoMaster.DevOpenHelper helper;
    public DaoMaster daoMaster;

    @Override
    public void onCreate() {
        super.onCreate();
        FrameManager.setAppContext(this);
        FrameManager.getInstance().init();

        initTypeface();
        setupDatabase();
    }

    private void initTypeface() {
        canaroExtraBold = Typeface.createFromAsset(getAssets(), CANARO_EXTRA_BOLD_PATH);
    }

    /**
     * 创建数据库
     */
    private void setupDatabase() {
        helper = new DaoMaster.DevOpenHelper(this,"dtcj", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }

}
