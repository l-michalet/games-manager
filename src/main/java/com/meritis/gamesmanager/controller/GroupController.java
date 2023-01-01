package com.meritis.gamesmanager.controller;


import com.meritis.gamesmanager.service.GroupService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

//    @GetMapping("/groups/prepare")
//    public ResponseEntity<List<GroupResponse>> prepareGroups() {
//        System.out.format("[GroupController] prepareGroups \n");
//        return new ResponseEntity<>(groupService.createGroups(4,4), HttpStatus.OK);
//    }
//
//    @GetMapping("/groups/schedule")
//    public ResponseEntity<List<GroupDaysResponse>> scheduleGroups() {
//        System.out.format("[GroupController] scheduleGroups \n");
//        return new ResponseEntity<>(groupService.scheduleGroups(), HttpStatus.OK);
//    }
}
