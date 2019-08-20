package com.dsp.springboot.rest.team;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.dsp.springboot.rest.fixture.Fixture;
import com.dsp.springboot.rest.fixture.FixtureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.xml.ws.WebServiceException;

@RestController
public class TeamResource {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private FixtureRepository fixtureRepository;

    @Autowired
    private TeamService teamService;

    @GetMapping("/teams")
    public List<Team> retrieveTeams(@RequestParam(value = "name", required = false) String query) {
        return query.isEmpty() ? teamRepository.findAll() :
                teamRepository.filterTeams(query.toUpperCase()).orElseGet(ArrayList::new);
    }

    @GetMapping("/teams/{id}")
    public Team retrieveTeam(@PathVariable long id) {
        Optional<Team> team = teamRepository.findById(id);

        if (!team.isPresent())
            throw new WebServiceException("Team Not Found Exception: " + id);

        return team.get();
    }

    @DeleteMapping("/teams/{id}")
    public void deleteTeam(@PathVariable long id) {
        teamRepository.deleteById(id);
    }

    @PostMapping("/teams")
    public ResponseEntity<Object> createTeam(@RequestBody Team team) {
        Team savedTeam = teamRepository.save(team);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedTeam.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/teams/{id}")
    public ResponseEntity<Object> updateTeam(@RequestBody Team team, @PathVariable long id) {
        Optional<Team> teamOptional = teamRepository.findById(id);

        if (!teamOptional.isPresent())
            return ResponseEntity.notFound().build();

        team.setId(id);

        teamRepository.save(team);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/teams/{id}/fixtures")
    public ResponseEntity<List<Fixture>> getTeamFixtures(@PathVariable long id) {
       return ResponseEntity.ok().body(teamService.findFixturesByTeam(id));
    }
}
