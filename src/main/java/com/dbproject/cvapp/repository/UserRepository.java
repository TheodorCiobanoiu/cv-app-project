package com.dbproject.cvapp.repository;

import com.dbproject.cvapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUsername(String username);

    User findUserByEmail(String email);

}