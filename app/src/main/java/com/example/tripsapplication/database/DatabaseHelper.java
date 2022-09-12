package com.example.tripsapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "TRIPS";
    public static final String TABLE_NAME2 = "EXPENSES";

    // Table columns
    public static final String _ID = "_id";
    public static final String NAME = "NAME";
    public static final String DESTINATION = "DESTINATION";
    public static final String DOTS = "DATEOFTRIPS";
    public static final String REQUIREASSESSEMENT = "REQUIREASSESSEMENT";
    public static final String DESCRIPTION = "DESCRIPTION";


    // database version
    static final int DB_VERSION = 1;

    // Database Information
    static final String DB_NAME = "TRIPS.DB";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT NOT NULL, " + DESTINATION + " TEXT NOT NULL, " + DOTS + " TEXT NOT NULL," + REQUIREASSESSEMENT + " TEXT NOT NULL, " + DESCRIPTION + " TEXT );";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
