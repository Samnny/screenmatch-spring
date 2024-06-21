package com.samay.screenmatch.model;

import com.samay.screenmatch.service.QueryMyMemory;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@Entity
@Table(name = "series")
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;

    @Enumerated(EnumType.STRING)
    private Category gender;

    private String actors;
    private String poster;
    private String synopsis;
    private Integer totalSeasons;
    private Double assessment;

    @Transient
    private List<Episode> episodes = new ArrayList<>();

    public Serie(){}

    public Serie(DataSerie dataSerie){
        this.title = dataSerie.title();
        this.totalSeasons = dataSerie.totalSeasons();
        this.assessment = OptionalDouble.of(Double.parseDouble(dataSerie.assessment())).orElse(0);
        this.gender = Category.fromString(dataSerie.gender().split(",")[0].trim());
        this.actors = dataSerie.actors();
        this.poster = dataSerie.poster();
        this.synopsis = QueryMyMemory.getTranslation(dataSerie.synopsis()).trim();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
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
