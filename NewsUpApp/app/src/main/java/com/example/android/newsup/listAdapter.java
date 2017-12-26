package com.example.android.newsup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by nitthin on 2/4/2017.
 */

public  class listAdapter extends ArrayAdapter<list> {


    public listAdapter(Context context, ArrayList<list> newslist) {

        super(context, 0, newslist);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        list currentlist = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list,parent,false);
        }

        TextView titleView = (TextView) convertView.findViewById(R.id.title_textView);
        titleView.setText(currentlist.getmTitle());

        TextView sectionView = (TextView) convertView.findViewById(R.id.section_textView);
        sectionView.setText(currentlist.getmSectionName());

        TextView webUrlView = (TextView) convertView.findViewById(R.id.webUrl_textView);
        webUrlView.setText(currentlist.getmwebUrl());


        return convertView;
    }

}
