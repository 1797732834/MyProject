package com.bc.homepage;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import android.widget.AdapterView;

import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bc.android_article.R;
import com.bc.function.Login;
import com.bc.function.Regist;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by YuXinHan on 2017/9/5.
 */

public class Homepage extends Activity {
    TextView txDefault, txGuoNei, txSheHui, txJunShi;
    ListView listView;
    MyAdapter adapter;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        navigationView = (NavigationView) findViewById(R.id.navigation);
        init();
        glissade();
        //申请权限
        setPermissions();
    }

    static final String[] PERMISSION = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,// 写入权限
            Manifest.permission.READ_EXTERNAL_STORAGE,  //读取权限
            Manifest.permission.INTERNET   ,    //读取设备信息
            Manifest.permission.ACCESS_NETWORK_STATE,//接入网络状态
            Manifest.permission.ACCESS_WIFI_STATE//接入WIFI状态
    };

    void init() {
        txDefault = findViewById(R.id.tx_default);
//    txDefault.setGravity(Gravity.CENTER_HORIZONTAL);
        txSheHui = findViewById(R.id.tx_shehui);
//    txSheHui.setGravity(Gravity.CENTER_HORIZONTAL);
        txGuoNei = findViewById(R.id.tx_guonei);
//    txGuoNei.setGravity(Gravity.CENTER_HORIZONTAL);
        txJunShi = findViewById(R.id.tx_junshi);
//    txJunShi.setGravity(Gravity.CENTER_HORIZONTAL);
        listView = findViewById(R.id.listview);


        new Thread() {
            public void run() {
                getDataByType("");
            }
        }.start();
        txDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread() {
                    public void run() {
                        getDataByType("toutiao");
                    }
                }.start();
            }
        });
        txSheHui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread() {
                    public void run() {
                        getDataByType("guoji");
                    }
                }.start();
            }
        });
        txGuoNei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread() {
                    public void run() {
                        getDataByType("guonei");
                    }
                }.start();
            }
        });
        txJunShi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread() {
                    public void run() {
                        getDataByType("shehui");
                    }
                }.start();
            }
        });


    }

    void getDataByType(String type) {
        try {
            URL url = new URL("http://v.juhe.cn/toutiao/index?"
                    + "type=" + type + "&key=2ca3a5b1cb6edf55250bff550ac34325");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(10000);
            con.setReadTimeout(10000);
            con.connect();

            if (con.getResponseCode() == 200) {
                InputStream is = con.getInputStream();
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                byte[] buffer = new byte[512];
                int length;
                while ((length = is.read(buffer)) != -1) {
                    bs.write(buffer, 0, length);
                    bs.flush();
                }

                final News news = new Gson().fromJson(bs.toString(), new TypeToken<News>() {
                }.getType());
                adapter = new MyAdapter(Homepage.this, news);
                runOnUiThread(new Thread() {
                    public void run() {
                        listView.setAdapter(adapter);
                    }
                });
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(Homepage.this, Item.class);
                        intent.putExtra("url", news.getResult().getData().get(i).getUrl());
                        startActivity(intent);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

            //侧拉的方法
                void glissade(){
                view = navigationView.getHeaderView(0);
                drawerLayout = findViewById(R.id.drawerLayout);
                drawerLayout.openDrawer(Gravity.LEFT);


              Button login = view.findViewById(R.id.login);
              Button regist = view.findViewById(R.id.regist);
                    //登陆的页面
             login.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                        Intent intent = new Intent(Homepage.this,Login.class);
                     startActivity(intent);
                 }
             });
                    //注册的页面
                    regist.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(Homepage.this,Regist.class);
                            startActivity(intent);
                        }
                    });
                    navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            switch (item.getItemId()){
                                case R.id.sousuo:
                                    Toast.makeText(Homepage.this, "搜索", Toast.LENGTH_SHORT).show();
                                    break;
                                case R.id.shoucang:
                                    Toast.makeText(Homepage.this, "收藏", Toast.LENGTH_SHORT).show();
                                    break;
                                case R.id.tongzhi:
                                    Toast.makeText(Homepage.this, "通知", Toast.LENGTH_SHORT).show();
                                    break;
                                case R.id.huodong:
                                    Toast.makeText(Homepage.this, "活动", Toast.LENGTH_SHORT).show();
                                    break;
                                case R.id.fankui:
                                    Toast.makeText(Homepage.this, "反馈", Toast.LENGTH_SHORT).show();
                                    drawerLayout.closeDrawers();
                                    break;
                            }
                            return false;
                        }
                    });
        }

        //登陆
//    public void login(){
//        Intent intent = new Intent(Homepage.this,Login.class);
//        startActivity(intent);
//        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//    }
//       //注册
//    public void regist(){
//        Intent intent = new Intent(Homepage.this,Regist.class);
//        startActivity(intent);
//        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//    }
        private void setPermissions() {
            if (ContextCompat.checkSelfPermission(Homepage.this,
                    PERMISSION[0]) != PackageManager.PERMISSION_GRANTED) {
                Log.d("data", "权限申请");
                Toast.makeText(this, "申请权限", Toast.LENGTH_SHORT).show();
                //Android 6.0申请权限
                ActivityCompat.requestPermissions(this, PERMISSION, 1);
            }
        }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == 1) {
            if (ContextCompat.checkSelfPermission(Homepage.this,
                    PERMISSION[0]) == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "写入申请成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "写入申请失败", Toast.LENGTH_SHORT).show();
            }
            if (ContextCompat.checkSelfPermission(Homepage.this,
                    PERMISSION[1]) == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "读取申请成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "读取申请失败", Toast.LENGTH_SHORT).show();
            }
            if (ContextCompat.checkSelfPermission(Homepage.this,
                    PERMISSION[2]) == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "联网申请成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "联网申请失败", Toast.LENGTH_SHORT).show();
            }
            if (ContextCompat.checkSelfPermission(Homepage.this,
                    PERMISSION[3]) == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "接入网络成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "接入网络失败", Toast.LENGTH_SHORT).show();
            }
            if (ContextCompat.checkSelfPermission(Homepage.this,
                    PERMISSION[4]) == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "网络状态成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "网络状态失败", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
