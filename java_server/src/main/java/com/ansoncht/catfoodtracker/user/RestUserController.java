package com.ansoncht.catfoodtracker.user;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestUserController {

    private final UserService userService;
    private final Logger LOGGER = LoggerFactory.getLogger(RestUserController.class);

    public RestUserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/signup")
    public ResponseEntity<UserDTO.SignUpResponse> signUp(
            @Valid @RequestBody UserDTO.SignUpRequest signUpRequest) {
        try {
            LOGGER.info("---signUp() is invoked---");
            LOGGER.info("user creation with: " + signUpRequest.getUsername());

            UserDTO.SignUpResponse signUpResponse = this.userService.registerUser(signUpRequest);
            if (signUpResponse != null) {
                LOGGER.info(
                        "\u001B[32m"
                                + "user creation succeeded with: "
                                + signUpRequest.getUsername()
                                + "\u001B[0m");

                return ResponseEntity.ok(signUpResponse);
            } else {
                LOGGER.error("user creation failed for: " + signUpRequest.getUsername());

                throw new Exception();
            }

        } catch (Exception e) {
            LOGGER.error("exception in signUp(): " + e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/user/signin")
    public ResponseEntity<UserDTO.SignInResponse> signIn(@Valid @RequestBody UserDTO.SignInRequest signInRequest) {
        try {
            LOGGER.info("---signIn() is invoked---");
            LOGGER.info(
                    String.format(
                            "user login with email: %s; username: %s",
                            signInRequest.getEmail(), signInRequest.getUsername()));

            UserDTO.SignInResponse signInResponse = this.userService.loginUser(signInRequest);
            if (signInResponse != null) {
                LOGGER.info(
                        "\u001B[32m"
                                + "user login succeeded with: "
                                + signInRequest.getUsername()
                                + "\u001B[0m");

                return ResponseEntity.ok(signInResponse);
            } else {
                LOGGER.error("user login failed for: " + signInRequest.getUsername());

                throw new Exception();
            }

        } catch (Exception e) {
            LOGGER.error("exception in signUp(): " + e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
