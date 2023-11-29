package org.talentform.databaseExamples;

public class Book {
    private String isbn;
    private String title;
    private String editor;
    private String language;
    private int year;

    public Book(String isbn, String title, String editor, String language, int year) {
        this.isbn = isbn;
        this.title = title;
        this.language = language;
        this.editor = editor;
        this.year = year;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getEditor() {
        return editor;
    }

    public String getLanguage() {
        return language;
    }

    public int getYear() {
        return year;
    }
}
