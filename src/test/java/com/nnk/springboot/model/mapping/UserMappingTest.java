package com.nnk.springboot.model.mapping;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.domain.mapping.UserMapping;
import com.nnk.springboot.dto.UserDTO;

@SpringBootTest
public class UserMappingTest {

    @Autowired
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
        listOfUser.get(1).setId(2);
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
        listOfUserDTO.get(1).setId(2);
        listOfUserDTO.get(1).setFullname("Testeur USER");
        listOfUserDTO.get(1).setUsername("testeur.user");
        listOfUserDTO.get(1).setPassword("1231231");
        listOfUserDTO.get(1).setRole("USER");
    }
    
    @BeforeEach
    public void setup() {
        listOfUser.get(0).setPassword("1231231");
    }

    @Test
    public void givenAListOfUser_whenMapToDTO_thenReturnsAListOfUserDTO() {
        // GIVEN
        // WHEN
        List<UserDTO> resultList = userMapping
                .mapAListOfUser(listOfUser);
        // THEN
        assertThat(resultList.toString())
                .isEqualTo(listOfUserDTO.toString());

    }

    @Test
    public void givenAUserDTO_whenMapToEntity_thenReturnsUser() {
        // GIVEN
        // WHEN
        User result = userMapping
                .mapDTOToEntity(listOfUserDTO.get(0));
        // THEN
        listOfUser.get(0).setPassword(result.getPassword());
        assertThat(result.toString())
                .isEqualTo(listOfUser.get(0).toString());

    }

}
