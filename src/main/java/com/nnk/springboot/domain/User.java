package com.nnk.springboot.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {
    /**
     * Id of the user (Primary Key).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
