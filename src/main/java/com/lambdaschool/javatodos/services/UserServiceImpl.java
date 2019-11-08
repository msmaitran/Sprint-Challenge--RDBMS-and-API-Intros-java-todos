package com.lambdaschool.javatodos.services;

import com.lambdaschool.javatodos.models.Role;
import com.lambdaschool.javatodos.models.Todo;
import com.lambdaschool.javatodos.models.User;
import com.lambdaschool.javatodos.repositories.RoleRepository;
import com.lambdaschool.javatodos.repositories.TodoRepository;
import com.lambdaschool.javatodos.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userrepos;

    @Autowired
    RoleRepository rolerrepos;

    @Autowired
    TodoRepository todorepos;

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public User findUserById(long id) {
        return userrepos.findById(id).orElseThrow(() ->
                new EntityNotFoundException(Long.toString(id)));
    }

    @Transactional
    @Override
    public User save(User user) {
        User newUser = new User();

        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setPrimaryemail(user.getPrimaryemail());

        for (Role r : user.getRoles()) {
            Role newRole = rolerrepos.findById(r.getRoleid()).orElse(r);
            newUser.getRoles().add(newRole);
        }

        for (Todo t : user.getTodos()) {
            Todo newTodo = new Todo(t.getDescription(), t.getDatestarted(), newUser);
            newUser.getTodos().add(newTodo);
        }
        return userrepos.save(newUser);
    }

    @Override
    public User addTodoToUser(Todo todo, long id) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
