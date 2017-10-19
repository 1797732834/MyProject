package com.example.tianqitong.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tianqitong.R;
import com.example.tianqitong.activity.Menu;
import com.example.tianqitong.activity.ResultShowActivity;
import com.example.tianqitong.activity.SearchActivity;
import com.example.tianqitong.dao.CityDao;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2017/6/1 0001.
 */

public class Weather_Fragment extends Fragment {
    View view;
    RelativeLayout bc;
    TextView city,add,more,music,music_not;
    String cityname;
    URL url;
    HttpURLConnection conn;
    ByteArrayOutputStream bs;
    String data = new String();
    String name;
    HashMap<String,String> map;
    TextView today_tem,today_wind,today_info;
    TextView date1,date2,date3;
    TextView date1_info,date2_info,date3_info;
    TextView date1_tem,date2_tem,date3_tem;
    TextView advice1,advice2,advice3;
    ArrayList<HashMap<String,String>> list;
    LinearLayout weather_ll,advice_ll;
    int[]sunShine_imgs = {
            R.mipmap.sunshine_01,
            R.mipmap.sunshine2,
            R.mipmap.sunshine3,
            R.mipmap.sunshine4,
            R.mipmap.sunshine5,
    };
    int[]rain_imgs = {
            R.mipmap.rain1,
            R.mipmap.rain2,
            R.mipmap.rain3,
    };
    int[]cloudy_imgs = {
            R.mipmap.cloudy1,
            R.mipmap.cloudy2,
            R.mipmap.cloudy3,
    };
    int[]snow_imgs = {
            R.mipmap.snow1,
            R.mipmap.snow2,
    };
    int[]fog_imgs = {
            R.mipmap.fog1,
            R.mipmap.fog2,
            R.mipmap.fog3,
    };
    int[]cloud_imgs = {
            R.mipmap.cloud1,
            R.mipmap.cloud2,
            R.mipmap.cloud3,
    };
    CityDao dao;
    boolean isExist;
    SharedPreferences sp;
    private boolean isPlay;
    private HashMap<String, String> info;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dao = new CityDao(getActivity());
        Intent intent = getActivity().getIntent();
        cityname = intent.getStringExtra("city");
        isExist = dao.selectByCity(cityname);
        if (!isExist){
            dao.add(cityname);
        }
        init();
        try {
            name = URLEncoder.encode(cityname,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        MyTask task = new MyTask();
        task.execute("http://v.juhe.cn/weather/index?cityname="+name+"&dtype=&format=&key=44c5f56c5b2958b4515f45f8b638e491");
        init();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = View.inflate(getActivity(), R.layout.activity_weather,null);
    }
    void init(){
        city = (TextView) view.findViewById(R.id.result_cityname);
        add = (TextView) view.findViewById(R.id.result_add);
        more = (TextView) view.findViewById(R.id.result_more);
        bc = (RelativeLayout) view.findViewById(R.id.result_bc);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),SearchActivity.class));
            }
        });
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),Menu.class));
            }
        });
        music = (TextView) view.findViewById(R.id.result_music);
        music_not = (TextView) view.findViewById(R.id.result_music_not);
        sp = getActivity().getSharedPreferences("state",MODE_PRIVATE);
        isPlay = sp.getBoolean("isPlay", true);
        if (isPlay) {
            music.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    music_not.setVisibility(View.VISIBLE);
                    ((ResultShowActivity) getActivity()).stopMusic();
                }
            });

            music_not.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    music_not.setVisibility(View.GONE);
                    ((ResultShowActivity) getActivity()).playMusic();
                }
            });
        }
        city.setText(cityname);
        today_tem = (TextView) view.findViewById(R.id.result_today_tem);
        today_wind = (TextView) view.findViewById(R.id.result_today_wind);
        today_info = (TextView) view.findViewById(R.id.result_today_info);
        date1 = (TextView) view.findViewById(R.id.result_date1);
        date2 = (TextView) view.findViewById(R.id.result_date2);
        date3 = (TextView) view.findViewById(R.id.result_date3);
        date1_info = (TextView) view.findViewById(R.id.result_date1_info);
        date2_info = (TextView) view.findViewById(R.id.result_date2_info);
        date3_info = (TextView)view. findViewById(R.id.result_date3_info);
        date1_tem = (TextView) view.findViewById(R.id.result_date1_tem);
        date2_tem = (TextView) view.findViewById(R.id.result_date2_tem);
        date3_tem = (TextView) view.findViewById(R.id.result_date3_tem);
        advice1 = (TextView) view.findViewById(R.id.result_advice1);
        advice2 = (TextView) view.findViewById(R.id.result_advice2);
        advice3 = (TextView) view.findViewById(R.id.result_advice3);
        weather_ll = (LinearLayout) view.findViewById(R.id.result_advice_ll);
        advice_ll = (LinearLayout)view. findViewById(R.id.result_weather_ll);
        //动态获取模拟器屏幕的高低，
