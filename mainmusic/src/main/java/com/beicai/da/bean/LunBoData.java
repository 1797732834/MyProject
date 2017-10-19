package com.beicai.da.bean;

import android.content.Context;
import android.widget.ImageView;


import com.beicai.da.mainmusic.R;

import java.util.ArrayList;

public class LunBoData {
	private static ArrayList<ImageView> list;
	public static ArrayList<ImageView> initData(Context context){
		int[] images = new int[]{
				R.mipmap.lunboaa,
				
				R.mipmap.lunboz,
				R.mipmap.lunbog,
				R.mipmap.lunboa,
				R.mipmap.lunbob,
				R.mipmap.lunbod,
				R.mipmap.lunbof,
				R.mipmap.lunboh,
				R.mipmap.lunboui,
				R.mipmap.lunbobb,
				R.mipmap.lunboc
		};
		ImageView iv;
		list = new ArrayList<ImageView>();
		for (int i = 0; i < images.length; i++) {
			iv = new ImageView(context);
			iv.setBackgroundResource(images[i]);
			
			
			list.add(iv);
		}
		return list;

	}

}
