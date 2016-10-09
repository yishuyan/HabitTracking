package com.example.android.habittracking;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.habittracking.Data.HabitContract.HabitEntry;
import com.example.android.habittracking.Data.HabitDbHelper;

/**
 * Created by yishuyan on 9/25/16.
 */

public class EditorActivity extends AppCompatActivity{

    private EditText itemEditText, durationEditText, frequencyEditText, placeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.habit_edit);

        itemEditText = (EditText) findViewById(R.id.habit_title);
        durationEditText = (EditText) findViewById(R.id.habit_duration);
        frequencyEditText = (EditText) findViewById(R.id.habit_frequency);
        placeEditText = (EditText) findViewById(R.id.habit_place);
    }

    private void insertPet() {
        String itemString = itemEditText.getText().toString().trim();
        String durationString = durationEditText.getText().toString().trim();
        String frequencyString = frequencyEditText.getText().toString().trim();
        String placeString = placeEditText.getText().toString().trim();
        Log.i("Input: ", itemString + ", " + durationString + ", " + frequencyString + "," + placeString);

        HabitDbHelper habitDbHelper = new HabitDbHelper(this);
        SQLiteDatabase db = habitDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_ITEM, itemString);
        values.put(HabitEntry.COLUMN_HABIT_DURATION, durationString);
        values.put(HabitEntry.COLUMN_HABIT_FREQUENCY, frequencyString);
        values.put(HabitEntry.COLUMN_HABIT_PLACE, placeString);

//        ContentValues values = new ContentValues();
//        values.put(HabitEntry.COLUMN_HABIT_ITEM, "1");
//        values.put(HabitEntry.COLUMN_HABIT_DURATION, "2");
//        values.put(HabitEntry.COLUMN_HABIT_FREQUENCY, "3");
//        values.put(HabitEntry.COLUMN_HABIT_PLACE, "4");

        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);

        if(newRowId == -1) {
            Toast.makeText(this, "Error with saving habits", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Habit saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_habit:
                insertPet();
                finish();
                return true;
            case R.id.delete_habit:
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
