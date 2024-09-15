package com.ansoncht.catfoodtracker.user;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.*;

import static org.springframework.test.util.AssertionErrors.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ansoncht.catfoodtracker.user.dto.UserDTO;
import com.ansoncht.catfoodtracker.user.dto.UserLoginDTO;
import com.ansoncht.catfoodtracker.user.dto.UserRegistrationDTO;

public class UserServiceTest {
    @Mock
    private UserRepository mockUserRepository;

    UserRegistrationDTO testSignUpRequest =
            new UserRegistrationDTO("test", "test@gmail.com", "test", "test", "test");
    UserLoginDTO testSignInRequest = new UserLoginDTO("test@gmail.com", "test");

    private UserService userService;
    private AutoCloseable closeable;

    @BeforeEach
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        userService = new UserService(mockUserRepository);

    }

    @AfterEach
    public void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    public void testRegisterUser_ValidRequest_ShouldSucceed() {
        UserRegistrationDTO req =
                new UserRegistrationDTO("test", "test@gmail.com", "test", "test", "test");
        String encodedPassword = new BCryptPasswordEncoder().encode(req.getPassword());
        User expected = new User("test", "test", "test", "test@gmail.com", encodedPassword);

        when(mockUserRepository.existsByEmail(req.getEmail())).thenReturn(false);
        when(mockUserRepository.existsByUsername(req.getUsername())).thenReturn(false);
        when(mockUserRepository.save(expected)).thenReturn(expected);

        UserDTO actual = userService.registerUser(req);

        assertNotNull(actual);
        assertEquals("Response ID: ", actual.getId(), expected.getId());
        assertEquals("Response Username: ", actual.getUsername(), expected.getUsername());
        assertEquals("Response Email: ", actual.getEmail(), expected.getEmail());
        assertEquals("Response First Name: ", actual.getFirstName(), expected.getFirstName());
        assertEquals("Response Last Name: ", actual.getLastName(), expected.getLastName());

        verify(mockUserRepository).save(Mockito.any(User.class));
    }

    @Test
    public void testRegisterUser_ExistingUsername_ThrowsException() {
        UserRegistrationDTO req =
                new UserRegistrationDTO("test", "test@gmail.com", "test", "test", "test");

        when(mockUserRepository.existsByUsername(req.getUsername())).thenReturn(true);

        assertThrows(RuntimeException.class, () -> userService.registerUser(req));
    }

    @Test
    public void testRegisterUser_ExistingEmail_ThrowsException() {
        UserRegistrationDTO req =
                new UserRegistrationDTO("test", "test@gmail.com", "test", "test", "test");

        when(mockUserRepository.existsByEmail(req.getUsername())).thenReturn(true);

        assertThrows(RuntimeException.class, () -> userService.registerUser(req));
    }

    @Test
    public void testAuthenticateUser_ValidRequest_ShouldSucceed() {
        UserLoginDTO req = new UserLoginDTO("test@gmail.com", "password");
        String encodedPassword = new BCryptPasswordEncoder().encode(req.getPassword());
        User expected = new User("test", "test", "test", "test@gmail.com", encodedPassword);

        when(mockUserRepository.findByUsernameOrEmail(req.getUsernameOrEmail()))
                .thenReturn(Optional.of(expected));

        UserDTO actual = userService.authenticateUser(req);

        assertNotNull(actual);
        assertEquals("Response ID: ", actual.getId(), expected.getId());
        assertEquals("Response Username: ", actual.getUsername(), expected.getUsername());
        assertEquals("Response Email: ", actual.getEmail(), expected.getEmail());
        assertEquals("Response First Name: ", actual.getFirstName(), expected.getFirstName());
        assertEquals("Response Last Name: ", actual.getLastName(), expected.getLastName());

        verify(mockUserRepository).findByUsernameOrEmail(req.getUsernameOrEmail());
    }

    @Test
    public void testAuthenticateUser_PasswordMismatch_ThrowsException() {
        String correctPassword = "correctPassword";
        String incorrectPassword = "wrongPassword";
        String encodedCorrectPassword = new BCryptPasswordEncoder().encode(correctPassword);
        UserLoginDTO req = new UserLoginDTO("test@gmail.com", incorrectPassword);
        User expected = new User("test", "test", "test", "test@gmail.com", encodedCorrectPassword);

        when(mockUserRepository.findByUsernameOrEmail(req.getUsernameOrEmail()))
                .thenReturn(Optional.of(expected));

        assertThrows(RuntimeException.class, () -> userService.authenticateUser(req));

        verify(mockUserRepository).findByUsernameOrEmail(req.getUsernameOrEmail());
    }
}
