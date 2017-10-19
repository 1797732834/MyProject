package com.beicai.da.mainmusic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.beicai.da.Utils.UserSPF;
import com.beicai.da.bean.Users;
import com.beicai.da.dao.MyUserSqlite;

/**
 * Created by da on 2017/9/2.
 */

public class DengLuBActivity extends Activity {
    EditText user = null;
    EditText pswd = null;
    ImageButton imageButton_denglu = null;
    Context context = null;
    public boolean flag;
    private ImageButton imageButton_zhuce;

    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.longdenglub);

        user = (EditText) findViewById(R.id.edittext1);
        pswd = (EditText) findViewById(R.id.edittext2);
        imageButton_denglu = (ImageButton) findViewById(R.id.deng_ImButton);
        imageButton_zhuce = (ImageButton) findViewById(R.id.zhu_ImButton);
        context = DengLuBActivity.this;
//		每次登陆的时候，首先判断有没有Sharepreferences对象，没有的话就是第一次登陆或者是上次登陆没有保存用户信息
//		有的话就把用户信息取出来
        Users users = UserSPF.getUsers(this);
        if(users!=null){
//			把userInfo的用户名和密码取出来，展示到输入框中
            user.setText(users.name);
            pswd.setText(users.password);
        }


        imageButton_denglu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String username  = user.getText().toString().trim();
                String password  = pswd.getText().toString().trim();
                //				String username = findViewById(R.id.edittext1).getText()
                //				String password = findViewById(R.id.edittext2).getT
                if(username != null &&password != null){
                    flag = true;
                    Users users = new Users();
                    users.name=username;
                    users.password=password;
                    UserSPF.saveUsers(DengLuBActivity.this,users);


                    //查询数据库
                    MyUserSqlite op = new MyUserSqlite(getApplicationContext(),"user",null,1);
                    boolean query = op.query(users);
                    if(query==true){
                        Toast.makeText(getApplicationContext(),"登录成功",Toast.LENGTH_SHORT).show();
                        // TODO Auto-generated method stub
                        Intent intent = new Intent(getApplicationContext(), IndexActivity.class);
                        startActivity(intent);

                    }else{
                        Toast.makeText(getApplicationContext(),"用户不存在",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"用户名和密码不能为空", Toast.LENGTH_LONG).show();
                }

            }


        });

        imageButton_zhuce.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(getApplicationContext(),ZhuCeAActivity.class);
                startActivity(intent);
            }
        });

    }
}
