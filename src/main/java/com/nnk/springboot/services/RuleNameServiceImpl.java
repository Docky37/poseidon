package com.nnk.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.mapping.RuleNameMapping;
import com.nnk.springboot.dto.RuleNameDTO;
import com.nnk.springboot.exceptions.RuleNameNotFoundException;
import com.nnk.springboot.repositories.RuleNameRepository;

/**
 * Implementation class of the RuleNameService interface, this class answer to
 * RuleNameController request using RuleNameRepository and RuleNameMapping
 * classes.
 *
 * @author Thierry Schreiner
 */
@Service
public class RuleNameServiceImpl implements RuleNameService {

    /**
     * RuleNameRepository bean injected by Spring when service is created.
     */
    @Autowired
    private RuleNameRepository ruleNameRepository;

    /**
     * RuleNameMapping bean injected by Spring when service is created.
     */
    @Autowired
    private RuleNameMapping ruleNameMapping;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RuleNameDTO> findAll() {

        return ruleNameMapping.mapAListOfRuleName(ruleNameRepository.findAll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RuleNameDTO save(final RuleNameDTO ruleNameDTO) {
        RuleName ruleName = ruleNameMapping.mapDTOToEntity(ruleNameDTO);
        System.out.println(ruleName.toString());
        RuleName savedRuleName = ruleNameRepository.save(ruleName);

        return ruleNameMapping.mapEntityToDTO(savedRuleName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RuleNameDTO getById(final int id) throws RuleNameNotFoundException {
        RuleName ruleName = ruleNameRepository.findById(id)
                .orElseThrow(() -> new RuleNameNotFoundException(
                        "No RuleName record exist for given id"));

        return ruleNameMapping.mapEntityToDTO(ruleName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RuleNameDTO delete(final int id) throws RuleNameNotFoundException {
        RuleName ruleName = ruleNameRepository.findById(id)
                .orElseThrow(() -> new RuleNameNotFoundException(
                        "No RuleName record exist for given id"));
        ruleNameRepository.deleteById(id);

        return ruleNameMapping.mapEntityToDTO(ruleName);
    }

}
