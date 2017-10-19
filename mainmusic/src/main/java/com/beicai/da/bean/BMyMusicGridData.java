package com.beicai.da.bean;


import com.beicai.da.mainmusic.R;

public class BMyMusicGridData {
	
	public static String[] BMusicText(){
		String[] texts = new String[]{
				"每日歌单：大鹏娜扎《悟空传》",
				"不服来段Freestyle!",
				"一名90后看过的香港电影",
				"Alex大叔推荐：12星座，专属你的治愈",
				"高档进阶：贝司吉他和爵士鼓的律动简史",
				"一秒回到初高学生时光",
		};
		return texts;
		
	}
	
	public static int[] BMusicImages(){
		
	int[] images = new int[]{
			R.mipmap.lyric_poster_default_bg5_small,
			R.mipmap.profile_lnjr_default,
			R.mipmap.lyric_poster_default_bg2_small,
			R.mipmap.lyric_poster_default_bg4_small,
			R.mipmap.profile_i_love_default,
			R.mipmap.profile_default_bg_small
			
	};
	return images;
	
	}
}
