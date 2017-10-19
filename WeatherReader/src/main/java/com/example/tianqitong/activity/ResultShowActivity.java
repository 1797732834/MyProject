package com.example.tianqitong.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.tianqitong.R;
import com.example.tianqitong.adapter.Result_FragmentPagerAdapter;
import com.example.tianqitong.dao.Iservice;
import com.example.tianqitong.fragment.News_Fragment;
import com.example.tianqitong.fragment.Weather_Fragment;
import com.example.tianqitong.service.ShowNotification_Service;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Administrator on 2017/5/28 0028.
 */

public class ResultShowActivity extends FragmentActivity {
    ViewPager pager;
    Weather_Fragment f1;
    News_Fragment f2;
    ArrayList<Fragment> list_fragment;
    MediaPlayer mediaPlayer;
    SharedPreferences sp;
    private boolean isPlay;
    MyConn myConn;
    public static Iservice iservice = new Iservice() {
        @Override
        public void showNotification(HashMap<String, String> map) {
        }
        @Override
        public void cancelNotification() {
        }
    };

    public static Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            HashMap<String,String> map = new HashMap<>();
            map.put("city",bundle.getString("city"));
            map.put("weather",bundle.getString("weather"));
            map.put("temp",bundle.getString("temp"));
            iservice.showNotification(map);
        }
    };

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        init();
        sp = getSharedPreferences("state",MODE_PRIVATE);
        isPlay = sp.getBoolean("isPlay", true);
        mediaPlayer = MediaPlayer.create(ResultShowActivity.this, R.raw.a);
        if (isPlay) {
            mediaPlayer.start();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sp.getBoolean("isPlay", true)) {
            mediaPlayer = MediaPlayer.create(ResultShowActivity.this, R.raw.a);
            mediaPlayer.start();
        }else{
            mediaPlayer.stop();
        }
    }

    void init(){
        list_fragment = new ArrayList<>();
        pager = (ViewPager) findViewById(R.id.result_viewpager);
        f1 = new Weather_Fragment();
        f2 = new News_Fragment();
        list_fragment.add(f1);
        list_fragment.add(f2);
        pager.setAdapter(new Result_FragmentPagerAdapter(getSupportFragmentManager(),list_fragment));

        Intent service = new Intent(ResultShowActivity.this, ShowNotification_Service.class);
        startService(service);
        myConn = new MyConn();
        bindService(service,myConn,BIND_AUTO_CREATE);
    }

    public void playMusic(){
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer = MediaPlayer.create(ResultShowActivity.this,R.raw.a);
                mediaPlayer.start();
                playMusic();
            }
        });
    }
    public void stopMusic(){
        mediaPlayer.pause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (sp.getBoolean("isPlay", true)) {
            mediaPlayer.stop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iservice.cancelNotification();
        unbindService(myConn);
    }

    class MyConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            iservice = (Iservice) iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
        }
    }


    // 设置按两次返回键退出
    long time = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - time < 2000) {
                finish();
            } else {
                Toast.makeText(ResultShowActivity.this, "再按一次返回键退出", Toast.LENGTH_LONG)
                        .show();
                time = System.currentTimeMillis();
            }
        }
        return true;
    }
}
