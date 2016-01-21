package zsbpj.lccpj.sql.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.List;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;
import zsbpj.lccpj.frame.FrameManager;
import zsbpj.lccpj.sql.DaoMaster;
import zsbpj.lccpj.sql.DaoSession;
import zsbpj.lccpj.sql.dao.IDatabase;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    2015年12月15日10:47:52
 * Description:    DatabaseManager
 */
public abstract   class DatabaseManager <M,K> implements IDatabase<M,K>{

    private static final String DEFAULT_DATABASE_NAME="xx.db";

    private static DaoMaster.DevOpenHelper mHelper;
    private static DaoSession daoSession;
    private static SQLiteDatabase database;
    private static DaoMaster daoMaster;
    protected String dbName;
    private Context context;

    /**
     * 默认的构造函数
     */
    public DatabaseManager(){
        this.context= FrameManager.getAppContext();
        this.dbName=DEFAULT_DATABASE_NAME;
        getOpenHelper(context,dbName);
    }

    public DatabaseManager(@NonNull String dataBaseName){
        this.context= FrameManager.getAppContext();
        this.dbName=dataBaseName;
        getOpenHelper(context,dbName);
    }

    /**
     * 打开数据库
     */
    protected void openReadableDB()throws SQLiteException{

    }

    /**
     * 获取WritableDatabase
     */
    protected SQLiteDatabase getWritableDatabase(){
        database=getOpenHelper(context,dbName).getWritableDatabase();
        return database;
    }

    /**
     * 获取ReadableDatabase
     */
    protected SQLiteDatabase getReadableDatabase(){
        database=getOpenHelper(context,dbName).getReadableDatabase();
        return database;
    }

    /**
     * 获取DevOpenHelper
     * @param context 上下文对象
     * @param dataBaseName 数据库的名字
     * @return DevOpenHelper
     */
    protected  DaoMaster.DevOpenHelper getOpenHelper(@NonNull Context context,@NonNull String dataBaseName){
        if (mHelper==null){
            mHelper=new DaoMaster.DevOpenHelper(context,dataBaseName,null);
        }
        return mHelper;
    }
    @Override
    public boolean insert(M m) {
        return false;
    }

    @Override
    public boolean delete(M m) {
        return false;
    }

    @Override
    public boolean deleteByKey(M key) {
        return false;
    }

    @Override
    public boolean deleteByKeyInTex(K... key) {
        return false;
    }

    @Override
    public boolean deleteAll() {
        return false;
    }

    @Override
    public boolean insertOrReplace() {
        return false;
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public boolean updateInTx(M... m) {
        return false;
    }

    @Override
    public boolean updateList(List<M> mList) {
        return false;
    }

    @Override
    public M selectByPrimaryKey(K key) {
        return null;
    }

    @Override
    public List<M> loadAll() {
        return null;
    }

    @Override
    public boolean refresh(M m) {
        return false;
    }

    @Override
    public void closeDbConnections() {

    }

    @Override
    public boolean dropDataBase() {
        return false;
    }

    @Override
    public void runInTx(Runnable runnable) {

    }

    @Override
    public AbstractDao<M, K> getAbstractDao() {
        return null;
    }

    @Override
    public boolean insertList(List<M> mList) {
        return false;
    }

    @Override
    public boolean insertOrReplaceList(List<M> mList) {
        return false;
    }

    @Override
    public QueryBuilder<M> getQueryBuilder() {
        return null;
    }

    @Override
    public List<M> queryRaw(String where, String... selectionArg) {
        return null;
    }

    @Override
    public Query<M> queryRawCreateListArgs(String where, Collection<Object> selectionArg) {
        return null;
    }
}
