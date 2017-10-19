package com.example.tianqitong.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.tianqitong.R;
import com.example.tianqitong.adapter.CityManagerListViewAdapter;
import com.example.tianqitong.dao.CityDao;

import java.util.ArrayList;
import java.util.HashMap;



public class CityManager extends Activity {
    ListView lv;
    CityDao dao;
    ArrayList<HashMap<String, String>> list;
    private CityManagerListViewAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    void init(){
        lv = (ListView) findViewById(R.id.city_manager_lv);
        dao = new CityDao(this);
        list = dao.selectAll();
        adapter = new CityManagerListViewAdapter(this, list);
        lv.setAdapter(adapter);
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, final long l) {
//                设置弹出对话框
                setContentView(R.layout.activity_city_manager);
                init();
                AlertDialog.Builder dialog = new AlertDialog.Builder(CityManager.this);
                dialog.setTitle("确认");
                dialog.setMessage("是否要删除");
                dialog.setIcon(R.mipmap.ic_launcher);
                dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("position...",l+"");
                        dao.deleteCity((int) l);
                        list = dao.selectAll();
                        adapter.updateAdapter(list);
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialog.show();
                return true;
            }
        });
    }
}
