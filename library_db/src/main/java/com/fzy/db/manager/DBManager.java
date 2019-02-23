package com.fzy.db.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.fzy.db.dao.DaoMaster;
import com.fzy.db.dao.DaoSession;

/**
 * Author: openXu
 * Time: 2019/2/23 17:26
 * class: DBManager
 * Description:
 */
public class DBManager {

    private final static String dbName = "fzy_zs119";
    private DaoMaster.DevOpenHelper openHelper;
    private volatile static DBManager instance;  //多线程访问
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private Context context;

    public static DBManager getInstance(){
        if(instance == null){
            synchronized (DBManager.class){
                if(instance == null)
                    instance = new DBManager();
            }
        }
        return instance;
    }

    public DBManager init(Context context){
        setDataBase(context);
        return this;
    }

    /**
     * 初始化数据库
     * @param context
     */
    private void setDataBase(Context context) {
        openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        db = openHelper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
