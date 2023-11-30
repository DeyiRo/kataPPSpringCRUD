package web.dao;

import web.model.User;

import java.util.List;

public interface UserDAO {


    void saveUser(User user);

    List<User> getAllUsers();

    User findUserById(long id);

    void updateUserById(long id, User user);

    void deleteUserById(long id);


}
