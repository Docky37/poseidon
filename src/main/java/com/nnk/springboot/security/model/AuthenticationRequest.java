package com.nnk.springboot.security.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Model class used to store the credentials of an authentication request.
 *
 * @author Thierry SCHREINER
 */
public class AuthenticationRequest {

    /**
     * The username of the user.
     */
    @Getter
    @Setter
    private String username;

    /**
     * The password of the user.
     */
    @Getter
    @Setter
    private String password;

    /**
     * Empty constructor.
     */
    public AuthenticationRequest() {
    }

    /**
     * Class constructor.
     *
     * @param pUsername
     * @param pPassword
     */
    public AuthenticationRequest(final String pUsername,
            final String pPassword) {
        username = pUsername;
        password = pPassword;
    }

}
