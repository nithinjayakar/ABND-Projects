package com.example.android.newsup;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by nitthin on 2/5/2017.
 */

public class NewsLoader extends AsyncTaskLoader<List<list>> {
    private static final String LOG_TAG = NewsLoader.class.getName();
    private  String url;

    public NewsLoader(Context context, String murl) {
        super(context);
        url = murl;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<list> loadInBackground() {
        if (url == null) {
            return null;
        }
        List<list> result = QueryUtils.fetchNews(url);
        return result;
    }
}

