package com.ansoncht.catfoodtracker.user;

import static com.ansoncht.catfoodtracker.user.UserDTO.createSignInRequest;
import static com.ansoncht.catfoodtracker.user.UserDTO.createSignUpRequest;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserServiceTest {

    private final Logger LOGGER = LoggerFactory.getLogger(UserServiceTest.class);
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    UserDTO.SignUpRequest testSignUpRequest =
            createSignUpRequest("test", "test", "test", "test@gmail.com", "test", "test");
    UserDTO.SignInRequest testSignInRequest = createSignInRequest("test@gmail.com", "test", "test");
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
    public void testRegisterUser_ValidRequest_ShouldSucceed() throws Exception {
        LOGGER.info("testRegisterUser_ValidRequest_ShouldSucceed() is invoked");

        UserDAO expectedUserDAO =
                UserDAO.createUserDAO("test", "test", "test", "test@gmail.com", "test");

        when(mockUserRepository.save(Mockito.any(UserDAO.class))).thenReturn(expectedUserDAO);

        UserDTO.SignUpResponse signUpResponse = userService.registerUser(testSignUpRequest);

        verify(mockUserRepository).save(Mockito.any(UserDAO.class));

        assert signUpResponse != null;
        assertEquals("Expected ID: ", signUpResponse.getId(), expectedUserDAO.getId());
        assertEquals("Expected Email: ", signUpResponse.getEmail(), expectedUserDAO.getEmail());
        assertEquals(
                "Expected Username: ", signUpResponse.getUsername(), expectedUserDAO.getUsername());
    }

    @Test
    public void testRegisterUser_ExistingEmail_ThrowsException() {
        LOGGER.info("testRegisterUser_ExistingEmail_ThrowsException() is invoked");

        UserDAO existingUser =
                UserDAO.createUserDAO("test", "test", "test", "test@gmail.com", "test");
        when(mockUserRepository.findByUsername(Mockito.anyString())).thenReturn(existingUser);
        when(mockUserRepository.findByEmail(Mockito.anyString())).thenReturn(existingUser);

        try {
            userService.registerUser(testSignUpRequest);
            fail("exception expected");
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    public void testLoginUser_ValidRequest_ShouldSucceed() throws Exception {
        LOGGER.info("testLoginUser_ValidRequest_ShouldSucceed() is invoked");

        String encryptedPassword = encoder.encode("test");
        UserDAO expectedUserDAO =
                UserDAO.createUserDAO("test", "test", "test", "test@gmail.com", encryptedPassword);

        when(mockUserRepository.findByEmail(Mockito.any(String.class))).thenReturn(expectedUserDAO);

        UserDTO.SignInResponse signInResponse = userService.loginUser(testSignInRequest);

        verify(mockUserRepository).findByEmail(Mockito.any(String.class));

        assert signInResponse != null;
        assertEquals("Expected ID: ", signInResponse.getId(), expectedUserDAO.getId());
        assertEquals("Expected Email: ", signInResponse.getEmail(), expectedUserDAO.getEmail());
        assertEquals(
                "Expected Username: ", signInResponse.getUsername(), expectedUserDAO.getUsername());
    }

    @Test
    public void testLoginUser_PasswordMismatch_ThrowsException() {
        LOGGER.info("testRegisterUser_ExistingEmail_ThrowsException() is invoked");

        UserDAO existingUser =
                UserDAO.createUserDAO("test", "test", "test", "test@gmail.com", "test");
        when(mockUserRepository.findByUsername(Mockito.anyString())).thenReturn(existingUser);

        try {
            userService.loginUser(testSignInRequest);
            fail("exception expected");
        } catch (Exception e) {
            assertNotNull(e);
        }
    }
}
