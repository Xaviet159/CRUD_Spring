package be.xavier.demoAPI.bll.services.iservices;

import be.xavier.demoAPI.dl.entities.User;

import java.util.List;

public interface IUserService {

    List<User> getAll();
    User getById(Long id);
    Long create(User user);
    void update(Long id, User user);
    void delete(Long id);
}
