package com.beicai.da.mainmusic;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.beicai.da.service.Iservice;


public class PlayMusic extends Activity implements OnClickListener {
private Button button_playmusic;
private Button button_nextmusic;
private Button button_premusic;
private static SeekBar sb;
private Iservice iservice;
static Thread t=new Thread(){
	@Override
	public void run() {
		handler.sendEmptyMessage(0X111);
	}
};
public static Handler handler = new Handler(){
public void handleMessage(Message msg){
	int duration = msg.arg1;// 进度总时长
	int currentPosition = msg.arg2;// 当前时长
	// 把这两个值赋值给进度条
	// 设置总时长
	//seekBar.setMax(duration);
	sb.setMax(duration);
	// 设置进度
	//seekBar.setProgress(currentPosition);
	sb.setProgress(currentPosition);
	handler.postDelayed(t, 2000);
}

};
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_player);
	button_playmusic = (Button) findViewById(R.id.button_playmusic);
	button_nextmusic = (Button) findViewById(R.id.button_nextmusic);
	button_premusic = (Button) findViewById(R.id.button_premusic);
	sb = (SeekBar) findViewById(R.id.seekBar1);
	
	button_nextmusic.setOnClickListener(this);
	button_playmusic.setOnClickListener(this);
	button_premusic.setOnClickListener(this);
	sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
		
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stubint progress=arg1.getIntExtra("progress", 0);
		//	MusicService.seekChange(progress);
			//int progress=seekBar.getProgress();
			// 停止拖拽后获取进度，设置给进度条
			iservice.callSeekToPosition(seekBar.getProgress());
		}
		
		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			// TODO Auto-generated method stub
			
		}
	});
}

@Override
public void onClick(View v) {
	// TODO Auto-generated method stub
	switch (v.getId()) {
	case R.id.button_nextmusic:
		
		break;
		
	case R.id.button_playmusic:
		
		break;
		
	case R.id.button_premusic:
		
		break;

	default:
		break;
	}
}
}
