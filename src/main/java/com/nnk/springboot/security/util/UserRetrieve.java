package com.nnk.springboot.security.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;

/**
 * This class contains a method used to retrieve the name of the logged user.
 *
 * @author Thierry Schreiner
 */
@Component
@NoArgsConstructor
public class UserRetrieve {

    /**
     * Method that finds the username of the logged user.
     * @return a String
     */
    public String getLoggedUser() {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return ((UserDetails) principal).getUsername();
    }

}
