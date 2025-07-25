package com.scripturebookapp.run;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/runs")
class RunController {

    private final RunRepo runRepo;

    RunController(RunRepo runRepo) {
        this.runRepo = runRepo;
    }

    // find all
    @GetMapping
    List<Run> findAll() {
        // method from RunRepo
        return runRepo.findAll();
    }

    // find by id
    @GetMapping("/{id}")
    Run findById(@PathVariable Integer id) {
        // method from RunRepo
        Optional<Run> run = runRepo.findById(id);
        if(run.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return run.get();
    }

    // create
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void create(@RequestBody Run run) {
        // method from RunRepo
        runRepo.create(run);
    }

    // update
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@RequestBody Run run, @PathVariable Integer id) {
        // method from RunRepo
        runRepo.update(run,id);
    }

    // delete
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        // method from RunRepo
        runRepo.delete(id);
    }

}