package org.library.controller;

import org.library.model.Book;
import org.library.repository.BookRepository;
import org.library.view.BookView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookController {
    private final BookRepository repository;
    private BookView view;
    public void setView(BookView view) {
        this.view = view;
    }

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    public void createBook(Book book) {
        try {
            repository.addBook(book);
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }


    public List<Book> getAllBooks() {
        try {
            return repository.findAllBooks();
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /*public void updateBook(String id, Book updatedBook) {
        try {
            repository.updateBook(id, updatedBook);
        } catch (SQLException e) {
            System.out.println("Database error while updating book: " + e.getMessage());
        }
    }
*/
    public void updateBook(String isbn) {
        try {
            Book existingBook = repository.findByISBN(isbn);
            if (existingBook == null) {
                System.out.println("Book with ISBN not found.");
                return;
            }
            repository.updateBook(isbn, existingBook);
        } catch (SQLException e) {
            System.out.println("Database error while updating book: " + e.getMessage());
        }
    }
    public void deleteBook(String isbn) {
        try {
            repository.deleteBook(isbn);
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}