package com.example.android.habittracking.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.habittracking.Data.HabitContract.HabitEntry;

/**
 * Created by yishuyan on 9/25/16.
 */

public class HabitDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = HabitDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "habits.db";

    private static final int DATABASE_VERSION = 3;

    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**create a new table.*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_HABITS_TABLE = "CREATE TABLE " + HabitEntry.TABLE_NAME + " ("
                + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitEntry.COLUMN_HABIT_ITEM + " TEXT NOT NULL, "
                + HabitEntry.COLUMN_HABIT_DURATION + " TEXT, "
                + HabitEntry.COLUMN_HABIT_FREQUENCY + " INTEGER NOT NULL, "
                + HabitEntry.COLUMN_HABIT_PLACE + " TEXT );";
        db.execSQL(SQL_CREATE_HABITS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
