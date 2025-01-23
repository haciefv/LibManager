package service;

import model.Borrow;

import java.time.LocalDate;

public interface BorrowService {

    void borrowBook(Long userId, Long bookId, LocalDate dueDate);
    void returnBook(Long borrowRecordId, LocalDate returnDate);
    void showBorrows();
    boolean isOverdue(Borrow b);
    Borrow[] getOverdueBorrowRecords();
    double getOverdueFee(Long borrowRecordId, double dailyPenalty);
    double getTotalCost();
    void updateBorrowRecord(Long borrowRecordId, boolean isReturned);
    void deleteBorrowRecord(Long borrowRecordId);

}
