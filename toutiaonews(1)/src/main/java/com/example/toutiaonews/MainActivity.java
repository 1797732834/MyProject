package com.example.toutiaonews;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends Activity implements OnClickListener {

  TextView txDefault, txGuoNei, txSheHui, txJunShi;
  ListView listView;
  MyAdapter adapter;
  ArrayList<HashMap<String,Object>> alist = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    init();
  }

  void init() {
    txDefault = findViewById(R.id.tx_default);
    txDefault.setGravity(Gravity.CENTER_HORIZONTAL);
    txDefault.setOnClickListener(this);
    txSheHui = findViewById(R.id.tx_shehui);
    txSheHui.setGravity(Gravity.CENTER_HORIZONTAL);
    txSheHui.setOnClickListener(this);
    txGuoNei = findViewById(R.id.tx_guonei);
    txGuoNei.setGravity(Gravity.CENTER_HORIZONTAL);
    txGuoNei.setOnClickListener(this);
    txJunShi = findViewById(R.id.tx_junshi);
    txJunShi.setGravity(Gravity.CENTER_HORIZONTAL);
    txJunShi.setOnClickListener(this);
    listView = findViewById(R.id.listview);
    listView.setOnItemClickListener(new OnItemClickListener() {
      // int  表示当前点击的ListView的item的位置ID
      //long  返回的是适配器的getItemId方法的返回值
      //adapterView代表当前适配器    可以获取当前Item所加载的数据
      //View表示当前视图  可以获取当前Item视图
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(MainActivity.this,ContentActivity.class);
//        Log.d("data",adapterView.getItemAtPosition(i).toString());
//        Toast.makeText(MainActivity.this, "int:"+i+",long"+l, Toast.LENGTH_SHORT).show();

//        TextView txTitle = view.findViewById(R.id.tx_title);
//        txTitle.setTextColor(Color.RED);


//用集合保存点击记录
// HashMap<String,Object> map = new HashMap<>();
//        map.put("position",i);
//        map.put("isClick",true);
//        if(!alist.contains(map)){//判断当前集合中是否含有要存入的map集合
//          alist.add(map);
//          adapter.updateTextColor(alist);
//        }
//

        intent.putExtra("url",
      ((News.ResultBean.DataBean)adapterView.getItemAtPosition(i)).getUrl());
//        Bundle b = new Bundle();
//        b.putString("dd","ddd");
//        intent.putExtras(b);
        startActivity(intent);
        ((News.ResultBean.DataBean)adapterView.getItemAtPosition(i)).setClick(true);

      }
    });
    new Thread(){
      public void run(){
        getDataByType("");
      }
    }.start();
  }

  @Override
  protected void onRestart() {
    super.onRestart();
    adapter.notifyDataSetChanged();
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
//        News news = new Gson().fromJson(bs.toString(),News.class);
        News news = new Gson().fromJson(bs.toString(), new TypeToken<News>() {
        }.getType());
        adapter = new MyAdapter(MainActivity.this, news,alist);
        runOnUiThread(new Thread() {
          public void run() {
            listView.setAdapter(adapter);
          }
        });
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Override
  public void onClick(View view) {
    switch(view.getId()){
      case R.id.tx_default:
        new Thread(){
          public void run(){
            getDataByType("");
          }
        }.start();
        alist.clear();//清空集合
        break;
      case R.id.tx_shehui:
        new Thread(){
          public void run(){
            getDataByType("shehui");
          }
        }.start();
        alist.clear();//清空集合
        break;
      case R.id.tx_guonei:
        new Thread(){
          public void run(){
            getDataByType("guonei");
          }
        }.start();
        alist.clear();//清空集合
        break;
      case R.id.tx_junshi:
        new Thread(){
          public void run(){
            getDataByType("junshi");
          }
        }.start();
        alist.clear();//清空集合
        break;
    }
  }
}
