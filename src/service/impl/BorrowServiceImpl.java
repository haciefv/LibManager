package service.impl;

import enums.Exceptions;
import exceptions.GeneralExceptions;
import helper.BorrowServiceHelper;
import service.BorrowService;
import model.Borrow;
import util.InputUtil;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;

public class BorrowServiceImpl implements BorrowService {
    InputUtil inputUtil = InputUtil.getInstance();
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
    public void borrowBook() {
        if (borrowCount == borrowRecords.length) {
            borrowRecords = Arrays.copyOf(borrowRecords, borrowRecords.length * 2);
        }
        Long userId = inputUtil.inputTypeLong("Enter the user id: ");
        Long bookId = inputUtil.inputTypeLong("Enter the book id: ");
        LocalDate dueDate = BorrowServiceHelper.dueTimeIdentify();
        Borrow newBorrow = new Borrow();
        newBorrow.setRecordID(borrowIdCounter++);
        newBorrow.setUserID(userId);
        newBorrow.setBookID(bookId);
        newBorrow.setDueDate(dueDate);
        newBorrow.setCost(BorrowServiceHelper.findCost(dueDate));
        newBorrow.setBorrowDate(LocalDate.now());
        newBorrow.setReturned(false);
        borrowRecords[borrowCount++] = newBorrow;
        borrowIdCounter++;
        System.out.println("Borrow record has been added successfully.");
    }

    @Override
    public void returnBook() {
        Long borrowRecordId = inputUtil.inputTypeLong("Enter the record id: ");
        LocalDate returnDate = LocalDate.now();
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
        currentBorrow.setCost(BorrowServiceHelper.findCost(LocalDate.now())); //cost when the book is returned
        System.out.println("The book has been returned.");
    }

    @Override
    public void showBorrows() {
        if (borrowCount == 0) throw new GeneralExceptions(Exceptions.Record_NOT_FOUND);
        BorrowServiceHelper.show(borrowRecords, borrowCount);
    }

    @Override
    public void getOverdueBorrowRecords() {
        if (borrowCount == 0) throw new GeneralExceptions(Exceptions.Record_NOT_FOUND);
        Borrow[] overdueRecords = new Borrow[borrowCount];
        for (int i = 0; i < borrowCount; i++) {
            if (BorrowServiceHelper.isOverdue(borrowRecords[i])) {
                overdueRecords[overDueBookCount++] = borrowRecords[i];
            }
        }
        if (overDueBookCount == 0) throw new GeneralExceptions(Exceptions.OverdueBorrowRecord_NOT_FOUND);
        BorrowServiceHelper.show(overdueRecords, overDueBookCount);
    }

    @Override
    public void getOverdueFee() {
        Long borrowRecordId = inputUtil.inputTypeLong("Enter the record id to view overdue fee: ");
        double dailyPenalty = 0.5;
        Borrow currentBorrow = BorrowServiceHelper.findBorrowRecordById(borrowRecords, borrowCount, borrowRecordId);
        if (currentBorrow == null) {
            throw new GeneralExceptions(Exceptions.Record_NOT_FOUND);
        }
        double overdueFee = 0;
        if (BorrowServiceHelper.isOverdue(currentBorrow)) {
            long overdueDays = Period.between(currentBorrow.getDueDate(), LocalDate.now()).getDays();
            overdueFee = overdueDays * dailyPenalty;
            currentBorrow.setCost(overdueFee + currentBorrow.getCost());
            // Update the borrow record cost
            currentBorrow.setCost(overdueFee + currentBorrow.getCost());
        }
        System.out.println("Overdue fee: " + overdueFee);
    }

    @Override
    public void getTotalCost() {
        double totalCost = 0;
        for (int i = 0; i < borrowCount; i++) {
            totalCost += borrowRecords[i].getCost();
        }
        System.out.println("Total cost: " + totalCost);
    }

    @Override
    public void updateBorrowRecord() {
        if (borrowCount == 0) {
            throw new GeneralExceptions(Exceptions.NO_RECORDS_TO_DELETE);
        }
        Long borrowRecordId = inputUtil.inputTypeLong("Enter the record id: ");
        Borrow currentRecord = BorrowServiceHelper.findBorrowRecordById(borrowRecords, borrowCount, borrowRecordId);
        if (currentRecord == null) {
            throw new GeneralExceptions(Exceptions.Record_NOT_FOUND);
        }
        byte choice = inputUtil.inputTypeByte("""
                What do you want to update?
                 [1] -> Due date
                """); // else....
        switch (choice) {
            case 1 -> BorrowServiceHelper.dueTimeExtend(currentRecord);
            default -> throw new GeneralExceptions(Exceptions.INVALID_OPTION);
        }
        System.out.println("Borrow record has been updated successfully.");

    }

    @Override
    public void deleteBorrowRecord() {
        Long borrowRecordId = inputUtil.inputTypeLong("Enter the record id to delete: ");
        // Not sure
        if (borrowCount == 0) {
            throw new GeneralExceptions(Exceptions.NO_RECORDS_TO_DELETE);
        }
        int deleteBorrowIndex = BorrowServiceHelper.getIndexOfBorrow(borrowRecords, borrowCount, borrowRecordId);
        borrowRecords = BorrowServiceHelper.delete(borrowRecords, borrowCount, deleteBorrowIndex);
        borrowCount--;
        borrowIdCounter--;
        System.out.println("The borrow record has been deleted successfully.");
    }
}
