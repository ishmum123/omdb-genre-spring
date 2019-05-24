package com.newscred.omdb.genre.controllers;

import com.newscred.omdb.genre.helpers.dataclass.Movie;
import com.newscred.omdb.genre.helpers.exceptions.ServiceExceptionHolder;
import com.newscred.omdb.genre.services.MovieSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("movie")
public class MovieSearchController {

    private final MovieSearchService movieSearchService;

    @GetMapping
    public List<Movie> getMovies(@RequestParam("search") final String search,
                                 @RequestParam(value = "page", defaultValue = "1") final int page) {
        if (search.contains(","))
            throw new ServiceExceptionHolder.IllegalSearchParamException("Query String must not contain char: ','");

        return movieSearchService.getMovies(search);
    }
}
