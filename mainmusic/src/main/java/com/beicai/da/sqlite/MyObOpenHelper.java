package com.beicai.da.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/*
 * 1、建立一个类继承SqliteOpenHelper，声明一个构造方法，参数包括(Context,name,.CursorFactory,version)；
   2、实现其oncreate() 创建表，实现.onupgrade() 版本号更新时执行；
   3、实例化所建类对象，对其进行读写操作，执行sql语句创建数据库（当对其使用读写时会判断数据库是否存在，如果存在，则不执行oncreate，不存在则执行）。
 */
public class MyObOpenHelper extends SQLiteOpenHelper {

	public MyObOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	//创建表，表结构的初始化
	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		arg0.execSQL("create table user(_id integer primary key autoincrement,name varchar(20),psd varchar(20))");
	}

	//做表格升级
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
