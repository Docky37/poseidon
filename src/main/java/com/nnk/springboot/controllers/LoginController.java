package com.nnk.springboot.controllers;

import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.security.model.AuthenticationRequest;
import com.nnk.springboot.security.model.AuthenticationResponse;
import com.nnk.springboot.security.util.JwtUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * This controller class is in charge of html request methods of registration
 * and login.
 *
 * @author Thierry SCHREINER
 */
@Controller
//@RequestMapping("app")
public class LoginController {

    /**
     * Create a SLF4J/LOG4J LOGGER instance.
     */
    static final Logger LOGGER = LoggerFactory
            .getLogger(BidListController.class);

    @Autowired
    private UserRepository userRepository;

    /**
     * Instance of UserDetailsService declaration.
     */
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Instance of Json Web Token utility class declaration.
     */
    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Instance of AuthenticationManager declaration.
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/login")
    public String login(final Model model, final String error, final String logout) {
        System.out.println("LoginController.login()");
        if (error != null) {
            model.addAttribute("error",
                    "Your username and password is invalid.");
        }
        if (logout != null) {
            model.addAttribute("message",
                    "You have been logged out successfully.");
        }
        model.addAttribute("authenticationRequest", new AuthenticationRequest());
        return "login";
    }

    /**
     * POST html request used to authenticate and provide a Token.
     *
     * @param authenticationRequest
     * @return a ResponseEntity<Object>
     * @throws Exception
     */
    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(
            final AuthenticationRequest authenticationRequest)
            throws Exception {
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @GetMapping("secure/article-details")
    public ModelAndView getAllUserArticles() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("users", userRepository.findAll());
        mav.setViewName("user/list");
        return mav;
    }

    @GetMapping("error")
    public ModelAndView error() {
        ModelAndView mav = new ModelAndView();
        String errorMessage = "You are not authorized for the requested data.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("403");
        return mav;
    }
}
