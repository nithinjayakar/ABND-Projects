package com.example.android.inventoryapp;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.inventoryapp.data.ProductContract.ProductEntry;

import static com.example.android.inventoryapp.data.ProductContract.ProductEntry.COLUMN_PRODUCT_QUANTITY;

/**
 * Created by nitthin on 2/12/2017.
 */

public class ProductCursorAdpter extends CursorAdapter {


    public ProductCursorAdpter(Context context, Cursor c) {
        super(context, c, 0);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.listview, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        final TextView tproduct = (TextView) view.findViewById(R.id.product_textView);
        final TextView tquantity = (TextView) view.findViewById(R.id.quantity_text_view);
        final TextView tprice = (TextView) view.findViewById(R.id.price_textView);

        Button sell = (Button) view.findViewById(R.id.sell_Button);


        String product = cursor.getString(cursor.getColumnIndexOrThrow(ProductEntry.COLUMN_PRODUCT_NAME));
        int quantity = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_QUANTITY));
        String price = cursor.getString(cursor.getColumnIndexOrThrow(ProductEntry.COLUMN_PRODUCT_PRICE));
        int itemIDColumnIndex = cursor.getColumnIndex(ProductEntry._ID);
        final long itemId = cursor.getLong(itemIDColumnIndex);


        tproduct.setText("Product "+product);
        tquantity.setText(String.valueOf(quantity));
        tprice.setText("Price "+price);

        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri CurrentUri = ContentUris.withAppendedId(ProductEntry.CONTENT_URI, itemId);
                int quantity = Integer.parseInt(tquantity.getText().toString());
                if (quantity > 0) {

                    int newQuantity = quantity - 1;
                    ContentValues updateValues = new ContentValues();
                    updateValues.put(ProductEntry.COLUMN_PRODUCT_QUANTITY, newQuantity);

                    view.getContext().getContentResolver().update(CurrentUri, updateValues, null, null);

                }
            }
        });
    }
}
