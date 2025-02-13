package service.impl;


import domain.core.exceptions.Exceptions;
import domain.core.exceptions.GeneralExceptions;
import helper.ManagementHelper;
import service.BookService;
import service.BorrowService;
import service.ManagementService;
import service.UserService;
import util.MenuUtil;

public class ManagementServiceImpl implements ManagementService {
    @Override
    public void management() {
        UserService userService = new UserServiceImpl();
        BookService bookService = new BookServiceImpl();
        BorrowService borrowService = new BorrowServiceImpl();

        while (true) {
            try {
                byte mainOption = MenuUtil.entryApp();
                switch (mainOption) {
                    case 0 -> System.exit(-1);
                    case 1 -> ManagementHelper.loginAsAdmin(userService);
                    case 2 -> ManagementHelper.loginOrRegistrationAsUser(userService);
                    case 3 -> ManagementHelper.manageBooks(bookService);
                    case 4 -> ManagementHelper.manageBorrowing(borrowService);
                    case 5 -> ManagementHelper.manageCommon();
                    default -> throw new GeneralExceptions(Exceptions.INVALID_OPTION);
                }
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
}