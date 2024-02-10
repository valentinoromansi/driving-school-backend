package com.drivingschool.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class MainController {

    @GetMapping(value = "/questions")
    public ResponseEntity<String> getTestData() {
        return new ResponseEntity<>("ssssdddd", HttpStatus.OK);
    }

}
