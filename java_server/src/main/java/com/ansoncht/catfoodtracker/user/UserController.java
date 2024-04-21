package com.ansoncht.catfoodtracker.user;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/signup")
    public UserDTO.SignUpResponse signUp(@Valid @RequestBody UserDTO.SignUpRequest signUpRequest) {
        System.out.println("calling REST signUp()");
        System.out.println("user creation with: " + signUpRequest.getUsername());

        UserDTO.SignUpResponse signUpResponse = this.userService.registerUser(signUpRequest);
        if (signUpResponse != null) {
            System.out.println("\u001B[32m" + "user creation succeeded with: " + signUpRequest.getUsername() + "\u001B[0m");
            return signUpResponse;
        }

        System.err.println("user creation failed with: " + signUpRequest.getUsername());

        return null;
    }

    //    @PostMapping("/user/signin")
    //    public String signIn(@Valid @RequestBody UserDTO.SignInRequest signInRequest) {
    //        System.out.println("Logging in user with " + signInRequest.getEmail());
    //        CreateUserRequest request = CreateUserRequest.newBuilder()
    //                .setUsername("john_doe")
    //                .setEmail("john@example.com")
    //                .setPassword("secretpassword")
    //                .build();
    // UserDAO signedUpUserDAO = this.userService.loginUser(signInRequest.getEmail());
    // return signedUpUserDAO.getEmail();
    //        return "hi";
    //    }
}
