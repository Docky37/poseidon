package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.RuleName;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * RuleName repository interface that extends JPA Repository.
 *
 * @author Thierry Schreiner
 */
public interface RuleNameRepository extends JpaRepository<RuleName, Integer> {

}
