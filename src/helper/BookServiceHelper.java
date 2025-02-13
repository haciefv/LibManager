package helper;

import domain.core.exceptions.Exceptions;
import domain.core.model.enums.Genres;
import domain.core.exceptions.GeneralExceptions;
import domain.core.model.Book;
import service.impl.BookServiceImpl;
import util.InputUtil;

public class BookServiceHelper {
    public static Book findBookById(Book[] books, int id, int booksCount) {
        for (int i = 0; i < booksCount; i++) {
            if (books[i] != null && books[i].getId() == id) {
                return books[i];
            }
        }
        // Implementation to find book by ID
        return null; // Placeholder return statement
    }
    public static Book[] deleteBookByName(Book[] books,String bookName,int booksCount) {
        boolean found = false;
        Book[] newBooks = new Book[books.length - 1];
        for (int i = 0, j = 0; i < booksCount; i++) {
            if (bookName.equalsIgnoreCase(books[i].getTitle())){
                found = true;
                continue;
            }
            newBooks[j++] = books[i];
            newBooks[j - 1].setId(j);
        }
        if(!found) throw new GeneralExceptions(Exceptions.BOOK_NOT_FOUND);
        //Implementation to delete a book by book name
        return newBooks;
    }



    public static Book[] deleteBookById(Book[] books,int bookID,int booksCount) {
        boolean found = false;
        Book[] newBooks = new Book[books.length - 1];
        for (int i = 0, j = 0; i < booksCount; i++) {
            if (bookID == books[i].getId()) {
                found = true;
                continue;
            }
            newBooks[j++] = books[i];
            newBooks[j - 1].setId(j);
        }

        if(!found) throw new GeneralExceptions(Exceptions.BOOK_NOT_FOUND);
        //Implementation to delete a book by ID
        return newBooks;
    }


    public static void settingTheWholeBook(Book book) {
        String bookName = InputUtil.getInstance().inputTypeString("Enter book name: ");
        String bookAuthor = InputUtil.getInstance().inputTypeString("Enter book author: ");
        int pageCount = InputUtil.getInstance().inputTypeInteger("Enter the number of pages in the book: ");
        for (Genres.BookGenre genre : Genres.BookGenre.values()) {
            System.out.println(genre);
        }
        int genreId = InputUtil.getInstance().inputTypeInteger("Enter the genre of the book: ");
        Genres.BookGenre genre = Genres.BookGenre.getById(genreId);

        boolean isAvaiable = InputUtil.getInstance().inputTypeString("""
                Available now :
                [1] -> Yes
                [2] -> No
                Enter: \s""").equals("1");
        book.setTitle(bookName);
        book.setAuthor(bookAuthor);
        book.setGenre(genre);
        book.setPageCount(pageCount);
        //setting every aspect of an empty book
    }


    public static void noBookAssignedYet(InputUtil inputUtil, BookServiceImpl bookServieImpl) {
        System.out.println("\n---------------| No book has been assigned yet! |---------------");
        if (inputUtil.inputTypeString("""
                    Would you like to add a new book?
                    Press [1] to add a new book
                    [no thanks]
                    Enter:\s""").equals("1")) bookServieImpl.addBook();
        //For the case if the admin wants to delete/update a book but there is no book assigned yet
    }

}
