package com.test.team.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.team.entity.UserAccess;
import com.test.team.entity.UserAccessId;

@Repository
public interface UserAccessRepository extends JpaRepository<UserAccess, UserAccessId> {

}
