package com.ansoncht.catfoodtracker.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final Logger LOGGER = LoggerFactory.getLogger(RestUserController.class);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    public UserDTO.SignUpResponse registerUser(UserDTO.SignUpRequest userDTO) throws Exception {
        try {
            LOGGER.info("---registerUser() is invoked---");
            LOGGER.info("user creation with: " + userDTO.getUsername());
            LOGGER.debug(String.format("first name: %s; last name %s; username: %s; email: %s",
                    userDTO.getFirstName(),
                    userDTO.getFirstName(),
                    userDTO.getUsername(),
                    userDTO.getEmail()
            ));


            if (userExists(userDTO.getUsername(), userDTO.getEmail())) {
                LOGGER.error("user with the same username or email already exists");

                throw new Exception("user with the same username or email already exists");
            }

            UserDAO userDAO = UserDAO.createUserDAO(userDTO);
            userDAO.setPassword(encryptPassword(userDTO.getPassword()));
            UserDAO createdUser = this.userRepository.save(userDAO);

            return UserDTO.createUserDTOSignUpResponse(createdUser);
        } catch (Exception e) {
            LOGGER.error("exception in registerUser()" + e.getMessage());

            throw new Exception("error creating user: " + e.getMessage(), e);
        }
    }

    public UserDTO.SignInResponse loginUser(UserDTO.SignInRequest userDTO) throws Exception {
        try {
            LOGGER.info("---loginUser() is invoked---");
            LOGGER.info("user login with: " + userDTO.getEmail());
            LOGGER.debug(String.format("username: %s; email: %s",
                    userDTO.getUsername(),
                    userDTO.getEmail()
            ));

            UserDAO foundUser = this.userRepository.findByEmail(userDTO.getEmail());
            if (foundUser != null && decryptPassword(userDTO.getPassword(), foundUser.getPassword())) {
                return UserDTO.createUserDTOSignInResponse(foundUser);
            } else {
                LOGGER.error("invalid email or password");

                throw new Exception("invalid email or password");
            }
        } catch (Exception e) {
            LOGGER.error("exception in loginUser(): " + e.getMessage());

            throw new Exception("error logging in user: " + e.getMessage(), e);
        }
    }

    private String encryptPassword(String password) {
        LOGGER.info("---encryptPassword() is invoked---");

        return this.bCryptPasswordEncoder.encode(password);
    }

    private boolean decryptPassword(String input, String saved) {
        LOGGER.info("---decryptPassword() is invoked---");

        return this.bCryptPasswordEncoder.matches(input, saved);
    }

    private boolean userExists(String username, String email) {
        LOGGER.info("---userExists() is invoked---");

        UserDAO usernameUser = userRepository.findByUsername(username);
        UserDAO emailUser = userRepository.findByEmail(email);

        return usernameUser != null || emailUser != null;
    }
}
