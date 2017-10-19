package com.example.tianqitong.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.tianqitong.R;
import com.example.tianqitong.adapter.SearchCityGridViewAdapter;
import com.example.tianqitong.beans.CityInfo;
import com.example.tianqitong.data.SearchCityData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/26 0026.
 */

public class SearchActivity extends Activity {

    URL url;
    HttpURLConnection conn;
    ByteArrayOutputStream bs;
    String data = new String();
    ArrayList<CityInfo> cityList;
    ArrayList<String> list1;
    AutoCompleteTextView auTv;
    GridView gridView;
    ProgressDialog dialog;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    String cityname,province;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        init();
        MyTask task = new MyTask();
        task.execute("http://v.juhe.cn/weather/citys?dtype=&key=44c5f56c5b2958b4515f45f8b638e491");
    }
    void init(){
        dialog = new ProgressDialog(SearchActivity.this);
        dialog.setTitle("正在加载中......");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        auTv = (AutoCompleteTextView) findViewById(R.id.search_autoCompleteTextView);
        gridView = (GridView) findViewById(R.id.search_gridView);

//      热门城市 网格布局设置
        String[] citys = SearchCityData.getSearchCityData();
        gridView.setAdapter(new SearchCityGridViewAdapter(SearchActivity.this,citys));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(SearchActivity.this,ResultShowActivity.class);
                Button city_bt = (Button)view.findViewById(R.id.city_bt);
                cityname = city_bt.getText().toString();
                intent.putExtra("city",cityname);
                startActivity(intent);
                finish();
            }
        });
    }


    class MyTask extends AsyncTask<String,Integer,String> {
        @Override
        protected void onProgressUpdate(Integer... values) {
            dialog.show();
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
                long total = conn.getContentLength();
                if (conn.getResponseCode() == 200){
                    byte buffer[] = new byte[1024];
                    bs = new ByteArrayOutputStream();
                    int length;
                    long data_length = 0;
                    while ((length = is.read(buffer)) != -1){
                        bs.write(buffer,0,length);
                        data_length += length;
                        publishProgress((int)((float)data_length/total*100));
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
        @Override
        protected void onPostExecute(String s) {
            dialog.dismiss();
            parseData();
        }
    }

    //    解析数据
    public void parseData(){
        if (data.equals(null)){
            Toast.makeText(SearchActivity.this,"网络连接异常，稍后再试", Toast.LENGTH_LONG).show();
        }else {
            try {
                JSONObject json = new JSONObject(data);
                JSONArray result = json.getJSONArray("result");
                cityList = new ArrayList<>();
                for (int i = 0; i < result.length(); i++) {
                    JSONObject resultJo = result.getJSONObject(i);
                    String id = resultJo.getString("id");
                    String province = resultJo.getString("province");
                    String city = resultJo.getString("city");
                    String district = resultJo.getString("district");

                    CityInfo cityInfo = new CityInfo();
                    cityInfo.setId(id);
                    cityInfo.setCity(city);
                    cityInfo.setProvince(province);
                    cityInfo.setDistrict(district);
                    cityList.add(cityInfo);
                    list1 = new ArrayList<>();
                    for (int j=0;j<cityList.size();j++){
                        list1.add(cityList.get(j).getDistrict()+" - "+cityList.get(j).getCity()+"，"+cityList.get(j).getProvince());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(SearchActivity.this,android.R.layout.simple_expandable_list_item_2,
                            android.R.id.text1,list1);
                    auTv.setAdapter(adapter);
                    auTv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            String info = adapterView.getItemAtPosition(i).toString();
                            cityname = info.substring(0,info.indexOf("-"));
                            Intent intent = new Intent(SearchActivity.this,ResultShowActivity.class);
                            intent.putExtra("city",cityname);
                            startActivity(intent);
                            finish();
                        }
                    });

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}
