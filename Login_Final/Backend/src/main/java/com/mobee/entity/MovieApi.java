package com.mobee.entity;


import com.sun.scenario.effect.Identity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@AllArgsConstructor
@Table(name="mobee.mobee_movie_api")
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

    @Builder
    public MovieApi(Long contents_num, String overview, String title, Float vote_average, String poster_path) {
        this.contents_num = contents_num;
        this.overview = overview;
        this.title = title;
        this.vote_average = vote_average;
        this.poster_path = poster_path;
    }

    public MovieApi() {

    }
}
