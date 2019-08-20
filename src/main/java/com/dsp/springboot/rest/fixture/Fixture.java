package com.dsp.springboot.rest.fixture;

import com.dsp.springboot.rest.team.Team;
import com.dsp.springboot.rest.tournament.Tournament;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Fixture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    private Team homeTeam;
    @ManyToOne
    private Team awayTeam;
    @ManyToOne
    private Team winningTeam;
    private int week;

    @ManyToOne
    private Tournament tournament;

    public Fixture() {
        super();
    }

    public Fixture(Tournament tournament, Team homeTeam, Team awayTeam, int week) {
        super();
        this.tournament = tournament;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.week = week;
    }

    public Long getId() {
        return id;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public Team getWinningTeam() {
        return winningTeam;
    }

    public int getWeek() {
        return week;
    }

    @JsonIgnore
    public Tournament getTournament() {
        return tournament;
    }

    public Long getTournamentId() {
        return tournament.getId();
    }


}
