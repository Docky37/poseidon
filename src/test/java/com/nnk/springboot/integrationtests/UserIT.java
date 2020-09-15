package com.nnk.springboot.integrationtests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.View;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.dto.UserDTO;
import com.nnk.springboot.exceptions.UserNotFoundException;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.UserService;
import com.nnk.springboot.util.UserRetrieve;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class UserIT {

    static final Logger LOGGER = LoggerFactory.getLogger(UserIT.class);

    @LocalServerPort
    private int port;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mvc;

    @Mock
    private View view;

    @MockBean
    private UserRetrieve userRetrieve;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    UserDTO userDTO;
    static List<User> listOfUser = new ArrayList<>();
    static List<UserDTO> listOfUserDTO = new ArrayList<>();
    static {
        listOfUser.add(new User());
        listOfUser.get(0).setId(1);
        listOfUser.get(0).setFullname("Testeur ADMIN");
        listOfUser.get(0).setUsername("testeur.admin");
        listOfUser.get(0).setPassword("Tadm-231");
        listOfUser.get(0).setRole("ADMIN");
        listOfUser.add(new User());
        listOfUser.get(1).setId(2);
        listOfUser.get(1).setFullname("Testeur USER");
        listOfUser.get(1).setUsername("testeur.user");
        listOfUser.get(1).setPassword("Tuser-123");
        listOfUser.get(1).setRole("USER");

        listOfUserDTO.add(new UserDTO());
        listOfUserDTO.get(0).setId(1);
        listOfUserDTO.get(0).setFullname("Testeur ADMIN");
        listOfUserDTO.get(0).setUsername("testeur.admin");
        listOfUserDTO.get(0).setPassword("Tadm-231");
        listOfUserDTO.get(0).setRole("ADMIN");
        listOfUserDTO.add(new UserDTO());
        listOfUserDTO.get(1).setId(2);
        listOfUserDTO.get(1).setFullname("Testeur USER");
        listOfUserDTO.get(1).setUsername("testeur.user");
        listOfUserDTO.get(1).setPassword("Tuser-123");
        listOfUserDTO.get(1).setRole("USER");
    }

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        userDTO = listOfUserDTO.get(0);
    }

    @AfterEach
    public void tearDrop() {
        userRepository.truncate();
    }

    @Test // GET List
    public void givenExistingUserDTO_whenFindAll_thenListed()
            throws Exception {
        // GIVEN
        given(userRetrieve.getLoggedUser()).willReturn("Testeur");
        // WHEN
        userService.save(listOfUserDTO.get(0));
        userService.save(listOfUserDTO.get(1));
        List<UserDTO> resultList = userService.findAll();
        // THEN
        assertThat(resultList.size()).isEqualTo(2);
        assertThat(resultList.get(0).getUsername())
                .isEqualTo(listOfUserDTO.get(0).getUsername());
        assertThat(resultList.get(1).getUsername())
                .isEqualTo(listOfUserDTO.get(1).getUsername());
    }

    @Test // POST VALIDATE
    public void givenAValidNewUserDTO_whenPost_thenSaved()
            throws Exception {
        // GIVEN
        given(userRetrieve.getLoggedUser()).willReturn("Testeur");
        UserDTO existingUserDTO = userService
                .save(listOfUserDTO.get(0));
        // WHEN
        mvc.perform(MockMvcRequestBuilders.post("/user/validate")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .sessionAttr("userDTO", existingUserDTO)
                .param("id", existingUserDTO.getId().toString())
                .param("username", userDTO.getUsername())
                .param("password", userDTO.getPassword())
                .param("fullname", userDTO.getFullname())
                .param("role", userDTO.getRole())).andDo(print())
               .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(
                        MockMvcResultMatchers.redirectedUrl("/user/list"))
                .andReturn();
        UserDTO userDTOResult = null;
        try {
            userDTOResult = userService
                    .getById(existingUserDTO.getId());
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        // THEN
        assertThat(userDTOResult.toString())
                .isEqualTo(existingUserDTO.toString());
    }

    @Test // POST UPDATE
    public void givenAValidUserDTOToUpdate_whenPost_thenSaved()
            throws Exception {
        // GIVEN
        given(userRetrieve.getLoggedUser()).willReturn("Testeur");
        UserDTO existingUserDTO = userService
                .save(listOfUserDTO.get(0));
        // WHEN
        mvc.perform(MockMvcRequestBuilders
                .post("/user/update/" + existingUserDTO.getId())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .sessionAttr("userDTO", existingUserDTO)
                .param("id", existingUserDTO.getId().toString())
                .param("username", userDTO.getUsername())
                .param("password", "Tadm-231")
                .param("fullname", userDTO.getFullname())
                .param("role", userDTO.getRole())).andDo(print())
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(
                        MockMvcResultMatchers.redirectedUrl("/user/list"))
                .andReturn();
        UserDTO userDTOResult = null;
        try {
            userDTOResult = userService
                    .getById(existingUserDTO.getId());
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        // THEN
        assertThat(userDTOResult.getUsername())
                .isEqualTo(userDTO.getUsername());
        assertThat(userDTOResult.getFullname())
                .isEqualTo(userDTO.getFullname());
        assertThat(userDTOResult.getRole())
                .isEqualTo(userDTO.getRole());
    }

    @Test // DELETE
    public void givenAValidId_whenDelete_thenDeleted()
            throws Exception, UserNotFoundException {
        // GIVEN
        userDTO = listOfUserDTO.get(0);
        given(userRetrieve.getLoggedUser()).willReturn("Testeur");
        UserDTO existingUserDTO = userService
                .save(listOfUserDTO.get(0));
        // WHEN
        UserDTO deletedUserDTO = null;
        try {
            deletedUserDTO = userService
                    .delete(existingUserDTO.getId());
        } catch (UserNotFoundException e) {
            LOGGER.error(" => No User record exist for id={}!",
                    existingUserDTO.getId());
        }

        // THEN
        assertThat(deletedUserDTO.toString())
                .isEqualTo(existingUserDTO.toString());
        assertThrows(UserNotFoundException.class, () -> {
            userService.getById(1);
        });
    }

}
