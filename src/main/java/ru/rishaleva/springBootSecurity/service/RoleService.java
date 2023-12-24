package ru.rishaleva.springBootSecurity.service;


import ru.rishaleva.springBootSecurity.model.Role;
import ru.rishaleva.springBootSecurity.model.User;

import java.util.List;

public interface RoleService {
    List<Role> getRoles();

    Role findById(Long id);
    void addRole(Role role);
}