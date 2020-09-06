package com.nnk.springboot.model.mapping;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.mapping.RatingMapping;
import com.nnk.springboot.dto.RatingDTO;

@SpringJUnitConfig(value = RatingMapping.class)
public class RatingMappingTest {

    @Autowired
    RatingMapping ratingMapping;

    static List<Rating> listOfRating = new ArrayList<>();
    static List<RatingDTO> listOfRatingDTO = new ArrayList<>();
    static {
        listOfRating.add(new Rating());
        listOfRating.get(0).setId(1);
        listOfRating.get(0).setMoodysRating("Aa1");
        listOfRating.get(0).setStandPoorsRating("AA+");
        listOfRating.get(0).setFitchRating("AA");
        listOfRating.get(0).setOrderNumber(3);
        listOfRating.add(new Rating());
        listOfRating.get(1).setId(2);
        listOfRating.get(1).setMoodysRating("Baa1");
        listOfRating.get(1).setStandPoorsRating("BBB+");
        listOfRating.get(1).setFitchRating("BBB+");
        listOfRating.get(1).setOrderNumber(5);

        listOfRatingDTO.add(new RatingDTO());
        listOfRatingDTO.get(0).setId(1);
        listOfRatingDTO.get(0).setMoodysRating("Aa1");
        listOfRatingDTO.get(0).setStandPoorsRating("AA+");
        listOfRatingDTO.get(0).setFitchRating("AA");
        listOfRatingDTO.get(0).setOrderNumber(3);
        listOfRatingDTO.add(new RatingDTO());
        listOfRatingDTO.get(1).setId(2);
        listOfRatingDTO.get(1).setMoodysRating("Baa1");
        listOfRatingDTO.get(1).setStandPoorsRating("BBB+");
        listOfRatingDTO.get(1).setFitchRating("BBB+");
        listOfRatingDTO.get(1).setOrderNumber(5);
    }

    @Test
    public void givenAListOfRating_whenMapToDTO_thenReturnsAListOfRatingDTO() {
        // GIVEN
        // WHEN
        List<RatingDTO> resultList = ratingMapping
                .mapAListOfRating(listOfRating);
        // THEN
        assertThat(resultList.toString())
                .isEqualTo(listOfRatingDTO.toString());

    }

    @Test
    public void givenARatingDTO_whenMapToEntity_thenReturnsRating() {
        // GIVEN
        // WHEN
        Rating result = ratingMapping
                .mapDTOToEntity(listOfRatingDTO.get(0));
        // THEN
        assertThat(result.toString())
                .isEqualTo(listOfRating.get(0).toString());

    }

}
