package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.CurvePoint;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CurvePoint repository interface that extends JPA Repository.
 *
 * @author Thierry Schreiner
 */
public interface CurvePointRepository
        extends JpaRepository<CurvePoint, Integer> {

    /**
     * This method is used to get the CurvePoint that has the given id.
     *
     * @param id
     * @return a CurvePoint
     */
    Optional<CurvePoint> findById(Integer id);

}
