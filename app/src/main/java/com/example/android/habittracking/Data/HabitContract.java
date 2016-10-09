package com.example.android.habittracking.Data;

import android.provider.BaseColumns;

/**
 * Created by yishuyan on 9/25/16.
 */

public final class HabitContract {

    private HabitContract() {}

    public static final class HabitEntry implements BaseColumns {

        public static final String TABLE_NAME = "habit_tracking";

        public static final String _ID = BaseColumns._ID;

        public static final String COLUMN_HABIT_ITEM = "HabitItem";

        public static final String COLUMN_HABIT_DURATION = "Duration";

        public static final String COLUMN_HABIT_FREQUENCY = "Frequency";

        public static final String COLUMN_HABIT_PLACE = "Place";
    }
}
