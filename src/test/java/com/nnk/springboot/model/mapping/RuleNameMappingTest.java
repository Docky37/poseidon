package com.nnk.springboot.model.mapping;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.mapping.RuleNameMapping;
import com.nnk.springboot.dto.RuleNameDTO;

@SpringJUnitConfig(value = RuleNameMapping.class)
public class RuleNameMappingTest {

    @Autowired
    RuleNameMapping ruleNameMapping;

    static List<RuleName> listOfRuleName = new ArrayList<>();
    static List<RuleNameDTO> listOfRuleNameDTO = new ArrayList<>();
    static {
        listOfRuleName.add(new RuleName());
        listOfRuleName.get(0).setId(1);
        listOfRuleName.get(0).setName("Name 1");
        listOfRuleName.get(0).setDescription("Description 1");
        listOfRuleName.get(0).setJson("json 1");
        listOfRuleName.get(0).setTemplate("Template 1");
        listOfRuleName.get(0).setSqlStr("sqlStr 1");
        listOfRuleName.get(0).setSqlPart("sqlPart 1");
        listOfRuleName.add(new RuleName());
        listOfRuleName.get(1).setId(1);
        listOfRuleName.get(1).setName("Name 1");
        listOfRuleName.get(1).setDescription("Description 1");
        listOfRuleName.get(1).setJson("json 1");
        listOfRuleName.get(1).setTemplate("Template 1");
        listOfRuleName.get(1).setSqlStr("sqlStr 1");
        listOfRuleName.get(1).setSqlPart("sqlPart 1");

        listOfRuleNameDTO.add(new RuleNameDTO());
        listOfRuleNameDTO.get(0).setId(1);
        listOfRuleNameDTO.get(0).setName("Name 1");
        listOfRuleNameDTO.get(0).setDescription("Description 1");
        listOfRuleNameDTO.get(0).setJson("json 1");
        listOfRuleNameDTO.get(0).setTemplate("Template 1");
        listOfRuleNameDTO.get(0).setSqlStr("sqlStr 1");
        listOfRuleNameDTO.get(0).setSqlPart("sqlPart 1");
        listOfRuleNameDTO.add(new RuleNameDTO());
        listOfRuleNameDTO.get(1).setId(1);
        listOfRuleNameDTO.get(1).setName("Name 1");
        listOfRuleNameDTO.get(1).setDescription("Description 1");
        listOfRuleNameDTO.get(1).setJson("json 1");
        listOfRuleNameDTO.get(1).setTemplate("Template 1");
        listOfRuleNameDTO.get(1).setSqlStr("sqlStr 1");
        listOfRuleNameDTO.get(1).setSqlPart("sqlPart 1");
    }

    @Test
    public void givenAListOfRuleName_whenMapToDTO_thenReturnsAListOfRuleNameDTO() {
        // GIVEN
        // WHEN
        List<RuleNameDTO> resultList = ruleNameMapping
                .mapAListOfRuleName(listOfRuleName);
        // THEN
        assertThat(resultList.toString())
                .isEqualTo(listOfRuleNameDTO.toString());

    }

    @Test
    public void givenARuleNameDTO_whenMapToEntity_thenReturnsRuleName() {
        // GIVEN
        // WHEN
        RuleName result = ruleNameMapping
                .mapDTOToEntity(listOfRuleNameDTO.get(0));
        // THEN
        assertThat(result.toString())
                .isEqualTo(listOfRuleName.get(0).toString());

    }

}
