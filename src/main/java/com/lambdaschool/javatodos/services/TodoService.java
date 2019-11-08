package com.lambdaschool.javatodos.services;

import com.lambdaschool.javatodos.models.Todo;

import java.util.List;

public interface TodoService {

    List<Todo> findAll();

    Todo update(Todo todo, long todoid);

    Todo save(Todo todo, long userid);
}
