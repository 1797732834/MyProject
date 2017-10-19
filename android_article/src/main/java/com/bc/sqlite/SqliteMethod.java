package com.bc.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.bc.bean.User;

/**
 * Created by YuXinHan on 2017/10/14.
 */

public class SqliteMethod {
    private DbOpenHelper helper;
    private String user;
    private String password;

    public SqliteMethod(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        helper = new DbOpenHelper(context, name, factory, version);
    }


    public long add(User bean){
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put("user", bean.user);
        values.put("password", bean.password);

        long insert = db.insert("news", null, values);
        return insert;
    }
}
