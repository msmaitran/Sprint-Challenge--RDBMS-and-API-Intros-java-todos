package com.lambdaschool.javatodos.services;

import com.lambdaschool.javatodos.models.Todo;
import com.lambdaschool.javatodos.models.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findUserById(long id);

    User save(User user);

    User addTodoToUser(Todo todo, long id);

    void delete(long id);
}
