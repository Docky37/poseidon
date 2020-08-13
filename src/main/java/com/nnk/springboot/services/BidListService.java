package com.nnk.springboot.services;

import java.util.List;

import com.nnk.springboot.domain.BidListDTO;

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
     * @return a List<BidListDTO>
     */
    BidListDTO save(BidListDTO bidListDTO);

    /**
     * Allows user to delete a Poseidon BidList of the DataBase.
     *
     * @param id
     * @return a BidListDTO
     */
    BidListDTO delete(Integer id);

    /**
     * Use to get the Poseidon bidList identified by the given id.
     *
     * @param id
     * @return a BidListDTO
     */
    BidListDTO getById(Integer id);

}
