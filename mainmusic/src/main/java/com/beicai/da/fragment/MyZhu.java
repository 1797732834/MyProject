package com.beicai.da.fragment;


import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.provider.MediaStore.Audio.Media;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;


import com.beicai.da.Utils.SPUtils;
import com.beicai.da.aptear.ZhuListAdapter;
import com.beicai.da.bean.ZhuMusicData;
import com.beicai.da.mainmusic.PlayMusic;
import com.beicai.da.mainmusic.R;
import com.beicai.da.service.Iservice;
import com.beicai.da.service.MusicService;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;

import java.util.ArrayList;


public class MyZhu extends Fragment implements OnClickListener{
	private int times = 0;// 上一首按钮被点击的次数
	private int times1 = 0;// 下一首按钮被点击的次数
	// 定义常量
	private static int PLAY = 0;// 表示播放的状态
	private static int PAUSE = 1;// 表示暂停的状态
	private static int REPLAY = 2;// 表示继续播放的状态

	// 当前的播放状态
	private static int STATE = PLAY;

	private ListView listv;
	private ImageView imagev;
	private ImageButton play_button;
	private ImageButton next_button;
	private ImageButton pre_button;
	private Iservice iservice;
	private ArrayList<ZhuMusicData> musiclist;
	private ArrayList<String> pathlist;
	private ZhuMusicData bean;
	private String path;
	private String size;
	private String title;
	private String diaplay_name;
	private String duration;
	private static SeekBar sb;
	public static Handler handler =  new Handler(){
		public void handleMessage(android.os.Message msg) {
			int duration = msg.arg1;
			int currentPostion = msg.arg2;
//			这两个设置给进度条
			sb.setMax(duration);
			sb.setProgress(currentPostion);
			
		};
	};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.myzhuframe, null);

		//设置打印错误日志  不设置 NoHttp相应的错误不会打印
		Logger.setDebug(true);
		NoHttp.initialize(getActivity());
		listv = (ListView) view.findViewById(R.id.listView_musiclist);
		imagev = (ImageView) view.findViewById(R.id.imageView_pic_pan);
		play_button = (ImageButton) view.findViewById(R.id.button_play);
		next_button = (ImageButton) view.findViewById(R.id.button_next);
		pre_button = (ImageButton) view.findViewById(R.id.button_pre);
		sb = (SeekBar) view.findViewById(R.id.seekBar1);
		imagev.setOnClickListener(this);
		play_button.setOnClickListener(this);
		next_button.setOnClickListener(this);
		pre_button.setOnClickListener(this);

		Intent intent = new Intent(getActivity(), MusicService.class);
		getActivity().startService(intent);

		MyConn mc = new MyConn();
		getActivity().bindService(intent, mc, Context.BIND_AUTO_CREATE);
		musiclist = new ArrayList<ZhuMusicData>();
		pathlist = new ArrayList<String>();
			Cursor cursor = getActivity().getContentResolver().query(Media.EXTERNAL_CONTENT_URI,
				new String[]{Media.DATA,Media.SIZE,Media.TITLE,Media.ARTIST,Media.DURATION},
				null, null, null);


		if (cursor != null && cursor.getCount() > 0 ) {
			//游标向下移动
			while (cursor.moveToNext()) {
				bean = new ZhuMusicData();
				path = cursor.getString(cursor.getColumnIndex(Media.DATA));
				size = cursor.getString(cursor.getColumnIndex(Media.SIZE));
				title = cursor.getString(cursor.getColumnIndex(Media.TITLE));
				diaplay_name = cursor.getString(cursor.getColumnIndex(Media.ARTIST));
				duration = cursor.getString(cursor.getColumnIndex(Media.DURATION));

				bean.duration = duration;
				bean.path = path;
				bean.size = size;
				bean.name = title;
				bean.zhuname = diaplay_name;

				musiclist.add(bean);
				pathlist.add(path);
			}
			listv.setAdapter(new ZhuListAdapter(getActivity(), musiclist));
			listv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					//					点击条目，进行音乐播放, 根据当前条目的索引，获取当前条目的路径，所有本地扫描出来的音乐对象都在musicList
					ZhuMusicData zmd = musiclist.get(arg2);
					String path2 = zmd.path;
					System.out.println("arg2+++++++++++++++++++++++++++++++++++++++"+arg2);
					System.out.println("path2++++++++++++++++++++++++++++++++++++++"+path2);
					iservice.callPlayMusic(arg2, path2);

					play_button.setImageDrawable(getResources().getDrawable(R.mipmap.player_ting));

					//					点击条目时，保存当前的条目角标到SP里
					SPUtils.saveMusicPosition(arg2, getActivity());

				}

			});
		}

		sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				// 停止拖拽后获取进度，设置给进度条
				// 拖拽后进度条的位置
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



		return view;




	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			//上一首
		case R.id.button_pre:

			//点击次数+1
			times += 1;
			// 获取当前被播放歌曲的脚标
			int position = SPUtils.getMusicPosition(v.getContext());
			if (position - times >= 0) {

				iservice.callPlayMusic(position, musiclist.get(position - times).path);
			} else if (times >= (musiclist.size())) {
				times = 0;
				iservice.callPlayMusic(position, musiclist.get(times).path);
			}
			break;
			//播放 暂停 继续播放
		case R.id.button_play:
			int position1 = SPUtils.getMusicPosition(v.getContext());

			if (STATE == PLAY) {
				iservice.callPlayMusic(position1, musiclist.get(position1).path);
				STATE = PAUSE;
				// 下面可更换图标
				// TODO Auto-generated method stub

				play_button.setImageDrawable(getResources().getDrawable(R.mipmap.player_ting));
				System.out.println("播放");
			} else if (STATE == PAUSE) {
				iservice.callPauseMusic();
				STATE = REPLAY;
				// 下面可更换图标
				// TODO Auto-generated method stub
				play_button.setImageDrawable(getResources().getDrawable(R.mipmap.player_go));
				System.out.println("暂停");
			} else if (STATE == REPLAY) {
				iservice.callReplayMusic();
				STATE = PAUSE;
				// 下面可更换图标
				// TODO Auto-generated method stub
				play_button.setImageDrawable(getResources().getDrawable(R.mipmap.player_ting));
				System.out.println("继续播放");
			}
			//下一首
		case R.id.button_next:
			times1 += 1;// 点击一次就减少一次
			int position2 = SPUtils.getMusicPosition(v.getContext());
			if (times1 >= musiclist.size()) {
				// iservice.callPlay(position2,listBean.get(position2+times1).path);
				times1 = musiclist.size()+1;
				iservice.callPlayMusic(position2, musiclist.get(times1).path);
			} else if (position2 + times1 >= 0) {
				iservice.callPlayMusic(position2,
						musiclist.get(position2 + times1).path);
			}
			break;
			//进入音乐播放
		case R.id.imageView_pic_pan:
			Intent intent = new Intent(getActivity(), PlayMusic.class);
			startActivity(intent);
			break;

		default:
			break;
		}

	}


	class MyConn implements ServiceConnection{
		//		必走其中一个方法
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			System.out.println("onServiceConnected");

			iservice = (Iservice) service;
			System.out.println("成功。。。。。。。。。。");

		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			System.out.println("onServiceDisconnected");
		}

	}

}
