package com.nnk.springboot.services;

import java.util.List;

import javax.validation.Valid;

import com.nnk.springboot.domain.CurvePointDTO;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.exceptions.CurvePointNotFoundException;

/**
 * This CurvePointService interface defines five methods in charge of driving
 * the CurvePoint CRUD operations using DataTransferObject.
 *
 * @author Thierry Schreiner
 */
public interface CurvePointService {

    /**
     * Used to get a list of all Poseidon CurvePoint stored in the curve_point
     * table of the Database.
     *
     * @return a List<CurvePoint>
     */
    List<CurvePointDTO> findAll();

    /**
     * Used to persist a Poseidon CurvePoint in DataBase.
     *
     * @param curvePointDTO
     * @return a CurvePoint
     */
    CurvePointDTO save(CurvePointDTO curvePointDTO );

    /**
     * Used to persist a Poseidon CurvePoint after update in DataBase.
     *
     * @param bidListDTO
     * @return a BidListFullDTO object
     */
    CurvePointDTO saveFullDTO(@Valid CurvePointDTO curvePointDTO);

    /**
     * Allows user to delete a Poseidon CurvePoint of the DataBase.
     *
     * @param id
     * @return a CurvePointDTO
     * @throws CurvePointNotFoundException
     */
    CurvePointDTO delete(Integer id) throws CurvePointNotFoundException;

    /**
     * Use to get the Poseidon CurvePoint identified by the given id.
     *
     * @param id
     * @return a CurvePointDTO
     * @throws CurvePointNotFoundException
     */
    CurvePointDTO getById(Integer id) throws CurvePointNotFoundException;

}
