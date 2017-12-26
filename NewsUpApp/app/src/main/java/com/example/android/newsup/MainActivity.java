package com.example.android.newsup;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<list>> {
    public static final String NEWS_REQUEST_URL = "http://content.guardianapis.com/search?q=politics&api-key=test";
    listAdapter lists;
    private static final int NEWS_LOADER_ID = 1;
    @Override
    public Loader<List<list>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(this,NEWS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<list>> loader, List<list> data) {
        lists.clear();

        if (data != null && !data.isEmpty()) {
            lists.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<list>> loader) {
        lists.clear();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lists = new listAdapter(MainActivity.this,new ArrayList<list>());
        ListView listview = (ListView) findViewById(R.id.myListView);
        listview.setAdapter(lists);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {


                list currentlist = lists.getItem(position);

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(currentlist.getmwebUrl()));
                startActivity(i);
            }
        });

        listview.setEmptyView(findViewById(R.id.empty_list_view));

        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isConnected = cm.getActiveNetworkInfo() != null;
        if(isConnected) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(0, null, this);
        }else{
            Toast.makeText(this,"connect to network",Toast.LENGTH_SHORT).show();
        }
    }
}
