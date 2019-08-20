package com.dsp.springboot.rest.fixture.service.impl;

import com.dsp.springboot.rest.fixture.Fixture;
import com.dsp.springboot.rest.fixture.FixtureRepository;
import com.dsp.springboot.rest.fixture.service.FixtureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FixtureServiceImpl implements FixtureService {

    @Autowired
    private FixtureRepository fixtureRepository;

    @Override
    public void createFixtures(Fixture fixture) {
        fixtureRepository.saveAndFlush(fixture);
    }

    private boolean validateFixtures(Fixture fixture){

        return false;
    }
}
