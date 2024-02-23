package com.meritis.gamesmanager;

import com.meritis.gamesmanager.model.request.TournamentRequest;
import com.meritis.gamesmanager.service.TournamentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(Application.class, args);

		TournamentService tournamentService = applicationContext.getBean(TournamentService.class);
		TournamentRequest tournamentRequest = new TournamentRequest();
		tournamentRequest.setName("First tournament");
		tournamentRequest.setNbOfGroups(4);
		tournamentRequest.setTeamIds(IntStream.rangeClosed(1, 16).mapToObj(Long::valueOf).collect(Collectors.toList()));
		tournamentRequest.setStartDate(LocalDate.now());
		tournamentRequest.setDirectElimination(false);
		tournamentService.createTournament(tournamentRequest);
	}
}
