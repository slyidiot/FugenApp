package com.fugenapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_EVENTS = "events";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_EVENT_NAME = "event_name";
    public static final String COLUMN_EVENT_DATE = "event_date";
    public static final String COLUMN_EVENT_TIME = "event_time";
    public static final String COLUMN_EVENT_VENUE = "event_venue";
    public static final String COLUMN_EVENT_DESC = "event_desc";
    public static final String COLUMN_EVENT_CAT = "event_cat";
    public static final String COLUMN_EVENT_IMAGE = "event_image";

    private static final String DATABASE_NAME = "fugenapp.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "create table" +
            TABLE_EVENTS + "( " + COLUMN_ID + "integer primary key autoincrement, " +
            COLUMN_EVENT_NAME + "text not null, " + COLUMN_EVENT_DATE + "text not null, " +
            COLUMN_EVENT_TIME + "text not null, " + COLUMN_EVENT_VENUE + "text not null, " +
            COLUMN_EVENT_DESC + "text not null, " + COLUMN_EVENT_DESC + "text not null, " +
            COLUMN_EVENT_CAT + "text not null, " + COLUMN_EVENT_IMAGE + "text not null);";


    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_EVENTS);
        onCreate(sqLiteDatabase);
    }
}
