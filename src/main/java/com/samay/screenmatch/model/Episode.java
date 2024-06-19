package com.samay.screenmatch.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episode {

    private Integer season;
    private String title;
    private Integer number;
    private Double assessment;
    private LocalDate releaseDate;

    public Episode(Integer numberSeason, DataEpisode dataEpisode) {
        this.season = numberSeason;
        this.title = dataEpisode.title();
        this.number = dataEpisode.number();

        try {
            this.assessment = Double.valueOf(dataEpisode.assessment());
        }catch (NumberFormatException e){
            this.assessment = 0.0;
        }

        try {
            this.releaseDate = LocalDate.parse(dataEpisode.releaseDate());
        }catch (DateTimeParseException e){
            this.releaseDate = null;
            // this.releaseDate = LocalDate.parse("00-00-00");
        }

    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getAssessment() {
        return assessment;
    }

    public void setAssessment(Double assessment) {
        this.assessment = assessment;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "season= " + season +
                ", title= " + title +
                ", number= " + number +
                ", assessment= " + assessment +
                ", releaseDate= " + releaseDate;

    }
}
