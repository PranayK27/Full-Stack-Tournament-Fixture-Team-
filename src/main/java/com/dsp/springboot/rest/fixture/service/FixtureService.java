package com.dsp.springboot.rest.fixture.service;

import com.dsp.springboot.rest.fixture.Fixture;
import org.springframework.stereotype.Service;

@Service
public interface FixtureService {
    public void createFixtures(Fixture fixture);
}


