package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.RuleName;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * RuleName repository interface that extends JPA Repository.
 *
 * @author Thierry Schreiner
 */
public interface RuleNameRepository extends JpaRepository<RuleName, Integer> {

    /**
     * Truncate method used in integration tests.
     */
    @Transactional
    @Modifying
    @Query(value = "TRUNCATE TABLE rule_name RESTART IDENTITY; ALTER SEQUENCE"
            + " hibernate_sequence RESTART WITH 1;", nativeQuery = true)
    void truncate();

}
