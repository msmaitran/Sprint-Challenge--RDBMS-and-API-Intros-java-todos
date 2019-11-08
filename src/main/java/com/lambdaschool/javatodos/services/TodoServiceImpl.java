package com.lambdaschool.javatodos.services;

import com.lambdaschool.javatodos.models.Todo;
import com.lambdaschool.javatodos.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "todoService")
public class TodoServiceImpl implements TodoService {

    @Autowired
    TodoRepository todorepos;

    @Transactional
    @Override
    public List<Todo> findAll() {
        List<Todo> list = new ArrayList<>();
        todorepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Todo update(Todo todo, long todoid) {
        Todo currentTodo = todorepos.findById(todoid).orElseThrow(() ->
                new EntityNotFoundException(Long.toString(todoid)));

        if (todo.getDatestarted() != null) {
            currentTodo.setDatestarted(todo.getDatestarted());
        }

        if (todo.getDescription() != null) {
            currentTodo.setDescription(todo.getDescription());
        }

        currentTodo.setCompleted(todo.isCompleted());

        return todorepos.save(currentTodo);
    }
}
