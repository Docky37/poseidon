package com.nnk.springboot.services;

import java.util.List;

import com.nnk.springboot.dto.UserDTO;
import com.nnk.springboot.exceptions.UserNotFoundException;

/**
 * This UserService interface defines four methods in charge of driving the User
 * CRUD operations using DataTransferObject.
 *
 * @author Thierry Schreiner
 */
public interface UserService {

    /**
     * Used to get a list of all Poseidon users stored in the user table of the
     * Database.
     *
     * @return a List<UserDTO> object
     */
    List<UserDTO> findAll();

    /**
     * Used to persist a Poseidon user in DataBase.
     *
     * @param userDTO
     * @return a UserDTO object
     */
    UserDTO save(UserDTO userDTO);

    /**
     * Use to get the Poseidon RuleName identified by the given id.
     * 
     * @param id
     * @return a UserDTO object
     * @throws UserNotFoundException
     */
    UserDTO getById(int id) throws UserNotFoundException;

    /**
     * Allows user to delete a Poseidon user of the DataBase.
     *
     * @param id
     * @return a UserDTO object
     * @throws UserNotFoundException 
     */
    UserDTO delete(int id) throws UserNotFoundException;

}
