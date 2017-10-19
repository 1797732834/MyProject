package com.beicai.da.aptear;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.beicai.da.bean.ZhuMusicData;
import com.beicai.da.mainmusic.R;

import java.util.ArrayList;


public class ZhuListAdapter extends BaseAdapter {
	
	private Context context;
	private ArrayList<ZhuMusicData> list;
	public ZhuListAdapter(Context context , ArrayList<ZhuMusicData> list){
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		View view = LayoutInflater.from(context).inflate(R.layout.zhu_item, null);
		TextView tv1 = (TextView) view.findViewById(R.id.textView1);
		TextView tv2 = (TextView) view.findViewById(R.id.textView2);
		
		ZhuMusicData zmd = list.get(arg0);
		tv1.setText(zmd.name);
		tv2.setText(zmd.zhuname);
		return view;
	}

}
