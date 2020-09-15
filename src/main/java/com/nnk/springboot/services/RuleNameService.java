package com.nnk.springboot.services;

import java.util.List;

import com.nnk.springboot.dto.RuleNameDTO;
import com.nnk.springboot.exceptions.RuleNameNotFoundException;

/**
 * This RuleNameService interface defines four methods in charge of driving
 * the RuleName CRUD operations using DataTransferObject.
 *
 * @author Thierry Schreiner
 */
public interface RuleNameService {

    /**
     * Used to get a list of all Poseidon RuleName stored in the rule_name
     * table of the Database.
     *
     * @return a List<RuleName>
     */
    List<RuleNameDTO> findAll();

    /**
     * Used to persist a Poseidon RuleName in DataBase.
     *
     * @param ruleNameDTO
     * @return a RuleNameDTO
     */
    RuleNameDTO save(RuleNameDTO ruleNameDTO);

    /**
     * Use to get the Poseidon RuleName identified by the given id.
     *
     * @param id
     * @return a RuleNameDTO
     * @throws RuleNameNotFoundException
     */
    RuleNameDTO getById(int id) throws RuleNameNotFoundException;

    /**
     * Allows user to delete a Poseidon RuleName of the DataBase.
     *
     * @param id
     * @return a RuleNameDTO
     * @throws RuleNameNotFoundException
     */
    RuleNameDTO delete(int id) throws RuleNameNotFoundException;

}
