package com.example.toutiaonews;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ListView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends Activity {

  TextView txDefault, txGuoNei, txSheHui, txJunShi;
  ListView listView;
  MyAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    init();
  }

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
    new Thread(){
      public void run(){
        getDataByType("");
      }
    }.start();
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
        adapter = new MyAdapter(MainActivity.this, news);
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
}
