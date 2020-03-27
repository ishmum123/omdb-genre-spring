package com.newscred.omdb.genre.database.repositories;

import com.newscred.omdb.genre.database.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
