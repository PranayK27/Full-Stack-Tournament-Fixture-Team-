package com.dsp.springboot.rest.tournament;

import com.dsp.springboot.rest.fixture.Fixture;
import com.dsp.springboot.rest.fixture.FixtureRepository;
import com.dsp.springboot.rest.tournament.FixtureCalculator;
import com.dsp.springboot.rest.tournament.Tournament;
import com.dsp.springboot.rest.tournament.TournamentRepository;
import com.dsp.springboot.rest.tournament.exception.TournamentException;
import com.dsp.springboot.rest.tournament.service.TournamentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;
import javax.xml.ws.WebServiceException;

@RestController
@RequestMapping("/tournaments")
public class TournamentResource {

    @Autowired
    private TournamentService tournamentService;


    //TODO: Create new tournament POST endpoint
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createTournament(@RequestBody Tournament tournament) {
        ResponseEntity entity = null;
        try {
            tournamentService.createTournament(tournament);
            entity =  ResponseEntity.ok().body(HttpStatus.CREATED);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @GetMapping
    public List<Tournament> listTournaments() {
        //return tournamentRepository.findAll();
    	return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tournament> getTournament(@PathVariable("id") long id) {
//    	return tournamentRepository.findById(id)
//        .map(tournament -> new ResponseEntity<>(tournament, HttpStatus.OK))
//        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    	return null;
    }
    

    /*return Collections.singletonList(
        		new Tournament(1, "BasketBall C", 1001, 1)); */

    @PostMapping("/{tournamentId}/generateFixtures")
    public ResponseEntity generateFixtures(@PathVariable("tournamentId") long tournamentId) {
        try {
            tournamentService.createFixtures(tournamentId);
        } catch (TournamentException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(HttpStatus.CREATED);
    }
}
