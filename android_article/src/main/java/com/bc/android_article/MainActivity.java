package com.bc.android_article;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.bc.homepage.Homepage;


public class MainActivity extends AppCompatActivity {
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        img1  = (ImageView)findViewById(R.id.img1);
        img2  = (ImageView)findViewById(R.id.img2);
        img3  = (ImageView)findViewById(R.id.img3);

        img2.setVisibility(View.GONE);
        img3.setVisibility(View.GONE);

        //创建动画集 set集合 无序的 shareInterpolator true所有的动画都是用动画集的加速器
        //false可以使用自己的变速器
        AnimationSet set = new AnimationSet(true);
        AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(3000);

        //fromX 动画启动时，X轴上的伸缩尺寸1.0正常大小 2.0两倍大小
        //toX 结束后，x轴上的伸缩尺寸
        //pivotXType Animation.ABSOLUTE, Animation.RELATIVE_TO_SELF(相对于自己进行缩放), or Animation.RELATIVE_TO_PARENT（相对于父控件进行缩放）
        //pivotXValue 0%-100%
        ScaleAnimation animation1 = new ScaleAnimation(0.5f, 1.0f, 0.5f, 1.0f, Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT,0.5f);

        //设置动画执行时间
        animation1.setDuration(3000);
        //interpolator加速器 liner interpolation LinearInterpolator 动画均匀的速度改变（默认）
        //AccelerateInterpolator 在动画开始的地方改变速度较慢，然后开始加速
        //CycleInterpolator 动画播放的特定的次数,变化速度按正弦曲线改变
        //DecelerateInterpolator 在动画开始的地方改变速度较快，然后开始减速
        animation1.setInterpolator(new AccelerateDecelerateInterpolator());
        animation1.setRepeatCount(0);
//        animation1.setRepeatMode(Animation.RESTART);
        set.addAnimation(animation);
        set.addAnimation(animation1);
        img1.startAnimation(set);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img3.setVisibility(v.GONE);
                img2.setVisibility(v.VISIBLE);
                AnimationSet set = new AnimationSet(true);
                AlphaAnimation animation = new AlphaAnimation(0.0f,1.0f);
                animation.setDuration(3000);
                ScaleAnimation animation2 = new ScaleAnimation(0.5f,1.0f,0.5f,1.0f,Animation.RELATIVE_TO_PARENT,0.5f,Animation.RELATIVE_TO_PARENT,0.5f);
                animation2.setDuration(3000);
                animation2.setInterpolator(new AccelerateDecelerateInterpolator());
                animation2.setRepeatCount(0);
                set.addAnimation(animation);
                set.addAnimation(animation2);
                img2.startAnimation(set);

            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img3.setVisibility(v.VISIBLE);
                AnimationSet set = new AnimationSet(true);
                AlphaAnimation animation = new AlphaAnimation(0.0f,1.0f);
                animation.setDuration(3000);
                ScaleAnimation animation2 = new ScaleAnimation(0.5f, 1.0f, 0.5f, 1.0f, Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT,0.5f);
                animation2.setDuration(3000);
                animation2.setInterpolator(new AccelerateDecelerateInterpolator());
                animation2.setRepeatCount(0);
                set.addAnimation(animation);
                set.addAnimation(animation2);
                img3.startAnimation(set);
            }
        });

    }
    public void jump(View v){
        Intent intent = new Intent(MainActivity.this,Homepage.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
