package com.dbproject.cvapp.repository;

import com.dbproject.cvapp.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<MyUser, Integer> {
    MyUser findUserByUsername(String username);

    MyUser findUserByEmail(String email);

}