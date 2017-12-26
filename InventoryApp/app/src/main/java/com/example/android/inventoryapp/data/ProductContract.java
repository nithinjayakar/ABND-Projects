package com.example.android.inventoryapp.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by nitthin on 2/8/2017.
 */

public final class ProductContract {
    public ProductContract(){}
    public static final class ProductEntry implements BaseColumns {

        public static final String CONTENT_AUTHORITY = "com.example.android.products";

        public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

        public static final String PATH_PETS = "products";

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PETS);




        public static final String TABLE_NAME = "products";

        public  static final String _ID = BaseColumns._ID;

        public static final  String COLUMN_PRODUCT_KEY_IMAGE  = "image_data";

        public static final String COLUMN_PRODUCT_NAME ="name";

        public static final String COLUMN_PRODUCT_PRICE = "price";

        public  static final String COLUMN_PRODUCT_QUANTITY = "quantity";

        public static final String COLUMN_PRODUCT_MEMBERSHIP = "membership";

        public static final int YES = 11;

        public static final int NO = 10;

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PETS;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PETS;


        public static boolean isValidMember(int member) {
            if (member == YES || member == NO) {
                return true;
            }
            return false;
        }
    }
}

