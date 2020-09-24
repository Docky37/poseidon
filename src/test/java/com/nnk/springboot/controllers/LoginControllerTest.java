package com.nnk.springboot.controllers;

import static org.junit.jupiter.api.Assertions.assertThrows;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.View;
import org.springframework.web.util.NestedServletException;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.dto.UserDTO;
import com.nnk.springboot.exceptions.UserNotFoundException;
import com.nnk.springboot.security.model.AuthenticationRequest;
import com.nnk.springboot.security.util.JwtUtil;
import com.nnk.springboot.services.UserDetailsServiceImpl;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class LoginControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mvc;

    @Mock
    private View view;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @MockBean
    private AuthenticationManager authenticationManager;

    UserDTO userDTO;

    @BeforeEach
    public void setup() {

        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test // GET login form
    public void whenGetLoginForm_thenDisplayLoginForm() throws Exception {
        // GIVEN
        // WHEN
        mvc.perform(MockMvcRequestBuilders.get("/login")).andDo(print())
                .andExpect(MockMvcResultMatchers.model()
                        .attributeExists("authenticationRequest"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        // THEN

    }

    @Test // AUTHENTICATE
    public void givenValidCredentials_whenPost_thenAuthenticate()
            throws Exception {
        // GIVEN
        AuthenticationRequest authenticationRequest = new AuthenticationRequest(
                "thierry.schreiner", "Tadm-123");
        User user = new User(1, "thierry.schreiner",
                "$2a$10$/Oa835whQGgbbHEl4lyyDe0IWlREZb69.ikdiCzUco81yOwx3W07m",
                "Thierry SCHREINER", "ADMIN");
        given(userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername()))
                        .willReturn(user);
        given(jwtUtil.generateToken(user)).willReturn("eyJhbGciOiJIUzI1NiJ9."
                + "eyJzdWIiOiJ0aGllcnJ5LnNjaHJlaW5lciIsImV4cCI6MTYwMDk1MzkxMCwiaWF0IjoxNjAwOTUwMzEwfQ.jWDSoqv-tSKbBDCckIo4mc1piXdkcObdpSX0QchQcTc");
        // WHEN
        mvc.perform(MockMvcRequestBuilders.post("/authenticate")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .sessionAttr("userDTO", authenticationRequest)
                .param("username", authenticationRequest.getUsername())
                .param("password", authenticationRequest.getPassword()))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/bidList/list"))
                .andReturn();
        // THEN
        verify(userDetailsService)
                .loadUserByUsername(authenticationRequest.getUsername());
        verify(jwtUtil).generateToken(user);

    }

    @Test // AUTHENTICATE
    public void givenInvalidCredentials_whenPost_thenError() throws Exception {
        // GIVEN
        AuthenticationRequest authenticationRequest = new AuthenticationRequest(
                "thierry.schreiner", "PasW-459");
        given(authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword())))
                                .willThrow(BadCredentialsException.class);
        // WHEN - THEN
        assertThrows(NestedServletException.class,
                () -> mvc
                        .perform(MockMvcRequestBuilders.post("/authenticate")
                                .contentType(
                                        MediaType.APPLICATION_FORM_URLENCODED)
                                .sessionAttr("userDTO", authenticationRequest)
                                .param("username",
                                        authenticationRequest.getUsername())
                                .param("password",
                                        authenticationRequest.getPassword()))
                        .andDo(print()).andReturn());
    }

}
