package be.xavier.demoAPI.pl.models.user.login;

import be.xavier.demoAPI.dl.entities.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegisterForm(
        @NotBlank
        @Size(max = 50)
        String username,
        @NotBlank
        String password
) {
    public User toEntity() {
        return new User(username, password);
    }
}

