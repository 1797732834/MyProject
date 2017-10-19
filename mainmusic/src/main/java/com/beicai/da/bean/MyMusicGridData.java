package com.beicai.da.bean;


import com.beicai.da.mainmusic.R;

public class MyMusicGridData {

	public static String[] MusicTextData(){
		String[] texts = new String[]{
				"歌手",
				"排行",
				"电台",
				"分类歌单",
				"视频MV",
				"数字专辑"
		};

		return texts;

	}

	public static int[] MusicImagesData(){
		int[] images = new int[]{
				R.mipmap.interested_people_avatar,
				R.mipmap.list_icon_playing,
				R.mipmap.ic_qahp_4,
				R.mipmap.label_add_normal,
				R.mipmap.icon_buy_album_and_song,
				R.mipmap.ic_run_radio_download_song
		};
		return images;
	}
}
