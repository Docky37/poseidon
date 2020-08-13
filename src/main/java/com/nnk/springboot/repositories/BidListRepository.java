package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.BidList;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Bid list repository interface that extends JPA Repository.
 *
 * @Thierry Schreiner
 */
public interface BidListRepository extends JpaRepository<BidList, Integer> {

	/**
	 * 
	 * @param id
	 * @return a BidList
	 */
    BidList findByBidListId(Integer id);

}
