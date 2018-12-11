package ru.voronezhtsev.contentprovider.client.data;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import ru.voronezhtsev.contentprovider.client.data.Note;

public class ConvertUtils {
    private static final String ID = "id";
    private static final String NOTE = "note";

    static ContentValues convertNote(Note note) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, note.getId());
        contentValues.put(NOTE, note.getNote());
        return contentValues;
    }

    static List<Note> convertToNotes(Cursor cursor) {
        List<Note> notes = new ArrayList<>();
        while (cursor.moveToNext()) {
            Note note = new Note();
            note.setId(cursor.getInt(cursor.getColumnIndex(ID)));
            note.setNote(cursor.getString(cursor.getColumnIndex(NOTE)));
            notes.add(note);
        }
        cursor.close();
        return notes;
    }

    static Note convertToNote(Cursor cursor) {
        while (cursor.moveToNext()) {
            Note note = new Note();
            note.setId(cursor.getInt(cursor.getColumnIndex(ID)));
            note.setNote(cursor.getString(cursor.getColumnIndex(NOTE)));
            return note;
        }
        cursor.close();
        return null;
    }
}
