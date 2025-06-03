package org.library;

import org.library.config.DBManager;
import org.library.controller.BookController;
import org.library.repository.BookRepository;
import org.library.view.BookView;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection connection = DBManager.initConnection();

        BookRepository repository = new BookRepository(connection);
        BookController controller = new BookController(repository);
        BookView view = new BookView(controller);
        controller.setView(view);

        view.start();
    }
}

