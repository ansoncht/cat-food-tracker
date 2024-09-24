package com.ansoncht.catfoodtracker.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

import com.ansoncht.catfoodtracker.user.User;

public class UserRegistrationDTO {
    @NotBlank
    @Size(max = 50)
    private String username;

    @NotBlank
    @Email
    @Size(max = 100)
    private String email;

    @NotBlank
    @Size(max = 50)
    private String firstName;

    @NotBlank
    @Size(max = 50)
    private String lastName;

    @NotBlank
    @Size(min = 8, max = 100)
    private String password;

    public UserRegistrationDTO() {}

    public UserRegistrationDTO(String username, String email, String firstName, String lastName,
            String password) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public User toUser(String encryptedPassword) {
        return new User(this.username, this.email, this.firstName, this.lastName,
                encryptedPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.username, this.email);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof UserRegistrationDTO))
            return false;
        UserRegistrationDTO other = (UserRegistrationDTO) obj;
        return Objects.equals(this.username, other.username)
                && Objects.equals(this.email, other.email);
    }

    @Override
    public String toString() {
        return "UserRegistrationDTO{" + "username='" + ", email='" + this.email + '\''
                + this.username + '\'' + ", firstName='" + this.firstName + '\'' + ", lastName='"
                + this.lastName + '\'' + ", password='[PROTECTED]'" + '}';
    }
}
