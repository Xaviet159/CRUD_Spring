package be.xavier.demoAPI.pl.models.post;

import be.xavier.demoAPI.dl.entities.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record PostForm(
        @Size(max = 1000)
        String content
) {
    public Post toEntity(){
        return new Post(content);
    }
}
