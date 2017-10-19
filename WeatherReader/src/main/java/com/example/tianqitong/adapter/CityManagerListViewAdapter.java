package com.example.tianqitong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tianqitong.R;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Administrator on 2017/6/6 0006.
 */

public class CityManagerListViewAdapter extends BaseAdapter {
    ArrayList<HashMap<String, String>> list;
    Context context;
    public CityManagerListViewAdapter(Context context, ArrayList<HashMap<String, String>> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {

        return list.get(i).get("id");
    }

    @Override
    public long getItemId(int i) {

        return Long.parseLong(list.get(i).get("id").toString());
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null){
            view = View.inflate(context, R.layout.activity_city_manager_item,null);
            holder = new ViewHolder();
            holder.tv = (TextView) view.findViewById(R.id.manager_item_tv);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        holder.tv.setText(list.get(i).get("city"));
        return view;
    }
    class ViewHolder{
        TextView tv;
    }
    public void updateAdapter(ArrayList<HashMap<String, String>> list){
        this.list = list;
        notifyDataSetChanged();
    }
}
