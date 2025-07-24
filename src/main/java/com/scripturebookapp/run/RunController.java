package com.scripturebookapp.run;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/runs")
    // function to return array of runs
    List<Run> findAll() {
        log.info("GET /api/runs endpoint called");
        return runRepo.findAll();
    }
}
