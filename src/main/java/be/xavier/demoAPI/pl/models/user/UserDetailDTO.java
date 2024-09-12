package be.xavier.demoAPI.pl.models.user;

import be.xavier.demoAPI.dl.entities.User;
import jakarta.persistence.Column;

public record UserDetailDTO(
        Long id,
        String username,
        String firstname,
        String lastname,
        String email,
        String password
) {
    public static UserDetailDTO fromEntity(User user){
        return new UserDetailDTO(user.getId(), user.getUsername(), user.getFirstname(), user.getLastname(),
                user.getEmail(), user.getPassword());
    }
}
