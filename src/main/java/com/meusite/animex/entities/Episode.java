package com.meusite.animex.entities;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity(name = "episode")
public class Episode {

    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    private String title;

    private Integer number;

    private String resume;

    @ManyToOne
    @JoinColumn(name = "anime_id")
    private Anime anime;

    public Episode() {}

    public Episode(String title, Integer number, String resume, Anime anime) {
        id = UUID.randomUUID();
        this.title = title;
        this.number = number;
        this.resume = resume;
        this.anime = anime;
    }

    public UUID getId() {
        return id;
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

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public Anime getAnime() {
        return anime;
    }

    public void setAnime(Anime anime) {
        this.anime = anime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Episode episode)) return false;
        return Objects.equals(getTitle(), episode.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle());
    }

    @Override
    public String toString() {
        return "Episode{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", number=" + number +
                ", resume='" + resume + '\'' +
                ", anime=" + anime +
                '}';
    }
}
