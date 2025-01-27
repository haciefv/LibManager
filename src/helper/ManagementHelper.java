package helper;

import enums.Exceptions;
import enums.Role;
import exceptions.GeneralExceptions;
import model.User;
import service.BookService;
import service.BorrowService;
import service.UserService;
import util.InputUtil;
import util.MenuUtil;

import java.time.LocalDate;

public class ManagementHelper {

    public static void loginOrRegistrationAsUser(UserService userService) {
        while (true) {
            byte userOption = MenuUtil.loginOrRegisterAsUserMenu();

            switch (userOption) {
                case 0 -> {
                    return;
                }
                case 1 -> userService.registerAsUser();
                case 2 -> {

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


                default -> throw new GeneralExceptions(Exceptions.INVALID_OPTION);
            }
        }
    }

    public static void loginAsAdmin(UserService userService) {
        while (true) {
            byte userOption = MenuUtil.loginAsAdmin();
            switch (userOption) {
                case 0 -> {
                    return;
                }
                case 1 -> {

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
                default -> throw new GeneralExceptions(Exceptions.INVALID_OPTION);
            }
        }
    }

    public static void adminMenu(UserService userService) {
        while (true) {
            byte userOption = MenuUtil.adminMenu();
            switch (userOption) {
                case 0 -> {
                    return;
                }
                case 1 -> {
                    Long userId = InputUtil.getInstance().inputTypeLong("Enter user ID to update: ");

                    System.out.println("Select the fields to update (Enter numbers separated by commas):");
                    System.out.println("1. Name");
                    System.out.println("2. Surname");
                    System.out.println("3. Password");
                    System.out.println("4. Birthday");
                    System.out.println("5. Gmail");
                    System.out.println("6. Phone Number");
                    System.out.println("7. is Active");
                    System.out.println("8. isBlocked");
                    System.out.println("9. Role");
                    System.out.print("Enter the numbers of fields to update (e.g., 1,3): ");

                    String selectedFields = InputUtil.getInstance().inputTypeString("");
                    String[] selectedFieldsArray = selectedFields.split(",");

                    User user = userService.getUserById(userId);

                    for (String field : selectedFieldsArray) {
                        switch (field.trim()) {
                            case "1":
                                String updatedName = InputUtil.getInstance().inputTypeString("Enter new name: ");
                                user.setName(updatedName);
                                break;
                            case "2":
                                String surname = InputUtil.getInstance().inputTypeString("Enter new surname: ");
                                user.setSurname(surname);
                                break;
                            case "3":
                                String password = InputUtil.getInstance().inputTypeString("Enter new password: ");
                                user.setPassword(password);
                                break;
                            case "4":
                                LocalDate birthdate = InputUtil.getInstance().inputTypeLocalDate("Enter new birth date(yyyy-MM-dd): ");
                                user.setBirthDate(birthdate);
                                break;
                            case "5":
                                String gmail = InputUtil.getInstance().inputTypeString("Enter new gmail: ");
                                user.setGmail(gmail);
                                break;
                            case "6":
                                String updatedPhone = InputUtil.getInstance().inputTypeString("Enter new phone: ");
                                user.setPhoneNumber(updatedPhone);
                                break;
                            case "7":
                                Boolean active = InputUtil.getInstance().inputTypeBoolean("Enter new active status: ");
                                user.setActive(active);
                                break;
                            case "8":
                                Boolean blocked = InputUtil.getInstance().inputTypeBoolean("Enter new Blocked status: ");
                                user.setBlocked(blocked);
                                break;
                            case "9":
                                Role role = InputUtil.getInstance().inputTypeRole("Enter new role: ");
                                user.setRole(role);
                                break;
                            default:
                                System.out.println("Invalid option selected.");
                                break;
                        }
                    }

                    userService.updateUser(userId, user);
                }

                case 2 -> {
                    Long userId = InputUtil.getInstance().inputTypeLong("Enter user ID to delete: ");

                    User user = userService.getUserById(userId);
                    userService.deleteByAdmin(user.getId());
                }

                case 3 -> userService.showUsers();
                case 4 -> userService.showUserProfile();
                case 5 -> userService.logout();
                default -> throw new GeneralExceptions(Exceptions.INVALID_OPTION);
            }
        }
    }


    public static void userMenu(UserService userService) {
        while (true) {
            byte userOption = MenuUtil.userMenu();

            switch (userOption) {
                case 0 -> {
                    return;
                }
                case 1 -> {

                    User currentUser = userService.getCurrentUser();

                    System.out.println("Select the fields to update (Enter numbers separated by commas):");
                    System.out.println("1. Name");
                    System.out.println("2. Surname");
                    System.out.println("3. Username");
                    System.out.println("4. Password");
                    System.out.println("5. Birthday");
                    System.out.println("6. Gmail");
                    System.out.println("7. Phone Number");
                    System.out.print("Enter the numbers of fields to update (e.g., 1,3): ");

                    String selectedFields = InputUtil.getInstance().inputTypeString("");
                    String[] selectedFieldsArray = selectedFields.split(",");

                    User user = userService.getUserById(currentUser.getId());

                    for (String field : selectedFieldsArray) {
                        switch (field.trim()) {
                            case "1":
                                String updatedName = InputUtil.getInstance().inputTypeString("Enter new name: ");
                                user.setName(updatedName);
                                break;
                            case "2":
                                String surname = InputUtil.getInstance().inputTypeString("Enter new surname: ");
                                user.setSurname(surname);
                                break;
                            case "3":
                                String username = InputUtil.getInstance().inputTypeString("Enter new username: ");
                                user.setPassword(username);
                                break;
                            case "4":
                                String password = InputUtil.getInstance().inputTypeString("Enter new password: ");
                                user.setPassword(password);
                                break;
                            case "5":
                                LocalDate birthdate = InputUtil.getInstance().inputTypeLocalDate("Enter new birth date(yyyy-MM-dd): ");
                                user.setBirthDate(birthdate);
                                break;
                            case "6":
                                String gmail = InputUtil.getInstance().inputTypeString("Enter new gmail: ");
                                user.setGmail(gmail);
                                break;
                            case "7":
                                String updatedPhone = InputUtil.getInstance().inputTypeString("Enter new phone: ");
                                user.setPhoneNumber(updatedPhone);
                                break;
                            default:
                                System.out.println("Invalid option selected.");
                                break;
                        }
                    }

                    userService.updateUser(currentUser.getId(), user);
                }

                case 2 -> userService.deleteProfile();
                case 3 -> userService.showUserProfile();
                case 4 -> userService.logout();
                default -> throw new GeneralExceptions(Exceptions.INVALID_OPTION);
            }
        }
    }

    public static void manageBooks(BookService bookService) {
        while (true) {
            byte bookOption = MenuUtil.bookMenu();
            switch (bookOption) {
                case 0 -> {
                    return;
                }
                case 1 -> System.out.println("book Service daxilinde her hansisa method");
                case 2 -> System.out.println("book Service daxilinde her hansisa 2ci method");
                default -> throw new GeneralExceptions(Exceptions.INVALID_OPTION);
            }
        }
    }

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
}
