package com.jinaplus.weather;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.Collections;
import java.util.TreeSet;

/**
 * Created by jinaplus on 2017. 8. 7..
 */

public class SearchActivity extends AppCompatActivity {
    private static final String TAG = SearchActivity.class.getSimpleName();

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
        webView.setWebViewClient(new WebViewClient());
        handleIntent();
    }

    private void handleIntent() {
        Intent appLinkIntent = getIntent();
        Collections.addAll(localAarray, new String[]{"seoul", "incheon", "busan", "kyungki", "deagu", "deajeon"});
        // ATTENTION: This was auto-generated to handle app links.
        String appLinkAction = appLinkIntent.getAction();
        String data = appLinkIntent.getDataString();
        if (Intent.ACTION_VIEW.equals(appLinkAction) && data != null) {
            String local = data.substring(data.lastIndexOf("/") + 1);
            Log.i(TAG, "local = " + local);
            Log.i(TAG, "appLinkIntent.getData() = " + appLinkIntent.getData().toString());
            showWeather(local);
        }
    }

    private void showWeather(String local) {
        if(localAarray.contains(local)) {
            webView.loadUrl("https://www.google.co.kr/search?q=weather+" + local);
        } else {
            Toast.makeText(this, local + "은(는) 지원하지 않는 지역입니다.", Toast.LENGTH_SHORT).show();
        }
    }
}
