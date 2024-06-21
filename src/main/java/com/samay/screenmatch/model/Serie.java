package com.samay.screenmatch.model;

import com.samay.screenmatch.service.QueryMyMemory;

import java.util.Optional;
import java.util.OptionalDouble;

public class Serie {

    private String title;
    private Category gender;
    private String actors;
    private String poster;
    private String synopsis;
    private Integer totalSeasons;
    private Double assessment;

    public Serie(DataSerie dataSerie){
        this.title = dataSerie.title();
        this.totalSeasons = dataSerie.totalSeasons();
        this.assessment = OptionalDouble.of(Double.parseDouble(dataSerie.assessment())).orElse(0);
        this.gender = Category.fromString(dataSerie.gender().split(",")[0].trim());
        this.actors = dataSerie.actors();
        this.poster = dataSerie.poster();
        this.synopsis = QueryMyMemory.getTranslation(dataSerie.synopsis()).trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getGender() {
        return gender;
    }

    public void setGender(Category gender) {
        this.gender = gender;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Integer getTotalSeasons() {
        return totalSeasons;
    }

    public void setTotalSeasons(Integer totalSeasons) {
        this.totalSeasons = totalSeasons;
    }

    public Double getAssessment() {
        return assessment;
    }

    public void setAssessment(Double assessment) {
        this.assessment = assessment;
    }

    @Override
    public String toString() {
        return "gender= " + gender +
                ", title= " + title +
                ", actors= " + actors +
                ", poster= " + poster +
                ", synopsis= " + synopsis +
                ", totalSeasons= " + totalSeasons +
                ", assessment= " + assessment;
    }
}
