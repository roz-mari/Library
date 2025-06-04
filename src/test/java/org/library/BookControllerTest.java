package org.library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.library.controller.BookController;
import org.library.model.Book;
import org.library.repository.BookRepository;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class BookControllerTest {

    private BookRepository mockRepository;
    private BookController controller;

    @BeforeEach
    void setUp() {
        mockRepository = mock(BookRepository.class);
        controller = new BookController(mockRepository);
    }

    private Book makeBook(String title, String isbn) {
        return new Book(
                title,
                Arrays.asList("Test Author"),
                "Sample description",
                isbn,
                Arrays.asList("Fiction")
        );
    }

    @Test
    void testCreateBook() throws SQLException {
        Book book = makeBook("Wicked The Life and Times of the Wicked Witch of the West", "9780061792946");
        controller.createBook(book);
        verify(mockRepository, times(1)).addBook(book);
    }

    @Test
    void testGetAllBooks() throws SQLException {
        List<Book> books = Arrays.asList(
                makeBook("A Wrinkle in Time", "9780312367541"),
                makeBook("The Bell Jar", "9780060837020")
        );

        when(mockRepository.findAllBooks()).thenReturn(books);
        List<Book> result = controller.getAllBooks();

        assertEquals(2, result.size());
        assertEquals("A Wrinkle in Time", result.getFirst().getTitle());
        verify(mockRepository, times(1)).findAllBooks();
    }

    @Test
    void testUpdateBook_WhenBookExists() throws SQLException {
        Book existingBook = makeBook("Wicked The Life and Times of the Wicked Witch of the West", "9780061792946");
        when(mockRepository.findByISBN("9780061792946")).thenReturn(existingBook);

        controller.updateBook("9780061792946");

        verify(mockRepository, times(1)).updateBook("9780061792946", existingBook);
    }

    @Test
    void testUpdateBook_WhenBookDoesNotExist() throws SQLException {
        when(mockRepository.findByISBN("9780061792946")).thenReturn(null);

        controller.updateBook("9780061792946");

        verify(mockRepository, never()).updateBook(anyString(), any(Book.class));
    }

    @Test
    void testDeleteBook() throws SQLException {
        controller.deleteBook("9780061792946");
        verify(mockRepository, times(1)).deleteBook("9780061792946");
    }
}