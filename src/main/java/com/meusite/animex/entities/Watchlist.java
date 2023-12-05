package com.meusite.animex.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "watchlist")
public class Watchlist {

    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "anime_id")
    private Anime anime;

    private LocalDate dateAdded;  // data que o anime foi adicionado


    // progresso do usuario no anime
    // avaliacoes pessoais

    public Watchlist() {}

    public Watchlist(User user, Anime anime) {
        id = UUID.randomUUID();
        this.user = user;
        this.anime = anime;
        dateAdded = LocalDate.now();
    }

    public UUID getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
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
        if (!(o instanceof Watchlist watchlist)) return false;
        return Objects.equals(getAnime(), watchlist.getAnime()) && Objects.equals(getDateAdded(), watchlist.getDateAdded());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAnime(), getDateAdded());
    }

    @Override
    public String toString() {
        return "Watchlist{" +
                "id=" + id +
                ", user=" + user +
                ", anime=" + anime +
                ", dateAdded=" + dateAdded +
                '}';
    }
}
