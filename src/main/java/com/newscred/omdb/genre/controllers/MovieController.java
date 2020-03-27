package com.newscred.omdb.genre.controllers;

import com.newscred.omdb.genre.database.entities.Movie;
import com.newscred.omdb.genre.helpers.exceptions.ServiceExceptionHolder.MovieRequestException;
import com.newscred.omdb.genre.services.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("movies")
@RequiredArgsConstructor
@Slf4j
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public List<Movie> getAllMovies() {
        log.debug("REST request to get all Movies");
        return movieService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable Long id) {
        log.debug("REST request to get Movie : {}", id);

        Optional<Movie> optionalMovie = movieService.findOne(id);

        return optionalMovie.map(movie -> ResponseEntity.ok().body(movie))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Movie> addMovie(@Valid @RequestBody Movie movie) throws URISyntaxException {
        log.debug("REST request to save Movie : {}", movie);
        if (movie.getId() != null) {
            throw new MovieRequestException(200, "A movie with id " + movie.getId() + " already exists");
        }
        Movie newMovie = movieService.save(movie);
        return ResponseEntity.created(new URI("/movies/" + newMovie.getId()))
                .body(newMovie);
    }

    @PutMapping
    public ResponseEntity<Movie> updateMovie(@Valid @RequestBody Movie movie) {
        log.debug("REST request to update Movie : {}", movie);
        if (movie.getId() == null) {
            throw new MovieRequestException(400, "Movie Id can not be null");
        }
        Movie updatedMovie = movieService.save(movie);
        return ResponseEntity.ok().body(updatedMovie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        log.debug("REST request to delete Movie : {}", id);
        return ResponseEntity.noContent().build();
    }
}
