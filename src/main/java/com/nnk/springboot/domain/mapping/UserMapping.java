package com.nnk.springboot.domain.mapping;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.dto.UserDTO;

/**
 * This class is used to perform bidirectional mapping between a User entity and
 * a UserDTO.
 *
 * @author Thierry Schreiner
 */
@Component
public class UserMapping {

    /**
     * This method is in charge of the mapping of a list of User entities to a
     * list of UserDTO. Use the mapEntityToDTO(User user)as a sub method.
     *
     * @param listOfUser
     * @return a List<UserDTO> object
     */
    public List<UserDTO> mapAListOfUser(final List<User> listOfUser) {
        List<UserDTO> listUserDTO = new ArrayList<>();
        for (User user : listOfUser) {
            UserDTO userDTO = mapEntityToDTO(user);
            listUserDTO.add(userDTO);
        }

        return listUserDTO;
    }

    /**
     * This method is in charge of the mapping of a User entity to a UserDTO.
     *
     * @param user
     * @return a UserDTO object
     */
    public UserDTO mapEntityToDTO(final User user) {
        final UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFullname(user.getFullname());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole());

        return userDTO;
    }

    /**
     * This method is in charge of the mapping of a UserDTO to a User entity.
     *
     * @param userDTO
     * @return a User object
     */
    public User mapDTOToEntity(final UserDTO userDTO) {
        final User user = new User();
        user.setId(userDTO.getId());
        user.setFullname(userDTO.getFullname());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());

        return user;
    }

}
