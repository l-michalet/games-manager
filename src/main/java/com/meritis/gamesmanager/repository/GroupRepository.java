package com.meritis.gamesmanager.repository;

import com.meritis.gamesmanager.model.Group;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GroupRepository {
    private static final List<Group> groups = new ArrayList<>();

    public void save(Group group) {
        int i = groups.indexOf(group);
        if (i == -1) {
            groups.add(group);
        } else {
            groups.set(i, group);
        }
    }

    public List<Group> findAll() {
        return groups;
    }

    public Group findById(Integer id) {
        return groups.get(id);
    }
}
