package com.ansoncht.catfoodtracker.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ansoncht.catfoodtracker.user.dto.UserDTO;
import com.ansoncht.catfoodtracker.user.dto.UserLoginDTO;
import com.ansoncht.catfoodtracker.user.dto.UserRegistrationDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
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

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        logger.info("Attempting to load user: {}", usernameOrEmail);

        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> {
                    logger.warn("User not found: {}", usernameOrEmail);

                    return new UsernameNotFoundException("User not found with username or email: " + usernameOrEmail);
                });

        logger.info("User loaded successfully: {}", user.getUsername());

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }

    private String encryptPassword(String rawPassword) {
        logger.debug("Encrypting password");

        return this.passwordEncoder.encode(rawPassword);
    }

    private boolean verifyPassword(String rawPassword, String encodedPassword) {
        logger.debug("Verifying password");

        return this.passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
