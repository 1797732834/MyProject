package com.example.tianqitong.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.tianqitong.R;
import com.example.tianqitong.dao.CityDao;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Administrator on 2017/6/6 0006.
 */

public class NotificationActivity extends Activity {
    LinearLayout notify_ll;
    Switch notify_switch;
    TextView choice_city;
    CityDao dao;
    private ArrayList<HashMap<String, String>> list;
    private AlertDialog.Builder dialog;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    private boolean isShow;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_notification);
        init();
    }
    void init(){
        notify_ll = (LinearLayout) findViewById(R.id.noti_notify);
        notify_switch = (Switch) findViewById(R.id.noti_notify_switch);
        choice_city = (TextView) findViewById(R.id.noti_notify_city);
        dao = new CityDao(this);
        list = dao.selectAll();
        choice_city.setText(list.get(0).get("city"));
        notify_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        sp = getSharedPreferences("state",MODE_PRIVATE);
        editor = sp.edit();
        isShow = sp.getBoolean("isShow", true);
        notify_switch.setChecked(isShow);
        notify_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    editor.putBoolean("isShow",true);
                    editor.commit();
                }else{
                    editor.putBoolean("isShow",false);
                    editor.commit();
                }
            }
        });

    }
    void showDialog(){
        dialog = new AlertDialog.Builder(NotificationActivity.this);
        dialog.setTitle("天气播报城市");
//        设置选项
        ArrayList<String> listCity = new ArrayList<>();
        for (int i=0;i<list.size();i++){
            String city = list.get(i).get("city");
            listCity.add(city);
        }
        final String[] citys = (String[]) listCity.toArray(new String[listCity.size()]);
        dialog.setSingleChoiceItems(citys, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                String choice = citys[i];
                choice_city.setText(choice);
            }
        });
        dialog.show();
    }

}
