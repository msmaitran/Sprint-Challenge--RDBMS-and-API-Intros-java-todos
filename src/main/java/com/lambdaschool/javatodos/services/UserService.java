package com.lambdaschool.javatodos.services;

import com.lambdaschool.javatodos.models.Todo;
import com.lambdaschool.javatodos.models.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findUserById(long id);

    User save(User user);

    void delete(long id);
}
