package com.esgi.guitton.candice.ftaproject.cache;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.esgi.guitton.candice.ftaproject.Item;

import java.util.ArrayList;

/**
 * Created by candiceguitton on 04/02/2018.
 */

public class DataBaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "dtb";
    public static final int VERSION = 1;
    private static final String CREATE_TABLE_ITEM =
            "CREATE TABLE  "
                    + Item.TABLE_NAME + " ( "
                    + Item.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + Item.COL_NAME + " VARCHAR, "
                    + Item.COL_PATH + " VARCHAR,"
                    + Item.COL_ID_USER + " VARCHAR)";

    public DataBaseHelper(Context context, String name, int version) {
        super(context, name, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ITEM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addItem(Item item) {


        if(!checkItem(item)) {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            final ContentValues row = new ContentValues();
            row.put(Item.COL_NAME,item.getName());
            row.put(Item.COL_PATH,item.getPath());
            row.put(Item.COL_ID_USER,item.getId_user());
            writableDatabase.insert(Item.TABLE_NAME, null, row);
            writableDatabase.close();

        }
    }

    private boolean checkItem(Item item) {
        SQLiteDatabase readableDatabase = getReadableDatabase();

        Cursor cursor = readableDatabase.query(Item.TABLE_NAME,
                new String[]{Item.COL_NAME}, Item.COL_NAME + "=?",
                new String[]{item.getName()}, null, null, null, null);
        if (cursor.moveToFirst()) {
            cursor.close();
            readableDatabase.close();
            return true;
        }else {
            cursor.close();
            readableDatabase.close();
            return false;
        }

    }

    public ArrayList<Item> getItems() {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        final ContentValues rows = new ContentValues();
        final ArrayList<Item> items = new ArrayList<>();

        String query = "SELECT * FROM " + Item.TABLE_NAME;
        Cursor cursor = readableDatabase.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                String name = cursor.getString(1);
                String path = cursor.getString(2);
                String id_user = cursor.getString(3);
                Item item = new Item(name, path, id_user);
                items.add(item);
            }while(cursor.moveToNext());
        }

        cursor.close();
        readableDatabase.close();
        return items;
    }

}
