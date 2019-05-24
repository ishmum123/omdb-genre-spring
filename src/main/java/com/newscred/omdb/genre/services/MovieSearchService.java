package com.newscred.omdb.genre.services;

import com.newscred.omdb.genre.helpers.dataclass.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MovieSearchService {

    public List<Movie> getMovies(String search) {
        /*final String logFile = "src/main/resources/formatted_descriptions";
        final SparkSession spark = SparkSession.builder().appName("OMDb").getOrCreate();
        final Dataset<String> dataset = spark.read().textFile(logFile).cache();

        dataset.filter(line -> line())

        return spark.read()*/
        return new ArrayList<>();

    }
}
