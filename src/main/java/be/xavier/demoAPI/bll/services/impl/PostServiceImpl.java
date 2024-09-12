package be.xavier.demoAPI.bll.services.impl;

import be.xavier.demoAPI.bll.services.iservices.IPostService;
import be.xavier.demoAPI.dal.irepositories.IPostRepository;
import be.xavier.demoAPI.dl.entities.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements IPostService {

    private IPostRepository postRepository;

    @Override
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @Override
    public Post getById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Le post avec l'id" + id + "est introuvable"));
    }

    @Override
    public Long create(Post post) {
        return postRepository.save(post).getId();
    }

    @Override
    public void update(Long id, Post post) {
        Post existingPost = getById(id);
        existingPost.setContent(post.getContent());
        postRepository.save(existingPost);
    }

    @Override
    public void delete(Long id) {
    Post deletePost = getById(id);
    postRepository.delete(deletePost);
    }
}
