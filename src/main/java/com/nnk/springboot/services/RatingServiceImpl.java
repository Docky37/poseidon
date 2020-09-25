package com.nnk.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.mapping.RatingMapping;
import com.nnk.springboot.dto.RatingDTO;
import com.nnk.springboot.exceptions.RatingNotFoundException;
import com.nnk.springboot.repositories.RatingRepository;

/**
 * Implementation class of the RatingService interface, this class answer to
 * RatingController request using RatingRepository and RatingMapping classes.
 *
 * @author Thierry Schreiner
 */
@Service
public class RatingServiceImpl implements RatingService {

    /**
     * RatingRepository bean injected by Spring when service is created.
     */
    @Autowired
    private RatingRepository ratingRepository;

    /**
     * RatingMapping bean injected by Spring when service is created.
     */
    @Autowired
    private RatingMapping ratingMapping;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RatingDTO> findAll() {

        return ratingMapping
                .mapAListOfRating(ratingRepository.findAll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RatingDTO save(final RatingDTO ratingDTO) {
        Rating rating = ratingMapping.mapDTOToEntity(ratingDTO);
        System.out.println(rating.toString());
        Rating savedRating = ratingRepository.save(rating);
        System.out.println(savedRating.toString());
        RatingDTO savedBidListDTO = ratingMapping.mapEntityToDTO(savedRating);
        System.out.println(savedBidListDTO.toString());
        return savedBidListDTO;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RatingDTO delete(final Integer id) throws RatingNotFoundException {
        Rating rating = ratingRepository.findById(id)
                .orElseThrow(() -> new RatingNotFoundException(
                        "No Rating record exist for given id"));
        ratingRepository.deleteById(id);

        return ratingMapping.mapEntityToDTO(rating);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RatingDTO getById(final Integer id) throws RatingNotFoundException {
        Rating rating = ratingRepository.findById(id)
                .orElseThrow(() -> new RatingNotFoundException(
                        "No Rating record exist for given id"));

        return ratingMapping.mapEntityToDTO(rating);
    }

}
