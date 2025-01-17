package enums;

import java.time.LocalDateTime;

public enum Exceptions {


    USER_NOT_FOUND("\n---------------| User is not found! |---------------"),
    BOOK_NOT_FOUND("\n---------------| Book is not found! |---------------"),
    INVALID_OPTION("\n---------------| Invalid option!!! |---------------"),
    INVALID_DATA_FORMAT("\n---------------| Invalid date format. Please use dd-MM-yyyy |---------------");

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
        return  message + "\n" +
                "\nTimeStamp = " + timeStamp + "\n"
                ;
    }

}
