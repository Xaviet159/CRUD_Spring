package be.xavier.demoAPI.pl.models.user;

import be.xavier.demoAPI.dl.entities.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserForm(
        @NotBlank
        @Size(max = 100)
        String username,
        @Size(max = 100)
        String email,
        @Size(max = 100)
        String firstname,
        @Size(max = 100)
        String lastname
) {
    public User toEntity() {
        return new User(username, email, firstname, lastname);
    }
}
