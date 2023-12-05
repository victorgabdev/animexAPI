package com.meusite.animex.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "anime")
public class Anime {

    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    private String title;
    private String description;

    @ManyToMany
    @JoinTable(
            name = "anime_genre",
            joinColumns = @JoinColumn(name = "anime_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres = new ArrayList<>();

    @OneToMany(mappedBy = "anime")
    private List<Episode> episodes = new ArrayList<>();

    public Anime() {}

    public Anime(String title, String description) {
        id = UUID.randomUUID();
        this.title = title;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }

    public void addGenre(Genre genre) {
        if (genre != null) {
            this.genres.add(genre);
            genre.getAnimes().add(this);
        }
    }

    public void removeGenre(Genre genre) {
        this.genres.remove(genre);
        genre.getAnimes().remove(this);
    }

    public void addEpisode(Episode episode) {
        if (episode != null) {
            this.episodes.add(episode);
            episode.setAnime(this);
        }
    }

    public void removeEpisode(Episode episode) {
        this.episodes.remove(episode);
        episode.setAnime(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Anime anime)) return false;
        return Objects.equals(getTitle(), anime.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle());
    }

    @Override
    public String toString() {
        return "Anime{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
