package com.nnk.springboot.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.domain.mapping.UserMapping;
import com.nnk.springboot.dto.UserDTO;
import com.nnk.springboot.exceptions.UserNotFoundException;
import com.nnk.springboot.repositories.UserRepository;

/**
 * Implementation class of the UserService interface, this class answer to
 * UserController request using UserRepository and UserMapping classes.
 *
 * @author Thierry Schreiner
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * UserRepository bean injected by Spring when service is created.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * UserMapping bean injected by Spring when service is created.
     */
    @Autowired
    private UserMapping userMapping;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserDTO> findAll() {
        List<User> listUser = new ArrayList<>();
        listUser = userRepository.findAll();

        return userMapping.mapAListOfUser(listUser);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDTO save(final UserDTO userDTO) {
        User user = userMapping.mapDTOToEntity(userDTO);
        System.out.println(user.toString());
        User savedUser = userRepository.save(user);
        System.out.println(savedUser.toString());
        UserDTO savedBidListDTO = userMapping
                .mapEntityToDTO(savedUser);
        System.out.println(savedBidListDTO.toString());
        return savedBidListDTO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDTO getById(final int id) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(
                        "No User record exist for given id"));

        return userMapping.mapEntityToDTO(user);
     }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDTO delete(final int id) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(
                        "No User record exist for given id"));
        userRepository.deleteById(id);

        return userMapping.mapEntityToDTO(user);
    }

}
