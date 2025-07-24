package com.scripturebookapp.run;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
// define the base path
@RequestMapping("/api") 
public class RunController {

    private static final Logger log = LoggerFactory.getLogger(RunController.class);
    private final RunRepo runRepo;

    // constructor
    public RunController(RunRepo runRepo) {
        this.runRepo = runRepo;
        log.info("RunController initialized");
    }
    // endpoint to return array of runs
    @GetMapping("/runs")
    List<Run> findAll() {
        log.info("GET /api/runs endpoint called");
        return runRepo.findAll();
    }
    // endpoint to return specific run
    @GetMapping("/runs/{id}")
    // PathVariable makes int dynamic
    Run findById(@PathVariable Integer id) {
        Optional<Run> run = runRepo.findById(id);
        if (run.isEmpty()) {
            log.warn("Run with id {} not found", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Run not found");
        } else {
            return run.get();
        }
    }
}
