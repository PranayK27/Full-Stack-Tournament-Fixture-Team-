package com.dsp.springboot.rest.tournament.service;

import java.sql.SQLException;

import com.dsp.springboot.rest.fixture.Fixture;
import com.dsp.springboot.rest.tournament.exception.TournamentException;
import org.springframework.stereotype.Service;

import com.dsp.springboot.rest.tournament.Tournament;

@Service
public interface TournamentService {
	
	void createTournament(Tournament tournament) throws SQLException;
    void createFixtures(Long tournamentId) throws TournamentException;
}
