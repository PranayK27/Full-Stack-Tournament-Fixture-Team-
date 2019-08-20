package com.dsp.springboot.rest.tournament;

import com.dsp.springboot.rest.fixture.Fixture;
import com.dsp.springboot.rest.team.Team;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name="tournament_teams")
    private List<Team> teams;

    @OneToMany(mappedBy = "tournament")
    private List<Fixture> fixtures;

    public Tournament() {
        super();
    }
    public Tournament(Long id, String name, List<Team> teams, List<Fixture> fixtures)
    {
    	this.id=id;
    	this.name=name;
    	this.teams=teams;
    	this.fixtures=fixtures;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Team> getTeams() {
        return this.teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Fixture> getFixtures() {
        return this.fixtures;
    }
}
