package com.newscred.omdb.genre.database.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String title;

    @Column
    private String language;

    @Column
    private String year;


    public Movie title(String title) {
        this.title = title;
        return this;
    }

    public Movie language(String language) {
        this.language = language;
        return this;
    }

    public Movie year(String year) {
        this.year = year;
        return this;
    }
}
