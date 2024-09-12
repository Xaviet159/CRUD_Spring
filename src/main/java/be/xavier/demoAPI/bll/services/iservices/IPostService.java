package be.xavier.demoAPI.bll.services.iservices;

import be.xavier.demoAPI.dl.entities.Post;

import java.util.List;

public interface IPostService {

    List<Post> getAll();
    Post getById(Long id);
    Long create(Post post);
    void update(Long id, Post post);
    void delete(Long id);
}
