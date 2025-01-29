package service;

import model.Book;

import java.util.List;

public interface BookService {
    Book getCurrentBook();
    void addBook();
    void updateBook();
    void deleteBook();
    void showAllBooks();
    Book getBookById(Long id);
    List<Book> getAvailableBooks();
}
