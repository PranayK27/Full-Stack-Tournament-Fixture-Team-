package com.dsp.springboot.rest.team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query(value = "SELECT * FROM TEAM WHERE UPPER(NAME) LIKE %?1%", nativeQuery = true)
    Optional<List<Team>> filterTeams(String teamQuery);
}
