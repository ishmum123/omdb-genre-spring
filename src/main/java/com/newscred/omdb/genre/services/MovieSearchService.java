package com.newscred.omdb.genre.services;

import com.newscred.omdb.genre.helpers.dataclass.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MovieSearchService {

    public List<Movie> getMovies(String search) {
        return null;
    }
}
