package edu.illinois.googlenewsdemo;

import com.google.gson.Gson;

import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

//      <uses-permission android:name="android.permission.INTERNET" />

//      Picasso.with(context).load(newsArticle.getUrlToImage()).into(imageView);


/**
 * Created by zilles on 10/26/17.
 */
public class NewsCollectionTest {

    public static final String urlString =
            "https://newsapi.org/v1/articles?source=google-news&sortBy=top&apiKey=";
    @Test
    public void getArticles() throws Exception {

        URL url = new URL(urlString + NewsApi.API_KEY);

        URLConnection connection = url.openConnection();
        connection.connect();

        InputStream inStream = connection.getInputStream();
        InputStreamReader inStreamReader = new InputStreamReader(inStream, Charset.forName("UTF-8"));

        Gson gson = new Gson();
        NewsCollection newsCollection = gson.fromJson(inStreamReader, NewsCollection.class);

        for (NewsArticle article: newsCollection.getArticles()) {
            System.out.println(article.toString());
        }
    }

}