package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RatingDTO;
import com.nnk.springboot.exceptions.RatingNotFoundException;
import com.nnk.springboot.services.RatingService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import javax.validation.Valid;

@Controller
public class RatingController {

    /**
     * Create a SLF4J/LOG4J LOGGER instance.
     */
    static final Logger LOGGER = LoggerFactory
            .getLogger(RatingController.class);

    /**
     * ListService bean injected by Spring when controller is created.
     */
    @Autowired
    private RatingService ratingService;

    /**
     * Get HTML request used to display the list.html front page that lists all
     * Ratings saved in Database.
     *
     * @param model
     * @return a String(the address of list.html page)
     */
    @GetMapping("/rating/list")
    public String home(final Model model) {
        LOGGER.info("NEW HTML GET REQUEST on /rating/list");
        List<RatingDTO> ratings = ratingService.findAll();
        model.addAttribute("ratings", ratings);
        return "rating/list";
    }

    /**
     * Get HTML request used to display the add.html front page that provides a
     * form to add a new Rating.
     *
     * @param model
     * @return a String(the address of add.html page)
     */
    @GetMapping("/rating/add")
    public String addRatingForm(final Model model) {
        LOGGER.info("NEW HTML GET REQUEST on /rating/add");
        model.addAttribute("ratingDTO", new RatingDTO());
        return "rating/add";
    }

    /**
     * Post HTML request used to validate data and save the new Rating.
     *
     * @param ratingDTO
     * @param result
     * @param model
     * @return a String(list.html redirection address if valid data else
     *         add.html address)
     */
    @PostMapping("/rating/validate")
    public String validate(@Valid final RatingDTO ratingDTO,
            final BindingResult result, final Model model) {
        LOGGER.info("NEW HTML POST REQUEST on /rating/validate: {}",
                ratingDTO.toString());
        if (result.hasErrors()) {
            LOGGER.error("ERROR(S): {}", result);
            return "rating/add";
        }
        RatingDTO savedRatingDTO = ratingService.save(ratingDTO);
        LOGGER.info(" => New Rating saved in DB: {}",
                savedRatingDTO.toString());
        return "redirect:/rating/list";
    }

    /**
     * Get HTML request used to display the update.html front page that provides
     * a form to update an existing Rating.
     *
     * @param id
     * @param model
     * @return a String(the address of update.html page)
     */
    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") final Integer id,
            final Model model) {
        LOGGER.info("NEW HTML GET REQUEST on /rating/update/{}", id);
        RatingDTO ratingDTO;
        try {
            ratingDTO = ratingService.getById(id);
            model.addAttribute("ratingDTO", ratingDTO);
            return "rating/update";
        } catch (RatingNotFoundException e) {
            LOGGER.error(" => No Rating record exist for id={}!", id);
            return "redirect:/rating/list";
        }
    }

    /**
     * Post HTML request used to validate data and save the updated Rating.
     *
     * @param id
     * @param ratingDTO
     * @param result
     * @param model
     * @return a String(list.html redirection address if valid data else
     *         update.html address)
     */
    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") final Integer id,
            @Valid final RatingDTO ratingDTO, final BindingResult result,
            final Model model) {
        ratingDTO.setId(id);
        LOGGER.info("NEW HTML POST REQUEST on /rating/update/{}", id);
        LOGGER.info("   => {}", ratingDTO.toString());
        if (result.hasErrors()) {
            LOGGER.error("ERROR(S): {}", result);
            return "rating/update";
        }
        RatingDTO savedRatingDTO = ratingService.save(ratingDTO);
        LOGGER.info(" => Updated Rating saved in DB: {}",
                savedRatingDTO.toString());
        return "redirect:/rating/list";
    }

    /**
     * Get HTML request used to delete a Rating by its id.
     *
     * @param id
     * @param model
     * @return a String(list.html redirection address)
     */
    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") final Integer id,
            final Model model) {
        LOGGER.info("NEW HTML DELETE REQUEST on /rating/delete/{}", id);

        try {
            RatingDTO deletedRatingDTO = ratingService.delete(id);
            LOGGER.info(" => Rating removed from DB: {}",
                    deletedRatingDTO.toString());
        } catch (RatingNotFoundException e) {
            LOGGER.error(" => No Rating record exist for id={}!", id);
            List<RatingDTO> ratings = ratingService.findAll();
            model.addAttribute("ratings", ratings);
        }

        return "redirect:/rating/list";
    }
}
