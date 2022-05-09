package com.mobee.entity;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@AllArgsConstructor
public class MovieApi {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Long contents_num;

    @Column
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
