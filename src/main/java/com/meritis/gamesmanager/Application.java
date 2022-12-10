package com.meritis.gamesmanager;

import com.meritis.gamesmanager.repository.GroupRepository;
import com.meritis.gamesmanager.repository.TeamRepository;
import com.meritis.gamesmanager.service.GameService;
import com.meritis.gamesmanager.service.GroupService;
import com.meritis.gamesmanager.service.TournamentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		TournamentService tournamentService = new TournamentService(new GroupService(new TeamRepository(), new GroupRepository(), new GameService()));
		tournamentService.createGroups(4,4);
		tournamentService.scheduleGroups();
		tournamentService.playGroups();
	}
}
