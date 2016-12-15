package com.example.administrator.on_off_button.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.example.administrator.on_off_button.R;

/**
 * Created by yangchun on 2016-12-15.
 */
public class AndroidCallJsActivity extends AppCompatActivity{

    private Button btn_show;
    private WebView wv;
    private JsInterface JSInterface2;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_call_js);

        btn_show = (Button) findViewById(R.id.btn_java2js_show);
        wv = (WebView) findViewById(R.id.wv_js2java);
        JSInterface2 = new JsInterface(wv);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.addJavascriptInterface(JSInterface2, "JSInterface");
        wv.setWebViewClient(new webviewClient());

        wv.loadUrl("file:///android_asset/index.html");
    }

    class webviewClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            btn_show.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    JSInterface2.javaCallJsFunction(1);
                }
            });
        }
    }
}
