package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Rating;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * CurvePoint repository interface that extends JPA Repository.
 *
 * @author Thierry Schreiner
 */
public interface RatingRepository extends JpaRepository<Rating, Integer> {

    /**
     * Truncate method used in integration tests.
     */
    @Transactional
    @Modifying
    @Query(value = "TRUNCATE TABLE rating RESTART IDENTITY; ALTER SEQUENCE"
            + " hibernate_sequence RESTART WITH 1;", nativeQuery = true)
    void truncate();

}
