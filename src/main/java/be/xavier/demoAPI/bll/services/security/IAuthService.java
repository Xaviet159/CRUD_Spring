package be.xavier.demoAPI.bll.services.security;


import be.xavier.demoAPI.dl.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IAuthService extends UserDetailsService {

    User register(User user);
    User login(String username, String password);
}
