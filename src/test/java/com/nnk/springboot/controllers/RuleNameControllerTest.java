package com.nnk.springboot.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.nnk.springboot.services.RuleNameService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class RuleNameControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mvc;

    @Mock
    private View view;

    @MockBean
    private RuleNameService ruleNameService;

    RuleNameDTO ruleNameDTO;

    @BeforeEach
    public void setup() {
        ruleNameDTO = new RuleNameDTO(1, "Name 1", "Description 1", "json 1",
                "Template 1", "sqlStr 1", "sqlPart 1");
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test // GET LIST
    public void whenGetListPage_thenDisplayListPage() throws Exception {
        // GIVEN
        List<RuleNameDTO> list = new ArrayList<RuleNameDTO>();
        list.add(ruleNameDTO);
        given(ruleNameService.findAll()).willReturn(list);
        // WHEN
        mvc.perform(MockMvcRequestBuilders.get("/ruleName/list")).andDo(print())
                .andExpect(MockMvcResultMatchers.model().attribute("ruleNames",
                        list))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        // THEN
        verify(ruleNameService).findAll();
    }

    @Test // GET ADD
    public void whenGetAddPage_thenDisplayAddPage() throws Exception {
        // GIVEN

        // WHEN - THEN
        mvc.perform(MockMvcRequestBuilders.get("/ruleName/add")).andDo(print())
                .andExpect(MockMvcResultMatchers.model()
                        .attributeExists("ruleNameDTO"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

    }

    @Test // POST VALIDATE
    public void givenAValidRuleNameDTO_whenPost_thenSaved() throws Exception {
        // GIVEN
        given(ruleNameService.save(any(RuleNameDTO.class)))
                .willReturn(ruleNameDTO);
        // WHEN
        mvc.perform(MockMvcRequestBuilders.post("/ruleName/validate")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .sessionAttr("ruleNameDTO", ruleNameDTO)
                .param("id", ruleNameDTO.getId().toString())
                .param("name", ruleNameDTO.getName())
                .param("description", ruleNameDTO.getDescription())
                .param("json", ruleNameDTO.getJson())
                .param("template", ruleNameDTO.getTemplate())
                .param("sqlStr", ruleNameDTO.getSqlStr())
                .param("sqlPart", ruleNameDTO.getSqlPart())).andDo(print())
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(
                        MockMvcResultMatchers.redirectedUrl("/ruleName/list"))
                .andReturn();
        // THEN
        verify(ruleNameService).save(any(RuleNameDTO.class));
    }

    @Test // GET UPDATE
    public void whenGetUpdatePage_thenDisplayUpdatePage()
            throws Exception, RuleNameNotFoundException {
        // GIVEN
        given(ruleNameService.getById(1)).willReturn(ruleNameDTO);
        // WHEN
        mvc.perform(MockMvcRequestBuilders.get("/ruleName/update/1"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.model()
                        .attributeExists("ruleNameDTO"))
                .andExpect(MockMvcResultMatchers.view().name("ruleName/update"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        // THEN
    }

    @Test // GET UPDATE WITH EXCEPTION
    public void givenAnUnknownId_whenGetUpdatePage_thenRedirectToListPage()
            throws Exception, RuleNameNotFoundException {
        // GIVEN
        given(ruleNameService.getById(7))
                .willThrow(RuleNameNotFoundException.class);
        // WHEN
        mvc.perform(MockMvcRequestBuilders.get("/ruleName/update/7"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.view()
                        .name("redirect:/ruleName/list"))
                .andExpect(MockMvcResultMatchers.status().is(302)).andReturn();
        // THEN
    }

    @Test // POST UPDATE
    public void givenAValidRuleNameDTOToUpdate_whenPost_thenSaved()
            throws Exception {
        // GIVEN
        given(ruleNameService.save(any(RuleNameDTO.class)))
                .willReturn(ruleNameDTO);
        // WHEN
        mvc.perform(MockMvcRequestBuilders.post("/ruleName/update/1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .sessionAttr("ruleNameDTO", ruleNameDTO)
                .param("id", ruleNameDTO.getId().toString())
                .param("name", ruleNameDTO.getName())
                .param("description", ruleNameDTO.getDescription())
                .param("json", ruleNameDTO.getJson())
                .param("template", ruleNameDTO.getTemplate())
                .param("sqlStr", ruleNameDTO.getSqlStr())
                .param("sqlPart", ruleNameDTO.getSqlPart())).andDo(print())
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(
                        MockMvcResultMatchers.redirectedUrl("/ruleName/list"))
                .andReturn();
        // THEN
        verify(ruleNameService).save(any(RuleNameDTO.class));
    }

    @Test // DELETE
    public void givenAValidId_whenDelete_thenDeleteAndRedirectToListPage()
            throws Exception, RuleNameNotFoundException {
        // GIVEN
        given(ruleNameService.delete(1)).willReturn(ruleNameDTO);
        // WHEN
        mvc.perform(MockMvcRequestBuilders.get("/ruleName/delete/1"))
                .andDo(print())
                .andExpect(
                        MockMvcResultMatchers.redirectedUrl("/ruleName/list"))
                .andReturn();
        // THEN
        verify(ruleNameService).delete(1);
    }

    @Test // DELETE WITH EXCEPTION
    public void givenUnknownId_whenDelete_thenRedirectToListPage()
            throws Exception, RuleNameNotFoundException {
        // GIVEN
        given(ruleNameService.delete(7))
                .willThrow(RuleNameNotFoundException.class);
        // WHEN
        mvc.perform(MockMvcRequestBuilders.get("/ruleName/delete/7"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.view()
                        .name("redirect:/ruleName/list"))
                .andExpect(MockMvcResultMatchers.status().is(302)).andReturn();
        // THEN
        verify(ruleNameService).delete(7);
    }

}
