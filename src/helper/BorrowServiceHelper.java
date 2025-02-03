package helper;

import enums.Exceptions;
import exceptions.GeneralExceptions;
import model.Borrow;
import util.InputUtil;

import java.time.LocalDate;
import java.time.Period;

public class BorrowServiceHelper {

    public static int getIndexOfBorrow(Borrow[] borrows, int count, Long borrowRecordId) {
        int index = -1;
        for (int i = 0; i < count; i++) {
            if (borrows[i].getRecordID().equals(borrowRecordId)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new GeneralExceptions(Exceptions.Record_NOT_FOUND);
        }
        return index;
    }

    public static Borrow findBorrowRecordById(Borrow[] borrows, int count, Long borrowRecordId) {
        for (int i = 0; i < count; i++) {
            if (borrows[i].getRecordID().equals(borrowRecordId)) return borrows[i];
        }
        return null;
    }

    public static Borrow[] delete(Borrow[] borrows, int count, int index) {
        Borrow[] newBorrows = new Borrow[count - 1];
        System.arraycopy(borrows, 0, newBorrows, 0, index);
        System.arraycopy(borrows, index + 1, newBorrows, index, count - index - 1);
        return newBorrows;
    }

    public static boolean isOverdue(Borrow b) {
        return LocalDate.now().isAfter(b.getDueDate());
    }

    public static void show(Borrow[] borrows, int borrowCount) {
        for (int i = 0; i < borrowCount; i++) {
            System.out.println(borrows[i]);
        }
    }

    public static LocalDate dueTimeIdentify() {
        InputUtil inputUtil = InputUtil.getInstance();
        while (true) {
            byte dueDate = inputUtil.inputTypeByte("""
                    Choose the borrow time:
                    [1] -> 1 month
                    [2] -> 2 months
                    [3] -> 3 months
                    Enter:\s""");  // for check
            switch (dueDate) {
                case 1, 2, 3 -> {
                    return LocalDate.now().plusMonths(dueDate);
                }
                default -> System.out.println("Invalid borrow time");
            }
        }

    }

    public static void dueTimeExtend(Borrow b) {
        InputUtil inputUtil = InputUtil.getInstance();
        while (true) {
            byte dueDate = inputUtil.inputTypeByte("""
                    How much dou you want to extend the due time:
                    [1] -> 1 month
                    [2] -> 2 months
                    [3] -> 3 months
                    Enter:\s""");
            switch (dueDate) {
                case 1, 2, 3 -> {
                    b.setReturnDate(b.getDueDate().plusMonths(dueDate));
                    System.out.println("Time extended successfully");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    public static double findCost(LocalDate dueDate) {
        int monthDiff = Period.between(LocalDate.now(), dueDate).getMonths();
        return monthDiff * 1.42;
    }


}
