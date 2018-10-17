package com.serachvana.lpk.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @PostMapping("/")
    public String getAnswerPost() {
        return "ok";
    }

    @GetMapping("/")
    public String getAnswerGet() {
        return "ok";
    }
}
