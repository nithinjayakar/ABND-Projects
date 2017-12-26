package com.example.android.newsup;

/**
 * Created by nitthin on 2/4/2017.
 */

public  class list {
    private  String mTitle;
    private  String mSectionName;
    private  String mwebUrl;

    public list(String title,String sectionName,String webUrl){
        mTitle = title;
        mSectionName = sectionName;
        mwebUrl = webUrl;

    }
    public String getmTitle() {
        return mTitle;
    }
    public String getmSectionName() {
        return mSectionName;
    }

    public String getmwebUrl() {
        return mwebUrl;
    }
}
