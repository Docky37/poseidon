package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.BidList;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Bid list repository interface that extends JPA Repository.
 *
 * @Author Thierry Schreiner
 */
public interface BidListRepository extends JpaRepository<BidList, Integer> {

    /**
     * This method is used to get the bidList that has the given id.
     *
     * @param id
     * @return a BidList
     */
    BidList findByBidListId(Integer id);

}
