package mobee.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;


    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @Builder.Default
    private Set<Heart> hearts = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Builder.Default
    private Set<Comment> comments = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    public void addComment(Comment comment){
        this.comments.add(comment);
        comment.setAccount(this);
    }

    public void deleteComment(Comment comment){
        this.comments.remove(comment);

    }

    public void addHeart(Heart heart) {
        this.hearts.add(heart);
        heart.setAccount(this);
    }

    public void deleteHeart(Heart heart) {
        this.hearts.remove(heart);
        heart.setAccount(null);
    }

}
