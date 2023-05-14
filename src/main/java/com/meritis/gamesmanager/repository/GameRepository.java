package com.meritis.gamesmanager.repository;

import com.meritis.gamesmanager.model.GroupGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<GroupGame, Integer> {

    List<GroupGame> findAllByGroupNameAndGroupDay(String groupName, Integer groupDay);
    List<GroupGame> findAllByGroupName(String groupName);
    List<GroupGame> findAllByGroupDay(Integer groupDay);
    List<GroupGame> findAll();
}
