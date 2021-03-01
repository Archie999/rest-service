package com.example.restservice.repository;

import com.example.restservice.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * Created by Archie on 2021-03-01.
 */
@Service
public interface UserRepository extends CrudRepository<User, String> {

    User findFirstByNameAndAge(String name,int age);

}
