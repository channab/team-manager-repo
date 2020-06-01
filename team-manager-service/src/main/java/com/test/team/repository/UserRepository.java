package com.test.team.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.test.team.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, String>{

}
