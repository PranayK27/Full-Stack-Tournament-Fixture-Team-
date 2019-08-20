package com.dsp.springboot.rest.tournament;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface TournamentRepository extends JpaRepository<Tournament, Long> {
	//public String getTournament();
}
