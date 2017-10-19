package com.beicai.da.mainmusic;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by da on 2017/9/2.
 */

public class DengLuAActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.longdenglu);
        setPermissions();
        ImageButton imagebutton = (ImageButton) findViewById(R.id.long_imageb2);
        ImageButton imb = (ImageButton) findViewById(R.id.long_imageb1);

        imb.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(),"功能未完成，敬请期待！",Toast.LENGTH_LONG);
            }
        });
        imagebutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent(DengLuAActivity.this, DengLuBActivity.class);
                startActivity(intent);
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
    //6.0以后要在代码中动态添加权限
    private void setPermissions() {
        if (ContextCompat.checkSelfPermission(DengLuAActivity.this,
                PERMISSION[0]) != PackageManager.PERMISSION_GRANTED) {
            Log.d("data", "权限申请");
            Toast.makeText(DengLuAActivity.this, "申请权限", Toast.LENGTH_SHORT).show();
            //Android 6.0申请权限
            ActivityCompat.requestPermissions(DengLuAActivity.this, PERMISSION, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == 1) {
            if (ContextCompat.checkSelfPermission(DengLuAActivity.this,
                    PERMISSION[0]) == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(DengLuAActivity.this, "写入申请成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(DengLuAActivity.this, "写入申请失败", Toast.LENGTH_SHORT).show();
            }
            if (ContextCompat.checkSelfPermission(DengLuAActivity.this,
                    PERMISSION[1]) == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(DengLuAActivity.this, "读取申请成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(DengLuAActivity.this, "读取申请失败", Toast.LENGTH_SHORT).show();
            }
            if (ContextCompat.checkSelfPermission(DengLuAActivity.this,
                    PERMISSION[2]) == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(DengLuAActivity.this, "联网申请成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(DengLuAActivity.this, "联网申请失败", Toast.LENGTH_SHORT).show();
            }
            if (ContextCompat.checkSelfPermission(DengLuAActivity.this,
                    PERMISSION[3]) == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(DengLuAActivity.this, "接入网络成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(DengLuAActivity.this, "接入网络失败", Toast.LENGTH_SHORT).show();
            }
            if (ContextCompat.checkSelfPermission(DengLuAActivity.this,
                    PERMISSION[4]) == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "网络状态成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "网络状态失败", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
