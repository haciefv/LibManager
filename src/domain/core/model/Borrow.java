package domain.core.model;

import domain.core.exceptions.Exceptions;
import domain.core.exceptions.GeneralExceptions;

import java.time.LocalDate;

public class Borrow {

    //    Elchin and Rufat
    private Long recordID;
    private Long userID;
    private Long bookID;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private Boolean isReturned;
    private Double cost;

    public Borrow() {
    }

    public Borrow(Long recordID, Long userID, Long bookID, LocalDate dueDate, Double cost) {
        if (recordID == null || recordID <= 0) throw new GeneralExceptions(Exceptions.ID_CANNOT_BE_ZERO);
        this.recordID = recordID;
        if (userID == null || userID <= 0) throw new GeneralExceptions(Exceptions.ID_CANNOT_BE_ZERO);
        this.userID = userID;
        if (bookID == null || bookID <= 0) throw new GeneralExceptions(Exceptions.ID_CANNOT_BE_ZERO);
        this.bookID = bookID;
        if (dueDate == null) throw new GeneralExceptions(Exceptions.INVALID_DATA_FORMAT);
        this.dueDate = dueDate;
        if (cost == null || cost <= 0) throw new GeneralExceptions(Exceptions.COST_CANNOT_BE_ZERO);
        this.cost = cost;
        this.borrowDate = LocalDate.now();
        this.returnDate = null;
        this.isReturned = false;
    }

    public Long getRecordID() {
        return recordID;
    }

    public void setRecordID(Long recordID) {
        this.recordID = recordID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getBookID() {
        return bookID;
    }

    public void setBookID(Long bookID) {
        this.bookID = bookID;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Boolean getIsReturned() {
        return isReturned;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "recordID=" + recordID +
                ", userID=" + userID +
                ", bookID=" + bookID +
                ", borrowDate=" + borrowDate +
                ", dueDate=" + dueDate +
                ", returnDate=" + returnDate +
                ", isReturned=" + isReturned +
                ", cost=" + cost +
                '}';
    }
}
