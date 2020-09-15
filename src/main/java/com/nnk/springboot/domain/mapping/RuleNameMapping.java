package com.nnk.springboot.domain.mapping;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.dto.RuleNameDTO;

/**
 * This class is used to perform bidirectional mapping between a RuleName entity
 * and a RuleNameDTO.
 *
 * @author Thierry Schreiner
 */
@Component
public class RuleNameMapping {

    /**
     * This method is in charge of the mapping of a list of RuleName entities to
     * a list of RuleNameDTO. Use the mapEntityToDTO(RuleName ruleName)as a sub
     * method.
     *
     * @param listOfRuleName
     * @return a List<RuleNameDTO> object
     */
    public List<RuleNameDTO> mapAListOfRuleName(
            final List<RuleName> listOfRuleName) {
        List<RuleNameDTO> listRuleNameDTO = new ArrayList<>();
        for (RuleName ruleName : listOfRuleName) {
            RuleNameDTO ruleNameDTO = mapEntityToDTO(ruleName);
            listRuleNameDTO.add(ruleNameDTO);
        }

        return listRuleNameDTO;
    }

    /**
     * This method is in charge of the mapping of a RuleNameDTO to a RuleName
     * entity.
     *
     * @param ruleNameDTO
     * @return a RuleName object
     */
    public RuleName mapDTOToEntity(final RuleNameDTO ruleNameDTO) {
        final RuleName ruleName = new RuleName();
        ruleName.setId(ruleNameDTO.getId());
        ruleName.setName(ruleNameDTO.getName());
        ruleName.setDescription(ruleNameDTO.getDescription());
        ruleName.setJson(ruleNameDTO.getJson());
        ruleName.setTemplate(ruleNameDTO.getTemplate());
        ruleName.setSqlStr(ruleNameDTO.getSqlStr());
        ruleName.setSqlPart(ruleNameDTO.getSqlPart());

        return ruleName;
    }

    /**
     * This method is in charge of the mapping of a RuleName entity to a
     * RuleNameDTO.
     *
     * @param ruleName
     * @return a RuleNameDTO object
     */
    public RuleNameDTO mapEntityToDTO(final RuleName ruleName) {
        final RuleNameDTO ruleNameDTO = new RuleNameDTO();
        ruleNameDTO.setId(ruleName.getId());
        ruleNameDTO.setName(ruleName.getName());
        ruleNameDTO.setDescription(ruleName.getDescription());
        ruleNameDTO.setJson(ruleName.getJson());
        ruleNameDTO.setTemplate(ruleName.getTemplate());
        ruleNameDTO.setSqlStr(ruleName.getSqlStr());
        ruleNameDTO.setSqlPart(ruleName.getSqlPart());

        return ruleNameDTO;
    }

}
