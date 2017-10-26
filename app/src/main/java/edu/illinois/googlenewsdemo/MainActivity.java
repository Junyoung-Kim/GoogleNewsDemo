package edu.illinois.googlenewsdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class MainActivity extends AppCompatActivity {

    public static final String urlString =
            "https://newsapi.org/v1/articles?source=google-news&sortBy=top&apiKey=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LinearLayout listLayout = (LinearLayout) findViewById(R.id.list_layout);

        String url = urlString + NewsApi.API_KEY;
        NewsAsyncTask newsAsyncTask = new NewsAsyncTask(this, listLayout);
        newsAsyncTask.execute(url);
    }
}
