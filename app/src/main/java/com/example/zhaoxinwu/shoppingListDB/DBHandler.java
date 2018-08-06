package com.example.zhaoxinwu.shoppingListDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {
    // Info of the DB
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "shoppingItem.db";
    private static final String TABLE_NAME = "shoppingItem";
    private static final String COLUMN_ESHINAME = "EshiName";
    private static final String COLUMN_NUBER = "Number";
    private static final String COLUMN_LOCATION = "Location";
    private static final String COLUMN_ITEMNAME = "ItemName";

    //Initialize the DB
    public DBHandler(Context context, SQLiteDatabase.CursorFactory factory)
    {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENT_TABLE = " CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ESHINAME + "TEXT" +
                COLUMN_NUBER + "TEXT" + COLUMN_LOCATION + "TEXT" + COLUMN_ITEMNAME + "TEXT" + ")";
        db.execSQL(CREATE_STUDENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVesion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addHandler(ShoppingItem shoppingItem) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ESHINAME, shoppingItem.getInfo()[0]);
        values.put(COLUMN_NUBER, shoppingItem.getInfo()[1]);
        values.put(COLUMN_LOCATION, shoppingItem.getInfo()[2]);
        values.put(COLUMN_ITEMNAME, shoppingItem.getInfo()[3]);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public String loadHandler() {
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
        args.put(COLUMN_NUBER, number);
        args.put(COLUMN_LOCATION, location);
        args.put(COLUMN_ITEMNAME, itemName);
        return db.update(TABLE_NAME, args, COLUMN_ESHINAME + "='" + eshiName + "'" + " AND " +
        COLUMN_ITEMNAME + "='" + itemName + "'", null) > 0;
    }
}
