package org.library.model;

import java.util.List;

public class Book {
    private String id;
    private String title;
    private List<String> authors;
    private String description;
    private String isbn;
    private List<String> genres;

    public Book(String id, String title, List<String> authors, String description, String isbn, List<String> genres) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        setDescription(description); // to enforce max 200 characters
        this.isbn = isbn;
        this.genres = genres;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description.length() > 200) {
            this.description = description.substring(0, 200);
        } else {
            this.description = description;
        }
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "Book {" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", description='" + description + '\'' +
                ", isbn='" + isbn + '\'' +
                ", genres=" + genres +
                '}';
    }
}