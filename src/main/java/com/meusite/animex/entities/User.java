package com.meusite.animex.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.*;

@Entity(name = "anime_user")
public class User {


    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;
    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Watchlist> watchlists = new ArrayList<>();

    public User() {}

    public User(String name, String email, String password) {
        id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Watchlist> getWatchlists() {
        return watchlists;
    }

    public void addWatchlist(Watchlist watchlist) {
        if (watchlist != null) {
            watchlist.setUser(this);
            this.watchlists.add(watchlist);
        }
    }

    public void removeWatchlist(Watchlist watchlist) {
        this.watchlists.remove(watchlist);
        watchlist.setUser(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(getEmail(), user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
