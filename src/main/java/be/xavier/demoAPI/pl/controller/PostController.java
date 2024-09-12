package be.xavier.demoAPI.pl.controller;

import be.xavier.demoAPI.bll.services.iservices.IPostService;
import be.xavier.demoAPI.pl.models.post.PostDetailDTO;
import be.xavier.demoAPI.pl.models.post.PostForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final IPostService iPostService;
    @GetMapping("/list")
    public ResponseEntity<List<PostDetailDTO>> getAllPost(){
        List<PostDetailDTO> posts =  iPostService.getAll().stream()
                .map(PostDetailDTO::toEntity)
                .toList();
        return ResponseEntity.ok(posts);
    }

    @PostMapping("/add")
    public ResponseEntity<PostDetailDTO> createPost(@RequestBody PostForm form){
        Long id = iPostService.create(form.toEntity());
        UriComponents uriComponents = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @DeleteMapping("/{id:^\\d+}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id){
        iPostService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id:^\\d+}")
    public ResponseEntity<PostDetailDTO> updatePost(@PathVariable Long id, @Valid @RequestBody PostForm form){
        iPostService.update(id, form.toEntity());
        return ResponseEntity.noContent().build();
    }
}
