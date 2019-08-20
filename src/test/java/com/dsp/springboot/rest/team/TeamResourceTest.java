package com.dsp.springboot.rest.team;

import com.dsp.springboot.rest.fixture.FixtureRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.xml.ws.WebServiceException;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class TeamResourceTest
{
    @Mock
    TeamRepository teamRepository;
    @Mock
    FixtureRepository fixtureRepository;

    @InjectMocks
    private TeamResource teamResource;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void retrieveTeams()
    {
        List<Team> allTeams = asList(
                new Team(1L, "Boston Celtics", 123, 456),
                new Team(2L, "Toronto Raptors", 123, 456)
        );
        List<Team> filteredTeams = allTeams.stream().filter(team -> team.getId() == 1).collect(Collectors.toList());

        when(teamRepository.findAll()).thenReturn(allTeams);
        when(teamRepository.filterTeams("BOSTON")).thenReturn(Optional.of(filteredTeams));

        List<Team> results = teamResource.retrieveTeams("");
        assertEquals(results.size(), 2);

        results = teamResource.retrieveTeams("Boston");
        assertEquals(results.size(), 1);
    }

    @Test
    public void retrieveTeam()
    {
        Team bostonTeam = new Team(1L, "Boston Celtics", 123, 456);
        when(teamRepository.findById(1L)).thenReturn(Optional.of(bostonTeam));

        assertEquals(teamResource.retrieveTeam(1), bostonTeam);

        exceptionRule.expect(WebServiceException.class);
        exceptionRule.expectMessage("Team Not Found Exception: 2");
        teamResource.retrieveTeam(2);
    }

    @Test
    public void deleteTeam()
    {
        teamResource.deleteTeam(1);
        verify(teamRepository, times(1)).deleteById(1L);
    }

    @Test
    public void createTeam()
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Team bostonTeam = new Team(1L, "Boston Celtics", 123, 456);

        when(teamRepository.save(bostonTeam)).thenReturn(bostonTeam);

        ResponseEntity<Object> savedTeamResponse = teamResource.createTeam(bostonTeam);

        verify(teamRepository, times(1)).save(bostonTeam);
        assertEquals(HttpStatus.CREATED, savedTeamResponse.getStatusCode());
        assertTrue(savedTeamResponse.getHeaders().getFirst("Location")
                .endsWith(Long.toString(bostonTeam.getId())));
    }

    @Test
    public void updateTeam()
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Team existingBostonTeam = new Team(1L, "Boston Celtics", 123, 456);
        Team newBostonTeam = new Team(1L, "Boston Celtics", 1234, 5678);

        when(teamRepository.findById(1L)).thenReturn(Optional.of(existingBostonTeam));

        ResponseEntity<Object> savedTeamResponse = teamResource.updateTeam(newBostonTeam, 1);

        verify(teamRepository, times(1)).save(newBostonTeam);
        assertEquals(HttpStatus.NO_CONTENT, savedTeamResponse.getStatusCode());

        ResponseEntity<Object> notFoundTeamResponse = teamResource.updateTeam(newBostonTeam, 2);

        assertEquals(HttpStatus.NOT_FOUND, notFoundTeamResponse.getStatusCode());
    }
}
