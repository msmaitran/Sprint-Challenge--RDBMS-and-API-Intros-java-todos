package com.lambdaschool.javatodos.controllers;

import com.lambdaschool.javatodos.models.Todo;
import com.lambdaschool.javatodos.models.User;
import com.lambdaschool.javatodos.services.TodoService;
import com.lambdaschool.javatodos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    TodoService todoService;

    @GetMapping(value = "/users",
                produces = {"application/json"})
    public ResponseEntity<?> listAllUsers() {
        List<User> myUsers = userService.findAll();
        return new ResponseEntity<>(myUsers, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{userid}",
                produces = {"application/json"})
    public ResponseEntity<?> findUserById(@PathVariable long userid) {
        User myUser = userService.findUserById(userid);
        return new ResponseEntity<>(myUser, HttpStatus.OK);
    }

    @PostMapping(value = "/user",
                 consumes = {"application/json"})
    public ResponseEntity<?> addNewUser(@Valid @RequestBody User newUser) {
        newUser = userService.save(newUser);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{userid}")
                .buildAndExpand(newUser.getUserid())
                .toUri();

        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @PostMapping(value = "/todo/{userid}",
                 consumes = {"application/json"})
    public ResponseEntity<?> addTodoToUser(@RequestBody Todo addTodo,
                                           @PathVariable long userid) {
        todoService.save(addTodo, userid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/userid/{userid}")
    public ResponseEntity<?> deleteUser(@PathVariable long userid) {
        userService.delete(userid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
