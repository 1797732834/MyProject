package com.example.gson1612b;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MainActivity extends Activity {
Button btToJava,btToJson,btNet;
  TextView tx;
  Gson gson;
  String jsonArray = "[\"Android\",\"Java\",\"PHP\"]";
  String javaObject[];
  List<String> javaList;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    init();
  }
  void init(){
    gson = new Gson();
    btToJava = findViewById(R.id.button);
    btToJava.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        //把JSON字符串转换成指定的Java对象（数组、集合都可以）
        //生成数组
        javaObject = gson.fromJson(jsonArray,String[].class);
        //生成集合
//        javaList = gson.fromJson(jsonArray,new TypeToken<List<String>>(){}.getType());
//        遍历数组
 for(int i = 0;i<javaObject.length;i++){
          Log.d("java",javaObject[i]);
        }
        //遍历集合
//        for(int i = 0;i<javaList.size();i++){
//          Log.d("java","集合数据："+javaList.get(i));
//        }
      }
    });
    btToJson = findViewById(R.id.button2);
    btToJson.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
       //把指定的java对象转换成JSON字符串
//        String data = gson.toJson(javaList,new TypeToken<List<String>>(){}.getType());
//        String data = gson.toJson(javaObject,new TypeToken<String[]>(){}.getType());
        String data = gson.toJson(javaObject,String[].class);
        Log.d("json",data);
      }
    });
    btNet = findViewById(R.id.button3);
    btNet.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        new Thread(){
          public void run(){
            try {
              URL url = new URL("http://op.juhe.cn/robot/index?"
                  + "info=%E4%BD%A0%E5%A5%BD&dtype=&loc=&userid=&key=761522f85f516959958a310457c29735");
              HttpURLConnection con = (HttpURLConnection) url.openConnection();
              con.setRequestMethod("GET");
              con.setConnectTimeout(10000);
              con.setReadTimeout(10000);
              con.connect();
              if(con.getResponseCode() == 200){
                InputStream inputStream = con.getInputStream();
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                byte buffer[] = new byte[512];
                int length;
                while((length = inputStream.read(buffer))!=-1){
                  bs.write(buffer,0,length);
                  bs.flush();
                }
                final NetRobot netRobot = new Gson().fromJson(bs.toString(),NetRobot.class);
                runOnUiThread(new Thread(){
                  public void run(){
//                    tx.setText("解析的数据：\n"+netRobot.getReason()+"\n"+netRobot.getError_code()
//                    +"\n"+netRobot.getResult().getCode()+"\n"+netRobot.getResult().getText());
                  tx.setText("获取的数据：\n"+netRobot.toString());
                  }
                });
              }
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        }.start();
      }
    });
    tx = findViewById(R.id.textView);

  }
}
