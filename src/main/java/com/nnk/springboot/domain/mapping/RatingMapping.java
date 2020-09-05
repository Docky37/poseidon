package com.nnk.springboot.domain.mapping;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RatingDTO;

/**
 * This class is used to perform bidirectional mapping between a Rating entity
 * and a RatingDTO.
 *
 * @author Thierry Schreiner
 */
@Component
public class RatingMapping {

    /**
     * This method is in charge of the mapping of a list of Rating entities to a
     * list of RatingDTO. Use the mapEntityToDTO(Rating rating)as a sub method.
     *
     * @param listRating
     * @return a List<RatingDTO> object
     */
    public List<RatingDTO> mapAListOfRating(List<Rating> listOfRating) {
        List<RatingDTO> listRatingDTO = new ArrayList<>();
        for (Rating rating : listOfRating) {
            RatingDTO ratingDTO = mapEntityToDTO(rating);
            listRatingDTO.add(ratingDTO);
        }
        return listRatingDTO;

    }

    /**
     * This method is in charge of the mapping of a RatingDTO to a Rating
     * entity.
     * 
     * @param ratingDTO
     * @return
     */
    public Rating mapDTOToEntity(RatingDTO ratingDTO) {
        final Rating rating = new Rating();
        rating.setId(ratingDTO.getId());
        rating.setMoodys_rating(ratingDTO.getMoodys_rating());
        rating.setSand_p_rating(ratingDTO.getSand_p_rating());
        rating.setFitch_rating(ratingDTO.getFitch_rating());
        rating.setOrder_number(ratingDTO.getOrder_number());

        return rating;
    }

    /**
     * This method is in charge of the mapping of a Rating entity to a
     * RatingDTO.
     *
     * @param rating
     * @return
     */
    public RatingDTO mapEntityToDTO(final Rating rating) {
        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setId(rating.getId());
        ratingDTO.setMoodys_rating(rating.getMoodys_rating());
        ratingDTO.setSand_p_rating(rating.getSand_p_rating());
        ratingDTO.setFitch_rating(rating.getFitch_rating());
        ratingDTO.setOrder_number(rating.getOrder_number());

        return ratingDTO;
    }

}
