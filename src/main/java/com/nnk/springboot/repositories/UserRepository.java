package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.User;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository
        extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    /**
     * Truncate method used in integration tests.
     */
    @Transactional
    @Modifying
    @Query(value = "TRUNCATE TABLE trade RESTART IDENTITY; ALTER SEQUENCE"
            + " hibernate_sequence RESTART WITH 1;", nativeQuery = true)
    void truncate();

    /**
     * This method is used to retrieve a registered user by his username.
     *
     * @param login
     * @return a User object
     */
    @Query(value = "SELECT users.* FROM users WHERE username = :username",
            nativeQuery = true)
    User findByUsername(@Param("username") String login);

}
