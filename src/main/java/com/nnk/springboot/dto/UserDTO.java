package com.nnk.springboot.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDTO {
    /**
     * Id of the userDTO.
     */
    private Integer id;

    /**
     * Username (mandatory to log in before using Poseidon).
     */
    @NotBlank(message = "Username is mandatory")
    private String username;

    /**
     * User's password (mandatory to log in before using Poseidon).
     */
    @NotBlank(message = "Password is mandatory")
    private String password;

    /**
     * User's full name.
     */
    @NotBlank(message = "FullName is mandatory")
    private String fullname;

    /**
     * User's role.
     */
    @NotBlank(message = "Role is mandatory")
    private String role;

}
