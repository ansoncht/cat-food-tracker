package com.ansoncht.catfoodtracker.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Objects;

import com.ansoncht.catfoodtracker.user.User;

public class UserDTO {
    @NotBlank
    private String id;

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

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public UserDTO() {}

    public UserDTO(String id, String username, String email, String firstName, String lastName,
            LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static UserDTO fromUser(User user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getFirstName(),
                user.getLastName(), user.getEmail(), user.getCreatedAt(), user.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.username, this.email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(this.id, userDTO.id)
                && Objects.equals(this.username, userDTO.username)
                && Objects.equals(this.email, userDTO.email);
    }

    @Override
    public String toString() {
        return "UserDTO{" + "id='" + this.id + '\'' + ", username='" + this.username + '\''
                + ", email='" + this.email + '\'' + ", firstName='" + this.firstName + '\''
                + ", lastName='" + this.lastName + '\'' + ", createdAt=" + this.createdAt
                + ", updatedAt=" + this.updatedAt + '}';
    }
}
