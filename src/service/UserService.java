package service;

import domain.core.model.User;

public interface UserService {

     void login(String username, String password);
     void registerAsUser();
     void updateUser(Long id, User updateUser);
     void deleteProfile();
     void deleteByAdmin(Long id);
     void showUsers();
     void showUserProfile();
     void logout();

     User getCurrentUser();

     User getUserById(Long userId);
}
