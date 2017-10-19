package com.example.tianqitong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.example.tianqitong.R;


/**
 * Created by Administrator on 2017/5/27 0027.
 */

public class SearchCityGridViewAdapter extends BaseAdapter {
    String searchCity[];
    Context context;
    public SearchCityGridViewAdapter(Context context, String searchCity[]) {
        this.searchCity = searchCity;
        this.context = context;
    }

    @Override
    public int getCount() {
        return searchCity.length;
    }

    @Override
    public Object getItem(int i) {
        return searchCity[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null){
            view = View.inflate(context, R.layout.activity_search_grid_item,null);
            holder = new ViewHolder();
            holder.city_bt = (Button) view.findViewById(R.id.city_bt);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        holder.city_bt.setText(searchCity[i]);
        return view;
    }
class ViewHolder{
    Button city_bt;
}
}
