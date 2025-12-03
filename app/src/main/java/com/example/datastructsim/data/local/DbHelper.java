package com.example.datastructsim.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "datastructsim";
    private static final int DATABASE_VERSION = 1;
    private final String CREATE_TABLE_ITEM= "CREATE TABLE item (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "titulo TEXT);";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_ITEM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS item");
        onCreate(sqLiteDatabase);
    }
}
