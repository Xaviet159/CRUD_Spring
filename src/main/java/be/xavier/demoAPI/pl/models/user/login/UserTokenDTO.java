package be.xavier.demoAPI.pl.models.user.login;

import be.xavier.demoAPI.dl.entities.User;
import be.xavier.demoAPI.dl.enums.UserRole;

public record UserTokenDTO(
        Long id,
        String username,
        UserRole role,
        String token
) {

    public static UserTokenDTO fromEntity(User user, String token) {
        return new UserTokenDTO(user.getId(),user.getUsername(),user.getRole(),token);
    }
}