package org.library.repository;
import org.library.config.DBManager;
import org.library.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BookRepository {
    private final Connection connection;
    public BookRepository(Connection connection) {
        this.connection = connection;
    }
    private String id;

    public boolean existsByIsbn(String isbn) throws SQLException {
        String sql = "SELECT COUNT(*) FROM books WHERE isbn = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, isbn);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;
        }
    }

        public void addBook(Book book) throws SQLException {
            if (!existsByIsbn(book.getIsbn())) {
                String sql = "INSERT INTO books (title, authors, description, isbn, genres) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, book.getTitle());
                    statement.setString(2, String.join(",", book.getAuthors()));
                    statement.setString(3, book.getDescription());
                    statement.setString(4, book.getIsbn());
                    statement.setString(5, String.join(",", book.getGenres()));
                    statement.executeUpdate();
                    System.out.println("Book added successfully.");
                }
            } else {
                System.out.println("Book already exists with this ISBN.");
                return;
            }
        }
        /* public void updateBook(String id, Book updatedBook) throws SQLException {
            if (!existsById(id)) {
                System.out.println("Book updated successfully.");
                return;
            }
            String sql = "UPDATE books SET title=?, authors=?, description=?, genres=? WHERE id=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, updatedBook.getTitle());
                statement.setString(2, String.join(",", updatedBook.getAuthors()));
                statement.setString(3, updatedBook.getDescription());
                statement.setString(4, updatedBook.getIsbn());
                statement.setString(5, String.join(",", updatedBook.getGenres()));
                statement.setString(6, id);
                statement.executeUpdate();
                System.out.println("Book updated successfully.");
            }
        }
*/
        public Book findByISBN(String isbn) throws SQLException {
            String sql = "SELECT * FROM books WHERE ISBN = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, isbn);
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    return new Book(
                            rs.getString("id"),
                            rs.getString("title"),
                            Arrays.asList(rs.getString("authors").split(",")),
                            rs.getString("description"),
                            rs.getString("isbn"),
                            Arrays.asList(rs.getString("genres").split(","))
                    );
                } else {
                    return null;
                }
            }
        }
    public void updateBook(String isbnToFind, Book originalBook) throws SQLException {
        if (!existsByIsbn(isbnToFind)) {
            System.out.println("Book not found.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose the field(s) you want to update");
        System.out.println("If you don't want to change a field, just press Enter and it will remain unchanged.");

        System.out.print("New Title (leave blank to skip): ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) title = originalBook.getTitle();

        System.out.print("New Authors (comma separated, leave blank to skip): ");
        String authorsInput = scanner.nextLine().trim();
        List<String> authors = authorsInput.isEmpty() ? originalBook.getAuthors() : Arrays.asList(authorsInput.split("\\s*,\\s*"));

        System.out.print("New Description (leave blank to skip): ");
        String description = scanner.nextLine().trim();
        if (description.length() > 200) description = description.substring(0, 200);
        if (description.isEmpty()) description = originalBook.getDescription();

        System.out.print("New ISBN (leave blank to skip): ");
        String newIsbn = scanner.nextLine().trim();
        if (newIsbn.isEmpty()) newIsbn = originalBook.getIsbn();

        System.out.print("New Genres (comma separated, leave blank to skip): ");
        String genresInput = scanner.nextLine().trim();
        List<String> genres = genresInput.isEmpty() ? originalBook.getGenres() : Arrays.asList(genresInput.split("\\s*,\\s*"));

        String bookId = originalBook.getId();

        Book updated = new Book(bookId, title, authors, description, newIsbn, genres);

        String sql = "UPDATE books SET title=?, authors=?, description=?, isbn=?, genres=? WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, updated.getTitle());
            statement.setString(2, String.join(",", updated.getAuthors()));
            statement.setString(3, updated.getDescription());
            statement.setString(4, updated.getIsbn());
            statement.setString(5, String.join(",", updated.getGenres()));
            statement.setString(6, updated.getId());
            statement.executeUpdate();
            System.out.println("Book updated successfully.");
        }
    }
    public void deleteBook(String isbn) throws SQLException {
            if (!existsByIsbn(isbn)) {
                System.out.println("Book not found.");
                return;
            }
        String sql = "DELETE FROM books WHERE isbn = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, isbn);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book deleted successfully.");
            } else {
                System.out.println("Book not found by ISBN.");
            }
        }
    }
        public List<Book> findAllBooks() throws SQLException {
            List<Book> books = new ArrayList<>();
            String sql = "SELECT * FROM books;";
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    Book book = new Book(
                            rs.getString("id"),
                            rs.getString("title"),
                            Arrays.asList(rs.getString("authors").split(",")),
                            rs.getString("description"),
                            rs.getString("isbn"),
                            Arrays.asList(rs.getString("genres").split(","))
                    );
                    books.add(book);
                }
            }
            return books;
        }

}

