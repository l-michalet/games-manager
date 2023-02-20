package com.meritis.gamesmanager;

import com.meritis.gamesmanager.configuration.DatabaseManager;
import com.meritis.gamesmanager.service.TournamentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootApplication
@EnableConfigurationProperties(DatabaseManager.class)
public class Application {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(Application.class, args);

		TournamentService tournamentService = applicationContext.getBean(TournamentService.class);

		List<Integer> teamInfoIds = IntStream.rangeClosed(1, 16).boxed().collect(Collectors.toCollection(ArrayList::new));
		tournamentService.createTournament("New tournament", teamInfoIds, 4);

	}
}
