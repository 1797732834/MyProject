package com.beicai.da.leida_test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * Created by wangye on 2017/8/28.
 */

public class ContentActivity extends Activity {
WebView webView;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.content);
    webView = findViewById(R.id.webview);
    webView.getSettings().setJavaScriptEnabled(true);
    Intent intent = getIntent();
    webView.loadUrl(intent.getStringExtra("url"));
  }
}
