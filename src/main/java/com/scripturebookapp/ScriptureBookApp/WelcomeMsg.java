package com.scripturebookapp.ScriptureBookApp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeMsg {

    @GetMapping("/hello")
    public String getWelcomeMsg() {
        return "Welcome msg baby!";
    }
}