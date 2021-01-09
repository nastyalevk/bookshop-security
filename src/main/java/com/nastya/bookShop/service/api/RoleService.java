package com.nastya.bookShop.service.api;

import com.nastya.bookShop.model.role.RoleDto;

import java.util.Optional;

public interface RoleService {

    Optional<RoleDto> findByName(String name);

}
