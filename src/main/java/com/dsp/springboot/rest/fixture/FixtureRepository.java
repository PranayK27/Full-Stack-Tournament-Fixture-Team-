package com.dsp.springboot.rest.fixture;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FixtureRepository extends JpaRepository<Fixture, Long> {
    // TODO: Implement correct query here
    @Query(value = "SELECT * FROM fixture f where f.home_team_id = 'teamId' OR f.away_team_id = 'teamId' ", nativeQuery = true)
    Optional<List<Fixture>> getFixturesByTeamId(long teamId);
}
