package com.mobee.repository;

import com.mobee.entity.MovieApi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<MovieApi, Long> {
}
