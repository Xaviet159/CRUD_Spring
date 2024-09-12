package be.xavier.demoAPI.bll.services.iservices;

import be.xavier.demoAPI.dl.entities.Comment;

import java.util.List;

public interface ICommentService {


    List<Comment> getAll();
    Comment getById(Long id);
    Long create(Comment comment);
    void update(Long id, Comment comment);
    void delete(Long id);
}
