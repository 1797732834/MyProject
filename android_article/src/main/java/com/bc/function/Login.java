package com.bc.function;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bc.android_article.R;
import com.bc.homepage.Homepage;
import com.bc.sqlite.DbOpenHelper;
import com.bc.sqlite.SqliteMethod;

/**
 * Created by YuXinHan on 2017/10/14.
 */

public class Login extends AppCompatActivity {

    private EditText et1;
    private EditText et2;
    private String username;
    private SqliteMethod method;
    private String password;
    private String username1;
    private String password1;
    private DbOpenHelper helper;
    private Button login;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        login = (Button) findViewById(R.id.login);
        et1 = (EditText) findViewById(R.id.luser);
        et2 = (EditText) findViewById(R.id.lpassword);
        helper = new DbOpenHelper(this, "hyx", null, 3);
        method = new SqliteMethod(this, "hyx", null, 3);

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                username = et1.getText().toString().trim();
                password = et2.getText().toString().trim();
                SQLiteDatabase db = helper.getReadableDatabase();
                Cursor cursor = db.query("news", null, null, null, null, null,null);
                if (cursor !=null && cursor.getCount()>0) {
                    while (cursor.moveToNext()) {
                        username1 = cursor.getString(cursor.getColumnIndex("user"));
                        password1 = cursor.getString(cursor.getColumnIndex("password"));

                    }
                }
                if(username.equals(username1) && password.equals(password1)){
                    Toast.makeText(Login.this, "登陆成功",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, Homepage.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(Login.this, "用户名/密码不正确",Toast.LENGTH_SHORT).show();
                }
            }
        });







        }
}
