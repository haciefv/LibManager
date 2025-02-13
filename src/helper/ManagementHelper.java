package helper;

import domain.core.exceptions.Exceptions;
import domain.core.model.enums.Role;
import domain.core.exceptions.GeneralExceptions;
import domain.core.model.User;
import service.BookService;
import service.BorrowService;
import service.UserService;
import service.impl.BookServiceImpl;
import util.InputUtil;
import util.MenuUtil;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ManagementHelper {

//    Admin and User Management

    public static void loginOrRegistrationAsUser(UserService userService) {
        Map<Byte, Runnable> actionMap = Map.of(
                (byte) 0, () -> {
                },
                (byte) 1, userService::registerAsUser,
                (byte) 2, () -> loginForUser(userService)
        );
        handleMenu(MenuUtil::loginOrRegisterAsUserMenu, actionMap);
    }

    public static void loginAsAdmin(UserService userService) {
        Map<Byte, Runnable> actionMap = Map.of(
                (byte) 0, () -> {},
                (byte) 1, () -> loginForAdmin(userService)
        );
        handleMenu(MenuUtil::loginAsAdmin, actionMap);
    }

    public static void adminMenu(UserService userService) {
        Map<Byte, Runnable> actionMap = Map.of(
                (byte) 0, () -> {},
                (byte) 1, () -> updateUserAsAdmin(userService),
                (byte) 2, () -> deleteUserAsAdmin(userService),
                (byte) 3, userService::showUsers,
                (byte) 4, userService::showUserProfile,
                (byte) 5, () -> manageBooks(new BookServiceImpl()),
                (byte) 6, userService::logout
        );
        handleMenu(MenuUtil::adminMenu, actionMap);
    }

    public static void userMenu(UserService userService) {
        Map<Byte, Runnable> actionMap = Map.of(
                (byte) 0, () -> {},
                (byte) 1, () -> updateUserAsUser(userService),
                (byte) 2, userService::deleteProfile,
                (byte) 3, userService::showUserProfile,
                (byte) 4, userService::logout
        );
        handleMenu(MenuUtil::userMenu, actionMap);
    }

    public static void handleMenu(Supplier<Byte> menuSupplier, Map<Byte, Runnable> actionMap) {
        while (true) {
            byte userOption = menuSupplier.get();
            Runnable action = actionMap.get(userOption);

            if (action != null) {
                action.run();
            } else {
                throw new GeneralExceptions(Exceptions.INVALID_OPTION);
            }
        }
    }

//    Book Management

    public static void manageBooks(BookService bookService) {
        while (true) {
            byte bookOption = MenuUtil.bookMenu();
            switch (bookOption) {
                case 0 -> {
                    return;
                }
                case 1 -> bookService.addBook();
                case 2 -> bookService.updateBook();
                case 3 -> bookService.deleteBook();
                case 4 -> bookService.showAllBooks();
                default -> throw new GeneralExceptions(Exceptions.INVALID_OPTION);
            }
        }
    }

//    Borrowing Management

    public static void manageBorrowing(BorrowService borrowService) {
        while (true) {
            byte borrowOption = MenuUtil.borrowMenu();
            switch (borrowOption) {
                case 0 -> {
                    return;
                }
                case 1 -> borrowService.borrowBook();
                case 2 -> borrowService.returnBook();
                case 3 -> borrowService.showBorrows();
                case 4 -> borrowService.getOverdueBorrowRecords();
                case 5 -> borrowService.getOverdueFee();
                case 6 -> borrowService.getTotalCost();
                case 7 -> borrowService.updateBorrowRecord();
                case 8 -> borrowService.deleteBorrowRecord();
                default -> throw new GeneralExceptions(Exceptions.INVALID_OPTION);
            }
        }
    }

//    Common Management

    public static void manageCommon() {
        while (true) {
            byte commonOption = MenuUtil.commonMenu();
            switch (commonOption) {
                case 0 -> {
                    return;
                }
                case 1 -> System.out.println("Generating reports...");
                case 2 -> System.out.println("Showing logs...");
                default -> throw new GeneralExceptions(Exceptions.INVALID_OPTION);
            }
        }
    }

//    Extracted Methods

    private static void loginForAdmin(UserService userService) {
        String username = InputUtil.getInstance().inputTypeString("Enter username: ");
        String password = InputUtil.getInstance().inputTypeString("Enter password: ");

        userService.login(username, password);

        User currentUser = userService.getCurrentUser();

        if (currentUser != null && currentUser.getRole() == Role.ADMIN) {
            System.out.println("Welcome, Admin: " + currentUser.getName());
            adminMenu(userService);
        } else {
            System.out.println("You are not authorized as an admin.");
        }
    }

    private static void loginForUser(UserService userService) {
        String username = InputUtil.getInstance().inputTypeString("Enter username: ");
        String password = InputUtil.getInstance().inputTypeString("Enter password: ");

        userService.login(username, password);

        User currentUser = userService.getCurrentUser();
        if (currentUser != null && currentUser.getRole() == Role.USER) {
            System.out.println("Welcome: " + currentUser.getName());
            userMenu(userService);
        } else {
            throw new GeneralExceptions(Exceptions.USER_NOT_FOUND);
        }
    }

    private static void deleteUserAsAdmin(UserService userService) {
        Long userId = InputUtil.getInstance().inputTypeLong("Enter user ID to delete: ");
        User user = userService.getUserById(userId);
        userService.deleteByAdmin(user.getId());
    }

    private static void updateUserAsAdmin(UserService userService) {
        Long userId = InputUtil.getInstance().inputTypeLong("Enter user ID to update: ");

        System.out.println("""
                Select the fields to update (Enter numbers separated by commas):
                1. Name
                2. Surname
                3. Password
                4. Birthday
                5. Gmail
                6. Phone Number
                7. is Active
                8. isBlocked
                9. Role
                Enter the numbers of fields to update (e.g., 1,3):\s""");


        String selectedFields = InputUtil.getInstance().inputTypeString("");
        String[] selectedFieldsArray = selectedFields.split(",");

        User user = userService.getUserById(userId);

        Map<String, Runnable> updateActions = getStringRunnableMapForAdmin(user);

        for (String field : selectedFieldsArray) {
            Runnable action = updateActions.get(field.trim());
            if (action != null) {
                action.run();
            } else {
                System.out.println("Invalid option selected.");
            }
        }
        userService.updateUser(userId, user);
    }

    private static Map<String, Runnable> getStringRunnableMapForAdmin(User user) {
        Map<String, Runnable> updateActions = new LinkedHashMap<>();

        updateActions.put("1", () -> user.setName(InputUtil.getInstance().inputTypeString("Enter new name: ")));
        updateActions.put("2", () -> user.setSurname(InputUtil.getInstance().inputTypeString("Enter new surname: ")));
        updateActions.put("3", () -> user.setPassword(InputUtil.getInstance().inputTypeString("Enter new password: ")));
        updateActions.put("4", () -> user.setBirthDate(InputUtil.getInstance().inputTypeLocalDate("Enter new birth date (yyyy-MM-dd): ")));
        updateActions.put("5", () -> user.setGmail(InputUtil.getInstance().inputTypeString("Enter new Gmail: ")));
        updateActions.put("6", () -> user.setPhoneNumber(InputUtil.getInstance().inputTypeString("Enter new phone: ")));
        updateActions.put("7", () -> user.setActive(InputUtil.getInstance().inputTypeBoolean("Enter new active status: ")));
        updateActions.put("8", () -> user.setBlocked(InputUtil.getInstance().inputTypeBoolean("Enter new Blocked status: ")));
        updateActions.put("9", () -> user.setRole(InputUtil.getInstance().inputTypeRole("Enter new role: ")));
        return updateActions;
    }

    private static void updateUserAsUser(UserService userService) {
        User currentUser = userService.getCurrentUser();

        System.out.println("""
                Select the fields to update (Enter numbers separated by commas):
                1. Name
                2. Surname
                3. Username
                4. Password
                5. Birthday
                6. Gmail
                7. Phone Number
                Enter the numbers of fields to update (e.g., 1,3):\s""");

        String selectedFields = InputUtil.getInstance().inputTypeString("");
        String[] selectedFieldsArray = selectedFields.split(",");

        User user = userService.getUserById(currentUser.getId());

        Map<String, Runnable> updateActions = getStringRunnableMapForUser(user);

        for (String field : selectedFieldsArray) {
            Runnable action = updateActions.get(field.trim());
            if (action != null) {
                action.run();
            } else {
                System.out.println("Invalid option selected.");
            }
        }

        userService.updateUser(currentUser.getId(), user);

    }

    private static Map<String, Runnable> getStringRunnableMapForUser(User user) {
        Map<String, Runnable> updateActions = new LinkedHashMap<>();

        updateActions.put("1", () -> user.setName(InputUtil.getInstance().inputTypeString("Enter new name: ")));
        updateActions.put("2", () -> user.setSurname(InputUtil.getInstance().inputTypeString("Enter new surname: ")));
        updateActions.put("3", () -> user.setUsername(InputUtil.getInstance().inputTypeString("Enter new username: ")));
        updateActions.put("4", () -> user.setPassword(InputUtil.getInstance().inputTypeString("Enter new password: ")));
        updateActions.put("5", () -> user.setBirthDate(InputUtil.getInstance().inputTypeLocalDate("Enter new birth date (yyyy-MM-dd): ")));
        updateActions.put("6", () -> user.setGmail(InputUtil.getInstance().inputTypeString("Enter new Gmail: ")));
        updateActions.put("7", () -> user.setPhoneNumber(InputUtil.getInstance().inputTypeString("Enter new phone: ")));
        return updateActions;
    }

}
