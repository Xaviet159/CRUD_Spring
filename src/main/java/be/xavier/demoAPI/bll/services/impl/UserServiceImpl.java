package be.xavier.demoAPI.bll.services.impl;

import be.xavier.demoAPI.bll.services.iservices.IUserService;
import be.xavier.demoAPI.dal.irepositories.IUserRepository;
import be.xavier.demoAPI.dl.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserRepository IUserRepository;
    @Override
    public List<User> getAll() {
        return IUserRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return IUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id" + id + "not fund"));
    }

    @Override
    public Long create(User user) {

        return IUserRepository.save(user).getId();
    }

    @Override
    public void update(Long id, User user) {
        User existingUser = getById(id);
        existingUser.setUsername(user.getUsername());
        existingUser.setFirstname(user.getFirstname());
        existingUser.setLastname(user.getLastname());
        existingUser.setEmail(user.getEmail());
        IUserRepository.save(existingUser);
    }

    @Override
    public void delete(Long id) {
        User existingUser = getById(id);
        IUserRepository.delete(existingUser);
    }

}
