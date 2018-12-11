package ru.voronezhtsev.contentprovider.client.data;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.content.ContentResolver;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

/**
 * Класс предоставляет доступ к заметкам
 *
 * @author Воронежцев Игорь on 11.12.2018
 */
public class NotesDAO {

    private static final Uri NOTES_URI = Uri.parse(
            "content://ru.voronezhtsev.contentproviderlesson/notes"
    );

    private static final Uri INSERT_URI = Uri.parse(
            "content://ru.voronezhtsev.contentproviderlesson/notes/add"
    );

    private static final String NOTE_ID = "id";

    private static final String GET_BY_ID_URI =
            "content://ru.voronezhtsev.contentproviderlesson/notes/";

    private static final Uri DELETE_URI = Uri.parse(
            "content://ru.voronezhtsev.contentproviderlesson/notes/delete"
    );

    private static final String UPDATE_NOTE = "content://ru.voronezhtsev.contentproviderlesson/notes/update/";
    private static final String ID_COLUMN = "id";
    private Context mContext;

    /**
     * Конструктор по умолчанию
     *
     * @param context конекст, необходим для вызова {@link Context#getContentResolver()}
     */
    public NotesDAO(Context context) {
        mContext = context;
    }

    /**
     * @return Список заметок блокнота, используя
     * {@link ContentResolver#query(Uri, String[], Bundle, CancellationSignal)})}
     */
    @NonNull
    public List<Note> getNotes() {
        Cursor cursor = mContext.getContentResolver().query(NOTES_URI,
                null, null, null);
        return ConvertUtils.convertToNotes(cursor);
    }

    /**
     * Вернуть заметку по ключу {@link Note#getId()}
     * @param id ключ заметки, с которой она была сохранена в блокноте
     * @return Заметка или {@code null} если не найдена
     */
    @Nullable
    public Note getNote(long id) {
        Uri uri = Uri.parse(GET_BY_ID_URI + id);
        Cursor cursor = mContext.getContentResolver().query(uri, null, null,
                null, null);
        return ConvertUtils.convertToNote(cursor);
    }

    public void insert(Note note) {
        mContext.getContentResolver().insert(INSERT_URI,ConvertUtils.convertNote(note));
    }

    /**
     * Удалить заметку по ключу
     *
     * @param id ключ заметки {@link Note#getId()}
     */
    public void delete(long id) {
        mContext.getContentResolver().delete(DELETE_URI, ID_COLUMN + "=?",
                new String[]{String.valueOf(id)});
    }

    /**
     * Обновить заметку
     * @param note заметка, которую следует обновить, для поиска используется
     *             {@link Note#getId()}, для обновления {@link Note#getNote()}
     */
    public void update(Note note) {
        Uri uri = Uri.parse(UPDATE_NOTE + note.getId());
        mContext.getContentResolver().update(uri, ConvertUtils.convertNote(note), null, null);
    }
}
