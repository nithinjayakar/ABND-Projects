package com.example.android.rjy_tourapp;

/**
 * Created by nitthin on 1/6/2017.
 */

public class list {
    private static final int NO_IMAGE = -1;
    private String mPlaceName;
    private String mAdress;
    private String mNumber;
    private int mImageId = NO_IMAGE;
    public list(String PlaceName,String Adress,String Number) {
        mPlaceName = PlaceName;
        mAdress = Adress;
        mNumber = Number;
    }
    public list(String PlaceName,String Adress,String Number,int ImageId) {
        mPlaceName = PlaceName;
        mAdress = Adress;
        mNumber = Number;
        mImageId = ImageId;
    }

    public String getmPlaceName() {
        return mPlaceName;
    }

    public String getmAdress() {
        return mAdress;
    }

    public String getmNumber() {
        return mNumber;
    }

    public int getmImageId() {
        return mImageId;
    }
    public boolean hasImage(){
        return mImageId != NO_IMAGE;
    }
}

