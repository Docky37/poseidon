package com.nnk.springboot.security.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * The JSON Web Token (JWT) Authentication filter checks if the incoming request
 * has a valid JSON Web Token (JWT). It checks the validity of the JWT by
 * verifying the JWT signature, audiences and issuer based on the HTTP filter
 * configuration.
 *
 * @author Thierry SCHREINER
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    /**
     * Create a SLF4J/LOG4J LOGGER instance.
     */
    static final Logger LOGGER = LoggerFactory
            .getLogger(JwtRequestFilter.class);

    /**
     * Instance of UserDetailsService declaration. Spring will use the
     * UserDetailsServiceImpl class.
     */
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Instance of JwtUtil class, a utility class that provides methods to
     * authenticate with a Json web token.
     */
    @Autowired
    private JwtUtil jwtUtil;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doFilterInternal(final HttpServletRequest request,
            final HttpServletResponse response, final FilterChain filterChain)
            throws ServletException, IOException {
        LOGGER.info("Start doFilterInternal method.");
        Cookie[] cookies = request.getCookies();
        String jwt = null;
        if (cookies != null) {
            jwt = cookies[0].getValue();
        }
        LOGGER.debug("Json Web Token = " + jwt);

        String username = null;

        if (jwt != null) {
            username = jwtUtil.extractUsername(jwt);
            LOGGER.debug("username =" + username);
        }

        if (username != null && SecurityContextHolder.getContext()
                .getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService
                    .loadUserByUsername(username);
            LOGGER.debug("userDetails =" + userDetails.toString());
            if (jwtUtil.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken userPassAuthToken =
                        new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                userPassAuthToken
                        .setDetails(new WebAuthenticationDetailsSource()
                                .buildDetails(request));
                SecurityContextHolder.getContext()
                        .setAuthentication(userPassAuthToken);
            }
        }
        filterChain.doFilter(request, response);
    }

}
