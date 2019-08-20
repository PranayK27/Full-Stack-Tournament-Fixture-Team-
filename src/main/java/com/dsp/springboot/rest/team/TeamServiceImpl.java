package com.dsp.springboot.rest.team;

import com.dsp.springboot.rest.fixture.Fixture;
import com.dsp.springboot.rest.fixture.FixtureRepository;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class TeamServiceImpl implements TeamService {

    @Autowired
    private FixtureRepository fixtureRepository;
    EntityManager em;

    @Override
    public List<Fixture> findFixturesByTeam(long teamId) {

        List<Fixture> homeTeamList = fixtureRepository.getFixturesByTeamId(teamId).get();

        System.out.println(homeTeamList.size());

        return homeTeamList;
    }
}
