package ru.bell.practice.springboot.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class PingController {

    @RequestMapping("/ping")
    public String ping() {
        return "pong";
    }
}