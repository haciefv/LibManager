package service.impl;

import model.User;
import service.UserService;


public class UserServiceImpl implements UserService {

    private User[] users = new User[0];
    private int userCount = 0;



    private void expandUserArrayIfNeeded() {
        if (userCount == users.length) {
            User[] newUsers = new User[users.length + 5];
            System.arraycopy(users, 0, newUsers, 0, users.length);
            users = newUsers;

        }

    }

    @Override
    public void registerUser(User user) {
        if (findUserById(user.getId()) != null) {
            System.out.println("User already registered");

            return;
        }
        expandUserArrayIfNeeded();
        users[userCount++] = user;

        System.out.println("User registered successfully");


    }

    @Override
    public void updateUser(Long id, User updateUser) {
        for (int i = 0; i < userCount; i++) {
            if (users[i].getId() == id) {
                users[i] = updateUser;

                System.out.println("User updated successfully");

                return;
            }
        }

        System.out.println("User not found");

    }

    @Override
    public void deleteUser(Long id) {
        for (int i = 0; i < userCount; i++) {
            if (users[i].getId() == id) {

                for (int j = i; j < userCount - 1; j++) {
                    users[j] = users[j + 1];
                }
                users[--userCount] = null;

                System.out.println("User deleted successfully");

                return;
            }

        }

        System.out.println("User not found");

    }

    @Override
    public void showUsers() {
        if (userCount==0){
            System.out.println("Not found any user");

            return;
        }
        for (int i = 0; i < userCount; i++){
            User user=users[i];
            System.out.println("name: "+user.getName()+"surname: "+user.getSurName());
        }

    }

    @Override
    public User findUserById(Long id) {
        for (int i = 0; i < userCount; i++) {
            if (users[i].getId() == id) {

                return users[i];
            }


        }
        return null;
    }
}