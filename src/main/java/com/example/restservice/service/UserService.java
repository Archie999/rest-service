package com.example.restservice.service;

import com.example.restservice.cache.UserCache;
import com.example.restservice.model.User;
import com.example.restservice.repository.UserRepository;
import com.example.restservice.util.UUIDUtil;
import com.example.restservice.util.exception.AlertException;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by Archie on 2021-03-01.
 */
@Service
public class UserService {

    @Autowired
    private UserCache userCache;

    public User getUser(String name,int age){
        return userCache.getUser(name,age);
    }

}
