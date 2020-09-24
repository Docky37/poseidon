package com.nnk.springboot.controllers;

import com.nnk.springboot.dto.UserDTO;
import com.nnk.springboot.exceptions.UserNotFoundException;
import com.nnk.springboot.services.UserService;

import io.swagger.annotations.ApiOperation;

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
    private UserService userService;

    /**
     * Get HTML request used to display the list.html front page that lists all
     * users saved in Database.
     *
     * @param model
     * @return a String(the address of list.html page)
     */
    @ApiOperation(value = "List all User", notes = "Display the list.html"
            + " front page that lists all bidLists saved in Database.",
            response = UserDTO[].class)
    @GetMapping("/user/list")
    public String home(final Model model) {
        LOGGER.info("NEW HTML GET REQUEST on /user/list");
        List<UserDTO> users = userService.findAll();
        model.addAttribute("users", users);
        return "user/list";
    }

    /**
     * Get HTML request used to display the add.html front page that provides a
     * form to add a new User.
     *
     * @param model
     * @return a String(the address of add.html page)
     */
    @ApiOperation(value = "Display the User Add form", notes = "Get the"
            + " add.html front page that allows user to add a new User.",
            response = String.class)
    @GetMapping("/user/add")
    public String addUser(final Model model) {
        LOGGER.info("NEW HTML GET REQUEST on /user/add");
        model.addAttribute("userDTO", new UserDTO());
        return "user/add";
    }

    /**
     * Post HTML request used to validate data and save the new User.
     *
     * @param model
     * @param userDTO
     * @param result
     * @return a String(list.html redirection address if valid data else
     *         add.html address)
     */
    @ApiOperation(value = "Add a new User", notes = "Check data of"
            + " User add.html form and save them in DataBase."
            + " Then redirect to the User/list",
            response = String.class)
    @PostMapping("/user/validate")
    public String validate(@Valid final UserDTO userDTO,
            final BindingResult result, final Model model) {
        LOGGER.info("NEW HTML POST REQUEST on /user/validate: {}",
                userDTO.toString());
        if (result.hasErrors()) {
            LOGGER.error("ERROR(S): {}", result);
            return "user/add";
        }
        UserDTO savedUserDTO = userService.save(userDTO);
        LOGGER.info(" => New User saved in DB: {}", savedUserDTO.toString());
        return "redirect:/user/list";
    }

    /**
     * Get HTML request used to display the update.html front page that provides
     * a form to update an existing BidList.
     *
     * @param id
     * @param model
     * @return a String(the address of update.html page)
     */
    @ApiOperation(value = "Display the User Update form", notes = "Get"
            + " the update.html front page that allows user to update a"
            + " User.", response = String.class)
    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") final Integer id,
            final Model model) {
        LOGGER.info("NEW HTML GET REQUEST on /user/update/{}", id);
        UserDTO userDTO;
        try {
            userDTO = userService.getById(id);
            model.addAttribute("userDTO", userDTO);
            return "user/update";
        } catch (UserNotFoundException e) {
            LOGGER.error(" => No User record exist for id={}!", id);
            return "redirect:/user/list";
        }
    }

    /**
     * Post HTML request used to validate data and save the updated BidList.
     *
     * @param id
     * @param userDTO
     * @param result
     * @param model
     * @return a String(list.html redirection address if valid data else
     *         update.html address)
     */
    @ApiOperation(value = "Update a User", notes = "Check data of"
            + " User update.html form and save them in DataBase."
            + "Then redirect to the User/list",
            response = String.class)
    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") final Integer id,
            @Valid final UserDTO userDTO, final BindingResult result,
            final Model model) {
        userDTO.setId(id);
        LOGGER.info("NEW HTML POST REQUEST on /user/update/{}", id);
        LOGGER.info("   => {}", userDTO.toString());
        if (result.hasErrors()) {
            LOGGER.error("ERROR(S): {}", result);
            return "user/update";
        }
        UserDTO savedUserDTO = userService.save(userDTO);
        LOGGER.info(" => Updated User saved in DB: {}",
                savedUserDTO.toString());
        return "redirect:/user/list";
    }

    /**
     * Get HTML request used to delete a User by its id.
     *
     * @param id
     * @param model
     * @return a String(the address of update.html page)
     */
    @ApiOperation(value = "Delete a User by id", notes = "Find a"
            + " User by its id and drop it from database."
            + " Then redirect to the User/list",
            response = String.class)
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") final Integer id,
            final Model model) {
        LOGGER.info("NEW HTML DELETE REQUEST on /user/delete/{}", id);

        try {
            UserDTO deletedUserDTO = userService.delete(id);
            LOGGER.info(" => User removed from DB: {}",
                    deletedUserDTO.toString());
        } catch (UserNotFoundException e) {
            LOGGER.error(" => No User record exist for id={}!", id);
            List<UserDTO> users = userService.findAll();
            model.addAttribute("users", users);
        }

        return "redirect:/user/list";
    }
}
