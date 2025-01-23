package helper;

import enums.Exceptions;
import exceptions.GeneralExceptions;
import model.Borrow;

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


}
