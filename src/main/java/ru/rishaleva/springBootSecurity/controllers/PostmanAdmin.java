package ru.rishaleva.springBootSecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rishaleva.springBootSecurity.model.User;
import ru.rishaleva.springBootSecurity.service.RoleService;
import ru.rishaleva.springBootSecurity.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class PostmanAdmin {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public PostmanAdmin(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    //Get User
    @GetMapping("/pass")
    public ResponseEntity<User> getCurrentUser(Principal principal) {
        return new ResponseEntity<>(userService.findByUserEmail(principal.getName()), HttpStatus.OK);
    }
    // GET ALL USERS
    @GetMapping(value = "/usersp")
    public List<User> getAllUsers() {
        List<User> listOfUsers = userService.getAllUsers();
        return listOfUsers;
    }
    //ADD NEW
    @PostMapping(value = "/users")
    public User addUser(@RequestBody User user) {
        userService.addUser(user);
        return user;
    }
    // UPDATE
    @PutMapping(value = "/users/{id}")
    public User updateUser(@PathVariable long id, @RequestBody User user){
        userService.updateUser(user);
        return user;
    }
    //DELETE
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.removeUser(id);
        return "User with id "  + id + " was deleted";
    }
}
