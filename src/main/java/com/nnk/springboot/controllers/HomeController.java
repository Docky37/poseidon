package com.nnk.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This home controller manages Poseidon hompage access.
 *
 * @author Thierry Schreiner
 */
@Controller
public class HomeController {

    /**
     * RequestMapping method that redirects to homepage.
     *
     * @param model
     * @return a String
     */
    @RequestMapping("/")
    public String home(final Model model) {
        return "home";
    }

    /**
     * RequestMapping method that redirects /admin/home to /bidList/list.
     *
     * @param model
     * @return a String
     */
    @RequestMapping("/admin/home")
    public String adminHome(final Model model) {
        return "redirect:/bidList/list";
    }

}
