package com.fugenapp.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fugenapp.FugenApp;
import com.fugenapp.database.model.Event;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class FugenAppDatabase extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "fugenapp.db";
    private static final int DATABASE_VERSION = 1;

    public FugenAppDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public ArrayList<Event> getAllEvents() {

        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Event> result = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from events;", null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                result.add(cursorToEvent(cursor));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return result;
    }

    public ArrayList<Event> getFilteredEvents(int eventType) {

        String query = "select * from events where cat = ?;";
        String category = null;

        switch (eventType) {
            case FugenApp.FLAGSHIP_EVENTS:
                category = "flagship";
                break;
            case FugenApp.EYE_CATCHERS:
                category = "eyecatcher";
                break;
            case FugenApp.TECHNICAL_EVENTS:
                category = "technical";
        }

        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Event> result = new ArrayList<>();

        Cursor cursor = db.rawQuery(query, new String[]{category});
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                result.add(cursorToEvent(cursor));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return result;
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
        event.contact = cursor.getString(8);
        event.department = cursor.getString(9);
        return event;
    }

}
