package ru.voronezhtsev.contentprovider.client.data;

public class Note {
    private long id;
    private String note;


    public Note() {
    }

    public Note(long id, String note) {
        this.id = id;
        this.note = note;
    }

    public Note(String note) {
        this.note = note;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}