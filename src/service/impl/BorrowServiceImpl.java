package service.impl;

import enums.Exceptions;
import exceptions.GeneralExceptions;
import helper.BookServiceHelper;
import helper.BorrowServiceHelper;
import model.Borrow;
import service.BorrowService;
import util.InputUtil;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;

public class BorrowServiceImpl implements BorrowService {
    private Borrow[] borrowRecords;
    private int borrowCount;
    private int overDueBookCount;
    private static long borrowIdCounter = 1;

    public BorrowServiceImpl() {
        borrowRecords = new Borrow[10];
        borrowCount = 0;
        overDueBookCount = 0;
    }

    @Override
    public void borrowBook(Long userId, Long bookId, LocalDate dueDate) {
        if (borrowCount == borrowRecords.length) {
            borrowRecords = Arrays.copyOf(borrowRecords, borrowRecords.length * 2);
        }
        Borrow newBorrow = new Borrow();
        newBorrow.setRecordID(borrowIdCounter++);
        newBorrow.setUserID(userId);
        newBorrow.setBookID(bookId);
        newBorrow.setDueDate(dueDate);
        newBorrow.setCost(1.42); //for check
        newBorrow.setBorrowDate(LocalDate.now());
        newBorrow.setReturned(false);
        borrowRecords[borrowCount++] = newBorrow;
    }

    @Override
    public void returnBook(Long borrowRecordId, LocalDate returnDate) {
        Borrow currentBorrow = BorrowServiceHelper.findBorrowRecordById(borrowRecords, borrowCount, borrowRecordId);
        if (currentBorrow == null) {
            throw new GeneralExceptions(Exceptions.Record_NOT_FOUND);
        }
        for (int i = 0; i < borrowCount; i++) {
            if (borrowRecords[i].getRecordID().equals(borrowRecordId)) {
                borrowRecords[i].setReturnDate(returnDate);
                borrowRecords[i].setReturned(true);
                break;
            }
        }

    }

    @Override
    public void showBorrows() {
        for (int i = 0; i < borrowCount; i++) {
            System.out.println(borrowRecords[i]);
        }
    }

    @Override
    public boolean isOverdue(Borrow b) {
        return LocalDate.now().isAfter(b.getDueDate());
    }

    @Override
    public Borrow[] getOverdueBorrowRecords() {
        Borrow[] overdueRecords = new Borrow[borrowCount];
        for (Borrow b : borrowRecords) {
            if (isOverdue(b)) {
                overdueRecords[overDueBookCount++] = b;
            }
        }
        if (overDueBookCount == 0) throw new GeneralExceptions(Exceptions.OverdueBorrowRecord_NOT_FOUND);
        return overdueRecords;
    }

    @Override
    public double getOverdueFee(Long borrowRecordId, double dailyPenalty) {
        Borrow currentBorrow = BorrowServiceHelper.findBorrowRecordById(borrowRecords, borrowCount, borrowRecordId);
        if (currentBorrow == null) {
            throw new GeneralExceptions(Exceptions.Record_NOT_FOUND);
        }
        if (isOverdue(currentBorrow)) {
            long overdueDays = Period.between(currentBorrow.getDueDate(), LocalDate.now()).getDays();
            double overdueFee = overdueDays * dailyPenalty;
            currentBorrow.setCost(overdueFee + currentBorrow.getCost());
            // Update the borrow record cost
            currentBorrow.setCost(overdueFee + currentBorrow.getCost());
            return overdueFee;
        }
        return 0; // no overdue fee
    }

    @Override
    public double getTotalCost() {
        double totalCost = 0;
        for (int i = 0; i < borrowCount; i++) {
            totalCost += borrowRecords[i].getCost();
        }
        return totalCost;
    }

    @Override
    public void updateBorrowRecord(Long borrowRecordId, boolean isReturned) {
        if (borrowCount == 0) {
            throw new GeneralExceptions(Exceptions.NO_RECORDS_TO_DELETE);
        }
        Borrow currentRecord = BorrowServiceHelper.findBorrowRecordById(borrowRecords, borrowCount, borrowRecordId);
        if (currentRecord == null) {
            throw new GeneralExceptions(Exceptions.Record_NOT_FOUND);
        }
        currentRecord.setReturned(isReturned);
    }

    @Override
    public void deleteBorrowRecord(Long borrowRecordId) {
        // Not sure
        if (borrowCount == 0) {
            throw new GeneralExceptions(Exceptions.NO_RECORDS_TO_DELETE);
        }
        int deleteBorrowIndex = BorrowServiceHelper.getIndexOfBorrow(borrowRecords, borrowCount, borrowRecordId);
        borrowRecords = BorrowServiceHelper.delete(borrowRecords, borrowCount, deleteBorrowIndex);
        borrowCount--;
    }
}
