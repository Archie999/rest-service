package com.example.restservice;

import com.example.restservice.model.User;
import com.example.restservice.service.UserService;
import com.example.restservice.util.exception.AlertException;
import com.example.restservice.util.http.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by Archie on 2021-03-01.
 */

@RestController
public class PostController {



    @Autowired
    private UserService userService;

        @PostMapping("/getUser")
    public ResultEntity<User> getUser(@RequestParam String name,@RequestParam int age) {
        return ResultEntity.ok(userService.getUser(name,age));
    }

    @PostMapping("/testError")
    public ResultEntity testError() {
        throw new AlertException("模拟异常");
    }
}
