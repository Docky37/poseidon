package com.nnk.springboot.services;

import java.util.List;

/**
 * This TradeService interface defines five methods in charge of driving the
 * Trade CRUD operations using DatTransferObject.
 *
 * @author Thierry Schreiner
 */
import com.nnk.springboot.dto.TradeDTO;
import com.nnk.springboot.dto.TradeFullDTO;
import com.nnk.springboot.exceptions.TradeNotFoundException;

public interface TradeService {

    /**
     * Used to get a list of all Poseidon trades stored in the trade table of
     * the Database.
     *
     * @return a List<TradeDTO>
     */
    List<TradeDTO> findAll();

    /**
     * Used to persist a Poseidon trade in DataBase.
     *
     * @param tradeDTO
     * @return a TradeDTO object
     */
    TradeDTO save(TradeDTO tradeDTO);

    /**
     * Used to persist a Poseidon Trade after update in DataBase.
     *
     * @param bidListDTO
     * @return a TradeFullDTO object
     */
    TradeFullDTO saveFullDTO(TradeFullDTO tradeDTO);

    /**
     * Allows user to delete a Poseidon Trade of the DataBase.
     *
     * @param id
     * @return a TradeDTO
     * @throws TradeNotFoundException
     */
    TradeDTO delete(int id) throws TradeNotFoundException;

    /**
     * Use to get the Poseidon bidList identified by the given id.
     *
     * @param id
     * @return a TradeDTO
     * @throws TradeNotFoundException
     */
    TradeDTO getById(int id) throws TradeNotFoundException;

}
