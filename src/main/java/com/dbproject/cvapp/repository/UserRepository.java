package com.dbproject.cvapp.repository;

import com.dbproject.cvapp.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUser, Integer> {
    Optional<MyUser> findUserByUsername(String username);

    Optional<MyUser> findUserByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Modifying
    @Transactional
    @Query("DELETE from MyUser u WHERE u.username = :username")
    void deleteByUsername(String username);

}