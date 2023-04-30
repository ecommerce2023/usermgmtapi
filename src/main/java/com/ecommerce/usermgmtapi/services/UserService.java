package com.ecommerce.usermgmtapi.services;

import com.ecommerce.usermgmtapi.domain.User;

import java.util.Optional;

public interface UserService {
    User save(User user);
    Optional<User> getById(Long id);
}
