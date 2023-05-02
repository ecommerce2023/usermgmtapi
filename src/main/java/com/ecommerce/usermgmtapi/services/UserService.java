package com.ecommerce.usermgmtapi.services;

import java.util.Optional;

import com.ecommerce.usermgmtapi.models.User;

public interface UserService {
    User save(User user);
    Optional<User> getById(Long id);
}
