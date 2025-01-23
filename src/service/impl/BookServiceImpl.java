package service.impl;

import enums.Genres;
import helper.BookServiceHelper;
import helper.UserServiceHelper;
import model.Book;
import model.User;
import service.BookService;
import util.InputUtil;

import java.time.LocalDateTime;
import java.util.List;
import enums.Genres.BookGenre;

public class BookServiceImpl implements BookService {
    private static final InputUtil inputUtil = InputUtil.getInstance();
    private Book[] books = new Book[10];
    private int booksCount = 0;
    private static Book currentBook ;
    private static int bookIdCounter = 1;
    private  User currentUser;

    @Override
    public Book getCurrentBook() {
        return currentBook;
    }
    public static void setCurrentBook(Book book) {
        currentBook = book;
    }
    private void expandBookArrayIfNeeded() {
        if (booksCount == books.length) {
            Book[] newBooks = new Book[books.length + 5];
            System.arraycopy(books, 0, newBooks, 0, books.length);
            books = newBooks;
        }
    }

    @Override
    public void addBook() {
        String bookName = InputUtil.getInstance().inputTypeString("Enter book name: ");
        String bookAuthor = InputUtil.getInstance().inputTypeString("Enter book author: ");
        int pageCount = InputUtil.getInstance().inputTypeInteger("Enter the number of pages in the book:");
        for (Genres.BookGenre genre : Genres.BookGenre.values()) {
            System.out.println(genre);
        }
        int genreId = InputUtil.getInstance().inputTypeInteger("Enter the genre of the book:");
        BookGenre genre = BookGenre.getById(genreId);

        boolean isAvaiable  = InputUtil.getInstance().inputTypeString("""
                Available now :
                [1] -> Yes
                [2] -> No
                Enter: """).equals("1");
        Book newBook = new Book();
        newBook.setId(bookIdCounter++);
        newBook.setTitle(bookName);
        newBook.setAuthor(bookAuthor);
        newBook.setGenre(genre);
        newBook.setPageCount(pageCount);
        newBook.setAddedDate(LocalDateTime.now());
        newBook.setAvailable(isAvaiable);
        books[booksCount++]=newBook;


    }

    @Override
    public void updateBook() {
        UserServiceHelper.checkIfAdmin(currentUser);
        int bookId = inputUtil.inputTypeInteger("Enter the book ID to update: ");
        Book book =BookServiceHelper.findBookById(books,bookId,booksCount);


        ;

    }



    @Override
    public void deleteBook() {

    }

    @Override
    public void showAllBooks() {
            System.out.println("All Books:");
            for (Book book : books) {
                if (book != null) {
                    System.out.println("--------------------------------------" +
                            "\nId: " + book.getId() +
                            "\nTitle: " + book.getTitle() +
                            "\nAuthor: " + book.getAuthor() +
                            "\nAvailable: " + book.isAvailable() +
                            "\nPage Count: " + book.getPageCount() +
                            "\nGenre: " + book.getGenre() +
                            "\n--------------------------------------"
                    );
                }
            }
    }



    @Override
    public Book getBookById(Long id) {
        return null;
    }

    @Override
    public List<Book> getAvailableBooks() {
        return null;
    }

}
