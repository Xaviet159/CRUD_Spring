package be.xavier.demoAPI.dl.entities;

import be.xavier.demoAPI.dl.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@NoArgsConstructor @AllArgsConstructor
@ToString @EqualsAndHashCode
@Entity
@Table(name = "user_")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(length = 100)
    private String username;
    @Setter
    @Column(length = 100)
    private String firstname;
    @Setter
    @Column(length = 100)
    private String lastname;
    @Setter
    @Column(length = 100)
    private String email;
    @Setter
    @Column(length = 100)
    private String password;
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private UserRole role;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;
    @OneToMany(mappedBy = "user")
    private List<Comment> comments;
    @OneToMany(mappedBy = "user")
    private List<Like> likes;
    @OneToMany(mappedBy = "user")
    private List<FriendShip> friendShips;
    @OneToMany(mappedBy = "friend")
    private List<FriendShip> friendsOf;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
    public User(String username, String email, String firstname, String lastname){
        this.username = username;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
}
