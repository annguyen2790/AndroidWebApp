package edu.temple.androidwebapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class BrowserActivity extends AppCompatActivity implements PageControlFragment.PageControlListener {

    PageControlFragment controlFragment = new PageControlFragment();
    PageViewerFragment viewerFragment = new PageViewerFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.page_control, controlFragment)
                .add(R.id.page_viewer, viewerFragment)
                .commit();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setContentView(R.layout.activity_browser);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.page_control, controlFragment)
                .add(R.id.page_viewer, viewerFragment)
                .commit();
    }

    @Override
    public void onPush(CharSequence input) {
        WebView webView = viewerFragment.v.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient( new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                controlFragment.urlText.setText(url);
            }
        });
        webView.loadUrl(input.toString());

    }

    @Override
    public void onForward() {
        WebView webView = viewerFragment.v.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                controlFragment.urlText.setText(url);
            }
        });
        if(webView.canGoForward()){
            webView.goForward();
        }

    }

    @Override
    public void onBackward() {
        WebView webView = viewerFragment.v.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                controlFragment.urlText.setText(url);
            }
        });
        if(webView.canGoBack()){
            webView.goBack();
        }
    }
}