package com.nnk.springboot.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;
import com.nnk.springboot.constants.Constants;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(description = "Details of a User.")
public class UserDTO {
    /**
     * Id of the userDTO.
     */
    @ApiModelProperty(hidden = true)
    private Integer id;

    /**
     * Username (mandatory to log in before using Poseidon).
     */
    @ApiModelProperty(notes = "Username (mandatory to log in before using"
            + " Poseidon).", required = true)
    @NotBlank(message = "Username is mandatory!")
    @Size(min = Constants.USERNAME_MIN_SIZE, max = Constants.USERNAME_MAX_SIZE,
            message = "Size of username: 7 min to 15 max")
    private String username;

    /**
     * User's password (mandatory to log in before using Poseidon).
     */
    @ApiModelProperty(notes = "User's password (mandatory to login before using"
            + " Poseidon). 8 to 20 characters, at least 1 number, 1 upper case"
            + " 1 lower case letter, 1 special character !@#$%^&* and no space",
            required = true)
    @NotBlank(message = "Password is mandatory!")
    @Pattern(regexp = Constants.PASSWORD_REGEXP,
            message = "8 to 20 characters, at least 1 number, 1 upper case &"
                    + " 1 lower case letter, 1 special character !@#$%^&*"
                    + " and no space")
    private String password;

    /**
     * User's full name.
     */
    @ApiModelProperty(notes = "User's Full Name."
            + " Poseidon).", required = true)
    @NotBlank(message = "FullName is mandatory")
    @Size(min = Constants.FULLNAME_MIN_SIZE, max = Constants.FULLNAME_MAX_SIZE,
            message = "Size of username: 7 min to 15 max")
    private String fullname;

    /**
     * User's role.
     */
    @ApiModelProperty(notes = "User's role (mandatory to log in before using"
            + " Poseidon).", required = true)
    @NotBlank(message = "Role is mandatory!")
    private String role;

}
