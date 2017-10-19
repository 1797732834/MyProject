package com.beicai.da.aptear;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class MyPageAdapter extends PagerAdapter {
	private ArrayList<ImageView> lists;
	private Context aContext;;
	public MyPageAdapter(Context context , ArrayList<ImageView> list){
		lists = list;
		aContext = context;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		container.addView(lists.get(position%(lists.size())));
		return lists.get(position%(lists.size()));
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		container.removeView(lists.get(position%(lists.size())));
	}

}
