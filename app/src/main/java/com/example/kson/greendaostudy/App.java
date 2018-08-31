package com.example.kson.greendaostudy;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.kson.greendaostudy.gen.DaoMaster;
import com.example.kson.greendaostudy.gen.DaoSession;

/**
 * Author:kson
 * E-mail:19655910@qq.com
 * Time:2018/08/31
 * Description:
 */
public class App extends Application {
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    public static App instances;

    @Override
    public void onCreate() {
        super.onCreate();
        initDb();

        instances = this;
    }

    public static App getInstances() {
        return instances;
    }

    /**
     * 初始化greendao
     */
    private void initDb() {
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        mHelper = new DaoMaster.DevOpenHelper(this, "jd.db", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();

    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
    public SQLiteDatabase getDb() {
        return db;
    }

}
