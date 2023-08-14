package com.cafe;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.cafe.entity.User;
import com.cafe.exception.NoItemsFoundException;
import com.cafe.repository.UserRepository;
import com.cafe.service.UserServiceImpl;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateUser() {
        User user = new User();
        
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.createUser(user);
        
        assertEquals(user, result);
    }

    @Test
    public void testLoginSuccess() {
        User user = new User();
        user.setUserName("testUser");
        user.setPassword("testPass");

        when(userRepository.findByUserName("testUser")).thenReturn(Optional.of(user));

        Optional<User> result = userService.login("testUser", "testPass");
        assertEquals(user, result.get());
    }

    @Test
    public void testLoginFail() {
        User user = new User();
        user.setUserName("testUser");
        user.setPassword("wrongPass");

        when(userRepository.findByUserName("testUser")).thenReturn(Optional.of(user));

        Optional<User> result = userService.login("testUser", "testPass");
        assertEquals(Optional.empty(), result);
    }

    @Test
    public void testGetUserById() {
        User user = new User();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.getUserById(1L);
        assertEquals(user, result);
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.updateUser(1L, user);
        assertEquals(user, result);
    }
    @Test
    public void testDeleteUser() {
        User user = new User();
        Long userId = 1L;
        user.setUserId(userId); 

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User result = userService.deleteUser(userId);
        
        verify(userRepository).findById(userId);
        verify(userRepository).deleteById(userId); 
        
        assertEquals(user, result);
    }
    @Test
    public void testGetAllUsers() {
        User user1 = new User();
        User user2 = new User();

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<User> results = userService.getAllUsers();
        assertEquals(2, results.size());
    }

    @Test
    public void testGetAllUsersEmpty() {
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        Exception exception = assertThrows(NoItemsFoundException.class, () -> {
            userService.getAllUsers();
        });

        assertEquals("No Users found.", exception.getMessage());
    }
}
