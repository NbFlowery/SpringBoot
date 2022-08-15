package com.nongboo.flowery.service;

import com.nongboo.flowery.entity.User;
import org.springframework.stereotype.Service;


public interface UserService {

    User findOne(long id);

}
