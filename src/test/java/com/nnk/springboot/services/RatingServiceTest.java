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

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RatingDTO;
import com.nnk.springboot.domain.mapping.RatingMapping;
import com.nnk.springboot.exceptions.RatingNotFoundException;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.RatingService;
import com.nnk.springboot.services.RatingServiceImpl;

@SpringJUnitConfig(value = RatingServiceImpl.class)
public class RatingServiceTest {

    @Autowired
    RatingService ratingService;

    @MockBean
    RatingRepository ratingRepository;

    @MockBean
    RatingMapping ratingMapping;

    static List<Rating> listOfRating = new ArrayList<>();
    static List<RatingDTO> listOfRatingDTO = new ArrayList<>();
    static {
        listOfRating.add(new Rating());
        listOfRating.get(0).setId(1);
        listOfRating.get(0).setMoodys_rating("Aa1");
        listOfRating.get(0).setSand_p_rating("AA+");
        listOfRating.get(0).setFitch_rating("AA");
        listOfRating.get(0).setOrder_number(3);
        listOfRating.add(new Rating());
        listOfRating.get(1).setId(2);
        listOfRating.get(1).setMoodys_rating("Baa1");
        listOfRating.get(1).setSand_p_rating("BBB+");
        listOfRating.get(1).setFitch_rating("BBB+");
        listOfRating.get(1).setOrder_number(5);

        listOfRatingDTO.add(new RatingDTO());
        listOfRatingDTO.get(0).setId(1);
        listOfRatingDTO.get(0).setMoodys_rating("Aa1");
        listOfRatingDTO.get(0).setSand_p_rating("AA+");
        listOfRatingDTO.get(0).setFitch_rating("AA");
        listOfRatingDTO.get(0).setOrder_number(3);
        listOfRatingDTO.add(new RatingDTO());
        listOfRatingDTO.get(1).setId(2);
        listOfRatingDTO.get(1).setMoodys_rating("Baa1");
        listOfRatingDTO.get(1).setSand_p_rating("BBB+");
        listOfRatingDTO.get(1).setFitch_rating("BBB+");
        listOfRatingDTO.get(1).setOrder_number(5);
    }

    @Test
    public void whenFindAll_thenReturnsListOfAllRatings() {
        // GIVEN
        given(ratingRepository.findAll()).willReturn(listOfRating);
        given(ratingMapping.mapAListOfRating(listOfRating))
                .willReturn(listOfRatingDTO);
        // WHEN
        List<RatingDTO> resultList = ratingService.findAll();
        // THEN
        assertThat(resultList.toString())
                .isEqualTo(listOfRatingDTO.toString());
    }

    @Test
    public void givenARatingDTO_whenSave_thenReturnsSavedObject() {
        // GIVEN
        given(ratingMapping.mapDTOToEntity(listOfRatingDTO.get(0)))
                .willReturn(listOfRating.get(0));
        given(ratingRepository.save(listOfRating.get(0)))
                .willReturn(listOfRating.get(0));
        given(ratingMapping.mapEntityToDTO(any(Rating.class)))
                .willReturn(listOfRatingDTO.get(0));
        // WHEN
        RatingDTO result = ratingService
                .save(listOfRatingDTO.get(0));
        // THEN
        assertThat(result.toString())
                .isEqualTo(listOfRatingDTO.get(0).toString());
    }

    @Test
    public void whenGetById_thenReturnsExpectedRating()
            throws RatingNotFoundException {
        // GIVEN
        given(ratingRepository.findById(2))
                .willReturn(Optional.of(listOfRating.get(1)));
        given(ratingMapping.mapEntityToDTO(any(Rating.class)))
                .willReturn(listOfRatingDTO.get(1));
        // WHEN
        RatingDTO result = ratingService.getById(2);
        // THEN
        System.out.println(result);
        assertThat(result.toString())
                .isEqualTo(listOfRatingDTO.get(1).toString());
    }

    @Test
    public void givenAnUnknownId_whenGetById_thenRatingNotFoundException()
            throws RatingNotFoundException {
        // GIVEN
        // WHEN - THEN
        assertThrows(RatingNotFoundException.class, () -> {
            ratingService.getById(3);
        });
    }

    @Test
    public void givenARatingDTO_whenDelete_thenReturnsDeletedRating()
            throws RatingNotFoundException {
        // GIVEN
        listOfRatingDTO.add(new RatingDTO());
        listOfRatingDTO.get(2).setId(3);
        listOfRatingDTO.get(2).setMoodys_rating("Aa1");
        listOfRatingDTO.get(2).setSand_p_rating("AA+");
        listOfRatingDTO.get(2).setFitch_rating("AA");
        listOfRatingDTO.get(2).setOrder_number(7);
        listOfRating.add(new Rating());
        listOfRating.get(2).setId(1);
        listOfRating.get(2).setMoodys_rating("Aa1");
        listOfRating.get(2).setSand_p_rating("AA+");
        listOfRating.get(2).setFitch_rating("AA");
        listOfRating.get(2).setOrder_number(7);
        given(ratingRepository.findById(3))
        .willReturn(Optional.of(listOfRating.get(2)));
        given(ratingMapping.mapEntityToDTO(any(Rating.class)))
                .willReturn(listOfRatingDTO.get(2));
        // WHEN
        RatingDTO result = ratingService.delete(3);
        // THEN
        assertThat(result.toString())
                .isEqualTo(listOfRatingDTO.get(2).toString());
    }

}
