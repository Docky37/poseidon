package com.nnk.springboot.controllers;

import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.security.model.AuthenticationRequest;
import com.nnk.springboot.security.util.JwtUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.servlet.ModelAndView;

/**
 * This controller class is in charge of html request methods of login.
 *
 * @author Thierry SCHREINER
 */
@Controller
//@RequestMapping("app")
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
     * UserRepository instance used to deal with database.
     */
    @Autowired
    private UserRepository userRepository;

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
     * @param logout
     * @return a String
     */
    @GetMapping("/login")
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
     * @return a ResponseEntity<Object>
     * @throws Exception
     */
    @PostMapping("/authenticate")
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
            response.addHeader("message", "401");
            LOGGER.error("Incorrect username or password! " + e);
            return "redirect:/error/401";
            //throw new BadCredentialsException("Incorrect username or password", e);
        }
        LOGGER.info("OK - Valid Credentials");
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        LOGGER.info("Token = " + jwt);
        Cookie cookie = new Cookie("Token", jwt);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(3600);
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
    @PostMapping("/app-logout")
    public String appLogout() {
        return "redirect:/logout";
    }

    /**
     * HTML GET request that lists all registred users.
     *
     * @return a ModelAndView
     */
    @GetMapping("secure/article-details")
    public ModelAndView getAllUserArticles() {
        LOGGER.info("HTML GET Request on secure/article-details");
        ModelAndView mav = new ModelAndView();
        mav.addObject("users", userRepository.findAll());
        mav.setViewName("user/list");
        return mav;
    }

    /**
     * HTML GET request that returns custom error page.
     *
     * @return a ModelAndView
     */
    @GetMapping("/error")
    public String error(final HttpServletRequest request) {
        String errorNumber = request.getHeader("message");
        LOGGER.error("Error " + errorNumber);
        //ModelAndView mav = new ModelAndView();
        String errorMessage = "";
        if (errorNumber==null) {
            errorNumber="403";
        }
        switch (errorNumber) {
        case "401":
            errorMessage = "Incorrect username or password!";
            break;
        case "403":
            errorMessage = "You are not authorized for the requested data!";
            break;
        case "404":
            errorMessage = "Sorry but there is no result for your request!";
            break;
        default:
            errorMessage = "Error " + errorNumber;
        }
        //mav.addObject("errorMsg", errorMessage);
        //mav.setViewName(errorNumber);
        return "redirect:/error/" + errorNumber;
    }

}
