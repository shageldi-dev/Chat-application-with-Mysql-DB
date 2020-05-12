package com.example.oguzhanchat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class Barada extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barada);
        webView=(WebView) findViewById(R.id.web1);

        webView.loadUrl("file:///android_asset/about.html");
    }
}
