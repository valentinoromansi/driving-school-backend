package com.drivingschool.controller;

import com.drivingschool.domain.Question;
import com.drivingschool.service.QuestionService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.drivingschool.util.Util;

import java.util.List;

@RestController
@RequestMapping(value = "/api/questions")
public class QuestionsController {

    final QuestionService questionService;


    @Autowired
    public QuestionsController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public ResponseEntity<List<Question>> getQuestion(
            @RequestParam String textSubstring,
            Pageable pageable
    ) {
        Page<Question> questions = questionService.findAll(textSubstring, pageable);
        return Util.getPaginatedResponse(questions);
    }

    @PostMapping
    public ResponseEntity<Question> saveQuestion(
            @RequestBody Question question
    ) {
        questionService.save(question);
        return ResponseEntity.ok().body(question);
    }


}
