package com.nnk.springboot.services;

import java.util.List;

import com.nnk.springboot.dto.UserDTO;

public interface UserService {

    List<UserDTO> findAll();

    UserDTO save(UserDTO userDTO);

    UserDTO getById(int id);

    UserDTO delete(int id);

}
