package com.beicai.da.service;


import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.os.Message;

import com.beicai.da.Utils.SPUtils;
import com.beicai.da.fragment.MyZhu;

import java.util.Timer;
import java.util.TimerTask;


public class MusicService extends Service {

	private MediaPlayer player;
	private Timer timer;
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return new MyBinder();
	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		player = new MediaPlayer();
	}
	/*
         * 音乐播放
         */
	public void playMusic(int id , String path){
		try {
			player.reset();
			player.setDataSource(path);
			player.prepare();
			player.start();
//			*************************************
			updateSeekBar(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/*
         * 音乐暂停
         */
	public void pauseMusic(){
		player.pause();
	}
	/*
         * 继续播放
         */
	public void replayMusic(){
		player.start();
	}

	/*
	 * 进度条拖拽
	 */
	public void seekToPostion(int wz){
		player.seekTo(wz);
	}
	/*
         * 进度条逻辑
         */
	public void updateSeekBar(int id){

		//		获取本地存储的id,和被点击后的id作对比
		int position = SPUtils.getMusicPosition(getApplicationContext());
		if (id != position && timer != null) {
			timer.cancel();//播放第二首歌，因为第一首歌的播放，timer存在，把第一首的timer取消
			System.out.println("timer"+"1111111111111111111111111111");
		}
		final int duration = player.getDuration();                                  
		timer = new Timer();                                                        
		TimerTask task = new TimerTask() {                                          

			@Override                                                               
			public void run() {                                                     
				// TODO Auto-generated method stub                                  
				//				获取当前进度
				int currentPosition = player.getCurrentPosition();
				//				是一个子线程的环境，这个进度是Main使用的，子主线程通信使用Handler

				//				Message message = new Message();                                    
				Message message = Message.obtain();                                 
				message.arg1 = duration;                                            
				message.arg2 = currentPosition;                                     
				MyZhu.handler.sendMessage(message);


			}                                                                       
		};                                                                          
		timer.schedule(task, 0, 1000);                                              

	}                                                                               

	class MyBinder extends Binder implements Iservice{                              

		@Override                                                                   
		public void callPlayMusic(int id,String path) {                             
			// TODO Auto-generated method stub                                      
			playMusic(id,path);                                                     
		}                                                                           

		@Override                                                                   
		public void callPauseMusic() {                                              
			// TODO Auto-generated method stub                                      
			pauseMusic();                                                           
		}                                                                           

		@Override                                                                   
		public void callReplayMusic() {                                             
			// TODO Auto-generated method stub                                      
			replayMusic();                                                          
		}                                                                           

		@Override                                                                   
		public void callSeekToPosition(int wz) {                                    
			// TODO Auto-generated method stub                                      
			seekToPostion(wz);                                                      
		}

		                                                                         


	}                                                                               

}

