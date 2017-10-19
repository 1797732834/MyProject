package com.bc.function;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bc.android_article.R;
import com.bc.bean.User;
import com.bc.homepage.Homepage;
import com.bc.sqlite.DbOpenHelper;
import com.bc.sqlite.SqliteMethod;

/**
 * Created by YuXinHan on 2017/10/14.
 */

public class Regist extends AppCompatActivity {
    private SqliteMethod method;
    private User bean;
    private String username;
    private String password;
    private EditText et1;
    private EditText et2;
    private DbOpenHelper helper;


//    private Button login;
//    private Button regist;
//    private User bean;
//    private SqliteMethod method;
//    private String username;
//    private String password;
//    private DbOpenHelper helper;
//    private String username1;
//    private String password1;
//    private EditText et1;
//    private EditText et2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist);
        Button regist = (Button) findViewById(R.id.regist);
        et1 = (EditText) findViewById(R.id.ruser);
        et2 = (EditText) findViewById(R.id.rpassword);

        bean = new User();
        helper = new DbOpenHelper(this, "hyx", null, 3);
        method = new SqliteMethod(this, "hyx", null, 3);


        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = et1.getText().toString().trim();
                password = et2.getText().toString().trim();
                bean.user= username;
                bean.password= password;
                long add = method.add(bean);
                if (add > 0 ) {
                    Toast.makeText(getApplicationContext(), "注册成功"+bean.user+bean.password, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Regist.this, Homepage.class);
                    startActivity(intent);
                }
            }
        });

    }
}
