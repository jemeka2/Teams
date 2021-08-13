package com.example.springboot_security_bookedition;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

//    @NotEmpty
//    @NotNull
    private String name;

//    @NotEmpty
//    @NotNull
    private String sport;

//    @NotEmpty
//    @NotNull
    private String city;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Player> players;

    public Team( String name,String city, Set<Player> players, String sport) {
        this.name = name;
        this.city = city;
        this.players = players;
        this.sport = sport;
    }

    public Team() {
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public Set<Player> getPlayers() {
        return players;
    }
    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public String getSport() {
        return sport;
    }
    public void setSport(String sport) {
        this.sport = sport;
    }
}
