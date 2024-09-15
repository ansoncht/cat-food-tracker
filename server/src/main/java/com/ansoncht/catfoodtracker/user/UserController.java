package com.ansoncht.catfoodtracker.user;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ansoncht.catfoodtracker.user.dto.UserDTO;
import com.ansoncht.catfoodtracker.user.dto.UserLoginDTO;
import com.ansoncht.catfoodtracker.user.dto.UserRegistrationDTO;

@Controller
public class UserController {
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/signup")
    public ResponseEntity<UserDTO> signUp(@Valid @RequestBody UserRegistrationDTO req) {
        logger.info("Signing up user: {}", req.getUsername());

        try {
            UserDTO res = this.userService.registerUser(req);
            if (res != null) {
                logger.info("\u001B[32mUser creation succeeded for: {}\u001B[0m",
                        req.getUsername());

                return ResponseEntity.ok(res);
            } else {
                logger.error("User creation failed for: {}", req.getUsername());

                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            logger.error("Exception in signUp: {}" + e.getMessage());

            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/user/signin")
    public ResponseEntity<UserDTO> signIn(@Valid @RequestBody UserLoginDTO req) {
        logger.info("Signing in user: {}", req.getUsernameOrEmail());

        try {
            UserDTO res = this.userService.authenticateUser(req);
            if (res != null) {
                logger.info("User login succeeded for: {}", req.getUsernameOrEmail());

                return ResponseEntity.ok(res);
            } else {
                logger.error("User login failed for: {}", req.getUsernameOrEmail());

                return ResponseEntity.badRequest().build();
            }

        } catch (Exception e) {
            logger.error("Exception in signIn: {}", e.getMessage());

            return ResponseEntity.internalServerError().build();
        }
    }
}
