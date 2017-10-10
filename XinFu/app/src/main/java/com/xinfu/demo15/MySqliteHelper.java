package com.xinfu.demo15;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/09/20
 *     desc   :
 * </pre>
 */
public class MySqliteHelper extends SQLiteOpenHelper {
    public MySqliteHelper(Context context) {
        super(context, Constant.DATABASE_NAME, null, Constant.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //创建表结构
        String sql = "create table " + Constant.TABLE_NAME + "(" +
                Constant.ID + " integer primary key autoincrement," +
                Constant.NAME + " varchar(255)," +
                Constant.AGE + " integer)";
        sqLiteDatabase.execSQL(sql);//执行sql语句

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}