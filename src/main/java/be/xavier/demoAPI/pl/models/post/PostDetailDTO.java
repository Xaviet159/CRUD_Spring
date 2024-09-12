package be.xavier.demoAPI.pl.models.post;

import be.xavier.demoAPI.dl.entities.Post;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record PostDetailDTO(
        Long id,
        String content,
        LocalDate localDate
) {

    public static PostDetailDTO toEntity(Post post){
        return new PostDetailDTO(post.getId(), post.getContent(),
                post.getCreationDate().toLocalDate());
    }
}
