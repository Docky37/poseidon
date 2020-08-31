package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.BidList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Bid list repository interface that extends JPA Repository.
 *
 * @author Thierry Schreiner
 */
public interface BidListRepository extends JpaRepository<BidList, Integer> {

    /**
     * This method is used to get the bidList that has the given id.
     *
     * @param id
     * @return a BidList
     */
    BidList findByBidListId(Integer id);

    /**
     * Truncate method used in integration tests.
     */
    @Transactional
    @Modifying
    @Query(value = "TRUNCATE TABLE bid_list RESTART IDENTITY; ALTER SEQUENCE"
            + " hibernate_sequence RESTART WITH 1;", nativeQuery = true)
    void truncate();
}
