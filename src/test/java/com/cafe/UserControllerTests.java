package com.cafe;

import com.cafe.entity.User;
import com.cafe.service.UserService;
import com.cafe.controller.UserController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

public class UserControllerTests {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        
        objectMapper = new ObjectMapper();
    }

@Test
void testLoginUserSuccess() throws Exception {
        User mockUser = new User("testUsername");

        mockUser.setPassword("testPassword");

        when(userService.login("testUsername", "testPassword")).thenReturn(Optional.of(mockUser));

        mockMvc.perform(get("/api/user/login")
                .param("username", "testUsername")
                .param("password", "testPassword"))

                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(mockUser)));
    }

    @Test
    void testLoginUserFailure() throws Exception {

        when(userService.login("wrongUsername", "wrongPassword")).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/user/login")
                .param("username", "wrongUsername")

                .param("password", "wrongPassword"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Username or password incorrect."));
    }

    @Test
    void testGetAllUsers() throws Exception {
        User mockUser = new User("testUsername");
        when(userService.getAllUsers()).thenReturn(Arrays.asList(mockUser));

        mockMvc.perform(get("/api/user"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(mockUser))));
    }

    @Test
    void testAddUser() throws Exception {
        User mockUserInput = new User("testUsername");

        User mockUserOutput = new User("testUsername");


        when(userService.createUser(any(User.class))).thenReturn(mockUserOutput);

        mockMvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockUserInput)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(mockUserOutput)));
    }

    @Test
    void testUpdateUser() throws Exception {
        Long userId = 1L;

        User mockUserInput = new User("testUsername");

        User mockUserOutput = new User("testUsername");

        when(userService.updateUser(eq(userId), any(User.class))).thenReturn(mockUserOutput);

        mockMvc.perform(put("/api/user/" + userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockUserInput)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(mockUserOutput)));
    }

@Test
void testGetUserById() throws Exception {
        Long userId = 1L;

        User mockUser = new User("testUsername");

        when(userService.getUserById(userId)).thenReturn(mockUser);

    mockMvc.perform(get("/api/user/" + userId))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(mockUser)));
    }

@Test
void testDeleteUser() throws Exception {
        Long userId = 1L;
        User mockUser = new User("testUsername");
        when(userService.deleteUser(userId)).thenReturn(mockUser);

    mockMvc.perform(delete("/api/user/" + userId))
                .andExpect(status().isOk())

                .andExpect(content().json(objectMapper.writeValueAsString(mockUser)));
    }

}
