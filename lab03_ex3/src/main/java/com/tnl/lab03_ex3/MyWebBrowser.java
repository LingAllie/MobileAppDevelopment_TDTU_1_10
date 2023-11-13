package com.tnl.lab03_ex3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class MyWebBrowser extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_web_browser);
        webView = findViewById(R.id.webView);

        String url = getIntent().getData().toString();
        webView.loadUrl(url);
    }
}