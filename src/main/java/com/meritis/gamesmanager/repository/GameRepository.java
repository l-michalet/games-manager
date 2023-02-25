package com.meritis.gamesmanager.repository;

import com.meritis.gamesmanager.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

    List<Game> findAllByGroupNameAndGroupDay(String groupName, Integer groupDay);
    List<Game> findAllByGroupName(String groupName);
    List<Game> findAllByGroupDay(Integer groupDay);
    List<Game> findAll();
}
