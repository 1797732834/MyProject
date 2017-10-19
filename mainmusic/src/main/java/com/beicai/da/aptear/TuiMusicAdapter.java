package com.beicai.da.aptear;




import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.beicai.da.mainmusic.R;


public class TuiMusicAdapter extends BaseAdapter {
	private Context context;
	private int[] images;
	private String[] data1;
	private String[] data2;
	public TuiMusicAdapter(Context context , int[] images , String[] data1 , String[] data2){
		this.context = context;
		this.images = images;
		this.data1 = data1;
		this.data2 = data2;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return images.length;
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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.tuimusic, null);

		ImageView imageview = (ImageView) view.findViewById(R.id.tui_imageView1);
		TextView textview1 = (TextView) view.findViewById(R.id.tui_textView1);
		TextView textview2 = (TextView) view.findViewById(R.id.tui_textView2);
		
		
		imageview.setImageResource(images[position]);
		textview1.setText(data1[position]);
		textview2.setText(data2[position]);
		return view;
	}

}
