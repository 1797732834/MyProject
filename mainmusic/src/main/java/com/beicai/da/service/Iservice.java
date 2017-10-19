package com.beicai.da.service;

public interface Iservice {
	void callPlayMusic(int id, String path);
	void callPauseMusic();
	void callReplayMusic();
	void callSeekToPosition(int wz);

}
