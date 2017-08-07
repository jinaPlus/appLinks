package com.jinaplus.weather;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.Toast;

import java.util.Collections;
import java.util.TreeSet;

/**
 * Created by jinaplus on 2017. 8. 7..
 */

public class SearchActivity extends AppCompatActivity {
    private WebView webView;
    private TreeSet<String> localAarray = new TreeSet<>();

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleIntent();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        webView = (WebView) findViewById(R.id.wv_search);
        handleIntent();
    }

    private void handleIntent() {
        Intent appLinkIntent = getIntent();
        Collections.addAll(localAarray, new String[]{"서울", "인천", "부산", "경기", "대구", "대전", "부산", "경주"});
        // ATTENTION: This was auto-generated to handle app links.
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();
        if (appLinkData != null) {
            String local = appLinkData.getLastPathSegment();
            showWeather(local);
        }
    }

    private void showWeather(String local) {
        if(localAarray.contains(local)) {
            webView.loadUrl("https://www.google.co.kr/search?q=날씨+" + local);
        } else {
            Toast.makeText(this, local + "은(는) 지원하지 않는 지역입니다.", Toast.LENGTH_SHORT).show();
        }
    }
}
