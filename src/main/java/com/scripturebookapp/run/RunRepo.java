package com.scripturebookapp.run;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class RunRepo {
    private List<Run> runs = new ArrayList<>();

    // get all runs
    public List<Run> findAll() {
        return runs;
    }
    // get run by id
    Run findById(Integer id) {
        return runs.stream()
            .filter(run -> run.id().equals(id))
            .findFirst()
            .get();
    }

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
