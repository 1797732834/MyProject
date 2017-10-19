package com.example.tianqitong.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.tianqitong.R;


/**
 * Created by Administrator on 2017/6/5 0005.
 */

public class Menu extends Activity {
    TextView reg_login,city_ma,noti;
    Switch bc_music;
    ImageView user_img;
    Button cancel;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    private boolean isPlay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        init();
    }
    void init(){
        bc_music = (Switch) findViewById(R.id.menu_bc_music);
        cancel = (Button) findViewById(R.id.menu_cancel);
        city_ma = (TextView) findViewById(R.id.menu_cityManager);
        noti = (TextView) findViewById(R.id.menu_notification);
        reg_login = (TextView) findViewById(R.id.menu_reg_login_tv);
        user_img = (ImageView) findViewById(R.id.user_img);

        reg_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(Menu.this,Login.class);
                startActivityForResult(intent,100);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_img.setImageResource(R.mipmap.user_default);
                reg_login.setText("注册/登录");
                cancel.setVisibility(View.GONE);
            }
        });
        city_ma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Menu.this,CityManager.class));
            }
        });
        sp = getSharedPreferences("state",MODE_PRIVATE);
        editor = sp.edit();
        isPlay = sp.getBoolean("isPlay", true);
        if (isPlay){
            bc_music.setChecked(true);
        }else{
            bc_music.setChecked(false);
        }
        bc_music.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    editor.putBoolean("isPlay",true);
                }else{
                    editor.putBoolean("isPlay",false);
                }
                editor.commit();
            }
        });

        noti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Menu.this,NotificationActivity.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 100){
            String username = data.getStringExtra("username");
            int img = data.getIntExtra("img",R.mipmap.user_iv);
            user_img.setImageResource(img);
            reg_login.setText(username);
            cancel.setVisibility(View.VISIBLE);
        }
    }
}
