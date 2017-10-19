package com.beicai.da.mainmusic;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;


public class IndexActivity extends Activity {


	private Intent intent;
	private ImageView lv;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		lv = (ImageView) findViewById(R.id.imageview_index);
		//	动画的透明度变化  fromAlpha起始处的透明度 0.0-1.0（范围）0.0完全透明 1.0实体
		AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation.setDuration(5000);
		//	动画是在ImageView控件上
		//	setRepeatCount动画重复的次数  INFINITE无限的
		//	Repeat重复 Mode模式  restart重新开始 reverse相反
		alphaAnimation.setRepeatCount(2);
		alphaAnimation.setRepeatMode(Animation.REVERSE);
		lv.startAnimation(alphaAnimation);

		// 延时操作秒，为的是等动画播放完后，才进行页面跳转
				TimerTask task = new TimerTask() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						// 要执行的操作
						intent = new Intent(getApplicationContext(),MainActivity.class);
						startActivity(intent);
					}
				};
					Timer timer = new Timer();
				timer.schedule(task, 9000);// 9秒后执行TimerTask的run方法
		
		
		//intent = new Intent(getApplicationContext(),MainActivity.class);
		//startActivity(intent);
		System.out.println("1111111111111111111111111111111");
	}
}
