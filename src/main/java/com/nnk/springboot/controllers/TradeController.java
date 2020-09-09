package com.nnk.springboot.controllers;

import com.nnk.springboot.dto.TradeDTO;
import com.nnk.springboot.dto.TradeFullDTO;
import com.nnk.springboot.exceptions.TradeNotFoundException;
import com.nnk.springboot.services.TradeService;

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

/**
 * Controller class use to manage Trade entity CRUD methods.
 *
 * @author Thierry Schreiner
 */
@Controller
public class TradeController {

    /**
     * Create a SLF4J/LOG4J LOGGER instance.
     */
    static final Logger LOGGER = LoggerFactory.getLogger(TradeController.class);

    /**
     * TradeService bean injected by Spring when controller is created.
     */
    @Autowired
    private TradeService tradeService;

    /**
     * Get HTML request used to display the list.html front page that lists all
     * trades saved in Database.
     *
     * @param model
     * @return a String(the address of list.html page)
     */
    @GetMapping("/trade/list")
    public String home(Model model) {
        LOGGER.info("NEW HTML GET REQUEST on /trade/list");
        List<TradeDTO> trades = tradeService.findAll();
        model.addAttribute("trades", trades);
        return "trade/list";
    }

    /**
     * Get HTML request used to display the add.html front page that provides a
     * form to add a new Trade.
     *
     * @param model
     * @return a String(the address of add.html page)
     */
    @GetMapping("/trade/add")
    public String addUser(final Model model) {
        LOGGER.info("NEW HTML GET REQUEST on /trade/add");
        model.addAttribute("tradeDTO", new TradeDTO());
        return "trade/add";
    }

    /**
     * Post HTML request used to validate data and save the new Trade.
     *
     * @param model
     * @param tradeDTO
     * @param result
     * @return a String(list.html redirection address if valid data else
     *         add.html address)
     */
    @PostMapping("/trade/validate")
    public String validate(@Valid final TradeDTO tradeDTO,
            final BindingResult result, final Model model) {
        LOGGER.info("NEW HTML POST REQUEST on /trade/validate: {}",
                tradeDTO.toString());
        if (result.hasErrors()) {
            LOGGER.error("ERROR(S): {}", result);
            return "trade/add";
        }
        TradeDTO savedTradeDTO = tradeService.save(tradeDTO);
        LOGGER.info(" => New trade saved in DB: {}", savedTradeDTO.toString());
        return "redirect:/trade/list";
    }

    /**
     * Get HTML request used to display the update.html front page that provides
     * a form to update an existing Trade.
     *
     * @param id
     * @param model
     * @return a String(the address of update.html page)
     * @throws TradeNotFoundException
     */
    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        TradeFullDTO tradeFullDTO;
        LOGGER.info("NEW HTML GET REQUEST on /trade/update/{}", id);
        try {
            tradeFullDTO = tradeService.getById(id);
            model.addAttribute("tradeFullDTO", tradeFullDTO);
            return "trade/update";
        } catch (TradeNotFoundException e) {
            LOGGER.error(" => No Trade record exist for id={}!", id);
            return "redirect:/trade/list";
        }
    }

    /**
     * Post HTML request used to validate data and save the updated Trade.
     *
     * @param id
     * @param tradeDTO
     * @param result
     * @param model
     * @return a String(list.html redirection address if valid data else
     *         update.html address)
     */
    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") final Integer id,
            @Valid final TradeFullDTO tradeDTO, final BindingResult result,
            final Model model) {
        tradeDTO.setTradeId(id);
        LOGGER.info("NEW HTML POST REQUEST on /trade/update/{}", id);
        LOGGER.info("   => {}", tradeDTO.toString());
        if (result.hasErrors()) {
            LOGGER.error("ERROR(S): {}", result);
            return "trade/update";
        }
        TradeFullDTO savedTradeDTO = tradeService.saveFullDTO(tradeDTO);
        LOGGER.info(" => Updated trade saved in DB: {}",
                savedTradeDTO.toString());
        return "redirect:/trade/list";
    }

    /**
     * Get HTML request used to delete a Trade by its id.
     *
     * @param id
     * @param model
     * @return a String(the address of update.html page)
     * @throws TradeNotFoundException
     */
    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        LOGGER.info("NEW HTML DELETE REQUEST on /trade/delete/{}", id);

        try {
            TradeDTO deletedTradeDTO = tradeService.delete(id);
            LOGGER.info(" => Trade removed from DB: {}",
                    deletedTradeDTO.toString());
        } catch (TradeNotFoundException e) {
            LOGGER.error(" => No Trade record exist for id={}!", id);
            List<TradeDTO> trades = tradeService.findAll();
            model.addAttribute("trades", trades);
        }

        return "redirect:/trade/list";
    }
}
