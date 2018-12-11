package ru.voronezhtsev.contentprovider.client.ui;

/**
 * Операции над заметками в списке
 */
public interface NotesActions {
    /**
     * Обновить заметку в списке
     * @param noteId идентификатор заметки
     */
    void update(long noteId);
}
