package com.beicai.da.leida_test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import android.view.View.OnClickListener;

import com.beicai.da.News.News;
import com.beicai.da.adapter.MyAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by da on 2017/9/11.
 */

public class ZhuActivity extends Activity implements OnClickListener{
    TextView toutiao , guonei , shehui , tiyu , keji , yule , junshi;
    ListView listView;
    MyAdapter adapter;
    ArrayList<HashMap<String,Object>> alist = new ArrayList<>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhu_main);
        init();
    }
    void init() {
        toutiao = findViewById(R.id.tx_toutiao);
        toutiao.setGravity(Gravity.CENTER_HORIZONTAL);
        toutiao.setOnClickListener(this);
        guonei = findViewById(R.id.tx_guonei);
        guonei.setGravity(Gravity.CENTER_HORIZONTAL);
        guonei.setOnClickListener(this);
        shehui = findViewById(R.id.tx_shehui);
        shehui.setGravity(Gravity.CENTER_HORIZONTAL);
        shehui.setOnClickListener(this);
        tiyu = findViewById(R.id.tx_tiyu);
        tiyu.setGravity(Gravity.CENTER_HORIZONTAL);
        tiyu.setOnClickListener(this);
        keji = findViewById(R.id.tx_keji);
        keji.setGravity(Gravity.CENTER_HORIZONTAL);
        keji.setOnClickListener(this);
        yule = findViewById(R.id.tx_yule);
        yule.setGravity(Gravity.CENTER_HORIZONTAL);
        yule.setOnClickListener(this);
        junshi = findViewById(R.id.tx_junshi);
        junshi.setGravity(Gravity.CENTER_HORIZONTAL);
        junshi.setOnClickListener(this);

        listView = findViewById(R.id.listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // int  表示当前点击的ListView的item的位置ID
            //long  返回的是适配器的getItemId方法的返回值
            //adapterView代表当前适配器    可以获取当前Item所加载的数据
            //View表示当前视图  可以获取当前Item视图
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ZhuActivity.this,ContentActivity.class);
//        Log.d("data",adapterView.getItemAtPosition(i).toString());
//        Toast.makeText(MainActivity.this, "int:"+i+",long"+l, Toast.LENGTH_SHORT).show();

//        TextView txTitle = view.findViewById(R.id.tx_title);
//        txTitle.setTextColor(Color.RED);


//用集合保存点击记录
// HashMap<String,Object> map = new HashMap<>();
//        map.put("position",i);
//        map.put("isClick",true);
//        if(!alist.contains(map)){//判断当前集合中是否含有要存入的map集合
//          alist.add(map);
//          adapter.updateTextColor(alist);
//        }
//

                intent.putExtra("url",
                        ((News.ResultBean.DataBean)adapterView.getItemAtPosition(i)).getUrl());
//        Bundle b = new Bundle();
//        b.putString("dd","ddd");
//        intent.putExtras(b);
                startActivity(intent);
                ((News.ResultBean.DataBean)adapterView.getItemAtPosition(i)).setClick(true);

            }
        });
        new Thread(){
            public void run(){
                getDataByType("");
            }
        }.start();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        adapter.notifyDataSetChanged();

    }

    void getDataByType(String type) {
        try {
            URL url = new URL("http://v.juhe.cn/toutiao/index?"
                    + "type=" + type + "&key=2ca3a5b1cb6edf55250bff550ac34325");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(10000);
            con.setReadTimeout(10000);
            con.connect();

            if (con.getResponseCode() == 200) {
                InputStream is = con.getInputStream();
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                byte[] buffer = new byte[512];
                int length;
                while ((length = is.read(buffer)) != -1) {
                    bs.write(buffer, 0, length);
                    bs.flush();
                }
//        News news = new Gson().fromJson(bs.toString(),News.class);
                News news = new Gson().fromJson(bs.toString(), new TypeToken<News>() {
                }.getType());
                adapter = new MyAdapter(ZhuActivity.this, news,alist);
                runOnUiThread(new Thread() {
                    public void run() {
                        listView.setAdapter(adapter);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.tx_toutiao:
                new Thread(){
                    public void run(){
                        getDataByType("");
                    }
                }.start();
                alist.clear();//清空集合
                break;
            case R.id.tx_guonei:
                new Thread(){
                    public void run(){
                        getDataByType("shehui");
                    }
                }.start();
                alist.clear();//清空集合
                break;
            case R.id.tx_guoji:
                new Thread(){
                    public void run(){
                        getDataByType("guonei");
                    }
                }.start();
                alist.clear();//清空集合
                break;
            case R.id.tx_shehui:
                new Thread(){
                    public void run(){
                        getDataByType("junshi");
                    }
                }.start();
                alist.clear();//清空集合
                break;
            case R.id.tx_tiyu:
                new Thread(){
                    public void run(){
                        getDataByType("shehui");
                    }
                }.start();
                alist.clear();//清空集合
                break;
            case R.id.tx_keji:
                new Thread(){
                    public void run(){
                        getDataByType("shehui");
                    }
                }.start();
                alist.clear();//清空集合
                break;
            case R.id.tx_yule:
                new Thread(){
                    public void run(){
                        getDataByType("shehui");
                    }
                }.start();
                alist.clear();//清空集合
                break;
            case R.id.tx_junshi:
                new Thread(){
                    public void run(){
                        getDataByType("shehui");
                    }
                }.start();
                alist.clear();//清空集合
                break;
        }
    }
}
