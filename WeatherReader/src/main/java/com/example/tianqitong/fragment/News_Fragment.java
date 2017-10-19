package com.example.tianqitong.fragment;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.tianqitong.R;


/**
 * Created by Administrator on 2017/6/1 0001.
 */

public class News_Fragment extends Fragment {
    View view;
    WebView webView;
    ProgressDialog dialog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        init();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = View.inflate(getActivity(), R.layout.activity_news,null);
    }
    void init(){
        dialog = new ProgressDialog(getActivity());
        dialog.setTitle("正在加载中......");
        webView = (WebView) view.findViewById(R.id.news_webView);
        webView.loadUrl("http://www.sohu.com/");
        //    给webView设置常用属性
        WebSettings webSettings = webView.getSettings();
//    设置兼容
        webSettings.setJavaScriptEnabled(true);
//        支持缩放
        webSettings.setSupportZoom(true);
//处理链接事件
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                webView.loadUrl(request.getUrl().toString());
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                dialog.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                dialog.dismiss();
            }
        });
    }

}
