package com.beicai.da.mainmusic;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.beicai.da.bean.Users;
import com.beicai.da.dao.MyUserSqlite;


public class ZhuCeAActivity extends Activity {


	private EditText ed_username;
	private EditText ed_psw1;
	private EditText ed_psw2;
	private ImageButton imb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zhuce);
		ed_username = (EditText) findViewById(R.id.edit_username);
		ed_psw1 = (EditText) findViewById(R.id.edit_password1);
		ed_psw2 = (EditText) findViewById(R.id.edit_password2);
		imb = (ImageButton) findViewById(R.id.zhuce_ImageButton);

		imb.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String name = ed_username.getText().toString().trim();
				String psw1 = ed_psw2.getText().toString().trim();
				String psw = ed_psw1.getText().toString().trim();
				if (!psw.equals(psw1)) {
					Toast.makeText(getApplicationContext(), "两次密码不相同！", Toast.LENGTH_LONG).show();
				}else{
					if (name != null && psw != null && psw1 != null) {

						//数据库
						MyUserSqlite op= new MyUserSqlite(getApplicationContext(),"user",null,1);
						Users users = new Users();
						users.name=name;
						users.password=psw;
						boolean query = op.query(users);
						if(query==false){
							long add = op.add(users);
							System.out.println(String.valueOf(add));
							if(String.valueOf(add)==null){
								Toast.makeText(getApplicationContext(),"注册失败",Toast.LENGTH_SHORT).show();
							}else{
								Toast.makeText(getApplicationContext(),"注册成功",Toast.LENGTH_LONG).show();
								Intent intent = new Intent(getApplicationContext(),DengLuBActivity.class);
								startActivity(intent);
							}
						}else{
							Toast.makeText(getApplicationContext(), "该用户已存在",Toast.LENGTH_LONG).show();
						}

					}else{
						Toast.makeText(getApplicationContext(),"用户名和密码不能为空", Toast.LENGTH_LONG).show();
					}
				}


			}
		});

	}
}
