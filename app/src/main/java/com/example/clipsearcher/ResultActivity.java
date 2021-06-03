package com.example.clipsearcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import static android.view.View.GONE;

public class ResultActivity extends AppCompatActivity {

    WebView searchWebView;
    String searchWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        searchWord = getIntent().getExtras().get("query").toString();
        loadWebView();


    }

    private void loadWebView() {
        searchWebView = findViewById(R.id.webviewSearchResult);
        searchWebView.setVisibility(View.VISIBLE);
        searchWebView.getSettings().setJavaScriptEnabled(true);
        searchWebView.getSettings().setAppCacheEnabled(true);
        searchWebView.loadUrl("https://duckduckgo.com/?q="+searchWord);
        searchWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                searchWebView.loadUrl(url);
                return false;
            }
        });
    }

    @Override
    public void onBackPressed(){

        if (searchWebView.canGoBack()){
            searchWebView.goBack();
        }else {
            finish();
        }
    }
}