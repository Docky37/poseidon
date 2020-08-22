package com.nnk.springboot.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class UserRetrieve {

    public String getConnectedUser() {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return ((UserDetails) principal).getUsername();
    }

}
