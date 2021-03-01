package com.example.restservice.cache;

import com.alibaba.fastjson.JSONObject;
import com.example.restservice.model.User;
import com.example.restservice.repository.UserRepository;
import com.example.restservice.util.UUIDUtil;
import com.example.restservice.util.exception.AlertException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by Archie on 2021-03-01.
 */
@Service
public class UserCache {

    private final String USER_INFO_CACHE_KEY ="User:";

    private final String ADD_USER_INFO_LOCK ="User:LOCK:";

    @Autowired
    private UserRepository userRepository;

    @Resource
    private RedisTemplate redisTemplate;


    public User getUser(String name,int age){

        User userCacheVal = (User) redisTemplate.opsForValue().get(USER_INFO_CACHE_KEY + name + age);
        if (null==userCacheVal){
            User firstByNameAndAge = userRepository.findFirstByNameAndAge(name, age);
            if(null!=firstByNameAndAge){
                redisTemplate.opsForValue().setIfAbsent(USER_INFO_CACHE_KEY+name+age,firstByNameAndAge,3L, TimeUnit.MINUTES);
                return firstByNameAndAge;
            }

            return  createUser(name,age);

        }
        return userCacheVal;
    }

    public User createUser(String name,int age){

        if(redisTemplate.hasKey(ADD_USER_INFO_LOCK+name+age)){
            throw new AlertException("请您稍后再试");
        }
        redisTemplate.opsForValue().set(ADD_USER_INFO_LOCK+name+age,"",300L,TimeUnit.MILLISECONDS);
        User user = new User();
        user.setAge(age);
        user.setName(name);
        user.setUserId(UUIDUtil.uuid());
        userRepository.save(user);
        redisTemplate.opsForValue().setIfAbsent(USER_INFO_CACHE_KEY+name+age,user,3L, TimeUnit.MINUTES);

        return user;
    }
}
