package com.nnk.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

/**
 * Custom implementation of UserDetailsService interface.
 *
 * @author Thierry SCHREINER
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     * UserRepository instance used to deal with database.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Override method that finds and loads details of a user from database when
     * he logs in.
     *
     * @return a Buddy object
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String login)
            throws UsernameNotFoundException {
        User user = userRepository.findByUsername(login)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "No user found with username: " + login));
        return user;
    }
}
