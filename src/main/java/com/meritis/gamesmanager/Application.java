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
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(Application.class, args);

		TournamentService tournamentService = applicationContext.getBean(TournamentService.class);

		tournamentService.createGroups(4,4);
		tournamentService.scheduleGroups();
		tournamentService.playGroups();
		tournamentService.getGroupResults();
	}
}
