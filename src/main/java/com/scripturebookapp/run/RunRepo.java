package com.scripturebookapp.run;

import java.util.List;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;

@Repository
public class RunRepo {
    private final List<Run> runs = new ArrayList<>();

    // endpoint to return array of runs
    // findAll()
    @GetMapping("/runs")
    List<Run> findAll() {
        return runs;
    }
    // endpoint to return specific run
    // findById(int id)
    @GetMapping("/runs/{id}")
    // PathVariable makes int dynamic
    Optional<Run> findById(@PathVariable Integer id) {
        return runs.stream()
        .filter(r -> r.id()
        .equals(id))
        .findFirst();
    }
    // endpoint to make run
    // create(Run run)
    void create(Run run) {
        runs.add(run);
    }

    // endpoint to update run
    // update(Run run, int id)
    void update(Run run, @PathVariable Integer id) {
        Optional<Run> existingRun = findById(id);
        if (existingRun.isPresent()) {
            runs.set(runs.indexOf(existingRun.get()), run);
        }
    }

    // endpoint to delete run
    // delete(int id)
    void delete(@PathVariable Integer id) {
        Optional<Run> run = findById(id);
        // if check
        if (run.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        runs.remove(run);
    }
}
