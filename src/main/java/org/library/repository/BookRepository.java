package org.library.repository;
import org.library.config.DBManager;
import org.library.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookRepository {
    private final Connection connection;
    public BookRepository(Connection connection) {
        this.connection = connection;
    }
    private String id;

    public boolean existsById(String id) throws SQLException {
            String sql = "SELECT COUNT(*) FROM books WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, id);
                ResultSet rs = statement.executeQuery();
                rs.next();
                return rs.getInt(1) > 0;
            }
        }

        public void addBook(Book book) throws SQLException {
            if (!existsById(book.getId())) {
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
                System.out.println("Book already exists with this Id.");
                return;
            }
        }

    public void updateBook(String id, Book updatedBook) throws SQLException {
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

        public void deleteBook(String id) throws SQLException {
            if (!existsById(id)) {
                System.out.println("Book not found.");
                return;
            }
            String sql = "DELETE  FROM books WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, id);
                statement.executeUpdate();
                System.out.println("Book deleted successfully.");
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

