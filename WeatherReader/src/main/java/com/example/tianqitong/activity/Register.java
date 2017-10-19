package com.example.tianqitong.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tianqitong.R;
import com.example.tianqitong.dao.UserDao;


/**
 * Created by Administrator on 2017/6/5 0005.
 */

public class Register extends Activity {
    EditText et_name,et_pwd1,et_pwd2;
    Button register;
    ImageView name_iv,pwd_iv;
    String name,pwd1,pwd2;
    UserDao dao;
    boolean isExist;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }
    void init(){
        dao = new UserDao(this);
        et_name = (EditText) findViewById(R.id.reg_et_username);
        et_pwd1 = (EditText) findViewById(R.id.reg_et_pwd1);
        et_pwd2 = (EditText) findViewById(R.id.reg_et_pwd2);
        register = (Button) findViewById(R.id.reg_button);
        name_iv = (ImageView) findViewById(R.id.reg_name_iv);
        pwd_iv = (ImageView) findViewById(R.id.reg_pwd2_iv);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = et_name.getText().toString();
                pwd2 = et_pwd2.getText().toString();
                if (name.length()<3) {
                    Toast.makeText(Register.this, "账号长度不能小于3位", Toast.LENGTH_LONG).show();
                    if (name.equals("") || name == null) {
                        Toast.makeText(Register.this, "账号不能位空", Toast.LENGTH_LONG).show();
                    }
                }else if(pwd1 == null || pwd1.equals("") || pwd2 == null || pwd2.equals("")){
                    Toast.makeText(Register.this, "密码不能为空", Toast.LENGTH_LONG).show();
                }else if(!(pwd1.equals(pwd2))){
                    Toast.makeText(Register.this, "两次密码不一致", Toast.LENGTH_LONG).show();
                }else if(isExist){
                    Toast.makeText(Register.this, "该账号已存在", Toast.LENGTH_LONG).show();
                }else {
                    dao.register(name, pwd2);
                    Toast.makeText(Register.this, "注册成功", Toast.LENGTH_LONG).show();
//					返回数据
                    Intent intent = new Intent();
                    intent.putExtra("name", name);
                    intent.putExtra("pwd", pwd2);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

        //		输入账号实现监听
        et_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                name = et_name.getText().toString();
                isExist = dao.selectUserByName(name);
                name_iv.setVisibility(View.VISIBLE);
                if (isExist) {
                    name_iv.setImageResource(R.mipmap.cuo);
                }else {
                    name_iv.setImageResource(R.mipmap.dui);
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });
//		再次输入密码实现监听
        et_pwd2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                pwd1 = et_pwd1.getText().toString();
                pwd2 = et_pwd2.getText().toString();
                pwd_iv.setVisibility(View.VISIBLE);
                if (pwd1.equals(pwd2)) {
                    pwd_iv.setImageResource(R.mipmap.dui);
                }else{
                    pwd_iv.setImageResource(R.mipmap.cuo);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

}
