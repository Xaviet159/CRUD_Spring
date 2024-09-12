package be.xavier.demoAPI.dl.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
    @Column(nullable = false, length = 1000)
   private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
   private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
   private Post post;

}
