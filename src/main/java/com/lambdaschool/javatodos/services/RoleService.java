package com.lambdaschool.javatodos.services;

import com.lambdaschool.javatodos.models.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAllRoles();

    Role findByName(String name);

    Role findRoleById(long id);

    Role save(Role role);

    Role update(long id, Role role);

    void delete(long id);
}
