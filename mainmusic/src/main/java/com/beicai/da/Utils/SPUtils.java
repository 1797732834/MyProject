package com.beicai.da.Utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class SPUtils {

	public static void saveMusicPosition(int position , Context context){
		//创建sp对象mode文件模式ʽ
		SharedPreferences sp = context.getSharedPreferences("musicId", Context.MODE_PRIVATE);
		Editor edit = sp.edit();
		edit.putInt("position", position);
		edit.commit() ;
	}
	public static int getMusicPosition(Context context){

		SharedPreferences sp = context.getSharedPreferences("musicId", Context.MODE_PRIVATE);

		int position = sp.getInt("postion", 0);
		return position;
	}
}
