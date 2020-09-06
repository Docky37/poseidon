package com.nnk.springboot.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

import com.nnk.springboot.dto.CurvePointDTO;
import com.nnk.springboot.exceptions.CurvePointNotFoundException;
import com.nnk.springboot.services.CurvePointService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CurvePointControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mvc;

    @Mock
    private View view;

    @MockBean
    private CurvePointService curvePointService;

    CurvePointDTO curvePointDTO;

    @BeforeEach
    public void setup() {
        curvePointDTO = new CurvePointDTO(1, 1, LocalDateTime.now(),
                new BigDecimal("100"), new BigDecimal("101"), null);

        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test // GET LIST
    public void whenGetListPage_thenDisplayListPage() throws Exception {
        // GIVEN
        List<CurvePointDTO> list = new ArrayList<CurvePointDTO>();
        list.add(curvePointDTO);
        given(curvePointService.findAll()).willReturn(list);
        // WHEN
        mvc.perform(MockMvcRequestBuilders.get("/curvePoint/list")).andDo(print())
                .andExpect(MockMvcResultMatchers.model().attribute("curvePoints",
                        list))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        // THEN
        verify(curvePointService).findAll();
    }

    @Test // GET ADD
    public void whenGetAddPage_thenDisplayAddPage() throws Exception {
        // GIVEN

        // WHEN
        mvc.perform(MockMvcRequestBuilders.get("/curvePoint/add")).andDo(print())
                .andExpect(MockMvcResultMatchers.model()
                        .attributeExists("curvePointDTO"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        // THEN
    }

    @Test // POST VALIDATE
    public void givenAValidCurvePointDTO_whenPost_thenSaved() throws Exception {
        // GIVEN
        given(curvePointService.save(any(CurvePointDTO.class)))
                .willReturn(curvePointDTO);
        // WHEN
        mvc.perform(MockMvcRequestBuilders.post("/curvePoint/validate")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .sessionAttr("curvePointDTO", curvePointDTO)
                .param("curvePointId", curvePointDTO.getCurvePointId().toString())
                .param("curveId", curvePointDTO.getCurveId().toString())
                //.param("asOfDate", curvePointDTO.getAsOfDate().toString())
                .param("term", curvePointDTO.getTerm().toString())
                .param("value", curvePointDTO.getValue().toString()))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/curvePoint/list"))
                .andReturn();
        // THEN
        verify(curvePointService).save(any(CurvePointDTO.class));
    }

    @Test // POST VALIDATE WITH ERROR
    public void givenANonValidNewCurvePointDTO_whenPost_thenTryAgain()
            throws Exception {
        // GIVEN
        curvePointDTO.setCurveId(null);;
        // WHEN
        mvc.perform(MockMvcRequestBuilders.post("/curvePoint/validate")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .sessionAttr("curvePointDTO", curvePointDTO)
                .param("curvePointId", curvePointDTO.getCurvePointId().toString())
                .param("curveId", (String) null)
                //.param("asOfDate", curvePointDTO.getAsOfDate().toString())
                .param("term", curvePointDTO.getTerm().toString())
                .param("value", curvePointDTO.getValue().toString()))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.model().hasErrors())
                .andExpect(MockMvcResultMatchers.view().name("curvePoint/add"))
                .andReturn();
        // THEN
        verify(curvePointService, never()).save(any(CurvePointDTO.class));
    }

    @Test // GET UPDATE
    public void whenGetUpdatePage_thenDisplayUpdatePage()
            throws Exception, CurvePointNotFoundException {
        // GIVEN
        given(curvePointService.getById(1)).willReturn(curvePointDTO);
        // WHEN
        mvc.perform(MockMvcRequestBuilders.get("/curvePoint/update/1"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.model()
                        .attributeExists("curvePointDTO"))
                .andExpect(MockMvcResultMatchers.view().name("curvePoint/update"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        // THEN
    }

    @Test // GET UPDATE WITH EXCEPTION
    public void givenAnUnknownId_whenGetUpdatePage_thenRedirectToListPage()
            throws Exception, CurvePointNotFoundException {
        // GIVEN
        given(curvePointService.getById(7))
                .willThrow(CurvePointNotFoundException.class);
        // WHEN
        mvc.perform(MockMvcRequestBuilders.get("/curvePoint/update/7"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.view()
                        .name("redirect:/curvePoint/list"))
                .andExpect(MockMvcResultMatchers.status().is(302)).andReturn();
        // THEN
    }

    @Test // POST UPDATE
    public void givenAValidCurvePointDTOToUpdate_whenPost_thenSaved()
            throws Exception {
        // GIVEN
        given(curvePointService.save(any(CurvePointDTO.class)))
                .willReturn(curvePointDTO);
        // WHEN
        mvc.perform(MockMvcRequestBuilders.post("/curvePoint/update/1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .sessionAttr("curvePointDTO", curvePointDTO)
                .param("curvePointId", curvePointDTO.getCurvePointId().toString())
                .param("curveId", curvePointDTO.getCurveId().toString())
                //.param("asOfDate", curvePointDTO.getAsOfDate().toString())
                .param("term", curvePointDTO.getTerm().toString())
                .param("value", curvePointDTO.getValue().toString()))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/curvePoint/list"))
                .andReturn();
        // THEN
        verify(curvePointService).save(any(CurvePointDTO.class));
    }

    @Test // POST UPDATE WITH ERROR
    public void givenANonValidCurvePointDTOToUpdate_whenPost_thenTryAgain()
            throws Exception {
        // GIVEN
        curvePointDTO.setCurveId(null);
        // WHEN
        mvc.perform(MockMvcRequestBuilders.post("/curvePoint/update/1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .sessionAttr("curvePointDTO", curvePointDTO)
                .param("curvePointId", curvePointDTO.getCurvePointId().toString())
                .param("curveId", (String) null)
                .param("asOfDate", curvePointDTO.getAsOfDate().toString())
                .param("term", curvePointDTO.getTerm().toString())
                .param("value", curvePointDTO.getValue().toString()))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.model().hasErrors())
                .andExpect(MockMvcResultMatchers.view().name("curvePoint/update"))
                .andReturn();
        // THEN
        verify(curvePointService, never()).save(any(CurvePointDTO.class));
    }


    @Test // DELETE
    public void givenAValidId_whenDelete_thenDeleteAndRedirectToListPage()
            throws Exception, CurvePointNotFoundException {
        // GIVEN
        given(curvePointService.delete(1)).willReturn(curvePointDTO);
        // WHEN
        mvc.perform(MockMvcRequestBuilders.get("/curvePoint/delete/1"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/curvePoint/list"))
                .andReturn();
        // THEN
        verify(curvePointService).delete(1);
    }

    @Test // DELETE WITH EXCEPTION
    public void givenUnknownId_whenDelete_thenRedirectToListPage()
            throws Exception, CurvePointNotFoundException {
        // GIVEN
        given(curvePointService.delete(7))
                .willThrow(CurvePointNotFoundException.class);
        // WHEN
        mvc.perform(MockMvcRequestBuilders.get("/curvePoint/delete/7"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.view()
                        .name("redirect:/curvePoint/list"))
                .andExpect(MockMvcResultMatchers.status().is(302)).andReturn();
        // THEN
        verify(curvePointService).delete(7);
    }

}
