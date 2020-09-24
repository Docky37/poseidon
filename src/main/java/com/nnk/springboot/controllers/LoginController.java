package com.nnk.springboot.controllers;

import com.nnk.springboot.constants.Constants;
import com.nnk.springboot.security.model.AuthenticationRequest;
import com.nnk.springboot.security.util.JwtUtil;

import io.swagger.annotations.ApiOperation;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * This controller class is in charge of html request methods of login.
 *
 * @author Thierry SCHREINER
 */
@Controller
public class LoginController {

    /**
     * Create a SLF4J/LOG4J LOGGER instance.
     */
    static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

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

    /**
     * HTML GET request used to display the login form for jwt authenticate.
     *
     * @param model
     * @param error
     * @return a String
     */
    @GetMapping("/login")
    @ApiOperation(value = "Login form", notes = "Display the login form"
            + " front page.",
            response = String.class)
    public String login(final Model model, final String error) {
        LOGGER.info("NEW HTML GET REQUEST on /login");
        model.addAttribute("authenticationRequest",
                new AuthenticationRequest());

        return "/login";
    }

    /**
     * POST html request used to authenticate and provide a Token.
     *
     * @param authenticationRequest
     * @param response
     * @return a ResponseEntity<Object>
     * @throws Exception
     */
    @PostMapping("/authenticate")
    @ApiOperation(value = "Authentication request", notes = "Check data of"
            + " AuthenticationRequest. If credentials are valid then create a"
            + " Json Web Token and redirect to /bidList/list with a JWT cookie",
            response = String.class)
    public String createAuthenticationToken(
            final AuthenticationRequest authenticationRequest,
            final HttpServletResponse response) throws Exception {
        LOGGER.info("NEW HTML POST REQUEST on /authenticate");
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            LOGGER.error("Incorrect username or password! " + e);
            throw e;
        }
        LOGGER.info("OK - Valid Credentials");
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        LOGGER.info("Token = " + jwt);
        Cookie cookie = new Cookie("Token", jwt);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(Constants.COOKIE_VALIDITY_IN_SECONDS);
        response.addCookie(cookie);
        LOGGER.info("Cookie = " + cookie.getValue());
        return "redirect:/bidList/list";
    }

    /**
     * HTML POST request use to redirect to Spring default logout.
     * SecurityConfig delete the Token cookie.
     *
     * @return a String
     */
    @ApiOperation(value = "Logout request", notes = "Redirect to Spring"
             + " default logout. SecurityConfig delete the Token cookie.",
            response = String.class)
    @PostMapping("/app-logout")
    public String appLogout() {
        return "redirect:/logout";
    }

}
