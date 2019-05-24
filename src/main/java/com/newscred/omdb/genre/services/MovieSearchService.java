package com.newscred.omdb.genre.services;

import com.newscred.omdb.genre.helpers.dataclass.Movie;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MovieSearchService {

    private final Dataset<Row> dataset;

    public MovieSearchService() {
        final String logFile = "src/main/resources/formatted_descriptions";
        final SparkSession spark = SparkSession.builder().appName("OMDb").config("spark.master", "local").getOrCreate();
        dataset = spark.read().json(logFile);
    }

    public List<Map<String, Object>> getMovies(String search, int page) {
        return dataset.filter(dataset.col("Genre").contains(search)).collectAsList().stream()
                .map(row -> {
                    final Map<String, Object> map = new HashMap<>();
                    for (Field field: Movie.class.getDeclaredFields())
                        map.put(field.getName(), row.getAs(row.fieldIndex(field.getName())));
                    return map;
                })
                .collect(Collectors.toList())
                .subList((page - 1) * 50, page * 50);
    }
}
