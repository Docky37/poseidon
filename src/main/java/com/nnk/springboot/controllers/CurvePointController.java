package com.nnk.springboot.controllers;

import com.nnk.springboot.dto.CurvePointDTO;
import com.nnk.springboot.exceptions.CurvePointNotFoundException;
import com.nnk.springboot.services.CurvePointService;

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
public class CurvePointController {

    /**
     * Create a SLF4J/LOG4J LOGGER instance.
     */
    static final Logger LOGGER = LoggerFactory
            .getLogger(CurvePointController.class);

    /**
     * ListService bean injected by Spring when controller is created.
     */
    @Autowired
    private CurvePointService curvePointService;

    /**
     * Get HTML request used to display the list.html front page that lists all
     * CurvePoints saved in Database.
     *
     * @param model
     * @return a String(the address of list.html page)
     */
    @GetMapping("/curvePoint/list")
    public String home(final Model model) {
        LOGGER.info("NEW HTML GET REQUEST on /curvePoint/list");
        List<CurvePointDTO> curvePoints = curvePointService.findAll();
        model.addAttribute("curvePoints", curvePoints);
        return "curvePoint/list";
    }

    /**
     * Get HTML request used to display the add.html front page that provides a
     * form to add a new CurvePoint.
     *
     * @param model
     * @return a String(the address of add.html page)
     */
    @GetMapping("/curvePoint/add")
    public String addCurvePointForm(final Model model) {
        LOGGER.info("NEW HTML GET REQUEST on /curvePoint/add");
        model.addAttribute("curvePointDTO", new CurvePointDTO());
        return "curvePoint/add";
    }

    /**
     * Post HTML request used to validate data and save the new CurvePoint.
     *
     * @param curvePointDTO
     * @param result
     * @param model
     * @return a String(list.html redirection address if valid data else
     *         add.html address)
     */
    @PostMapping("/curvePoint/validate")
    public String validate(@Valid final CurvePointDTO curvePointDTO,
            final BindingResult result, final Model model) {
        LOGGER.info("NEW HTML POST REQUEST on /curvePoint/validate: {}",
                curvePointDTO.toString());
        if (result.hasErrors()) {
            LOGGER.error("ERROR(S): {}", result);
            return "curvePoint/add";
        }
        CurvePointDTO savedCurvePointDTO = curvePointService
                .save(curvePointDTO);
        LOGGER.info(" => New CurvePoint saved in DB: {}",
                savedCurvePointDTO.toString());
        return "redirect:/curvePoint/list";
    }

    /**
     * Get HTML request used to display the update.html front page that provides
     * a form to update an existing CurvePoint.
     *
     * @param id
     * @param model
     * @return a String(the address of update.html page)
     * @throws CurvePointNotFoundException
     */
    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") final Integer id,
            final Model model) {
        LOGGER.info("NEW HTML GET REQUEST on /curvePoint/update/{}", id);
        CurvePointDTO curvePointDTO;
        try {
            curvePointDTO = curvePointService.getById(id);
            model.addAttribute("curvePointDTO", curvePointDTO);
            return "curvePoint/update";
        } catch (CurvePointNotFoundException e) {
            LOGGER.error(" => No CurvePoint record exist for id={}!", id);
            return "redirect:/curvePoint/list";
        }
    }

    /**
     * Post HTML request used to validate data and save the updated CurvePoint.
     *
     * @param id
     * @param curvePointDTO
     * @param result
     * @param model
     * @return a String(list.html redirection address if valid data else
     *         update.html address)
     */
    @PostMapping("/curvePoint/update/{id}")
    public String updateCurvePoint(@PathVariable("id") final Integer id,
            @Valid final CurvePointDTO curvePointDTO,
            final BindingResult result, final Model model) {
        curvePointDTO.setCurvePointId(id);
        LOGGER.info("NEW HTML POST REQUEST on /curvePoint/update/{}", id);
        LOGGER.info("   => {}", curvePointDTO.toString());
        if (result.hasErrors()) {
            LOGGER.error("ERROR(S): {}", result);
            return "curvePoint/update";
        }
        CurvePointDTO savedCurvePointDTO = curvePointService
                .save(curvePointDTO);
        LOGGER.info(" => Updated CurvePoint saved in DB: {}",
                savedCurvePointDTO.toString());
        return "redirect:/curvePoint/list";
    }

    /**
     * Get HTML request used to delete a CurvePoint by its id.
     *
     * @param id
     * @param model
     * @return a String(list.html redirection address)
     */
    @GetMapping("/curvePoint/delete/{id}")
    public String deleteCurvePoint(@PathVariable("id") final Integer id,
            final Model model) {
        LOGGER.info("NEW HTML DELETE REQUEST on /curvePoint/delete/{}", id);

        try {
            CurvePointDTO deletedCurvePointDTO = curvePointService.delete(id);
            LOGGER.info(" => CurvePoint removed from DB: {}",
                    deletedCurvePointDTO.toString());
        } catch (CurvePointNotFoundException e) {
            LOGGER.error(" => No CurvePoint record exist for id={}!", id);
            List<CurvePointDTO> curvePoints = curvePointService.findAll();
            model.addAttribute("curvePoints", curvePoints);
        }

        return "redirect:/curvePoint/list";
    }
}
