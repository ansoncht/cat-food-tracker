package com.ansoncht.catfoodtracker.user;

import static com.ansoncht.catfoodtracker.user.UserDTO.createSignInRequest;
import static com.ansoncht.catfoodtracker.user.UserDTO.createSignUpRequest;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Objects;

public class UserServiceTest {

    UserDTO.SignUpRequest testSignUpRequest =
            createSignUpRequest("test", "test", "test", "test@gmail.com", "test", "test");

    UserDTO.SignInRequest testSignInRequest =
            createSignInRequest("test@gmail.com", "test", "test");

    @Mock private UserRepository mockUserRepository;

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
    public void testRestRegisterUser() throws Exception {
        System.out.println("---testRegisterUser() is invoked---");

        UserDAO expectedUserDAO = UserDAO.createUserDAO("test", "test", "test", "test@gmail.com", "test");

        when(mockUserRepository.save(Mockito.any(UserDAO.class))).thenReturn(expectedUserDAO);

        UserDTO.SignUpResponse signUpResponse = userService.registerUser(testSignUpRequest);

        verify(mockUserRepository).save(Mockito.any(UserDAO.class));

        assert signUpResponse != null;
        assertEquals("id: ", signUpResponse.getId(), expectedUserDAO.getId());
        assertEquals("id: ", signUpResponse.getEmail(), expectedUserDAO.getEmail());
        assertEquals("id: ", signUpResponse.getUsername(), expectedUserDAO.getUsername());
    }

//    @Test
//    public void testLoginUser() {
//        System.out.println("Running testSigninUser");
//
//        UserDAO expectedUserDAO = new UserDAO("test", "test", "test", "test@gmail.com", "test");
//
//        when(mockUserRepository.findByEmail(Mockito.any(String.class))).thenReturn(expectedUserDAO);
//
//        UserDAO foundUser = userService.loginUser(email);
//
//        verify(mockUserRepository).findByEmail(email);
//        assert foundUser != null;
//        // Additional assertions based on your application logic
//    }
}
