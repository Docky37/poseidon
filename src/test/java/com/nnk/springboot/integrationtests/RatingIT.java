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

import com.nnk.springboot.dto.RatingDTO;
import com.nnk.springboot.exceptions.RatingNotFoundException;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.security.util.UserRetrieve;
import com.nnk.springboot.services.RatingService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class RatingIT {

    static final Logger LOGGER = LoggerFactory.getLogger(RatingIT.class);

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
    private RatingService ratingService;

    @Autowired
    private RatingRepository ratingRepository;

    RatingDTO ratingDTO;
    static List<RatingDTO> listOfRatingDTO = new ArrayList<>();
    static {
        listOfRatingDTO.add(new RatingDTO());
        listOfRatingDTO.get(0).setId(1);
        listOfRatingDTO.get(0).setMoodysRating("moodysRating 1");
        listOfRatingDTO.get(0).setStandPoorsRating("standPoorsRating 1");
        listOfRatingDTO.get(0).setFitchRating("fitchRating 1");
        listOfRatingDTO.get(0).setOrderNumber(1);
        listOfRatingDTO.add(new RatingDTO());
        listOfRatingDTO.get(1).setId(2);
        listOfRatingDTO.add(new RatingDTO());
        listOfRatingDTO.get(1).setId(2);
        listOfRatingDTO.get(1).setMoodysRating("moodysRating 2");
        listOfRatingDTO.get(1).setStandPoorsRating("standPoorsRating 2");
        listOfRatingDTO.get(1).setFitchRating("fitchRating 2");
        listOfRatingDTO.get(1).setOrderNumber(2);
    }

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        ratingDTO = listOfRatingDTO.get(0);
    }

    @AfterEach
    public void tearDrop() {
        ratingRepository.truncate();
    }

    @Test // GET List
    public void givenExistingRatingDTO_whenFindAll_thenListed()
            throws Exception {
        // GIVEN
        given(userRetrieve.getLoggedUser()).willReturn("Testeur");
        // WHEN
        ratingService.save(listOfRatingDTO.get(0));
        ratingService.save(listOfRatingDTO.get(1));
        List<RatingDTO> resultList = ratingService.findAll();
        // THEN
        assertThat(resultList.size()).isEqualTo(2);
        assertThat(resultList.get(0).getMoodysRating())
                .isEqualTo(listOfRatingDTO.get(0).getMoodysRating());
        assertThat(resultList.get(1).getOrderNumber())
                .isEqualTo(listOfRatingDTO.get(1).getOrderNumber());
    }

    @Test // POST VALIDATE
    public void givenAValidNewRatingDTO_whenPost_thenSaved()
            throws Exception {
        // GIVEN
        given(userRetrieve.getLoggedUser()).willReturn("Testeur");
        RatingDTO existingRatingDTO = ratingService
                .save(listOfRatingDTO.get(0));
        // WHEN
        mvc.perform(MockMvcRequestBuilders.post("/rating/validate")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .sessionAttr("ratingDTO", existingRatingDTO)
                .param("id", existingRatingDTO.getId().toString())
                .param("moodysRating", existingRatingDTO.getMoodysRating())
                .param("standPoorsRating", existingRatingDTO.getStandPoorsRating())
                .param("fitchRating", existingRatingDTO.getFitchRating())
                .param("orderNumber", existingRatingDTO.getOrderNumber().toString()))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(
                        MockMvcResultMatchers.redirectedUrl("/rating/list"))
                .andReturn();
        RatingDTO ratingDTOResult = null;
        try {
            ratingDTOResult = ratingService
                    .getById(existingRatingDTO.getId());
        } catch (RatingNotFoundException e) {
            e.printStackTrace();
        }
        // THEN
        assertThat(ratingDTOResult.toString())
                .isEqualTo(existingRatingDTO.toString());
    }

    @Test // POST UPDATE
    public void givenAValidRatingDTOToUpdate_whenPost_thenSaved()
            throws Exception {
        // GIVEN
        given(userRetrieve.getLoggedUser()).willReturn("Testeur");
        RatingDTO existingRatingDTO = ratingService
                .save(listOfRatingDTO.get(0));
        // WHEN
        mvc.perform(MockMvcRequestBuilders
                .post("/rating/update/" + existingRatingDTO.getId())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .sessionAttr("ratingDTO", existingRatingDTO)
                .param("id", existingRatingDTO.getId().toString())
                .param("moodysRating", existingRatingDTO.getMoodysRating())
                .param("standPoorsRating", "Updated StandPoorsRating")
                .param("fitchRating", existingRatingDTO.getFitchRating())
                .param("orderNumber", existingRatingDTO.getOrderNumber().toString()))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(
                        MockMvcResultMatchers.redirectedUrl("/rating/list"))
                .andReturn();
        RatingDTO ratingDTOResult = null;
        try {
            ratingDTOResult = ratingService
                    .getById(existingRatingDTO.getId());
        } catch (RatingNotFoundException e) {
            e.printStackTrace();
        }
        // THEN
        assertThat(ratingDTOResult.getMoodysRating())
                .isEqualTo(ratingDTO.getMoodysRating());
        assertThat(ratingDTOResult.getStandPoorsRating())
                .isEqualTo("Updated StandPoorsRating");
        assertThat(ratingDTOResult.getFitchRating())
                .isEqualTo(ratingDTO.getFitchRating());
        assertThat(ratingDTOResult.getOrderNumber())
                .isEqualTo(ratingDTO.getOrderNumber());
    }

    @Test // DELETE
    public void givenAValidId_whenDelete_thenDeleted()
            throws Exception, RatingNotFoundException {
        // GIVEN
        ratingDTO = listOfRatingDTO.get(0);
        given(userRetrieve.getLoggedUser()).willReturn("Testeur");
        RatingDTO existingRatingDTO = ratingService
                .save(listOfRatingDTO.get(0));
        // WHEN
        RatingDTO deletedRatingDTO = null;
        try {
            deletedRatingDTO = ratingService
                    .delete(existingRatingDTO.getId());
        } catch (RatingNotFoundException e) {
            LOGGER.error(" => No Rating record exist for id={}!",
                    existingRatingDTO.getId());
        }

        // THEN
        assertThat(deletedRatingDTO.toString())
                .isEqualTo(existingRatingDTO.toString());
        assertThrows(RatingNotFoundException.class, () -> {
            ratingService.getById(1);
        });
    }

}
