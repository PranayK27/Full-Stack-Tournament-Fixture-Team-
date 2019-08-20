package com.dsp.springboot.rest.team;

import com.dsp.springboot.rest.fixture.Fixture;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeamService {
    public List<Fixture> findFixturesByTeam(long teamId);
}
