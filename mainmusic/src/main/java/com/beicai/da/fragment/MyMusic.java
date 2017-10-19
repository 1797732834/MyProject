package com.beicai.da.fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.beicai.da.aptear.BMyMusicAdapter;
import com.beicai.da.aptear.MyMusicAdapter;
import com.beicai.da.aptear.MyPageAdapter;
import com.beicai.da.aptear.TuiMusicAdapter;
import com.beicai.da.bean.BMyMusicGridData;
import com.beicai.da.bean.LunBoData;
import com.beicai.da.bean.MyMusicGridData;
import com.beicai.da.bean.tuiMusicData;
import com.beicai.da.mainmusic.R;

import java.util.ArrayList;



public class MyMusic extends Fragment{
	private ViewPager viewPager;
	private ArrayList<ImageView> list;
	private View view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.musiczhu, null);
		lunbo();
		grid();
		bgrid();
		cgrid();
		return view;
	}
	public void lunbo(){
		//轮播图
		viewPager = (ViewPager) view.findViewById(R.id.lunbo);
		list = LunBoData.initData(getActivity());
		System.out.println(list);
		viewPager.setAdapter(new MyPageAdapter(getActivity(), list));
		int m = (Integer.MAX_VALUE/2)%list.size();
		int currentPosition = Integer.MAX_VALUE/2-m;
		viewPager.setCurrentItem(currentPosition);

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					SystemClock.sleep(2000);
					
					getActivity().runOnUiThread(new Runnable() {


						public void run() {
							// TODO Auto-generated method stub
							viewPager.setCurrentItem(viewPager.getCurrentItem()+1);						}
					});
  
				}
			}
		}).start();
	}

	public void grid(){
		System.out.println("0000000000000000");
		//setContentView(R.layout.musiczhu);
		//网格1
		GridView gv = (GridView) view.findViewById(R.id.musicgv1);
		int[] images = MyMusicGridData.MusicImagesData();
		String[] text = MyMusicGridData.MusicTextData();

		gv.setAdapter(new MyMusicAdapter(getActivity(), images, text));
		gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:
				
					Toast.makeText(getActivity(), "hahahah", Toast.LENGTH_LONG).show();
					break;
				case 1:
					Toast.makeText(getActivity(), "hahahah", Toast.LENGTH_LONG).show();
					break;
				case 2:
					Toast.makeText(getActivity(), "hahahah", Toast.LENGTH_LONG).show();
					break;
				case 3:
					Toast.makeText(getActivity(), "hahahah", Toast.LENGTH_LONG).show();
					break;
				case 4: 
					Toast.makeText(getActivity(), "hahahah", Toast.LENGTH_LONG).show();
					break;
				case 5:
					Toast.makeText(getActivity(), "hahahah", Toast.LENGTH_LONG).show();
					break;
				}
			}
		});
	}

	public void bgrid(){
		//网格2
		GridView gv = (GridView) view.findViewById(R.id.musicgv2);
		int[] images = BMyMusicGridData.BMusicImages();
		String[] texts = BMyMusicGridData.BMusicText();

		gv.setAdapter(new BMyMusicAdapter(getActivity() , images , texts));
		gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:

					break;
				case 1:

					break;
				case 2:

					break;
				case 3:

					break;
				case 4:

					break;
				case 5:

					break;

				default:
					break;
				}

			}
		});
	}

	public void cgrid(){
		GridView gv = (GridView) view.findViewById(R.id.musicgv3);
		int[] images = tuiMusicData.GetImages();
		String[] text1 = tuiMusicData.GetTexts1();
		String[] text2 = tuiMusicData.GetTexts2();

		gv.setAdapter(new TuiMusicAdapter(getActivity(), images, text1, text2));
		gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

				switch (position) {
				case 0:

					break;
				case 1:

					break;
				case 2:

					break;
				case 3:

					break;
				case 4:

					break;

				default:
					break;
				}

			}
		});
	
}
}
