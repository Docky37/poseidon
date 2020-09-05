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
        rating.setMoodysRating(ratingDTO.getMoodysRating());
        rating.setStandPoorsRating(ratingDTO.getStandPoorsRating());
        rating.setFitchRating(ratingDTO.getFitchRating());
        rating.setOrderNumber(ratingDTO.getOrderNumber());

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
        ratingDTO.setMoodysRating(rating.getMoodysRating());
        ratingDTO.setStandPoorsRating(rating.getStandPoorsRating());
        ratingDTO.setFitchRating(rating.getFitchRating());
        ratingDTO.setOrderNumber(rating.getOrderNumber());

        return ratingDTO;
    }

}
