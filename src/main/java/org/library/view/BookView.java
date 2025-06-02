package org.library.view;
import org.library.controller.BookController;
import org.library.model.Book;

import javax.sound.sampled.BooleanControl;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;

public class BookView {
    private final BookController controller;
    private final Scanner scanner = new Scanner(System.in);

    public BookView(BookController controller) {
        this.controller = controller;
    }

    public void start() {
        while (true) {
            System.out.println("\nLibrary Menu:\n1. Add Book\n2. List Books\n3. Update Book\n4. Delete Book\n5. Exit");

            int choice;

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    listBooks();
                    break;
                case 3:
                    updateBook();
                    break;
                case 4:
                    deleteBook();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    private void listBooks() {
        List<Book> books = controller.getALlBooks();
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            for (Book b : books) {
                System.out.println(b);
            }
        }
        askToContinue();
    }

    private void addBook() {
        Book book = readBookWithoutId();
        controller.createBook(book);
        askToContinue();
    }

    private void updateBook() {
        System.out.print("Enter ISBN of book to update: ");
        String isbn = scanner.nextLine();
        Book book = readBookWithId(isbn);
        controller.updateBook(isbn, book);
        askToContinue();
    }

    private void deleteBook() {
        System.out.print("Enter Id of book to delete: ");
        String id = scanner.nextLine();
        controller.deleteBook(id);
        askToContinue();
    }

    private void askToContinue() {
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }
    private Book readBookWithoutId() {
        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Authors: ");
        List<String> authors = Arrays.asList(scanner.nextLine().split("\\s*,\\s*"));

        System.out.print("Description (max 200 chars): ");
        String description = scanner.nextLine();
        if (description.length() > 200) {
            description = description.substring(0, 200);
        }

        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Genres (comma separated): ");
        List<String> genres = Arrays.asList(scanner.nextLine().split("\\s*,\\s*"));
        return new Book(null, title, authors, description, isbn, genres);
    }

    private Book readBookWithId(String id) {
        Book book = readBookWithoutId();
        book.setId(id);
        return book;
    }
}