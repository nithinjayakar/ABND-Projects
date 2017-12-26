package com.example.android.rjy_tourapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by nitthin on 1/6/2017.
 */

public class listAdapter extends ArrayAdapter<list> {

    public listAdapter(Activity context, ArrayList<list> list) {

        super(context, 0, list);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_items, parent, false);
        }
        list currentlist = getItem(position);

        TextView placeView = (TextView) listItemView.findViewById(R.id.place_textView);
        placeView.setText(currentlist.getmPlaceName());

        TextView addressView = (TextView) listItemView.findViewById(R.id.address_textView);
        addressView.setText(currentlist.getmAdress());

        TextView numberView = (TextView) listItemView.findViewById(R.id.number_textview);
        numberView.setText(currentlist.getmNumber());
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.allImage_imageView);
        if (currentlist.hasImage()) {
            imageView.setImageResource(currentlist.getmImageId());
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
        }
        return listItemView;
    }
}
