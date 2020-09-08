package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * Controller class use to manage User entity CRUD methods.
 *
 * @author Thierry Schreiner
 */
@Controller
public class UserController {

    /**
     * Create a SLF4J/LOG4J LOGGER instance.
     */
    static final Logger LOGGER = LoggerFactory
            .getLogger(BidListController.class);

    /**
     * UserRepository bean injected by Spring when controller is created.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Get HTML request used to display the list.html front page that lists all
     * users saved in Database.
     *
     * @param model
     * @return a String(the address of list.html page)
     */
    @RequestMapping("/user/list")
    public String home(final Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "user/list";
    }

    /**
     * Get HTML request used to display the add.html front page that provides a
     * form to add a new User.
     *
     * @param model
     * @return a String(the address of add.html page)
     */
    @GetMapping("/user/add")
    public String addUser(final Model model) {
        return "user/add";
    }

    /**
     * Post HTML request used to validate data and save the new User.
     *
     * @param model
     * @param user
     * @param result
     * @return a String(list.html redirection address if valid data else
     *         add.html address)
     */
    @PostMapping("/user/validate")
    public String validate(@Valid final User user, final BindingResult result,
            final Model model) {
        if (!result.hasErrors()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);
            model.addAttribute("users", userRepository.findAll());
            return "redirect:/user/list";
        }
        return "user/add";
    }

    /**
     * Get HTML request used to display the update.html front page that provides
     * a form to update an existing BidList.
     *
     * @param id
     * @param model
     * @return a String(the address of update.html page)
     */
    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") final Integer id,
            final Model model) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Invalid user Id:" + id));
        user.setPassword("");
        model.addAttribute("user", user);
        return "user/update";
    }

    /**
     * Post HTML request used to validate data and save the updated BidList.
     *
     * @param id
     * @param user
     * @param result
     * @param model
     * @return a String(list.html redirection address if valid data else
     *         update.html address)
     */
    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") final Integer id,
            @Valid final User user, final BindingResult result,
            final Model model) {
        if (result.hasErrors()) {
            return "user/update";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setId(id);
        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return "redirect:/user/list";
    }

    /**
     * Get HTML request used to delete a BidList by its id.
     *
     * @param id
     * @param model
     * @return a String(the address of update.html page)
     */
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") final Integer id,
            final Model model) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        model.addAttribute("users", userRepository.findAll());
        return "redirect:/user/list";
    }
}
