package com.example.tripsapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManger {

    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DatabaseManger(Context c) {
        context = c;
    }

    public DatabaseManger open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String name, String destination, String dots, String requireAssessment, String description) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.NAME, name);
        contentValue.put(DatabaseHelper.DESTINATION, destination);
        contentValue.put(DatabaseHelper.DOTS, dots);
        contentValue.put(DatabaseHelper.REQUIREASSESSEMENT, requireAssessment);
        contentValue.put(DatabaseHelper.DESCRIPTION, description);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[]{DatabaseHelper._ID, DatabaseHelper.NAME, DatabaseHelper.DESTINATION, DatabaseHelper.DOTS, DatabaseHelper.REQUIREASSESSEMENT, DatabaseHelper.DESCRIPTION};
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }


    public int update(long _id, String name, String destination, String dots, String requireAssessment, String description) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NAME, name);
        contentValues.put(DatabaseHelper.DESTINATION, destination);
        contentValues.put(DatabaseHelper.DOTS, dots);
        contentValues.put(DatabaseHelper.REQUIREASSESSEMENT, requireAssessment);
        contentValues.put(DatabaseHelper.DESCRIPTION, description);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }


}
