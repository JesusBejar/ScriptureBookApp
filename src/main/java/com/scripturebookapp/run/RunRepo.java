package com.scripturebookapp.run;
import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.PostConstruct;

public class RunRepo {
    private List<Run> runs = new ArrayList<>();

    // get all runs
    public List<Run> findAll() {
        return runs;
    }
}
