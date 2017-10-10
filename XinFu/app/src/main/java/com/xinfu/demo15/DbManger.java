package com.xinfu.demo15;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/09/20
 *     desc   :
 * </pre>
 */
public class DbManger {
    private static MySqliteHelper mMySqliteHelper;

    public static MySqliteHelper getIntance(Context context) {
        if (mMySqliteHelper == null) {
            mMySqliteHelper = new MySqliteHelper(context);
        }
        return mMySqliteHelper;
    }

    public static void add(String name, String age) {
        //去重
        if (isHave(name)) {
            delete((name));
        }
        //删除第一条数据
        ArrayList<ProductInfo> productInfos = searchAllData();
        if (productInfos.size() >= 20) {
            Cursor c = ExecSQLForCursor("SELECT * FROM person");
            if (c.moveToFirst()) {
                String s = c.getString(c.getColumnIndex("name"));
                delete(s);
            }
        }
        //添加
        SQLiteDatabase db = mMySqliteHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("age", age);
        db.insert(Constant.TABLE_NAME, null, cv);
    }

    public static void delete(String name) {
        SQLiteDatabase db = mMySqliteHelper.getWritableDatabase();
        String whereClause = "name=?";
        String[] whereArgs = new String[]{name};
        db.delete("person", whereClause, whereArgs);
    }

    /**
     * 检查数据库是否有这条数据
     *
     * @param name
     * @return
     */
    private static boolean isHave(String name) {
        SQLiteDatabase db = mMySqliteHelper.getWritableDatabase();
        String[] columns = new String[]{"id", "name", "age"};
        String selection = "name=?";
        String[] selectionArgs = {name};
        String groupBy = null;
        String having = null;
        String orderBy = null;
        Cursor cursor = db.query("person", columns, selection, selectionArgs, groupBy, having, orderBy);
        if (cursor.moveToNext()) {
            return true;
        }
        return false;
    }

    /**
     * 查找所有
     *
     * @return
     */
    public static ArrayList<ProductInfo> searchAllData() {
        String sql = "SELECT * FROM person";
        return ExecSQLForMemberInfo(sql);
    }

    /**
     * 执行SQL命令返回list
     *
     * @param sql
     * @return
     */
    private static ArrayList<ProductInfo> ExecSQLForMemberInfo(String sql) {
        ArrayList<ProductInfo> list = new ArrayList<ProductInfo>();
        Cursor c = ExecSQLForCursor(sql);
        while (c.moveToNext()) {
            ProductInfo info = new ProductInfo();
            info.setProductId(c.getString(c.getColumnIndex("name")));
            info.setPrice(c.getString(c.getColumnIndex("age")));
            list.add(info);
        }
        c.close();
        return list;
    }

    /**
     * 执行SQL，返回一个游标
     *
     * @param sql
     * @return
     */
    private static Cursor ExecSQLForCursor(String sql) {
        SQLiteDatabase db = mMySqliteHelper.getWritableDatabase();
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

}