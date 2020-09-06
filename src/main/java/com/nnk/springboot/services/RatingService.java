package com.nnk.springboot.services;

import java.util.List;

import com.nnk.springboot.domain.RatingDTO;
import com.nnk.springboot.exceptions.RatingNotFoundException;

/**
 * This CurvePointService interface defines five methods in charge of driving
 * the CurvePoint CRUD operations using DataTransferObject.
 *
 * @author Thierry Schreiner
 */
public interface RatingService {

    /**
     * Used to get a list of all Poseidon Rating stored in the curve_point
     * table of the Database.
     *
     * @return a List<Rating>
     */
    List<RatingDTO> findAll();

    /**
     * Used to persist a Poseidon Rating in DataBase.
     *
     * @param ratingDTO
     * @return a Rating
     */
    RatingDTO save(RatingDTO ratingDTO);

    /**
     * Allows user to delete a Poseidon Rating of the DataBase.
     *
     * @param id
     * @return a RatingDTO
     * @throws RatingNotFoundException
     */
    RatingDTO delete(Integer id) throws RatingNotFoundException;

    /**
     * Use to get the Poseidon Rating identified by the given id.
     *
     * @param id
     * @return a RatingDTO
     * @throws RatingNotFoundException
     */
    RatingDTO getById(Integer id) throws RatingNotFoundException;

}
