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

import com.nnk.springboot.domain.RatingDTO;
import com.nnk.springboot.exceptions.RatingNotFoundException;
import com.nnk.springboot.services.RatingService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class RatingControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mvc;

    @Mock
    private View view;

    @MockBean
    private RatingService ratingService;

    RatingDTO ratingDTO;

    @BeforeEach
    public void setup() {
        ratingDTO = new RatingDTO(1, "Aa1", "AA+", "AA", 3);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test // GET LIST
    public void whenGetListPage_thenDisplayListPage() throws Exception {
        // GIVEN
        List<RatingDTO> list = new ArrayList<RatingDTO>();
        list.add(ratingDTO);
        given(ratingService.findAll()).willReturn(list);
        // WHEN
        mvc.perform(MockMvcRequestBuilders.get("/rating/list")).andDo(print())
                .andExpect(MockMvcResultMatchers.model().attribute("ratings",
                        list))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        // THEN
        verify(ratingService).findAll();
    }

    @Test // GET ADD
    public void whenGetAddPage_thenDisplayAddPage() throws Exception {
        // GIVEN

        // WHEN
        mvc.perform(MockMvcRequestBuilders.get("/rating/add")).andDo(print())
                .andExpect(MockMvcResultMatchers.model()
                        .attributeExists("ratingDTO"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        // THEN
    }

    @Test // POST VALIDATE
    public void givenAValidRatingDTO_whenPost_thenSaved() throws Exception {
        // GIVEN
        given(ratingService.save(any(RatingDTO.class))).willReturn(ratingDTO);
        // WHEN
        mvc.perform(MockMvcRequestBuilders.post("/rating/validate")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .sessionAttr("ratingDTO", ratingDTO)
                .param("id", ratingDTO.getId().toString())
                .param("moodysRating", ratingDTO.getMoodysRating().toString())
                .param("standPoorsRating",
                        ratingDTO.getStandPoorsRating().toString())
                .param("fitchRating",
                        ratingDTO.getFitchRating().toString())
                .param("orderNumber",
                        ratingDTO.getOrderNumber().toString()))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/rating/list"))
                .andReturn();
        // THEN
        verify(ratingService).save(any(RatingDTO.class));
    }


    @Test // GET UPDATE
    public void whenGetUpdatePage_thenDisplayUpdatePage()
            throws Exception, RatingNotFoundException {
        // GIVEN
        given(ratingService.getById(1)).willReturn(ratingDTO);
        // WHEN
        mvc.perform(MockMvcRequestBuilders.get("/rating/update/1"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.model()
                        .attributeExists("ratingDTO"))
                .andExpect(MockMvcResultMatchers.view().name("rating/update"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        // THEN
    }

    @Test // GET UPDATE WITH EXCEPTION
    public void givenAnUnknownId_whenGetUpdatePage_thenRedirectToListPage()
            throws Exception, RatingNotFoundException {
        // GIVEN
        given(ratingService.getById(7))
                .willThrow(RatingNotFoundException.class);
        // WHEN
        mvc.perform(MockMvcRequestBuilders.get("/rating/update/7"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.view()
                        .name("redirect:/rating/list"))
                .andExpect(MockMvcResultMatchers.status().is(302)).andReturn();
        // THEN
    }

    @Test // POST UPDATE
    public void givenAValidRatingDTOToUpdate_whenPost_thenSaved()
            throws Exception {
        // GIVEN
        given(ratingService.save(any(RatingDTO.class))).willReturn(ratingDTO);
        // WHEN
        mvc.perform(MockMvcRequestBuilders.post("/rating/update/1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .sessionAttr("ratingDTO", ratingDTO)
                .param("id", ratingDTO.getId().toString())
                .param("moodysRating", ratingDTO.getMoodysRating().toString())
                .param("standPoorsRating",
                        ratingDTO.getStandPoorsRating().toString())
                .param("fitchRating",
                        ratingDTO.getFitchRating().toString())
                .param("orderNumber",
                        ratingDTO.getOrderNumber().toString()))
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/rating/list"))
                .andReturn();
        // THEN
        verify(ratingService).save(any(RatingDTO.class));
    }


    @Test // DELETE
    public void givenAValidId_whenDelete_thenDeleteAndRedirectToListPage()
            throws Exception, RatingNotFoundException {
        // GIVEN
        given(ratingService.delete(1)).willReturn(ratingDTO);
        // WHEN
        mvc.perform(MockMvcRequestBuilders.get("/rating/delete/1"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/rating/list"))
                .andReturn();
        // THEN
        verify(ratingService).delete(1);
    }

    @Test // DELETE WITH EXCEPTION
    public void givenUnknownId_whenDelete_thenRedirectToListPage()
            throws Exception, RatingNotFoundException {
        // GIVEN
        given(ratingService.delete(7)).willThrow(RatingNotFoundException.class);
        // WHEN
        mvc.perform(MockMvcRequestBuilders.get("/rating/delete/7"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.view()
                        .name("redirect:/rating/list"))
                .andExpect(MockMvcResultMatchers.status().is(302)).andReturn();
        // THEN
        verify(ratingService).delete(7);
    }

}
