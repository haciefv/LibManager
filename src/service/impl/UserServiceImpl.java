package service.impl;

import enums.Exceptions;
import enums.Role;
import exceptions.GeneralExceptions;
import helper.UserServiceHelper;
import model.User;
import service.UserService;
import util.InputUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class UserServiceImpl implements UserService {

    private static final InputUtil inputUtil = InputUtil.getInstance();
    private User[] users = new User[10];
    private int userCount = 0;
    private static User currentUser;
    private static long userIdCounter = 1;


    public UserServiceImpl() {
        User admin = new User();
        admin.setName("admin");
        admin.setSurname("admin");
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setRole(Role.ADMIN);
        admin.setId(userIdCounter++);
        admin.setActive(true);
        admin.setBlocked(false);
        admin.setRegisteredDate(LocalDateTime.now());
        admin.setLastLoginDate(LocalDateTime.now());

        users[userCount++] = admin;
    }

    @Override
    public User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    private void expandUserArrayIfNeeded() {
        if (userCount == users.length) {
            User[] newUsers = new User[users.length + 5];
            System.arraycopy(users, 0, newUsers, 0, users.length);
            users = newUsers;
        }
    }

    @Override
    public void login(String username, String password) {
        User user = findUserByUsername(username);

        if (user == null) {
            throw new GeneralExceptions(Exceptions.USER_NOT_REGISTERED);
        }

        if (user.getPassword().equals(password)) {
            setCurrentUser(user);
            System.out.println("Login successful for: " + user.getName());
        } else {
            throw new GeneralExceptions(Exceptions.INVALID_CREDENTIALS);
        }
    }

    private User findUserByUsername(String username) {
        for (User user : users) {
            if (user != null && user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void registerAsUser() {
        String username = InputUtil.getInstance().inputTypeString("Enter your username: ");

        if (findUserByUsername(username) != null) {
            throw new GeneralExceptions(Exceptions.USER_ALREADY_REGISTERED);
        }

        String name = InputUtil.getInstance().inputTypeString("Enter your name: ");
        String surname = InputUtil.getInstance().inputTypeString("Enter your surname: ");
        String password = InputUtil.getInstance().inputTypeString("Enter your password: ");
        LocalDate birthDate = InputUtil.getInstance().inputTypeLocalDate("Enter your birthDate(yyyy-MM-dd): ");
        String gmail = InputUtil.getInstance().inputTypeString("Enter your gmail: ");
        String phoneNumber = InputUtil.getInstance().inputTypeString("Enter your phone number: ");

        User newUser = new User();

        newUser.setId(userIdCounter++);
        newUser.setName(name);
        newUser.setSurname(surname);
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setBirthDate(birthDate);
        newUser.setGmail(gmail);
        newUser.setPhoneNumber(phoneNumber);
        newUser.setActive(true);
        newUser.setBlocked(false);
        newUser.setRegisteredDate(LocalDateTime.now());
        newUser.setLastLoginDate(LocalDateTime.now());
        newUser.setRole(Role.USER);

        expandUserArrayIfNeeded();

        users[userCount++] = newUser;
        System.out.println("\nUser registered successfully");
    }

    @Override
    public void updateUser(Long id, User updateUser) {
        User existingUser = UserServiceHelper.findUserById(users, userCount, id);

        if (existingUser == null) {
            throw new GeneralExceptions(Exceptions.USER_NOT_FOUND);
        }

        UserServiceHelper.updateUserData(existingUser, updateUser);
        System.out.println("\nProfile has been updated successfully.");
    }

    @Override
    public void deleteProfile() {
        UserServiceHelper.checkIfUser(currentUser);
        UserServiceHelper.deleteUser(users, userCount, currentUser.getId());
        System.out.println("Your profile has been deleted successfully.");
    }

    @Override
    public void deleteByAdmin(Long id) {
        UserServiceHelper.checkIfAdmin(currentUser);
        UserServiceHelper.deleteUser(users, userCount, id);
        System.out.println("User with ID " + id + " has been deleted successfully.");
    }

    @Override
    public void showUsers() {

        UserServiceHelper.checkIfAdmin(currentUser);

        if (userCount == 0) {
            throw new GeneralExceptions(Exceptions.USER_NOT_FOUND);
        }

        for (int i = 0; i < userCount; i++) {
            User user = users[i];
            System.out.println(
                    "--------------------------------------" +
                            "\nId: " + user.getId() +
                            "\nName: " + user.getName() +
                            "\nSurname: " + user.getSurname() +
                            "\nUsername: " + user.getUsername() +
                            "\nPassword: " + user.getPassword() +
                            "\nBirthDate: " + user.getBirthDate() +
                            "\nGmail: " + user.getGmail() +
                            "\nPhone Number: " + user.getPhoneNumber() +
                            "\nActive: " + user.getIsActive() +
                            "\nBlocked: " + user.getIsBlocked() +
                            "\nRegistered Date: " + user.getRegisteredDate() +
                            "\nLast Login Date: " + user.getLastLoginDate() +
                            "\nRole: " + user.getRole() +
                            "\n--------------------------------------"
            );
        }
    }

    @Override
    public void showUserProfile() {
        UserServiceHelper.checkIfUser(currentUser);
        System.out.println(currentUser);
    }

    @Override
    public void logout() {
        currentUser = null;
        System.out.println("You have been logged out.");
    }

    @Override
    public User getUserById(Long userId) {

        for (User user : users) {
            if (Objects.equals(user.getId(), userId)) {
                return user;
            }
        }
        return null;

    }

//    --------------------------------------------------------------------------

//    FIXME: current user uzerinde duzelisler

//    --------------------------------------------------------------------------

//    TODO: burada userin staus ve blokedlerine baxmaq lazimdi eger uzer block olunubsa artiq girisine icaze olmayacaq mueyyen bir muddetlik (sebeb kodun 5 defe ardicil olaraq yalnis daxil olunmasi)
//    TODO: user oz melumatlarini silerken ve ya admin her hansisa uzeri silerkken onun statusunu deyismek bir basa silmemek
//    TODO: status deyisdikden sonra umumi siyahida hemin userin gorsenmemeyi

}
