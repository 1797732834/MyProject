package com.beicai.da.Utils;



import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.beicai.da.bean.Users;


public class UserSPF {

	public static void saveUsers(Context context, Users us){
		
		if(us!=null){}
		@SuppressWarnings("deprecation")
		SharedPreferences sp = context.getSharedPreferences("userinfo",Context.MODE_PRIVATE);
		
		Editor edit = sp.edit();
		edit.putString("name",us.name);
		edit.putString("psd",us.password);
		edit.commit();
	}
	
	public static Users getUsers(Context context){
		@SuppressWarnings("deprecation")
		SharedPreferences sp = context.getSharedPreferences("userinfo",Context.MODE_PRIVATE);
		String name = sp.getString("name","admin");
		String psd = sp.getString("psd","admin");
		
		Users us = new Users();
		us.name=name;
		us.password=psd;
		return us;
	}
}
