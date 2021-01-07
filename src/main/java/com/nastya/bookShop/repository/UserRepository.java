package com.nastya.bookShop.repository;

import com.nastya.bookShop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUserName(String userName);

    Boolean existsByUserName(String username);

    Boolean existsByEmail(String email);

}
