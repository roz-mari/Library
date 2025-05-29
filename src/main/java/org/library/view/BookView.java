package org.library.view;

import org.library.model.Book;

import java.util.List;
import java.util.Scanner;

public class BookView {
    private final Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        System.out.println("\nLibrary Menu:");
        System.out.println("1. Show all books");
        System.out.println("2. Add a new book");
        System.out.println("3. Edit a book");
        System.out.println("4. Delete a book");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }

    public void showBooks(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("No books found.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public Scanner getScanner() {
        return scanner;
    }
}