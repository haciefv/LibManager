package helper;

import enums.Exceptions;
import exceptions.GeneralExceptions;
import model.User;
import enums.Role;

public class UserServiceHelper {

    public static User findUserById(User[] users, int userCount, Long id) {
        for (int i = 0; i < userCount; i++) {
            if (users[i] != null && users[i].getId().equals(id)) {
                return users[i];
            }
        }
        return null;
    }

    public static User findUserByCredentials(User[] users, int userCount, String usernameOrGmail, String password) {
        for (int i = 0; i < userCount; i++) {
            if (users[i] != null &&
                    (users[i].getGmail().equals(usernameOrGmail) || users[i].getUsername().equals(usernameOrGmail)) &&
                    users[i].getPassword().equals(password)) {
                return users[i];
            }
        }
        return null;
    }

    public static void checkIfAdmin(User currentUser) {
        if (currentUser == null || currentUser.getRole() != Role.ADMIN) {
            throw new GeneralExceptions(Exceptions.UNAUTHORIZED_ACCESS);
        }
    }

    public static void checkIfUser(User currentUser) {
        if (currentUser == null) {
            throw new GeneralExceptions(Exceptions.USER_NOT_FOUND);
        }
    }

    public static void updateUserData(User existingUser, User updateUser) {
        existingUser.setName(updateUser.getName());
        existingUser.setSurname(updateUser.getSurname());
        existingUser.setPhoneNumber(updateUser.getPhoneNumber());
        existingUser.setGmail(updateUser.getGmail());
        existingUser.setUsername(updateUser.getUsername());
    }

    public static void deleteUser(User[] users, int userCount, Long id) {
        for (int i = 0; i < userCount; i++) {
            if (users[i] != null && users[i].getId().equals(id)) {
                for (int j = i; j < userCount - 1; j++) {
                    users[j] = users[j + 1];
                }
                users[--userCount] = null;
                return;
            }
        }
        throw new GeneralExceptions(Exceptions.USER_NOT_FOUND);
    }
}
