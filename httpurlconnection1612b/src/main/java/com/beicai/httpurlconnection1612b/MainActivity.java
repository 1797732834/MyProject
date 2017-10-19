package com.beicai.httpurlconnection1612b;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends Activity {

  Button bt;
  TextView tx;
  String data;
  Handler handler = new Handler() {
    @Override
    public void handleMessage(Message msg) {
      if (msg.what == 1) {
        tx.setText("获取的数据：\n" + data);
      }
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    init();
  }

  void init() {
    bt = findViewById(R.id.button);
    bt.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        new Thread() {
          public void run() {
            //安卓4.0以后要求必须在子线程中联网
            try {
              //通过地址对象封装网址
//              URL url = new URL("http://www.baidu.com");
              //GET联网方式 将网址和数据以？拼接起来
//              URL url = new URL("http://v.juhe.cn/toutiao/index"+"?"+
//                  "type="+"shishang"+"&key=2ca3a5b1cb6edf55250bff550ac34325");
              //POST请求  URL只封装服务器接口地址 不显示提交的数据
              //聚合
//              URL url = new URL("http://v.juhe.cn/toutiao/index");
              //mob
              URL url = new URL("http://apicloud.mob.com/v1/weather/query");
              //通过地址对象创建网络连接对象
              HttpURLConnection con = (HttpURLConnection) url.openConnection();
              //设置联网属性 GET  POST要大写
              con.setRequestMethod("POST");
              //设置请求超时
              con.setConnectTimeout(15000);
              //设置读取超时
              con.setReadTimeout(15000);
              //开始连接
              con.connect();
              OutputStream outputStream = con.getOutputStream();
              //聚合
//              outputStream.write(("type="+"shehui"+"&key=2ca3a5b1cb6edf55250bff550ac34325").getBytes("UTF-8"));
             //mob
              outputStream.write(("province=%E6%B1%9F%E8%8B%8F&key=1f8663e1f2868&city=%E5%8D%97%E4%BA%AC").getBytes("UTF-8"));
              //判断请求是否正确
              if (con.getResponseCode() == 200) {//Requst请求     Response  响应
                InputStream inputStream = con.getInputStream();
                //内存中缓存数据的流
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                //循环读取数据的工具
                byte[] buffer = new byte[512];
                int length;//标记每次读取数据的长度
                while ((length = inputStream.read(buffer)) != -1) {
                  byteArrayOutputStream.write(buffer, 0, length);
                  byteArrayOutputStream.flush();
                }
                inputStream.close();
                byteArrayOutputStream.close();
                data = byteArrayOutputStream.toString();
                Log.d("data", data);
                //handler发送消息的方式  1、空消息  2、非空消息
                handler.sendEmptyMessage(1);
                //非空消息
//                Message msg = handler.obtainMessage();// 从消息池中取出可以循环利用的消息对象  new Message()
//                msg.what = 0x112;
//                msg.obj = data;
//                handler.sendMessage(msg);
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
