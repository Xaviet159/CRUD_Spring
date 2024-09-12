package be.xavier.demoAPI.dl.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@RequiredArgsConstructor
@ToString @EqualsAndHashCode
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String content;
   @CreationTimestamp
   private LocalDateTime creationDate;

   @ManyToOne
   @JoinColumn(name = "user_id")
   private User user;

   @OneToMany(mappedBy = "post")
   private List<Comment> comments;

   @OneToMany(mappedBy = "post")
   private List<Like> likes;

    public Post( String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
