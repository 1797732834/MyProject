package com.beicai.da.mainmusic;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import com.beicai.da.fragment.MyFind;
import com.beicai.da.fragment.MyMusic;
import com.beicai.da.fragment.MyZhu;

public class MainActivity extends FragmentActivity {


    //定义FragmentTabHost对象
    private FragmentTabHost mTabHost;

    //定义一个布局
    private LayoutInflater layoutInflater;

    //定义数组来存放Fragment界面
    private Class fragmentArray[] = {
            MyZhu.class,
            MyMusic.class,
            MyFind.class,

    };
    //Tab选项卡的文字
    //类型,,top(头条，默认),shehui(社会),guonei(国内),guoji(国际),yule(娱乐),tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)
    private String mTextviewArray[] = {
            "我 的", "音 乐", "发 现"};
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }
    private void initView(){
        //实例化布局对象
        layoutInflater = LayoutInflater.from(this);

        //实例化TabHost对象，得到TabHost
        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        //得到fragment的个数
        int count = fragmentArray.length;
        for(int i = 0; i < count; i++){
            //为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i]).setIndicator(getTabItemView(i));
            //将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
            //设置Tab按钮的背景
            mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.selector_tab_background);
        }
        }
    /**
     * 给Tab按钮设置文字
     */
    private View getTabItemView(int index){
        View view = layoutInflater.inflate(R.layout.zhupage_im, null);

        TextView textView = (TextView) view.findViewById(R.id.tv_tab);
        Log.d( "getTabItemView: ",mTextviewArray[index]);
        textView.setText(mTextviewArray[index]);

        return view;
    }
}

