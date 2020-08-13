package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidListDTO;
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

@Controller
public class BidListController {

	/**
	 * Create a SLF4J/LOG4J LOGGER instance.
	 */
	static final Logger LOGGER = LoggerFactory.getLogger(BidListController.class);

	@Autowired
	BidListService bidListService;

	@GetMapping("/bidList/list")
	public String home(Model model) {
		LOGGER.info("NEW HTML GET REQUEST on /bidList/list");
		List<BidListDTO> bidLists = bidListService.findAll();
		model.addAttribute("bidLists", bidLists);
		return "bidList/list";
	}

	@GetMapping("/bidList/add")
	public String addBidForm(Model model) {
		LOGGER.info("NEW HTML GET REQUEST on /bidList/add");
		model.addAttribute("bidListDTO", new BidListDTO());
		return "bidList/add";
	}

	@PostMapping("/bidList/validate")
	public String validate(final Model model, @Valid final BidListDTO bidListDTO, final BindingResult result) {
		LOGGER.info("NEW HTML POST REQUEST on /bidList/validate: {}", bidListDTO.toString());
		if (result.hasErrors()) {
			LOGGER.error("ERROR(S): {}", result);
			return "bidList/add";
		}
		BidListDTO savedBidListDTO = bidListService.save(bidListDTO);
		LOGGER.info(" => New bidList saved in DB: {}", savedBidListDTO.toString());
		return "redirect:/bidList/list";
	}

	@GetMapping("/bidList/update/{id}")
	public String showUpdateForm(@PathVariable("id") final Integer id, final Model model) {
		LOGGER.info("NEW HTML GET REQUEST on /bidList/update/{}", id);
		BidListDTO bidListDTO = bidListService.getById(id);
		model.addAttribute("bidListDTO", bidListDTO);
		return "bidList/update";
	}

	@PostMapping("/bidList/update/{id}")
	public String updateBid(@PathVariable("id") Integer id, @Valid BidListDTO bidListDTO, BindingResult result,
			Model model) {
		bidListDTO.setBidListId(id);
		LOGGER.info("NEW HTML POST REQUEST on /bidList/update/{}", id);
		LOGGER.info("   => {}", bidListDTO.toString());
		if (result.hasErrors()) {
			LOGGER.error("ERROR(S): {}", result);
			return "bidList/update";
		}
		BidListDTO savedBidListDTO = bidListService.save(bidListDTO);
		LOGGER.info(" => Updated bidList saved in DB: {}", savedBidListDTO.toString());
		return "redirect:/bidList/list";
	}

	@GetMapping("/bidList/delete/{id}")
	public String deleteBid(@PathVariable("id") Integer id, Model model) {
		LOGGER.info("NEW HTML DELETE REQUEST on /bidList/delete/{}", id);
		BidListDTO deletedBidListDTO = bidListService.delete(id);
		if (deletedBidListDTO != null) {
			LOGGER.info(" => BidList deleted: {}", deletedBidListDTO.toString());
		} else {
			LOGGER.error(" => BidList with id={} not found!", id);
		}

		return "redirect:/bidList/list";
	}
}
