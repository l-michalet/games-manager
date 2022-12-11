package com.meritis.gamesmanager;

import com.meritis.gamesmanager.repository.GameRepository;
import com.meritis.gamesmanager.repository.GroupRepository;
import com.meritis.gamesmanager.repository.TeamRepository;
import com.meritis.gamesmanager.repository.TeamResultsRepository;
import com.meritis.gamesmanager.service.GameService;
import com.meritis.gamesmanager.service.GroupService;
import com.meritis.gamesmanager.service.ResultsService;
import com.meritis.gamesmanager.service.TeamService;
import com.meritis.gamesmanager.service.TournamentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		ResultsService resultsService = new ResultsService(new TeamResultsRepository());
		GameService gameService = new GameService(new GameRepository(), resultsService);
		TeamService teamService = new TeamService(new TeamRepository());
		TournamentService tournamentService = new TournamentService(new GroupService(new GroupRepository(), gameService, teamService, resultsService));

		tournamentService.createGroups(4,4);
		tournamentService.scheduleGroups();
		tournamentService.playGroups();
		tournamentService.getGroupResults();
	}
}
