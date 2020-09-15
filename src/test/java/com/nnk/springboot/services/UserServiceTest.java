package com.nnk.springboot.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.dto.UserDTO;
import com.nnk.springboot.exceptions.UserNotFoundException;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.UserService;
import com.nnk.springboot.services.UserServiceImpl;

@SpringJUnitConfig(value = UserServiceImpl.class)
public class UserServiceTest {

    @Autowired
    UserService userService;

    @MockBean
    UserRepository userRepository;

    @MockBean
    UserMapping userMapping;

    static List<User> listOfUser = new ArrayList<>();
    static List<UserDTO> listOfUserDTO = new ArrayList<>();
    static {
        listOfUser.add(new User());
        listOfUser.get(0).setId(1);
        listOfUser.get(0).setFullname("Testeur ADMIN");
        listOfUser.get(0).setUsername("testeur.admin");
        listOfUser.get(0).setPassword("1231231");
        listOfUser.get(0).setRole("ADMIN");
        listOfUser.add(new User());
        listOfUser.get(1).setId(1);
        listOfUser.get(1).setFullname("Testeur USER");
        listOfUser.get(1).setUsername("testeur.user");
        listOfUser.get(1).setPassword("1231231");
        listOfUser.get(1).setRole("USER");

        listOfUserDTO.add(new UserDTO());
        listOfUserDTO.get(0).setId(1);
        listOfUserDTO.get(0).setFullname("Testeur ADMIN");
        listOfUserDTO.get(0).setUsername("testeur.admin");
        listOfUserDTO.get(0).setPassword("1231231");
        listOfUserDTO.get(0).setRole("ADMIN");
        listOfUserDTO.add(new UserDTO());
        listOfUserDTO.get(1).setId(1);
        listOfUserDTO.get(1).setFullname("Testeur USER");
        listOfUserDTO.get(1).setUsername("testeur.user");
        listOfUserDTO.get(1).setPassword("1231231");
        listOfUserDTO.get(1).setRole("USER");
    }

    @Test
    public void whenFindAll_thenReturnsListOfAllUsers() {
        // GIVEN
        given(userRepository.findAll()).willReturn(listOfUser);
        given(userMapping.mapAListOfUser(listOfUser))
                .willReturn(listOfUserDTO);
        // WHEN
        List<UserDTO> resultList = userService.findAll();
        // THEN
        assertThat(resultList.toString())
                .isEqualTo(listOfUserDTO.toString());
    }

    @Test
    public void givenAUserDTO_whenSave_thenReturnsSavedObject() {
        // GIVEN
        given(userMapping.mapDTOToEntity(listOfUserDTO.get(0)))
                .willReturn(listOfUser.get(0));
        given(userRepository.save(listOfUser.get(0)))
                .willReturn(listOfUser.get(0));
        given(userMapping.mapEntityToDTO(any(User.class)))
                .willReturn(listOfUserDTO.get(0));
        // WHEN
        UserDTO result = userService
                .save(listOfUserDTO.get(0));
        // THEN
        assertThat(result.toString())
                .isEqualTo(listOfUserDTO.get(0).toString());
    }

    @Test
    public void whenGetById_thenReturnsExpectedUser()
            throws UserNotFoundException {
        // GIVEN
        given(userRepository.findById(2))
                .willReturn(Optional.of(listOfUser.get(1)));
        given(userMapping.mapEntityToDTO(any(User.class)))
                .willReturn(listOfUserDTO.get(1));
        // WHEN
        UserDTO result = userService.getById(2);
        // THEN
        System.out.println(result);
        assertThat(result.toString())
                .isEqualTo(listOfUserDTO.get(1).toString());
    }

    @Test
    public void givenAnUnknownId_whenGetById_thenUserNotFoundException()
            throws UserNotFoundException {
        // GIVEN
        // WHEN - THEN
        assertThrows(UserNotFoundException.class, () -> {
            userService.getById(3);
        });
    }

    @Test
    public void givenAUserDTO_whenDelete_thenReturnsDeletedUser()
            throws UserNotFoundException {
        // GIVEN
        listOfUser.add(new User());
        listOfUser.get(2).setId(3);
        listOfUser.get(2).setFullname("Testeur USER3");
        listOfUser.get(2).setUsername("testeur.user3");
        listOfUser.get(2).setPassword("1231231");
        listOfUser.get(2).setRole("USER");

        listOfUserDTO.add(new UserDTO());
        listOfUserDTO.get(0).setId(1);
        listOfUserDTO.get(2).setFullname("Testeur USER3");
        listOfUserDTO.get(2).setUsername("testeur.user3");
        listOfUserDTO.get(2).setPassword("1231231");
        listOfUserDTO.get(2).setRole("USER");
 
        given(userRepository.findById(3))
        .willReturn(Optional.of(listOfUser.get(2)));
        given(userMapping.mapEntityToDTO(any(User.class)))
                .willReturn(listOfUserDTO.get(2));
        // WHEN
        UserDTO result = userService.delete(3);
        // THEN
        assertThat(result.toString())
                .isEqualTo(listOfUserDTO.get(2).toString());
    }

}
