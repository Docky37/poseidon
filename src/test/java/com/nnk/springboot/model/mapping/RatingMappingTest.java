package com.nnk.springboot.model.mapping;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RatingDTO;
import com.nnk.springboot.domain.mapping.RatingMapping;

@SpringJUnitConfig(value = RatingMapping.class)
public class RatingMappingTest {

    @Autowired
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
