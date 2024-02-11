package com.drivingschool.controller;

import com.drivingschool.domain.Question;
import com.drivingschool.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class QuestionsController {

    final QuestionService questionService;

    @Autowired
    public QuestionsController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping(value = "/questions")
    public ResponseEntity<List<Question>> getTestData(
            @RequestParam String textSubstring
    ) {
        List<Question> questions = questionService.findAll(textSubstring);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

}
