package com.scripturebookapp.run;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
// define the base path
@RequestMapping("/api") 
public class RunRepo {
    private static final Logger log = LoggerFactory.getLogger(RunRepo.class);
    private final List<Run> runs = new ArrayList<>();
    private final RunRepo runRepo;

    // constructor
    public RunRepo(RunRepo runRepo) {
        this.runRepo = runRepo;
        log.info("RunController initialized");
    }
    // endpoint to return array of runs
    // findAll()
    @GetMapping("/runs")
    List<Run> findAll() {
        log.info("GET /api/runs endpoint called");
        return runRepo.findAll();
    }
    // endpoint to return specific run
    // findById(int id)
    @GetMapping("/runs/{id}")
    // PathVariable makes int dynamic
    Run findById(@PathVariable Integer id) {
        Run run = runRepo.findById(id);
        if (run == null) {
            log.warn("Run with id {} not found", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Run not found");
        } 
        return run;
    }
    // endpoint to make run
    // create(Run run)
    void create(Run run) {
        runs.add(run);
    }

    // endpoint to update run
    // update(Run run, int id)
    void update(Run run, @PathVariable Integer id) {
        Run existingRun = runRepo.findById(id);
        // if check
        if (existingRun == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        runs.set(runs.indexOf(existingRun), run);
    }

    // endpoint to delete run
    // delete(int id)
    void delete(@PathVariable Integer id) {
        Run run = runRepo.findById(id);
        // if check
        if (run == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        runs.remove(run);
    }
}
