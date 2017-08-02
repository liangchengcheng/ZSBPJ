package view.lcc.tyzs.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.squareup.okhttp.OkHttpClient;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import view.lcc.tyzs.bean.DaoMaster;
import view.lcc.tyzs.bean.DaoSession;
import view.lcc.tyzs.frame.Frame;
import view.lcc.tyzs.frame.okhttp.OkHttpClientManager;

import static android.database.sqlite.SQLiteDatabase.OPEN_READWRITE;

//MultiDexApplication(应该使用这个)
public class BaseApplication extends Application {

    private static final int CONNECT_TIMEOUT_MILLIS = 30 * 1000;
    private static final int READ_TIMEOUT_MILLIS = 30 * 1000;

    private static List<Activity> activityList = new LinkedList();

    private OkHttpClient okHttpClient;

    private final String db_name = "jxyh.db";
    private final String db_path = "databases";
    private final String DB_UPDATE = "updateDB.xml";
    private final String E_DB = "db";
    private static final String E_VER = "version";
    private static final String E_SQL_EXEC = "sqlExec";
    private static final String ATTR_VER_CODE = "ver-code";
    private static final String ATTR_SQL = "sql";
    private static DaoSession daoSession;
    private SQLiteDatabase db;
    private int mDbVersion;

    private static BaseApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        Frame.setAppContext(this);
        Frame.getInstance().init();
        initOkHttp();
        mInstance = this;

        //数据库升级
        new Thread() {

            @Override
            public void run() {
                super.run();
                File dir = new File(getFilesDir().getParent(), db_path);
                if (!dir.exists())
                    dir.mkdirs();

                File dbFile = new File(dir, db_name);

                if (!dbFile.exists()) {
                    try {
                        dbFile.createNewFile();
                        FileOutputStream fos = new FileOutputStream(dbFile);
                        InputStream inputStream = getAssets().open(db_name);

                        byte[] buffer = new byte[10240];
                        int len;
                        while ((len = inputStream.read(buffer)) != -1) {
                            fos.write(buffer, 0, len);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    db = SQLiteDatabase.openDatabase(dbFile.getPath(), null, OPEN_READWRITE);
                    updateDB();
                }

                db = SQLiteDatabase.openDatabase(dbFile.getPath(), null, OPEN_READWRITE);
                DaoMaster daoMaster = new DaoMaster(db);
                daoSession = daoMaster.newSession();

            }
        }.start();

    }

    private void initOkHttp() {
        okHttpClient = OkHttpClientManager.getInstance().getOkHttpClient();
        okHttpClient.setConnectTimeout(CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
        okHttpClient.setReadTimeout(READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
    }

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
        //System.exit(0);
    }


    private void updateDB() {

        getDbVersion();
        InputStream inputStream = null;
        try {
            inputStream = getAssets().open(DB_UPDATE);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inputStream);

            NodeList items = doc.getElementsByTagName(E_DB);
            if (items == null || items.getLength() <= 0) {
                return;
            }
            Node root = items.item(0);
            parseDbUpdate(root);
        } catch (Exception e) {
        } finally {
            if (inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    private void parseDbUpdate(Node root) throws Exception {
        if (!isValidElement(root))
            return;

        NodeList items = root.getChildNodes();

        boolean isNeedUpdate = false;
        int size = items.getLength();
        for (int i = 0; i < size; i++) {
            Node nodeVersion = items.item(i);
            String elemName = nodeVersion.getNodeName();
            if (!E_VER.equalsIgnoreCase(elemName) || !isValidElement(nodeVersion))
                continue;

            Element e = (Element) nodeVersion;
            String versionCode = e.getAttribute(ATTR_VER_CODE);
            if (isNeedUpdate || isNeedUpdate(versionCode)) {
                isNeedUpdate = true;

                try {
                    db.beginTransaction();
                    parseVersionElement(e);
                    db.execSQL(String.format("UPDATE DBINFO SET version = '%s'", versionCode));
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }
            }
        }
    }

    private boolean isNeedUpdate(String curVersion) {
        String curNum = curVersion.replace(".", "");
        return Integer.parseInt(curNum.trim()) > mDbVersion;
    }

    private void getDbVersion() {
        String dbNum = getCurDBVersion().replace(".", "");
        mDbVersion = Integer.parseInt(dbNum.trim());
    }

    private String getCurDBVersion() {
        String version = null;
        Cursor c = null;
        try {
            c = db.rawQuery("select version from DBINFO", null);
            if (c != null && c.moveToFirst())
                version = c.getString(0);

            return version;
        } finally {
            if (c != null)
                c.close();
        }
    }

    private void parseVersionElement(Element elemVer) throws Exception {
        if (null == elemVer)
            return;

        NodeList items = elemVer.getChildNodes();
        int count = items.getLength();
        for (int i = 0; i < count; i++) {
            Node item = items.item(i);
            if (!isValidElement(item)) {
                continue;
            }

            Element e = (Element) item;
            String elemName = item.getNodeName();
            if (E_SQL_EXEC.equalsIgnoreCase(elemName)) {
                parseExecSql(e);
            }
        }
    }

    private void parseExecSql(Element elemExecSql) throws Exception {
        if (null == elemExecSql)
            return;

        String sql = elemExecSql.getAttribute(ATTR_SQL);
        db.execSQL(sql);
    }

    public static boolean isValidElement(Node item) {
        return item != null && !TextUtils.isEmpty(item.getNodeName()) && item instanceof Element;
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }

    public static Context getContext() {
        return mInstance.getApplicationContext();
    }

}
