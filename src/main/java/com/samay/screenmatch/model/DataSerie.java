package com.samay.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataSerie(

        @JsonAlias("Title")
        String title,

        Integer totalSeasons,

        @JsonAlias("imdbRating")
        String assessment

) {
}
