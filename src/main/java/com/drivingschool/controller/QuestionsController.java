package com.drivingschool.controller;

import com.drivingschool.annotations.RequestParams;
import com.drivingschool.domain.Question;
import com.drivingschool.dto.QuestionDto;
import com.drivingschool.filter.QuestionFilter;
import com.drivingschool.service.QuestionService;
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

    public record TestRec(String abc){
        public TestRec {
        }
    }

    @Autowired
    public QuestionsController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public ResponseEntity<List<Question>> getQuestion(
            Pageable pageable,
            @RequestParams QuestionFilter filter
    ) {
        Page<Question> questions = questionService.findAll(filter, pageable);
        return Util.getPaginatedResponse(questions);
    }

    @PostMapping
    public ResponseEntity<QuestionDto> saveQuestion(
            @RequestBody QuestionDto questionDto
    ) {
        QuestionDto question = questionService.save(questionDto);
        return ResponseEntity.ok().body(question);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteQuestion(
            @PathVariable Long id
    ) {
        questionService.delete(id);
        return ResponseEntity.ok().build();
    }


}