//        获取窗口管理器
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
//        获取显示对象
        Display dy = wm.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        dy.getMetrics(dm);
        int height = dm.heightPixels;
        int width = dm.widthPixels;
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(width,height/2);
        bc.setLayoutParams(p);
        bc.setAlpha((float) 0.8);

        info = new HashMap<>();
    }
    public class MyTask extends AsyncTask<String,Integer,String> {

        @Override
        protected void onPostExecute(String s) {
            parseData();
            weather_ll.setVisibility(View.VISIBLE);
            advice_ll.setVisibility(View.VISIBLE);
            today_tem.setText(map.get("temp")+"℃");
            today_wind.setText(map.get("wind_direction")+" "+map.get("wind_strength")+"/ 湿度："+map.get("humidity"));
            today_info.setText(map.get("weather"));
            if (list == null){
                return;
            }
            date1.setText(list.get(0).get("week_day"));
            date1_tem.setText(list.get(0).get("temperature_day"));
            date1_info.setText(list.get(0).get("weather_day"));
            date2.setText(list.get(1).get("week_day"));
            date2_tem.setText(list.get(1).get("temperature_day"));
            date2_info.setText(list.get(1).get("weather_day"));
            date3.setText(list.get(2).get("week_day"));
            date3_tem.setText(list.get(2).get("temperature_day"));
            date3_info.setText(list.get(2).get("weather_day"));

            advice1.setText("穿衣建议："+map.get("dressing_advice"));
            advice2.setText("洗衣建议："+map.get("wash_index"));
            advice3.setText("运动建议："+map.get("exercise_index"));

            if (map.get("weather").contains("晴")){
                bc.setBackgroundResource(sunShine_imgs[0]);
                updateBc(sunShine_imgs);
            }else if(map.get("weather").contains("雨")){
                bc.setBackgroundResource(rain_imgs[0]);
                updateBc(rain_imgs);
            }else if (map.get("weather").contains("阴")){
                bc.setBackgroundResource(cloudy_imgs[0]);
                updateBc(cloudy_imgs);
            }else if (map.get("weather").contains("雪")){
                bc.setBackgroundResource(snow_imgs[0]);
                updateBc(snow_imgs);
            }else if (map.get("weather").contains("雾")){
                bc.setBackgroundResource(fog_imgs[0]);
                updateBc(fog_imgs);
            }else if (map.get("weather").contains("云")){
                bc.setBackgroundResource(cloud_imgs[0]);
                updateBc(cloud_imgs);
            }
            if (sp.getBoolean("isShow",true)){
                Message message = Message.obtain();
                Bundle bundle = new Bundle();
                bundle.putString("city",info.get("city"));
                bundle.putString("temp",info.get("temp"));
                bundle.putString("weather",info.get("weather"));
                message.setData(bundle);
                ((ResultShowActivity)getActivity()).handler.sendMessage(message);
            }

        }
        @Override
        protected String doInBackground(String... strings) {
            try {
                url = new URL(strings[0]);
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(10000);
                conn.setReadTimeout(10000);
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestProperty("Accept-Encoding", "identity");
                conn.connect();

                InputStream is = conn.getInputStream();
                if (conn.getResponseCode() == 200){
                    byte buffer[] = new byte[1024];
                    bs = new ByteArrayOutputStream();
                    int length;
                    while ((length = is.read(buffer)) != -1){
                        bs.write(buffer,0,length);
                    }
                    is.close();
                    bs.close();
                    data = bs.toString();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return data;
        }
    }

    //背景更改
    void updateBc(final int imgs[]){
        new Thread(new Runnable() {
            @Override
            public void run() {
                final int[] i = {0};
                while (true) {
                    SystemClock.sleep(5000);
                    i[0]++;
                    // 在子线程中调用runOnUiThread方法更新UI
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            bc.setBackgroundResource(imgs[i[0]]);
                            if (i[0] ==imgs.length-1){
                                i[0] = 0;
                            }
                            Log.d("num....", i[0] +"");
                        }
                    });
                }
            }
        }).start();
    }
    void parseData(){
        if (TextUtils.isEmpty(data)){
            Toast.makeText(getActivity(),"网络连接异常，稍后再试", Toast.LENGTH_LONG).show();
        }else {
            try {
                map = new HashMap<>();
                JSONObject json = new JSONObject(data);
                JSONObject result = json.getJSONObject("result");
                JSONObject sk = result.getJSONObject("sk");
                String temp = sk.getString("temp");
                String wind_direction = sk.getString("wind_direction");
                String wind_strength = sk.getString("wind_strength");
                String humidity = sk.getString("humidity");
                String time = sk.getString("time");
                JSONObject today = result.getJSONObject("today");
                String temperature = today.getString("temperature");
                String weather = today.getString("weather");
                String wind = today.getString("wind");
                String week = today.getString("week");
                String city = today.getString("city");
                String date_y = today.getString("date_y");
                String dressing_index = today.getString("dressing_index");
                String dressing_advice = today.getString("dressing_advice");
                String uv_index = today.getString("uv_index");
                String comfort_index = today.getString("comfort_index");
                String wash_index = today.getString("wash_index");
                String travel_index = today.getString("travel_index");
                String exercise_index = today.getString("exercise_index");
                String drying_index = today.getString("drying_index");

                map.put("temp",temp);
                map.put("wind_direction",wind_direction);
                map.put("wind_strength",wind_strength);
                map.put("humidity",humidity);
                map.put("time",time);
                map.put("temperature",temperature);
                map.put("weather",weather);
                map.put("wind",wind);
                map.put("week",week);
                map.put("city",city);
                map.put("date_y",date_y);
                map.put("dressing_index",dressing_index);
                map.put("dressing_advice",dressing_advice);
                map.put("uv_index",uv_index);
                map.put("comfort_index",comfort_index);
                map.put("wash_index",wash_index);
                map.put("travel_index",travel_index);
                map.put("exercise_index",exercise_index);
                map.put("drying_index",drying_index);

                JSONObject future = result.getJSONObject("future");
                Iterator<String> keys = future.keys();
                list = new ArrayList<>();
                while (keys.hasNext()){
                    String key = keys.next();
                    HashMap<String,String> map =new HashMap<>();
                    JSONObject day = future.getJSONObject(key);
                    String temperature_day = day.getString("temperature");
                    map.put("temperature_day",temperature_day);
                    String weather_day = day.getString("weather");
                    map.put("weather_day",weather_day);
                    String week_day = day.getString("week");
                    map.put("week_day",week_day);
                    list.add(map);
                }
                info.put("city",cityname);
                info.put("temp",map.get("temp"));
                info.put("weather",map.get("weather"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


}
