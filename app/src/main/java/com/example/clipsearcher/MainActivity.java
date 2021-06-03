package com.example.clipsearcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity {

    private EditText searchText;
    String searchWord;
    Button searchButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchText = findViewById(R.id.editTextSearchText);
        searchButton = findViewById(R.id.buttonSearch);
        searchText.addTextChangedListener(searchTextWatcher);

        // Get clipboard content and display it on the search box

        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = clipboardManager.getPrimaryClip();

        if(clipData != null){
        CharSequence clipChar = clipData.getItemAt(0).getText();

            searchText.setText(clipChar.toString());
            Log.i("Clipboard data",clipChar.toString());
        }

        //if search box empty disable button

        if(TextUtils.isEmpty(searchWord)){
            searchButton.setEnabled(false);
        }


    }

    //method to search and display webview

    public void getSearchResults(View view) {

        Intent searchIntent = new Intent(MainActivity.this,ResultActivity.class);
        searchIntent.putExtra("query","searchWord");
        startActivity(searchIntent);

    }

    //method to watch for text changes in the search box

    private TextWatcher searchTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            searchWord = searchText.getText().toString();
            if(!TextUtils.isEmpty(searchWord)){
                searchButton.setEnabled(true);
            }else{
                searchButton.setEnabled(false);
            }

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };



}