package com.ecommerce.usermgmtapi.serviceImpl;

import com.ecommerce.usermgmtapi.models.User;
import com.ecommerce.usermgmtapi.repositories.UserRepository;
import com.ecommerce.usermgmtapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User save(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public Optional<User> getById(Long id) {
        return this.userRepository.findById(id);
    }

}
