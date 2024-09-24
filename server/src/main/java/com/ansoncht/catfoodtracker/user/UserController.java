package com.ansoncht.catfoodtracker.user;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ansoncht.catfoodtracker.user.dto.UserDTO;
import com.ansoncht.catfoodtracker.user.dto.UserLoginDTO;
import com.ansoncht.catfoodtracker.user.dto.UserRegistrationDTO;

@RestController
public class UserController {
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/v1/user/signup")
    public ResponseEntity<UserDTO> signUp(@Valid @RequestBody UserRegistrationDTO req) {
        logger.info("Signing up user: {}", req.getUsername());

        try {
            UserDTO res = this.userService.registerUser(req);

            logger.info("\u001B[32mUser creation succeeded for: {}\u001B[0m", req.getUsername());

            return ResponseEntity.ok(res);
        } catch (RuntimeException e) {
            logger.error("User creation failed for: {}", req.getUsername());

            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/api/v1/user/signin")
    public ResponseEntity<UserDTO> signIn(@Valid @RequestBody UserLoginDTO req) {
        logger.info("Signing in user: {}", req.getUsernameOrEmail());

        try {
            UserDTO res = this.userService.authenticateUser(req);

            logger.info("User login succeeded for: {}", req.getUsernameOrEmail());

            return ResponseEntity.ok(res);

        } catch (RuntimeException e) {
            logger.error("User login failed for: {}", req.getUsernameOrEmail());

            return ResponseEntity.badRequest().build();
        }
    }
}
