package com.esgi.guitton.candice.ftaproject.cache;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.esgi.guitton.candice.ftaproject.HomePageActivity;
import com.esgi.guitton.candice.ftaproject.Item;
import com.esgi.guitton.candice.ftaproject.MainActivity;
import com.esgi.guitton.candice.ftaproject.User;
import com.esgi.guitton.candice.ftaproject.fragments.InventoryFragment;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

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

    private static final String CREATE_TABLE_FRIEND =
            "CREATE TABLE  "
                    + User.TABLE_NAME + " ( "
                    + User.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + User.COL_LASTNAME + " VARCHAR, "
                    + User.COL_FIRSTNAME + " VARCHAR,"
                    + User.COL_EMAIL + " VARCHAR,"
                    + User.COL_ID_USER + " VARCHAR)";
    private static final String GET_LAST_ITEM = " SELECT id, name, path, id_user " +
            "FROM "+ Item.TABLE_NAME +
            " WHERE " +Item.COL_ID + " = (SELECT max(id) FROM "+ Item.TABLE_NAME+ ")" ;


    public DataBaseHelper(Context context, String name, int version) {
        super(context, name, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ITEM);
        db.execSQL(CREATE_TABLE_FRIEND);
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




    public void addFriend(User user) {


        if(!checkUser(user)) {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            final ContentValues row = new ContentValues();
            row.put(User.COL_LASTNAME,user.getLastname());
            row.put(User.COL_FIRSTNAME,user.getFirstname());
            row.put(User.COL_EMAIL, user.getEmail());
            row.put(User.COL_ID_USER, user.getUser_id());
            writableDatabase.insert(User.TABLE_NAME, null, row);
            writableDatabase.close();

        }
    }

    private boolean checkUser(User user) {
        SQLiteDatabase readableDatabase = getReadableDatabase();

        Cursor cursor = readableDatabase.query(User.TABLE_NAME,
                new String[]{User.COL_FIRSTNAME}, User.COL_FIRSTNAME + "=?",
                new String[]{user.getFirstname()}, null, null, null, null);
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

    public ArrayList<User> getFriends() {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        final ContentValues rows = new ContentValues();
        final ArrayList<User> users = new ArrayList<>();

        String query = "SELECT * FROM " + User.TABLE_NAME;
        Cursor cursor = readableDatabase.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                String lastname = cursor.getString(1);
                String firstname = cursor.getString(2);
                String email = cursor.getString(3);
                String id_user = cursor.getString(4);
                User user = new User(lastname, firstname, email, id_user);
                users.add(user);
            }while(cursor.moveToNext());
        }

        cursor.close();
        readableDatabase.close();
        return users;
    }

    public Item getLastItem()
    {   Item item = null;
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor cursor = readableDatabase.rawQuery(GET_LAST_ITEM, null);

        if(cursor.moveToFirst()) {
            do {
                String id = cursor.getString(0);
                String name = cursor.getString(1);
                String path = cursor.getString(2);
                String id_user = cursor.getString(3);
                item = new Item(name, path, id_user);
            }while(cursor.moveToNext());
        }

        cursor.close();
        readableDatabase.close();
        return item;
    }

}
