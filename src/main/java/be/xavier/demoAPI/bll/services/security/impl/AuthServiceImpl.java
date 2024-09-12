package be.xavier.demoAPI.bll.services.security.impl;

import be.xavier.demoAPI.bll.services.security.IAuthService;
import be.xavier.demoAPI.dal.irepositories.IUserRepository;
import be.xavier.demoAPI.dl.entities.User;
import be.xavier.demoAPI.dl.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final IUserRepository IUserRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public User register(User user) {
        if(IUserRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("User with username " + user.getUsername() + " already exists");
        }
        user.setRole(UserRole.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return IUserRepository.save(user);
    }

    @Override
    public User login(String username, String password) {
        User existingUser = IUserRepository.findByUsername(username).orElseThrow();
        if(!passwordEncoder.matches(password, existingUser.getPassword())) {
            throw new RuntimeException("Incorrect password");
        }
        return existingUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return IUserRepository.findByUsername(username).orElseThrow();
    }
}
