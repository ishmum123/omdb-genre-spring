package com.newscred.omdb.genre.helpers.dataclass;

import lombok.Data;

import java.io.Writer;
import java.time.Year;
import java.util.List;

@Data
public class Movie {
    private final String Title;
    private final String Year;
    private final String Rated;
    private final String Released;
    private final String Runtime;
    private final String Genre;
    private final String Director;
    private final String Writer;
    private final String Actors;
    private final String Plot;
    private final String Language;
    private final String Country;
    private final String Awards;
    private final String Poster;
    private final List<Rating> Ratings;
    private final String Metascore;
    private final String imdbRating;
    private final String imdbVotes;
    private final String imdbID;
    private final String Type;
    private final String DVD;
    private final String BoxOffice;
    private final String Production;
    private final String Website;
    private final String Response;
}
