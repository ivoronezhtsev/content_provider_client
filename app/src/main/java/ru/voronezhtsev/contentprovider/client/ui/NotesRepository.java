package ru.voronezhtsev.contentprovider.client.ui;

import java.util.List;

import ru.voronezhtsev.contentprovider.client.data.Note;
import ru.voronezhtsev.contentprovider.client.data.PreferencesDAO;

/**
 * Интерфейс доступа к настройкам и заметкам блокнота
 */
public interface NotesRepository {
    /**
     * @return размер текста из настроек, sp
     */
    String getTextSize();

    /**
     * @return цвет текста из настроек, например #ff000000
     */
    String getTextColor();

    /**
     * Сохранить размер шрифта в SharedPreferences {@link PreferencesDAO#saveTextSize(String)}
     *
     * @param size размер шрифта, sp
     */
    void saveTextSize(String size);

    /**
     * Сохранить цвет текста SharedPreferences {@link PreferencesDAO#saveTextColor(String)}
     *
     * @param color цвет текста , например #ff000000
     */
    void saveTextColor(String color);

    /**
     * @return все записи блокнота
     */
    List<Note> getNotes();

    /**
     * Вернуть заметку по ключу
     *
     * @param id ключ заметки {@link Note#getId()}
     * @return заметка или {@code null} если ее нет в блокноте
     */
    Note getNote(long id);

    /**
     * Вставить заметку в хранилище блокнота
     *
     * @param note заметка
     */
    void insert(Note note);

    /**
     * Обновить заметку в хранилище блокнота
     *
     * @param note заметка
     */
    void update(Note note);

    /**
     * Удалить заметку
     *
     * @param id идентификатор заметки
     */
    void delete(long id);
}