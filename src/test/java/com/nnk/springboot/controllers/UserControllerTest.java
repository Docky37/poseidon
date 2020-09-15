package com.nnk.springboot.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.nnk.springboot.dto.UserDTO;
import com.nnk.springboot.exceptions.UserNotFoundException;
import com.nnk.springboot.services.UserService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mvc;

    @Mock
    private View view;

    @MockBean
    private UserService userService;

    UserDTO userDTO;

    @BeforeEach
    public void setup() {
        userDTO = new UserDTO(1, "testeur.admin", "PasW-123", "Testeur ADMIN",
                "ADMIN");
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test // GET LIST
    public void whenGetListPage_thenDisplayListPage() throws Exception {
        // GIVEN
        List<UserDTO> list = new ArrayList<UserDTO>();
        list.add(userDTO);
        given(userService.findAll()).willReturn(list);
        // WHEN
        mvc.perform(MockMvcRequestBuilders.get("/user/list")).andDo(print())
                .andExpect(MockMvcResultMatchers.model().attribute("users",
                        list))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        // THEN
        verify(userService).findAll();
    }

    @Test // GET ADD
    public void whenGetAddPage_thenDisplayAddPage() throws Exception {
        // GIVEN

        // WHEN - THEN
        mvc.perform(MockMvcRequestBuilders.get("/user/add")).andDo(print())
                .andExpect(MockMvcResultMatchers.model()
                        .attributeExists("userDTO"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

    }

    @Test // POST VALIDATE
    public void givenAValidUserDTO_whenPost_thenSaved() throws Exception {
        // GIVEN
        given(userService.save(any(UserDTO.class)))
                .willReturn(userDTO);
        // WHEN
        mvc.perform(MockMvcRequestBuilders.post("/user/validate")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .sessionAttr("userDTO", userDTO)
                .param("id", userDTO.getId().toString())
                .param("username", userDTO.getUsername())
                .param("password", userDTO.getPassword())
                .param("fullname", userDTO.getFullname())
                .param("role", userDTO.getRole())).andDo(print())
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(
                        MockMvcResultMatchers.redirectedUrl("/user/list"))
                .andReturn();
        // THEN
        verify(userService).save(any(UserDTO.class));
    }

    @Test // GET UPDATE
    public void whenGetUpdatePage_thenDisplayUpdatePage()
            throws Exception, UserNotFoundException {
        // GIVEN
        given(userService.getById(1)).willReturn(userDTO);
        // WHEN
        mvc.perform(MockMvcRequestBuilders.get("/user/update/1"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.model()
                        .attributeExists("userDTO"))
                .andExpect(MockMvcResultMatchers.view().name("user/update"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        // THEN
    }

    @Test // GET UPDATE WITH EXCEPTION
    public void givenAnUnknownId_whenGetUpdatePage_thenRedirectToListPage()
            throws Exception, UserNotFoundException {
        // GIVEN
        given(userService.getById(7))
                .willThrow(UserNotFoundException.class);
        // WHEN
        mvc.perform(MockMvcRequestBuilders.get("/user/update/7"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.view()
                        .name("redirect:/user/list"))
                .andExpect(MockMvcResultMatchers.status().is(302)).andReturn();
        // THEN
    }

    @Test // POST UPDATE
    public void givenAValidUserDTOToUpdate_whenPost_thenSaved()
            throws Exception {
        // GIVEN
        given(userService.save(any(UserDTO.class)))
                .willReturn(userDTO);
        // WHEN
        mvc.perform(MockMvcRequestBuilders.post("/user/update/1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .sessionAttr("userDTO", userDTO)
                .param("id", userDTO.getId().toString())
                .param("username", userDTO.getUsername())
                .param("password", userDTO.getPassword())
                .param("fullname", userDTO.getFullname())
                .param("role", userDTO.getRole())).andDo(print())
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(
                        MockMvcResultMatchers.redirectedUrl("/user/list"))
                .andReturn();
        // THEN
        verify(userService).save(any(UserDTO.class));
    }

    @Test // DELETE
    public void givenAValidId_whenDelete_thenDeleteAndRedirectToListPage()
            throws Exception, UserNotFoundException {
        // GIVEN
        given(userService.delete(1)).willReturn(userDTO);
        // WHEN
        mvc.perform(MockMvcRequestBuilders.get("/user/delete/1"))
                .andDo(print())
                .andExpect(
                        MockMvcResultMatchers.redirectedUrl("/user/list"))
                .andReturn();
        // THEN
        verify(userService).delete(1);
    }

    @Test // DELETE WITH EXCEPTION
    public void givenUnknownId_whenDelete_thenRedirectToListPage()
            throws Exception, UserNotFoundException {
        // GIVEN
        given(userService.delete(7))
                .willThrow(UserNotFoundException.class);
        // WHEN
        mvc.perform(MockMvcRequestBuilders.get("/user/delete/7"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.view()
                        .name("redirect:/user/list"))
                .andExpect(MockMvcResultMatchers.status().is(302)).andReturn();
        // THEN
        verify(userService).delete(7);
    }

}
