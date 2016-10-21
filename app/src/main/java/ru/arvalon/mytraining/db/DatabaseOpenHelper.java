package ru.arvalon.mytraining.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by arvalon on 07.06.2016.
 */
public class DatabaseOpenHelper extends SQLiteAssetHelper{

    private static final String DATABASE_NAME = "DB2.sqlite";
    private static final int DATABASE_VERSION = 1;

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
