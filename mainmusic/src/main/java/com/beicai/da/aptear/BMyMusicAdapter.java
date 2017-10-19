package com.beicai.da.aptear;





import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.beicai.da.mainmusic.R;


public class BMyMusicAdapter extends BaseAdapter {
	private Context context;
	private int[] images;
	private String[] data;

	public BMyMusicAdapter(Context context , int[] images , String[] data){
		this.context = context;
		this.images = images;
		this.data = data;

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
		View view = inflater.inflate(R.layout.music_grid2, null);

		TextView textview = (TextView) view.findViewById(R.id.grid2textView1);
		ImageView imageview = (ImageView) view.findViewById(R.id.grid2imageView1);

		textview.setText(data[position]);
		imageview.setImageResource(images[position]);

		return view;
	}

}
