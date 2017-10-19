package com.beicai.da.aptear;





import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.beicai.da.mainmusic.R;


public class MyMusicAdapter extends BaseAdapter {
	private Context context;
	private int[] images;
	private String[] data;
	public MyMusicAdapter(Context mContext, int[] imgs, String[] texts){
		context = mContext;
		images = imgs;
		data = texts;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return images.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view =inflater.inflate(R.layout.music_grid, null);
		
		TextView textview = (TextView) view.findViewById(R.id.musiceditText1);
		ImageView imageview = (ImageView) view.findViewById(R.id.musicimageView1);
		
		textview.setText(data[position]);
		imageview.setImageResource(images[position]);
		return view;
	}

}
