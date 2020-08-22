package com.nnk.springboot.services;

import java.util.List;

import javax.validation.Valid;

import com.nnk.springboot.domain.BidListDTO;
import com.nnk.springboot.domain.BidListFullDTO;
import com.nnk.springboot.exceptions.BidListNotFoundException;

/**
 * This BidListService interface defines five methods in charge of driving the
 * BidList CRUD operations using DatTransferObject.
 *
 * @author Thierry Schreiner
 */
public interface BidListService {

    /**
     * Used to get a list of all Poseidon bidLists stored in the bid_list table
     * of the Database.
     *
     * @return a List<BidListDTO>
     */
    List<BidListDTO> findAll();

    /**
     * Used to persist a Poseidon BidList in DataBase.
     *
     * @param bidListDTO
     * @return a BidListDTO
     */
    BidListDTO save(BidListDTO bidListDTO);

    /**
     * Used to persist a Poseidon BidList after update in DataBase.
     *
     * @param bidListDTO
     * @return a BidListFullDTO object
     */
    BidListFullDTO saveFullDTO(@Valid BidListFullDTO bidListDTO);

    /**
     * Allows user to delete a Poseidon BidList of the DataBase.
     *
     * @param id
     * @return a BidListDTO
     * @throws BidListNotFoundException
     */
    BidListDTO delete(Integer id) throws BidListNotFoundException;

    /**
     * Use to get the Poseidon bidList identified by the given id.
     *
     * @param id
     * @return a BidListDTO
     * @throws BidListNotFoundException
     */
    BidListFullDTO getById(Integer id) throws BidListNotFoundException;

}
