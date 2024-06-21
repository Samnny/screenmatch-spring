package com.samay.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataResponse(
        @JsonAlias(value = "translatedText")
        String textTranslated
) {
}
