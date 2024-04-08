package ru.rishaleva.springBootSecurity.dao;

import org.springframework.stereotype.Repository;
import ru.rishaleva.springBootSecurity.model.User;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserDao {
    User findByUserEmail(String email);

    Optional<User> getUser(Long id);

    List<User> getAllUsers();

    void addUser(User user);

    void removeUser(Long id);

    void updateUser(Long id, User user);

}
