package com.dsp.springboot.rest.tournament.service.impl;

import java.sql.SQLException;

import com.dsp.springboot.rest.fixture.Fixture;
import com.dsp.springboot.rest.fixture.FixtureRepository;
import com.dsp.springboot.rest.fixture.service.FixtureService;
import com.dsp.springboot.rest.fixture.util.FixtureUtils;
import com.dsp.springboot.rest.tournament.exception.TournamentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dsp.springboot.rest.tournament.Tournament;
import com.dsp.springboot.rest.tournament.TournamentRepository;
import com.dsp.springboot.rest.tournament.service.TournamentService;

@Component
public class TournamentServiceImpl implements TournamentService{
	
	@Autowired
	private TournamentRepository repository;

	@Autowired
	private FixtureService fixtureService;

	@Autowired
	private FixtureRepository fixtureRepository;

	@Autowired
	private FixtureUtils fixtureUtils;

	@Override
	public void createTournament(Tournament tournament) throws SQLException {
		repository.saveAndFlush(tournament);
		
	}

	@Override
	public void createFixtures(Long tournamentId) throws TournamentException{
      if(!findTournamentId(tournamentId)){
      	throw new TournamentException("Sorry bro,Tournamnet not exist");
	  }
     //fixtureRepository.saveAndFlush(fixture);
      fixtureUtils.getFixtures(tournamentId);

	}

	private boolean findTournamentId(long tournamentId) {
		return repository.existsById(tournamentId);
	}

}
