package com.example.android.inventoryapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.NavUtils;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.inventoryapp.data.ProductContract.ProductEntry;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static com.example.android.inventoryapp.data.ProductProvider.LOG_TAG;

public class Details extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    EditText nameFIELD;
    EditText priceFIELD;
    Button minusField;
    public TextView quantityField;
    Button plusField;
    Button upload;
    RadioButton yesRadioButtonField;
    RadioButton noRadioButtonField;
    int quantity = 1;
    static final int PICK_IMAGE_REQUEST = 0;
    private ImageView mImageView;
    private static final String STATE_URI = "STATE_URI";
    Uri mUri;
    private Bitmap mBitmap;
    private static final int EXISTING_PET_LOADER = 0;
    private Uri currentUri;
    private boolean mProductHasChanged = false;
    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            mProductHasChanged = true;
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent i = getIntent();
        currentUri = i.getData();

        if(currentUri == null){
            setTitle("Add Product");
            invalidateOptionsMenu();
        }
        else{
            setTitle("Edit Product");
            getSupportLoaderManager().initLoader(EXISTING_PET_LOADER, null, this);
        }

        nameFIELD = (EditText)findViewById(R.id.edit_product_name);

        quantityField = (TextView) findViewById(R.id.quantity_text_view);

        priceFIELD = (EditText)findViewById(R.id.edit_product_price);

        mImageView = (ImageView) findViewById(R.id.image);

        ViewTreeObserver viewTreeObserver = mImageView.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mImageView.getViewTreeObserver();
                mImageView.setImageBitmap(getBitmapFromUri(mUri));
            }
        });

        upload = (Button) findViewById(R.id.upLoad_Button);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImageSelector();
            }
        });

        plusField = (Button) findViewById(R.id.plus_Button);
        plusField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quantity >= 1000){
                    Toast.makeText(Details.this,"You cannot have more than 1000 products",Toast.LENGTH_SHORT).show();
                    return;
                }
                quantity++;
                display(quantity);
            }
        });
        minusField = (Button) findViewById(R.id.minus_Button);
        minusField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quantity <= 1){
                    Toast.makeText(Details.this,"You cannot have less than 1 product",Toast.LENGTH_SHORT).show();
                    return;
                }
                quantity--;
                display(quantity);
            }
        });
        Button order = (Button)findViewById(R.id.order_button);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ProductName = nameFIELD.getText().toString();
                Intent i = new Intent(Intent.ACTION_SENDTO);
                i.setData(Uri.parse("mailto:"));
                i.putExtra(Intent.EXTRA_SUBJECT,"get more orderes for this product: " + ProductName);
                if (i.resolveActivity(getPackageManager()) != null) {
                    startActivity(i);
                }
            }
        });

        yesRadioButtonField = (RadioButton) findViewById(R.id.yes_radioButton);

        noRadioButtonField = (RadioButton) findViewById(R.id.no_radioButton);

        nameFIELD.setOnTouchListener(mTouchListener);
        priceFIELD.setOnTouchListener(mTouchListener);
        quantityField.setOnTouchListener(mTouchListener);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mUri != null)
            outState.putString(STATE_URI, mUri.toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState.containsKey(STATE_URI) &&
                !savedInstanceState.getString(STATE_URI).equals("")) {
            mUri = Uri.parse(savedInstanceState.getString(STATE_URI));
        }
    }
    private void saveProduct() {
        String name = nameFIELD.getText().toString().trim();
        String price = priceFIELD.getText().toString().trim();
        String quantityString = quantityField.getText().toString().trim();

        String imageIdString;

        if (mUri != null) {
            imageIdString = mUri.toString();
        } else {
            imageIdString = "";
        }

        boolean yes = yesRadioButtonField.isChecked();

        if ( currentUri == null &&
                TextUtils.isEmpty(name) || TextUtils.isEmpty(price) ||
                TextUtils.isEmpty(quantityString)||TextUtils.isEmpty(imageIdString)){
            Toast.makeText(this, "Please, enter all the item information.",Toast.LENGTH_SHORT).show();
            return;
        }
        ContentValues values = new ContentValues();
        values.put(ProductEntry.COLUMN_PRODUCT_NAME, name);
        values.put(ProductEntry.COLUMN_PRODUCT_PRICE, price);
        values.put(ProductEntry.COLUMN_PRODUCT_KEY_IMAGE, imageIdString);

        int quantity = 0;
        if (!TextUtils.isEmpty(quantityString)) {
            quantity = Integer.parseInt(quantityString);
        }
        values.put(ProductEntry.COLUMN_PRODUCT_QUANTITY,quantity);

        if(yes) {
            values.put(ProductEntry.COLUMN_PRODUCT_MEMBERSHIP,ProductEntry.YES );
        }else {
            values.put(ProductEntry.COLUMN_PRODUCT_MEMBERSHIP,ProductEntry.NO );
        }
        if (currentUri == null) {

            Uri newUri = getContentResolver().insert(ProductEntry.CONTENT_URI, values);

            if (newUri == null) {
                Toast.makeText(this, getString(R.string.editor_insert_Product_failed),
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.editor_insert_Product_successful),
                        Toast.LENGTH_SHORT).show();
            }

        } else {

            int rowsAffected = getContentResolver().update(currentUri, values, null, null);
            if (rowsAffected == 0) {

                Toast.makeText(this, getString(R.string.editor_update_product_failed),
                        Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(this, getString(R.string.editor_update_product_successful),
                        Toast.LENGTH_SHORT).show();
            }
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (currentUri == null) {
            MenuItem menuItem = menu.findItem(R.id.action_delete);
            menuItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_save:
                saveProduct();
                finish();
                return true;

            case R.id.action_delete:
                showDeleteConfirmationDialog();
                return true;

            case android.R.id.home:
                if (!mProductHasChanged) {
                    NavUtils.navigateUpFromSameTask(Details.this);
                    return true;
                }
                DialogInterface.OnClickListener discardButtonClickListener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                NavUtils.navigateUpFromSameTask(Details.this);
                            }
                        };
                showUnsavedChangesDialog(discardButtonClickListener);

                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        if (!mProductHasChanged) {
            super.onBackPressed();
            return;
        }
        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        finish();
                    }
                };
        showUnsavedChangesDialog(discardButtonClickListener);
    }
    private void display(int number) {
        quantityField = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityField.setText("" + number);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                ProductEntry._ID,
                ProductEntry.COLUMN_PRODUCT_KEY_IMAGE,
                ProductEntry.COLUMN_PRODUCT_NAME,
                ProductEntry.COLUMN_PRODUCT_PRICE,
                ProductEntry.COLUMN_PRODUCT_QUANTITY,
                ProductEntry.COLUMN_PRODUCT_MEMBERSHIP};
        return new CursorLoader(this,currentUri,projection,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor == null || cursor.getCount() < 1) {
            return;
        }
        if (cursor.moveToFirst()) {
            int nameColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_NAME);
            int breedColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_PRICE);
            int genderColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_QUANTITY);
            int weightColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_MEMBERSHIP);

            int imageColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_KEY_IMAGE);

            String name = cursor.getString(nameColumnIndex);
            String price = cursor.getString(breedColumnIndex);
            int quantity = cursor.getInt(genderColumnIndex);
            int member = cursor.getInt(weightColumnIndex);

            String imageId = cursor.getString(imageColumnIndex);
            if (!imageId.isEmpty()) {
                mUri = Uri.parse(imageId);
                mBitmap = this.getBitmapFromUri(mUri);
                mImageView.setImageBitmap(mBitmap);
            }


            nameFIELD.setText(name);
            priceFIELD.setText(price);
            quantityField.setText(Integer.toString(quantity));
            switch (member) {
                case ProductEntry.YES:
                    yesRadioButtonField.setChecked(true);
                    break;
                case ProductEntry.NO:
                    noRadioButtonField.setChecked(true);
                    break;
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        nameFIELD.setText("");
        priceFIELD.setText("");
        quantityField.setText("");
        yesRadioButtonField.setChecked(false);
        noRadioButtonField.setChecked(false);
    }
    private void showUnsavedChangesDialog(
            DialogInterface.OnClickListener discardButtonClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.unsaved_changes_dialog_msg);
        builder.setPositiveButton(R.string.discard, discardButtonClickListener);
        builder.setNegativeButton(R.string.keep_editing, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void showDeleteConfirmationDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_dialog_msg);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                deletePet();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void deletePet() {
        if (currentUri != null) {
            int rowsDeleted = getContentResolver().delete(currentUri, null, null);
            if (rowsDeleted == 0) {

                Toast.makeText(this, getString(R.string.editor_delete_product_failed),
                        Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(this, getString(R.string.editor_delete_product_successful),
                        Toast.LENGTH_SHORT).show();
            }
        }
        finish();
    }
    public void openImageSelector() {
        Intent intent;

        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
        } else {
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
        }

        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {


        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {


            if (resultData != null) {
                mUri = resultData.getData();
                Log.i(LOG_TAG, "Uri: " + mUri.toString());

                mImageView.setImageBitmap(getBitmapFromUri(mUri));
            }
        } else if (resultCode == Activity.RESULT_OK) {

        }
    }
    public Bitmap getBitmapFromUri(Uri uri) {

        if (uri == null || uri.toString().isEmpty())
            return null;

        int targetW = mImageView.getWidth();
        int targetH = mImageView.getHeight();

        InputStream input = null;
        try {
            input = this.getContentResolver().openInputStream(uri);

            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(input, null, bmOptions);
            input.close();

            int photoW = bmOptions.outWidth;
            int photoH = bmOptions.outHeight;


            int scaleFactor = Math.min(photoW / targetW, photoH / targetH);


            bmOptions.inJustDecodeBounds = false;
            bmOptions.inSampleSize = scaleFactor;
            bmOptions.inPurgeable = true;

            input = this.getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(input, null, bmOptions);
            input.close();
            return bitmap;

        } catch (FileNotFoundException fne) {
            Log.e(LOG_TAG, "Failed to load image.", fne);
            return null;
        } catch (Exception e) {
            Log.e(LOG_TAG, "Failed to load image.", e);
            return null;
        } finally {
            try {
                input.close();
            } catch (IOException ioe) {

            }
        }
    }
}
