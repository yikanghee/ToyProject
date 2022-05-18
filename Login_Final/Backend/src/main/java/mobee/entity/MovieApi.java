package mobee.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name="mobee.MovieApi")
public class MovieApi {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column
    private Long contents_num;

    @Column(length = 10000)
    private String description;

    @Column
    private String title;

    @Column
    private Float vote_average;

    @Column
    private String imgUrl;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "movieApi", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Builder.Default
    private Set<Heart> hearts = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "movieApi", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Builder.Default
    private Set<Comment> comments = new HashSet<>();

    public void addComment(Comment comment){
        this.comments.add(comment);
        comment.setMovieApi(this);
    }

    public void deleteComment(Comment comment){
        this.comments.remove(comment);
        comment.setMovieApi(null);
    }

    public void addHeart(Heart heart) {
        this.hearts.add(heart);
        heart.setMovieApi(this);
    }

    public void deleteHeart(Heart heart) {
        this.hearts.remove(heart);
        heart.setMovieApi(null);
    }
//    public MovieApi(Long contents_num, String overview, String title, Float vote_average, String poster_path, Date createdAt, Date) {
//        this.contents_num = contents_num;
//        this.overview = overview;
//        this.title = title;
//        this.vote_average = vote_average;
//        this.poster_path = poster_path;
//        this.createdAt =
//    }
//
//    public MovieApi() {
//
//    }

//    private Long id;
//
//    private String imgUrl;
//
//    @Column(nullable = false)
//    private String title;
//
//    private String bookElement;
//
//    @Lob
//    @Column(nullable = false)
//    private String description;

}
