package edu.illinois.googlenewsdemo;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import static edu.illinois.googlenewsdemo.MainActivity.urlString;

/**
 * Created by zilles on 10/26/17.
 */

public class NewsAsyncTask extends AsyncTask<String, Integer, NewsCollection> {
    public static final String TAG = NewsAsyncTask.class.getSimpleName();

    private Context context;
    private LinearLayout listLayout;

    public NewsAsyncTask(Context context, LinearLayout listLayout) {
        this.context = context;
        this.listLayout = listLayout;
    }

    @Override
    protected NewsCollection doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);

            URLConnection connection = url.openConnection();
            connection.connect();

            InputStream inStream = connection.getInputStream();
            InputStreamReader inStreamReader = new InputStreamReader(inStream, Charset.forName("UTF-8"));

            Gson gson = new Gson();
            NewsCollection newsCollection = gson.fromJson(inStreamReader, NewsCollection.class);

            return newsCollection;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(NewsCollection newsCollection) {
        if (newsCollection == null) {
            return;
        }

        for (NewsArticle article: newsCollection.getArticles()) {
            Log.d(TAG, "article: " + article.getTitle());

            View newsListItem = LayoutInflater.from(context).
                    inflate(R.layout.news_list_item, listLayout, false);
            final TextView titleTextView = (TextView) newsListItem.findViewById(R.id.titleTextView);
            titleTextView.setText(article.getTitle());
            final TextView authorTextView = (TextView) newsListItem.findViewById(R.id.authorTextView);
            authorTextView.setText(article.getAuthor());

            listLayout.addView(newsListItem);
        }
    }
}
