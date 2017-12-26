package com.example.android.newsup;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by nitthin on 2/5/2017.
 */

public class QueryUtils {
    public static final String LOG_TAG = QueryUtils.class.getSimpleName();

    public static ArrayList<list> fetchNews(String stringUrl){
        URL url = createUrl(stringUrl);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        }catch (IOException e){
            Log.e(LOG_TAG, "Error closing input stream", e);
        }
        ArrayList<list> lists = extractNews(jsonResponse);
        return lists;
    }

    public static URL createUrl(String string){
        URL url = null;
        try{
            url = new URL(string);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG,"problem in building url",e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = null;
        if(url == null){
            return  jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream stream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.connect();
            if(urlConnection.getResponseCode() == 200) {
                stream = urlConnection.getInputStream();
                jsonResponse = readFromStream(stream);
            }else {
                Log.e(LOG_TAG,"error response code :" + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        }
        finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (stream != null) {
                stream.close();
            }
        }
        return jsonResponse;
    }
    public static String readFromStream(InputStream stream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (stream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(stream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static ArrayList<list> extractNews(String jsonResponse) {
        ArrayList<list> lists = new ArrayList<>();
        try {
            JSONObject baseJson = new JSONObject(jsonResponse);
            JSONObject response = baseJson.getJSONObject("response");
            JSONArray results = response.getJSONArray("results");
            for(int i = 0;i<results.length();i++){
                JSONObject details = results.getJSONObject(i);
                String sectionName = details.getString("sectionName");
                String title = details.getString("webTitle");
                String webUrl = details.getString("webUrl");
                list list = new list(title,sectionName,webUrl);
                lists.add(list);
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the earthquake JSON results", e);
        }
        return lists;


    }
}
