package com.example.zhaoxinwu.shoppingListDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHandler extends SQLiteOpenHelper {
    // Info of the DB
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "shoppingItemDB";
    public static final String TABLE_NAME = "shoppingItem";
    public static final String COLUMN_ESHINAME = "EshiName";
    public static final String COLUMN_NUMBER = "Number";
    public static final String COLUMN_LOCATION = "Location";
    public static final String COLUMN_ITEMNAME = "ItemName";

    //Initialize the DB
    public DBHandler(Context context, SQLiteDatabase.CursorFactory factory)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = " CREATE TABLE " + TABLE_NAME +
                "(" +
                COLUMN_ESHINAME + " text, " +
                COLUMN_NUMBER + " text NOT NULL, " +
                COLUMN_LOCATION + " text NOT NULL, " +
                COLUMN_ITEMNAME + " text NOT NULL" +
                ");";
        db.execSQL(CREATE_TABLE);
        Log.d("DATABASECREATED!",  "CREATED DB!");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVesion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addHandler(ShoppingItem shoppingItem) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ESHINAME, shoppingItem.getInfo()[0]);
        values.put(COLUMN_NUMBER, shoppingItem.getInfo()[1]);
        Log.d("SHOPPING ITEM INFO", shoppingItem.getInfo()[0]);
        Log.d("SHOPPING ITEM INFO", shoppingItem.getInfo()[1]);
        Log.d("SHOPPING ITEM INFO", shoppingItem.getInfo()[2]);
        Log.d("SHOPPING ITEM INFO", shoppingItem.getInfo()[3]);
        values.put(COLUMN_LOCATION, shoppingItem.getInfo()[2]);
        values.put(COLUMN_ITEMNAME, shoppingItem.getInfo()[3]);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
        Log.d("FINISHED", "INSERTED");
    }

    public String loadHandler() {
        Log.d("STARTSEARCHINGDB", "SEARCHING");
        String result = "";
        String query = "Select * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while(cursor.moveToNext()) {
            String result_0 = cursor.getString(0); //eshiName
            String result_1 = cursor.getString(1); //number
            String result_2 = cursor.getString(2); //location
            String result_3 = cursor.getString(3); //itemName
            result += String.valueOf(result_0) + " " + String.valueOf(result_1) + " " + String.valueOf(result_2) +
                    " " + String.valueOf(result_3) + System.getProperty("line.separator");
            Log.d("RESULT", result);
        }
        cursor.close();
        db.close();
        return result;
    }

    public  ShoppingItem findHandler(String eshiName, String itemName) {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ESHINAME +"='" + eshiName +"'"
                + " AND " + COLUMN_ITEMNAME + "='" + itemName + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        ShoppingItem shoppingItem = new ShoppingItem("","","", "");
        if(cursor.moveToFirst()) {
            cursor.moveToFirst();
            shoppingItem.setInfo(cursor.getString(0),
                    cursor.getString(1), cursor.getString(2), cursor.getString(3));

        }
        else {
            shoppingItem = null;
        }
        db.close();
        return shoppingItem;
    }

    public boolean deleteHandler(String eshiName, String itemName) {
        boolean result = false;
        String query = "Select * FROM " + TABLE_NAME + " WHERE " + COLUMN_ESHINAME + "= ' " + eshiName
                + "'" + " AND " + COLUMN_ITEMNAME + "= '" + itemName + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        ShoppingItem shoppingItem = new ShoppingItem("","","","");
        if(cursor.moveToFirst()) {
            shoppingItem.setInfo(cursor.getString(0),
                    cursor.getString(1), cursor.getString(2), cursor.getString(3));
            db.delete(TABLE_NAME, COLUMN_ESHINAME + "='" + eshiName + "'" + " AND " + COLUMN_ITEMNAME + "= '" + itemName + "'",
                    new String[] {
                    String.valueOf(shoppingItem.getInfo()[0])}
                    );
            cursor.close();
            result = true;
        }
        return result;
    }

    public boolean updateHandler(String eshiName, String number, String location, String itemName ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_ESHINAME, eshiName);
        args.put(COLUMN_NUMBER, number);
        args.put(COLUMN_LOCATION, location);
        args.put(COLUMN_ITEMNAME, itemName);
        return db.update(TABLE_NAME, args, COLUMN_ESHINAME + "='" + eshiName + "'" + " AND " +
        COLUMN_ITEMNAME + "='" + itemName + "'", null) > 0;
    }
}
