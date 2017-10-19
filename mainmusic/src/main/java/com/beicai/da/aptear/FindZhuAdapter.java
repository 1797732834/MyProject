package com.beicai.da.aptear;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.beicai.da.bean.FindInfo;
import com.beicai.da.mainmusic.R;

import java.util.ArrayList;


public class FindZhuAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<FindInfo> list;


	public FindZhuAdapter(Context context , ArrayList<FindInfo> list){
		this.context = context;
		this.list = list;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	MyHolder a;
	View view;
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView == null){
			a = new MyHolder();
			view = LayoutInflater.from(context).inflate(R.layout.find1_grid, null);
			a.atext1 = (TextView) view.findViewById(R.id.find_textView1);
			a.atext2 = (TextView) view.findViewById(R.id.find_textView2);
			a.atext3 = (TextView) view.findViewById(R.id.find_textView4);
			a.aimage = (ImageView) view.findViewById(R.id.find_imageView1);
			view.setTag(a);
		}else{
			view = convertView;
			a = (MyHolder) view.getTag();
		}

		FindInfo fin = list.get(position);
		a.atext1.setText(fin.texts1);
		a.atext2.setText(fin.texts2);
		a.atext3.setText(fin.texts3);
		a.aimage.setImageResource(fin.images);
		return view;
	}

	class MyHolder{
		TextView atext1;
		TextView atext2;
		TextView atext3;
		ImageView aimage;
	}

}
