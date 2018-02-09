package com.fugenapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.fugenapp.database.model.Event;

import java.util.ArrayList;
import java.util.List;

public class EventsDataSource {

    private SQLiteDatabase database;
    private SQLiteHelper sqLiteHelper;
    private String[] allColumns = {SQLiteHelper.COLUMN_ID, SQLiteHelper.COLUMN_EVENT_NAME,
            SQLiteHelper.COLUMN_EVENT_DATE, SQLiteHelper.COLUMN_EVENT_TIME,
            SQLiteHelper.COLUMN_EVENT_VENUE, SQLiteHelper.COLUMN_EVENT_DESC,
            SQLiteHelper.COLUMN_EVENT_CAT, SQLiteHelper.COLUMN_EVENT_IMAGE};

    public EventsDataSource(Context context) {
        sqLiteHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = sqLiteHelper.getWritableDatabase();
    }

    public void close() {
        sqLiteHelper.close();
    }

    public Event createEvent(String name, String date, String time, String venue,
                             String desc, String cat, String image) {

        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_EVENT_NAME, name);
        values.put(SQLiteHelper.COLUMN_EVENT_DATE, date);
        values.put(SQLiteHelper.COLUMN_EVENT_TIME, time);
        values.put(SQLiteHelper.COLUMN_EVENT_VENUE, venue);
        values.put(SQLiteHelper.COLUMN_EVENT_DESC, desc);
        values.put(SQLiteHelper.COLUMN_EVENT_CAT, cat);
        values.put(SQLiteHelper.COLUMN_EVENT_IMAGE, image);

        long insertId = database.insert(SQLiteHelper.TABLE_EVENTS, null, values);

        Cursor cursor = database.query(SQLiteHelper.TABLE_EVENTS,
                allColumns, SQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);

        cursor.moveToFirst();
        Event event = cursorToEvent(cursor);
        cursor.close();
        return event;

    }

    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<Event>();

        Cursor cursor = database.query(SQLiteHelper.TABLE_EVENTS, allColumns, null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Event event = cursorToEvent(cursor);
            events.add(event);
            cursor.moveToNext();
        }
        cursor.close();
        return events;
    }

    private Event cursorToEvent(Cursor cursor) {
        Event event = new Event();
        event.id = cursor.getLong(0);
        event.name = cursor.getString(1);
        event.date = cursor.getString(2);
        event.time = cursor.getString(3);
        event.venue = cursor.getString(4);
        event.desc = cursor.getString(5);
        event.cat = cursor.getString(6);
        event.image = cursor.getString(7);

        return event;
    }

}
