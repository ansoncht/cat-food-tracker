package com.ansoncht.catfoodtracker.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Mock
    private UserRepository mockUserRepository;

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
    public void testRegisterUser() {
        System.out.println("Running testRegisterUser");
        UserDTO userDTO = new UserDTO();
        UserDAO userDAO = new UserDAO();
        when(mockUserRepository.save(userDAO)).thenReturn(userDAO);

        UserDAO savedUser = userService.registerUser(userDTO);

        verify(mockUserRepository).save(userDAO);
        assert savedUser != null;
        // Additional assertions based on your application logic
    }

    @Test
    public void testLoginUser() {
        System.out.println("Running testSigninUser");
        String email = "test@example.com";
        UserDAO userDAO = new UserDAO();
        when(mockUserRepository.findByEmail(email)).thenReturn(userDAO);

        UserDAO foundUser = userService.loginUser(email);

        verify(mockUserRepository).findByEmail(email);
        assert foundUser != null;
        // Additional assertions based on your application logic
    }
}
