package com.scripturebookapp.run;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Optional;

import jakarta.annotation.PostConstruct;

@Repository
public class RunController {
    private List<Run> runs = new ArrayList<>();

    // get
    // get all runs
    @GetMapping
    List<Run> findAll() {
        return runRepo.findAll();
    }
    // get run by id
    @GetMapping("/{id}")
    Run findById(@PathVariable Integer id) {
        Optional<Run> run = runRepo.findById(id);
        if(run.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Run not found.");
        }
        return run.get();
    }

    // post
    // what is an annotation??
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void create(@RequestBody Run run) {
        runRepo.create(run);
    }
    // put 
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@RequestBody Run run, @PathVariable Integer id) {
        runRepo.update(run,id);
    }

    // delete
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        runRepo.delete(id);
    }

    // data
    @PostConstruct
    private void init() {
        runs.add(new Run(
            1,
            "Morning Run",
            java.time.LocalDateTime.now().minusDays(1),
            java.time.LocalDateTime.now().minusDays(1).plusMinutes(45),
            5
        ));
        runs.add(new Run(
            2,
            "Evening Run",
            java.time.LocalDateTime.now().minusDays(2),
            java.time.LocalDateTime.now().minusDays(2).plusMinutes(60),
            4
        ));
    }
}