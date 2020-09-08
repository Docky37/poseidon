package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Trade;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * RuleName repository interface that extends JPA Repository.
 *
 * @author Thierry Schreiner
 */
public interface TradeRepository extends JpaRepository<Trade, Integer> {

}
