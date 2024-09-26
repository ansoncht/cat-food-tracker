package com.ansoncht.catfoodtracker.user;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.ansoncht.catfoodtracker.security.JwtAuthenticationFilter;
import com.ansoncht.catfoodtracker.user.dto.UserDTO;
import com.ansoncht.catfoodtracker.user.dto.UserLoginDTO;
import com.ansoncht.catfoodtracker.user.dto.UserRegistrationDTO;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {

    private static final LocalDateTime FIXED_TIME = LocalDateTime.of(2024, 1, 1, 12, 59, 59);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSignUp_ValidRequest_ShouldSucceedWithOk() throws Exception {

        UserRegistrationDTO req = new UserRegistrationDTO("test", "test@gmail.com", "test", "test",
                "testPassword");
        UserDTO expected = new UserDTO("1L", "test", "test@gmail.com", "test", "test",
                FIXED_TIME, FIXED_TIME);

        when(userService.registerUser(any(UserRegistrationDTO.class))).thenReturn(expected);

        mockMvc.perform(post("/api/v1/user/signup").contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(expected.getId()))
                .andExpect(jsonPath("$.username").value(expected.getUsername()))
                .andExpect(jsonPath("$.email").value(expected.getEmail()))
                .andExpect(jsonPath("$.firstName").value(expected.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(expected.getLastName()))
                .andExpect(jsonPath("$.createdAt").value(expected.getCreatedAt().toString()))
                .andExpect(jsonPath("$.updatedAt").value(expected.getUpdatedAt().toString()));

        verify(userService).registerUser(any(UserRegistrationDTO.class));
    }

    @Test
    void testSignUp_InvalidRequest_ShouldFailWithBadRequest() throws Exception {

        UserRegistrationDTO req = new UserRegistrationDTO("test", "test@gmail.com", "test", "test", "test");

        mockMvc.perform(post("/api/v1/user/signup").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req))).andExpect(status().isBadRequest());
    }

    @Test
    void testSignUp_ExistingUsername_ShouldFailWithBadRequest() throws Exception {
        UserRegistrationDTO req = new UserRegistrationDTO("test", "test@gmail.com", "test", "test",
                "testPassword");

        when(userService.registerUser(any(UserRegistrationDTO.class)))
                .thenThrow(new RuntimeException("Test RuntimeException"));

        mockMvc.perform(post("/api/v1/user/signup").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req))).andExpect(status().isBadRequest());

        verify(userService).registerUser(any(UserRegistrationDTO.class));
    }

    @Test
    void testSignIn_ValidRequest_ShouldSucceedWithOk() throws Exception {

        UserLoginDTO req = new UserLoginDTO("test", "testPassword");
        UserDTO expected = new UserDTO("1L", "test", "test@gmail.com", "test", "test",
                FIXED_TIME, FIXED_TIME);

        when(userService.authenticateUser(any(UserLoginDTO.class))).thenReturn(expected);

        mockMvc.perform(post("/api/v1/user/signin").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req))).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(expected.getId()))
                .andExpect(jsonPath("$.username").value(expected.getUsername()))
                .andExpect(jsonPath("$.email").value(expected.getEmail()))
                .andExpect(jsonPath("$.firstName").value(expected.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(expected.getLastName()))
                .andExpect(jsonPath("$.createdAt").value(expected.getCreatedAt().toString()))
                .andExpect(jsonPath("$.updatedAt").value(expected.getUpdatedAt().toString()));

        verify(userService).authenticateUser(any(UserLoginDTO.class));
    }

    @Test
    void testSignIn_InvalidRequest_ShouldFailWithBadRequest() throws Exception {
        UserLoginDTO req = new UserLoginDTO("test", "test");

        mockMvc.perform(post("/api/v1/user/signin").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req))).andExpect(status().isBadRequest());
    }

    @Test
    void testSignUp_IncorrectPassword_ShouldFailWithBadRequest() throws Exception {
        UserLoginDTO req = new UserLoginDTO("test", "testPassword");

        when(userService.authenticateUser(any(UserLoginDTO.class)))
                .thenThrow(new RuntimeException("Test RuntimeException"));

        mockMvc.perform(post("/api/v1/user/signin").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req))).andExpect(status().isBadRequest());

        verify(userService).authenticateUser(any(UserLoginDTO.class));
    }
}
