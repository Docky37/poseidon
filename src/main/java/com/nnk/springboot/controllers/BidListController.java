package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidListDTO;
import com.nnk.springboot.exceptions.BidListNotFoundException;
import com.nnk.springboot.services.BidListService;

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
 * Controller class use to manage BidList entity CRUD methods.
 * 
 * @author Thierry Schreiner
 */
@Controller
public class BidListController {

    /**
     * Create a SLF4J/LOG4J LOGGER instance.
     */
    static final Logger LOGGER = LoggerFactory
            .getLogger(BidListController.class);

    /**
     * BidListService bean injected by Spring when controller is created.
     */
    @Autowired
    private BidListService bidListService;

    /**
     * Get HTML request used to display the list.html front page that lists all
     * bidLists saved in Database.
     *
     * @param model
     * @return a String(the address of list.html page)
     */
    @GetMapping("/bidList/list")
    public String home(final Model model) {
        LOGGER.info("NEW HTML GET REQUEST on /bidList/list");
        List<BidListDTO> bidLists = bidListService.findAll();
        model.addAttribute("bidLists", bidLists);
        return "bidList/list";
    }

    /**
     * Get HTML request used to display the add.html front page that provides a
     * form to add a new BidList.
     *
     * @param model
     * @return a String(the address of add.html page)
     */
    @GetMapping("/bidList/add")
    public String addBidForm(final Model model) {
        LOGGER.info("NEW HTML GET REQUEST on /bidList/add");
        model.addAttribute("bidListDTO", new BidListDTO());
        return "bidList/add";
    }

    /**
     * Post HTML request used to validate data and save the new BidList.
     *
     * @param model
     * @param bidListDTO
     * @param result
     * @return a String(list.html redirection address if valid data else
     *         add.html address)
     */
    @PostMapping("/bidList/validate")
    public String validate(final Model model,
            @Valid final BidListDTO bidListDTO, final BindingResult result) {
        LOGGER.info("NEW HTML POST REQUEST on /bidList/validate: {}",
                bidListDTO.toString());
        if (result.hasErrors()) {
            LOGGER.error("ERROR(S): {}", result);
            return "bidList/add";
        }
        BidListDTO savedBidListDTO = bidListService.save(bidListDTO);
        LOGGER.info(" => New bidList saved in DB: {}",
                savedBidListDTO.toString());
        return "redirect:/bidList/list";
   }

    /**
     * Get HTML request used to display the update.html front page that provides
     * a form to update an existing BidList by its address.
     *
     * @param id
     * @param model
     * @return a String(the address of update.html page)
     * @throws BidListNotFoundException 
     */
    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") final Integer id,
            final Model model) {
        LOGGER.info("NEW HTML GET REQUEST on /bidList/update/{}", id);
        BidListDTO bidListDTO;
        try {
            bidListDTO = bidListService.getById(id);
            model.addAttribute("bidListDTO", bidListDTO);
            return "bidList/update";
        } catch (BidListNotFoundException e) {
            LOGGER.error(" => No BidList record exist for id={}!", id);
            return "redirect:/bidList/list";
        }
    }

    /**
     * Post HTML request used to validate data and save the updated BidList.
     *
     * @param id
     * @param bidListDTO
     * @param result
     * @param model
     * @return a String(list.html redirection address if valid data else
     *         update.html address)
     */
    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") final Integer id,
            @Valid final BidListDTO bidListDTO, final BindingResult result,
            final Model model) {
        bidListDTO.setBidListId(id);
        LOGGER.info("NEW HTML POST REQUEST on /bidList/update/{}", id);
        LOGGER.info("   => {}", bidListDTO.toString());
        if (result.hasErrors()) {
            LOGGER.error("ERROR(S): {}", result);
            return "bidList/update";
        }
        BidListDTO savedBidListDTO = bidListService.save(bidListDTO);
        LOGGER.info(" => Updated bidList saved in DB: {}",
                savedBidListDTO.toString());
        return "redirect:/bidList/list";
    }

    /**
     * Get HTML request used to delete a BidList by its id.
     *
     * @param id
     * @param model
     * @return a String(the address of update.html page)
     * @throws BidListNotFoundException 
     */
    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") final Integer id,
            final Model model) {
        LOGGER.info("NEW HTML DELETE REQUEST on /bidList/delete/{}", id);
        
        try{
            BidListDTO deletedBidListDTO = bidListService.delete(id);
        }catch (BidListNotFoundException e) {
            LOGGER.error(" => No BidList record exist for id={}!", id);
        }
        

        return "redirect:/bidList/list";
    }
}
