package com.ansoncht.catfoodtracker.user.dto;

import java.util.Objects;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserLoginDTO {
    @NotBlank
    @Size(max = 100)
    private String usernameOrEmail;

    @NotBlank
    @Size(min = 8, max = 100)
    private String password;

    public UserLoginDTO() {}

    public UserLoginDTO(String usernameOrEmail, String password) {
        this.usernameOrEmail = usernameOrEmail;
        this.password = password;
    }

    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.usernameOrEmail);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof UserLoginDTO))
            return false;
        UserLoginDTO other = (UserLoginDTO) obj;
        return Objects.equals(this.usernameOrEmail, other.usernameOrEmail);
    }

    @Override
    public String toString() {
        return "UserLoginDTO{" + "usernameOrEmail='" + this.usernameOrEmail + '\''
                + ", password='[PROTECTED]'" + '}';
    }
}
