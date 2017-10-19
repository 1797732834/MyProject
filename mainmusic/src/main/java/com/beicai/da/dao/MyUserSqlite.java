package com.beicai.da.dao;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import com.beicai.da.bean.Users;
import com.beicai.da.sqlite.MyObOpenHelper;


public class MyUserSqlite {


	private MyObOpenHelper helper;

	public MyUserSqlite(Context context,String name,CursorFactory factory,int version) {
		helper= new MyObOpenHelper(context, name, factory, version);
	}
	
	public long add(Users us){
		//1.打开数据库
//		SQLiteDatabase db = helper.getReadableDatabase();
//		//2.执行sql
//		db.execSQL("",new String[]{});
//
//		db.close();

		//使用封装好的方法
		SQLiteDatabase db = helper.getReadableDatabase();
		
		ContentValues values = new ContentValues();
		values.put("name",us.name);
		values.put("psd",us.password);
		long insert= db.insert("user", null, values);
		
		return insert;
	}
	
	public int delete(Users u){
//		SQLiteDatabase db = helper.getReadableDatabase();
//		db.execSQL("",new String[]{});
//		db.close();
		
		SQLiteDatabase db = helper.getReadableDatabase();
		int num = db.delete("user","name=?",new String[]{u.name});
		return num;
		
	}
	
	public int update(Users u){
		SQLiteDatabase db = helper.getReadableDatabase();
		
		ContentValues values = new ContentValues();
		values.put("psd",u.password);
		int update = db.update("user", values,"_id=?",new String[]{u.id.toString()});
		return update;
	}
	
	public boolean query(Users us){
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from user  where name=? and psd=?",new String[]{us.name,us.password});
		//Cursor cursor =db.query("user",null,null,null,null,null,null);
		
		if(cursor!=null&&cursor.getCount()>0){
			if(cursor.moveToNext()==true){
				cursor.close();
				db.close();
				return true;
			}
			cursor.close();
			db.close();
			return false;
		}else{
			cursor.close();
			db.close();
			return false;
		}

	}

}
