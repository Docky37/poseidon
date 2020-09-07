package com.nnk.springboot.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.mapping.RuleNameMapping;
import com.nnk.springboot.dto.RuleNameDTO;
import com.nnk.springboot.exceptions.RuleNameNotFoundException;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.RuleNameService;
import com.nnk.springboot.services.RuleNameServiceImpl;

@SpringJUnitConfig(value = RuleNameServiceImpl.class)
public class RuleNameServiceTest {

    @Autowired
    RuleNameService ruleNameService;

    @MockBean
    RuleNameRepository ruleNameRepository;

    @MockBean
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
    public void whenFindAll_thenReturnsListOfAllRuleNames() {
        // GIVEN
        given(ruleNameRepository.findAll()).willReturn(listOfRuleName);
        given(ruleNameMapping.mapAListOfRuleName(listOfRuleName))
                .willReturn(listOfRuleNameDTO);
        // WHEN
        List<RuleNameDTO> resultList = ruleNameService.findAll();
        // THEN
        assertThat(resultList.toString())
                .isEqualTo(listOfRuleNameDTO.toString());
    }

    @Test
    public void givenARuleNameDTO_whenSave_thenReturnsSavedObject() {
        // GIVEN
        given(ruleNameMapping.mapDTOToEntity(listOfRuleNameDTO.get(0)))
                .willReturn(listOfRuleName.get(0));
        given(ruleNameRepository.save(listOfRuleName.get(0)))
                .willReturn(listOfRuleName.get(0));
        given(ruleNameMapping.mapEntityToDTO(any(RuleName.class)))
                .willReturn(listOfRuleNameDTO.get(0));
        // WHEN
        RuleNameDTO result = ruleNameService
                .save(listOfRuleNameDTO.get(0));
        // THEN
        assertThat(result.toString())
                .isEqualTo(listOfRuleNameDTO.get(0).toString());
    }

    @Test
    public void whenGetById_thenReturnsExpectedRuleName()
            throws RuleNameNotFoundException {
        // GIVEN
        given(ruleNameRepository.findById(2))
                .willReturn(Optional.of(listOfRuleName.get(1)));
        given(ruleNameMapping.mapEntityToDTO(any(RuleName.class)))
                .willReturn(listOfRuleNameDTO.get(1));
        // WHEN
        RuleNameDTO result = ruleNameService.getById(2);
        // THEN
        System.out.println(result);
        assertThat(result.toString())
                .isEqualTo(listOfRuleNameDTO.get(1).toString());
    }

    @Test
    public void givenAnUnknownId_whenGetById_thenRuleNameNotFoundException()
            throws RuleNameNotFoundException {
        // GIVEN
        // WHEN - THEN
        assertThrows(RuleNameNotFoundException.class, () -> {
            ruleNameService.getById(3);
        });
    }

    @Test
    public void givenARuleNameDTO_whenDelete_thenReturnsDeletedRuleName()
            throws RuleNameNotFoundException {
        // GIVEN
        listOfRuleName.add(new RuleName());
        listOfRuleName.get(2).setId(1);
        listOfRuleName.get(2).setName("Name 1");
        listOfRuleName.get(2).setDescription("Description 1");
        listOfRuleName.get(2).setJson("json 1");
        listOfRuleName.get(2).setTemplate("Template 1");
        listOfRuleName.get(2).setSqlStr("sqlStr 1");
        listOfRuleName.get(2).setSqlPart("sqlPart 1");

        listOfRuleNameDTO.add(new RuleNameDTO());
        listOfRuleNameDTO.get(2).setId(1);
        listOfRuleNameDTO.get(2).setName("Name 1");
        listOfRuleNameDTO.get(2).setDescription("Description 1");
        listOfRuleNameDTO.get(2).setJson("json 1");
        listOfRuleNameDTO.get(2).setTemplate("Template 1");
        listOfRuleNameDTO.get(2).setSqlStr("sqlStr 1");
        listOfRuleNameDTO.get(2).setSqlPart("sqlPart 1");
        given(ruleNameRepository.findById(3))
        .willReturn(Optional.of(listOfRuleName.get(2)));
        given(ruleNameMapping.mapEntityToDTO(any(RuleName.class)))
                .willReturn(listOfRuleNameDTO.get(2));
        // WHEN
        RuleNameDTO result = ruleNameService.delete(3);
        // THEN
        assertThat(result.toString())
                .isEqualTo(listOfRuleNameDTO.get(2).toString());
    }

}
