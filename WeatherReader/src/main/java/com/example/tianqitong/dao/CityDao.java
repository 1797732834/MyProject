package com.example.tianqitong.dao;

import android.content.Context;
import android.database.Cursor;

import com.example.tianqitong.helper.HelperForCity;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Administrator on 2017/6/5 0005.
 */

public class CityDao {
    HelperForCity helper;
    public CityDao(Context context) {
        helper = new HelperForCity(context);
    }

//    添加城市
    public void add(String city){
        if (!selectByCity(city)) {
            String sql = "insert into cityInfo (city) values(?)";
            helper.db.execSQL(sql, new String[]{city});
        }
    }
//    查询城市是否已经存在
    public boolean selectByCity(String city){
        boolean flag = false;
        String sql = "select * from cityInfo where city = ?";
        Cursor c = helper.db.rawQuery(sql, new String[]{city});
        c.moveToFirst();
        if (c.getCount() >0){
            flag = true;
        }
        return  flag;
    }
    public ArrayList<HashMap<String,String>> selectAll(){
        String sql = "select * from cityInfo";
        Cursor c = helper.db.rawQuery(sql,null);
        c.moveToPrevious();
        ArrayList<HashMap<String,String>> list = new ArrayList<>();
        if (c.getCount()>0) {
            while (c.moveToNext()) {
                HashMap<String,String> map = new HashMap<>();
                String city = c.getString(c.getColumnIndex("city"));
                int id = c.getInt(c.getColumnIndex("id"));
                map.put("city",city);
                map.put("id",id+"");
                list.add(map);
            }
        }
        return list;
    }
//    删除城市
    public void deleteCity(int id){
        String sql = "delete from cityInfo where id = ?";
        helper.db.execSQL(sql,new String[]{id+""});
    }
}
