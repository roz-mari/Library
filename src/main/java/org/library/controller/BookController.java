package org.library.controller;

import org.library.model.Book;
import org.library.repository.BookRepository;
import org.library.view.BookView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BookController {
    private final BookRepository repository;
    private final BookView view;

    public BookController(BookRepository repository, BookView view) {
        this.repository = repository;
        this.view = view;
    }

    public void start() {
        Scanner scanner = view.getScanner();
        boolean running = true;

        while (running) {
            view.showMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> showBooks();
                case "2" -> addBook();
                case "3" -> editBook();
                case "4" -> deleteBook();
                case "5" -> {
                    running = false;
                    view.showMessage("Goodbye!");
                }
                default -> view.showMessage("Invalid option. Try again.");
            }
        }
    }

    public void showBooks() {
        List<Book> books = repository.getAll();
        view.showBooks(books);
    }

    public void addBook() {
        Scanner scanner = view.getScanner();

        System.out.print("ID: ");
        String id = scanner.nextLine();

        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Authors (comma separated): ");
        List<String> authors = Arrays.asList(scanner.nextLine().split(","));

        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Genres (comma separated): ");
        List<String> genres = Arrays.asList(scanner.nextLine().split(","));

        Book newBook = new Book(id, title, authors, description, isbn, genres);
        repository.save(newBook);
        view.showMessage("Book added successfully.");
    }

    public void editBook() {
        Scanner scanner = view.getScanner();

        System.out.print("Enter the ID of the book to edit: ");
        String id = scanner.nextLine();

        Book existing = repository.findById(id);
        if (existing == null) {
            view.showMessage("Book not found.");
            return;
        }

        System.out.print("New Title (leave blank to keep unchanged): ");
        String title = scanner.nextLine();
        if (!title.isEmpty()) existing.setTitle(title);

        System.out.print("New Authors (comma separated, leave blank to keep unchanged): ");
        String authors = scanner.nextLine();
        if (!authors.isEmpty()) existing.setAuthors(Arrays.asList(authors.split(",")));

        System.out.print("New Description: ");
        String description = scanner.nextLine();
        if (!description.isEmpty()) existing.setDescription(description);

        System.out.print("New ISBN: ");
        String isbn = scanner.nextLine();
        if (!isbn.isEmpty()) existing.setIsbn(isbn);

        System.out.print("New Genres (comma separated): ");
        String genres = scanner.nextLine();
        if (!genres.isEmpty()) existing.setGenres(Arrays.asList(genres.split(",")));

        repository.update(existing);
        view.showMessage("Book updated successfully.");
    }

    public void deleteBook() {
        Scanner scanner = view.getScanner();

        System.out.print("Enter the ID of the book to delete: ");
        String id = scanner.nextLine();

        repository.delete(id);
        view.showMessage("Book deleted successfully.");
    }
}