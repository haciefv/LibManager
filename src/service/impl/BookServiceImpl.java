package service.impl;

import domain.core.exceptions.Exceptions;
import domain.core.exceptions.GeneralExceptions;
import helper.BookServiceHelper;
import domain.core.model.Book;
import domain.core.model.User;
import service.BookService;
import util.InputUtil;

import java.util.List;
import domain.core.model.enums.Genres.BookGenre;

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
        Book newBook = new Book();
        BookServiceHelper.settingTheWholeBook(newBook);
        newBook.setId(bookIdCounter++);
        books[booksCount++]=newBook;


    }

    @Override
    public void updateBook() {
        //UserServiceHelper.checkIfAdmin(currentUser);
        //***    Problems regarding getting currentUser     ***
        if (booksCount == 0) {
            BookServiceHelper.noBookAssignedYet(inputUtil,this);
            return;
        }
        int bookId = inputUtil.inputTypeInteger("Enter the book ID to update: ");
        if(bookId <=0) throw new GeneralExceptions(Exceptions.ID_CANNOT_BE_ZERO);
        Book book = BookServiceHelper.findBookById(books, bookId, booksCount);
        if(book == null) throw new GeneralExceptions(Exceptions.BOOK_NOT_FOUND);
        byte choice = inputUtil.inputTypeByte("""
                What aspect do you want to change?
                [1] -> Book Title
                [2] -> Book Author
                [3] -> Book Page
                [4] - > Genre
                [5] -> Availability
                [6] -> The Whole Book
                Enter:\s""");
        switch (choice) {
            case 1 -> book.setTitle(inputUtil.inputTypeString("Enter the new book name: "));
            case 2 -> book.setAuthor(inputUtil.inputTypeString("Enter the new author: "));
            case 3 -> book.setPageCount(inputUtil.inputTypeInteger("Enter the new page count: "));
            case 4 -> book.setGenre(BookGenre.getById(inputUtil.inputTypeInteger("Enter the new genre: ")));
            case 5 -> book.setAvailable(InputUtil.getInstance().inputTypeString("""
                    Available now :
                    [1] -> Yes
                    [2] -> No
                    Enter:\s""").equals("1"));
            case 6 -> BookServiceHelper.settingTheWholeBook(book);
            default -> throw new GeneralExceptions(Exceptions.INVALID_OPTION);

        }
        System.out.println("\n---------------| The book has successfully been updated! |---------------");

    }



    @Override
    public void deleteBook() {
        //UserServiceHelper.checkIfAdmin(currentUser);
        //***    Problems regarding getting currentUser     ***
        if (booksCount == 0) {
            BookServiceHelper.noBookAssignedYet(inputUtil,this);
            return;
        }
        String choice = inputUtil.inputTypeString("""
                How would you like to find the book?
                [1] -> by ID
                [2] -> by name
                Enter:\s""");
        Object bookInformation = choice.equals("1") ? inputUtil.inputTypeInteger("Enter the ID: ") : (choice.equals("2") ? inputUtil.inputTypeString("Enter the book name: ") : null);
        if (bookInformation == null) throw new GeneralExceptions(Exceptions.INVALID_OPTION);
        String type = bookInformation.getClass().getSimpleName();

        switch (type) {
            case "String":
                String bookName = (String) bookInformation;
                books = BookServiceHelper.deleteBookByName(books,bookName,booksCount);
                break;

            case "Integer":
                int bookID = (int) bookInformation;
                if (bookID <= 0 || bookID > bookIdCounter) throw new GeneralExceptions(Exceptions.ID_CANNOT_BE_ZERO);
                books = BookServiceHelper.deleteBookById(books,bookID,booksCount);
                break;
        }

        booksCount--;
        bookIdCounter--;
        System.out.println("\n---------------| The book has successfully been deleted! |---------------");


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
