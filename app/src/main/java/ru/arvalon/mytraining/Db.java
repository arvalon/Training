package ru.arvalon.mytraining;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by arvalon on 24.04.2016.
 */
public class Db extends SQLiteOpenHelper {

    public static Db instance;

    public static final String DB_NAME="TrainingDB";
    public static final int VERSION=1;

    public static final String TrainingDB_Exercise_name="exercise_name";

    private Context context;

    private static final String All_EXERCISES="all_exercises";
    private static final String CREATE_TABLE_ALL_EXERCICES=
            "create table " + All_EXERCISES+
                    " ("+"_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    +TrainingDB_Exercise_name+" TEXT)";

    public Db(Context context) {
        super(context, DB_NAME, null, VERSION);
        this.context=context;
    }

    public static Db getInstance(Context context){
        if (instance==null){
            instance= new Db(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ALL_EXERCICES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
