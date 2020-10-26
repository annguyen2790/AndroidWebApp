package edu.temple.androidwebapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
    public void onPush(CharSequence input) {
        TextView tv = viewerFragment.v.findViewById(R.id.textViewTest);
        tv.setText(input);
    }
}