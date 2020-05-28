package com.greenfoxacademy.zerothweekproject.repositories;

import com.greenfoxacademy.zerothweekproject.modells.daos.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
  User findByName(String name);
}