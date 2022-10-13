package com.dbproject.cvapp.repository;

import java.util.Optional;

import com.dbproject.cvapp.model.Roles;
import com.dbproject.cvapp.model.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {
    Optional<Roles> findByName(UserRoles name);
}

