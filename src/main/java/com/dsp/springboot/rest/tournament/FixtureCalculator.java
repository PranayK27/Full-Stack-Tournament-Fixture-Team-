package com.dsp.springboot.rest.tournament;

import com.dsp.springboot.rest.fixture.Fixture;

import java.util.Collections;
import java.util.List;

public class FixtureCalculator {

    private Tournament tournament;

    public FixtureCalculator(Tournament tournament) {
        this.tournament = tournament;
    }

    public List<Fixture> calculateFixtures() {
        // TODO: Implement schedule creation here.
        return Collections.emptyList();
    }

}
