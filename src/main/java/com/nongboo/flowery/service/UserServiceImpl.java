package com.nongboo.flowery.service;


import com.nongboo.flowery.entity.User;
import com.nongboo.flowery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findOne(long id) {

        Optional<User> user = userRepository.findById(id);

        if (user.isPresent())
            return user.get();
        else
            return null;
    }
}
