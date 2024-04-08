package ru.rishaleva.springBootSecurity.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.rishaleva.springBootSecurity.dao.UserDao;
import ru.rishaleva.springBootSecurity.model.User;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findByUserEmail(String email) {
        return userDao.findByUserEmail(email);
    }

    @Override
    public User getUser(Long id) {
        return userDao.getUser(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void addUser(User user) {
        user.setId(null);
        userDao.addUser(user);
    }

    @Override
    public void removeUser(Long id) {
        userDao.removeUser(id);
    }

    @Override
    public void updateUser(Long id, User user) {
        var existingUser = getUser(id);

        existingUser.setUsername(user.getUsername());
        existingUser.setLastname(user.getLastname());
        existingUser.setEmail(user.getEmail());
        existingUser.setAge(user.getAge());
        if (!user.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(existingUser.getPassword()));
        }
        existingUser.setRoles(user.getRoles());
        userDao.addUser(existingUser);
    }
}


