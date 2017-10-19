package com.example.tianqitong.dao;


import android.content.Context;
import android.database.Cursor;

import com.example.tianqitong.helper.HelperForUser;


public class UserDao {

	private HelperForUser helper;

	public UserDao(Context context) {
		helper = new HelperForUser(context);
	}
	//	注册用户
	public void register(String name, String pwd){
		if(!selectUserByName(name)){
			String sql = "insert into userInfo (name,pwd) values(?,?)";
			helper.db.execSQL(sql,new String[]{name,pwd});
		}
	}

	//	根据用户账号查找用户
	public boolean selectUserByName(String name){
		boolean flag = false;
		String sql = "select * from userInfo where name = ?";
		Cursor c = helper.db.rawQuery(sql, new String[]{name});
		if (c.getCount()>0) {
			flag = true;
		}
		return flag;
	}
	//	根据账号查找密码��
	public String selectPwdByName(String name){
		String sql = "select * from userInfo where name = ?";
		Cursor c = helper.db.rawQuery(sql, new String[]{name});
		c.moveToFirst();
		String pwd = c.getString(c.getColumnIndex("pwd"));
		return pwd;
	}
}
