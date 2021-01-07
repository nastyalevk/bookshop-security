package com.nastya.bookShop.repository;

import com.nastya.bookShop.model.ERole;
import com.nastya.bookShop.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(ERole name);

}

