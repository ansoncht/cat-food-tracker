package com.ansoncht.catfoodtracker.user;

import com.ansoncht.catfoodtracker.proto.CreateUserRequest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserDTO {
    public static class SignInRequest {
        @NotBlank @Email private String email;

        @NotBlank private String password;

        public String getEmail() {
            return this.email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return this.password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public SignInRequest() {}

        public SignInRequest(String email, String password) {
            this.email = email;
            this.password = password;
        }
    }

    public static class SignUpRequest {
        @NotBlank private String firstName;

        @NotBlank private String lastName;

        @NotBlank private String username;

        @NotBlank @Email private String email;

        @NotBlank private String password;

        @NotBlank private String confirmedPassword;

        public SignUpRequest() {}

        public SignUpRequest(
                String firstName,
                String lastName,
                String username,
                String email,
                String password,
                String confirmedPassword) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.username = username;
            this.email = email;
            this.password = password;
            this.confirmedPassword = confirmedPassword;
        }

        public String getFirstName() {
            return this.firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return this.lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getUsername() {
            return this.username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return this.email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return this.password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getConfirmedPassword() {
            return this.confirmedPassword;
        }

        public void setConfirmedPassword(String confirmedPassword) {
            this.confirmedPassword = confirmedPassword;
        }

        public CreateUserRequest convertToCreateUserRequest() {
            return CreateUserRequest.newBuilder()
                    .setEmail(this.getEmail())
                    .setPassword(this.getPassword())
                    .setUsername(this.getUsername())
                    .build();
        }
    }

    public static class SignUpResponse {
        @NotBlank private String id;

        @NotBlank private String email;

        @NotBlank private String username;

        public SignUpResponse() {}

        public SignUpResponse(String id, String email, String username) {
            this.id = id;
            this.email = email;
            this.username = username;
        }

        public String getId() {
            return this.id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEmail() {
            return this.email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUsername() {
            return this.username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
