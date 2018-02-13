/*
 * MIT License
 *
 * Copyright (c) 2018 Vaisakh J Nair
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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
                break;
            case FugenApp.FUN_EVENTS:
                category = "fun";
                break;
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
