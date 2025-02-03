package enums;

import java.time.LocalDateTime;

public enum Exceptions {


    USER_NOT_FOUND("\n---------------| User is not found! |---------------"),
    BOOK_NOT_FOUND("\n---------------| Book is not found! |---------------"),
    Record_NOT_FOUND("\n---------------| Record is not found! |---------------"),
    OverdueBorrowRecord_NOT_FOUND("\n---------------| Overdue record is not found! |---------------"),
    ID_CANNOT_BE_ZERO("\n---------------| Id cannot be small than 0 or null! |---------------"),
    COST_CANNOT_BE_ZERO("\n---------------| Cost cannot be small than 0 or null! |---------------"),
    INVALID_OPTION("\n---------------| Invalid option!!! |---------------"),
    ACCESS_DENIED("\n---------------| You don't have access for this process! |---------------"),
    UNAUTHORIZED_ACCESS("\n---------------| Only admins are authorized to perform this action! |---------------"),
    INVALID_DATA_FORMAT("\n---------------| Invalid date format. Please use dd-MM-yyyy |---------------"),
    USER_ALREADY_REGISTERED("\n---------------| UserName already used! |---------------"),
    USER_NOT_REGISTERED("\n---------------| User not registered! |---------------"),
    INVALID_CREDENTIALS("\n---------------| Wrong password! |---------------"),
    NO_RECORDS_TO_DELETE("\n---------------| You don't have book to delete! |---------------");


    private final String message;
    private final LocalDateTime timeStamp = LocalDateTime.now().withNano(0);

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    Exceptions(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message + "\n"
                ;
    }

}
