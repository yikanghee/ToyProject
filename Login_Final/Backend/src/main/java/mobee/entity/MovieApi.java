package mobee.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name="mobee.MovieApi")
public class MovieApi {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column
    private Long contents_num;

    @Column(length = 10000)
    private String overview;

    @Column
    private String title;

    @Column
    private Float vote_average;

    @Column
    private String poster_path;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

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
}
