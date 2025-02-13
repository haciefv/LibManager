package service.impl;

import enums.Exceptions;
import exceptions.GeneralExceptions;
import helper.ManagementHelper;
import service.BookService;
import service.BorrowService;
import service.ManagementService;
import service.UserService;
import util.MenuUtil;

import java.util.Map;
import java.util.function.Supplier;

public class ManagementServiceImpl implements ManagementService {

    @Override
    public void management() {
        Map<Byte, Runnable> actionMap = getByteRunnableMap();
        handleMenu(MenuUtil::entryApp, actionMap);
    }

    private static Map<Byte, Runnable> getByteRunnableMap() {
        UserService userService = new UserServiceImpl();
        BookService bookService = new BookServiceImpl();
        BorrowService borrowService = new BorrowServiceImpl();

        return Map.of(
                (byte) 0, () -> System.exit(-1),
                (byte) 1, () -> ManagementHelper.loginAsAdmin(userService),
                (byte) 2, () -> ManagementHelper.loginOrRegistrationAsUser(userService),
                (byte) 3, () -> ManagementHelper.manageBooks(bookService),
                (byte) 4, () -> ManagementHelper.manageBorrowing(borrowService),
                (byte) 5, ManagementHelper::manageCommon
        );
    }

    private static void handleMenu(Supplier<Byte> menuSupplier, Map<Byte, Runnable> actionMap) {
        while (true) {
            try {
                byte option = menuSupplier.get();
                Runnable action = actionMap.get(option);

                if (action == null) {
                    throw new GeneralExceptions(Exceptions.INVALID_OPTION);
                }

                action.run();
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
}
