package be.xavier.demoAPI.bll.services.iservices;

import be.xavier.demoAPI.dl.entities.Like;

import java.util.List;

public interface ILikeService {

    List<Like> getAll();
    Like getById(Long id);
    Long create(Like like);
    void update(Long id, Like like);
    void delete(Long id);
}
