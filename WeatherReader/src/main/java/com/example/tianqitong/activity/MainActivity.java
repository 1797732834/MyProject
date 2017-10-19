package com.example.tianqitong.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.example.tianqitong.R;
import com.example.tianqitong.adapter.GuidePagerAdapter;
import com.example.tianqitong.data.GuildeImg;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ViewPager pager;
    ArrayList<ImageView> list;
    Button bt1;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        sp = getSharedPreferences("state",MODE_PRIVATE);
        editor = sp.edit();
        if (sp.getBoolean("isRun",false)){
            setContentView(R.layout.activity_logo);
            new Thread(){
                @Override
                public void run() {
                    try {
                        sleep(3000);
                        startActivity(new Intent(MainActivity.this,SearchActivity.class));
                        finish();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }else{
            setContentView(R.layout.activity_main);
            editor.putBoolean("isRun",true);
            editor.commit();
            init();
        }
    }
    void init(){
        pager = (ViewPager) findViewById(R.id.guide_viewpager);
        bt1 = (Button) findViewById(R.id.button);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SearchActivity.class));
                finish();
            }
        });
        list = GuildeImg.getImgs(this);
        pager.setAdapter(new GuidePagerAdapter(list));
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == list.size()-1){
                    bt1.setVisibility(View.VISIBLE);
                }else{
                    bt1.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}
