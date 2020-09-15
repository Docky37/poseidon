package com.nnk.springboot.controllers;

import com.nnk.springboot.dto.RuleNameDTO;
import com.nnk.springboot.exceptions.RuleNameNotFoundException;
import com.nnk.springboot.services.RuleNameService;

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
 * Controller class use to manage RuleName entity CRUD methods.
 *
 * @author Thierry Schreiner
 */
@Controller
public class RuleNameController {

    /**
     * Create a SLF4J/LOG4J LOGGER instance.
     */
    static final Logger LOGGER = LoggerFactory
            .getLogger(RuleNameController.class);

    /**
     * ListService bean injected by Spring when controller is created.
     */
    @Autowired
    private RuleNameService ruleNameService;

    /**
     * Get HTML request used to display the list.html front page that lists all
     * RuleNames saved in Database.
     *
     * @param model
     * @return a String(the address of list.html page)
     */
    @GetMapping("/ruleName/list")
    public String home(final Model model) {
        LOGGER.info("NEW HTML GET REQUEST on /ruleName/list");
        List<RuleNameDTO> ruleNames = ruleNameService.findAll();
        model.addAttribute("ruleNames", ruleNames);
        return "ruleName/list";
    }

    /**
     * Get HTML request used to display the add.html front page that provides a
     * form to add a new RuleName.
     *
     * @param model
     * @return a String(the address of add.html page)
     */
    @GetMapping("/ruleName/add")
    public String addRuleForm(final Model model) {
        LOGGER.info("NEW HTML GET REQUEST on /ruleName/add");
        model.addAttribute("ruleNameDTO", new RuleNameDTO());
        return "ruleName/add";
    }

    /**
     * Post HTML request used to validate data and save the new RuleName.
     *
     * @param ruleNameDTO
     * @param result
     * @param model
     * @return a String(list.html redirection address if valid data else
     *         add.html address)
     */
    @PostMapping("/ruleName/validate")
    public String validate(@Valid final RuleNameDTO ruleNameDTO,
            final BindingResult result, final Model model) {
        LOGGER.info("NEW HTML POST REQUEST on /ruleName/validate: {}",
                ruleNameDTO.toString());
        if (result.hasErrors()) {
            LOGGER.error("ERROR(S): {}", result);
            return "ruleName/add";
        }
        RuleNameDTO savedRuleNameDTO = ruleNameService.save(ruleNameDTO);
        LOGGER.info(" => New RuleName saved in DB: {}",
                savedRuleNameDTO.toString());
        return "redirect:/ruleName/list";
    }

    /**
     * Get HTML request used to display the update.html front page that provides
     * a form to update an existing RuleName.
     *
     * @param id
     * @param model
     * @return a String(the address of update.html page)
     */
    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") final Integer id,
            final Model model) {
        LOGGER.info("NEW HTML GET REQUEST on /ruleName/update/{}", id);
        RuleNameDTO ruleNameDTO;
        try {
            ruleNameDTO = ruleNameService.getById(id);
            model.addAttribute("ruleNameDTO", ruleNameDTO);
            return "ruleName/update";
        } catch (RuleNameNotFoundException e) {
            LOGGER.error(" => No RuleName record exist for id={}!", id);
            return "redirect:/ruleName/list";
        }
    }

    /**
     * Post HTML request used to validate data and save the updated RuleName.
     *
     * @param id
     * @param ruleNameDTO
     * @param result
     * @param model
     * @return a String(list.html redirection address if valid data else
     *         update.html address)
     */
    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") final Integer id,
            @Valid final RuleNameDTO ruleNameDTO, final BindingResult result,
            final Model model) {
        ruleNameDTO.setId(id);
        LOGGER.info("NEW HTML POST REQUEST on /ruleName/update/{}", id);
        LOGGER.info("   => {}", ruleNameDTO.toString());
        if (result.hasErrors()) {
            LOGGER.error("ERROR(S): {}", result);
            return "ruleName/update";
        }
        RuleNameDTO savedRuleNameDTO = ruleNameService.save(ruleNameDTO);
        LOGGER.info(" => Updated RuleName saved in DB: {}",
                savedRuleNameDTO.toString());
        return "redirect:/ruleName/list";
    }

    /**
     * Get HTML request used to delete a RuleName by its id.
     *
     * @param id
     * @param model
     * @return a String(list.html redirection address)
     */
    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") final Integer id,
            final Model model) {
        LOGGER.info("NEW HTML DELETE REQUEST on /ruleName/delete/{}", id);

        try {
            RuleNameDTO deletedRuleNameDTO = ruleNameService.delete(id);
            LOGGER.info(" => RuleName removed from DB: {}",
                    deletedRuleNameDTO.toString());
        } catch (RuleNameNotFoundException e) {
            LOGGER.error(" => No RuleName record exist for id={}!", id);
            List<RuleNameDTO> ruleNames = ruleNameService.findAll();
            model.addAttribute("ruleNames", ruleNames);
        }

        return "redirect:/ruleName/list";
    }
}
