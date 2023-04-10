package com.meritis.gamesmanager.controller;

import com.meritis.gamesmanager.model.Results;
import com.meritis.gamesmanager.repository.ResultsRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ResultsApi {

    private final ResultsRepository resultsRepository;

    public ResultsApi(ResultsRepository resultsRepository) {
        this.resultsRepository = resultsRepository;
    }

    @GetMapping("/results/{teamId}")
    @ApiOperation(value = "Find results by teamId", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the results"),
            @ApiResponse(code = 404, message = "Team not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<Results> findResults(@PathVariable("teamId") String teamId) {
        System.out.format("ResultsApi | findResults teamId=%s", teamId);
        Results results = resultsRepository.findByTeamId(teamId)
                .orElseThrow(RuntimeException::new);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @GetMapping("/results")
    @ApiOperation(value = "List all results", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the results"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<List<Results>> listResults() {
        System.out.format("ResultsApi | listResults");
        return new ResponseEntity<>(resultsRepository.findAll(), HttpStatus.OK);
    } //TODO: By tournament / group
}