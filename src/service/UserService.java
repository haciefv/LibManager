package service;

import model.User;

public interface UserService {


     void registerUser(User user);
     void updateUser(Long id, User updateUser);
     void  deleteUser(Long id);
     void showUsers();
     User findUserById(Long id);



}
