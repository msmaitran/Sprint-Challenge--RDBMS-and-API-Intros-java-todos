package com.lambdaschool.javatodos.repositories;

import com.lambdaschool.javatodos.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
