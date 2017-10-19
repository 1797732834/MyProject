package com.beicai.da.fragment;





import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.beicai.da.News.News;
import com.beicai.da.aptear.FindZhuAdapter;
import com.beicai.da.bean.FindGridData;
import com.beicai.da.bean.FindInfo;
import com.beicai.da.mainmusic.NewsActivity;
import com.beicai.da.mainmusic.R;
import java.util.ArrayList;


public class MyFind extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.find_frame, null);
		ListView lv = (ListView) view.findViewById(R.id.find_gridview);
		Button news_bt = view.findViewById(R.id.news);
		news_bt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getActivity(), NewsActivity.class);
                startActivity(intent);
			}
		});
		FindGridData fd = new FindGridData();
		ArrayList<FindInfo> list = fd.getInfo(getActivity());
		lv.setAdapter(new FindZhuAdapter(getActivity(), list));
		return view;
	}


}
