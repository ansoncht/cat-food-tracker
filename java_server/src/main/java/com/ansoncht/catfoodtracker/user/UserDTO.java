package com.ansoncht.catfoodtracker.user;

import com.ansoncht.catfoodtracker.proto.CreateUserRequest;

import com.ansoncht.catfoodtracker.proto.LoginUserRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserDTO {
    public static SignUpRequest createSignUpRequest(
            String firstName,
            String lastName,
            String username,
            String email,
            String password,
            String confirmedPassword) {
        return new SignUpRequest(firstName, lastName, username, email, password, confirmedPassword);
    }

    public static SignInRequest createSignInRequest(
            String email, String username, String password) {
        return new SignInRequest(email, username, password);
    }

    public static UserDTO.SignUpRequest createUserDTOSignUpRequest(
            CreateUserRequest createUserRequest) {
        return new UserDTO.SignUpRequest(
                createUserRequest.getFirstName(),
                createUserRequest.getLastName(),
                createUserRequest.getUsername(),
                createUserRequest.getEmail(),
                createUserRequest.getPassword(),
                createUserRequest.getConfirmedPassword());
    }

    public static UserDTO.SignInRequest createUserDTOSignInRequest(
            LoginUserRequest loginUserRequest) {
        return new UserDTO.SignInRequest(
                loginUserRequest.getEmail(),
                loginUserRequest.getUsername(),
                loginUserRequest.getPassword());
    }

    public static UserDTO.SignUpResponse createUserDTOSignUpResponse(UserDAO userDAO) {
        return new UserDTO.SignUpResponse(
                userDAO.getId(), userDAO.getEmail(), userDAO.getUsername(), "");
    }

    public static UserDTO.SignInResponse createUserDTOSignInResponse(UserDAO userDAO) {
        return new UserDTO.SignInResponse(
                userDAO.getId(), userDAO.getEmail(), userDAO.getUsername(), "");
    }

    public static class SignInRequest {
        @NotBlank @Email private String email;

        @NotBlank private String username;

        @NotBlank private String password;

        private SignInRequest() {}

        private SignInRequest(String email, String username, String password) {
            this.email = email;
            this.username = username;
            this.password = password;
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

        public String getPassword() {
            return this.password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class SignInResponse {
        @NotBlank private String id;

        @NotBlank private String email;

        @NotBlank private String username;

        @NotBlank private String error;

        private SignInResponse() {}

        private SignInResponse(String id, String email, String username, String error) {
            this.id = id;
            this.email = email;
            this.username = username;
            this.error = error;
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

        public String getError() {
            return this.error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }

    public static class SignUpRequest {
        @NotBlank private String firstName;

        @NotBlank private String lastName;

        @NotBlank private String username;

        @NotBlank @Email private String email;

        @NotBlank private String password;

        @NotBlank private String confirmedPassword;

        private SignUpRequest() {}

        private SignUpRequest(
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

        @NotBlank private String error;

        private SignUpResponse() {}

        private SignUpResponse(String id, String email, String username, String error) {
            this.id = id;
            this.email = email;
            this.username = username;
            this.error = error;
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

        public String getError() {
            return this.error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }
}
