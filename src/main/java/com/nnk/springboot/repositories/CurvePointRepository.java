package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.CurvePoint;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CurvePoint repository interface that extends JPA Repository.
 *
 * @author Thierry Schreiner
 */
public interface CurvePointRepository
        extends JpaRepository<CurvePoint, Integer> {

}
