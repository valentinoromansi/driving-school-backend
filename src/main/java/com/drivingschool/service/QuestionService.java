package com.drivingschool.service;

import com.drivingschool.domain.Question;
import com.drivingschool.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> findAll(String textSubstring) {
        return questionRepository.findAllByTextContaining_query(textSubstring);
    }
}
