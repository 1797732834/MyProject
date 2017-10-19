package com.example.mynohttp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.yanzhenjie.nohttp.Headers;
import com.yanzhenjie.nohttp.InitializationConfig;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.download.DownloadListener;
import com.yanzhenjie.nohttp.download.DownloadQueue;
import com.yanzhenjie.nohttp.download.DownloadRequest;
import com.yanzhenjie.nohttp.rest.CacheMode;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.security.Permission;

public class MainActivity extends AppCompatActivity {
Button btNet,btDown;
  TextView tx;
  ImageView ig;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    setPermissions();
    init();
    //设置打印错误日志  不设置 NoHttp相应的错误不会打印
    Logger.setDebug(true);
//    InitializationConfig initializationConfig =InitializationConfig.newBuilder(this)
//        .readTimeout(20000)
//        .connectionTimeout(20000)
//        .build();
    NoHttp.initialize(this);
  }
  public void init(){
    btNet = (Button) findViewById(R.id.btNet);
    btNet.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
//        netWorkByNoHttpToString();
        netWorkByNoHttpToBitmap();
      }
    });
    btDown = (Button) findViewById(R.id.btDown);
    btDown.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        netWorkByNoHttpDownLoad();
      }
    });
    tx = (TextView) findViewById(R.id.textView);
    ig = (ImageView) findViewById(R.id.imageView);
  }

  public void netWorkByNoHttpToString(){
    String url="http://v.juhe.cn/toutiao/index";
    //创建请求对象 设置泛型
    Request<String>  request = NoHttp.createStringRequest(url,
        RequestMethod.POST);
    request.add("type","");
    request.add("key","2ca3a5b1cb6edf55250bff550ac34325");
    request.setCacheMode(CacheMode.REQUEST_NETWORK_FAILED_READ_CACHE);
    //创建请求队列，添加到队列中
    RequestQueue requestQueue = NoHttp.newRequestQueue();
    requestQueue.add(0, request, new OnResponseListener<String>() {
      @Override
      public void onStart(int what) {

      }

      @Override
      public void onSucceed(int what, Response<String> response) {
            tx.setText(response.get());
      }

      @Override
      public void onFailed(int what, Response<String> response) {

      }

      @Override
      public void onFinish(int what) {

      }
    });

  }
  public void netWorkByNoHttpToBitmap()  {

    String url="http://08.imgmini.eastday.com//mobile//20170925//20170925150134_77ae353e7f15f1fecc294dec794238b3_3_mwpm_03200403.jpg";
    //创建请求对象 设置泛型
    Request<Bitmap>  request = NoHttp.createImageRequest(url,
        RequestMethod.GET);
    //创建请求队列，添加到队列中
    RequestQueue requestQueue = NoHttp.newRequestQueue();
    requestQueue.add(1, request, new OnResponseListener<Bitmap>() {
      @Override
      public void onStart(int what) {
        Log.d("data","开始");
      }

      @Override
      public void onSucceed(int what, Response<Bitmap> response) {
        Log.d("data","成功");
        ig.setImageBitmap(response.get());
      }

      @Override
      public void onFailed(int what, Response<Bitmap> response) {
        Log.d("data","失败");
      }

      @Override
      public void onFinish(int what) {
        Log.d("data","结束");
      }
    });

  }
  static final String[] PERMISSION = new String[]{
      Manifest.permission.WRITE_EXTERNAL_STORAGE,// 写入权限
      Manifest.permission.READ_EXTERNAL_STORAGE,  //读取权限
      Manifest.permission.INTERNET   ,    //读取设备信息
      Manifest.permission.ACCESS_NETWORK_STATE,//接入网络状态
      Manifest.permission.ACCESS_WIFI_STATE//接入WIFI状态
  };


  public void netWorkByNoHttpDownLoad(){
//    File file = new File(Environment.getExternalStorageDirectory()+"/dsdas.jpg");
//    FileOutputStream os = null;
//    try {
//      os = new FileOutputStream(file);
//      os.write("dsadasdasd".getBytes());
//      os.flush();
//      os.close();
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
    //下载 需要创建特定的请求对象
    DownloadRequest request = NoHttp.createDownloadRequest(
        "http://08.imgmini.eastday.com//mobile//20170925//20170925150134_77ae353e7f15f1fecc294dec794238b3_3_mwpm_03200403.jpg"
       ,RequestMethod.GET , Environment.getExternalStorageDirectory()+"","my.jpg",true,true);
    //下载的请求队列也时特定的
    DownloadQueue queue = NoHttp.newDownloadQueue();
    queue.add(0, request, new DownloadListener() {
      @Override
      public void onDownloadError(int what, Exception exception) {
        Log.d("data",exception.toString());
      }
      @Override
      public void onStart(int what, boolean isResume, long rangeSize, Headers responseHeaders,
          long allCount) {
      }

      @Override
      public void onProgress(int what, int progress, long fileCount, long speed) {
      }

      @Override
      public void onFinish(int what, String filePath) {
        ig.setImageBitmap(BitmapFactory.decodeFile(filePath));
      }
      @Override
      public void onCancel(int what) {

      }
    });

  }

  //6.0以后要在代码中动态添加权限
  private void setPermissions() {
    if (ContextCompat.checkSelfPermission(MainActivity.this,
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
      if (ContextCompat.checkSelfPermission(MainActivity.this,
              PERMISSION[0]) == PackageManager.PERMISSION_GRANTED) {
        Toast.makeText(this, "写入申请成功", Toast.LENGTH_SHORT).show();
      } else {
        Toast.makeText(this, "写入申请失败", Toast.LENGTH_SHORT).show();
      }
      if (ContextCompat.checkSelfPermission(MainActivity.this,
              PERMISSION[1]) == PackageManager.PERMISSION_GRANTED) {
        Toast.makeText(this, "读取申请成功", Toast.LENGTH_SHORT).show();
      } else {
        Toast.makeText(this, "读取申请失败", Toast.LENGTH_SHORT).show();
      }
      if (ContextCompat.checkSelfPermission(MainActivity.this,
              PERMISSION[2]) == PackageManager.PERMISSION_GRANTED) {
        Toast.makeText(this, "联网申请成功", Toast.LENGTH_SHORT).show();
      } else {
        Toast.makeText(this, "联网申请失败", Toast.LENGTH_SHORT).show();
      }
      if (ContextCompat.checkSelfPermission(MainActivity.this,
              PERMISSION[3]) == PackageManager.PERMISSION_GRANTED) {
        Toast.makeText(this, "接入网络成功", Toast.LENGTH_SHORT).show();
      } else {
        Toast.makeText(this, "接入网络失败", Toast.LENGTH_SHORT).show();
      }
      if (ContextCompat.checkSelfPermission(MainActivity.this,
              PERMISSION[4]) == PackageManager.PERMISSION_GRANTED) {
        Toast.makeText(this, "网络状态成功", Toast.LENGTH_SHORT).show();
      } else {
        Toast.makeText(this, "网络状态失败", Toast.LENGTH_SHORT).show();
      }
    }
  }

}
