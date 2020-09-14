package com.nnk.springboot.integrationtests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

import com.nnk.springboot.dto.CurvePointDTO;
import com.nnk.springboot.exceptions.CurvePointNotFoundException;
import com.nnk.springboot.services.CurvePointService;
import com.nnk.springboot.util.UserRetrieve;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class CurvePointIT {

    static final Logger LOGGER = LoggerFactory.getLogger(CurvePointIT.class);

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
    private CurvePointService curvePointService;


    CurvePointDTO curvePointDTO;
    static List<CurvePointDTO> listOfCurvePointDTO = new ArrayList<>();
    static {
        listOfCurvePointDTO.add(new CurvePointDTO());
        listOfCurvePointDTO.get(0).setCurvePointId(1);
        listOfCurvePointDTO.get(0).setCurveId(7);
        listOfCurvePointDTO.get(0).setTerm(new BigDecimal("150.0050"));
        listOfCurvePointDTO.get(0).setValue(new BigDecimal("140.0000"));
        listOfCurvePointDTO.add(new CurvePointDTO());
        listOfCurvePointDTO.get(1).setCurvePointId(2);
        listOfCurvePointDTO.get(1).setCurveId(8);
        listOfCurvePointDTO.get(1).setTerm(new BigDecimal("100.5000"));
        listOfCurvePointDTO.get(1).setValue(new BigDecimal("101.0250"));
        listOfCurvePointDTO.get(1).setCreationDate(LocalDateTime.now());
    }

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        curvePointDTO = listOfCurvePointDTO.get(0);
    }

    @Test // GET List
    public void givenExistingCurvePointDTO_whenFindAll_thenListed()
            throws Exception {
        // GIVEN
        curvePointDTO = listOfCurvePointDTO.get(0);
        given(userRetrieve.getLoggedUser()).willReturn("Testeur");
        // WHEN
        curvePointService.save(listOfCurvePointDTO.get(0));
        curvePointService.save(listOfCurvePointDTO.get(1));
        List<CurvePointDTO> resultList = curvePointService.findAll();
        // THEN
        resultList.get(0)
                .setCreationDate(listOfCurvePointDTO.get(0).getCreationDate());
        resultList.get(1)
                .setCreationDate(listOfCurvePointDTO.get(1).getCreationDate());
        assertThat(resultList.toString())
                .isEqualTo(listOfCurvePointDTO.toString());
    }

    @Test // POST VALIDATE
    public void givenAValidNewCurvePointDTO_whenPost_thenSaved() throws Exception {
        // GIVEN
        given(userRetrieve.getLoggedUser()).willReturn("Testeur");
        // WHEN
        mvc.perform(MockMvcRequestBuilders.post("/curvePoint/validate")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .sessionAttr("curvePointDTO", curvePointDTO)
                .param("curvePointId",
                        curvePointDTO.getCurvePointId().toString())
                .param("curveId", curvePointDTO.getCurveId().toString())
                .param("term", curvePointDTO.getTerm().toString())
                .param("value", curvePointDTO.getValue().toString()))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(
                        MockMvcResultMatchers.redirectedUrl("/curvePoint/list"))
                .andReturn();
        CurvePointDTO curvePointDTOResult = null;
        try {
            curvePointDTOResult = curvePointService.getById(1);
        } catch (CurvePointNotFoundException e) {
            e.printStackTrace();
        }
        // THEN
        assertThat(curvePointDTOResult.getCurveId())
                .isEqualTo(curvePointDTO.getCurveId());
        assertThat(curvePointDTOResult.getTerm())
                .isEqualTo(curvePointDTO.getTerm());
        assertThat(curvePointDTOResult.getValue())
                .isEqualTo(curvePointDTO.getValue());
    }

    @Test // POST UPDATE
    public void givenAValidCurvePointDTOToUpdate_whenPost_thenSaved()
            throws Exception {
        // GIVEN
        curvePointDTO = listOfCurvePointDTO.get(0);
        given(userRetrieve.getLoggedUser()).willReturn("Testeur");
        // WHEN
        mvc.perform(MockMvcRequestBuilders.post("/curvePoint/update/1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .sessionAttr("curvePointDTO", curvePointDTO)
                .param("curvePointId",
                        curvePointDTO.getCurvePointId().toString())
                .param("curvePointId",
                        curvePointDTO.getCurvePointId().toString())
                .param("curveId", curvePointDTO.getCurveId().toString())
                .param("term", curvePointDTO.getTerm().toString())
                .param("value", curvePointDTO.getValue().toString()))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(
                        MockMvcResultMatchers.redirectedUrl("/curvePoint/list"))
                .andReturn();
        CurvePointDTO curvePointDTOResult = null;
        try {
            curvePointDTOResult = curvePointService.getById(1);
        } catch (CurvePointNotFoundException e) {
            e.printStackTrace();
        }
        // THEN
        assertThat(curvePointDTOResult.getCurveId())
                .isEqualTo(curvePointDTO.getCurveId());
        assertThat(curvePointDTOResult.getTerm())
                .isEqualTo(curvePointDTO.getTerm());
        assertThat(curvePointDTOResult.getValue())
                .isEqualTo(curvePointDTO.getValue());
    }

    @Test // DELETE
    public void givenAValidId_whenDelete_thenDeleted()
            throws Exception, CurvePointNotFoundException {
        // GIVEN
        curvePointDTO = listOfCurvePointDTO.get(0);
        given(userRetrieve.getLoggedUser()).willReturn("Testeur");
        curvePointService.save(listOfCurvePointDTO.get(0));
        // WHEN
        CurvePointDTO deletedCurvePointDTO = null;
        try {
            deletedCurvePointDTO = curvePointService.delete(1);
        } catch (CurvePointNotFoundException e) {
            LOGGER.error(" => No CurvePoint record exist for id={}!", 1);
        }

        // THEN
        listOfCurvePointDTO.get(0)
                .setCreationDate(deletedCurvePointDTO.getCreationDate());
        assertThat(deletedCurvePointDTO.toString())
                .isEqualTo(listOfCurvePointDTO.get(0).toString());
        assertThrows(CurvePointNotFoundException.class, () -> {
            curvePointService.getById(1);
        });
    }

}
