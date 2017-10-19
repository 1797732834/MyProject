package com.example.tianqitong.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tianqitong.R;
import com.example.tianqitong.dao.UserDao;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Administrator on 2017/6/5 0005.
 */

public class Login extends Activity {
    EditText et_un,et_pwd;
    Button login,reg;
    CheckBox rem,auto;
    ImageView user_iv;
    String name,pwd;
    UserDao dao;
    boolean isExist;

    SharedPreferences userInfo;
    SharedPreferences.Editor editor;
    ProgressDialog dialog;

    Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            if(msg.what == 0x111){
                dialog.dismiss();
                Intent intent = new Intent();
                intent.putExtra("username", name);
                intent.putExtra("img", R.mipmap.user_iv);
//				发送数据
                setResult(Activity.RESULT_OK, intent);
                //关闭当前页面
                finish();
            }
        };
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }
    void init(){
        dao = new UserDao(this);
        et_pwd = (EditText) findViewById(R.id.login_et_password);
        et_un = (EditText) findViewById(R.id.login_et_username);
        login = (Button) findViewById(R.id.login_button);
        reg = (Button) findViewById(R.id.login_register_button);
        rem = (CheckBox) findViewById(R.id.login_rem_checkBox);
        auto = (CheckBox) findViewById(R.id.login_auto_checkBox);
        user_iv = (ImageView) findViewById(R.id.user_img);
        userInfo = getSharedPreferences("info", MODE_PRIVATE);
        editor = userInfo.edit();

        //		给记住密码设置监听
        rem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    editor.putBoolean("isRem", false);
                    editor.commit();
                }
            }
        });
//		给自动登录设置监听
        auto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if (!isChecked) {
                    editor.putBoolean("isAuto", false);
                    editor.commit();
                }else{
                    rem.setChecked(true);
                }
            }
        });

        //		回显操作
        if(userInfo.getBoolean("isAuto", false)) {
            auto.setChecked(true);
            rem.setChecked(true);
            name = userInfo.getString("name", "");
            pwd = userInfo.getString("pwd", "");
            et_un.setText(name);
            et_pwd.setText(pwd);
            dialog = new ProgressDialog(Login.this);
            dialog.setTitle("正在登录");
            dialog.setIcon(R.mipmap.ic_launcher);
            dialog.setCanceledOnTouchOutside(true);
            dialog.setCancelable(true);
            dialog.show();

            Timer timer = new Timer();
            timer.schedule(new TimerTask(){
                @Override
                public void run() {
                    handler.sendEmptyMessage(0x111);
                }
            }, 2000);

        }else if(userInfo.getBoolean("isRem", false)){
            rem.setChecked(true);
            et_un.setText(userInfo.getString("name", ""));
            et_pwd.setText(userInfo.getString("pwd", ""));
        }

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Login.this,Register.class),200);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = et_un.getText().toString();
                pwd = et_pwd.getText().toString();
                isExist = dao.selectUserByName(name);
                if (isExist) {
                    String password = dao.selectPwdByName(name);
                    if (pwd.equals(password)) {
                        if (auto.isChecked()) {
                            editor.putString("name", name);
                            editor.putString("pwd", pwd);
                            editor.putBoolean("isRem", true);
                            editor.putBoolean("isAuto", true);
                            editor.commit();
                        }else if(rem.isChecked()){
                            editor.putString("name", name);
                            editor.putString("pwd", pwd);
                            editor.putBoolean("isRem", true);
                            editor.commit();
                        }
                        Intent intent = new Intent();
                        intent.putExtra("username", name);
                        intent.putExtra("img", R.mipmap.user_iv);
//						发送数据
                        setResult(Activity.RESULT_OK, intent);
                        //关闭当前页面
                        finish();
                    }else{
                        Toast.makeText(Login.this, "密码错误", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(Login.this, "账号输入错误，该用户不存在", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 200) {
            name = data.getStringExtra("name");
            pwd = data.getStringExtra("pwd");
            et_un.setText(name);
        }
    }
}
