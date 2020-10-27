package edu.temple.androidwebapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class BrowserActivity extends AppCompatActivity implements PageControlFragment.PageControlListener {

    PageControlFragment controlFragment = new PageControlFragment();
    PageViewerFragment viewerFragment = new PageViewerFragment();
    WebView viewWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        if(savedInstanceState == null){
            addFragments();
        }else{
            controlFragment = (PageControlFragment)getSupportFragmentManager().findFragmentByTag("TAG1");
            viewerFragment = (PageViewerFragment)getSupportFragmentManager().findFragmentByTag("TAG2");
            getSupportFragmentManager().beginTransaction().replace(R.id.page_control, controlFragment).replace(R.id.page_viewer, viewerFragment).commit();



        }


    }


    public void addFragments(){
        getSupportFragmentManager().beginTransaction()
                .add(R.id.page_control, controlFragment, "TAG1")
                .add(R.id.page_viewer, viewerFragment, "TAG2")
                .commit();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

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
        if(!input.toString().startsWith("https://")) {
            webView.loadUrl("https://" + input.toString());
        }else{
            webView.loadUrl(input.toString());
        }

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