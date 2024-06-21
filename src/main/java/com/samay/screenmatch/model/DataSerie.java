package com.samay.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jdk.jfr.Category;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataSerie(

        @JsonAlias("Title")
        String title,

        @JsonAlias("Genre")
        String gender,

        @JsonAlias("Actors")
        String actors,

        @JsonAlias("Poster")
        String poster,

        @JsonAlias("Plot")
        String synopsis,

        Integer totalSeasons,

        @JsonAlias("imdbRating")
        String assessment



) {
}
