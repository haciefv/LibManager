package helper;

import model.Book;

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
//    Update, delete, hele yazilmayib

}
