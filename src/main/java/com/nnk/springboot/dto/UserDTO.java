package com.nnk.springboot.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;
import com.nnk.springboot.constants.Constants;

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
public class UserDTO {
    /**
     * Id of the userDTO.
     */
    private Integer id;

    /**
     * Username (mandatory to log in before using Poseidon).
     */
    @NotBlank(message = "Username is mandatory!")
    @Size(min = Constants.USERNAME_MIN_SIZE, max = Constants.USERNAME_MAX_SIZE,
            message = "Size of username: 7 min to 15 max")
    private String username;

    /**
     * User's password (mandatory to log in before using Poseidon).
     */
    @NotBlank(message = "Password is mandatory!")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])"
            + "[a-zA-Z0-9!@#$%^&*](?=\\S+$).{8,15}$" , 
            message=" 8 to 15 characters, at least 1 number, 1 upper case &"
                    + " 1 lower case letter, 1 special character !@#$%^&*"
                    + " and no space")
    private String password;

    /**
     * User's full name.
     */
    @NotBlank(message = "FullName is mandatory")
    @Size(min = Constants.FULLNAME_MIN_SIZE, max = Constants.FULLNAME_MAX_SIZE,
            message = "Size of username: 7 min to 15 max")
    private String fullname;

    /**
     * User's role.
     */
    @NotBlank(message = "Role is mandatory!")
    private String role;

}
