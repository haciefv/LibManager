package service;


public interface BorrowService {

    void borrowBook();
    void returnBook();
    void showBorrows();
    void getOverdueBorrowRecords();
    void getOverdueFee();
    void getTotalCost();
    void updateBorrowRecord();
    void deleteBorrowRecord();

}
