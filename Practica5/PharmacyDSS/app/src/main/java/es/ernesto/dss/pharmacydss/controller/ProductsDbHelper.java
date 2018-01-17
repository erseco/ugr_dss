package es.ernesto.dss.pharmacydss.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import es.ernesto.dss.pharmacydss.model.ProductModel;

/**
 * Created by ernesto on 12/01/2018.
 */

public class ProductsDbHelper extends SQLiteOpenHelper {

    private static ProductsDbHelper instance = null;

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE products (" +
                    "_id TEXT PRIMARY KEY," +
                    "name"+ TEXT_TYPE + COMMA_SEP +
                    "price" + TEXT_TYPE + COMMA_SEP +
                    "manufacturer" + TEXT_TYPE + COMMA_SEP +
                    "status" + TEXT_TYPE + " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS products";

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "products.db";

    public ProductsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        this.getWritableDatabase().execSQL("DELETE FROM products;");
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void insertProduct(String id, String name, String price, String manufacturer, String status) {
        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put("_id", id);
        values.put("name", name);
        values.put("price", price);
        values.put("manufacturer", manufacturer);
        values.put("status", status);


        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert("products", null, values);
    }

    public ProductModel getProduct(String _id) {


        SQLiteDatabase db = this.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                "_id",
                "name",
                "price",
                "manufacturer",
                "status"
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = "_id = ?";
        String[] selectionArgs = { _id };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                 "_id DESC";

        Cursor c = db.query(
                "products",                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        c.moveToFirst();

        ProductModel result = new ProductModel(
                c.getString(c.getColumnIndexOrThrow("_id")),
                c.getString(c.getColumnIndexOrThrow("name")),
                c.getString(c.getColumnIndexOrThrow("price")),
                c.getString(c.getColumnIndexOrThrow("manufacturer")),
                c.getString(c.getColumnIndexOrThrow("status"))
        );

        return result;
    }


    public List<ProductModel> getAllProducts() {


        SQLiteDatabase db = this.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                "_id",
                "name",
                "price",
                "manufacturer",
                "status"
        };


        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                "_id DESC";

        Cursor c = db.query(
                "products",                     // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        List<ProductModel> result = new ArrayList<ProductModel>();

        while (c.moveToNext()) {

            result.add(new ProductModel(
                    c.getString(c.getColumnIndexOrThrow("_id")),
                    c.getString(c.getColumnIndexOrThrow("name")),
                    c.getString(c.getColumnIndexOrThrow("price")),
                    c.getString(c.getColumnIndexOrThrow("manufacturer")),
                    c.getString(c.getColumnIndexOrThrow("status"))
            ));

        }

        c.close();

        return result;
    }


}
