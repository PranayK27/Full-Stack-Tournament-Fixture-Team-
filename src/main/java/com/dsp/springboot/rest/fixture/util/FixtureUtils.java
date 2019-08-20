package com.dsp.springboot.rest.fixture.util;

import com.dsp.springboot.rest.fixture.Fixture;
import com.dsp.springboot.rest.fixture.FixtureRepository;
import com.dsp.springboot.rest.team.Team;
import com.dsp.springboot.rest.team.TeamRepository;
import com.dsp.springboot.rest.tournament.Tournament;
import com.dsp.springboot.rest.tournament.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class FixtureUtils {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private FixtureRepository fixtureRepository;
    @Autowired
    private TournamentRepository tournamentRepository;
    private List<Team> teams = new LinkedList<>();
    private Long homeTeam;
    private Long awayTeam;
    private Map<String,Long> teamsMap = new HashMap<>();


    private List<Team> getAllTeams() {
        this.teams = teamRepository.findAll();
        for (int i = 0; i < teams.size(); i++) {
            teamsMap.put(String.valueOf(i+1),teams.get(i).getId());
        }
        return teams;
    }

    public void getFixtures(Long tournamentId) {

        int teams = getAllTeams().size();


        // Generate the schedule using round robin algorithm.
        int totalRounds = (teams - 1) * 2;
        int matchesPerRound = teams / 2;
        String[][] rounds = new String[totalRounds][matchesPerRound];

        int halfRoundMark = (totalRounds / 2);


        for (int round = 0; round < totalRounds; round++) {
            for (int match = 0; match < matchesPerRound; match++) {
                int home = (round + match) % (teams - 1);
                int away = (teams - 1 - match + round) % (teams - 1);

                // Last team stays in the same place while the others
                // rotate around it.
                if (match == 0) {
                    away = teams - 1;
                }
                String roundString;
                if (round < halfRoundMark) {
                    roundString = (home + 1) +","+  (away + 1);
                } else {
                    roundString = (away + 1) +","+(home + 1) ;
                }
                rounds[round][match] = roundString;

            }
        }

        // Display the rounds
        for (int i = 0; i < rounds.length; i++) {
            System.out.println("Week " + (i + 1));
            int week = i + 1;
            System.out.println(Arrays.asList(rounds[i]));
            getTeams(Arrays.asList(rounds[i]),tournamentId,week);

            System.out.println();
        }


    }

    private void getTeams(List<String> teams,Long tournamentId,int week){
        for(int i = 0;i<teams.size();i++){
            String[] teamsString = teams.get(i).split(",");
            this.homeTeam = teamsMap.get(teamsString[0]);
            this.awayTeam = teamsMap.get(teamsString[1]);
            Optional<Team> homeTeam = teamRepository.findById(this.homeTeam);
            Optional<Team> awayTeam = teamRepository.findById(this.awayTeam);
            Optional<Tournament> tournament = tournamentRepository.findById(tournamentId);
            Fixture fixture = new Fixture(tournament.get(),homeTeam.get(),awayTeam.get(),week);
            fixtureRepository.saveAndFlush(fixture);
        }

    }

}