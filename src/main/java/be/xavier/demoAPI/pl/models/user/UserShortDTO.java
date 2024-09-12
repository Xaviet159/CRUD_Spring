package be.xavier.demoAPI.pl.models.user;

import be.xavier.demoAPI.dl.entities.User;

public record UserShortDTO (
        Long id,
        String username,
        String email,
        String firstname,
        String lastname
) {
    public static UserShortDTO fromEntity(User user){
        return new UserShortDTO(user.getId(), user.getUsername(), user.getEmail(), user.getFirstname(),
                user.getLastname());
    }
}
