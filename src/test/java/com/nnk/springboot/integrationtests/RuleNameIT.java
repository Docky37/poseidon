package com.nnk.springboot.integrationtests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.View;

import com.nnk.springboot.dto.RuleNameDTO;
import com.nnk.springboot.exceptions.RuleNameNotFoundException;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.RuleNameService;
import com.nnk.springboot.util.UserRetrieve;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class RuleNameIT {

    static final Logger LOGGER = LoggerFactory.getLogger(RuleNameIT.class);

    @LocalServerPort
    private int port;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mvc;

    @Mock
    private View view;

    @MockBean
    private UserRetrieve userRetrieve;

    @Autowired
    private RuleNameService ruleNameService;

    @Autowired
    private RuleNameRepository ruleNameRepository;

    RuleNameDTO ruleNameDTO;
    static List<RuleNameDTO> listOfRuleNameDTO = new ArrayList<>();
    static {
        listOfRuleNameDTO.add(new RuleNameDTO());
        listOfRuleNameDTO.get(0).setId(1);
        listOfRuleNameDTO.get(0).setName("Name 1");
        listOfRuleNameDTO.get(0).setDescription("Description 1");
        listOfRuleNameDTO.get(0).setJson("json 1");
        listOfRuleNameDTO.get(0).setTemplate("Template 1");
        listOfRuleNameDTO.get(0).setSqlStr("sqlStr 1");
        listOfRuleNameDTO.get(0).setSqlPart("sqlPart 1");
        listOfRuleNameDTO.add(new RuleNameDTO());
        listOfRuleNameDTO.get(1).setId(2);
        listOfRuleNameDTO.get(1).setName("Name 2");
        listOfRuleNameDTO.get(1).setDescription("Description 2");
        listOfRuleNameDTO.get(1).setJson("json 2");
        listOfRuleNameDTO.get(1).setTemplate("Template 2");
        listOfRuleNameDTO.get(1).setSqlStr("sqlStr 2");
        listOfRuleNameDTO.get(1).setSqlPart("sqlPart 2");
    }

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        ruleNameDTO = listOfRuleNameDTO.get(0);
    }

    @AfterEach
    public void tearDrop() {
        ruleNameRepository.truncate();
    }

    @Test // GET List
    public void givenExistingRuleNameDTO_whenFindAll_thenListed()
            throws Exception {
        // GIVEN
        given(userRetrieve.getLoggedUser()).willReturn("Testeur");
        // WHEN
        ruleNameService.save(listOfRuleNameDTO.get(0));
        ruleNameService.save(listOfRuleNameDTO.get(1));
        List<RuleNameDTO> resultList = ruleNameService.findAll();
        // THEN
        assertThat(resultList.size()).isEqualTo(2);
        assertThat(resultList.get(0).getName())
                .isEqualTo(listOfRuleNameDTO.get(0).getName());
        assertThat(resultList.get(1).getName())
                .isEqualTo(listOfRuleNameDTO.get(1).getName());
    }

    @Test // POST VALIDATE
    public void givenAValidNewRuleNameDTO_whenPost_thenSaved()
            throws Exception {
        // GIVEN
        given(userRetrieve.getLoggedUser()).willReturn("Testeur");
        RuleNameDTO existingRuleNameDTO = ruleNameService
                .save(listOfRuleNameDTO.get(0));
        // WHEN
        mvc.perform(MockMvcRequestBuilders.post("/ruleName/validate")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .sessionAttr("ruleNameDTO", existingRuleNameDTO)
                .param("id", existingRuleNameDTO.getId().toString())
                .param("name", existingRuleNameDTO.getName())
                .param("description", existingRuleNameDTO.getDescription())
                .param("json", existingRuleNameDTO.getJson())
                .param("template", existingRuleNameDTO.getTemplate())
                .param("sqlStr", existingRuleNameDTO.getSqlStr())
                .param("sqlPart", existingRuleNameDTO.getSqlPart()))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(
                        MockMvcResultMatchers.redirectedUrl("/ruleName/list"))
                .andReturn();
        RuleNameDTO ruleNameDTOResult = null;
        try {
            ruleNameDTOResult = ruleNameService
                    .getById(existingRuleNameDTO.getId());
        } catch (RuleNameNotFoundException e) {
            e.printStackTrace();
        }
        // THEN
        assertThat(ruleNameDTOResult.toString())
                .isEqualTo(existingRuleNameDTO.toString());
    }

    @Test // POST UPDATE
    public void givenAValidRuleNameDTOToUpdate_whenPost_thenSaved()
            throws Exception {
        // GIVEN
        given(userRetrieve.getLoggedUser()).willReturn("Testeur");
        RuleNameDTO existingRuleNameDTO = ruleNameService
                .save(listOfRuleNameDTO.get(0));
        // WHEN
        mvc.perform(MockMvcRequestBuilders
                .post("/ruleName/update/" + existingRuleNameDTO.getId())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .sessionAttr("ruleNameDTO", existingRuleNameDTO)
                .param("id", existingRuleNameDTO.getId().toString())
                .param("name", existingRuleNameDTO.getName())
                .param("description", "Updated Description")
                .param("json", existingRuleNameDTO.getJson())
                .param("template", existingRuleNameDTO.getTemplate())
                .param("sqlStr", existingRuleNameDTO.getSqlStr())
                .param("sqlPart", existingRuleNameDTO.getSqlPart()))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(
                        MockMvcResultMatchers.redirectedUrl("/ruleName/list"))
                .andReturn();
        RuleNameDTO ruleNameDTOResult = null;
        try {
            ruleNameDTOResult = ruleNameService
                    .getById(existingRuleNameDTO.getId());
        } catch (RuleNameNotFoundException e) {
            e.printStackTrace();
        }
        // THEN
        assertThat(ruleNameDTOResult.getName())
                .isEqualTo(ruleNameDTO.getName());
        assertThat(ruleNameDTOResult.getJson())
                .isEqualTo(ruleNameDTO.getJson());
        assertThat(ruleNameDTOResult.getSqlStr())
                .isEqualTo(ruleNameDTO.getSqlStr());
        assertThat(ruleNameDTOResult.getDescription())
                .isEqualTo("Updated Description");
    }

    @Test // DELETE
    public void givenAValidId_whenDelete_thenDeleted()
            throws Exception, RuleNameNotFoundException {
        // GIVEN
        ruleNameDTO = listOfRuleNameDTO.get(0);
        given(userRetrieve.getLoggedUser()).willReturn("Testeur");
        RuleNameDTO existingRuleNameDTO = ruleNameService
                .save(listOfRuleNameDTO.get(0));
        // WHEN
        RuleNameDTO deletedRuleNameDTO = null;
        try {
            deletedRuleNameDTO = ruleNameService
                    .delete(existingRuleNameDTO.getId());
        } catch (RuleNameNotFoundException e) {
            LOGGER.error(" => No RuleName record exist for id={}!",
                    existingRuleNameDTO.getId());
        }

        // THEN
        assertThat(deletedRuleNameDTO.toString())
                .isEqualTo(existingRuleNameDTO.toString());
        assertThrows(RuleNameNotFoundException.class, () -> {
            ruleNameService.getById(1);
        });
    }

}
