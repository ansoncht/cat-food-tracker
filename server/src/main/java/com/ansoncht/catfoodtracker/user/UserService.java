package com.ansoncht.catfoodtracker.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ansoncht.catfoodtracker.user.dto.UserDTO;
import com.ansoncht.catfoodtracker.user.dto.UserLoginDTO;
import com.ansoncht.catfoodtracker.user.dto.UserRegistrationDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    public UserDTO registerUser(UserRegistrationDTO userRegistrationDTO) {
        logger.info("Attempting to register new user: {}", userRegistrationDTO.getUsername());

        if (userRepository.existsByUsername(userRegistrationDTO.getUsername())) {
            logger.warn("Registration failed: Username already exists: {}",
                    userRegistrationDTO.getUsername());
            throw new RuntimeException("Username already exists");
        }

        if (userRepository.existsByEmail(userRegistrationDTO.getEmail())) {
            logger.warn("Registration failed: Email already exists: {}",
                    userRegistrationDTO.getEmail());
            throw new RuntimeException("Email already exists");
        }

        String encryptedPassword = encryptPassword(userRegistrationDTO.getPassword());
        User user = userRegistrationDTO.toUser(encryptedPassword);
        User savedUser = userRepository.save(user);

        logger.info("User registered successfully: {}", savedUser.getUsername());

        return UserDTO.fromUser(savedUser);
    }

    public UserDTO authenticateUser(UserLoginDTO userLoginDTO) {
        logger.info("Attempting login for user: {}", userLoginDTO.getUsernameOrEmail());

        User user = this.userRepository.findByUsernameOrEmail(userLoginDTO.getUsernameOrEmail(),
                userLoginDTO.getUsernameOrEmail()).orElseThrow(() -> {
                    logger.warn("Login failed: User not found: {}",
                            userLoginDTO.getUsernameOrEmail());

                    return new RuntimeException("Invalid username or email");
                });

        if (!verifyPassword(userLoginDTO.getPassword(), user.getPassword())) {
            logger.warn("Login failed: Incorrect password for user: {}",
                    userLoginDTO.getUsernameOrEmail());

            throw new RuntimeException("Invalid password");
        }

        logger.info("User authenticated successfully: {}", userLoginDTO.getUsernameOrEmail());

        return UserDTO.fromUser(user);

    }

    private String encryptPassword(String rawPassword) {
        logger.debug("Encrypting password");

        return this.bCryptPasswordEncoder.encode(rawPassword);
    }

    private boolean verifyPassword(String rawPassword, String encodedPassword) {
        logger.debug("Verifying password");

        return this.bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
    }
}
