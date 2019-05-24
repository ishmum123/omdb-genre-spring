package com.newscred.omdb.genre.controllers;

import com.newscred.omdb.genre.helpers.exceptions.ServiceExceptionHolder;
import com.newscred.omdb.genre.services.MovieSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("movies")
public class MovieSearchController {

    private final MovieSearchService movieSearchService;

    @GetMapping
    public List<Map<String, Object>> getMovies(@RequestParam(value = "search", defaultValue = "") final String search,
                                               @RequestParam(value = "page", defaultValue = "1") int page) {
        if (search.contains(","))
            throw new ServiceExceptionHolder.IllegalSearchParamException("Query String must not contain char: ','");

        return movieSearchService.getMovies(search, page);
    }
}
