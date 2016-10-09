package com.example.android.habittracking;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.habittracking.Data.HabitContract.HabitEntry;
import com.example.android.habittracking.Data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    private HabitDbHelper habitDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addButton = (Button) findViewById(R.id.fab);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        habitDbHelper =  new HabitDbHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }
    /**display database information on the screen.*/
    private void displayDatabaseInfo() {
        SQLiteDatabase db = habitDbHelper.getReadableDatabase();

        String[] protection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_ITEM,
                HabitEntry.COLUMN_HABIT_DURATION,
                HabitEntry.COLUMN_HABIT_FREQUENCY,
                HabitEntry.COLUMN_HABIT_PLACE };

        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME,
                protection,
                null,
                null,
                null,
                null,
                null
        );

        TextView habitDisplayView = (TextView) findViewById(R.id.text_view_habit);
        try{
            habitDisplayView.setText("This habit tracking table contains " + cursor.getCount() + " habits.\n\n");
            habitDisplayView.append(HabitEntry._ID + " - " +
            HabitEntry.COLUMN_HABIT_ITEM + " - " +
            HabitEntry.COLUMN_HABIT_DURATION + " - " +
            HabitEntry.COLUMN_HABIT_FREQUENCY + " - " +
            HabitEntry.COLUMN_HABIT_PLACE + "\n");

            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_ITEM);
            int durationColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_DURATION);
            int frequencyColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_FREQUENCY);
            int placeColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_PLACE);

            while(cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String habitItem = cursor.getString(nameColumnIndex);
                String habitDuration = cursor.getString(durationColumnIndex);
                String habitFrequency = cursor.getString(frequencyColumnIndex);
                String habitPlace = cursor.getString(placeColumnIndex);

                habitDisplayView.append(("\n" + currentID + " - " +
                habitItem + " - " +
                habitDuration + " - " +
                habitFrequency + " - " +
                habitPlace));
            }
        }finally {
            cursor.close();
        }
    }

}
